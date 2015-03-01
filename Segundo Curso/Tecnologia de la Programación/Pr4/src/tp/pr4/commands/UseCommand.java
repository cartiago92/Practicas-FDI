package tp.pr4.commands;
import tp.pr4.*;
import tp.pr4.commands.exceptions.*;
import tp.pr4.items.*;

public class UseCommand implements Command {

 //Atributo
 private String id;
 private Player player;
 private Map mapa;

 //METODOS
 public UseCommand(){
 }
 
 private UseCommand(String id, Player play, Map mapa){
	 this.id=id;
	 this.player=play;
	 this.mapa=mapa;
 }
	
 public Command parse(String cad, Game execContext)throws WrongCommandFormatException{
	 String []palabras=cad.split(" ");
	 Player play=execContext.getPlayer();
	 Map m=execContext.getCurrentMap();
	 Command tmp;
		 if(palabras.length==2){
			 if ((palabras[0].equalsIgnoreCase("USE"))||(palabras[0].equalsIgnoreCase("USAR"))){
				 tmp=new UseCommand(palabras[1],play,m);
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
	    		 System.out.println(Costants.MESSAGE_CHANGES);
	    		  if(!auxit.canBeUsed()){ 
	    			 player.removeItem(this.id);
			    	 System.out.println("The "+this.id+Costants.MESSAGE_DELETE_INVENTORY);
	    		  }
	    	   }
	    	 else throw new CommandExecutionException(Costants.MESSAGE_DONT_KNOW_USE); 
	    }
	 
 }
	
 public String getHelp(){
	return "USE|USAR <<id>>";
 }
	
 public String getIdItem(){
	 return this.id;
 }
 
}
