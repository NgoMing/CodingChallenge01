package Utilities;

/*
 * Position class is used to indicate position of Robot
 */
public class Position {
	int x;
	int y;
	
	// constructors
	public Position() {
		setX(0);
		setY(0);
	}
	
	public Position(int x, int y) {
		setX(x);
		setY(y);
	}
	
	// access methods
	public int getX() { return x; }
	public int getY() { return y; }
	
	// update methods
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public Position add(Position p) {
		Position result = new Position();
		
		result.setX(this.getX() + p.getX());
		result.setY(this.getY() + p.getY());
		
		return result;
	}
}
