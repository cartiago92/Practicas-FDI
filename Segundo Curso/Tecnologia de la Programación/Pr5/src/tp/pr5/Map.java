package tp.pr5;
import java.util.ArrayList;

public class Map extends Observable<MapObserver>{
   //Atributos
   private Room initialRoom;
   private ArrayList<Door> doors;
     
 public Map(Room initialRoom){
	 this.initialRoom=initialRoom;
	 this.doors= new ArrayList<Door>();
	 
 }
 
 public Door addDoor(Room source, Directions direction, Room target){ 
	//Crea una nueva puerta entre las habitaciones dadas como
	//parametro, se añade en el mapa y la devuelve
	
	Door door=new Door(source, direction, target, false);          
	this.doors.add(door);
	return door;
 }
 
 public Door addBidirectionalDoor(Room source, Directions direction, Room target){ 
  //Similar al método addDoor pero la creación de una puerta 
  //que puede ser recorrido en ambos sentidos.
	
	Door door=new Door(source, direction, target, true);                         
    this.doors.add(door);
    return door;                                                                               
 }

 public Door getDoor(Room room, Directions dir){  
  //Devuelve la puerta que está en la dirección de una habitación. Tengamos en cuenta que puede 
  //ser que esta puerta no se puede utilizar (si está cerrada), pero no nos importa.
	
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

 //METODO USADO APARTE
 
 public void playerExamineRoom(){
  //Pide al jugador quiere examinar una habitación
	 for(int i=0; i < this.getObservers().size(); i++ ){
			this.getObservers().get(i).playerHasExaminedRoom(initialRoom);
	}
	 
 }
 
 public void playerChangeRoom(Directions dir){
	 for(int i=0; i < this.getObservers().size(); i++ ){
			this.getObservers().get(i).roomEntered(dir, this.initialRoom);
	}
 }
 
}
