public class test
{
	public static void main(String[] args)
	{
		ListInterface myList = new ReferenceBasedList();

		myList.add(1,"AAA");
		myList.add(2,"BBB");
		myList.add(3,"CCC");
		myList.add(4,"DDD");

		for (int i=1; i<=myList.size(); i++)
			System.out.println("Location: " + i + "  Value: " + myList.get(i));


		myList.insert("EEE");
		myList.insert("FFF");

		myList.remove(6);
		myList.remove(2);

		for (int i=1; i<=myList.size(); i++)
			System.out.println("Location: " + i + "  Value: " + myList.get(i));

		if (myList.exists("GGG"))
			System.out.println("Yes it does.");
		else
			System.out.println("NO it does not.");

		Server s = new Server("/home/imagine/workspace/NetworkServer/src/inputfile.txt");
		s.startServer();
		s.addUser("makis", "jhsdf98");
		s.addUser("werty", "454");
	}
};
