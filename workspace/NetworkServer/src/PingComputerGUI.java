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
import javax.swing.JFrame;

public class PingComputerGUI extends JFrame{

    private JPanel contentPane;
    private JPanel detailsPane;
    private JPanel buttonPane;

    private JLabel lipaddress;
    private JLabel lhostname;
    
    private JTextField tipaddress;
    private JTextField thostname;

    private JButton bPing;
    private JButton bBack;
    
    
    public PingComputerGUI(){

    	contentPane = (JPanel)this.getContentPane();
        contentPane.setPreferredSize(new Dimension(350,100));
        this.setTitle("Ping another computer");

        detailsPane = new JPanel();
        detailsPane.setLayout(new GridLayout(2,2));
        lipaddress = new JLabel("Ip Address");
        detailsPane.add(lipaddress);
        tipaddress = new JTextField();
        detailsPane.add(tipaddress);
        lhostname = new JLabel("Host Name");
        detailsPane.add(lhostname);
        thostname = new JTextField();
        detailsPane.add(thostname);

        buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        
        bPing = new JButton("Ping User");
        bPing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{
                	if(((tipaddress.getText() != null && !tipaddress.getText().isEmpty() && tipaddress.getText() instanceof  String)) || 
                			(thostname.getText() != null && !thostname.getText().isEmpty() && thostname.getText() instanceof  String)){
                			bPing_actionPerformed(e);
                	}
                	else{
                		JOptionPane.showMessageDialog(null, "Please provide either hostname or ipAddress", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
                	}
                }
                catch (Exception ei) {
            		JOptionPane.showMessageDialog(null, "You need to specify either hostname or ipAddress", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPane.add(bPing);
        
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

    public void bPing_actionPerformed(ActionEvent e) {
	
        ServerGUI.getServer().pingIpAddress(this.tipaddress.getText());
        this.tipaddress.setText("");
        
        ServerGUI.getServer().pingHostName(this.thostname.getText());
        this.thostname.setText(""); 
    }
}
