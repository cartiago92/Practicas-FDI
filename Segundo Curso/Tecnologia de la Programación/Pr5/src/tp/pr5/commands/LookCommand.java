package tp.pr5.commands;
import tp.pr5.*;
import tp.pr5.commands.exceptions.*;

public class LookCommand implements Command{
 private String id;
 private Player play;
	
 public LookCommand(){
	 
 }
 
 public LookCommand(String id,Player play){
	this.id=id;
	this.play=play;
 }
	
 public Command parse(String cad)throws WrongCommandFormatException{
		 
	 String []palabras=cad.split(" ");
		Command tmp; 

		 if ((palabras[0].equalsIgnoreCase("LOOK"))||(palabras[0].equalsIgnoreCase("MIRA"))){ 
			   if(palabras.length==1)tmp=new LookCommand("",null);
			   else if(palabras.length==2)tmp=new LookCommand(palabras[1],null);
			   else throw new WrongCommandFormatException("What?");
			  }
			 else throw new WrongCommandFormatException("What?");
		 return tmp;
		
}
		
 public void execute()throws CommandExecutionException{
	 
	 if(this.id.equalsIgnoreCase("")) this.play.requestLookInventory();
	 else if(this.play.getItem(this.id)==null){
		 throw new CommandExecutionException("There is no "+ this.id +" in your inventory.");
	     }
 	 else this.play.lookItem(this.id);	
	
 }
 
 public void configureContext(Game g, Map m, Player p){
	//Establecer el contexto de ejecución. El método recibe todo el juego (el juego, el mapa y el jugador)
	//a pesar de que la aplicación real de ejecución () no lo requiera.	 
	this.play=p;	 
 }
		
 public String getHelp(){
	return "LOOK|MIRA [<<id>>]";
 }
		
 public String getIdItem(){
	 return this.id; 
 }

}
