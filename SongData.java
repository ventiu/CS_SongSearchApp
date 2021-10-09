
// interface (implemented with proposal)

interface SongDataInterface {
    public String getTitle();
    public String getArtist();
    public int getYearPublished();
}



/**
 * This is an object that stores the information about the individual songs
 * 
 * @author Emma Ashton
 *
 */
public class SongData implements SongDataInterface {
	private String title;
	private String artist;
	private int year;
	
	/**
	 * This is the constructor that sets the variables 
	 *
	 * @param title - The title of the song
	 * @param artist - The artist or band who made the song
	 * @param year - The year the song was released
	 */
	public SongData(String title, String artist, int year)
    {
    	this.title = title;
    	this.artist = artist;
    	this.year = year;
    }

	
    /**
     * @return - the title of the song 
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * @return - the artist of the song
     */
    @Override
    public String getArtist() {
        return artist;
    }

    /**
     * @return - the year the song was published
     */
    @Override
    public int getYearPublished() {
        return year;
    }
}

