package site.root3287.sudo2.net.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server{
	private ServerSocket serverSocket;
	private int port;
	private boolean running;
	
	public Server(int port) {
		setPort(port);
		try {
			this.serverSocket = new ServerSocket(this.port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPort(int port) {
		if(this.running) {
			return;
		}
		this.port = port;
	}
	
	public void close() {
		try {
			this.serverSocket.close();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * @return the serverSocket
	 */
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
}
