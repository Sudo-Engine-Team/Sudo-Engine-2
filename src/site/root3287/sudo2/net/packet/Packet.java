package site.root3287.sudo2.net.packet;

import java.net.Socket;

public class Packet {
	private String data;
	private Socket socket;
	public Packet(String p, Socket serverClient){
		this.data = p;
		this.socket = serverClient;
	}
	public String getData(){
		return data;
	}
	public Socket getSocket(){
		return socket;
	}
}
