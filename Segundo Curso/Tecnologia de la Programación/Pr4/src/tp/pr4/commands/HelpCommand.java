package tp.pr4.commands;

import tp.pr4.Game;
import tp.pr4.Parser;
import tp.pr4.commands.exceptions.*;

public class HelpCommand implements Command{
 
 public HelpCommand(){
	 
 }
	
 public Command parse(String cad, Game execContext)throws WrongCommandFormatException{
	 String []palabras=cad.split(" ");
		Command tmp; 
		if(palabras.length==1){
			   if ((palabras[0].equalsIgnoreCase("HELP"))||(palabras[0].equalsIgnoreCase("AYUDA"))){
				   tmp=new HelpCommand();
			   }
			   else throw new WrongCommandFormatException("What?");
			  }
			 else throw new WrongCommandFormatException("What?");
		 return tmp;
		
 }
		
 public void execute()throws CommandExecutionException{
	 System.out.println(Parser.getHelp());
 }
		
 public String getHelp(){
		return "HELP|AYUDA";
 }
	
}
