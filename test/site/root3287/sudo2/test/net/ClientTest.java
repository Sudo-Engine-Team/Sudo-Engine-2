package site.root3287.sudo2.test.net;

import java.util.Scanner;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.net.client.Client;
import site.root3287.sudo2.net.packet.events.PacketReceiveEvent;

public class ClientTest {
	public static void main(String[] args) {
		Client c = new Client("127.0.0.1", 25565);
		c.send("Hello");
		c.addListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				if(!(e instanceof PacketReceiveEvent))
					return false;
				System.out.println("Server > Client "+((PacketReceiveEvent)e).getPacket().getData());
				return true;
			}
		});
		c.start();
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		while(!line.equalsIgnoreCase("quit")){
			c.send(line);
			line = in.nextLine();
		}
		in.close();
		c.close();
	}
}
