package tp.pr2;
import java.util.ArrayList;

public class Player {
	//Atributos
	private int health;
	private int score;
	private ArrayList<Item> inventario;
	
 public Player(){     //Constructor de jugador. Inicialmente, sus atributos son: 100 para la salud 
   this.health=Costants.INITIAL_LIVE;                      //y 0 para los puntos. El inventario está vacía
   this.score=Costants.INITIAL_SCORE;
   this.inventario= new ArrayList<Item>();
 }
 
 public boolean addItem(Item item){    //Añade un Item al Inventario, true si el elemento se añadió y falso
   for (int i=0; i<inventario.size();i++){      //cuando el jugador ya tenía un item con el mismo nombre.
	   if (inventario.get(i).getId().equalsIgnoreCase(item.getId()))return false;
   }
	 this.inventario.add(item);               
	 return true;
 }
 
 public Item getItem(String id){     // Devuelve el elemento del inventario de acuerdo con el nombre del elemento del artículo. 
   for (int i=0; i<inventario.size();i++){
	 if (inventario.get(i).getId().equalsIgnoreCase(id))return inventario.get(i);                          //Si no existe, devuelve null.
   }
   return null;
 }
 
 public boolean removeItem(String id){  //Elimina el item dado como parametro, true si el 
   for (int i=0;i<inventario.size();i++){       //jugador tenía ese elemento y se elimina del inventario
	  if (inventario.get(i).getId().equalsIgnoreCase(id)){
		  inventario.remove(inventario.get(i));
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
 }
 
 public void addHealth(int health){   //La salud se ha actualizado.
	 this.health=this.health+health;
 }
 
 public int getHealth(){
	 return this.health;
 }
 
 public String showItems(){   //Se muestran los items transportados por el jugador
	 String aux="";
	 String LINE_SEPARATOR = System.getProperty("line.separator");
	 for (int i=0;i<inventario.size();i++){
		 if (i==inventario.size()-1)aux=aux+inventario.get(i).toString();
		 else aux=aux+inventario.get(i).toString()+LINE_SEPARATOR;
	 }
	 if (aux.equalsIgnoreCase(""))aux=aux+Costants.MESAGE_POOR;
	 return aux;
 }
}
