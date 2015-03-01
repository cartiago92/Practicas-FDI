package tp.pr2;

public abstract class Item {
	//Atributos
	private String id;
	private String description;
	
 public Item(){}
	
 protected Item(String id, String description){  //Cobstructor del item, es unico
	 this.id=id;
	 this.description=description;
 }  
 
 public String getId(){
	 return id;
 }
 
 public String toString(){   //Método que devuelve una cadena con información sobre un elemento. 
	                                                   //El patrón es el siguiente  --item[<>]=<>
	return "--Item["+this.id+"]="+this.description;
 }
 
 public abstract boolean use(Player who, Room where);  //Método llamado cuando se utiliza el elemento. Las clases derivadas 
	                                                   //deben implementar el método. True si el elemento se utilizo con exito
 
 public abstract boolean canBeUsed();   //Dependiendo del tipo de elemento, que podría ser utilizado para siempre (una llave) o sólo un número 
	                                    //limitado de veces (de alimentos y valioso)
	                                     //true si el elemento se puede utilizar. Esto no garantiza que la invocación a usar el método devuelve true, porque el jugador podría tratar de usarlo en un lugar equivocado, etc
 
 
}
