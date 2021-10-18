import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// interface (implemented with proposal)

interface SongLoaderInterface {
    public List<SongDataInterface> loadFile(String csvFilePath) throws FileNotFoundException;
    public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException;
}


/**
 * This Class is used to extract information from the CSV files and load it into SongData object in a large
 * LinkedList
 * 
 * @author Emma Ashton
 *
 */
public class SongLoader implements SongLoaderInterface {

    
    
    /**
     * This method parses one line of a CSV file. It takes the line and read character by character keeping track of quotes
     * and when it hits a comma while having balanced quotes(not an in text comma) it will format the quotes(get rid of wrapping
     * quotes and turn double quotes into single) and add the string to the output list. After reading all of the characters
     * in the input String it will return.
     * 
     * @param line - a line of CSV text that need to be parsed
     * @return A linked list of Strings where each element is one of the parsed values
     */
    public List<String> readCSVLine(String line)
    {
    	List<String> out = new LinkedList<String>();
    	int quoteCount = 0;
    	String currString = "";
    	int charCount = 0;
    	
	//append a comma to the end so it will properly capture the last string
    	if(line.charAt(line.length()-1) !=',') {line = line + ",";}
    	
    	for(char currChar: line.toCharArray())
    	{
    		charCount++;
    		//ending a block only when quotes are balanced(method 2 described in class)
    		if((currChar == ',' && quoteCount % 2 ==0))
    		{
    			//This deals with empty strings
    			if(currString.equals(""))
    			{
					out.add(currString);
					continue;
    			}
    			
    			//Get rid of the quote characters on either side of a block(if they exist)
    			if(currString.charAt(0) == '"' && currString.charAt(currString.length()-1) == '"')
    			{
    				//this deals with strings that are just two quote characters
    				if(currString.length() == 2)
    				{
    					currString = "";
    					out.add(currString);
    					continue;
    				}
    				currString = currString.substring(1, currString.length()-1);
    			}
    			
    			//turn the double quotes into single quotes
    			currString = currString.replaceAll("\"\"", "\"");
    			out.add(currString);
    			currString = "";
    			continue;
    		}
    		
    		if(currChar == '"') {quoteCount++;}
    		
    		currString = currString + currChar;
    		
    	}
    	return out;
    }
    

	
    /**
     * This reads the heading of the CSV file to get the variable information and calls readCSVLine() for every line in the file.
     * It takes the information from the CSV file and creates a new SongData object for each
     * 
     * @param csvFilePath - The file that will have the SongDatas extracted from
     * @return - a linked list of all the SongData objects in the file
     */
    @Override
    public List<SongDataInterface> loadFile(String csvFilePath) throws FileNotFoundException {
        Scanner scnr = new Scanner(new File(csvFilePath), "UTF-8");
        List<SongDataInterface> out = new LinkedList<SongDataInterface>();
        
        //The first line of the CSV files contain the variable names for each column, this extracts that information
        List<String> header = readCSVLine(scnr.nextLine());
        int titleIndex = header.indexOf("title");
        int artistIndex = header.indexOf("artist");
        int yearIndex = header.indexOf("year");
        if(yearIndex == -1) {yearIndex = header.indexOf("released");}//work around for Top500Songs that uses released instead of year
        
        
        while(scnr.hasNextLine())
        {
		String next = scnr.nextLine();
		if(next.equals("")){continue;}//Skip any empty lines
        	List<String> line = readCSVLine(next);
        	String year = line.get(yearIndex).replaceAll("[^\\d]", "");//extracts just the numerical part of the year(some had months)
        	SongData temp = new SongData(line.get(titleIndex), line.get(artistIndex), Integer.parseInt(year));
        	out.add(temp);
        }
        return out;
    }
    
    /**
     * This method calls loadFile() for every file in the directory
     * 
     * @param directoryPath - the path to the  directory that holds all of the songs
     * @return - a linked list that has all of the SongData objects from all the files in the directory
     */
    @Override
    public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException {
    	List<SongDataInterface> out = new LinkedList<SongDataInterface>();
        File folder = new File(directoryPath);
    	File[] listOfFiles = folder.listFiles();
    	for(File file: listOfFiles)
    	{
    		out.addAll(loadFile(file.getAbsolutePath()));
    	}
        return out;
    }

}

