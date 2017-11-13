package src;
import Utilities.Position;

/*
 * This class includes the direction and 
 * vector of the direction of Robot
 */
public class DirectionState {
	String dirStr;		// direction of Robot in String type :  NORTH,   WEST,   SOUTH,  and  EAST
	Position dirPos;	// position of the direction         : (0, 1), (-1, 0), (0, -1), and (1, 0)
	
	// constructor
	public DirectionState(String dirStr, Position dirPos) {
		this.dirStr = dirStr;
		this.dirPos = dirPos;
	}
	
	// access methods
	public String getDirStr() { return dirStr; }
	public Position getPos() { return dirPos; }
}
