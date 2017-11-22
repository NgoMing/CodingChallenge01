package test.resources;
import static org.junit.Assert.*;

import org.junit.Test;

import main.java.Position;
import main.resources.Robot;

public class RobotTest {

	@Test
	public void shouldConstructsWithoutParamCorrectly() {
		Robot robot = new Robot();
		assertEquals(new Position(), robot.getPos());
		assertTrue(robot.getCurrentDirection().getDirStr().equals("NORTH"));
	}
	
	@Test
	public void shouldConstructsWithThreeParamsCorrectly() {
		Robot robot = new Robot(2, 3, "WEST");
		assertEquals(new Position(2, 3), robot.getPos());
		assertTrue(robot.getCurrentDirection().getDirStr().equals("WEST"));
	}
	
	@Test
	public void shouldConstructsWithTwoParamsCorrectly() {
		Position pos = new Position(2, 3);
		Robot robot = new Robot(pos, "WEST");
		assertEquals(pos, robot.getPos());
		assertTrue(robot.getCurrentDirection().getDirStr().equals("WEST"));
	}
	
	@Test
	public void shouldTurnLeftCorrectly() {
		Robot robot = new Robot();
		robot.left();
		assertTrue(robot.getCurrentDirection().getDirStr().equals("WEST"));
	}
	
	@Test
	public void shouldTurnRightCorrectly() {
		Robot robot = new Robot();
		robot.right();
		assertTrue(robot.getCurrentDirection().getDirStr().equals("EAST"));
	}
	
	@Test
	public void shouldMoveCorrectly() {
		Robot robot = new Robot();
		robot.move();
		assertEquals(new Position(0, 1), robot.getPredictPos());
	}
	
	@Test
	public void shouldToStringCorrectly() {
		Robot robot = new Robot();
		String outputString = robot.toString();
		String expectedString = "0,0,NORTH";
		assertTrue(outputString.equals(expectedString));
	}
}
