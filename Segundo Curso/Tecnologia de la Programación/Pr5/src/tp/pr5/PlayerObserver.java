package tp.pr5;

import java.util.List;

import tp.pr5.items.Item;

public interface PlayerObserver {

 public void playerLookedInventory(List<Item> inventory);
	//Notifica que el jugador miraba el inventario
 
 public void inventoryUpdate(List<Item> inventory);
	 //Notifica que el inventario del jugador ha sido cambiado

 public void playerUpdate(int newHealth, int newScore);
 	//Notifica que los atributos del jugador (la salud y la puntuación) han cambiado
 
 public void playerDead();
	 //Notifica si el jugador esta muerto
 
 public void itemLooked(String description);
 	//Notifica que el jugador desea mirar un item

 public void itemUsed(java.lang.String itemName);
	 //Notifica que un elemento se ha utilizado
 
 public void itemEmpty(java.lang.String itemName);
	 //Notifica si el item esta vacio
 
}
