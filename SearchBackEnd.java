// --== CS400 Project One File Header ==--
// Name: Justin Garza
// Email: jmgarza2@wisc.edu
// Team: Red
// Group: BA
// TA: Cameron Ruggles
// Lecturer: Gary Dahl
// Notes to Grader:

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

interface SearchBackEndInterface {
    public void addSong(SongDataInterface song);

    public boolean containsSong(SongDataInterface song);

    // returns list of the titles of all songs that contain the word titleWord in their song title
    public List<String> findTitles(String titleWord);

    // returns list of the artists of all songs that contain the word titleWord in their song title
    public List<String> findArtists(String titleWord);

    // returns the number of songs that contain the word titleWord in their song title, and were published in year
    public int findNumberOfSongsInYear(String titleWord, int year);
}


/**
 * This class creates, populates, and allows for access and mutation of the Hash table that stores the songs as a
 * database.
 *
 * @Author Justin Garza
 */
public class SearchBackEnd implements SearchBackEndInterface {

    private LinkedList<SongDataInterface>[] songTable;
    private int capacity;
    private int size;

    /**
     * Main constructor to initialize and populate SongData table.
     */
    public void SearchBackEnd() throws FileNotFoundException {
        this.capacity = 25;
        songTable = new LinkedList[this.capacity];
        this.size = 0;
        populate();
    }

    /**
     * Populates the songTable with the initial list.
     *
     * @throws FileNotFoundException
     */
    private void populate() throws FileNotFoundException {
        List<SongDataInterface> iniList = new SongLoader().loadAllFilesInDirectory("~/project1_BA_red/data");
        for (int i = 0; i <= iniList.size(); i++) {
            addSong(iniList.get(i));
        }
    }

    /**
     * Generates the hash value of the table where the new node
     * should be stored.
     *
     * @param song The Song to hash.
     * @return Returns the hashed int for the location.
     */
    private int hash(SongDataInterface song) {
        int hash;
        hash = Math.abs(song.getTitle().hashCode()) % this.capacity;
        return hash;
    }

    /**
     * Adds the given song to the hash table.
     *
     * @param song The song to be added to the hash table.
     */
    @Override
    public void addSong(SongDataInterface song) {
        int addLoc = hash(song);

        if (song == null || containsSong(song)) {
        } else {
            songTable[addLoc].add(song);
            size++;
        }
    }

    /**
     * Searches the hash table to find a matching song to the one given.
     *
     * @param song The song to search the hash table for.
     * @return Return true if the song is contained, and false otherwise.
     */
    @Override
    public boolean containsSong(SongDataInterface song) {
        boolean contains = false;

        for (int i = 0; i < songTable.length; i++) {
            for (int j = 0; j < songTable[i].size(); j++) {
                if (songTable[i].get(j).getTitle().equals(song.getTitle())) {
                    contains = true;
                }
            }
        }
        return contains;
    }

    /**
     * Finds all the songs with the matching titleWord and will add the titles
     * to a list to be returned.
     *
     * @param titleWord Given word to search for.
     * @return Returns a list of the songs with a matching titleWord.
     */
    @Override
    public List<String> findTitles(String titleWord) {
        List<String> titles = new LinkedList<String>();

        for (int i = 0; i < songTable.length; i++) {
            for (int j = 0; j < songTable[i].size(); j++) {
                if (songTable[i].get(j).getTitle().contains(titleWord)) {
                    titles.add(songTable[i].get(j).getTitle());
                }
            }
        }
        return titles;
    }

    /**
     * Finds all the songs with the matching titleWord and will
     * return a list of all the artists of those songs.
     *
     * @param titleWord Given word to search for.
     * @return Returns a list of the artist of the songs with a matching titleWord.
     */
    @Override
    public List<String> findArtists(String titleWord) {
        List<String> artists = new LinkedList<String>();

        for (int i = 0; i < songTable.length; i++) {
            for (int j = 0; j < songTable[i].size(); j++) {
                if (songTable[i].get(j).getTitle().contains(titleWord)) {
                    artists.add(songTable[i].get(j).getArtist());
                }
            }
        }
        return artists;
    }

    /**
     * Finds all the songs that have the titleWord and the same
     * year, then returns a number of songs that were contained
     * in those parameters.
     *
     * @param titleWord Given word to search for.
     * @param year      Given number to search for.
     * @return Returns the number of songs published in that year.
     */
    @Override
    public int findNumberOfSongsInYear(String titleWord, int year) {
        int counter = 0;

        for (int i = 0; i < songTable.length; i++) {
            for (int j = 0; j < songTable[i].size(); j++) {
                if (songTable[i].get(j).getYearPublished() == year && songTable[i].get(j).getTitle().contains(titleWord)) {
                    counter++;
                }
            }
        }
        return counter;
    }
}

// placeholder(s) (implemented with proposal, and possibly added to later)

class SearchBackEndPlaceholder implements SearchBackEndInterface {
    private SongDataInterface onlySong;

    public void addSong(SongDataInterface song) {
        this.onlySong = song;
    }
    public boolean containsSong(SongDataInterface song) {
        return onlySong.equals(song);
    }
    public List<String> findTitles(String titleWord) {
        List<String> titles = new LinkedList<>();
        if(onlySong.getTitle().contains(titleWord))
            titles.add(onlySong.getTitle());
        return titles;
    }
    public List<String> findArtists(String titleWord) {
        List<String> artists = new LinkedList<>();
        if(onlySong.getArtist().contains(titleWord))
            artists.add(onlySong.getArtist());
        return artists;
    }
    public int findNumberOfSongsInYear(String titleWord, int year) {
        if(onlySong.getYearPublished() == year) return 1;
        return 0;
    }
}
