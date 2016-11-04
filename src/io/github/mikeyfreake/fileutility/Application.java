package io.github.mikeyfreake.fileutility;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import io.github.mikeyfreake.fileutility.commands.MoveCommand;
import io.github.mikeyfreake.fileutility.commands.SearchCommand;

public class Application {

	private static Scanner scanIn;

	public static void main(String[] args) throws IOException {

		System.out.println("FileUtility starting up...");

		startScanner();

	}

	private static void startScanner() throws IOException {
		printCommands();

		scanIn = new Scanner(System.in);

		String inputString;
		List<File> fileList = null;

		// Read user input.
		while (!(inputString = requestInput("Enter command: ", false)).equals("q")) {

			switch (inputString) {
			case "s":
				fileList = doSearch();
				break;
			case "p":
				printCommands();
				break;
			case "m":
				if (fileList == null || fileList.isEmpty()) {
					System.out.println("You have not selected any files to move.");
					break;
				} else {
					doMove(fileList);
					break;
				}
			case "c":
				if (fileList == null || fileList.isEmpty()) {
					System.out.println("You have not selected any files to copy.");
					break;
				} else {
					doCopy(fileList);
					break;
				}
			}
		}

		scanIn.close();
		System.out.println("Bye!");
	}

	private static void doCopy(List<File> fileList) throws IOException {
		MoveCommand mc = new MoveCommand();

		String target = requestInput("Enter target directory: ", false);

		mc.doCopy(fileList, target);
	}

	private static void doMove(List<File> fileList) throws IOException {
		MoveCommand mc = new MoveCommand();

		String target = requestInput("Enter target directory: ", false);

		mc.doMove(fileList, target);
	}

	private static List<File> doSearch() throws IOException {
		SearchCommand sc = new SearchCommand();

		String regex, directory;

		regex = requestInput("Enter text: ", false);
		directory = requestInput("Enter folder directory: ", false);

		List<File> fileList = sc.textSearch(regex, directory);
		System.out.println("Found the following files: ");

		for (int i = 1; i <= fileList.size(); i++) {
			System.out.println("\t" + i + ": " + fileList.get(i - 1).getName());
		}

		return fileList;
	}

	private static void printCommands() {
		System.out.println("Command List" 
				+ "\n\tSearch for files containing text (s)" 
				+ "\n\tMove selected files (m)"
				+ "\n\tCopy selected files (c)" 
				+ "\n\tPrint commands (p)" 
				+ "\n\tQuit (q)");
	}

	private static String requestInput(String message, boolean acceptNull) {
		System.out.print(message);
		String inputString;

		while ((inputString = scanIn.nextLine()).isEmpty() && !acceptNull) {
			System.out.println("!!! You must provide a value.");
			System.out.println(message);
		}

		return inputString;
	}
}
