package site.root3287.sudo2.net.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import site.root3287.sudo2.net.packet.Packet;
import site.root3287.sudo2.net.packet.events.PacketReceiveEvent;

public class ServerClient implements Runnable{

	private Socket socket;
	private PrintWriter out;
	private Server server;
	private BufferedReader in;
	
	public ServerClient(Server server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		String line;
		while(true){
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				line = in.readLine();
				if(line == null)
					break;
				this.server.packetReceiver.execute(new PacketReceiveEvent(new Packet(line, socket))); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void send(String msg){
		out.println(msg);
	}
	
	public Socket getSocket(){
		return this.socket;
	}
	
	public void setSocket(Socket socket){
		this.socket = socket;
	}
}
