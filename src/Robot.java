package src;
import Utilities.CircularlyLinkedList;
import Utilities.Position;

public class Robot {
	// the position of robot
	Position pos = new Position();											
	// the position of robot if it move
	Position predictPos;									
	// the direction of robot
	CircularlyLinkedList<DirectionState> direction = new CircularlyLinkedList<>();	
	private static Robot instance;
	
	// access methods for test
	public Position getPos() { return pos; }
	public Position getPredictPos() { return predictPos; }
	public DirectionState getCurrentDirection() { return direction.first(); }
	
	private void initDirection() {
		direction.addFirst(new DirectionState("EAST",  new Position( 1,  0)));
		direction.addFirst(new DirectionState("SOUTH", new Position( 0, -1)));
		direction.addFirst(new DirectionState("WEST",  new Position(-1,  0)));
		direction.addFirst(new DirectionState("NORTH", new Position( 0,  1)));
	}
	
	// change the direction of robot to desired direction - dir
	private void changeDirection(String dir) {
		while (!direction.first().getDirStr().equals(dir))
			direction.rotate(1);
	}
	
	// singleton pattern
	private Robot() { initDirection(); }
	public static Robot getInstance(int x, int y, String dir) {
		if (instance == null) {
			instance = new Robot();
		}
		instance.pos.setX(x);
		instance.pos.setY(y);
		instance.changeDirection(dir);
		return instance;
	}
	public static Robot getInstance() {
		if (instance == null) {
			instance = new Robot();
		}
		// set default values
		instance.pos.setX(0);
		instance.pos.setY(0);
		instance.changeDirection("NORTH");
		return instance;
	}
	
	// active methods
	public void left() {
		direction.rotate(1);
	}
	
	public void right() {
		direction.rotate(-1);
	}
	
	public void move() {
		predictPos = pos.add(direction.first().getPos());
		// check valid move from table class
		// TODO
		pos = predictPos;
	}
	
	public String toString() {
		return  Integer.toString(pos.getX()) + ',' + 
				Integer.toString(pos.getY()) + ',' +
				direction.first().getDirStr();
	}
}
