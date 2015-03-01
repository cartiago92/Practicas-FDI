package tp.pr2;

public abstract class PersistentItem extends Item{
	 
 public PersistentItem(){}
 
 public PersistentItem(String id, String description){
	 super(id, description);
 }
 
 public abstract boolean use(Player who, Room where);
	 
 public boolean canBeUsed(){
	 return true;
 }
	 
 public String toString(){
	 String aux=super.toString();
	 return aux;
 }

}
