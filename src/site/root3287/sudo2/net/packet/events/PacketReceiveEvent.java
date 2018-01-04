package site.root3287.sudo2.net.packet.events;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.net.packet.Packet;

public class PacketReceiveEvent extends Event{

	private Packet packet;
	
	public PacketReceiveEvent(Packet p) {
		super(new PacketReceiveEventType());
		packet = p;
	}

	public Packet getPacket() {
		return packet;
	}

	public void setPacket(Packet packet) {
		this.packet = packet;
	}

}
