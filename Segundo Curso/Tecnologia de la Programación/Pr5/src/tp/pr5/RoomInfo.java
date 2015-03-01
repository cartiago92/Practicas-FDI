package tp.pr5;

public interface RoomInfo {

 public String getName();
//Retorna el nombre de la habitacion
 
 public String getDescription();
 //Devuelve la descripción de la habitación y una lista con los identificadores 
 //de los objetos y su descripción que pertenecen a esta sala.
 //<<description room>> <<item descriptions>>
 
  public boolean isExit();
 //Esto es una salida?
 
}
