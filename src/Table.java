package src;
import java.io.*;

import Utilities.Position;

public class Table {
	final static int MAX_WIDTH = 5;
	final static int MAX_HEIGHT = 5;
	final static String currentWorkingDir = System.getProperty("user.dir");
	final static String testDir = "\\src\\TestCases\\";
	static Robot robot;
	
	public static void main(String[] args) {
		description();
		try {
			playGame();
		} catch (IOException e) {
			System.out.println("IO ERROR: check your io-connection");
		}
	}
	
	/*
	 * description of robot simulator
	 */
	public static void description() {
		System.out.println("This is a Robot game");
		System.out.println("Your can use a list of command to control the robot:");
		System.out.println("PLACE X,Y,F: place the robot into table " + MAX_WIDTH + "x" + MAX_HEIGHT +" with ");
		System.out.println("The origin (0,0) can be considered to be the SOUTH WEST most corner");
		System.out.println("	where X: position of robot in the horizontal axis");
		System.out.println("	where Y: position of robot in the vertical axis");
		System.out.println("	where F: direction of robot (NORTH, WEST, SOUTH, EAST)");
		System.out.println("LEFT  : turn left robot");
		System.out.println("RIGTH : turn right robot");
		System.out.println("MOVE  : move robot forward 1 step");
		System.out.println("REPORT: show the position and direction of the robot");
		System.out.println("EXIT  : stop playing");
		System.out.println("Any actions make robot fall will not be accepted - do nothing");
		System.out.println("Any actions will be ignored if you have not placed a robot");
	}
	
	/*
	 * main function includes all action in the simulator
	 */
	public static void playGame() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		boolean gameOver = false;
		while (!gameOver) {
			str = br.readLine();
			if (str.contains(".*")) {
				processAllFileCommand(str.substring(0, str.indexOf(".*")));
			}
			else if (str.contains(".set")) {
				processFileCommand(str);
			}
			else {
				if (!processCommandLine(str))
					break;
			}
		}
		System.out.println("See you again!");
		try {
			System.in.read();
		} catch (IOException e) { }
	}
	
	/*
	 * check whether robot will be valid or not, considering some constraints
	 * @return true if the position is valid
	 * 		   false if the position is invalid
	 */
	public static boolean checkValidMove(Position pos) {
		return checkValidBoundary(pos);
	}
	
	/*
	 * check whether robot will be out of the table
	 * @return true if the position is valid
	 * 		   false if the position is invalid
	 */
	public static boolean checkValidBoundary(Position pos) {
		if (pos == null) return false;
		
		// check horizontal axis
		if ((pos.getX() < 0) || (pos.getX() > MAX_WIDTH))
			return false;
		
		// check vertical axis
		if ((pos.getY() < 0) || (pos.getY() > MAX_HEIGHT))
			return false;
				
		return true;
	}
	
	/*
	 * read and process all commands in all files in folder
	 * @param fileFolder: the name of desired folder
	 * @throws IOException from processFileCommand functions
	 */
	public static void processAllFileCommand(String folderName) throws IOException {
		File folder = new File(currentWorkingDir + testDir + folderName);
		File[] listOfFile = folder.listFiles();
		for (File file : listOfFile) {
			if (file.isDirectory())
				processAllFileCommand(folderName + "\\" + file.getName());
			else if (file.isFile())
				processFileCommand(folderName + "\\" + file.getName());
		}
	}
	
	/*
	 * read and process all commands in a file
	 * @param fileName: the name of file where users want to process commands in
	 * @throws IOException when cannot read line in file  
	 */
	public static void processFileCommand(String fileName) throws IOException {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(currentWorkingDir + testDir + fileName));
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND: " + currentWorkingDir + testDir + fileName + " is not existed");
			return;
		}
		
		String line;
		try {
			line = br.readLine();
		} 
		catch (IOException e) {
			System.out.println("IO ERROR: check the input-output connection");
			throw new IOException();
		} 
		finally {
			br.close();
		}
		
		while (line != null) {
			// process if this file is a test file
			if (line.toUpperCase().contains("EXPECTED_OUTPUT")) {
				String[] expectedOutput = line.split(" ");
				if (robot == null) {
					if (expectedOutput[1].toUpperCase().equals("NULL")) {
						System.out.println(fileName + " PASS");
					}
				}
				else if (expectedOutput[1].toUpperCase().equals(robot.toString())) {
					System.out.println(fileName + " PASS");
				}
				else{
					System.out.println(fileName + " ERROR"); 
					System.out.println("\texpected output: " + expectedOutput[1].toUpperCase());
					System.out.println("\t    real output: " + robot);
				}
			}
			// process if this is an user file
			else if (!processCommandLine(line))
				break;
			
			try {
				line = br.readLine();
			} 
			catch (IOException e) {
				System.out.println("IO ERROR: check the input-output connection");
				throw new IOException();
			} 
			finally {
				br.close();
			}
		}

		br.close();
	}
	
	/*
	 * @return: false if do not want to play anymore
	 * 			true if keep playing
	 */
	public static boolean processCommandLine(String cmd) {
		String[] parser;
		String[] robotState;
		parser = cmd.split(" ");
		
		// PLACE X,Y,F command
		if ((parser.length == 2) && ("PLACE".equals(parser[0].toUpperCase()))) {
			robotState = parser[1].split(",");
			// check enough information for X,Y,F
			if (robotState.length == 3) {
				Position placingPos = new Position(Integer.parseInt(robotState[0]), Integer.parseInt(robotState[1]));
				if (checkValidMove(placingPos)) {
					robot = Robot.getInstance();
					robot.place(Integer.parseInt(robotState[0]), 
							Integer.parseInt(robotState[1]),
							robotState[2]);
				}
			}
		} else if (parser.length == 1) {
			switch (cmd.toUpperCase()) {
			case "LEFT":
				if (robot != null)
					robot.left();
				break;
			case "RIGHT": 
				if (robot != null)
					robot.right();
				break;
			case "MOVE":
				if (robot != null) {
					robot.move();
					if (checkValidMove(robot.getPredictPos()))
						robot.updatePos();
				}
				break;
			case "REPORT":
				System.out.println(robot);
				break;
			case "EXIT":
				return false;
			default:
				System.out.println("Unknown command");
				break;
			}
		}
		else {
			System.out.println("Unknown command");
		}
		
		return true;
	}
}
