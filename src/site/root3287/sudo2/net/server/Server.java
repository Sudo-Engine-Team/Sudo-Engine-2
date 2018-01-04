package site.root3287.sudo2.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.net.packet.Packet;
import site.root3287.sudo2.net.packet.events.PacketReceiveEvent;
import site.root3287.sudo2.net.packet.events.PacketReceiveEventType;

public class Server{
	private static int BUFFER_SIZE = 1024*20;
	private DatagramSocket socket;
	private Thread listen;
	private boolean running = false;
	private byte[] receiveDataBuffer = new byte[BUFFER_SIZE];
	private int port;
	private EventDispatcher receiveEvent = new EventDispatcher(new PacketReceiveEventType());
	
	/**
	 * Starts a server on a dedicated port
	 * @param port
	 */
	public Server(int port){
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.port = port;
	}
	
	/**
	 * Start the server
	 */
	public void start(){
		System.out.println("Starting server on "+ this.port);
		running = true;
		this.listen = new Thread(()->listen(), "listen");
		this.listen.setDaemon(true);
		this.listen.start();
	}
	
	public void stop() {
		//TODO: tell the list of clients that the server has stopped
		this.running = false;
	}
	
	/**
	 * Listens for a packet from any calient and returns a packet recived event.
	 */
	private void listen() {
		while(running){
			DatagramPacket p = new DatagramPacket(receiveDataBuffer, receiveDataBuffer.length);
			try {
				socket.receive(p);
				receiveEvent.execute(new PacketReceiveEvent(new Packet(p)));
				receiveDataBuffer = new byte[BUFFER_SIZE]; //Clear the buffer
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * send message to any client
	 * @param dest - address to the remote host.
	 * @param destPort - port of the the remote host.
	 * @param data - message to be sent
	 */
	public void send(String dest, int destPort, final byte[] data){
		try {
			this.socket.send(new DatagramPacket(data, data.length, InetAddress.getByName(dest), destPort));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * add a listener event to the list of listeners
	 * @param l - listener of the event
	 */
	public void addReceiveListener(Listener l){
		this.receiveEvent.addListener(l);
	}
}
