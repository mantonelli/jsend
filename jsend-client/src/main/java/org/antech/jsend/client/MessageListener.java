package org.antech.jsend.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Logger;

import lombok.extern.log4j.Log4j;

import org.antech.jsend.client.ui.ClientDialog;

public class MessageListener extends Thread {

	private final Logger log = Logger.getLogger(this.getName());
	
	ClientDialog    dialog;
	MulticastSocket socket;
	
	public MessageListener() throws IOException {
		
		dialog   = new ClientDialog();
		
		socket = new MulticastSocket(9898);
		InetAddress address = InetAddress.getByName("224.0.0.255");
		socket.joinGroup(address);
	}
	
	public void run() {
		
		while(true) {
		try {

			DatagramPacket packet;
    
            // get a few quotes
			for (int i = 0; i < 5; i++) {
		
			    byte[] buf = new byte[256];
		            packet = new DatagramPacket(buf, buf.length);
		            socket.receive(packet);
		
		            String received = new String(packet.getData(), 0, packet.getLength());
		            log.info("Recebi: " + received);

		            dialog.addMessage(received);
		            dialog.run();
		            //JOptionPane.showMessageDialog(null, received, "Mensagem", JOptionPane.OK_OPTION);
			}

			//socket.leaveGroup(address);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		}
	}
}
