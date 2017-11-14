package src;
import java.io.*;

import Utilities.Position;

public class Table {
	final static int MAX_WIDTH = 5;
	final static int MAX_HEIGHT = 5;
	static Robot robot;
	
	public static void main(String[] args) throws IOException {
		description();
		playGame();
	}
	
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
	
	public static void playGame() throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		String[] parser;
		String[] robotState;
		Position placingPos;
		boolean gameOver = false;
		while (!gameOver) {
			str = br.readLine();
			parser = str.split(" ");
			
			// PLACE X,Y,F command
			if ((parser.length == 2) && ("PLACE".equals(parser[0].toUpperCase()))) {
				robotState = parser[1].split(",");
				// check enough information for X,Y,F
				if (robotState.length == 3) {
					placingPos = new Position(Integer.parseInt(robotState[0]), Integer.parseInt(robotState[1]));
					if (checkValidMove(placingPos)) {
						robot = Robot.getInstance();
						robot.place(Integer.parseInt(robotState[0]), 
								Integer.parseInt(robotState[1]),
								robotState[2]);
					}
				}
			} else if (parser.length == 1) {
				switch (str.toUpperCase()) {
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
					gameOver = true;
					break;
				default:
					System.out.println("Unknown command");
					break;
				}
			}
		}
		System.out.println("See you again!");
		br.readLine();
	}
	
	public static boolean checkValidMove(Position pos) {
		return checkValidBoundary(pos);
	}
	
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
}
