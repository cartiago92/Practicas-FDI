package tp.pr5.commands;
import tp.pr5.*;
import tp.pr5.commands.exceptions.*;
import tp.pr5.items.*;

public class UseCommand implements Command {

 //Atributo
 private String id;
 private Player player;
 private Map mapa;

 //METODOS
 public UseCommand(){
 }
 
 public UseCommand(String id){
	 this.id=id;
 }
	
 public Command parse(String cad)throws WrongCommandFormatException{
	 String []palabras=cad.split(" ");

	 Command tmp;
		 if(palabras.length==2){
			 if ((palabras[0].equalsIgnoreCase("USE"))||(palabras[0].equalsIgnoreCase("USAR"))){
				 tmp=new UseCommand(palabras[1]);
			 }
			 else throw new WrongCommandFormatException("What?");
		 }
		 else throw new WrongCommandFormatException("What?");
		return tmp;
 }
	
 public void execute()throws CommandExecutionException{
	 Item auxit=this.player.getItem(this.id);
	    if(auxit==null)throw new CommandExecutionException(Costants.MESSAGE_NO_ITEM + this.id +"."); 
	    else if(auxit.canBeUsed()){
	    	   if(auxit.use(player, this.mapa.getCurrentRoom())){
	    		  if(!auxit.canBeUsed()){ 
	    			 player.removeItem(this.id);
	    		  }
	    	   }
	    	 else throw new CommandExecutionException(Costants.MESSAGE_DONT_KNOW_USE); 
	    }
	 
 }
 
 public void configureContext(Game g, Map m, Player p){
	//Establecer el contexto de ejecución. El método recibe todo el juego (el juego, el mapa y el jugador)
	//a pesar de que la aplicación real de ejecución () no lo requiera.	 
	this.mapa=m;
	this.player=p;
 }
	
 public String getHelp(){
	return "USE|USAR <<id>>";
 }
	
 public String getIdItem(){
	 return this.id;
 }
 
}
