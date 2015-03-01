package tp.pr5.commands;
import tp.pr5.*;
import tp.pr5.commands.exceptions.*;
import tp.pr5.items.*;

public class PickCommand implements Command{
 //Atributo
	private String id;
	private Player player;
	private Map mapa;

 public PickCommand(){
	 
 }
 
 public PickCommand(String id){
	 this.id=id; 
 }
 
 public Command parse(String cad)throws WrongCommandFormatException{
	 String []palabras=cad.split(" ");

	 Command tmp;
	 
		 if(palabras.length==2){
			 if (((palabras[0].equalsIgnoreCase("PICK"))||(palabras[0].equalsIgnoreCase("COGER")))){
				 tmp=new PickCommand(palabras[1]);
			 }
			 else throw new WrongCommandFormatException("What?");
		 }
		 else throw new WrongCommandFormatException("What?");
	 return tmp;
	 
 }
		
 public void execute()throws CommandExecutionException{
	 Item it2= this.player.getItem(this.id);
	 Room r=this.mapa.getCurrentRoom();
	 
	   if(!r.existsItem(this.id))throw new CommandExecutionException(Costants.MESSAGE_PICK_ERROR1);
	   else if(it2!=null)throw new CommandExecutionException(Costants.MESSAGE_ITEM_IS_IN_INVENTARY1+this.id+Costants.MESSAGE_ITEM_IS_IN_INVENTARY2);
	   else {r.pickItem(player, this.id); 
	        mapa.playerExamineRoom();
	         }
	
 }
 
 public void configureContext(Game g, Map m, Player p){
	//Establecer el contexto de ejecución. El método recibe todo el juego (el juego, el mapa y el jugador)
	//a pesar de que la aplicación real de ejecución () no lo requiera.	 
	this.mapa=m;
	this.player=p;

 }
 
		
 public String getHelp(){
	return "PICK|COGER <<id>>" ;
 }

}
