import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{
	
    protected JPanel contentPane;
    private JPanel mainGuiPane;
    private JPanel buttonPane;
    
    private JButton bNetworkServer;
    private JButton bNetworkUser;
    private JButton bExit;
    
    public MainGUI(){
    	
    	contentPane = (JPanel)this.getContentPane();
    	contentPane.setPreferredSize(new Dimension(300,300));
        this.setTitle("MainWindow");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(3,1));
        
        bNetworkServer = new JButton("Network Server");
        bNetworkServer.setBackground(Color.LIGHT_GRAY);
        buttonPane.add(bNetworkServer);
        bNetworkServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		dispose();
            		bNetworkServer_actionPerformed(e);
            }
        });
        bNetworkUser = new JButton("Network User");
        bNetworkUser.setBackground(Color.white);
        buttonPane.add(bNetworkUser);
        bNetworkUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{	
            		bNetworkUser_actionPerformed(e);
            		dispose();
            	}
            	catch(Exception ex){
            		JOptionPane.showMessageDialog(null, "Firstly you have to star the server", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        bExit = new JButton("Exit");
        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                //dispose();
            }
        });
        bExit.setBackground(Color.LIGHT_GRAY);
        buttonPane.add(bExit);
        
        mainGuiPane = new JPanel();
        mainGuiPane.add(buttonPane, BorderLayout.CENTER);
        contentPane.add(mainGuiPane);
        contentPane.add(buttonPane, BorderLayout.CENTER);
    }

    private void bNetworkServer_actionPerformed(ActionEvent e) {
      	JFrame server = new ServerGUI();
      	
        server.pack();
        server.setLocationRelativeTo(null);
        server.setVisible(true);
    }

    public void bNetworkUser_actionPerformed(ActionEvent e){
        JFrame user = new UserGUI();
        user.pack();
        user.setLocationRelativeTo(null);
        user.setVisible(true);
    }
}
