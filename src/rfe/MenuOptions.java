package rfe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Scanner;

public class MenuOptions {
	
	List<SystemFile> repeatedFiles;
	File rootFile;
	
	public MenuOptions(List<SystemFile> repeatedFiles, File rootFile) {
		this.repeatedFiles = repeatedFiles;
		this.rootFile = rootFile.getAbsoluteFile();
	}
	
	public MenuOptions() {
	}
	
	public void openMenu() {
		
		System.out.println("\n--- What do you want do? ---");
		System.out.println("\n");
		System.out.println("[0] - Exit Program");
		System.out.println("[1] - Create a log file");
		System.out.println("[2] - Move repeated files to unique directory");
//		System.out.println("[3] - Delete repeated files");
		
		choiceReceiver();
	}
	
	private void choiceReceiver() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Choose a number: ");
		int option = sc.nextInt();
		
		switch(option){
			case 0:
				System.exit(0);
				sc.close();
				break;
			case 1:
				createLog(repeatedFiles);
				sc.close();
				break;
			case 2:
				moveFile(repeatedFiles);
				sc.close();
				break;
		}
	}

	private void moveFile(List<SystemFile> repeatedFiles) {
		Scanner mv = new Scanner(System.in);
		System.out.print("Do you want keep directory default to move files? ["+rootFile+"] y/N: ");
		String answer = mv.next();
		if(answer.toUpperCase().equals("n".toUpperCase())) {
			System.out.print("Inform new path directory: ");
			String newDirectory = mv.next();
			File file = new File(newDirectory+FileSystems.getDefault().getSeparator()+"movedRepeatedFiles");			
			boolean wasCreated = file.mkdir();
			if(wasCreated) {
				repeatedFiles.forEach(i -> {
					try {
						File moved = new File(file+FileSystems.getDefault().getSeparator()+i.getFile().getName());
						Files.move(i.getFile().toPath(), moved.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				mv.close();
				System.out.println("Successfully move all file to the directory "+file);
			}
			return;
		}
		
		File file = new File(rootFile+FileSystems.getDefault().getSeparator()+"movedRepeatedFiles");			
		boolean wasCreated = file.mkdir();
		if(wasCreated) {
			repeatedFiles.forEach(i -> {
				try {
					File moved = new File(file+FileSystems.getDefault().getSeparator()+i.getFile().getName());
					Files.move(i.getFile().toPath(), moved.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			mv.close();
			System.out.println("Successfully move all file to the directory "+file);
		}
	}

	private void createLog(List<SystemFile> repeatedFiles) {
		
		 try {
			  FileWriter writer = new FileWriter(new File(rootFile+"/log.txt"));
				 if(repeatedFiles.isEmpty()) {
					 writer.write("Not found repeated Files.");
				 }
		      repeatedFiles.forEach(i -> {
				try {
					writer.append(i.getFile().toString()+"\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		      writer.close();
		      System.out.println("Successfully wrote to the log file at "+rootFile+"/log.txt");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }				
	}

}
