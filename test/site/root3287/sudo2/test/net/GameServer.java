package site.root3287.sudo2.test.net;

import java.io.IOException;

import site.root3287.sudo2.net.server.Server;

public class GameServer {
	public static void main(String[] args) {
		Server s = new Server(25565);
		s.start();
		char in = ' ';
		while (in != 'q'){
			//TODO SOMETHING
			try {
				in = (char) System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
