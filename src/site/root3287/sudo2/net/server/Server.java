package site.root3287.sudo2.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.net.packet.Packet;
import site.root3287.sudo2.net.packet.PacketReceiveEvent;
import site.root3287.sudo2.net.packet.PacketReceiveEventType;

public class Server{
	private DatagramSocket socket;
	private Thread listen;
	private boolean running = false;
	private byte[] receiveDataBuffer = new byte[1024*10];
	private int port;
	private EventDispatcher receiveEvent = new EventDispatcher(new PacketReceiveEventType());
	public Server(int port){
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.port = port;
	}
	public void start(){
		System.out.println("Starting server on "+ this.port);
		running = true;
		this.listen = new Thread(()->listen(), "listen");
		this.listen.start();
	}
	private void listen() {
		while(running){
			DatagramPacket p = new DatagramPacket(receiveDataBuffer, receiveDataBuffer.length);
			try {
				socket.receive(p);
				receiveEvent.execute(new PacketReceiveEvent(new Packet(p)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void send(String dest, int destPort, final byte[] data){
		try {
			this.socket.send(new DatagramPacket(data, data.length, InetAddress.getByName(dest), destPort));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addReceiveListener(Listener l){
		this.receiveEvent.addListener(l);
	}
}
