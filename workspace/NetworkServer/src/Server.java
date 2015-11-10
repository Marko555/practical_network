
import java.util.ArrayList;

public class Server {

	private String filePath;
	private ListInterface usersList;
	private UserLaptop user;
	private StackInterface ipAddress;

	public Server(String fPath){
		this.filePath = fPath;
		usersList = new ReferenceBasedList();
		this.generateIPs(); 
	}
	
	private void generateIPs(){
		ipAddress = new StackArrayBased();
		/*int randomNum;
		for(int i=0; i < 4; i++){
			randomNum = 2 + (int)(Math.random()*255);
			ipAddress.push("192.168.1."+randomNum);
		
		}*/
		ipAddress.push("192.168.1.145");
		ipAddress.push("192.168.1.189");
		ipAddress.push("192.168.1.45");
		ipAddress.push("192.168.1.204");
		ipAddress.push("192.168.1.221");
	}
	
	private void generateDataStructure(ArrayList<String> lsta){
		int pos = 1;
		for(int i=0; i< lsta.size(); i=i+2){
			user = new UserLaptop(lsta.get(i), lsta.get(i+1));
			if(usersList.isEmpty()){
				usersList.add(pos, user);
			}
			else if(user.compareTo(((UserLaptop)usersList.showLast()).getUsername()) == 1){
				usersList.add(pos, user);
			}
			else{
				int k = usersList.size();
				//System.out.println(k);
				while(user.compareTo(((UserLaptop)usersList.get(k)).getUsername()) != 1){
					k--;
					//System.out.println(k);
					if(k==0){
						k++;
						break;
					}
				}		  
				usersList.add(k+1, user);
			}
			pos++;
		}
		
	}
	
	private void updateDS(UserLaptop newUser){
		if(usersList.isEmpty()){
			usersList.add(1, newUser);

		}
		else if(newUser.compareTo(((UserLaptop)usersList.showLast()).getUsername()) == 1){

			usersList.add(usersList.size() + 1, newUser);
		}

		else{


			int k = usersList.size();
			//System.out.println(k);
			while(newUser.compareTo(((UserLaptop)usersList.get(k)).getUsername()) != 1){
				k--;
				System.out.println(k);
				if(k==0){
					k++;
					break;
				}

			}		  
			usersList.add(k+1, newUser);

		}
	}

	public void startServer(){
		ArrayList<String> lst = new ArrayList<String>();
		lst = FileRead.readFromFile(filePath);
		generateDataStructure(lst);
	}
	
	public void addUser(String name, String pass){
		UserLaptop nUser = new UserLaptop(name, pass);
		
		if (this.getUser(name, pass) == null){
			updateDS(nUser);
		}
		else
			System.out.println("The user already exists!");
			
	}
	
	public void removeUser(String name1, String pass1){		
		for(int i=1; i<=usersList.size(); i++){
			if(name1.equals(((UserLaptop)usersList.get(i)).getUsername()) && pass1.equals(((UserLaptop)usersList.get(i)).getPassword())){
				if(((UserLaptop)usersList.get(i)).getcLap() != null){
					ipAddress.push(((UserLaptop)usersList.get(i)).getcLap().getIpAddress());
				}
				
				usersList.remove(i);
				System.out.println("The user has successfully removed!");
				break;
			}
			
		}
		
	}
	
	
	public void pingIpAddress(String ipAddress){
		for(int i=1; i<=usersList.size(); i++){
			if(((UserLaptop)usersList.get(i)).isConnected()){
				if(ipAddress == ((UserLaptop)usersList.get(i)).getcLap().getIpAddress()){
					System.out.println("Sending Ping Request to " + ipAddress);
					break;
				}
				else
					System.out.println(ipAddress + "Sorry, but the Ip Address provided is not connected to the network");
			}
		}
	}
	public void pingHostName(String hostName){
		for(int i=1; i<=usersList.size(); i++){
			if(((UserLaptop)usersList.get(i)).isConnected()){
				if(hostName == ((UserLaptop)usersList.get(i)).getcLap().getHostName()){
					System.out.println("Sending Ping Request to " + hostName); 
					break;
				}
				else
					System.out.println(hostName + "Sorry, but the host name provided is not connected to the network");
			}
			
		}
		
		
	}
	public void connectToNetwork(String hName,String name, String pass){
		ComputerLaptop laptop = new ComputerLaptop(hName, (String)ipAddress.pop());
		if (this.getUser(name, pass) != null){
			((UserLaptop)getUser(name,pass)).connect(laptop);
		}
		else
			System.out.println("Unable to connect! User already connected or invalid username or password!");
		
	}
	
	public void showUsers(){
		for (int j=1; j<=usersList.size(); j++)
			System.out.println("Location: " + j + "  Value: " + ((UserLaptop)usersList.get(j)).toString());
	}
	
	private Object getUser(String name, String pass){
		for (int j=1; j<=usersList.size(); j++){
			if ((((UserLaptop)usersList.get(j)).getUsername().equalsIgnoreCase(name)) &&
					(((UserLaptop)usersList.get(j)).getPassword().equalsIgnoreCase(pass))){
				return usersList.get(j);
			}
				
		}
		return null;
	}
	
	public void showConnected(){
		for (int j=1; j<=usersList.size(); j++){
			if(((UserLaptop)usersList.get(j)).getcLap() != null)
				System.out.println("Location: " + j + "  Value: " + ((UserLaptop)usersList.get(j)).toString());		
			}
	}
	
}
