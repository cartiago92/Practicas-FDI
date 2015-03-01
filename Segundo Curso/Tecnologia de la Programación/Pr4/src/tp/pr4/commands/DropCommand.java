package tp.pr4.commands;
import tp.pr4.Game;
import tp.pr4.Map;
import tp.pr4.Player;
import tp.pr4.Room;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;
import tp.pr4.items.Item;

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
	
 private DropCommand(String s,Player play,Map m){
	 this.id=s;
	 this.player=play;
	 this.mapa= m;
 }
	//METODOS
 public Command parse(String cad, Game execContext)throws WrongCommandFormatException{ //Analizadores de la cadena de devolver una instancia DropCommand 
	                                                                                       //o lanzar un WrongCommandFormatException ()
	 String []palabras=cad.split(" ");
	 Map m=execContext.getCurrentMap();
	 Player p=execContext.getPlayer();
	 Command tmp;

		 if(palabras.length==2){
			 if (((palabras[0].equalsIgnoreCase("DROP"))||(palabras[0].equalsIgnoreCase("SOLTAR")))){
				 tmp=new DropCommand(palabras[1],p,m);
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
	   }
	   else throw new CommandExecutionException("The item is already in the room"); 
		 }
 
 
 public String getHelp(){
	//	devuelve la sintaxis de comandos PICK <>
	 return "DROP|SOLTAR <<id>>";
 }

}
