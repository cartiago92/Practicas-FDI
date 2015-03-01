package tp.pr2.testprofesor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr2.Command;
import tp.pr2.Directions;
import tp.pr2.Door;
import tp.pr2.Food;
import tp.pr2.Item;
import tp.pr2.Key;
import tp.pr2.Map;
import tp.pr2.Player;
import tp.pr2.Room;
import tp.pr2.Valuable;
import tp.pr2.VerbCommands;

public class GameTest {

	private MockGame gameTest;
	private Room targetRoom;
	private Room sourceRoom;
	private Map mapTest;
	private Item itemTest;
	private String itemId;
	Player playerTest;

	@Before
	public void setUp() throws Exception {
		targetRoom = new Room (false, "Target room desc");
		sourceRoom = new Room (false, "Source room desc");
		itemId = "itemId";
		itemTest = new MockItem(itemId, "itemDescription");
		sourceRoom.addItem(itemTest);
		
		mapTest = new Map();
		mapTest.addDoor(sourceRoom, Directions.NORTH, targetRoom);
		gameTest = new MockGame(mapTest, sourceRoom);
		playerTest = gameTest.getPlayer();
	}

	@Test
	public void testChangeRoom() {
		// No door
		gameTest.doChangeRoom(Directions.EAST);
		assertEquals("ERROR: changeRoom has changed the room but there is no door in direction EAST", sourceRoom, gameTest.getCurrentRoom());

		// The door is closed
		gameTest.doChangeRoom(Directions.NORTH);
		assertEquals("ERROR: changeRoom has changed the room but the door between rooms is closed", sourceRoom, gameTest.getCurrentRoom());

		Door retDoor = mapTest.getDoor(sourceRoom, Directions.NORTH);
		retDoor.open();
		// The door is open but...
		gameTest.doChangeRoom(Directions.NORTH);
		assertNotSame("ERROR: changeRoom remains in the sourceRoom but the door between rooms is open", sourceRoom, gameTest.getCurrentRoom());
		assertEquals("ERROR: changeRoom changes the room through an open door but the player " +
				"arrives to a room that is not the targetRoom", targetRoom, gameTest.getCurrentRoom());
	}

	@Test
	public void testChangeRoomLooseLive() {


		int health = playerTest.getHealth();

		// No door
		gameTest.doChangeRoom(Directions.EAST);
		assertEquals("ERROR: changeRoom does not move the player but it consumes health", health, playerTest.getHealth());

		// The door is closed
		gameTest.doChangeRoom(Directions.NORTH);
		assertEquals("ERROR: changeRoom does not move the player because the door is closed but it consumes health", health, playerTest.getHealth());

		Door retDoor = mapTest.getDoor(sourceRoom, Directions.NORTH);
		retDoor.open();
		// The door is open 
		gameTest.doChangeRoom(Directions.NORTH);
		assertTrue("ERROR: changeRoom moves the player it does not consume health", playerTest.getHealth()<health);	
	}

	@Test
	public void testPickItemCorrect() {
		Command c = new Command(VerbCommands.PICK, itemId);
		gameTest.doProcessCommand(c);
		assertFalse("ERROR: Item has not been removed from the current room", sourceRoom.existsItem(itemId));
		assertNotNull("ERROR: Player inventory does not contain the item", playerTest.getItem(itemId));
	}
	
	@Test
	public void testPickItemInPlayerInventory() {
		Command c = new Command(VerbCommands.PICK, itemId);
		// The player has another item with the same name
		playerTest.addItem(itemTest);
		gameTest.doProcessCommand(c);
		assertNotNull("ERROR: Player inventory does not contain the item", playerTest.getItem(itemId));
		playerTest.removeItem(itemId);
		assertNull("ERROR: Player inventory has duplicates", playerTest.getItem(itemId));
		
		assertTrue("ERROR: Item should remain in the current room", sourceRoom.existsItem(itemId));
	}
	
	@Test
	public void testUseFoodOnce() {
		int health = playerTest.getHealth();
		int inc = 10;
		Food testFood = new Food(itemId, "Food for only one use", inc);
		playerTest.addItem(testFood);
		Command c = new Command(VerbCommands.USE, itemId);
		gameTest.doProcessCommand(c);
		assertEquals("ERROR: Player health has not been increased after using Food", health+inc, playerTest.getHealth());
		assertNull("ERROR: The food has not been removed from player inventory", playerTest.getItem(itemId));
	}
	
	@Test
	public void testUseFoodMoreThanOnce() {
		int health = playerTest.getHealth();
		int inc = 10;
		// A food that can be used twice
		Food testFood = new Food(itemId, "Food for using twice", inc, 2);
		playerTest.addItem(testFood);
		Command c = new Command(VerbCommands.USE, itemId);
		gameTest.doProcessCommand(c);
		assertEquals("ERROR: Player health has not been increased after using Food", health+inc, playerTest.getHealth());
		assertNotNull("ERROR: The food has been removed from player inventory but it can be used one more time", playerTest.getItem(itemId));
		// Use it again
		gameTest.doProcessCommand(c);
		assertEquals("ERROR: Player health has not been increased after using Food", health+2*inc, playerTest.getHealth());
		assertNull("ERROR: The food has not been removed from player inventory", playerTest.getItem(itemId));
	}
	
	@Test
	public void testUseValuable() {
		int points = playerTest.getPoints();
		int inc = 10;
		Valuable testValuable = new Valuable(itemId, "valuable", inc);
		playerTest.addItem(testValuable);
		Command c = new Command(VerbCommands.USE, itemId);
		gameTest.doProcessCommand(c);
		assertEquals("ERROR: Player points has not been increased after using Valuable", points+inc, playerTest.getPoints());
		assertNull("ERROR: The valuable has not been removed from player inventory", playerTest.getItem(itemId));
	}
	
	@Test
	public void testUseKeyCorrect() {
		Door retDoor = mapTest.getDoor(sourceRoom, Directions.NORTH);
		Key testKey = new Key(itemId, "Food for only one use", retDoor);
		playerTest.addItem(testKey);
		Command c = new Command(VerbCommands.USE, itemId);
		gameTest.doProcessCommand(c);
		assertTrue("ERROR: Using the key does not open the door", retDoor.isOpen());
		assertNotNull("ERROR: The key has been removed from player inventory", playerTest.getItem(itemId));
	}
	
	@Test
	public void testUseKeyWrongPlaceDoor() {
		Door retDoor = mapTest.getDoor(sourceRoom, Directions.NORTH);
		Key testKey = new Key(itemId, "Food for only one use", retDoor);
		playerTest.addItem(testKey);
		// Go to the opposite room
		gameTest.setCurrentRoom(targetRoom);
		Command c = new Command(VerbCommands.USE, itemId);
		gameTest.doProcessCommand(c);
		assertFalse("ERROR: Using the key has open the door but we cannot open the door from out current position (targetRoom)", retDoor.isOpen());
		assertNotNull("ERROR: The key has been removed from player inventory", playerTest.getItem(itemId));
	}
	
}
