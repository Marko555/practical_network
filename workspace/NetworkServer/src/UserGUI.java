import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserGUI extends JFrame{
	
    protected JPanel contentPane;
    private JPanel mainGuiPane;
    private JPanel buttonPane;

    private JButton bConnectToNetwork;
    private JButton bDisconnectFromNetwork;
    private JButton bPingComputer;
    private JButton bBack;
    
    public UserGUI(){
    	
    	contentPane = (JPanel)this.getContentPane();
    	contentPane.setPreferredSize(new Dimension(300,300));
        this.setTitle("UserWindow");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(4,1));
        
        bConnectToNetwork = new JButton("Connect To Network");
        bConnectToNetwork.setBackground(Color.LIGHT_GRAY);
        buttonPane.add(bConnectToNetwork);
        bConnectToNetwork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		dispose();
            		bConnectToNetwork_actionPerformed(e);
            }
        });
        bDisconnectFromNetwork = new JButton("Disconnect From Network");
        bDisconnectFromNetwork.setBackground(Color.white);
        buttonPane.add(bDisconnectFromNetwork);
        bDisconnectFromNetwork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{	
            		bDisconnectFromNetwork_actionPerformed(e);
            		dispose();
            	}
            	catch(Exception ex){
            		JOptionPane.showMessageDialog(null, "First you have to create new student class", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        
        bPingComputer = new JButton("Ping Computer");
        bPingComputer.setBackground(Color.LIGHT_GRAY);
        buttonPane.add(bPingComputer);
        bPingComputer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{
            		bPingComputer_actionPerformed(e);
            		dispose();
            	}
            	catch(Exception ex){
            		JOptionPane.showMessageDialog(null, "First you have to create new student class", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        
        bBack = new JButton("Back");
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	bBack_actionPerformed(e);
            }
        });
        bBack.setBackground(Color.white);
        buttonPane.add(bBack);
        mainGuiPane = new JPanel();
        mainGuiPane.add(buttonPane, BorderLayout.CENTER);
        contentPane.add(mainGuiPane);
        contentPane.add(buttonPane, BorderLayout.CENTER);
    }

    private void bConnectToNetwork_actionPerformed(ActionEvent e) {
      	JFrame ctn = new ();
        ctn.pack();
        ctn.setLocationRelativeTo(null);
        ctn.setVisible(true);
    }

    public void bDisconnectFromNetwork_actionPerformed(ActionEvent e){
        JFrame dfn = new ();
        dfn.pack();
        dfn.setLocationRelativeTo(null);
        dfn.setVisible(true);
    }
    
    public void bPingComputer_actionPerformed(ActionEvent e){
        JFrame pc  = new ();
        pc.pack();
        pc.setLocationRelativeTo(null);
        pc.setVisible(true);
    }
    public void bBack_actionPerformed(ActionEvent e){
        JFrame b = new MainGUI();
        b.pack();
        b.setLocationRelativeTo(null);
        b.setVisible(true);
    }
}
