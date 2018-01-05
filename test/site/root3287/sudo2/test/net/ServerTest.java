package site.root3287.sudo2.test.net;

import java.util.Scanner;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.net.packet.events.PacketReceiveEvent;
import site.root3287.sudo2.net.server.Server;

public class ServerTest {
	public static void main(String[] args) {
		Server s = new Server(3306);
		s.addReceiveListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				if(e instanceof PacketReceiveEvent) {
					System.out.println("Packet Recived!");
					String message ="";
					for(byte b : ((PacketReceiveEvent) e).getPacket().getDatagramPacket().getData()){
						message += (char)b;
					}
					System.out.println(((PacketReceiveEvent) e).getPacket().getDatagramPacket().getSocketAddress()+"> "+message);
					return true;
				}
				return false;
			}
		});
		s.start();
		
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		while (!line.equalsIgnoreCase("quit")) {
			System.out.println(line);
			line = in.nextLine();
		}
		in.close();
		s.stop();
	}
}
