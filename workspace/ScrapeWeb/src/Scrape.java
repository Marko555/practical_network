import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;


public class Scrape {
	public static void main(String[] args){
		
		Document doc = null;
		try {
			doc = Jsoup.connect("http://yify-movie.com/").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(doc.title());
		
		
	}
	
	
}
