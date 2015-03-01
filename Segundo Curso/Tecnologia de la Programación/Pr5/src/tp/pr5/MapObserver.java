package tp.pr5;


public interface MapObserver {

 public void roomEntered(Directions direction, RoomInfo targetRoom);
	 //Notifica que el jugador ha entrado una habitaci�n procedente de una direcci�n dada
 
 public void playerHasExaminedRoom(RoomInfo r);
	 //Notifica que el jugador ha examinado una habitaci�n
 
}
