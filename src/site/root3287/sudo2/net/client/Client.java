package site.root3287.sudo2.net.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;

import org.json.JSONObject;

import site.root3287.sudo2.display.Application;

public class Client {
	
	private InetAddress serverAddress;
	private int serverPort;
	
	private Thread send;
	
	private DatagramSocket socket;
	
	public String user = "root";
	
	public Client(String serverAddr, int serverPort){
		Application.getClientLogger().log(Level.INFO, "Creating a client to "+serverAddr+":"+serverPort);
		this.serverPort = serverPort;
		try {
			this.serverAddress = InetAddress.getByName(serverAddr);
			this.socket = new DatagramSocket();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connect(){
		send(new JSONObject().append("type", "CONNECTION").append("username", user));
	}
	
	public void send(JSONObject data){
		send(data.toString());
	}
	
	public String receive() {
		byte[] data = new byte[1024*10];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String message = new String(packet.getData());
		return message;
}
	
	public void send(final String data){
		this.send = new Thread("SUDO-Client-Send"){
			public void run() {
				DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, serverAddress, serverPort);
				try {
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		this.send.start();
	}
	
	public void close() {
		new Thread() {
			public void run() {
				synchronized (socket) {
					socket.close();
				}
			}
		}.start();
	}
}
