package tp.pr4.items;

import tp.pr4.Player;
import tp.pr4.Room;

public class Food extends ExpirationItem{
	//Atributos
	private int health;
	
 public Food(String name, String description, int health, int times){
	 super(name, description, times); 
	 this.health=health;
 }
 
 public Food(String name, String description, int health){   //Constructor de una clase de alimento que se consume en un solo uso
     super(name,description);
     this.health=health;
     super.times=1;
 }
 
 public boolean use(Player who, Room where){   //Devuelve verdadero si el jugador puede utilizar este objeto en esta sala. La comida puede ser utilizado en cualquier lugar
   if (super.canBeUsed()){
	   who.addHealth(this.health);
	   super.times--;
	   return true;
   }
   else return false;
 }

}
