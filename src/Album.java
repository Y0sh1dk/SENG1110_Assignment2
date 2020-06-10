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

    private final int MAX_SONGS = 5;

    private String name;
    private Song[] songs = new Song[MAX_SONGS];
    private int songs_counter = 0;

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

    public int getSongs_counter() {
        return this.songs_counter;
    }

    public int getSONG_MAX() {
        return this.MAX_SONGS;
    }

    public String listAllSongs(boolean details) {
        String songList = "";
        if (details == true) {
            for (int i=0; i<songs_counter; i++) {
                songList += "Song" + i + ": "  + "\n\tName: " + songs[i].getName() + "\n\tArtist: " + songs[i].getArtist() + "\n\tDuration: " + songs[i].getDuration() + "\n\tGenre: " + songs[i].getGenre() + "\n\n";
            }
        } else { // If details == false
            for (int i=0; i<songs_counter; i++) {
                songList += "Song" + i + ": "  + "\n\tName: " + songs[i].getName() + "\n\tArtist: " + songs[i].getArtist() + "\n\n";
            }
        }
        return songList;
    }


    public String listSongsUnderTime(int timeMax) {
        String songList = "";
        for (int i=0; i<songs_counter; i++) {
            if (songs[i].getDuration() <= timeMax) {
                songList += songs[i].getArtist() + " - " + songs[i].getName() + "\n";
            }
        }
        return  songList;
    }


    public int deleteSong(String songName) {
        for (int i=0; i<songs_counter; i++) {
            if (this.songs[i].getName().equalsIgnoreCase(songName)) {
                for (int j=i; j<songs_counter-1; j++) {  // Shift
                    songs[j] = songs[j+1];
                }
                songs_counter--;
                return 1;  // Successful
            }
        }
        return 0;
    }


    public int addSong(String inputName, String inputArtist, int inputDuration, String inputGenre) {
        if (!this.doesSongExist(inputName, inputArtist, inputDuration)) { // if song doesnt exist, continue
            this.calculateTotalTime();
            if (this.totalTime + inputDuration <= this.MAX_TIME) {
                songs[songs_counter] = new Song(inputName, inputArtist, inputDuration, inputGenre);
                songs_counter++;
                alphaSortSongs();
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }


    private void calculateTotalTime() {
        this.totalTime = 0;
        int time = 0;
        for (int i=0; i<songs_counter; i++) {
            time += this.songs[i].getDuration();
        }
        this.totalTime = time;
    }


    private boolean doesSongExist(String inputName, String inputArtist, int inputDuration) {
        for (int i=0; i<songs_counter; i++) {
            if (this.songs[i].getName().equalsIgnoreCase(inputName) && this.songs[i].getArtist().equalsIgnoreCase(inputArtist) && this.songs[i].getDuration() == inputDuration) {
                return true;
            }
        }
        return false;
    }


    public String songsOfGenre(String inputgenre) {
        String songOfGenreList = "\nAlbum Name: " + name + "\n";

        for (int i=0; i<songs_counter; i++) {
            if (songs[i].getGenre().equalsIgnoreCase(inputgenre)) {
                songOfGenreList += songs[i].getArtist() + " - " + songs[i].getName() + "\n";
            }
        }
        if (songOfGenreList.equals("\nAlbum Name: " + name + "\n")) {
            songOfGenreList += "None!\n";
        }
        return songOfGenreList;
    }


    private void alphaSortSongs() {
        for (int b = 0; b < MAX_SONGS; b++) {   // Looping multiple times fixes some issues?
            for (int i = 0; i < songs.length - 1; i++) {
                Song a = songs[i];
                Song next = songs[i + 1];

                if (next != null) { // If next song actually exists
                    String a_name = a.getName().toLowerCase();
                    String next_name = next.getName().toLowerCase();

                    int min_len;
                    if (a_name.length() > next_name.length()) {
                        min_len = next_name.length();
                    } else if (a_name.length() < next_name.length()) {
                        min_len = a_name.length();
                    } else {  // equal length
                        min_len = next_name.length();
                    }

                    for (int j = 0; j < min_len; j++) {
                        if (a_name.charAt(j) > next_name.charAt(j)) { // Compare letters
                            // swap
                            Song tmp = songs[i];
                            songs[i] = songs[i + 1];
                            songs[i + 1] = tmp;
                            break;
                        } else if (a_name.charAt(j) < next_name.charAt(j)) { // Correct alphabetically
                            break;
                        }
                    }
                }
            }
        }
    }

}
