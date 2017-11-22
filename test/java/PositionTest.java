package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.Position;

public class PositionTest {

	@Test
	public void shouldConstructWithNoParamCorrectly() {
		Position pos = new Position();
		assertEquals(0, pos.getX());
		assertEquals(0, pos.getY());
	}

	@Test
	public void shouldConstructWithParamsCorrectly() {
		Position pos = new Position(2, 3);
		assertEquals(2, pos.getX());
		assertEquals(3, pos.getY());
	}
	
	@Test
	public void shouldAddCorrectly() {
		Position pos1 = new Position(2, 3);
		Position pos2 = new Position(-5, 7);
		Position posRes = pos1.add(pos2);
		assertEquals(new Position(-3, 10), posRes);
	}
}
