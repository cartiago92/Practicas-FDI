package tp.pr5.items;

import tp.pr5.Door;
import tp.pr5.Player;
import tp.pr5.Room;

public class Key extends PersistentItem{
	//Atributos
	private Door door;
	
 public Key(String id, String description, Door door){
	 super(id, description);
	 this.door=door;
 }
 
 public boolean use(Player who, Room where){
	  if (this.door.isInRoom(where)){
		if (this.door.isOpen()) this.door.close();
		else this.door.open();
		who.itemUsed("");
		return true;
      }
  //}
  return false;
}
 
}
