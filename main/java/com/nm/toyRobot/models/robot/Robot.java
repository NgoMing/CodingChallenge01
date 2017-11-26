package com.nm.toyRobot.models.robot;
import com.nm.toyRobot.utilities.*;

public class Robot {
	// the position of robot
	Position pos;											
	// the position of robot if it move
	Position predictPos;									
	// the direction of robot
	CircularlyLinkedList<DirectionState> direction;	
	
	// access methods
	public Position getPos() { return pos; }
	public Position getPredictPos() { return predictPos; }
	public DirectionState getCurrentDirection() { return direction.first(); }
	
	private void initDirection() {
		direction = new CircularlyLinkedList<>();
		direction.addFirst(new DirectionState("EAST",  new Position( 1,  0)));
		direction.addFirst(new DirectionState("SOUTH", new Position( 0, -1)));
		direction.addFirst(new DirectionState("WEST",  new Position(-1,  0)));
		direction.addFirst(new DirectionState("NORTH", new Position( 0,  1)));
	}
	
	// change the direction of robot to desired direction - dir
	private void changeDirection(String dir) {
		// null check
		if (direction == null) return;
		
		for (int i = 0; i < direction.size(); i++){
			if (direction.first().getDirStr().equals(dir))
				return;
			direction.rotate(1);
		}
	}
	
	//constructors
	public Robot() { 
		initDirection();
		pos = new Position();
		predictPos = new Position();
		changeDirection("NORTH");
	}
	
	public Robot(int x, int y, String dir) {
		initDirection();
		pos = new Position(x, y);
		predictPos = pos;
		changeDirection(dir);
	}
	
	public Robot(Position pos, String dir) {
		initDirection();
		this.pos = pos;
		this.predictPos = this.pos;
		changeDirection(dir);
	}
	
	
	// active methods
	public void place(int x, int y, String dir) {
		pos.setX(x);
		pos.setY(y);
		changeDirection(dir);
	}
	
	public void place(Position pos, String dir) {
		this.pos = pos;
		changeDirection(dir);
	}
	
	public void left() {
		direction.rotate(1);
	}
	
	public void right() {
		direction.rotate(-1);
	}
	
	public void move() {
		predictPos = pos.add(direction.first().getPos());
	}
	
	public void updatePos() {
		pos = predictPos;
	}
	
	public String toString() {
		return  Integer.toString(pos.getX()) + ',' + 
				Integer.toString(pos.getY()) + ',' +
				direction.first().getDirStr();
	}
}
