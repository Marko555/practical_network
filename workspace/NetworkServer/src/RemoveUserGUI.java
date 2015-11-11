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

public class RemoveUserGUI extends JFrame{

    private JPanel contentPane;
    private JPanel detailsPane;
    private JPanel buttonPane;

    private JLabel lUsername;
    private JLabel lPassword;
    
    private JTextField tUsername;
    private JTextField tPassword;

    private JButton bRemove;
    private JButton bBack;
    
    private Server ser;

    public RemoveUserGUI(Server s){

    	this.ser = s;
    	contentPane = (JPanel)this.getContentPane();
        contentPane.setPreferredSize(new Dimension(350,100));
        this.setTitle("Remove User");

        detailsPane = new JPanel();
        detailsPane.setLayout(new GridLayout(2,2));
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
        
        bRemove = new JButton("Remove User");
        bRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{
                	if(tUsername.getText() != null && !tUsername.getText().isEmpty() && tUsername.getText() instanceof  String){
                		if(tPassword.getText() != null && !tPassword.getText().isEmpty() && tPassword.getText() instanceof  String){
                			bRemove_actionPerformed(e);
                		}
                		else{
                			JOptionPane.showMessageDialog(null, "Please provide password", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
                		}
                	}
                	else{
                		JOptionPane.showMessageDialog(null, "Please provide user name as a string", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
                	}
                }
                catch (NumberFormatException wi) {
            		JOptionPane.showMessageDialog(null, "You need to specify password as a number ", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPane.add(bRemove);
        
        bBack = new JButton("Back");
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            	bBack_actionPerformed(e);
            }
        });
        buttonPane.add(bBack);

        contentPane.add(buttonPane, BorderLayout.SOUTH);
        contentPane.add(detailsPane, BorderLayout.CENTER);
    }

    private void bBack_actionPerformed(ActionEvent e) {
    	JFrame b = new ServerGUI();
        b.pack();
        b.setLocationRelativeTo(null);
        b.setVisible(true);
    }

    public void bRemove_actionPerformed(ActionEvent e) {
    	try{
    		this.ser.removeUser(this.tUsername.getText(), this.tPassword.getText());
    	}
    	catch (Exception ei){
    		JOptionPane.showMessageDialog(null, "The user with that name and password does not exist", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
    	}
        this.tUsername.setText("");
        this.tPassword.setText("");    
    }
}
