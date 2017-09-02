package site.root3287.sudo2.net.event;

import java.net.DatagramPacket;

import site.root3287.sudo2.events.Event;

public class PacketReceiveEvent extends Event{
	private DatagramPacket packet;
	public PacketReceiveEvent(DatagramPacket p) {
		super(new PacketReceiveEventType());
		this.packet = p;
	}
	public DatagramPacket getPacket(){
		return packet;
	}

}
