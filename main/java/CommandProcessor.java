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
	
	public String[] parserLine(String cmd, boolean upperCase) {
		String cmdInCase;
		if (upperCase)
			cmdInCase = cmd.toUpperCase();
		else
			cmdInCase = cmd.toLowerCase();
		String[] result = cmdInCase.split(delimiters);
		return result;
	}
	
	public String[] parserLine(String cmd) {
		return parserLine(cmd, true);
	}
	
	
}
