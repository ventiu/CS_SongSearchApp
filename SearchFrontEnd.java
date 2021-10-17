// interface (implemented with proposal)
import java.util.ArrayList;
import java.util.Scanner;
interface SearchFrontEndInterface {    
    public void run(SearchBackEndInterface searchEngine);

    // Here is a sample of the minimal set of options that 
    // this front end will support:

    // SongSearch Command Menu:
    // 1. Insert New Song into Database
    // 2. Search For Song Titles by Words in those Title
    // 3. Search For Artists by Words in their Song Titles
    // 4. Display Years of Songs with Word in Title as Histogram
    // 5. Quit
}

// public class (implemented primarilly in final app week)

public class SearchFrontEnd implements SearchFrontEndInterface {

    @Override
    public void run(SearchBackEndInterface searchEngine) {
        // TODO Auto-generated method stub
    
    }

}

// placeholder(s) (implemented with proposal, and possibly added to later)
class SearchFrontEndPlaceholder implements SearchFrontEndInterface {	private SearchBackEndInterface s;
	public void menu(){
		System.out.println("SongSearch Command Menu");
        System.out.println("1. Insert New Song into Database");
        System.out.println("2. Search For Song Titles by Words in those Title");
        System.out.println("3. Search For Artists by Words in their Song Titles");
        System.out.println("4. Display Years of Songs with Word in Title as Histogram");
        System.out.println("5. Quit");
        Scanner scnr = new Scanner(System.in);
        String result = scnr.next();
        if (result.equals("1")) {
        	System.out.println("Please insert new Song Name:");
        	String s1 = scnr.next();
        	System.out.println("Please insert new Song Artist:");
        	String s2 = scnr.next();
        	System.out.println("Please insert new Song Year:");
        	int s3 = scnr.nextInt();
        	SongDataInterface song =  new SongData(s1, s2, s3);
        	
        	s.addSong(song);
        	System.out.print("Song added sucessfully");
        	menu();
        }
        if (result.equals("2")) {
        	System.out.println("Please insert Word:");
        	String s1 = scnr.next();
        	System.out.println(s.findTitles(s1));
        	menu();
        }
        if (result.equals("3")) {
        	System.out.println("Please insert Word:");
        	String s1 = scnr.next();
        	System.out.println(s.findArtists(s1));
        	menu();
        }
        if (result.equals("4")) {
        	System.out.println("Please insert Word:");
        	String s1 = scnr.next();
        	histogram(s1);
        	menu();
        }
        if (result.equals("5")) {
        	System.out.println("Thank you for using SongSearch. Goodbye.");
        	return; 
	}
	}
	public void histogram(String songName) {
		ArrayList<Integer> count = new ArrayList<>();
		for (int j = 0; j < 76; j++ ) {
		for (int i = 1945; i < 2021; i++) {
			
		count.add(s.findNumberOfSongsInYear(songName, i));	
		}
		}
		int year = 1945;
		for (int j = 0; j < count.size(); j++) {
			System.out.print(year + ": ");
			int test = count.get(j);
			 while(test > 0) {
				 System.out.print("*");
				 test--;
			 }
			 year++;
			 System.out.print("\n");
		}
	}
    public void run(SearchBackEndInterface searchEngine) {
    	this.s = searchEngine; 
        menu();
        }
    
   
}    
