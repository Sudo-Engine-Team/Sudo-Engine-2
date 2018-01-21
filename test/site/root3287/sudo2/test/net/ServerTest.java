package site.root3287.sudo2.test.net;

import java.io.IOException;

import site.root3287.sudo2.net.client.Client;
import site.root3287.sudo2.net.server.Server;

public class ServerTest {
	public static void main(String[] args) {
		System.out.println("starting server");
		Server s = new Server(25565);
		System.out.println("waiting for client");
		try {
			Client c = new Client(s.getServerSocket().accept());
			System.out.println(c.getDestinationAddress()+":"+c.getDestinationPort()+" > "+c.getInput().readLine());
			c.send("Hi");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.close();
	}
}
