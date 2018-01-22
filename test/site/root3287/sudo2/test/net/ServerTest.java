package site.root3287.sudo2.test.net;

import java.io.IOException;
import java.util.Scanner;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.net.client.Client;
import site.root3287.sudo2.net.packet.events.PacketReceiveEvent;
import site.root3287.sudo2.net.server.Server;

public class ServerTest {
	public static void main(String[] args) {
		System.out.println("starting server");
		Server s = new Server(25565);
		System.out.println("waiting for client");
		Client c = null;
		try {
			c = new Client(s.getServerSocket().accept());
			System.out.println("Client connected");
			c.send("Welcome to the server!");
			c.addListener(new Listener() {
				
				@Override
				public boolean onEvent(Event e) {
					if(!(e instanceof PacketReceiveEvent))
						return false;
					System.out.println(((PacketReceiveEvent)e).getPacket().getSocket().getInetAddress()+":"+((PacketReceiveEvent)e).getPacket().getSocket().getPort()+" > Server "+((PacketReceiveEvent)e).getPacket().getData());
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.close();
	}
}
