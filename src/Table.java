package src;

public class Table {
	Robot robot;
	
	public static void main(String[] args) {
		description();
		playGame();
	}
	
	public static void description() {
		System.out.println("This is a Robot game");
		System.out.println("Your can use a list of command to control the robot:");
		System.out.println("PLACE X,Y,F: place the robot into table 5x5 with ");
		System.out.println("The origin (0,0) can be considered to be the SOUTH WEST most corner");
		System.out.println("	where X: position of robot in the horizontal axis");
		System.out.println("	where Y: position of robot in the vertical axis");
		System.out.println("	where F: direction of robot (NORTH, WEST, SOUTH, EAST)");
		System.out.println("LEFT  : turn left robot");
		System.out.println("RIGTH : turn right robot");
		System.out.println("MOVE  : move robot forward 1 step");
		System.out.println("REPORT: show the position and direction of the robot");
		System.out.println("Any actions make robot fall will not be accepted - do nothing");
		System.out.println("Any actions will be ignored if you have not placed a robot");
	}
	
	public static void playGame() {
		
	}
	
	public static boolean checkValidMove() {
		return checkValidBoundary();
	}
	
	public static boolean checkValidBoundary() {
		return true;
	}
}
