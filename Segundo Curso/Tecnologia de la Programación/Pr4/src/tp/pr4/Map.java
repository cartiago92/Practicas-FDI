package tp.pr4;
import java.util.ArrayList;

import tp.pr4.gui.MapPanel;

public class Map {
   //Atributos
   private Room initialRoom;
   private ArrayList<Door> doors;
	
 public Map(Room initialRoom){
	 this.initialRoom=initialRoom;
	 this.doors= new ArrayList<Door>();
 }
 
 public Door addDoor(Room source, Directions direction, Room target){ 
	//Crea una nueva puerta entre las habitaciones dadas como
	//parametro, se a�ade en el mapa y la devuelve
	
	Door door=new Door(source, direction, target, false);          
	this.doors.add(door);
	return door;
 }
 
 public Door addBidirectionalDoor(Room source, Directions direction, Room target){ 
  //Similar al m�todo addDoor pero la creaci�n de una puerta 
  //que puede ser recorrido en ambos sentidos.
	
	Door door=new Door(source, direction, target, true);                         
    this.doors.add(door);
	return door;                                                                               
 }

 public Door getDoor(Room room, Directions dir){  
  //Devuelve la puerta que est� en la direcci�n de una habitaci�n. Tengamos en cuenta que puede 
  //ser que esta puerta no se puede utilizar (si est� cerrada), pero no nos importa.
	
	for (int i=0;i<this.doors.size();i++){  
	  Door puerta=this.doors.get(i);
      if (puerta.isInRoom(room,dir))return puerta;
	}
	return null;
 }
 
 public Room getCurrentRoom(){     
	return this.initialRoom; }
 
 public void setCurrentRoom(Room initialRoom){     
    this.initialRoom=initialRoom; 
 } 
 
 public void setMapPanel(MapPanel mapPanel){
	//Establece el panel que mostrar� el mapa en la interfaz de Swing
 }
 
}
