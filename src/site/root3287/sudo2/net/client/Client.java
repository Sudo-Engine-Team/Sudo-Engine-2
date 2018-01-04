package site.root3287.sudo2.net.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.net.packet.Packet;
import site.root3287.sudo2.net.packet.events.PacketReceiveEvent;
import site.root3287.sudo2.net.packet.events.PacketReceiveEventType;

public class Client{
	private static int BUFFER_SIZE = 1064*20;
	private InetAddress destAddress;
	private int port;
	private DatagramSocket socket;
	private Thread receive;
	private boolean running = false;
	private byte[] data = new byte[BUFFER_SIZE];
	private EventDispatcher receiveDispatcher = new EventDispatcher(new PacketReceiveEventType());
	
	/**
	 * Connects to a remote host
	 * @param address - address of the remote host
	 * @param port - port of the remote host.
	 */
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
	
	/**
	 * Starts a connections with the remote host
	 */
	public void start(){
		this.running = true;
		this.receive = new Thread(()->listen(), "run");
		this.receive.setDaemon(true);
		this.receive.start();
	}
	
	public void stop() {
		//TODO: tell the server that the client has disconnected.
		this.running = false;
	}
	
	/**
	 * Recieve packets from the remote host, once a packet is recived it will trigger a packet receive event.
	 */
	private void listen() {
		while(running){
			try {
				DatagramPacket p = new DatagramPacket(data, data.length);
				this.socket.receive(p);
				receiveDispatcher.execute(new PacketReceiveEvent(new Packet(p)));
				this.data = new byte[BUFFER_SIZE];
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Send data to the connected remote host
	 * @param data - data of the packet
	 */
	public void send(final String data){
		send(this.destAddress, this.port, data);
	}
	
	
	/**
	 * Send data to a specified hsot.
	 * @param data - message
	 * @param dest - remote host address
	 * @param port - remote host port
	 */
	public void send(InetAddress dest, int port, final String data){
		//System.out.println("Attempting to send "+ new String(data.getBytes()));
		try {
			this.socket.send(new DatagramPacket(data.getBytes(), data.getBytes().length, dest, port));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
