package tp.pr4.testprofesor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//import tp.pr2.Command;
import tp.pr4.Directions;
import tp.pr4.Door;
import tp.pr4.items.Food;
import tp.pr4.items.Item;
import tp.pr4.items.Key;
import tp.pr4.Game;
import tp.pr4.Map;
import tp.pr4.Player;
import tp.pr4.Room;
import tp.pr4.items.Valuable;

public class GameTest {

	@Test
	public void runGameWithNullMapTest() {
		try{
			Game g = new Game(null);
			g.runGame();
		} catch (Exception e) {
			fail("ERROR: The game should control that the map can be a null pointer." +
					" In this case, the runGame should do nothing and the game finishes.");
		}
		
	}
	
}
