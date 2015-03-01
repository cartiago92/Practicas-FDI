package tp.pr5.commands;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.items.Item;

public class DropCommand implements Command {
	//ATRIBUTOS
	private String id;
	private Player player;
	private Map mapa;

	//CONTRUCTORA
 public DropCommand(){
	//Se construye un comando sin el contexto de la ejecución. Por lo tanto,
	//si el comando se ejecuta una excepción debe ser elevado (que no sabe qué elemento a caer).	
	}
	
 public DropCommand(String s){
	 this.id=s;
 }
	//METODOS
 public Command parse(String cad)throws WrongCommandFormatException{ //Analizadores de la cadena de devolver una instancia DropCommand 
	                                                                                       //o lanzar un WrongCommandFormatException ()
	 String []palabras=cad.split(" ");
	
	 Command tmp;

		 if(palabras.length==2){
			 if (((palabras[0].equalsIgnoreCase("DROP"))||(palabras[0].equalsIgnoreCase("SOLTAR")))){
				 tmp=new DropCommand(palabras[1]);
			 }
			 else throw new WrongCommandFormatException("What?");
		 }
		 else throw new WrongCommandFormatException("What?");
	 return tmp;
	 	
 }
 
 public void execute()throws CommandExecutionException{
		//La ejecución debe colocar el elemento que figura 
		//en el constructor del jugador que figura en la habitación dada	
	 
	 Item it2= this.player.getItem(this.id);
	 if(it2==null){
		 throw new CommandExecutionException("You do not have any " + this.id+".");
	 }
	 Room r=this.mapa.getCurrentRoom();  
	   if(!r.existsItem(this.id)){
		   r.addItem(it2);
		   this.player.removeItem(this.id);
		   this.mapa.playerExamineRoom();
	   }
	   else throw new CommandExecutionException("The item is already in the room"); 
		 }
 
 public void configureContext(Game g, Map m, Player p){
 //Establecer el contexto de ejecución. El método recibe todo el juego (el juego, el mapa y el jugador)
 //a pesar de que la aplicación real de ejecución () no lo requiera.
	this.mapa=m;
	this.player=p;
	
 }
 
 
 public String getHelp(){
	//	devuelve la sintaxis de comandos PICK <>
	 return "DROP|SOLTAR <<id>>";
 }

}
