public class test
{
	public static void main(String[] args)
	{
		

		Server s = new Server("/home/imagine/workspace/NetworkServer/src/inputfile2.txt");
		s.startServer();
		s.addUser("ldfj", "123");
		s.addUser("lsajf", "123");
		s.addUser("oeirut", "123");
		s.addUser("ncbv", "123");
		s.addUser("pyu", "123");
		
		s.connectToNetwork("Kostas-pc", "dranidis", "dhr342");
		s.connectToNetwork("Thanos-pc", "nikos", "123");
		
		//s.showUsers();
		
		//s.showConnected();
		int posEqual = 0;
		int posComma = 0;
		for (int i = 0; i < s.showConnected().length; i++) {
			try {
				posEqual = s.showConnected()[i].indexOf("=", 5);
				posComma = s.showConnected()[i].indexOf(",", 5);
				System.out.println( "Username: " + s.showConnected()[i].substring(posEqual+1, posComma));
				posEqual = s.showConnected()[i].lastIndexOf(":");
				System.out.println("IP: " + s.showConnected()[i].substring(posEqual+1, s.showConnected()[i].length()-1));
				System.out.println("Host Name: "+ s.showConnected()[i].substring(s.showConnected()[i].indexOf(":", 0), s.showConnected()[i].indexOf(",", posComma+1)));
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				System.out.println("No other users connected");
			}
		}
		s.closeSystem();
		
		
	}
};
