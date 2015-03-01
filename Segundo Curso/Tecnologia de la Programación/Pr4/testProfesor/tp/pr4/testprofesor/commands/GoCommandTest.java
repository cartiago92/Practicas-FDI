package tp.pr4.testprofesor.commands;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import tp.pr4.Door;
import tp.pr4.Directions;
import tp.pr4.Map;
import tp.pr4.Player;
import tp.pr4.Room;
import tp.pr4.commands.Command;
import tp.pr4.commands.GoCommand;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;
import tp.pr4.testprofesor.MockGame;
import tp.pr4.testprofesor.MockMap;

public class GoCommandTest {
	private MockGame game;
	private Map map;
	private Room sourceRoom;
	private Room targetRoom;
	private Command testCommand;
	
	@Before
	public void setUp() throws Exception {
		sourceRoom = new Room(false, "Source");
		targetRoom = new Room(false, "Target");
		map = new MockMap(sourceRoom);
		map.addDoor(sourceRoom, Directions.NORTH, targetRoom);
		game = new MockGame(map);	
		testCommand = new GoCommand();
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("move north", game);
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseFailWrongDirection() {
		try {
			testCommand.parse("go norte", game);
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("go east", game);
			testCommand = testCommand.parse("ir east", game);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}

	@Test
	public void testExecuteNoDoor() {
		Player playerTest = game.getPlayer();
		int health = playerTest.getHealth();
		try {
			testCommand = testCommand.parse("go east", game);
			testCommand.execute();
			fail("ERROR: execute does not throw a exception when trying to move in a direction without a door");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
			assertEquals("ERROR: The command does not move the player but it consumes health", health, playerTest.getHealth());
		}
	}
	
	@Test
	public void testExecuteClosedDoor() {
		Player playerTest = game.getPlayer();
		int health = playerTest.getHealth();
		try {
			testCommand = testCommand.parse("go north", game);
			testCommand.execute();
			fail("ERROR: execute does not throw a exception when trying to move in a direction with a closed door");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
			assertEquals("ERROR: The command does not move the player but it consumes health", health, playerTest.getHealth());
		}
	}
	
	@Test
	public void testExecuteOpenDoor() {
		Player playerTest = game.getPlayer();
		int health = playerTest.getHealth();
		// Open the door
		Door retDoor = map.getDoor(sourceRoom, Directions.NORTH);
		retDoor.open();
		try {
			testCommand = testCommand.parse("go north", game);
			testCommand.execute();
			assertEquals("ERROR: execute changes the room through an open door but the player " +
					"arrives to a room that is not the targetRoom", targetRoom, map.getCurrentRoom());
			assertFalse("ERROR: Player arrives at a non-exit room but the command requests to finish the game", game.isQuitRequested());
			assertTrue("ERROR: The command moves the player but it does not consume health", playerTest.getHealth()<health);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
			fail("ERROR: execute throws an exception when trying to move in a direction with an open door");
		}
	}
	
	@Test
	public void testExecuteArrivesExit() {
		// Now the target room is an exit room
		targetRoom = new Room(true, "Target");
		map = new MockMap(sourceRoom);
		map.addDoor(sourceRoom, Directions.NORTH, targetRoom);
		game = new MockGame(map);	
		// Open the door
		Door retDoor = map.getDoor(sourceRoom, Directions.NORTH);
		retDoor.open();
		try {
			testCommand = testCommand.parse("go north", game);
			testCommand.execute();
			assertEquals("ERROR: execute changes the room through an open door but the player " +
					"arrives to a room that is not the targetRoom", targetRoom, map.getCurrentRoom());
			assertTrue("ERROR: Player arrives at an exit room but the command does not request to finish the game", game.isQuitRequested());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		catch (CommandExecutionException e) {
			fail("ERROR: execute throws an exception when trying to move in a direction with an open door");
		}
	}

	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word GO", help.contains("GO"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word IR", help.contains("IR"));
	}

}
