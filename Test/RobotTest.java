package Test;
import static org.junit.Assert.*;

import org.junit.Test;

import Utilities.Position;
import src.Robot;

public class RobotTest {

	@Test
	public void shouldConstructsWithoutParamCorrectly() {
		Robot robot = Robot.getInstance();
		assertEquals(new Position(), robot.getPos());
		assertTrue(robot.getCurrentDirection().getDirStr().equals("NORTH"));
	}
	
	@Test
	public void shouldConstructsWithParamsCorrectly() {
		Robot robot = Robot.getInstance(2, 3, "WEST");
		assertEquals(new Position(2, 3), robot.getPos());
		assertTrue(robot.getCurrentDirection().getDirStr().equals("WEST"));
	}

	@Test
	public void shouldSingletonPatternWorkCorrectly() {
		Robot robot1 = Robot.getInstance();
		Robot robot2 = Robot.getInstance();
		assertEquals(robot1, robot2);
	}
	
	@Test
	public void shouldTurnLeftCorrectly() {
		Robot robot = Robot.getInstance();
		robot.left();
		assertTrue(robot.getCurrentDirection().getDirStr().equals("WEST"));
	}
	
	@Test
	public void shouldTurnRightCorrectly() {
		Robot robot = Robot.getInstance();
		robot.right();
		assertTrue(robot.getCurrentDirection().getDirStr().equals("EAST"));
	}
	
	@Test
	public void shouldMoveCorrectly() {
		Robot robot = Robot.getInstance();
		robot.move();
		assertEquals(new Position(0, 1), robot.getPos());
	}
	
//	@Test
//	public void shouldDoNothingIfThisMoveWillMakeRobotFall() {
//		Robot robot = Robot.getInstance(0, 0, "WEST");
//		robot.move();
//		assertEquals(new Position(0, 0), robot.getPos());
//	}
	
	@Test
	public void shouldToStringCorrectly() {
		Robot robot = Robot.getInstance();
		String outputString = robot.toString();
		String expectedString = "0,0,NORTH";
		assertTrue(outputString.equals(expectedString));
	}
}
