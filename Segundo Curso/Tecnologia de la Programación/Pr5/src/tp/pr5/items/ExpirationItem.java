package tp.pr5.items;

import tp.pr5.Player;
import tp.pr5.Room;

public abstract class ExpirationItem extends Item{
	//Atributos
	protected int times;

 public ExpirationItem(){}
 
 public ExpirationItem(String id, String description, int times){
	 super(id, description);
	 this.times=times;
 }
 
 public ExpirationItem(String id, String description){
	 super(id,description);
 }
	
 public abstract boolean use(Player who, Room where);
 
 public boolean canBeUsed(){
	 if (this.times<=0)return false;
	 else return true;
 }
 
 public String toString(){
	String aux=super.toString();
	return aux+"//"+this.times;
 }

}
