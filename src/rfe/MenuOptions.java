package rfe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
