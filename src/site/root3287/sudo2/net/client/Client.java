package site.root3287.sudo2.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{
	private String destAddress;
	private int destPort;
	
	private Socket socket;
	private OutputStreamWriter os;
	private PrintWriter output;
	private BufferedReader input;
	
	public Client(Socket s) {
		this.socket = s;
		try {
			this.destAddress = s.getInetAddress().toString().replace('/', ' ').trim();
			this.destPort = s.getPort();
			this.os = new OutputStreamWriter(socket.getOutputStream());
			this.output = new PrintWriter(this.os);
			this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public Client(String destAddres, int port) {
		this.destAddress = destAddres;
		this.destPort = port;
		try {
			this.socket = new Socket(destAddres, port);
			this.os = new OutputStreamWriter(this.socket.getOutputStream());
			this.output = new PrintWriter(os);
			this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(String message) {
		output.println(message);
		try {
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the destAddress
	 */
	public String getDestinationAddress() {
		return destAddress;
	}

	/**
	 * @param destAddress the destAddress to set
	 */
	public void setDestinationAddress(String destAddress) {
		this.destAddress = destAddress;
	}

	/**
	 * @return the destPort
	 */
	public int getDestinationPort() {
		return destPort;
	}

	/**
	 * @param destPort the destPort to set
	 */
	public void setDestinationPort(int destPort) {
		this.destPort = destPort;
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}
	
	public void close() {
		this.output.close();
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedReader getInput() {
		return this.input;
	}
}
