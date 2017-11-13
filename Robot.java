import Utilities.CircularlyLinkedList;
import Utilities.Position;

public class Robot {
	Position pos;										// the position of robot
	Position predictPos;								// the position of robot if it move
	CircularlyLinkedList<DirectionState> direction;		// the direction of robot
	private static Robot instance;
	
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
	private Robot() {
		initDirection();
	}
	public Robot getInstance(int x, int y, String dir) {
		if (instance == null) {
			instance = new Robot();
		}
		instance.pos.setX(x);
		instance.pos.setY(y);
		instance.changeDirection(dir);
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
		pos = predictPos;
	}
	
	public String toString() {
		return pos.getX() + ',' + 
				pos.getY() + ',' +
				direction.first().getDirStr();
	}
}
