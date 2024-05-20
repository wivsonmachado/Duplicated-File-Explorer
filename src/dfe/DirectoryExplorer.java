package dfe;


import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DirectoryExplorer {

	
	private List<SystemFile> filesList = new ArrayList<>();
	
	private File rootFile;
	
	private int countFiles = 0;
	
	private int count = 0;
	
	public DirectoryExplorer() {
	}
	
	public DirectoryExplorer(File rootFile) {
		this.rootFile = rootFile;
		countFiles(rootFile);
		directoryExplorer(rootFile);
	}

	public List<SystemFile> getFilesList() {
		return filesList;
	}
	
	public int getCountFiles() {
		return countFiles;
	}
	
	public int getCount() {
		return this.count;
	}
			
	public void countFiles(File file) {
		File[] listFiles = file.listFiles();
		
		if(listFiles == null) {
			return;
		}
		
		for(File f : listFiles) {
			if(f.isFile() && !Files.isSymbolicLink(f.toPath())) {
				countFiles++;
			}else {
				countFiles(f);					
			}
		}
		
	}
	
	public void directoryExplorer(File file) {
		File[] listFiles = file.listFiles();
		
		if(listFiles == null) {
			return;
		}
		
		for(File f : listFiles) {
			if(f.isFile()) {
				SystemFile myFile = new SystemFile(f);
				filesList.add(myFile);
				count++;
				System.out.print("Searching files...    "+getCount()+"/"+getCountFiles()+" files\r");
			}else {
				directoryExplorer(f);																
			}
		}
	}
	
	public List<SystemFile> compareFiles() {
		
		System.out.print("Comparing files...                                               \r");
			
		List<SystemFile> allRepeatedFiles = filesList.stream()
					.filter(i -> Collections.frequency(filesList, i) > 1)
					.collect(Collectors.toList());
		
		List<SystemFile> distinctRepeatedFiles = allRepeatedFiles.stream()
		             .distinct()
		             .collect(Collectors.toList());
		
		for(SystemFile file: distinctRepeatedFiles) {
			allRepeatedFiles.removeIf(i -> file.getFile().toString().equals(i.getFile().toString()));
		}
		
		System.out.print(getCountFiles()+" files Analyzed    "+"\n\n"+"Amount duplicated files at "+rootFile.getAbsoluteFile()+": "+allRepeatedFiles.size()+" files\n"); 

		return allRepeatedFiles;
	}
	
}
