package tp.pr4.testprofesor.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr4.Directions;
import tp.pr4.commands.Command;
import tp.pr4.commands.GoCommand;
import tp.pr4.commands.QuitCommand;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;
import tp.pr4.testprofesor.MockGame;
import tp.pr4.testprofesor.MockMap;

public class QuitCommandTest {
	private MockGame game;
	private Command testCommand;
	
	@Before
	public void setUp() throws Exception {
		game = new MockGame(new MockMap(null));	
		testCommand = new QuitCommand();
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("qit", game);
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("salir", game);
			testCommand = testCommand.parse("quit", game);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}

	@Test
	public void testExecute() {
		try {
			testCommand = testCommand.parse("quit", game);
			testCommand.execute();
			assertTrue("ERROR: The command does not request to finish the game", game.isQuitRequested());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
			fail("ERROR: quit command does not throw a CommandExecutionExcception");
		}
	}

	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word QUIT", help.contains("QUIT"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word SALIR", help.contains("SALIR"));
	}

}
