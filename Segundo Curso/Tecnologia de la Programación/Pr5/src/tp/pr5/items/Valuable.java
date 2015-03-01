package tp.pr5.items;

import tp.pr5.Player;
import tp.pr5.Room;

public class Valuable extends ExpirationItem {
	//Atributos
	private int score;
	
 public Valuable(String id, String description, int score){   //Default constructor de un objeto de valor. El tiempo toma el valor por defecto indicados en las constantes
	 super(id, description);
	 this.score=score;
	 super.times=1;
 }

 public boolean use(Player who, Room where){   //los puntos son agragados al jugador, true si la accion puede llevarse a cabo
	 if (super.canBeUsed()){
		 who.addPoints(this.score);
		 super.times--;
		 who.requestPlayerUpdate(); //---
		 who.itemUsed("");   //---
		 return true;
	 }
	 else return false;
 }
 
}
