package org.antech.jsend.sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;
import java.util.Date;

public class MessageSender extends Thread {
	
	protected DatagramSocket socket = null;
	protected BufferedReader reader = null;
	
	private String message = "";
	
	public MessageSender(String message) throws SocketException {
		socket = new DatagramSocket(9897);
		this.message = System.getenv("user.name") + "##" + message;
	}
	
	public void run() {
		byte[] buffer = new byte[256];

        buffer = message.getBytes();

        InetAddress group = null;
		try {
			group = InetAddress.getByName("224.0.0.255");
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
					 					               group, 9898);
			socket.send(packet);
			socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
