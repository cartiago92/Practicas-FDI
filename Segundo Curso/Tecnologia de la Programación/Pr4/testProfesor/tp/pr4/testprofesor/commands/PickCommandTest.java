package tp.pr4.testprofesor.commands;


import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tp.pr4.testprofesor.MockItem;
import tp.pr4.items.Item;
import tp.pr4.Map;
import tp.pr4.Player;
import tp.pr4.Room;
import tp.pr4.commands.Command;
import tp.pr4.commands.PickCommand;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;
import tp.pr4.testprofesor.MockGame;
import tp.pr4.testprofesor.MockMap;

public class PickCommandTest {

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
		testCommand = new PickCommand();
		playerTest = game.getPlayer();
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("pik item", game);
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseNoParameter() {
		try {
			testCommand = testCommand.parse("pick", game);
			fail("ERROR: parse does not throw the exception with a wrong command");

		} catch (WrongCommandFormatException e) {
		}
	}

	
	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("pick "+itemId, game);
			testCommand = testCommand.parse("coger "+itemId, game);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}
	
	@Test
	public void testExecuteNoItemInRoom(){
		try {
			testCommand = testCommand.parse("pick "+itemId, game);
			testCommand.execute();
			fail("ERROR: execute does not throw an exception when trying to pick an item that does not exist in the room");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
		}				
	}
	
	@Test
	public void testExecuteItemAlreadyInInventory(){
		try {
			sourceRoom.addItem(itemTest);
			playerTest.addItem(new MockItem(itemId, "desc"));
			testCommand = testCommand.parse("pick "+itemId, game);
			testCommand.execute();
			fail("ERROR: execute does not throw an exception when trying to pick an item that already exists in the inventory");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
		}				
	}
	
	@Test
	public void testExecuteCorrect(){
		try {
			sourceRoom.addItem(itemTest);
			testCommand = testCommand.parse("pick "+itemId, game);
			testCommand.execute();
			assertNotNull("ERROR: Player inventory does not contain the item", playerTest.getItem(itemId));
			assertFalse("ERROR: Room already contains the item", sourceRoom.existsItem(itemId));

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
			fail("ERROR: execute throws an exception when trying to pick an item but it is correct");
		}				
	}
	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word PICK", help.contains("PICK"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word COGER", help.contains("COGER"));
	}

}
