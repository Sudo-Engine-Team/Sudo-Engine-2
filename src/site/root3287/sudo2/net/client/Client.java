package site.root3287.sudo2.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.net.packet.Packet;
import site.root3287.sudo2.net.packet.events.PacketReceiveEvent;
import site.root3287.sudo2.net.packet.events.PacketReceiveEventType;

public class Client{
	private InetAddress destAddress;
	private int port;
	private Socket socket;
	private Thread receive;
	private boolean running;
	private EventDispatcher receiveDispatcher = new EventDispatcher(new PacketReceiveEventType());
	private BufferedReader in;
	private PrintWriter out;
	
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
			this.socket = new Socket(this.destAddress, this.port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Starts a connections with the remote host
	 */
	public void start(){
		this.running = true;
		this.receive = new Thread(()->listen(), "listen");
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
			String line = "";
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				line = in.readLine();
				if(line == null)
					break;
				receiveDispatcher.execute(new PacketReceiveEvent(new Packet(line, socket)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//SEND TO SERVER CLOSED OR DISCONNECTION SCREEN.
	}

	/**
	 * Send data to the connected remote host
	 * @param data - data of the packet
	 */
	public void send(final String data){
		out.println(data);
		System.out.println(data);
	}
	
	public void addReceiveListener(Listener l){
		this.receiveDispatcher.addListener(l);
	}
}
