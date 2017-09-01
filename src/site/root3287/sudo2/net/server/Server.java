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

public class Server {
	private int port;
	private DatagramSocket socket;
	private Thread run, manage, send, receive;
	private final int MAX_ATTEMPTS = 10;
	
	List<ServerClients> clients = new ArrayList<>();
	
	public Server(int port){
		Application.SERVER_LOGGER.log(Level.INFO, "Creating a server on port "+port);
		try {
			this.socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(JSONObject data, InetAddress addr, int port){
		send(data.toString(), addr, port);
	}
	
	public void send(String data, InetAddress addr, int port){
		send = new Thread("Send") {
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
}
