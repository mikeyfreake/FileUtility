package io.github.mikeyfreake.fileutility.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class MoveCommand {

	public boolean doMove(List<File> fileList, String path) throws IOException {
		System.out.println("Moving " + fileList.size() + " files to " + path);

		for (File file : fileList) {
			Path target = new File(path + "\\" + file.getName()).toPath();
			Files.move(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("\tMoved \n\t\t" + file.getCanonicalPath() + "\n\tto\n\t\t" + target.toString());
		}
		
		return true;
	}
	
	public boolean doCopy(List<File> fileList, String path) throws IOException {
		System.out.println("Copying " + fileList.size() + " files to " + path);

		for (File file : fileList) {
			Path target = new File(path + "\\" + file.getName()).toPath();
			Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("\tCopied \n\t\t" + file.getCanonicalPath() + "\n\tto\n\t\t" + target.toString());
		}
		
		return true;
	}
}
