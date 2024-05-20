package dfe;


import java.io.File;

public class SystemFile {
	
	private File file;
	private String hash;
	
	public SystemFile(File file) {
		this.file = file;
		this.hash = Hasher.generateHash(file);
	}
	
	
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public String getHash() {
		return hash;
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemFile other = (SystemFile) obj;
		if (hash == null) {
			if (other.hash != null)
				return false;
		} else if (!hash.equals(other.hash))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return getFile().getAbsolutePath() + " - " + getHash();
	}



	 
}
