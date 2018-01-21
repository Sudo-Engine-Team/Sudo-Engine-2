package site.root3287.sudo2.test.net;

import java.io.IOException;

import site.root3287.sudo2.net.client.Client;

public class ClientTest {
	public static void main(String[] args) {
		Client c = new Client("127.0.0.1", 25565);
		c.send("Hello");
		try {
			System.out.println("Server > "+c.getInput().readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.close();
	}
}
