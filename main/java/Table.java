package main.java;
import java.io.*;

public class Table {
	final static int DEFAULT_HEIGHT = 5;
	final static int DEFAULT_WIDTH = 5;
	final static String currentWorkingDir = System.getProperty("user.dir");
	final static String testDir = "\\src\\TestCases\\";
	
	private int height;
	private int width;
	private Robot robot;
	private CommandProcessor cmd;
	
	// constructors
	public Table () {
		setSize(DEFAULT_HEIGHT, DEFAULT_WIDTH);
		cmd = new CommandProcessor("[, ]+");
	}
	
	public Table(int heigth, int width) {
		setSize(heigth, width);
		cmd = new CommandProcessor("[, ]+");
	}
	
	// access methods
	public int getHeight() { return height; }
	public int getWidth() { return width; }
	
	// update methods
	public void setSize(int height, int width) {
		setHeight(height);
		setWidth(width);
	}
	
	public void setHeight(int height) { this.height = height; }
	public void setWidth(int width) { this.width = width; }
	
	// process command line
	public void processCommandLine(String robotCmd) {
		
		String[] parser = cmd.parserLine(robotCmd);
		int size = parser.length;
		
		// process robot command line
		// PLACE X,Y,F
		if ((size == 4) && "PLACE".equals(parser[0])) {
			Position placingPos = new Position(Integer.parseInt(parser[1]), Integer.parseInt(parser[2]));
			if (checkValidMove(placingPos)) {
				robot = new Robot(placingPos, parser[3]);
			}
		}
		// RIGHT
		else if ((size == 1) && "RIGHT".equals(parser[0])) {
			if (robot != null)
				robot.right();
		}
		// LEFT
		else if ((size == 1) && "LEFT".equals(parser[0])) {
			if (robot != null)
				robot.left();
		}
		// MOVE
		else if ((size == 1) && "MOVE".equals(parser[0])) {
			if (robot != null) {
				robot.move();
				if (checkValidMove(robot.getPredictPos()))
					robot.updatePos();
			}
		}
		// REPORT
		else if ((size == 1) && "REPORT".equals(parser[0])) {
			System.out.println(robot);
		}	
	}
	
	// process lines in a file
	public void processCommandLinesInFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(currentWorkingDir + testDir + fileName));
		String line = br.readLine();
		
		while (line != null) {
			line = line.toUpperCase();
			if (line.contains("EXPECTED_OUTPUT")) {
				String[] expectedOutput = line.split(" ");
				if (robot == null) {
					if (expectedOutput[1].equals("NULL")) {
						System.out.println(fileName + " PASS");
					}
				}
				else if (expectedOutput[1].equals(robot.toString())) {
					System.out.println(fileName + " PASS");
				}
				else{
					System.out.println(fileName + " ERROR"); 
					System.out.println("\texpected output: " + expectedOutput[1]);
					System.out.println("\t    real output: " + robot);
				}
			}
			else {
				processCommandLine(line);
			}
			line = br.readLine();
		}

		br.close();
	}
	
	public void processCommandLinesInAllFiles(String folderName) throws IOException {
		File folder = new File(currentWorkingDir + testDir + folderName);
		File[] listOfFile = folder.listFiles();
		for (File file : listOfFile) {
			if (file.isDirectory())
				processCommandLinesInAllFiles(folderName + "\\" + file.getName());
			else if (file.isFile())
				processCommandLinesInFile(folderName + "\\" + file.getName());
		}
	}
	
	public void processCommand() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while (true) {
			str = br.readLine();
			if (str.contains(".*")) {
				processCommandLinesInAllFiles(str.substring(0, str.indexOf(".*")));
			}
			else if (str.contains(".set")) {
				processCommandLinesInFile(str);
			}
			else {
				processCommandLine(str);
			}
		}
	}
	
	public  boolean checkValidMove(Position pos) {
		return checkValidBoundary(pos);
	}
	
	public  boolean checkValidBoundary(Position pos) {
		if (pos == null) return false;
		
		// check horizontal axis
		if ((pos.getX() < 0) || (pos.getX() > width))
			return false;
		
		// check vertical axis
		if ((pos.getY() < 0) || (pos.getY() > height))
			return false;
				
		return true;
	}
}
