package tp.pr5;

public interface RoomInfo {

 public String getName();
//Retorna el nombre de la habitacion
 
 public String getDescription();
 //Devuelve la descripci�n de la habitaci�n y una lista con los identificadores 
 //de los objetos y su descripci�n que pertenecen a esta sala.
 //<<description room>> <<item descriptions>>
 
  public boolean isExit();
 //Esto es una salida?
 
}
