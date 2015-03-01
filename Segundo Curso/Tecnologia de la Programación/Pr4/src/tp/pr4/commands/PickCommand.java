package tp.pr4.commands;
import tp.pr4.*;
import tp.pr4.commands.exceptions.*;
import tp.pr4.items.*;

public class PickCommand implements Command{
 //Atributo
	private String id;
	private Player player;
	private Map mapa;

 public PickCommand(){
	 
 }
 
 private PickCommand(String id, Player play, Map mapa){
	 this.id=id;
	 this.player=play;
	 this.mapa=mapa; 
 }
 
 public Command parse(String cad, Game execContext)throws WrongCommandFormatException{
	 String []palabras=cad.split(" ");
	 Map m=execContext.getCurrentMap();
	 Player p=execContext.getPlayer();
	 Command tmp;
	 
		 if(palabras.length==2){
			 if (((palabras[0].equalsIgnoreCase("PICK"))||(palabras[0].equalsIgnoreCase("COGER")))){
				 tmp=new PickCommand(palabras[1],p,m);
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
	   else r.pickItem(player, this.id); 
	
 }
		
 public String getHelp(){
	return "PICK|COGER <<id>>" ;
 }

}
