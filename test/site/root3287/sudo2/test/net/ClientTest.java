package site.root3287.sudo2.test.net;

import java.io.IOException;
import java.util.Scanner;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.net.client.Client;
import site.root3287.sudo2.net.packet.events.PacketReceiveEvent;

public class ClientTest {
	public static void main(String[] args) {
		Client c = new Client("127.0.0.1", 25565);
		c.addReceiveListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				if(!(e instanceof PacketReceiveEvent)){
					return false;
				}
				System.out.println("Server > Client "+((PacketReceiveEvent)e).getPacket().getPacket());
				return true;
			}
		});
		c.start();
		char in = ' ';
		while(in != 'q'){
			c.send(new Scanner(System.in).nextLine());
			try {
				in = (char) System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
