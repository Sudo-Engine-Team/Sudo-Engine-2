package site.root3287.sudo2.test.net;

import java.io.IOException;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.net.packet.events.PacketReceiveEvent;
import site.root3287.sudo2.net.server.Server;

public class ServerTest {
	public static void main(String[] args) {
		Server s = new Server(25565);
		s.packetReceiver.addListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				if(!(e instanceof PacketReceiveEvent)){
					return false;
				}
				System.out.println(((PacketReceiveEvent)e).getPacket().getSocket().getInetAddress().toString().replace('/', ' ').trim()
						+":"+
						((PacketReceiveEvent)e).getPacket().getSocket().getPort()
						+" > Server" + ((PacketReceiveEvent)e).getPacket().getPacket());
				return true;
			}
		});
		s.start();
		char in = ' ';
		while (in != 'q'){
			try {
				in = (char) System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
