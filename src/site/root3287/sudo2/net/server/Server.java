package site.root3287.sudo2.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import site.root3287.sudo2.events.EventDispatcher;
import site.root3287.sudo2.net.packet.events.PacketReceiveEventType;

public class Server{
	private ServerSocket serverSocket;
	private int port;
	private boolean running;
	private Thread serverThread;
	public EventDispatcher packetReceiver;
	public static Logger serverLogger;
	
	public Server(int port){
		this.port = port;
		packetReceiver = new EventDispatcher(new PacketReceiveEventType());
		serverLogger = Logger.getLogger("SUDO-Server");
	}
	
	public void start(){
		serverLogger.log(Level.INFO, "Starting Server...");
		this.running = true;
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serverThread = new Thread(()->main(), "Server-Thread");
		serverThread.setDaemon(true);
		serverThread.start();
	}
	
	private void main(){
		serverLogger.log(Level.INFO, "Server Started!");
		while(running){
			ServerClient client = new ServerClient(this);
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
				client.setSocket(clientSocket);
				serverLogger.log(Level.INFO, client.getSocket().getInetAddress().toString().replace('/', ' ').trim()+":"+client.getSocket().getPort()+" has connected!");
			} catch (IOException e) {
				//The client has timed out
				serverLogger.log(Level.INFO, client.getSocket().getInetAddress().toString().replace('/', ' ').trim()+":"+client.getSocket().getPort()+" has timed out!");
			}
			Thread t = new Thread(client);
			t.start();
		}
	}
}
