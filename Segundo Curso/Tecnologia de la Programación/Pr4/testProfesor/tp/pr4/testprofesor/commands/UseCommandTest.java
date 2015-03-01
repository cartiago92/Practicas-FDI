package tp.pr4.testprofesor.commands;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tp.pr4.Directions;
import tp.pr4.Door;
import tp.pr4.items.*;
import tp.pr4.Map;
import tp.pr4.Player;
import tp.pr4.Room;
import tp.pr4.commands.Command;
import tp.pr4.commands.UseCommand;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;
import tp.pr4.testprofesor.MockGame;
import tp.pr4.testprofesor.MockMap;

public class UseCommandTest {

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
		map = new MockMap(sourceRoom);
		game = new MockGame(map);	
		testCommand = new UseCommand();
		playerTest = game.getPlayer();
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("usse item", game);
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseNoParameter() {
		try {
			testCommand = testCommand.parse("use", game);
			fail("ERROR: parse does not throw the exception with a wrong command");

		} catch (WrongCommandFormatException e) {
		}
	}

	
	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("use "+itemId, game);
			testCommand = testCommand.parse("usar "+itemId, game);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}
	
	@Test
	public void testExecuteNoItemInInventory(){
		try {
			testCommand = testCommand.parse("use "+itemId, game);
			testCommand.execute();
			fail("ERROR: execute does not throw an exception when trying to use an item that does not exist in the player inventory");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
		}				
	}
	
	@Test
	public void testExecuteUseValuable(){
		try {
			int points = playerTest.getPoints();
			int inc = 10;
			Valuable testValuable = new Valuable(itemId, "valuable", inc);
			playerTest.addItem(testValuable);
			testCommand = testCommand.parse("use "+itemId, game);
			testCommand.execute();
			assertEquals("ERROR: Player points has not been increased after using Valuable", points+inc, playerTest.getPoints());
			assertNull("ERROR: The valuable has not been removed from player inventory", playerTest.getItem(itemId));

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
			fail("ERROR: execute throws an exception when when trying to use an item that exists in the player inventory");
		}				
	}
	
	@Test
	public void testExecuteUseFoodOnce(){
		try {
			int health = playerTest.getHealth();
			int inc = 10;
			Food testFood = new Food(itemId, "Food for only one use", inc);
			playerTest.addItem(testFood);
			testCommand = testCommand.parse("use "+itemId, game);
			testCommand.execute();
			assertEquals("ERROR: Player health has not been increased after using Food", health+inc, playerTest.getHealth());
			assertNull("ERROR: The food has not been removed from player inventory", playerTest.getItem(itemId));

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
			fail("ERROR: execute throws an exception when when trying to use an item that exists in the player inventory");
		}				
	}
	
	@Test
	public void testExecuteUseFoodMoreThanOnce(){
		try {
			int health = playerTest.getHealth();
			int inc = 10;
			// A food that can be used twice
			Food testFood = new Food(itemId, "Food for using twice", inc, 2);
			playerTest.addItem(testFood);
			testCommand = testCommand.parse("use "+itemId, game);
			testCommand.execute();
			assertEquals("ERROR: Player health has not been increased after using Food", health+inc, playerTest.getHealth());
			assertNotNull("ERROR: The food has been removed from player inventory but it can be used one more time", playerTest.getItem(itemId));
			// Use it again
			testCommand = testCommand.parse("use "+itemId, game);
			testCommand.execute();
			assertEquals("ERROR: Player health has not been increased after using Food", health+2*inc, playerTest.getHealth());
			assertNull("ERROR: The food has not been removed from player inventory", playerTest.getItem(itemId));
		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
			fail("ERROR: execute throws an exception when when trying to use an item that exists in the player inventory");
		}				
	}

	@Test
	public void testExecuteUseKeyCorrect(){
		try {
			Room target = new Room(false, "target");
			map.addDoor(sourceRoom, Directions.NORTH, target);
			Door retDoor = map.getDoor(sourceRoom, Directions.NORTH);
			Key testKey = new Key(itemId, "The key", retDoor);
			playerTest.addItem(testKey);
			testCommand = testCommand.parse("use "+itemId, game);
			testCommand.execute();
			assertTrue("ERROR: Using the key does not open the door", retDoor.isOpen());
			assertNotNull("ERROR: The key has been removed from player inventory", playerTest.getItem(itemId));

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
			fail("ERROR: execute throws an exception when trying to use an item that exists in the player inventory");
		}				
	}
	
	@Test
	public void testExecuteUseKeyWrongPlace(){
		try {
			Room target = new Room(false, "target");
			map = new MockMap(target);
			map.addDoor(sourceRoom, Directions.NORTH, target);
			game = new MockGame(map);
			Door retDoor = map.getDoor(sourceRoom, Directions.NORTH);
			Key testKey = new Key(itemId, "The key", retDoor);
			playerTest.addItem(testKey);
			testCommand = testCommand.parse("use "+itemId, game);
			testCommand.execute();
			fail("ERROR: execute does not throw an exception when trying to use a key that annot open the door from the current position (targetRoom)");
			//assertFalse("ERROR: Using the key has open the door but we cannot open the door from out current position (targetRoom)", retDoor.isOpen());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
			assertNotNull("ERROR: The key has been removed from player inventory", playerTest.getItem(itemId));
		}				
	}
	
	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word USE", help.contains("USE"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word USAR", help.contains("USAR"));
	}

}
