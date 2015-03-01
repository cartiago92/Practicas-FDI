package tp.pr4.commands;
import tp.pr4.*;
import tp.pr4.commands.exceptions.*;

public class GoCommand implements Command{
  //Atributos
	private Directions dir;
	private Player play;
	private Map map;
	private Game game;
 
 public GoCommand(){
 }
 
 private GoCommand(Map mapa, Player playe, Directions dire,Game gam){
	 this.map=mapa;
	 this.play=playe;
	 this.dir=dire;
	 this.game=gam;
 }

 public Command parse(String cad, Game execContext)throws WrongCommandFormatException{
	String []palabras=cad.split(" ");
	Command tmp; 
	Map m=execContext.getCurrentMap();
	Player p=execContext.getPlayer();
	
		if(palabras.length==2){
		   if ((palabras[0].equalsIgnoreCase("GO"))||(palabras[0].equalsIgnoreCase("IR"))){
			  if (palabras[1].equalsIgnoreCase("NORTH")) tmp=new GoCommand(m,p,Directions.NORTH,execContext);
			  else if (palabras[1].equalsIgnoreCase("SOUTH")) tmp=new GoCommand(m,p,Directions.SOUTH,execContext);
			  else if (palabras[1].equalsIgnoreCase("EAST")) tmp=new GoCommand(m,p,Directions.EAST,execContext);
			  else if (palabras[1].equalsIgnoreCase("WEST")) tmp=new GoCommand(m,p,Directions.WEST,execContext);
			  else throw new WrongCommandFormatException("What?");
		   }
		   else throw new WrongCommandFormatException("What?");
	     }
	     else throw new WrongCommandFormatException("What?");
  return tmp; 
	
 }
 
 private void changeRoom(Directions dir)throws CommandExecutionException{  
	Room r=this.map.getCurrentRoom();
    Door d= this.map.getDoor(r, dir);
    
      if((d != null)&&(d.isOpen())){
		this.map.setCurrentRoom((d.nextRoom(r)));
		this.play.addHealth(Costants.LOST_LIVE);
		 if (!this.play.dead()&&(!this.map.getCurrentRoom().isExit()))System.out.println(d.nextRoom(r).getDescription());
	  }
	  else if (d==null)throw new CommandExecutionException(Costants.MESSAGE_DOOR_NOT_EXIST);
	  else if (!d.isOpen())throw new CommandExecutionException(Costants.MESSAGE_DOOR_CLOSED1+this.dir+Costants.MESSAGE_DOOR_CLOSED2);
   
 }	
  
		
 public void execute()throws CommandExecutionException{
		  Door auxd= map.getDoor(this.map.getCurrentRoom(), this.dir);
		  
		    if(auxd==null) throw new CommandExecutionException(Costants.MESSAGE_DOOR_NOT_EXIST);
		    else if(!auxd.isOpen()) throw new CommandExecutionException(Costants.MESSAGE_DOOR_CLOSED1+this.dir+Costants.MESSAGE_DOOR_CLOSED2);
		    else if(!auxd.isInRoom(this.map.getCurrentRoom()))throw new CommandExecutionException(Costants.MESSAGE_DOOR_NOT_EXIST);
		    else {System.out.println(Costants.MESSAGE_CHANGE_ROOM+dir);
		    	  changeRoom(this.dir);
		    	  if (this.map.getCurrentRoom().isExit())
		    	  this.game.requestQuit();
		    	  else System.out.println("HEALTH = "+this.play.getHealth()+", SCORE ="+this.play.getPoints());
		    	  }
 }
		
 public String getHelp(){
	return  "GO|IR { NORTH|EAST|SOUTH|WEST }";
 }
		
 public Directions getDirection(){
	 return this.dir;
		 
 }
 
}
