package site.root3287.sudo2.net;

import java.util.Scanner;

import site.root3287.sudo2.net.client.Client;

public class ClientTest {
	public static void main(String[] args) {
		Client c = new Client("127.0.0.1", 3306);
		c.start();
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		while(!line.equalsIgnoreCase("quit")) {
			c.send(line);
			line = in.nextLine();
		}
		in.close();
		c.stop();
	}
}
