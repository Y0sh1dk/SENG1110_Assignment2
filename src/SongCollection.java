/**
 * Class: SongCollection
 * Class that holds main program loop and allows for users to interact with Album and Song class's through a TIO
 *
 * @author Yosiah de Koeyer
 * @Student_Number c3329520
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SongCollection
{

	private final String FILE_NAME = "Collection.txt";
	private final int MAX_ALBUMS = 4;
	private Album[] albums = new Album[MAX_ALBUMS];  // Array of length MAX_ALBUMS of class type Album
	private int album_counter = 0;

	/**
	 * Method: run()
	 *
	 *  Main program loop, doesnt end until user specifies or if an exception is thrown.
	 *  Prints to console to form a user interface
	 */
	private void run() {
		Scanner scanner = new Scanner(System.in);  // Create new scanner object

		System.out.println("############################################################################################");
		System.out.println("SENG1110 - Assignment 2\n");
		System.out.println("Name: Yosiah de Koeyer");
		System.out.println("Student No: c332950");
		System.out.println("\n NOTES:");
		System.out.println("\t- All entered strings are striped of whitespace and converted to all lowercase");
		System.out.println("\t- Alphabetical sorting algorithm is implemented for albums and songs");
		System.out.println("\t- Max number of albums is 4");
		System.out.println("\t- Max number of songs per album is 5");
		System.out.println("\t- Albums and songs can be read in from 'Collection.txt', located in same directory");

		System.out.println("############################################################################################");
		returnToMenu(scanner, "To continue to program...");
		while (true) {  // Run forever

			int option = 0;
			System.out.println("\n1) List albums");
			System.out.println("2) Create album");
			System.out.println("3) Delete album");
			System.out.println("4) List songs from album");
			System.out.println("5) Add song to album");
			System.out.println("6) Delete song from album");
			System.out.println("7) List all songs whose duration is under a certain time");
			System.out.println("8) List all songs of a specific genre");
			System.out.println("9) Load from file");
			System.out.println("10) Exit program\n");
			System.out.println("Please Select a Option:");
			try {   // Try to scan for next int
				option = scanner.nextInt();
			} catch (Exception InputMismatchException){   // catch exception if not int so doesnt crash
				System.out.println("Did not Provide a integer");
			}

			switch (option) {
				case 1:
					listAlbums(scanner);
					break;
				case 2:
					createAlbum(scanner);
					break;
				case 3:
					deleteAlbum(scanner);
					break;
				case 4:
					listSongsFromAlbum(scanner);
					break;
				case 5:
					addSongToAlbum(scanner);
					break;
				case 6:
					deleteSongFromAlbum(scanner);
					break;
				case 7:
					listSongsUnderTime(scanner);
					break;
				case 8:
					listSongsOfGenre(scanner);
					break;
				case 9:
					loadFromFile(scanner);
					break;
				case 10:
					System.out.println("Exiting Program...");
					System.exit(0);  // Exit program
					break;
				default:
					break;
			}
		}
	}


	private void listAlbums(Scanner scanner) {
		scanner.nextLine(); // to throw out '/n'
		String albumList = "";
		if (album_counter > 0) {
			for (int i=0; i<album_counter; i++) {
				String album = "Album" + i + ": " + albums[i].getName();
				System.out.println(album);
			}
		} else {
			System.out.println("Error: There are currently no albums");
		}
		returnToMenu(scanner);  // Make user press Enter
	}


	private void createAlbum(Scanner scanner, String ... album_names) {
		boolean from_file;
		if (album_names.length > 0) {
			from_file = true;
			for (int i=0; i < album_names.length; i++) {
				if (album_names[i] != null) { // if album is valid
					String albumName = album_names[i].strip().toLowerCase();
					if (!doesAlbumExist(albumName)) { // if album with that name doesnt exist
						albums[album_counter] = new Album(albumName);
						album_counter++;
					}
				}
			}
		} else {
			from_file = false;
			if (album_counter < MAX_ALBUMS) {
				String albumName;
				System.out.println("Please enter a Album name:");
				scanner.nextLine();
				albumName = scanner.nextLine().strip().toLowerCase();  // Strip whitespace
				if (!doesAlbumExist(albumName)) { // if album with that name doesnt exist
					albums[album_counter] = new Album(albumName);
					album_counter++;

				} else {
					System.out.println("Error: A album with the name '" + albumName + "' already exists");
				}
			} else {
				System.out.println("Error: Maximum number of albums reached! (Max 4)");
				scanner.nextLine();
			}
		}
		if (from_file) { // If from_file == true
			System.out.println("Albums added from file '" + FILE_NAME + "'");
			alphaSortAlbums();
			returnToMenu(scanner, "to continue");  // Make user press Enter
		} else {
			alphaSortAlbums();
			returnToMenu(scanner);  // Make user press Enter
		}


	}


	private void deleteAlbum(Scanner scanner) {
		if (album_counter > 0) {
			String albumName;
			System.out.println("Please enter a Album name:");
			scanner.nextLine();
			albumName = scanner.nextLine().strip().toLowerCase();  // Strip whitespace
			if (doesAlbumExist(albumName)) {
				for (int i=0; i<album_counter; i++) {
					if (albums[i].getName().equalsIgnoreCase(albumName)) {
						for (int j=i; j<album_counter-1; j++) {  // Shift
							albums[j] = albums[j + 1];
						}
						System.out.println("Album '" + albumName + "' was successfuly deleted");
						album_counter--;
					}
				}
			} else {
				System.out.println("Error: No album with the name '" + albumName + "' exists");
			}
		} else {
			System.out.println("Error: There are currently no albums, please create one first");
		}
		returnToMenu(scanner);  // Make user press Enter
	}


	private void listSongsFromAlbum(Scanner scanner) {
		if (album_counter > 0) {  // there are actually albums
			String albumName;
			System.out.println("Please Enter Album Name:");
			scanner.nextLine(); // to throw out '/n'
			albumName = scanner.nextLine().strip().toLowerCase();  // Strip whitespace
			if (doesAlbumExist(albumName)) {  // If album does exist
				for (int i = 0; i < album_counter; i++) {
					if (albums[i].getName().equalsIgnoreCase(albumName)) {
						if (albums[i].listAllSongs(true).equals("")) {   // If album.listAllSongs returns nothing
							System.out.println("Album: " + albums[i].getName() + "\nEmpty!");
						} else {
							System.out.println("Album: " + albums[i].getName() + "\n" + albums[i].listAllSongs(true));   // Prints all songs
						}
					}
				}
			} else {
				System.out.println("Error: No album with the name '" + albumName + "' exists");
			}
		} else {
			System.out.println("Error: There are currently no albums, please create one first");
		}
		returnToMenu(scanner);  // Make user press Enter
	}


	private void addSongToAlbum(Scanner scanner, String[] ... song_details) {
		boolean from_file;
		if (song_details.length > 0) {
			from_file = true;
			for (int i = 0; i < song_details.length; i++) {
				for (int j = 0; j < album_counter; j++) {
					if (albums[j].getName().equalsIgnoreCase(song_details[i][0])) {
						if (song_details[i][1] != null) { // If there is a song at that index
							String songName = song_details[i][1].strip().toLowerCase();
							String songArtist = song_details[i][2].strip().toLowerCase();
							int songDuration = Integer.parseInt(song_details[i][3]);
							String songGenre = song_details[i][4].strip().toLowerCase();
							albums[j].addSong(songName, songArtist, songDuration, songGenre);
						}
					}
				}
			}

		} else {
			from_file = false;
			if (album_counter > 0) {  // If there are albums to add too
				String albumName;
				System.out.println("Please enter album name you would like to add a song too:");
				scanner.nextLine(); // to throw out '/n'
				albumName = scanner.nextLine().strip().toLowerCase(); // Strip whitespace
				if (doesAlbumExist(albumName)) {  // If album does exist
					for (int i = 0; i < album_counter; i++) {
						if (albums[i].getName().equalsIgnoreCase(albumName)) {  // If the album
							if (albums[i].getSongs_counter() < albums[i].getSONG_MAX() ) {
								System.out.println("Please enter song Name:");
								String songName = scanner.nextLine().strip().toLowerCase();
								System.out.println("Please enter song Artist:");
								String songArtist = scanner.nextLine().strip().toLowerCase();
								System.out.println("Please enter song Duration (in seconds):");
								int songDuration = scanner.nextInt();
								String songGenre = getValidGenre(scanner);
								int code = albums[i].addSong(songName, songArtist, songDuration, songGenre);
								if (code == 1) {
									System.out.println("Successfully added song");
								} else if (code == 2) {
									System.out.println("Error: adding will exceed album time limit (720 seconds)");
								} else if (code == 3) {
									System.out.println("Error: song it already exists in album");
								}
							}
						}
					}
				} else {
					System.out.println("Error: No album with the name '" + albumName + "' exists");
				}
			} else {
				scanner.nextLine(); // to throw out '/n'
				System.out.println("Error: There are currently no albums, please create one first");
			}
		} if (from_file) { // If from_file == true
			System.out.println("Songs added from file '" + FILE_NAME + "'");
			alphaSortAlbums();
			returnToMenu(scanner, "to continue");  // Make user press Enter
		} else {
			alphaSortAlbums();
			returnToMenu(scanner);  // Make user press Enter
		}
	}

	private void deleteSongFromAlbum(Scanner scanner) {
		String albumName;
		scanner.nextLine(); // to throw out '/n'
		System.out.println("Please Enter Album Name:");
		albumName = scanner.nextLine().strip().toLowerCase();
		if (doesAlbumExist(albumName)) { // if album with that name exists
			for (int i = 0; i < album_counter; i++) {
				if (albums[i].getName().equalsIgnoreCase(albumName)) {  // If the album
					System.out.println("Please enter song Name:");
					String songName = scanner.nextLine().strip().toLowerCase();
					int song_code = albums[i].deleteSong(songName);
					if (song_code == 1) {
						System.out.println("Success!");
					} else {
						System.out.println("Error: Song with that names doesnt exist!");
					}
				}
			}
		} else {
				System.out.println("Album does not exist");
		}
		returnToMenu(scanner);  // Make user press Enter
	}


	private void listSongsUnderTime(Scanner scanner) {
		if (album_counter > 0) {  // If there are ablums
			int time;
			String songList = "";
			System.out.println("Please enter time to find songs under (in seconds)");
			scanner.nextLine(); // to throw out '/n'
			time = scanner.nextInt();
			scanner.nextLine(); // to throw out '/n'
			System.out.println("Songs under the time " + time + " seconds:\n");
			for (int i=0; i<album_counter; i++) {
				songList += albums[i].listSongsUnderTime(time);
			}
			if (songList.equals("")) {
				System.out.println("None");
			} else {
				System.out.println(songList);
			}
		} else {
			System.out.println("Error: There are currently no albums, please create one first");
		}
		returnToMenu(scanner);  // Make user press Enter
	}


	private void listSongsOfGenre(Scanner scanner) {
		if (album_counter > 0) {
			String genre;
			System.out.println("Please Enter Genre:");
			scanner.nextLine(); // to throw out '/n'
			genre = scanner.nextLine().strip().toLowerCase();
			System.out.println("\nAll songs of the genre '" + genre + "':");
			String songsOfGenreList = "";
			for (int i=0; i<album_counter; i++) {
				songsOfGenreList += albums[i].songsOfGenre(genre);
			}
			System.out.println(songsOfGenreList);
		} else {
			System.out.println("Error: There are currently no albums, please create one first");
			scanner.nextLine(); // to throw out '/n'
		}
		returnToMenu(scanner);  // Make user press Enter
	}


	private boolean doesAlbumExist(String albumName) {
		for (int i=0; i<album_counter; i++) {
			if (albums[i].getName().equalsIgnoreCase(albumName)) {
				return true;
			}
		}
		return false;
	}


	private String getValidGenre(Scanner scanner) {
		boolean valid = false;
		String genre = "";
		scanner.nextLine(); // to throw out '/n'
		while (!valid) {
			System.out.println("Please enter song Genre:");
			genre = scanner.nextLine().strip().toLowerCase();
			if (genre.equalsIgnoreCase("rock") || genre.equalsIgnoreCase("pop") || genre.equalsIgnoreCase("hip-hop") || genre.equalsIgnoreCase("bossa nova")) {
				valid = true;
			} else {
				System.out.println("Error: Invalid Genre");
			}
		}
		return genre;
	}


	private void loadFromFile(Scanner scanner) {
		try {
			String[][] data = arrayFromFile();  // Load data from .txt into 2D array
//			data = formatCollectionArray(data); // Resize array to correct size
			loadArray(scanner, data);
		} catch (FileNotFoundException e) {
			System.out.println("Error: cannot open file '" + FILE_NAME + "'");
			scanner.nextLine(); // to throw out '/n'
			returnToMenu(scanner);
		}
	}

	private void loadArray( Scanner scanner, String[][] data) {
//		System.out.println(Arrays.deepToString(data));

		// create array of album names
		String[] album_names = new String[data.length];
		for (int i=0; i<data.length; i++) {
			album_names[i] = data[i][0];
		}
		scanner.nextLine(); // to throw out '/n'
		createAlbum(scanner, album_names); // add albums

		// To add songs to albums
		addSongToAlbum(scanner, data);

	}


	private String[][] arrayFromFile() throws FileNotFoundException {
		Scanner inputStream;
		String[][] data = new String[4*5][5]; //  4*5 because 4 albums of 5 songs each
		int song_count = 0;
		int songs_code = 0;
		int album_count = 0;

		inputStream = new Scanner (new File(FILE_NAME));
		while (inputStream.hasNextLine()) {
			String line = inputStream.nextLine();
			if (line.contains("Album")) {
				songs_code = 0;
				song_count = 0;
				String[] split_line = line.split(" ", 2);
				for (int i=0; i < 5; i++) {
					data[album_count*5+i][0] = split_line[1];
				}
				album_count++;
			} else if (line.contains("Songs")) {
				songs_code = 1;
			}

			if (songs_code == 1) {
				if (line.contains("Name")) {
					String[] split_line = line.split(" ", 2);
					data[((album_count-1)*5)+song_count][1] = split_line[1];
				} else if (line.contains("Artist")) {
					String[] split_line = line.split(" ", 2);
					data[((album_count-1)*5)+song_count][2] = split_line[1];
				} else if (line.contains("Duration")) {
					String[] split_line = line.split(" ", 2);
					data[((album_count-1)*5)+song_count][3] = split_line[1];
				} else if (line.contains("Genre")) {
					String[] split_line = line.split(" ", 2);
					data[((album_count-1)*5)+song_count][4] = split_line[1];
					song_count++;
					}
				}
			}
			inputStream.close();
		return data;
	}


	private String[][] formatCollectionArray(String[][] input) {
		// Find how many valid entries in array
		int entries = 0;
		for (int i=0; i<input.length; i++) {
			if (input[i][1] != null) {
				entries++;
			}
		}
		String[][] new_array = new String[entries][input[0].length]; // Make new array of correct size
		// Add valid data from input array to new array
		int new_count = 0;
		for (int i=0; i<input.length; i++) {
			if (input[i][1] != null) {
				for (int j=0; j<input[0].length; j++) {
					new_array[new_count][j] = input[i][j];
				}
				new_count++;
			}
		}
		return new_array;
	}


	private void alphaSortAlbums() {
		for (int b = 0; b < MAX_ALBUMS; b++) {   // Looping multiple times fixes some issues?, inefficient
			for (int i = 0; i < albums.length - 1; i++) {
				Album a = albums[i];
				Album next = albums[i + 1];

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

					for (int j = 0; j < min_len; j++) {  // Compare letter by letter
						if (a_name.charAt(j) > next_name.charAt(j)) {
							// swap
							Album tmp = albums[i];
							albums[i] = albums[i + 1];
							albums[i + 1] = tmp;
							break;
						} else if (a_name.charAt(j) < next_name.charAt(j)) { // Correct alphabetically
							break;
						}
					}
				}
			}
		}
	}


	private void returnToMenu(Scanner scanner, String ... message) {
		if (message.length > 0) {
			System.out.println("\nPress \"ENTER\" " + message[0]);
		} else {
			System.out.println("\nPress \"ENTER\" Key to return to menu");
		}
		scanner.nextLine();

	}

	// Entrypoint
	public static void main(String[] args) {
		SongCollection sc = new SongCollection();  // Create new instance of SongCollection class
		sc.run();  // run SongCollection.run() method
	}
}

