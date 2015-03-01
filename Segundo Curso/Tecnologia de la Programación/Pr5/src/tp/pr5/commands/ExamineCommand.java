package tp.pr5.commands;
import tp.pr5.*;
import tp.pr5.commands.exceptions.*;

public class ExamineCommand implements Command{
 //Atributos
	private Map mapa;
	
 public ExamineCommand(){
	 
 }
 
 public ExamineCommand(Map mapa){
	this.mapa=mapa; 
 }
	
 public Command parse(String cad)throws WrongCommandFormatException{
	 String []palabras=cad.split(" ");
		Command tmp; 
		 if(palabras.length==1){
			   if ((palabras[0].equalsIgnoreCase("EXAMINE"))||(palabras[0].equalsIgnoreCase("EXAMINAR"))){
				   tmp=new ExamineCommand(null);
			   }
			   else throw new WrongCommandFormatException("What?");
			  }
			 else throw new WrongCommandFormatException("What?");
		 return tmp;
		
 }
		
 public void execute()throws CommandExecutionException{
	 this.mapa.playerExamineRoom();
	 }
		
 public void configureContext(Game g, Map m, Player p){
//Establecer el contexto de ejecución. El método recibe todo el juego (el juego, el mapa y el jugador)
//a pesar de que la aplicación real de ejecución () no lo requiera.	 
 this.mapa=m;
	 
 }
 
 public String getHelp(){
   return "EXAMINE|EXAMINAR";
 }
 
}
