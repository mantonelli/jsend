package org.antech.jsend.sender.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.SocketException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antech.jsend.sender.MessageSender;

public class SenderWindow extends JFrame {

	private JEditorPane messagePane;
	private JButton btnSend, btnCancel;
	
	public SenderWindow() {
		this.setLayout(new BorderLayout());
		initInterface();
	}
	
	private void initInterface() {
		JPanel mainPanel    = new JPanel(new BorderLayout(3,3));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JPanel messagePanel = new JPanel(new BorderLayout());
		messagePanel.setBorder(BorderFactory.createEtchedBorder());
		
		messagePane = new JEditorPane();
		messagePanel.add(messagePane);
		
		mainPanel.add(messagePanel, BorderLayout.CENTER);
		
		//////////////////////// Botões /////////////////////////
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		
		btnSend   = new JButton("Enviar");
		btnSend.setToolTipText("Enviar mensagem");
		btnSend.setMnemonic(KeyEvent.VK_E);
		btnSend.setIcon(new ImageIcon(SenderWindow.class.getResource("images/ok.png")));
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(messagePane.getText().trim().equals("")) return;
				
				try {
					MessageSender sender = new MessageSender(messagePane.getText());
					sender.run();
				} catch (SocketException e) {
					e.printStackTrace();
				}

				messagePane.setText("");

			}
		});
		
		btnCancel = new JButton("Fechar");
		btnCancel.setToolTipText("Fechar programa");
		btnCancel.setMnemonic(KeyEvent.VK_F);
		btnCancel.setIcon(new ImageIcon(SenderWindow.class.getResource("images/close.png")));
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
		
		buttonPanel.add(btnSend);
		buttonPanel.add(btnCancel);
		
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		this.setMinimumSize(new Dimension(300,250));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//this.setModalityType(ModalityType.TOOLKIT_MODAL);
		this.setTitle("Envio de Mensagem");
		this.setIconImage(new ImageIcon(SenderWindow.class.getResource("images/default.png")).getImage());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		pack();
		
		this.setVisible(true);
		
		
	}
	
}
