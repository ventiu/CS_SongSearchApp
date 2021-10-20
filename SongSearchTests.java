// --== CS400 Project One File Header ==--
// Name: Tianyou Wen
// Email: twen22@wisc.edu
// Team: Red
// Group: BA
// TA: Cameron Ruggles
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class SongSearchTests {

    public static void main(String[] args) throws Exception {
		runAllBackEnd();
		runAllDataWrangler();
	  	runAllFrontEnd();
		runAllIntegrationManager();
	}

    // Data Wrangler Code Tests
	public static void runAllDataWrangler() throws FileNotFoundException
	{
		System.out.println("Read CSV Line : " + testReadCSVLine());
		System.out.println("Read File     : " + testReadFile());
		System.out.println("Read Directory: " + testReadDirectory());
	}

	/**
	 * This tests a normal line of csv, a line that has quotes around some elements, and a line that has internal quotes
	 * @return - whether it passed the test
	 */
	public static boolean testReadCSVLine()
	{
		SongLoader tester = new SongLoader();
		String test1 = "1,2,3";
		List<String> corr1 = new LinkedList<String>();
		corr1.add("1");
		corr1.add("2");
		corr1.add("3");
		List<String>out1 = tester.readCSVLine(test1);
		if(!corr1.equals(out1)) {return false;}
		
		String test2 = "\"1\",\"2\",\"3\"";
		List<String>out2 = tester.readCSVLine(test2);
		if(!corr1.equals(out2)) {return false;}
		
		String test3 = "\"\"1\"\",\"\"2\"\",\"\"\"3\"\"\"";
		List<String> corr3 = new LinkedList<String>();
		corr3.add("\"1\"");
		corr3.add("\"2\"");
		corr3.add("\"3\"");
		List<String>out3 = tester.readCSVLine(test3);
		if(!corr3.equals(out3)) {return false;}
		
		return true;
	}
	
	/**
	 * This tests a file with two lines, a file that has extra text in the year column, and a file with extra columns
	 * @return - whether it passed the test
	 * @throws FileNotFoundException 
	 */
	public static boolean testReadFile() throws FileNotFoundException
	{
		SongLoader tester = new SongLoader();
		List<SongDataInterface> corr = new LinkedList<SongDataInterface>();
		corr.add(new SongData("1","2",3));
		
		List<SongDataInterface> test1 = tester.loadFile("dwTest/dir1/test1.csv");
		if(!corr.get(0).getArtist().equals(test1.get(0).getArtist()) || 
				!corr.get(0).getTitle().equals(test1.get(0).getTitle()) ||
				corr.get(0).getYearPublished() != test1.get(0).getYearPublished()){return false;}
		
		List<SongDataInterface> test2 = tester.loadFile("dwTest/dir2/test2.csv");
		if(!corr.get(0).getArtist().equals(test2.get(0).getArtist()) || 
				!corr.get(0).getTitle().equals(test2.get(0).getTitle()) ||
				corr.get(0).getYearPublished() != test2.get(0).getYearPublished()){return false;}
		
		List<SongDataInterface> test3 = tester.loadFile("dwTest/dir2/test3.csv");
		if(!corr.get(0).getArtist().equals(test3.get(0).getArtist()) || 
				!corr.get(0).getTitle().equals(test3.get(0).getTitle()) ||
				corr.get(0).getYearPublished() != test3.get(0).getYearPublished()){return false;}
		
		return true;
	}
	
	/**
	 * This tests a directory with one file and a directory with multiple files
	 * @return - whether it passed the test
	 * @throws FileNotFoundException 
	 */
	public static boolean testReadDirectory() throws FileNotFoundException
	{
		SongLoader tester = new SongLoader();
		List<SongDataInterface> corr = new LinkedList<SongDataInterface>();
		corr.add(new SongData("1","2",3));
		
		List<SongDataInterface> test1 = tester.loadAllFilesInDirectory("dwTest/dir1");
		if(!corr.get(0).getArtist().equals(test1.get(0).getArtist()) || 
				!corr.get(0).getTitle().equals(test1.get(0).getTitle()) ||
				corr.get(0).getYearPublished() != test1.get(0).getYearPublished()){return false;}
		
		corr.add(new SongData("1","2",3));
		
		List<SongDataInterface> test2 = tester.loadAllFilesInDirectory("dwTest/dir2");
		if(!corr.get(0).getArtist().equals(test2.get(0).getArtist()) || 
				!corr.get(0).getTitle().equals(test2.get(0).getTitle()) ||
				corr.get(0).getYearPublished() != test2.get(0).getYearPublished() ||
				!corr.get(1).getArtist().equals(test2.get(1).getArtist()) || 
				!corr.get(1).getTitle().equals(test2.get(1).getTitle()) ||
				corr.get(1).getYearPublished() != test2.get(1).getYearPublished() ){return false;}
		
		return true;
	}


    // Back End Developer Tests

	public static void runAllBackEnd() throws FileNotFoundException {
        System.out.println("BackEnd Contains: " + testCon());
        System.out.println("BackEnd Artists : " + testArt());
        System.out.println("BackEnd Titles  : " + testTitles());
        System.out.println("BackEnd Year    : " + testYear());
    }

    /**
    * Tests the add & contains method of the hash table.
    *
    * @return True if passed, false otherwise.
    */
    public static boolean testCon() throws FileNotFoundException {
        SearchBackEnd testTable = new SearchBackEnd();
        SongData check1 = new SongData("Secrets", "OneRepublic", 2010);
		SongData check2 = new SongData("Tangerine", "Glass Animals", 2015);
		SongData check3 = new SongData("still feel", "Half Alive", 2008);
		testTable.addSong(check1);
		testTable.addSong(check2);
		testTable.addSong(check3);
        if(testTable.containsSong(check1) && testTable.containsSong(check2) && testTable.containsSong(check3)) {
        } else return false;
        return true;
    	}

	/**
    * Tests the output of the findArtists method to ensure it includes all necessary elements.
    *
    * @return True if passed, false otherwise.
    */
    private static boolean testArt() throws FileNotFoundException {
        boolean passed = false;
        SearchBackEnd testTable = new SearchBackEnd();
        SongData song1 = new SongData("Holiday", "Green Day", 2004);
        SongData song2 = new SongData("Holiday", "Lil Nas X", 2020);
        SongData song3 = new SongData("infestissumam", "Ghost", 2013);
        testTable.addSong(song1);
        testTable.addSong(song2);
        testTable.addSong(song3);
        String output1 = testTable.findArtists("Holiday").toString();
        String output2 = testTable.findArtists("fes").toString();
		//System.out.println(testTable.findArtists("Holiday"));
        	//System.out.println(output1 + "\n" + output2);
        if(output1.equals("[Green Day] [Lil Nas X]") || output2.equals("[Ghost]")) {
            passed = true;
        }
        return passed;
    }

	/**
    * Tests the output of the findTitles method to ensure it includes all necessary elements.
    *
    * @return True if passed, false otherwise.
    */
    private static boolean testTitles() throws FileNotFoundException {
        boolean passed = false;
        SearchBackEnd testTable = new SearchBackEnd();
        SongData song1 = new SongData("Holiday", "Green Day", 2004);
        SongData song2 = new SongData("Holiday", "Lil Nas X", 2020);
        SongData song3 = new SongData("infestissumam", "Ghost", 2013);
        testTable.addSong(song1);
        testTable.addSong(song2);
		testTable.addSong(song3);
		String output1 = testTable.findTitles("Holiday").toString();
		String output2 = testTable.findTitles("fes").toString();
		//System.out.println(testTable.findTitles("Holiday"));
		//System.out.println(output1 + "\n" + output2);
		if(output1.equals("[Holiday] [Holiday]") || output2.equals("[infestissumam]")) {
			passed = true;
		}
		return passed;
    }

	/**
	* Tests the method to find the number of songs within a year.
	*
	* @return True if passed, false otherwise.
	*/
	public static boolean testYear() throws FileNotFoundException {
		SearchBackEnd testTable = new SearchBackEnd();
		SongData check1 = new SongData("Secrets", "OneRepublic", 2010);
		SongData check2 = new SongData("Tangerine", "Glass Animals", 2005);
		SongData check3 = new SongData("Yellow Submerine", "Half Alive", 2005);
		testTable.addSong(check1);
		testTable.addSong(check2);
		testTable.addSong(check3);
		if(testTable.findNumberOfSongsInYear("ine",2005) == 2 && testTable.findNumberOfSongsInYear("Sec",2010) == 1) {
		} else return false;
		return true;
	}



    // Front End Developer Tests
    /**
	 * Calls all FrontEnd based test cases.
	 * @throws Exception
	 */
	public static void  runAllFrontEnd()  {
		System.out.println("testMenu: " + testMenuOutput());
		System.out.println("testHistogram: " + testHistogram());
        System.out.println("testMenuIntputError: " + testMenuInputError());
	}
    /**
	 * Checks the correctness of Menu Output when input format is correct and quits
	 * 
	 * @return true if test pass
	 */
	public static boolean testMenuOutput() {
		SongSearchTests test = new SongSearchTests("2\nLove\n5\n");


		SearchFrontEnd frontEnd = new SearchFrontEnd();
		SearchBackEnd backEnd = new SearchBackEnd();
		frontEnd.run(backEnd);
		System.out.print("S");
		String output = test.checkOutput();
		if (output.startsWith("SongSearch Command Menu") &&  output.contains("Thank you for using SongSearch. Goodbye.")) {
		
			return true;
		} else {
			System.out.print(output);
			return false;
		}
	}

	/**
	 * Checks the correctness of histogram() if input format is correct.
	 * 
	 * @return true if test pass
	 */
	public static boolean testHistogram() {
		SongSearchTests test = new SongSearchTests("4\nLove\n");

		SearchFrontEnd frontEnd = new SearchFrontEnd();
		SearchBackEnd backEnd = new SearchBackEnd();
		frontEnd.run(backEnd);

		String output = test.checkOutput();
		if (output.contains("1945: \n1946: \n1947: \n1948: \n1949: \n1950: \n1951: \n1952: \n1953: \n1954: \n1955: \n1956: **\n1957: *\n1958: *\n1959: \n1960: \n1961: *\n1962: \n1963: *\n1964: *****\n1965: \n1966: **\n1967: ****\n1968: *\n1969: *\n1970: \n1971: \n1972: *\n1973: \n1974: \n1975: \n1976: \n1977: *\n1978: \n1979: \n1980: *\n1981: \n1982: *\n1983: \n1984: ***\n1985: \n1986: *\n1987: \n1988: \1989: *\n1990: \n1991: *\n1992: \n1993: \n1994: \n1995: \n1996: *\n1997: \n1998: \n1999: \n2000: \n2001: \n2002: \n2003: \n2004: \n2005: \n2006: \n2007: \n2008: \n2009: \n2010: ***\n2011: **\n2012: **\n2013: ****\n2014: ****\n2015: ***********\n2016: ***\n2017: *****\n2018: ****\n2019: ***\n2020: \n")) {
			return true;
		} else {
			System.out.print(output);
			return false;
		}
	}

	/**
	 * Checks the correctness of Menu Output when input format is incorrect for adds
	 * song.
	 * 
	 * @return true if test pass
	 */
	public static boolean testMenuInputError() {
		SongSearchTests test = new SongSearchTests("1\nLove Story\nTaylor Swift\nTS08\n");
		
		SearchFrontEnd frontEnd = new SearchFrontEnd();
		SearchBackEnd backEnd = new SearchBackEnd();
		frontEnd.run(backEnd);
		
		String output = test.checkOutput();
		if (output.startsWith("SongSearch Command Menu")
				&& output.contains("Noninteger value included. Please Start Over.")) {
			return true;
		} else

		{
			System.out.print(output);
			return false;
		}
	}

    // Integration Manager Tests
	public static void runAllIntegrationManager(){
		System.out.println("Test loading song data: " + IntegrationManager_TestData());
		System.out.println("Test song search back end: " + IntegrationManager_TestBackEnd());
		System.out.println("Test user interface: " + IntegrationManager_TestFrontEnd());
	}
	/**
	 * Check if the Data can be successfully read
	 * Throws FileNotFoundException if no such file
	 * 
	 * @return true if test passes
	 */
	public static boolean IntegrationManager_TestData() {
		List<SongDataInterface> corr = new LinkedList<SongDataInterface>();
		corr.add(new SongData("Like a Rolling Stone", "Bob Dylan",1965));		
		SongLoader songLoader = new SongLoader();
		try {
			List<SongDataInterface> test1 = songLoader.loadFile("./data/Top_500_Songs.csv");
			
			if (corr.get(0).getArtist().equals(test1.get(0).getArtist()) &&
			 	corr.get(0).getTitle().equals(test1.get(0).getTitle()) &&
				corr.get(0).getYearPublished() == test1.get(0).getYearPublished())
			return true;
		} catch (Exception FileNotFoundException){
			System.out.println("No such a file found");
		}
		return false;
	}

	/**
	 * Test if the backEnd works well for searching data
	 * 
	 * @return return true if test passes
	 */
	public static boolean IntegrationManager_TestBackEnd() {
		SearchBackEnd backEnd = new SearchBackEnd();
        SongData song1 = new SongData("Apologize", "Greg Wells", 2007);
        SongData song2 = new SongData("Sweet but Psycho", "Biscuits", 2018);
        SongData song3 = new SongData("Mood", "24kGolden", 2020);
        backEnd.addSong(song1);
        backEnd.addSong(song2);
        backEnd.addSong(song3);
		
		// Test backEnd for searching by name, find the artiest, find the number of songs by year
		if (backEnd.findTitles("polo").toString().equals("[Apologize]") &&
		backEnd.findTitles("but").toString().equals("[Sweet but Psycho]") &&
		backEnd.findArtists("Mood").toString().equals("[24kGolden]") &&
		backEnd.findNumberOfSongsInYear("but",2018) == 1)
			return true;
		return false;
	}

	/**
	 * Test if the frontEnd works well and as what we expected 
	 * 
	 * @return return true if test passes
	 */
	public static boolean IntegrationManager_TestFrontEnd() {
		SongSearchTests tester = new SongSearchTests("1\nthat's a good song\ngroup BA\n2022\n5\n");
		SearchFrontEnd FrontEnd = new SearchFrontEnd();
		SearchBackEnd BackEnd = new SearchBackEnd();

		FrontEnd.run(BackEnd);

		String output = tester.checkOutput();

		// Check if the output is exactly what we want
        if(output.startsWith("SongSearch Command Menu") && 
           output.contains("Please insert new Song Name:") &&
		   output.contains("Song added sucessfully") &&
		   output.contains("Thank you for using SongSearch. Goodbye.") )
		   return true;
        
		return false;
	}

    // Below is the code that actually implements the redirection of System.in and System.out,
    // and you are welcome to ignore this code: focusing instead on how the constructor and
    // checkOutput() method is used int he example above.

    private PrintStream saveSystemOut; // store standard io references to restore after test
    private PrintStream saveSystemErr;
    private InputStream saveSystemIn;
    private ByteArrayOutputStream redirectedOut; // where output is written to durring the test
    private ByteArrayOutputStream redirectedErr;

    /**
     * Creates a new test object with the specified string of simulated user input text.
     * @param programInput the String of text that you want to simulate being typed in by the user.
     */
    public SongSearchTests(String programInput) {
        // backup standard io before redirecting for tests
        saveSystemOut = System.out;
        saveSystemErr = System.err;
        saveSystemIn = System.in;    
        // create alternative location to write output, and to read input from
        System.setOut(new PrintStream(redirectedOut = new ByteArrayOutputStream()));
        System.setErr(new PrintStream(redirectedErr = new ByteArrayOutputStream()));
        System.setIn(new ByteArrayInputStream(programInput.getBytes()));
    }

    /**
     * Call this method after running your test code, to check whether the expected
     * text was printed out to System.out and System.err.  Calling this method will 
     * also un-redirect standard io, so that the console can be used as normal again.
     * 
     * @return captured text that was printed to System.out and System.err durring test.
     */
    public String checkOutput() {
        try {
            String programOutput = redirectedOut.toString() + redirectedErr.toString();
            return programOutput;    
        } finally {
            // restore standard io to their pre-test states
            System.out.close();
            System.setOut(saveSystemOut);
            System.err.close();
            System.setErr(saveSystemErr);
            System.setIn(saveSystemIn);    
        }
    }
}
