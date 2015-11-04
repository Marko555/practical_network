public class UserLaptop {
	
	private String username;
	private String password;
	private ComputerLaptop cLap;

	public UserLaptop(String name, String pass){
		
		this.username = name;
		this.password = pass;
		cLap = null;
	}
	
	public boolean isConnected(){
		if(this.cLap == null)
			return true;
		else
			return false;
	}

	public int compareTo(String name){
		if (this.username.compareTo(name) < 0)
			return -1;
		else if (this.username.compareTo(name) > 0)
			return 1;
		else
			return 0;
	}


	public String getUsername() {
		return username;
	}



	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "UserLaptop [username=" + username + ", password=" + password
				+ "]";
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}
	
	public void connectToNetwork(ComputerLaptop lap){
		if(isConnected())
			System.out.println("User already connected!");
		else{
			this.cLap = lap;
		}
	}
	
	public void disconnectFromNetwork(){
		cLap = null;
	}

	public ComputerLaptop getcLap() {
		return cLap;
	}

	public void setcLap(ComputerLaptop cLap) {
		this.cLap = cLap;
	}
}