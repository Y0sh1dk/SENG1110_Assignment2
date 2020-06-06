/**
 * Class: SongCollection
 * Class that holds main program loop and allows for users to interact with Album and Song class's through a TIO
 *
 * @author Yosiah de Koeyer
 * @Student_Number c3329520
 * @Last_Edit 08/05/2020
 */

import java.util.*;

public class SongCollection
{
//	private Album album1 = null, album2 = null, album3 = null; // Initialise albums of type Album
	private final int MAX_ALBUMS = 5;
	private int album_counter = 0;
	private Album[] albums = new Album[MAX_ALBUMS];  // Array of length MAX_ALBUMS of class type Album

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

	private void listAlbums() {

	}

	private void createAlbum() {

	}

	private void deleteAlbum() {

	}

	private void listSongsFromAlbum() {

	}

	private void addSongToAlbum() {

	}

	private void deleteSongFromAlbum() {

	}

	private void listSongsUnderTime() {

	}

	private void listSongsOfGenre() {

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

