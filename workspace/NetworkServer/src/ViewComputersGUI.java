
import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ViewComputersGUI extends JFrame {

	
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel myTable;

    private ComputerLaptop cl;
    private UserLaptop ul;

    public ViewComputersGUI() {
    	
    	contentPane = (JPanel)this.getContentPane();
    	setTitle("Computers Connected");
    	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	
    	myTable = new DefaultTableModel();
    	table = new JTable(myTable);
    	myTable.addColumn("IPs");
    	
    	myTable.addColumn("Host Name");
    	myTable.addColumn("Username");
    	constructTable();
    	
    	contentPane.add(table, BorderLayout.CENTER);
    	
    	
    }

	private void constructTable() {
	 	String[] conUsers = ServerGUI.getServer().showConnected();
	 	
		Object[] row = new Object[3];
		int posEqual = 0;
		int posComma = 0;
		for (int i = 0; i < conUsers.length; i++) {
			try {
				posEqual = conUsers[i].indexOf("=", 5);
				posComma = conUsers[i].indexOf(",", 5);
				row[2] = conUsers[i].substring(posEqual+1, posComma);
				posEqual = conUsers[i].lastIndexOf(":");
				row[1] = conUsers[i].substring(posEqual+1, conUsers[i].length()-1);
				row[0] = conUsers[i].substring(conUsers[i].indexOf(":", 0), conUsers[i].indexOf(",", posComma+1));
				myTable.addRow(row);
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				System.out.println("No other users connected");
			}
		}
	}
}
