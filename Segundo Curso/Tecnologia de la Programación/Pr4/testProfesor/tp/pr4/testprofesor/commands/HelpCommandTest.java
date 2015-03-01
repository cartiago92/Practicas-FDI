package tp.pr4.testprofesor.commands;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tp.pr4.commands.Command;
import tp.pr4.commands.HelpCommand;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;
import tp.pr4.testprofesor.MockGame;
import tp.pr4.testprofesor.MockMap;


public class HelpCommandTest {
	private MockGame game;
	private Command testCommand;
	
	@Before
	public void setUp() throws Exception {
		game = new MockGame(new MockMap(null));	
		testCommand = new HelpCommand();
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("hel", game);
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("help", game);
			testCommand = testCommand.parse("ayuda", game);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}

	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word HELP", help.contains("HELP"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word AYUDA", help.contains("AYUDA"));
	}

}
