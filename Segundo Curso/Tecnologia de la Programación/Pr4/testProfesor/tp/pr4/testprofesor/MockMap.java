package tp.pr4.testprofesor;

import tp.pr4.Map;
import tp.pr4.Room;

public class MockMap extends Map {

	public MockMap(Room initRoom) {
		super(initRoom);
	}
	
	public Room getCurrentRoom() {
		Room r = super.getCurrentRoom();
		if (r==null)
			return new Room(false, "");
		else
			return r;		
	}

}
