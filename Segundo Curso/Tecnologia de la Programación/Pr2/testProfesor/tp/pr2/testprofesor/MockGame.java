package tp.pr2.testprofesor;

import java.lang.reflect.Field;

import tp.pr2.Command;
import tp.pr2.Directions;
import tp.pr2.Game;
import tp.pr2.Map;
import tp.pr2.Player;
import tp.pr2.Room;

public class MockGame extends Game {

	public MockGame(Map map, Room initialRoom) {
		super(map, initialRoom);
	}
	
	public void doChangeRoom(Directions direction){
		changeRoom(direction);
	}
	
	public Player getPlayer() {
		Player player = null;
		// Usamos instrospeccion para encontrar el atributo que es de la clase Player
		Field []fields = Game.class.getDeclaredFields();
		for (Field f:fields)
			if (f.getType().equals(Player.class))
				try {
					f.setAccessible(true);
					player = (Player)f.get(this);
					f.setAccessible(false);
					return player;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					return null;

				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return null;
				}
		return player;
	}
	
	public boolean doProcessCommand(Command c) {
		return processCommand(c);		
	}

}
