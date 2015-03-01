package tp.pr5.console;

import java.util.List;

import tp.pr5.Directions;

import tp.pr5.Costants;
import tp.pr5.GameObserver;
import tp.pr5.MapObserver;
import tp.pr5.Parser;
import tp.pr5.PlayerObserver;
import tp.pr5.RoomInfo;

import tp.pr5.items.*;

public class Console implements GameObserver, PlayerObserver, MapObserver{
	//ATRIBUTOS
	
	//CONSTRUCTORA POR DEFECTO
	
	//METODOS
	public void roomEntered(Directions direction,RoomInfo targetRoom){
	//Notifica que el jugador ha entrado una habitación procedente de una dirección dada
	  if (!direction.equals(Directions.UNKNOWN)){
		System.out.println(Costants.MESSAGE_CHANGE_ROOM+direction);
		System.out.println(targetRoom.getDescription());
	  }
	}
	
	public void playerHasExaminedRoom(RoomInfo r){
	//Notifica que el jugador examinado una habitación
		System.out.println(r.getDescription());
		
	}
	
	public void inventoryUpdate(List<Item> inventory){
	//Notifica que el inventario del jugador ha cambiado
		
	}
	
	public void playerUpdate(int newHealth, int newScore){
	//Notifica que los atributos del jugador (la salud y la puntuación) han cambiado
		System.out.println("HEALTH = "+newHealth+", SCORE ="+newScore);
	}
	
	public void itemLooked(String description){
	//Notifica que el jugador desea examinar un item
	System.out.println(description);
		
	}
	
	public void itemUsed(String itemName){
	//Notifica que un elemento se ha utilizado
	System.out.println(Costants.MESSAGE_CHANGES);	
	}
	
	public void itemEmpty(String itemName){
	//Notifica que un elemento está vacío	
	System.out.println("The "+itemName+Costants.MESSAGE_DELETE_INVENTORY);	
	}
	
	public void playerDead(){
	//Notifica a la muerte del jugador	
		System.out.println("You are dead");
	}
	
	public void playerLookedInventory(List<Item> inventory){
	//Notifica que el jugador miraba el inventario	
		 String aux=Costants.MESSAGE_ITEMS+"\n";
		 String LINE_SEPARATOR = System.getProperty("line.separator");
		 for (int i=0;i<inventory.size();i++){
			 if (i==inventory.size()-1)aux=aux+inventory.get(i).toString();
			 else aux=aux+inventory.get(i).toString()+LINE_SEPARATOR;
		 }
		 if (aux.equalsIgnoreCase(""))aux=aux+Costants.MESAGE_POOR;
		 System.out.println(aux);
	}
	
	public void gameStart(RoomInfo initialRoom, int playerPoints, int playerHealth){
	//Avisa de que empiece el juego. Se proporciona el estado inicial del juego	
		System.out.println(initialRoom.getDescription()+"\nHEALTH = "+playerHealth+", SCORE =" +playerPoints);	
		
	}
	
	public void gameOver(boolean win){
	//Notifica que el juego se termina y si el jugador gana o es la muerte	
		if (win) System.out.println("GAME OVER \n Thank you for playing, goodbye.");
		//else System.out.println("GAME OVER \nThank you for playing, goodbye.");
		 
	}
	
	public void gameQuit(){
	//Notifica que el jugador pide abandonar el juego	
		this.gameOver(false);
		System.exit(0);
	}
	
	public void gameHelp(){
	//Notifica que las solicitudes de información ayudan a los jugadores	
		System.out.println(/*"You are lost. You are alone. You wander around\nYour command words are:" + "\n" +*/ Parser.getHelp());	
	}
	
	public void gameError(String msg){
	//Notifica que el juego no puede ejecutar un comando
	System.out.println(msg);
	}

}
