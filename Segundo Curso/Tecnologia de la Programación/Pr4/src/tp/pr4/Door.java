package tp.pr4;

public class Door {
  //Atributos
	private Room hab_origen;
	private Directions direction;
	private Room hab_destino;
	private boolean bidirectional;
	private boolean isopen;

 public Door(Room source, Directions direction, Room target, boolean bidirectional){
	 this.hab_origen=source;
	 this.direction=direction;
	 this.hab_destino=target;
	 this.bidirectional=bidirectional;
	 this.isopen=false;
 }	
 
 public Door(Room source, Directions direction, Room target, boolean bidirectional, boolean isOpen){
	 this.hab_origen=source;
	 this.direction=direction;
	 this.hab_destino=target;
	 this.bidirectional=bidirectional;
	 this.isopen=isOpen;
 }
 
 public void close(){   //Esta funcion cierra la puerta
	 this.isopen=false;
 }
 
 public boolean connect(Room roomA, Room roomB){  //Devuelve true si la puerta conecta la habitacion A y B y podemos ir de A a B y la puerta esta abierta
	 if (((((this.hab_origen.equals(roomA))&&(this.hab_destino.equals(roomB)))||
		 ((this.hab_origen.equals(roomB))&&(this.hab_destino.equals(roomA))&&(bidirectional))))&&(this.isopen))return true;
	 else return false;
 }
 
 public boolean isInRoom(Room room){  // Devuelve true si la puerta pertenece a la habitacion de entrada room. Tengamos en cuenta que si la puerta es bidireccional, 
	if (this.bidirectional){                                                         //entonces comprueba que la room == hab_origen || room == hab_destino.
	 if ((this.hab_origen.equals(room))||(this.hab_destino.equals(room)))return true;}
	else if (this.hab_origen.equals(room))return true;
	return false;
 }
 
 public boolean isInRoom(Room room, Directions where){ //Devuelve true si el parámetro de entrada la puerta pertenece a room en la dirección representada where.
	 
	 if ((this.hab_origen.equals(room))&&(this.direction==where))return true;
		else if ((this.hab_destino.equals(room))&&(this.direction.getOposite()==where))return true;
		return false;
 }
 
 public boolean isOpen(){  //chekea si la puerta esta abierta
	 if (this.isopen)return true;
	 else return false;
 }
 
 public Room nextRoom(Room whereAmI){ //Devuelve la habitación del otro lado de whereAmI si es posible (incluso si la puerta está cerrada)
	 if (this.bidirectional){
	  if (this.hab_origen.equals(whereAmI))return this.hab_destino;
	  else if (this.hab_destino.equals(whereAmI))return this.hab_origen;
	  }
	 else{ if (this.hab_origen.equals(whereAmI))return this.hab_destino;}
	 return null;
 }
 
 public void open(){  // esta funcion abre la puerta
	 this.isopen=true;
 }
 
 public Room getHab_destino(){
	 return this.hab_destino;
 }
 
}