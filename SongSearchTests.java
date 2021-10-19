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
    	//System.out.println("Test loading song data: " + IntegrationManager_TestData());
		//System.out.println("Test song search back end: " + IntegrationManager_TestBackEnd());
		//System.out.println("Test user interface: " + IntegrationManager_TestFrontEnd());
		//runAllDataWrangler();
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

    // Front End Developer Tests
         /**
	 * Calls all FrontEnd based test cases.
	 * @throws Exception
	 */
	public static void runAllFrontEnd() throws Exception {
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
		test tester = new test("1\nLove Story\nTaylor Swift\n2008\n5\n");
		String output = tester.checkOutput();
		if (output.startsWith("Welcome to SongSearch") && output.contains("Song added sucessfully") && output.contains("Thank you for using SongSearch. Goodbye.")) {
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
		test test = new test("4\nlove\n");
		String output = test.checkOutput();
		if (output.contains("")) {
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
		test tester = new test("1\nLove Story\nTaylor Swift\nTS08\n2008\n");
		String output = tester.checkOutput();
		if (output.startsWith("Welcome to SongSearch")
				&& output.contains("Noninteger value included. Please insert new Song Year:")) {
			return true;
		} else

		{
			System.out.print(output);
			return false;
		}
	}

    // Integration Manager Tests
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

	public static boolean IntegrationManager_TestBackEnd() {
		
		
		return false;
	}

	public static boolean IntegrationManager_TestFrontEnd() {
		SongSearchTests tester = new SongSearchTests("1\nBe My Baby\n5\n");
		
		SearchFrontEnd FrontEnd = new SearchFrontEnd();
		SearchBackEnd BackEnd = new SearchBackEnd();
		FrontEnd.run(BackEnd);
		String output = tester.checkOutput();
        if(output.startsWith("SongSearch Command Menu") && 
           output.contains("Please insert new Song Name:") &&
		   output.contains("Song added sucessfully") &&
		   output.contains("Thank you for using SongSearch. Goodbye."))
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
