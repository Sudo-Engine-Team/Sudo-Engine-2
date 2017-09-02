package site.root3287.sudo2.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.json.JSONObject;

import site.root3287.sudo2.display.Application;

public class Server implements Runnable{
	private int port;
	private DatagramSocket socket;
	private Thread run, manage, send, receive;
	private final int MAX_ATTEMPTS = 10;
	private boolean running;
	
	List<ServerClients> clients = new ArrayList<>();
	
	public Server(int port){
		Application.getServerLogger().log(Level.INFO, "Creating a server on port "+port);
		try {
			this.socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		run = new Thread(this,"Sudo-Server-main");
	}
	
	public void send(JSONObject data, InetAddress addr, int port){
		send(data.toString(), addr, port);
	}
	
	public void send(String data, InetAddress addr, int port){
		send = new Thread("Sudo-Server-Send") {
			public void run() {
				DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, addr, port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		send.start();
	}
	
	public void sendAll(String data){
		for(ServerClients c : clients){
			send(data, c.address, c.port);
		}
	}

	@Override
	public void run() {
		running = true;
		Application.getServerLogger().log(Level.INFO, "Starting server on port "+this.port);
		manageClients();
		receive();
	}
	
	public void manageClients(){
		manage = new Thread("SUDO-Server-Manager"){
			public void run(){
				while(running){
					//TODO: Disconnection
				}
			}
		};
		manage.start();
	}
	
	public void receive(){
		receive = new Thread("Sudo-Server-Receiver"){
			public void run(){
				while (running) {
					byte[] data = new byte[1024*10];
					DatagramPacket packet = new DatagramPacket(data, data.length);
					try {
						socket.receive(packet);
					} catch (SocketException e) {
					} catch (IOException e) {
						e.printStackTrace();
					}
					//process(packet)
				}
			}
		};
	}
	
	private void process(DatagramPacket packet) {
		JSONObject data = new JSONObject(packet.getData());
		
	}
}
