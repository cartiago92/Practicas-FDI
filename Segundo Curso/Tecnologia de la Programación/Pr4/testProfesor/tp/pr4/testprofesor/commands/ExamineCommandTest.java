package tp.pr4.testprofesor.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr4.Directions;
import tp.pr4.Map;
import tp.pr4.Room;
import tp.pr4.commands.Command;
import tp.pr4.commands.ExamineCommand;
import tp.pr4.commands.GoCommand;
import tp.pr4.commands.exceptions.WrongCommandFormatException;
import tp.pr4.testprofesor.MockGame;
import tp.pr4.testprofesor.MockMap;

public class ExamineCommandTest {
	private MockGame game;
	private Map map;
	private Room sourceRoom;
	private Command testCommand;
	private String sourceDescription;

	@Before
	public void setUp() throws Exception {
		sourceDescription = "Source";
		sourceRoom = new Room(false, sourceDescription);
		map = new MockMap(sourceRoom);
		game = new MockGame(map);	
		testCommand = new ExamineCommand();
	}
	
	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("exam", game);
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}

	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("examine", game);
			testCommand = testCommand.parse("examinar", game);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}


	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word EXAMINE", help.contains("EXAMINE"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word EXAMINAR", help.contains("EXAMINAR"));
	}

}
