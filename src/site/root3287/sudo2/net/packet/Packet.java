package site.root3287.sudo2.net.packet;

import java.net.Socket;

public class Packet {
	private String packet;
	private Socket socket;
	public Packet(String p, Socket serverClient){
		this.packet = p;
		this.socket = serverClient;
	}
	public String getPacket(){
		return packet;
	}
	public Socket getSocket(){
		return socket;
	}
}
