package site.root3287.sudo2.test.net;

import java.io.IOException;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.Listener;
import site.root3287.sudo2.net.packet.events.PacketReceiveEvent;
import site.root3287.sudo2.net.server.Server;

public class GameServer {
	public static void main(String[] args) {
		
		Server s = new Server(25565);
		s.addReceiveListener(new Listener() {
			
			@Override
			public boolean onEvent(Event e) {
				if(!(e instanceof PacketReceiveEvent)){
					return false;
				}
				
				String message="";
				for(byte b : ((PacketReceiveEvent)e).getPacket().getDatagramPacket().getData()){
					message+=(char)b;
				}
				System.out.println(message);
				//JSONObject msssageOBJ = new JSONObject(message);
				return true;
			}
		});
		s.start();
		
		char input = ' ';
		while(s.isRunning()){
			if(input == 'q'){
				s.stop();
			}
			try {
				input = (char)System.in.read();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
