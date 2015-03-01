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
 
 public String toString(){   //M�todo que devuelve una cadena con informaci�n sobre un elemento. 
	                                                   //El patr�n es el siguiente  --item[<>]=<>
	return "--Item["+this.id+"]="+this.description;
 }
 
 public abstract boolean use(Player who, Room where);  //M�todo llamado cuando se utiliza el elemento. Las clases derivadas 
	                                                   //deben implementar el m�todo. True si el elemento se utilizo con exito
 
 public abstract boolean canBeUsed();   //Dependiendo del tipo de elemento, que podr�a ser utilizado para siempre (una llave) o s�lo un n�mero 
	                                    //limitado de veces (de alimentos y valioso)
	                                     //true si el elemento se puede utilizar. Esto no garantiza que la invocaci�n a usar el m�todo devuelve true, porque el jugador podr�a tratar de usarlo en un lugar equivocado, etc
 
 
}
