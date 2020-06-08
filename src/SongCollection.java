/**
 * Class: SongCollection
 * Class that holds main program loop and allows for users to interact with Album and Song class's through a TIO
 *
 * @author Yosiah de Koeyer
 * @Student_Number c3329520
 * @Last_Edit 08/05/2020
 */


// TODO: Add breaks to loops?


import java.util.*;

public class SongCollection
{
	private final int MAX_ALBUMS = 5;
	private Album[] albums = new Album[MAX_ALBUMS];  // Array of length MAX_ALBUMS of class type Album
	private int album_counter = 0;

	/**
	 * Method: run()
	 *
	 *  Main program loop, doesnt end until user specifies or if an exception is thrown.
	 *  Prints to console to form a user interface
	 */
	private void run() {
		while (true) {  // Run forever
			Scanner scanner = new Scanner(System.in);  // Create new scanner object
			int option = 0;
			System.out.println("\n1) List albums");
			System.out.println("2) Create album");
			System.out.println("3) Delete album");
			System.out.println("4) List songs from album");
			System.out.println("5) Add song to album");
			System.out.println("6) Delete song from album");
			System.out.println("7) List all songs whose duration is under a certain time");
			System.out.println("8) List all songs of a specific genre");
			System.out.println("9) Exit program\n");
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
				String album = "Album" + i + ":" + albums[i].getName();
				System.out.println(album);
			}
		} else {
			System.out.println("There are currently no albums");
		}
	}

	private void createAlbum(Scanner scanner) {
		if (album_counter < MAX_ALBUMS) {
			String albumName;
			System.out.println("Please enter a Album name:");
			scanner.nextLine();
			albumName = scanner.nextLine().strip();  // Strip whitespace
			if (!doesAlbumExist(albumName)) { // if album with that name doesnt exist
				albums[album_counter] = new Album(albumName);
				album_counter++;
			}
		}
	}

	private void deleteAlbum(Scanner scanner) {
		if (album_counter > 0) {
			String albumName;
			System.out.println("Please enter a Album name:");
			scanner.nextLine();
			albumName = scanner.nextLine().strip();  // Strip whitespace
			if (doesAlbumExist(albumName)) {
				for (int i=0; i<album_counter; i++) {
					if (albums[i].getName().equalsIgnoreCase(albumName)) {
						albums[i] = null;
						album_counter--;
					}
				}
			}
		}
	}

	private void listSongsFromAlbum(Scanner scanner) {
		if (album_counter > 0) {  // there are actually albums
			String albumName;
			System.out.println("Please Enter Album Name:");
			scanner.nextLine(); // to throw out '/n'
			albumName = scanner.nextLine().strip();  // Strip whitespace
			if (doesAlbumExist(albumName)) {  // If album does exist
				for (int i = 0; i < album_counter; i++) {
					if (albums[i].getName().equalsIgnoreCase(albumName)) {
						if (albums[i].listAllSongs(true).equals("")) {   // If album.listAllSongs returns nothing
							System.out.println("Album" + i + ": " + albums[i].getName() + "\nEmpty!");
						} else {
							System.out.println("Album" + i + ": " + albums[i].getName() + "\n" + albums[i].listAllSongs(true));   // Prints all songs
						}
					}
				}
			} else {
				System.out.println("There are currently no albums with that name");
			}
		} else {
			System.out.println("There are currently no albums");
		}
	}

	private void addSongToAlbum(Scanner scanner) {
		if (album_counter > 0) {  // If there are albums to add too
			String albumName;
			System.out.println("Please enter album name you would like to add a song too:");
			scanner.nextLine(); // to throw out '/n'
			albumName = scanner.nextLine().strip(); // Strip whitespace
			if (doesAlbumExist(albumName)) {  // If album does exist
				for (int i = 0; i < album_counter; i++) {
					if (albums[i].getName().equalsIgnoreCase(albumName)) {  // If the album
						if (albums[i].getSongs_counter() < albums[i].getSONG_MAX() ) {
							System.out.println("Please enter song Name:");
							String songName = scanner.nextLine();
							System.out.println("Please enter song Artist:");
							String songArtist = scanner.nextLine();
							System.out.println("Please enter song Duration (in seconds):");
							int songDuration = scanner.nextInt();
							String songGenre = getValidGenre(scanner);
							albums[i].addSong(songName, songArtist, songDuration, songGenre);
						}
					}
				}
			}
		}
	}

	private void deleteSongFromAlbum(Scanner scanner) {

	}

	private void listSongsUnderTime(Scanner scanner) {
		if (album_counter > 0) {  // If there are ablums
			int time;
			String songList = "";
			System.out.println("Please enter Time to find songs under");
			scanner.nextLine(); // to throw out '/n'
			time = scanner.nextInt();
			scanner.nextLine(); // to throw out '/n'
			System.out.println("Songs under the time " + time + " seconds:");
			for (int i=0; i<album_counter; i++) {
				songList += albums[i].listSongsUnderTime(time);
			}
			if (songList.equals("")) {
				System.out.println("None");
			} else {
				System.out.println(songList);
			}
		}
	}

	private void listSongsOfGenre(Scanner scanner) {
		if (album_counter > 0) {
			String genre;
			System.out.println("Please Enter Genre:");
			scanner.nextLine(); // to throw out '/n'
			genre = scanner.nextLine().strip();
			System.out.println("\nAll songs of the genre " + genre + ":");
			String songsOfGenreList = "";
			for (int i=0; i<album_counter; i++) {
				songsOfGenreList += albums[i].songsOfGenre(genre);
			}
			if (songsOfGenreList.equals("")) {
				System.out.println("None");
			} else {
				System.out.println(songsOfGenreList);
			}
		}
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
			genre = scanner.nextLine();
			if (genre.equalsIgnoreCase("rock") || genre.equalsIgnoreCase("pop") || genre.equalsIgnoreCase("hip-hop") || genre.equalsIgnoreCase("bossa nova")) {
				valid = true;
			} else {
				System.out.println("Invalid Genre");
			}
		}
		return genre;
	}


	private void returnToMenu(Scanner scanner) {
		System.out.println("\nPress \"ENTER\" Key to return to menu");
		scanner.nextLine();
	}

	// Entrypoint
	public static void main(String[] args) {
		SongCollection sc = new SongCollection();  // Create new instance of SongCollection class
		sc.run();  // run SongCollection.run() method
	}
}

