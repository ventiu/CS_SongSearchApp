// interface (implemented with proposal)
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
// --== CS400 Project One File Header ==--
// Name: Julia Oghigian
// Email: oghigian@wisc.edu
// Team: Red
// Group: BA
// TA: Cameron Ruggles
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
/**
 * This interface generates a command menu and a histogram for songs that will
 * be used for SearchFrontEnd.
 *
 * @author Julia Oghigian
 */
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
	// generates command menu
	void menu();

	// prints out a histogram
	void histogram(String songName);
}
/**
 * This class generates a command menu and a histogram for songs though use of
 * front end while implementing SearchFrontEndInterface.
 * 
 * @author Julia Oghigian
 */
public class SearchFrontEnd implements SearchFrontEndInterface {
	private SearchBackEndInterface backEndInterface;

	/**
	 * Generates a command menu that allows for the user to chose five separate
	 * functions.
	 */
	@Override
	public void menu() {
		Scanner scnr = new Scanner(System.in);
		while (true){
			System.out.println("SongSearch Command Menu");
			System.out.println("1. Insert New Song into Database");
			System.out.println("2. Search For Song Titles by Words in those Title");
			System.out.println("3. Search For Artists by Words in their Song Titles");
			System.out.println("4. Display Years of Songs with Word in Title as Histogram");
			System.out.println("5. Quit");
			System.out.println("Please Select 1, 2, 3, 4, or 5.");
			String result = scnr.next();
			if (result.equals("1")) {
				System.out.println("Please insert new Song Name:");
				scnr.nextLine();
				String songName = scnr.nextLine();
				System.out.println("Please insert new Song Artist:");
					String artistName = scnr.nextLine();
				System.out.println("Please insert new Song Year:");
			int songYear = 0;
			try{
			
				songYear = scnr.nextInt();

			}catch ( InputMismatchException e){
					System.out.println("Noninteger value included in Song Year. Please Start Over.");
					return;	
				}
			
			while (String.valueOf(songYear).length() != 4) {
				System.out.println("Year is not correct length. Please insert new Song Year:");
				songYear = 0;
				songYear = scnr.nextInt();					
			}

			SongDataInterface song = new SongData(songName, artistName, songYear);
			backEndInterface.addSong(song);
			System.out.println("Song added sucessfully");
			continue;
			}
			else if (result.equals("2")) {
				System.out.println("Please insert Word:");
				String songWord = scnr.next();
				System.out.println(backEndInterface.findTitles(songWord));
				continue;
			}
			else if (result.equals("3")) {
				System.out.println("Please insert Word:");
				String songWordForArtists = scnr.next();
				System.out.println(backEndInterface.findArtists(songWordForArtists));
				continue;
			}
			else if (result.equals("4")) {
				System.out.println("Please insert Word:");
				String songWordHistogram = scnr.next();
				histogram(songWordHistogram);
				continue;
			}
			else if (result.equals("5")) {
				System.out.println("Thank you for using SongSearch. Goodbye.");
				break;
			}
			else {
				System.out.println("Invalid Choice. Select again.");
				continue;
			}
		}
		
	}

	/**
	 * Generates an asterisk based histogram based on a word in a song title. The
	 * histogram spans from 1945 to 2021.
	 * 
	 * @param songName a string containing the song search word.
	 */
	@Override
	public void histogram(String songName) {
		ArrayList<Integer> count = new ArrayList<>();
		int number = 0;
		
			for (int i = 1945; i < 2021; i++) {

				 count.add((backEndInterface.findNumberOfSongsInYear(songName, i)));
		
			}
			
		
		int year = 1945;
		for (int j = 0; j < count.size(); j++) {
			System.out.print(year + ": ");
			int test = count.get(j);
			while (test > 0) {
				System.out.print("*");
				test--;
			}
			year++;
			System.out.print("\n");
		}
	}

	/**
	 * Runs the command menu.
	 * 
	 * @param searchEngine a new SearchBackEndInterface
	 */
	@Override
	public void run(SearchBackEndInterface searchEngine) {
		this.backEndInterface = searchEngine;
		menu();
	}

}

// placeholder(s) (implemented with proposal, and possibly added to later)
class SearchFrontEndPlaceholder implements SearchFrontEndInterface {
	private SearchBackEndInterface searchBackEndPlaceholder;

	/**
	 * Runs the command menu.
	 * 
	 * @param searchEngine a new SearchBackEndInterface
	 */
	public void run(SearchBackEndInterface searchEngine) {
		this.searchBackEndPlaceholder = searchEngine;
		menu();

	}

	/**
	 * Generates a command menu that allows for the user to chose five separate
	 * functions.
	 */
	public void menu() {

	}

	/**
	 * Generates an asterisk based histogram based on a word in a song title. The
	 * histogram spans from 1945 to 2021.
	 * 
	 * @param songName a string containing the song search word.
	 */
	public void histogram(String songName) {

	}

}

