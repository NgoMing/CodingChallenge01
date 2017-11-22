package main.java;

public class CommandProcessor {
	final private String DEFAULT_DELIMITERS = " ";
	
	private String delimiters;
	
	public CommandProcessor () {
		setDelimiters(DEFAULT_DELIMITERS);
	}
	
	public CommandProcessor (String delimiters) {
		setDelimiters(delimiters);
	}
	
	public void setDelimiters(String delimiters) {
		this.delimiters = delimiters;
	}
	
	public String getDelimiters() {
		return delimiters;
	}
	
	public String[] paserLine(String cmd) {
		String[] result = cmd.split(delimiters);
		return result;
	}
}
