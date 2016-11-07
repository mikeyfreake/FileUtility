package io.github.mikeyfreake.fileutility.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchCommand {

	public List<File> textSearch(String searchString, String directory) throws IOException {
		System.out.println("Searching for '" + searchString + "' in directory '" + directory + "'");

		List<File> fileList = new ArrayList<File>();

		File folder = new File(directory);

		// Validate that the directory exists.
		if (folder.exists() && folder.isDirectory()) {

			File[] files = folder.listFiles();
			System.out.println("\tNumber of files to search: " + files.length);
			
			for (File file : files) {
				if (file.isDirectory()) {
					continue;
				}
				
				final BufferedReader reader = new BufferedReader(new FileReader(file));
				final StringBuilder contents = new StringBuilder();
				while (reader.ready()) {
					contents.append(reader.readLine());
				}
				reader.close();
				final String stringContents = contents.toString();
				if (stringContents.contains(searchString)) {
					fileList.add(file);
				}
			}
		}

		return fileList;
	}
}
