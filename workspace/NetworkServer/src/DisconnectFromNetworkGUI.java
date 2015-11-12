import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DisconnectFromNetworkGUI extends JFrame{

    private JPanel contentPane;
    private JPanel detailsPane;
    private JPanel buttonPane;

    private JLabel lUsername;
    private JLabel lPassword;
    private JLabel lHostname;
    
    private JTextField tUsername;
    private JTextField tPassword;
    private JTextField tHostname;

    private JButton bDisconnect;
    private JButton bBack;
        
    public DisconnectFromNetworkGUI(){

    	contentPane = (JPanel)this.getContentPane();
        contentPane.setPreferredSize(new Dimension(350,100));
        this.setTitle("Disconnect User To Network");

        detailsPane = new JPanel();
        detailsPane.setLayout(new GridLayout(3,2));
        lHostname = new JLabel("Host Name");
        detailsPane.add(lHostname);
        tHostname = new JTextField();
        detailsPane.add(tHostname);
        lUsername = new JLabel("User Name");
        detailsPane.add(lUsername);
        tUsername = new JTextField();
        detailsPane.add(tUsername);
        lPassword = new JLabel("User password");
        detailsPane.add(lPassword);
        tPassword = new JTextField();
        detailsPane.add(tPassword);

        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        
        bDisconnect = new JButton("Disconnect User");
        bDisconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{
                	if(tUsername.getText() != null && !tUsername.getText().isEmpty() && tUsername.getText() instanceof  String){
                		if(tPassword.getText() != null && !tPassword.getText().isEmpty() && tPassword.getText() instanceof  String){
                			tHostname.getText();
                			bDisconnect_actionPerformed(e);
                		}
                		else{
                			JOptionPane.showMessageDialog(null, "Please provide password", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
                		}
                	}
                	else{
                		JOptionPane.showMessageDialog(null, "Please provide user name", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
                	}
                }
                catch (Exception ei) {
            		JOptionPane.showMessageDialog(null, "You need to specify the host name", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPane.add(bDisconnect);
        
        bBack = new JButton("Back");
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	bBack_actionPerformed(e);
            	dispose();
            }
        });
        buttonPane.add(bBack);

        contentPane.add(buttonPane, BorderLayout.SOUTH);
        contentPane.add(detailsPane, BorderLayout.CENTER);
    }

    private void bBack_actionPerformed(ActionEvent e) {
    	JFrame b = new UserGUI();
        b.pack();
        b.setLocationRelativeTo(null);
        b.setVisible(true);
    }

    public void bDisconnect_actionPerformed(ActionEvent e) {
	
        ServerGUI.getServer().disconnectFromNetwork(this.tHostname.getText(), this.tUsername.getText(), this.tPassword.getText());
 
        this.tUsername.setText("");
        this.tPassword.setText(""); 
        this.tHostname.setText(""); 
    }
}

