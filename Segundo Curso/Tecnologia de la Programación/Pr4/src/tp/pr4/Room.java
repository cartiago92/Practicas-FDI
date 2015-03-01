package tp.pr4;
import java.util.ArrayList;

import tp.pr4.gui.MapCell;
import tp.pr4.items.Item;

public class Room {
 //Atributos
  private String description;
  private boolean exit;
  private ArrayList<Item> items;
  private String nombre;

 //Constructoras
 public Room(){
  this.items=new ArrayList<Item>();
 }
 
 public Room(boolean exit, String description){
  this.items=new ArrayList<Item>();
  this.description=description;
  this.exit=exit;
 }
 
 public Room(boolean exit, String description, Item[] item){
  this.description=description;
  this.exit=exit;			
  this.items=new ArrayList<Item>();
	 for (int j=0;j<item.length;j++){
		 Item auxit=item[j];
		 if (!this.items.contains(auxit))
		 this.items.add(auxit);
		}
	}
 
 public Room(boolean exit,String description,String name){
  this.description=description;
  this.exit=exit;
  this.nombre=name;
 }
 //Metodos
 public boolean addItem(Item it){
		
	for(int i=0; i < this.items.size();i++){
		if(this.items.get(i).getId().equalsIgnoreCase(it.getId())) return false;
	}
		
	return this.items.add(it);
		
	}
 
 public boolean pickItem(Player who,String id){
	 Item it;
	 for(int i=0; i<this.items.size(); i++){
		if(this.items.get(i).getId().equalsIgnoreCase(id)){
			it=this.items.get(i);
			this.items.remove(i);
			return who.addItem(it);
				}
	}
	return false;
	}
 
 public boolean isExit(){ //Es una sala de salida?
   if (this.exit)return true;
   return false;
 }

 public String getDescription(){
   String aux=this.description+"\n"+Costants.MESSAGE_ITEMS_ROOM;
   String aux2="";
	for(int i=0; i < items.size(); i++){
	  aux2= aux2+"\n"+items.get(i).toString();	
	}
	if (aux2.equalsIgnoreCase("")) aux=this.description+"\n"+Costants.MESSAGE_EMPTY;
	else aux=aux+aux2;
	
	return aux;
	}
 
 public boolean existsItem(String id){ //retorna true si el item existe.
  for (int i=0;i<items.size();i++){                  
	if (items.get(i).getId().equalsIgnoreCase(id))return true;
  }
  return false;
 }

 public void enterRoom(MapCell cell){
  //Muestra sobre la interfaz de giro que el jugador entra en esta sala	 
 }
 
 public void leaveRoom(){
	 //Muestra sobre la interfaz Swing que el jugador sale de la habitación
 }
}