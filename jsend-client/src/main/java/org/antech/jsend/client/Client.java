package org.antech.jsend.client;

import java.awt.*;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Client {
	
	/*
	final static TrayIcon trayIcon = new TrayIcon(new ImageIcon(Client.class.getResource("ui/images/tray.png")).getImage());
	final static SystemTray tray   = SystemTray.getSystemTray();
	*/
	
	public static void main(String[] args) {
	
		/*
		if(SystemTray.isSupported()) {
			try {
	            tray.add(trayIcon);
	        } catch (AWTException e) {
	            System.out.println("TrayIcon could not be added.");
	            return;
	        }
		}
		*/
		
		MessageListener listener;
		try {
			listener = new MessageListener();
			listener.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				tray.remove(trayIcon);
			}
		});
		*/
	}
}
