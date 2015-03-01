package tp.pr5.commands;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.*;

public class QuitCommand implements Command{
	private Game game;
	
 public QuitCommand(){
	 
 }
 
 private QuitCommand(Game gam){
	 this.game=gam;
	 
 }

 public Command parse(String cad)throws WrongCommandFormatException{
			String []palabras=cad.split(" ");
			Command tmp; 
			//Game g=execContext;
			 if(palabras.length==1){
				   if ((palabras[0].equalsIgnoreCase("QUIT"))||(palabras[0].equalsIgnoreCase("SALIR"))){
					  tmp = new QuitCommand(null);
				   }
				   else throw new WrongCommandFormatException("What?");
			     }
			     else throw new WrongCommandFormatException("What?");
			 return tmp;
			
 }
 
 public void configureContext(Game g, Map m, Player p){
	//Establecer el contexto de ejecución. El método recibe todo el juego (el juego, el mapa y el jugador)
	//a pesar de que la aplicación real de ejecución () no lo requiera.	 
	this.game=g;	 
 }
 
		
 public void execute()throws CommandExecutionException{
	this.game.requestQuit();
 }
		
 public String getHelp(){
	return "QUIT|SALIR";
 }
}
