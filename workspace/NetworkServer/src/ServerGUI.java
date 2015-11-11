import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame{
	
	private boolean netUp = false;
	
    protected JPanel contentPane;
    private JPanel mainGuiPane;
    private JPanel buttonPane;
    
    private JButton bStartServer;
    private JButton bAddUser;
    private JButton bRemoveUser;
    private JButton bViewComputers;
    private JButton bBack;
    
    private Server s = new Server("/home/marko/Desktop/inpufile.txt");
    
    public ServerGUI(){
    	
    	contentPane = (JPanel)this.getContentPane();
    	contentPane.setPreferredSize(new Dimension(300,300));
        this.setTitle("ServerWindow");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout(5,1));
        
        bStartServer = new JButton("Start Server");
        bStartServer .setBackground(Color.LIGHT_GRAY);
        buttonPane.add(bStartServer );
        bStartServer .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	bStartServer_actionPerformed(e);
            	JOptionPane.showMessageDialog(null, "The server is started", "Start", JOptionPane.INFORMATION_MESSAGE);
            	
            	bStartServer.setEnabled(false);
            	bStartServer.setText("Running...");
            	setNetUp(true);
            	
            }
        });
        bAddUser = new JButton("Add User");
        bAddUser.setBackground(Color.white);
        buttonPane.add(bAddUser);
        bAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{
            		bAddUser_actionPerformed(e);
            	}
            	catch (Exception ex){
            		JOptionPane.showMessageDialog(null, "Firstly you have to start the server", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        
        bRemoveUser = new JButton("Remove User");
        bRemoveUser.setBackground(Color.LIGHT_GRAY);
        buttonPane.add(bRemoveUser);
        bRemoveUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{
            		bRemoveUser_actionPerformed(e);
            	}
            	catch(Exception ex){
            		JOptionPane.showMessageDialog(null, "Firstly you have to start the server", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        
        bViewComputers = new JButton("View Computers");
        bViewComputers.setBackground(Color.white);
        buttonPane.add(bViewComputers);
        bViewComputers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try{
            		bViewComputers_actionPerformed(e);
            	}
            	catch(Exception ex){
            		JOptionPane.showMessageDialog(null, "Firstly you have to start the server", "WRONG !!!", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        
        bBack = new JButton("Back");
        bBack.setBackground(Color.LIGHT_GRAY);
        buttonPane.add(bBack);
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		bBack_actionPerformed(e);
        		dispose();
            }
        });
        mainGuiPane = new JPanel();
        mainGuiPane.add(buttonPane, BorderLayout.CENTER);
        contentPane.add(mainGuiPane);
        contentPane.add(buttonPane, BorderLayout.CENTER);
    }
    
    public boolean isNetUp() {
		return netUp;
	}

	public void setNetUp(boolean netUp) {
		this.netUp = netUp;
	}


    private void bStartServer_actionPerformed(ActionEvent e) {
      	s.startServer();
    }
    
    private void bAddUser_actionPerformed(ActionEvent e) {
      	
    	JFrame au = new AddUserGUI(s);
        au.pack();
        au.setLocationRelativeTo(null);
        au.setVisible(true);
    }

    public void bRemoveUser_actionPerformed(ActionEvent e){
    	JFrame ru = new RemoveUserGUI(s);
        ru.pack();
        ru.setLocationRelativeTo(null);
        ru.setVisible(true);
    }
    
    public void bViewComputers_actionPerformed(ActionEvent e){
        JFrame vc = new ViewComputersGUI();
        vc.pack();
        vc.setLocationRelativeTo(null);
        vc.setVisible(true);
    }
    
    public void bBack_actionPerformed(ActionEvent e){
        JFrame b = new MainGUI();
        b.pack();
        b.setLocationRelativeTo(null);
        b.setVisible(true);
    }
}
