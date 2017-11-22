package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.CommandProcessor;

public class CommandProcessorTest {

	@Test
	public void shouldConstructorWithoutParamCorrectly() {
		CommandProcessor cmd = new CommandProcessor();
		assertTrue(cmd.getDelimiters().equals(" "));
	}
	
	@Test
	public void shouldConstructorWithParamCorrectly() {
		CommandProcessor cmd = new CommandProcessor(",");
		assertTrue(cmd.getDelimiters().equals(","));
	}

	@Test
	public void shouldPaserCorrectly() {
		CommandProcessor cmd = new CommandProcessor();
		String str = "Programming Language";
		String[] strExpected = {"Programming", "Language"};
		
		assertArrayEquals(strExpected, cmd.paserLine(str));
	}
}
