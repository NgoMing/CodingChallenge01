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
	public void shouldPaserInUpperCaseCorrectly() {
		CommandProcessor cmd = new CommandProcessor("[- ]+");
		String str = "Java-Programming Language";
		String[] strExpected = {"JAVA", "PROGRAMMING", "LANGUAGE"};
		
		assertArrayEquals(strExpected, cmd.parserLine(str));
	}
	
	@Test
	public void shouldPaserInLowerCaseCorrectly() {
		CommandProcessor cmd = new CommandProcessor("[- ]+");
		String str = "Java-Programming Language";
		String[] strExpected = {"java", "programming", "language"};
		
		assertArrayEquals(strExpected, cmd.parserLine(str, false));
	}
}
