import java.io.*; /* Required for handling the IOExceptions */
import java.util.ArrayList;

public class FileRead
{
	/*public static void main( String [ ] args )
	{
		readFromFile( "inputfile.txt" );
	}*/

	public static ArrayList<String> readFromFile( String fileName )
	{
		String oneLine;
		ArrayList<String> lines = new ArrayList<String>();

		try
		{
			/* Create a FileWriter object that handles the low-level details of reading */
			FileReader theFile = new FileReader( fileName );

			/* Create a BufferedReader object to wrap around the FileWriter object */
			/* This allows the use of high-level methods like readline */			
			BufferedReader fileIn  = new BufferedReader( theFile );
		
			/* Read the first line of the file */
			oneLine = fileIn.readLine();
			
			/* Read the rest of the lines of the file and output them on the screen */
			while( oneLine != null ) /* A null string indicates the end of file */
			{
				lines.add(oneLine);
				oneLine = fileIn.readLine();
			}

			/* Close the file so that it is no longer accessible to the program */
			fileIn.close( );
		}

		/* Handle the exception thrown by the FileReader constructor if file is not found */
		catch (FileNotFoundException e){
			System.out.println( "Unable to locate the file: " + fileName );
		}

		/* Handle the exception thrown by the FileReader methods */
		catch (IOException e){
			System.out.println( "There was a problem reading the file: " + fileName);
		}
		
		return lines;
	} /* End of method readFromFile */
} /* End of class FileRead */