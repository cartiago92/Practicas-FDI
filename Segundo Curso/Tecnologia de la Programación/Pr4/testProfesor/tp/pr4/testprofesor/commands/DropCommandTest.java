package tp.pr4.testprofesor.commands;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tp.pr4.Map;
import tp.pr4.Player;
import tp.pr4.Room;
import tp.pr4.commands.Command;
import tp.pr4.commands.DropCommand;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;
import tp.pr4.items.Item;
import tp.pr4.testprofesor.MockGame;
import tp.pr4.testprofesor.MockItem;
import tp.pr4.testprofesor.MockMap;

public class DropCommandTest {

	private MockGame game;
	private Room sourceRoom;
	private Map map;
	private String itemId;
	private Player playerTest;
	private Command testCommand;
	private Item itemTest;
	
	@Before
	public void setUp() throws Exception {
		sourceRoom = new Room(false, "Source");
		itemId = "theItemId";
		itemTest = new MockItem(itemId, "itemDescription");
		map = new MockMap(sourceRoom);
		game = new MockGame(map);	
		testCommand = new DropCommand();
		playerTest = game.getPlayer();
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("drrop item", game);
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseNoParameter() {
		try {
			testCommand = testCommand.parse("drop", game);
			fail("ERROR: parse does not throw the exception with a wrong command");

		} catch (WrongCommandFormatException e) {
		}
	}

	
	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("drop "+itemId, game);
			testCommand = testCommand.parse("soltar "+itemId, game);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}
	
	@Test
	public void testExecuteNoItemInInventory(){
		try {
			testCommand = testCommand.parse("drop "+itemId, game);
			testCommand.execute();
			fail("ERROR: execute does not throw an exception when trying to drop an item that does not exist in the player inventory");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
		}				
	}
	
	@Test
	public void testExecuteItemAlreadyInRoom(){
		try {
			sourceRoom.addItem(itemTest);
			playerTest.addItem(new MockItem(itemId, "desc"));
			testCommand = testCommand.parse("drop "+itemId, game);
			testCommand.execute();
			fail("ERROR: execute does not throw an exception when trying to drop an item that already exists in the room");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
		}				
	}
	
	@Test
	public void testExecuteCorrect(){
		try {
			playerTest.addItem(itemTest);
			testCommand = testCommand.parse("drop "+itemId, game);
			testCommand.execute();
			assertTrue("ERROR: Room does not contain the item", sourceRoom.existsItem(itemId));
			assertNull("ERROR: Player inventory already contains", playerTest.getItem(itemId));

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
		assertTrue("ERROR: getHelp returns a description which does not contain the word DROP", help.contains("DROP"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word SOLTAR", help.contains("SOLTAR"));
	}

}
