/**
 * Class: Song
 * A class used to represent a song.
 *
 * @author Yosiah de Koeyer
 * @Student_Number c3329520
 * @Last_Edit 08/05/2020
 */

public class Song {

	private String name;
	private String artist;
	private int duration;
	private String genre;


	// Song constructor when args are given
	public Song(String inputName, String inputArtist, int inputDuration, String inputGenre) {
		this.name = inputName;
		this.artist = inputArtist;
		this.duration = inputDuration;
		this.genre = inputGenre;
	}

	// Song constructor when no args are given (Never used)
	public Song() {
		this.name = null;
		this.artist = null;
		this.duration = 0;
		this.genre = null;
	}


// Getter/Setter methods from here down
	public void setName(String inputName) {
		 this.name = inputName;
	}

	public String getName() {
		return this.name;
	}

	public void setArtist (String inputArtist) {
		this.artist = inputArtist;
	}

	public String getArtist() {
		return this.artist;
	}

	public void setDuration(int inputDuration) {
		this.duration = inputDuration;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setGenre(String inputGenre) {
		this.genre = inputGenre;
	}

	public String getGenre() {
		return this.genre;
	}
}
