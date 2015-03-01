package tp.pr4.testprofesor.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr4.Directions;
import tp.pr4.Door;
import tp.pr4.Map;
import tp.pr4.Player;
import tp.pr4.Room;
import tp.pr4.commands.Command;
import tp.pr4.commands.LookCommand;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;
import tp.pr4.testprofesor.MockGame;
import tp.pr4.testprofesor.MockItem;
import tp.pr4.testprofesor.MockMap;

public class LookCommandTest {
	
	private MockGame game;
	private Room sourceRoom;
	private Map map;
	private String itemId;
	private Player playerTest;
	private Command testCommand;
	@Before
	public void setUp() throws Exception {
		sourceRoom = new Room(false, "Source");
		itemId = "theItemId";
		map = new MockMap(sourceRoom);
		game = new MockGame(map);	
		testCommand = new LookCommand();
		playerTest = game.getPlayer();
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("lok", game);
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseCorrectNoParameter() {
		try {
			testCommand = testCommand.parse("look", game);
			testCommand = testCommand.parse("mira", game);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}

	
	@Test
	public void testParseCorrectWithParameter() {
		try {
			testCommand = testCommand.parse("look "+itemId, game);
			testCommand = testCommand.parse("mira "+itemId, game);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}
	
	@Test
	public void testExecuteNoItemInInventory(){
		try {
			testCommand = testCommand.parse("look "+itemId, game);
			testCommand.execute();
			fail("ERROR: execute does not throw an exception when trying to look an item that does not exist in the player inventory");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
		}				
	}
	
	@Test
	public void testExecuteCorrect(){
		try {
			playerTest.addItem(new MockItem(itemId, "desc"));
			testCommand = testCommand.parse("look "+itemId, game);
			testCommand.execute();

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
			fail("ERROR: execute throws an exception when when trying to look an item that exists in the player inventory");
		}				
	}

	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word LOOK", help.contains("LOOK"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word MIRA", help.contains("MIRA"));

	}

}
