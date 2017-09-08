package site.root3287.sudo2.net.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.net.packet.Packet;
import site.root3287.sudo2.net.packet.PacketReceiveEvent;
import site.root3287.sudo2.net.packet.PacketReceiveEventType;

public class Client{
	private InetAddress destAddress;
	private int port;
	private DatagramSocket socket;
	private Thread receive;
	private boolean running = false;
	private byte[] data = new byte[1024*10];
	private EventDispatcher receiveDispatcher = new EventDispatcher(new PacketReceiveEventType());
	public Client(String address, int port){
		try {
			this.destAddress = InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.port = port;
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start(){
		this.running = true;
		this.receive = new Thread(()->listen(), "run");
		this.receive.start();
	}
	
	private void listen() {
		while(running){
			try {
				DatagramPacket p = new DatagramPacket(data, data.length);
				this.socket.receive(p);
				receiveDispatcher.execute(new PacketReceiveEvent(new Packet(p)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void send(final String data){
		send(data, this.destAddress, this.port);
	}
	
	public void send(final String data, InetAddress dest, int port){
		System.out.println("Attempting to send "+ new String(data.getBytes()));
		try {
			this.socket.send(new DatagramPacket(data.getBytes(), data.getBytes().length, dest, port));
		} catch (IOException e) {
			System.out.println("Cannot send");
		}
	}
}
