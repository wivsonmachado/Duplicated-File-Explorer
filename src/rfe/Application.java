package rfe;


import java.io.File;
import java.util.List;

public class Application {
	
	public static void main(String[] args) {
		
		File file;
		if(args.length == 0) {
			file = new File("").getAbsoluteFile();
		}else {
			file = new File(args[0]);			
		}
		DirectoryExplorer explorer = new DirectoryExplorer(file);
		List<SystemFile> repeatedFiles = explorer.compareFiles();
		MenuOptions menu =  new MenuOptions(repeatedFiles, file);
		menu.openMenu();
	}

}
