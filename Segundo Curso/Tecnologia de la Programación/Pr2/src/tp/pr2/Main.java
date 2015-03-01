package tp.pr2;
import java.util.ArrayList;

public class Main {

	/*private static Room[] createRooms() {
		Room[] rooms = new Room[4];
		
		rooms[0] = new Room(false, "Estamos en la entrada. ¡Comienza la aventura!");
		rooms[1] = new Room(false, "Esto es un pasillo.");
		rooms[2] = new Room(false, "Hemos llegado al salon. Al Norte parece que hay una puerta.");
		rooms[3] = new Room(true, "Llegamos a la salida");
		
		return rooms;
	}

	private static Door[] insertDoors(Map m, Room[] r) {

		Door[] d = new Door[3];

		d[0] = m.addBidirectionalDoor(r[0], Directions.SOUTH, r[1]);
		d[0].open();

		d[1] = m.addDoor(r[1], Directions.EAST, r[2]);
		d[1].open();

		d[2] = m.addBidirectionalDoor(r[2], Directions.NORTH, r[3]);
		d[2].close();

		return d;

	}

	public static void insertItems(Room[] r, Door[] d) {
		
		r[0].addItem(new Food("agua", "Botella de agua, entran tres tragos.", 10, 3));
		r[0].addItem(new Food("carne", "Parece carne, pero no huele igual...", -50));
		r[1].addItem(new Key("llave", "Una llave brillante", d[2]));
		r[2].addItem(new Valuable("moneda", "Moneda brillante, parece de oro.", 30));
	}

	public static void main(String[] args) {

		Map m = new Map();
		Room[] r = createRooms();
		Door[] d = insertDoors(m, r);
		insertItems(r, d);
		if (r[0] != null) {
			Game g = new Game(m, r[0]);
			g.runGame();
		}

	}
}*//**
	 * 
	 * Ejemplo de mapa del enunciado.
	 * 
	|---------------------+---------------|
	| *Entrada*           | *Salida*      |
	| Agua                |               |
	| Carne en mal estado |               |
	|------  BID   -------+---- BID  -----|
	| *Pasillo*           | *Salón*       |
	| Llave puerta 3     UNID             |
	|                     | Moneda de oro |
	|---------------------+---------------|
	 * 
	 * 
	 * @author Marco Antonio
	 * @y.exclude
	 */
	

  private static Room[] createRooms() {
	Room[] rooms = new Room[4];
			
	rooms[0] = new Room(false, "Estamos en la entrada. ¡Comienza la aventura!");
	rooms[1] = new Room(false, "Esto es un pasillo.");
	rooms[2] = new Room(false, "Hemos llegado al salon. Al Norte parece que hay una puerta.");
	rooms[3] = new Room(true, "Llegamos a la salida");
			
	return rooms;
  }

  private static Door[] insertDoors(Map m, Room[] r) {

	Door[] d = new Door[3];

	d[0] = m.addBidirectionalDoor(r[0], Directions.SOUTH, r[1]);
	d[0].open();

	d[1] = m.addDoor(r[1], Directions.EAST, r[2]);
	d[1].open();

	d[2] = m.addBidirectionalDoor(r[2], Directions.NORTH, r[3]);
	d[2].close();

	return d;

  }

  public static void insertItems(Room[] r, Door[] d) {
			
	r[0].addItem(new Food("agua", "Botella de agua, entran tres tragos.", 10, 3));
	r[0].addItem(new Food("carne", "Parece carne, pero no huele igual...", -50));
	r[0].addItem(new Food("seta", "Tal vez sea venenosa...", -50));
	r[1].addItem(new Key("llave", "Una llave brillante", d[2]));
	r[2].addItem(new Valuable("moneda", "Moneda brillante, parece de oro.", 30));
  
  }

		/**
		 * Main method
		 */
  public static void main(String[] args) {

	Map m = new Map();
	Room[] r = createRooms();
	Door[] d = insertDoors(m, r);
	insertItems(r, d);
	if (r[0] != null) {
	  Game g = new Game(m, r[0]);
	  g.runGame();
	}

  }
  
}


