package site.root3287.sudo2.net.packet;

import java.net.DatagramPacket;

public class Packet {
	private DatagramPacket packet;
	public Packet(DatagramPacket p){
		this.packet = p;
	}
	public DatagramPacket getDatagramPacket(){
		return packet;
	}
}
