
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
		else {
			int k = usersList.size();
			//System.out.println(k);
			while(newUser.compareTo(((UserLaptop)usersList.get(k)).getUsername()) != 1){
				k--;
				//System.out.println(k);
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
			System.out.println("The user is added.");
		}
		else
			System.out.println("The user with the specific name or password already exists!");
	}
	
	public void removeUser(String name1, String pass1){		
		for(int i=1; i<=usersList.size(); i++){
			if(name1.equals(((UserLaptop)usersList.get(i)).getUsername()) && pass1.equals(((UserLaptop)usersList.get(i)).getPassword())){
				if(((UserLaptop)usersList.get(i)).getcLap() != null){
					ipAddress.push(((UserLaptop)usersList.get(i)).getcLap().getIpAddress());
				}				
				usersList.remove(i);
				System.out.println("The user has successfully removed!");
				return;
			}
		}
		System.out.println("The user with specific name and password does not exist.");
	}
		
	public void pingIpAddress(String ipAddress){
		for(int i=1; i<=usersList.size(); i++){
			if(((UserLaptop)usersList.get(i)).isConnected()){
				if(ipAddress.equals(((UserLaptop)usersList.get(i)).getcLap().getIpAddress())){
					System.out.println("Sending Ping Request to " + ipAddress);
					return;
				}
				else
					System.out.println(ipAddress + "Sorry, but the user with the Ip Address provided is not connected to the network");
					return;
			}
		}
		System.out.println("Sorry, but it looks like there are no users connected");
	}
	
	public void pingHostName(String hostName){
		for(int i=1; i<=usersList.size(); i++){
			if(((UserLaptop)usersList.get(i)).isConnected()){
				if(hostName.equals(((UserLaptop)usersList.get(i)).getcLap().getHostName())){
					System.out.println("Sending Ping Request to " + hostName); 
					return;
				}
				else
					System.out.println(hostName + " Sorry, but the user with host name provided is not connected to the network");
					return;
			}	
		}
		System.out.println("Sorry, but it looks like there are no users connected");
	}
	
	public void connectToNetwork(String hName,String name, String pass){
		ComputerLaptop laptop = new ComputerLaptop(hName, (String)ipAddress.pop());
		if (this.getUser(name, pass) != null){
			((UserLaptop)getUser(name,pass)).connect(laptop);
			System.out.println("Connected!");
		}
		else
			System.out.println("Unable to connect! User already connected or invalid username or password!");		
	}
	public void disconnectFromNetwork(String hName,String name, String pass){
		for(int i=1; i<=usersList.size(); i++){
			if(name.equals(((UserLaptop)usersList.get(i)).getUsername()) && pass.equals(((UserLaptop)usersList.get(i)).getPassword()) && 
					hName.equals(((UserLaptop)usersList.get(i)).getcLap().getHostName())){
				ipAddress.push(((UserLaptop)usersList.get(i)).getcLap().getIpAddress());
				((UserLaptop)getUser(name,pass)).disconnectFromNetwork();				
				System.out.println("The user is succesfully disconnected");
				return;
			}
		}
		System.out.println("Sorry, but the the user with that host name ,name and password does not exist.");
	}
	//do we need the method below???
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
		boolean any = false;
		for (int j=1; j<=usersList.size(); j++){
			if(((UserLaptop)usersList.get(j)).getcLap() != null){
				System.out.println("Location: " + j + "  Value: " + ((UserLaptop)usersList.get(j)).toString());
				any=true;
			}
		}
		if(!any)
			System.out.println("Looks like there are no computers connected!");
	}	
}
