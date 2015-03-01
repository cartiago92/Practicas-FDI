package tp.pr5;

import java.util.ArrayList;
import tp.pr5.items.Item;

public class Player extends Observable<PlayerObserver>{
//Atributos
 private int health;
 private int score;
 private ArrayList<Item> inventario;

//Contructoras
 public Player(){     //Constructor de jugador. Inicialmente, sus atributos son: 100 para la salud 
   this.health=Costants.INITIAL_LIVE;                      //y 0 para los puntos. El inventario está vacía
   this.score=Costants.INITIAL_SCORE;
   this.inventario= new ArrayList<Item>();
 }
 
 //Metodos
 public boolean addItem(Item item){    //Añade un Item al Inventario, true si el elemento se añadió y falso
   for (int i=0; i<inventario.size();i++){      //cuando el jugador ya tenía un item con el mismo nombre.
	   if (inventario.get(i).getId().equalsIgnoreCase(item.getId()))return false;
   }
	 this.inventario.add(item); 
	 this.requestInventoryUpdate();   //----
	 return true;
 }
 
 public Item getItem(String id){     // Devuelve el elemento del inventario de acuerdo con el nombre del elemento del artículo. 
   for (int i=0; i<inventario.size();i++){
	 if (inventario.get(i).getId().equalsIgnoreCase(id))return inventario.get(i);    //Si no existe, devuelve null.
	 
   }
   return null;
 }
 
 public boolean removeItem(String id){  //Elimina el item dado como parametro, true si el 
   for (int i=0;i<inventario.size();i++){       //jugador tenía ese elemento y se elimina del inventario
	  if (inventario.get(i).getId().equalsIgnoreCase(id)){
		  inventario.remove(inventario.get(i));
		  this.requestInventoryUpdate();  //---
		  if (!id.equalsIgnoreCase("llave")) this.itemEmpty(id);            //---
		  return true;
	 }
   }
   return false;
 }
 
 public boolean dead(){   //True si el juador esta muerto
	 if (this.health<=Costants.DEFAULT_EXPIRATION_LIVE)return true;
	 else return false;
 }
 
 public int getPoints(){   //Devuelve los puntos del jugador
	 return score;
 }
 
 public void addPoints(int points){   //Suma el valor de los points
	 this.score=this.score+points;
	 //if (this.playpan!=null) this.playpan.updatePlayerScore(this.score);
	
 }
 
 public void addHealth(int health){   //La salud se ha actualizado.
	 this.health=this.health+health;
	// if (this.playpan!=null)this.playpan.updatePlayerHealth(this.health);
 }
 
 public int getHealth(){
	 return this.health;
 }
 
 public String toString(){
	return "Health= "+this.health+", Score= "+this.score; 
 }
 
 
 public void requestPlayerUpdate(){
   //Pide al jugador a informar a los observadores que los atributos de los jugadores han cambiado
	 for(int i=0; i < this.getObservers().size(); i++ )
		this.getObservers().get(i).playerUpdate(this.health, this.score);
 }

 public void requestInventoryUpdate(){
   //Pide al jugador a informar a los observadores que el inventario ha cambiado
	 for(int i=0; i < this.getObservers().size(); i++ )
		this.getObservers().get(i).inventoryUpdate(this.inventario);
 }
 
 
 public void lookItem(String itemName){
	 //Pide al jugador a informar a los observadores acerca de una descripción del artículo
	 Item it=this.getItem(itemName);
	 
	 for(int i=0; i < this.getObservers().size(); i++ )
		 this.getObservers().get(i).itemLooked(it.getDescription());
 }
 
 public void itemEmpty(String itemName){
	 //Pide al jugador a informar a los observadores que un elemento está vacío
	 for(int i=0; i < this.getObservers().size(); i++ )
		 this.getObservers().get(i).itemEmpty(itemName);
 }
 
 public void itemUsed(String itemName){
	 //Pide al jugador a informar a los observadores acerca de un elemento se ha utilizado
	 for(int i=0; i < this.getObservers().size(); i++ )
		 this.getObservers().get(i).itemUsed(itemName);
 }
 
 public void requestLookInventory(){
	 //Pide al jugador a informar a los observadores acerca de la descripción del inventario
	 for(int i=0; i < this.getObservers().size(); i++ )
		 this.getObservers().get(i).playerLookedInventory(this.inventario);

 }
 
}
