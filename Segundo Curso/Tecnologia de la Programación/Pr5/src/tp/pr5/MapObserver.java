package tp.pr5;


public interface MapObserver {

 public void roomEntered(Directions direction, RoomInfo targetRoom);
	 //Notifica que el jugador ha entrado una habitación procedente de una dirección dada
 
 public void playerHasExaminedRoom(RoomInfo r);
	 //Notifica que el jugador ha examinado una habitación
 
}
