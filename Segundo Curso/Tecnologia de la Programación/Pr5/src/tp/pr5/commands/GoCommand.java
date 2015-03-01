package tp.pr5.commands;
import tp.pr5.*;
import tp.pr5.commands.exceptions.*;

public class GoCommand implements Command{
  //Atributos
	private Directions dir;
	private Player play;
	private Map map;
 
 public GoCommand(){
 }
 
 public GoCommand(Directions dire){
	 this.dir=dire;
 }

 public Command parse(String cad)throws WrongCommandFormatException{
	String []palabras=cad.split(" ");
	Command tmp; 
	
		if(palabras.length==2){
		   if ((palabras[0].equalsIgnoreCase("GO"))||(palabras[0].equalsIgnoreCase("IR"))){
			  if (palabras[1].equalsIgnoreCase("NORTH")) tmp=new GoCommand(Directions.NORTH);
			  else if (palabras[1].equalsIgnoreCase("SOUTH")) tmp=new GoCommand(Directions.SOUTH);
			  else if (palabras[1].equalsIgnoreCase("EAST")) tmp=new GoCommand(Directions.EAST);
			  else if (palabras[1].equalsIgnoreCase("WEST")) tmp=new GoCommand(Directions.WEST);
			  else throw new WrongCommandFormatException("What?");
		   }
		   else throw new WrongCommandFormatException("What?");
	     }
	     else throw new WrongCommandFormatException("What?");
  return tmp; 
	
 }
		
 public void execute()throws CommandExecutionException{
		 Door auxd= map.getDoor(this.map.getCurrentRoom(), this.dir);
		 Room aux=this.map.getCurrentRoom() ;
		    if(auxd==null) throw new CommandExecutionException(Costants.MESSAGE_DOOR_NOT_EXIST);
		    else if(!auxd.isOpen()) throw new CommandExecutionException(Costants.MESSAGE_DOOR_CLOSED1+this.dir+Costants.MESSAGE_DOOR_CLOSED2);
		    else if(!auxd.isInRoom(this.map.getCurrentRoom()))throw new CommandExecutionException(Costants.MESSAGE_DOOR_NOT_EXIST);
		    else {
		    	Room nextRoom = auxd.nextRoom (aux);
			    this.map.setCurrentRoom(nextRoom);
			    if (this.map.getCurrentRoom() != aux) {
			      this.play.addHealth(Costants.LOST_LIVE);
			      this.map.playerChangeRoom(this.dir);
			      this.play.requestPlayerUpdate();
			    }
			}
 }
		
 public void configureContext(Game g, Map m, Player p){
	//Establecer el contexto de ejecución. El método recibe todo el juego (el juego, el mapa y el jugador)
	//a pesar de que la aplicación real de ejecución () no lo requiera.	 
	this.map=m;
	this.play=p;
 }
 
 public String getHelp(){
	return  "GO|IR { NORTH|EAST|SOUTH|WEST }";
 }
 
 public Directions getDirection(){
	 return this.dir;
		 
 }
 
}
