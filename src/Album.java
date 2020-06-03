/**
 * Class: Album
 * A Class used to represent a Album. Is used to store instances of the Song class and provide all functionality
 * relating to a album.
 *
 * @author Yosiah de Koeyer
 * @Student_Number c3329520
 * @Last_Edit 08/05/2020
 */

public class Album {

    private String name;
    private Song song1 = null , song2 = null, song3 = null, song4 = null;
    private boolean isFull = false;
    private int totalTime = 0;
    private final int MAX_TIME = 720; //12 minutes


    //  Constructor if args given
    public Album(String inputName) {
        this.name = inputName;

    }

    //  Constructor if no args given (Never used)
    public Album() {
        this.name = null;
    }

// Getter/Setter methods
    public void setName(String inputName) {
        this.name = inputName;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Method: listAllSongs()
     *
     * @param details if true, return all details, if not return just name and artist
     * @return string containing all songs, with or without full details
     */
    public String listAllSongs(boolean details) {
        String songList = "";
        if (details == true) {
            if (this.song1 != null) {  // If song exists
                songList += "\tSong1:" + "\n\tName:" + song1.getName() + "\n\tArtist:" + song1.getArtist() + "\n\tDuration:" + song1.getDuration() + "\n\tGenre:" + song1.getGenre() + "\n\n";
            }
            if (this.song2 != null) {  // If song exists
                songList += "\tSong2:" + "\n\tName:" + song2.getName() + "\n\tArtist:" + song2.getArtist() + "\n\tDuration:" + song2.getDuration() + "\n\tGenre:" + song2.getGenre() + "\n\n";
            }
            if (this.song3 != null) {  // If song exists
                songList += "\tSong3:" + "\n\tName:" + song3.getName() + "\n\tArtist:" + song3.getArtist() + "\n\tDuration:" + song3.getDuration() + "\n\tGenre:" + song3.getGenre() + "\n\n";
            }
            if (this.song4 != null) {  // If song exists
                songList += "\tSong4:" + "\n\tName:" + song4.getName() + "\n\tArtist:" + song4.getArtist() + "\n\tDuration:" + song4.getDuration() + "\n\tGenre:" + song4.getGenre() + "\n\n";
            }
        }
        else { // If details == false
            if (this.song1 != null) {  // If song exists
                songList += "\tSong1:" + "\n\tName:" + song1.getName() + "\n\tArtist:" + song1.getArtist() + "\n\n";
            }
            if (this.song2 != null) {  // If song exists
                songList += "\tSong2:" + "\n\tName:" + song2.getName() + "\n\tArtist:" + song1.getArtist() + "\n\n";
            }
            if (this.song3 != null) {  // If song exists
                songList += "\tSong3:" + "\n\tName:" + song3.getName() + "\n\tArtist:" + song3.getArtist() + "\n\n";
            }
            if (this.song4 != null) {  // If song exists
                songList += "\tSong4:" + "\n\tName:" + song4.getName() + "\n\tArtist:" + song4.getArtist() + "\n\n";
            }
        }
        return songList;
    }

    /**
     * Method: listsSongsUnderTime()
     *
     * @param timeMax maximum time to list songs under
     * @return string containing lists of songs under certain time
     */
    public String listSongsUnderTime(int timeMax) {
        String songList = "";
        if (this.song1 != null) {  // If song exists
            if (this.song1.getDuration() <= timeMax) {  // If song duration equal or less than input time
                songList += this.song1.getArtist() + " - " + this.song1.getName() + "\n";
            }
        }
        if (this.song2 != null) {  // If song exists
            if (this.song2.getDuration() <= timeMax) {  // If song duration equal or less than input time
                songList += this.song2.getArtist() + " - " + this.song2.getName() + "\n";
            }
        }
        if (this.song3 != null) {  // If song exists
            if (this.song3.getDuration() <= timeMax) {  // If song duration equal or less than input time
                songList += this.song3.getArtist() + " - " + this.song3.getName() + "\n";
            }
        }
        return  songList;
    }

    /**
     * Method: deleteSong()
     *
     * @param songName string containing name of song
     * @return int, 1 if successful, 0 if not successful
     */
    public int deleteSong(String songName) {
        if (this.song1 != null) {  // If song exists
            if (this.song1.getName().equalsIgnoreCase(songName)) {  // if song name equal to input name
                this.song1 = null; // delete
                return 1;
            }
        } if (this.song2 != null) {  // If song exists
            if (this.song2.getName().equalsIgnoreCase(songName)) {  // if song name equal to input name
                this.song2 = null; // delete
                return 1;
            }
        } if (this.song3 != null) {  // If song exists
            if (this.song3.getName().equalsIgnoreCase(songName)) {  // if song name equal to input name
                this.song3 = null; // delete
                return 1;
            }
        }
        return 0;
    }


    /**
     * Method: addSong()
     *
     * @param inputName String containing name of song
     * @param inputArtist String containing artist of song
     * @param inputDuration Int containing duration of song
     * @param inputGenre String containing genre of song
     * @return Int, 0 if album is full, 1 if successful, 2 if adding will exceed time limit, 3 if already exists
     */
    public int addSong(String inputName, String inputArtist, int inputDuration, String inputGenre) {
        if (!this.doesSongExist(inputName, inputArtist, inputDuration)) { // if song doesnt exist, continue
            this.calculateTotalTime();
            if (this.totalTime + inputDuration <= this.MAX_TIME) {
                if (this.song1 == null) {  // If song doesnt exist
                    this.song1 = new Song(inputName, inputArtist, inputDuration, inputGenre); // Create new song instance
                    this.calculateTotalTime();
                    return 1;
                } else if (this.song2 == null) {  // If song doesnt exist
                    this.song2 = new Song(inputName, inputArtist, inputDuration, inputGenre); // Create new song instance
                    this.calculateTotalTime();
                    return 1;
                } else if (this.song3 == null) {  // If song doesnt exist
                    this.song3 = new Song(inputName, inputArtist, inputDuration, inputGenre); // Create new song instance
                    this.calculateTotalTime();
                    return 1;
                } else if (this.song4 == null) {  // If song doesnt exist
                    this.song4 = new Song(inputName, inputArtist, inputDuration, inputGenre); // Create new song instance
                    this.calculateTotalTime();
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }

    /**
     * Method: calculateTotalTime()
     *
     * Calculates total run time of album and assigns it to totalTime attribute
     */
    private void calculateTotalTime() {
        this.totalTime = 0;
        int time = 0;
        if (this.song1 != null) {  // If song exists
            time += this.song1.getDuration();
        }
        if (this.song2 != null) {  // If song exists
            time += this.song2.getDuration();
        }
        if (this.song3 != null) {  // If song exists
            time += this.song3.getDuration();
        }
        if (this.song4 != null) {  // If song exists
            time += this.song4.getDuration();
        }
        this.totalTime = time;
    }

    /**
     * Method: doesSongExist()
     *
     * Checks if a song exists
     *
     * @param inputName String containing name of song
     * @param inputArtist String containing artist of song
     * @param inputDuration Int containing duration of song
     * @return Boolean, True if successful, False if not successful
     */
    private boolean doesSongExist(String inputName, String inputArtist, int inputDuration) {
        if (this.song1 != null) {  // If song exists
            if (this.song1.getName().equalsIgnoreCase(inputName) && this.song1.getArtist().equalsIgnoreCase(inputArtist) && this.song1.getDuration() == inputDuration) {
                return true;
            }
        } if (this.song2 != null) {  // If song exists
            if (this.song2.getName().equalsIgnoreCase(inputName) && this.song2.getArtist().equalsIgnoreCase(inputArtist) && this.song2.getDuration() == inputDuration) {
                return true;
            }
        } if (this.song3 != null) {  // If song exists
            if (this.song3.getName().equalsIgnoreCase(inputName) && this.song3.getArtist().equalsIgnoreCase(inputArtist) && this.song3.getDuration() == inputDuration) {
                return true;
            }
        } if (this.song4 != null) {  // If song exists
            if (this.song4.getName().equalsIgnoreCase(inputName) && this.song4.getArtist().equalsIgnoreCase(inputArtist) && this.song4.getDuration() == inputDuration) {
                return true;
            }
        }
        return false;
    }


    /**
     * Method: songsOfGenre
     *
     * @param inputgenre genre of which to list songs from
     * @return string containing a list of songs of a specific genre
     */
    public String songsOfGenre(String inputgenre) {
        String songOfGenreList = "";
        if (this.song1 != null) {  // If song exists
            if (this.song1.getGenre().equalsIgnoreCase(inputgenre)) {  // if song name equal to input name
                songOfGenreList += "Song1: " + this.song1.getArtist() + " - " + this.song1.getName() + "\n";
            }
        }
        if (this.song2 != null) {  // If song exists
            if (this.song2.getGenre().equalsIgnoreCase(inputgenre)) {  // if song name equal to input name
                songOfGenreList += "Song2: " + this.song2.getArtist() + " - " + this.song2.getName() + "\n";
            }
        }
        if (this.song3 != null) {  // If song exists
            if (this.song3.getGenre().equalsIgnoreCase(inputgenre)) {  // if song name equal to input name
                songOfGenreList += "Song3: " + this.song3.getArtist() + " - " + this.song3.getName() + "\n";
            }
        }
        if (this.song4 != null) {  // If song exists
            if (this.song4.getGenre().equalsIgnoreCase(inputgenre)) {  // if song name equal to input name
                songOfGenreList += "Song4: " + this.song3.getArtist() + " - " + this.song4.getName() + "\n";
            }
        }
        if (songOfGenreList.equals("")) {
            return("None!");
        } else {
            return songOfGenreList;
        }
    }

    /**
     * Method: howManySongs()
     *
     * @return int containing number of songs present in album
     */
    public int howManySongs() {
        int n=0;
        if (song1 != null) {  // If song exists
            n++;
        }
        if (song2 != null) {  // If song exists
            n++;
        }
        if (song3 != null) {  // If song exists
            n++;
        }
        if (song4 != null) {  // If song exists
            n++;
        }
        return n;
    }
}
