package tp.pr4.commands;
import tp.pr4.Game;
import tp.pr4.commands.exceptions.*;

public class QuitCommand implements Command{
	private Game game;
	
 public QuitCommand(){
	 
 }
 
 private QuitCommand(Game gam){
	 this.game=gam;
	 
 }

 public Command parse(String cad, Game execContext)throws WrongCommandFormatException{
			String []palabras=cad.split(" ");
			Command tmp; 
			Game g=execContext;
			 if(palabras.length==1){
				   if ((palabras[0].equalsIgnoreCase("QUIT"))||(palabras[0].equalsIgnoreCase("SALIR"))){
					  tmp = new QuitCommand(g);
				   }
				   else throw new WrongCommandFormatException("What?");
			     }
			     else throw new WrongCommandFormatException("What?");
			 return tmp;
			
 }
		
 public void execute()throws CommandExecutionException{
	this.game.requestQuit();
 }
		
 public String getHelp(){
	return "QUIT|SALIR";
 }
}
