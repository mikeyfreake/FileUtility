package com.precisiondrilling.fu.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchCommand {

	public List<File> regexSearch(String regex, String directory) throws IOException {
		System.out.println("Searching for '" + regex + "' in directory '" + directory + "'");

		List<File> fileList = new ArrayList<File>();

		File folder = new File(directory);

		// Validate that the directory exists.
		if (folder.exists() && folder.isDirectory()) {

			for (File file : folder.listFiles()) {
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
				if (stringContents.toString().contains(regex)) {
					fileList.add(file);
				}
			}
		}

		return fileList;
	}
}
