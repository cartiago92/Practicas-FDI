package tp.pr2;

public class Key extends PersistentItem{
	//Atributos
	private Door door;
	
 public Key(String id, String description, Door door){
	 super(id, description);
	 this.door=door;
 }
 
 public boolean use(Player who, Room where){
  //if (!this.door.getBidirectional()){
	  if (this.door.isInRoom(where)){
		if (this.door.isOpen()) this.door.close();
		else this.door.open();
		return true;
      }
  //}
  return false;
}
 
}
