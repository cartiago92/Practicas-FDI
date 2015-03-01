package tp.pr2;
import java.util.ArrayList;

public class Map {
   //Atributos
   private ArrayList<Door> doors;
	
 public Map(){
	 this.doors= new ArrayList<Door>();
 }
 
 public Door addDoor(Room source, Directions direction, Room target){ //Crea una nueva puerta entre las habitaciones dadas como
	Door door=new Door(source, direction, target, false);          //parametro, se a�ade en el mapa y la devuelve
	this.doors.add(door);
	return door;
 }
 
 public Door addBidirectionalDoor(Room source, Directions direction, Room target){ //Similar al m�todo addDoor pero la creaci�n de una puerta 
	Door door=new Door(source, direction, target, true);                         //que puede ser recorrido en ambos sentidos.
    this.doors.add(door);
	return door;                                                                               
 }

 public Door getDoor(Room room, Directions dir){  //Devuelve la puerta que est� en la direcci�n de una habitaci�n. Tengamos en cuenta que puede 
	for (int i=0;i<this.doors.size();i++){  //ser que esta puerta no se puede utilizar (si est� cerrada), pero no nos importa.
	  Door puerta=this.doors.get(i);
      if (puerta.isInRoomaux(room,dir))return puerta;
	}
	return null;
 }
 
}
