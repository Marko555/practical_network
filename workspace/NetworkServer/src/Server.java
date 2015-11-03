import java.util.ArrayList;

public class Server {

	private String filePath;
	private ListInterface usersList;
	private UserLaptop user;

	public Server(String fPath){
		this.filePath = fPath;
		usersList = new ReferenceBasedList();
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
				System.out.println(k);
				while(user.compareTo(((UserLaptop)usersList.get(k)).getUsername()) != 1){
					k--;
					System.out.println(k);
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
			System.out.println(k);
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

		for (int j=1; j<=usersList.size(); j++)
			System.out.println("Location: " + j + "  Value: " + usersList.get(j));
	}
	
	public void addUser(String name, String pass){
		UserLaptop nUser = new UserLaptop(name, pass);
		updateDS(nUser);
		
		for (int j=1; j<=usersList.size(); j++)
			System.out.println("Location: " + j + "  Value: " + usersList.get(j));
		
	}


}
