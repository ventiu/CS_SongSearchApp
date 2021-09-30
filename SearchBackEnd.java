import java.util.List;

// interface (implemented with proposal)

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

// public class (implemented primarilly in final app week)

public class SearchBackEnd implements SearchBackEndInterface {

    @Override
    public void addSong(SongDataInterface song) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean containsSong(SongDataInterface song) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<String> findTitles(String titleWord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> findArtists(String titleWord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int findNumberOfSongsInYear(String titleWord, int year) {
        // TODO Auto-generated method stub
        return 0;
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
