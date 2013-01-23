package org.antech.jsend.client.ui;

import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;

public class ClientDialog extends JDialog implements Runnable {

	SimpleDateFormat formato;
	private Vector<String> messages;
	private Vector<Date> dates;
	private Vector<String> users;
	private JTextField  dateField, userField;
	private JEditorPane messagePane;
	private JButton btnPrev, btnNext, btnClose;
	private int index;
	
	public ClientDialog() {
		messages = new Vector<String>();
		dates    = new Vector<Date>();
		users    = new Vector<String>();
		formato  = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		initInterface();
	}
	
	private void initInterface() {
		this.setLayout(new BorderLayout(3,3));
		
		JPanel mainPanel    = new JPanel(new BorderLayout(3,3));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		dateField = new JTextField();
		dateField.setEditable(false);
		
		userField = new JTextField();
		userField.setEditable(false);
		
		datePanel.add(new JLabel("Data:"));
		datePanel.add(dateField);
		datePanel.add(userField);
		
		mainPanel.add(datePanel, BorderLayout.NORTH);
		
		JPanel messagePanel = new JPanel(new BorderLayout());
		messagePane = new JEditorPane();
		messagePane.setEditable(false);
		messagePane.setBorder(BorderFactory.createEtchedBorder());
		messagePanel.add(messagePane,BorderLayout.CENTER);
		
		mainPanel.add(messagePanel,BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		
		btnPrev   = new JButton(new ImageIcon(ClientDialog.class.getResource("images/prev.png")));
		btnPrev.setToolTipText("Mensagem anterior");
		btnPrev.setMnemonic(KeyEvent.VK_P);
		btnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				index--;
				if(index < 0) index = 0;
				
				showMessage();
			}
		});
		
		btnNext   = new JButton(new ImageIcon(ClientDialog.class.getResource("images/next.png")));
		btnNext.setToolTipText("Pr�xima mensagem");
		btnNext.setMnemonic(KeyEvent.VK_N);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				index++;
				if(index >= messages.size()) index = messages.size() - 1;
				
				showMessage();				
			}
		});
		
		btnClose   = new JButton("Fechar");
		btnClose.setToolTipText("Fechar janela");
		btnClose.setMnemonic(KeyEvent.VK_F);
		btnClose.setIcon(new ImageIcon(ClientDialog.class.getResource("images/close.png")));
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		buttonPanel.add(btnPrev);
		buttonPanel.add(btnNext);
		buttonPanel.add(btnClose);
		
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		this.setSize(450, 250);
		this.setTitle("Mensagens Recebidas");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
	}
	
	public void addMessage(String s) {
		
		String user    = s.substring(0,s.indexOf("##"));
		String message = s.substring(s.indexOf("##") + 2);
		
		dates.add(new Date(System.currentTimeMillis()));
		users.add(user);
		messages.add(message);
		index = messages.size() - 1;
	}
	
	private void showMessage() {
		dateField.setText(formato.format(dates.get(index)));
		userField.setText(users.get(index));
		messagePane.setText(messages.get(index));
	}

	@Override
	public void run() {
		showMessage();
		this.setVisible(true);		
	}	
}
