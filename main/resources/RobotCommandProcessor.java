package main.resources;

import main.java.CommandProcessor;

public class RobotCommandProcessor {
	CommandProcessor cmd;
	
	public RobotCommandProcessor() {
		cmd = new CommandProcessor("[, ]+");
	}
	
	public void process(String robotCmd) {
		
		String[] parser = cmd.parserLine(robotCmd);
		int size = parser.length;
		
		// process robot command line
		// PLACE X,Y,F
		if ((size == 4) && "PLACE".equals(parser[0])) {
			
		}
		// RIGHT
		else if ((size == 1) && "RIGHT".equals(parser[0])) {
			
		}
		// LEFT
		else if ((size == 1) && "LEFT".equals(parser[0])) {
			
		}
		// MOVE
		else if ((size == 1) && "MOVE".equals(parser[0])) {
			
		}
		// REPORT
		else if ((size == 1) && "REPORT".equals(parser[0])) {
			
		}	
	}
}
