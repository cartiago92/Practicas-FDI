package tp.pr4.commands;
import tp.pr4.*;
import tp.pr4.commands.exceptions.*;

public class ExamineCommand implements Command{
 //Atributos
	private Map mapa;
	
 public ExamineCommand(){
	 
 }
 
 public ExamineCommand(Map mapa){
	this.mapa=mapa; 
 }
	
 public Command parse(String cad, Game execContext)throws WrongCommandFormatException{
	 String []palabras=cad.split(" ");
		Command tmp; 
		Map m=execContext.getCurrentMap();
		 if(palabras.length==1){
			   if ((palabras[0].equalsIgnoreCase("EXAMINE"))||(palabras[0].equalsIgnoreCase("EXAMINAR"))){
				   tmp=new ExamineCommand(m);
			   }
			   else throw new WrongCommandFormatException("What?");
			  }
			 else throw new WrongCommandFormatException("What?");
		 return tmp;
		
 }
		
 public void execute()throws CommandExecutionException{
	 System.out.println(this.mapa.getCurrentRoom().getDescription());
	 }
		
 public String getHelp(){
   return "EXAMINE|EXAMINAR";
 }
 
}
