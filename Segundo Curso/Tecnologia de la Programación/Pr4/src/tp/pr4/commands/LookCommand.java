package tp.pr4.commands;
import tp.pr4.*;
import tp.pr4.commands.exceptions.*;

public class LookCommand implements Command{
 private String id;
 private Player play;
	
 public LookCommand(){
	 
 }
 
 public LookCommand(String id,Player play){
	this.id=id;
	this.play=play;
 }
	
 public Command parse(String cad, Game execContext)throws WrongCommandFormatException{
		 
	 String []palabras=cad.split(" ");
		Command tmp; 
		Player p=execContext.getPlayer();
		 if ((palabras[0].equalsIgnoreCase("LOOK"))||(palabras[0].equalsIgnoreCase("MIRA"))){ 
			   if(palabras.length==1)tmp=new LookCommand("",p);
			   else if(palabras.length==2)tmp=new LookCommand(palabras[1],p);
			   else throw new WrongCommandFormatException("What?");
			  }
			 else throw new WrongCommandFormatException("What?");
		 return tmp;
		
}
		
 public void execute()throws CommandExecutionException{
	 
	 if(this.id.equalsIgnoreCase(" ")) System.out.println(Costants.MESSAGE_ITEMS+"\n"+this.play.showItems());  	
	 else if(this.play.getItem(this.id)==null){
		 throw new CommandExecutionException("There is no "+ this.id +" in your inventory.");
	     }
 	 else System.out.println(this.play.getItem(this.id).toString());	
	
 }
		
 public String getHelp(){
	return "LOOK|MIRA [<<id>>]";
 }
		
 public String getIdItem(){
	 return this.id; 
 }

}
