import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class SongSearchTests {

    public static void main(String[] args) throws Exception {
    runAllDataWrangler();
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

    // Integration Manager Tests


}
