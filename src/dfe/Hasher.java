package dfe;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
	
	public static String generateHash(File file) {	
		InputStream inputStream;
		StringBuilder hash = new StringBuilder();
		byte[] bytes = new byte[4096*4096];

		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			inputStream = new FileInputStream(file);
			
			int read = 0;
			while((read = inputStream.read(bytes)) > 1) {
			  md.update(bytes, 0, read);
			}
						
			inputStream.close();
			
			byte[] res = md.digest();
			
			BigInteger bigInt = new BigInteger(1, res);
			
			hash.append(bigInt.toString());
			

		}catch(FileNotFoundException e) {
			//System.out.println("Error: File not found "+ e.getMessage());
			return "Error: File not found "+ e.getMessage();
		}catch (IOException e) {
			//System.out.println("Error: Can not read file "+ e.getMessage());
			return "Error: Can not read file "+ e.getMessage();
		}catch(NoSuchAlgorithmException ex) {
			//System.out.println("Error: Encripty failed "+ ex.getMessage());
			return "Error: Encripty failed "+ ex.getMessage();
		}
		return hash.toString();
	}

	

}
