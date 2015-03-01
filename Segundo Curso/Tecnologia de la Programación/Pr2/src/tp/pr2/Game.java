package tp.pr2;
import java.util.*;
import tp.pr2.Command;

public class Game {
	
//ATRIBUTOS
  private Room initialRoom;
  private Map map;
  private Player player;
	
//CONTRUCTORA
  public Game(){
   this.initialRoom=null;
   this.map=new Map();
   this.player=new Player();
  }
	 
  public Game(Map map, Room initialRoom){ //DEPENDE DE LOS ATRIBUTOS		
   this.map= map;
   this.initialRoom = initialRoom;
   this.player = new Player();
  }
	
//METODOS
  protected void changeRoom(Directions dir){   //Trata de cambiar la habitación donde se encuentra el jugador, moverlo a otra habitación. Después de eso,
	if(dir != Directions.UNKNOWN){   
		 Door d= map.getDoor(initialRoom, dir);//se imprime la descripción de la habitación.
	  if((d != null)&&(d.isOpen())){        //Si no hay habitación en esta dirección, el jugador permanece en la misma habitación y un mensaje de error.
		this.initialRoom = map.getDoor(initialRoom,dir).nextRoom(initialRoom);
		this.player.addHealth(Costants.LOST_LIVE);
		if (!initialRoom.isExit())
		System.out.println(initialRoom.getDescription());
		}
	  else System.out.println(Costants.MESSAGE_WAY_CLOSED + dir);
    }	
  }
	
  public Room getCurrentRoom(){     //Devuelve una referencia a la habitación donde se encuentra el jugador que se encuentre.
	return this.initialRoom; }       //Este método se emplea sólo por las pruebas unitarias
	
  public void setCurrentRoom(Room currentRoom){
	this.initialRoom=currentRoom;}
	
  protected boolean processCommand(Command command){
	VerbCommands verb=command.getVerb();
	Directions dir= command.getDirection();
	String idcomm=command.getIdItem();
    boolean fin=false; 
	 if (command.isValid()){  
		 
	   if (verb==VerbCommands.PICK){
		  Item it2= this.player.getItem(idcomm);
		   if(!this.initialRoom.existsItem(idcomm))System.out.println(Costants.MESSAGE_PICK_ERROR);
		   else if(it2!=null)System.out.println(Costants.MESSAGE_ITEM_IS_IN_INVENTARY1+idcomm+Costants.MESSAGE_ITEM_IS_IN_INVENTARY2);
		   else {initialRoom.pickItem(player, idcomm); }	
			   
	   }
		    
	   if(verb==VerbCommands.GO){
		  Door auxd= map.getDoor(initialRoom, dir);
		    if(auxd==null) System.out.println("What the hell am I supposed to do going to "+dir+" ?");
		    else if(!auxd.isOpen()) System.out.println(Costants.MESSAGE_DOOR_CLOSED1+dir+Costants.MESSAGE_DOOR_CLOSED2);
		    else if(!auxd.isInRoom(initialRoom))System.out.println(Costants.MESSAGE_DOOR_NOT_EXIST);
		    else {System.out.println(Costants.MESSAGE_CHANGE_ROOM+dir);
		    	  this.changeRoom(dir);
		    	  if (!initialRoom.isExit())
		    	  System.out.println("HEALTH = "+player.getHealth()+", SCORE ="+player.getPoints());
		    	  }
	   }
			  
	   if(verb==VerbCommands.LOOK){
		    if(idcomm=="") System.out.println(Costants.MESSAGE_ITEMS+"\n"+player.showItems());  	
		    else if(player.getItem(idcomm)==null)System.out.println("There is no "+idcomm+" in your inventory.");
	    	else System.out.println(player.getItem(idcomm).toString());		
	   }
		    
	   if(verb==VerbCommands.USE){
		   Item auxit=player.getItem(idcomm);	
		    if(auxit==null) System.out.println(Costants.MESSAGE_NO_ITEM + idcomm +".");
		    else if(auxit.canBeUsed()){
		    	   if(auxit.use(player, initialRoom)){
		    		 System.out.println(Costants.MESSAGE_CHANGES);
		    		  if(!auxit.canBeUsed()){ 
		    			 player.removeItem(idcomm);
				    	 System.out.println("The "+idcomm+Costants.MESSAGE_DELETE_INVENTORY);
		    		  }
		    	   }
		    	 else System.out.println(Costants.MESSAGE_DONT_KNOW_USE);
		    	 }
	   }
		    
	   if(verb==VerbCommands.HELP) System.out.println(Parser.getHelp());   
	   
	   if(verb==VerbCommands.EXAMINE) System.out.println(initialRoom.getDescription());
		    
	   if ((verb==VerbCommands.QUIT)||(this.initialRoom.isExit())||(this.player.dead())) fin=true;
	}
	return fin;
		//cierto , si a la ejecución del comando termina el juego. falsa de lo contrario
	}
	
	public void runGame(){
	//Bucle principal del juego	
		boolean fin=false;
		String line="";
		Command comm;
		Scanner sc=new Scanner(System.in);
		System.out.println(this.initialRoom.getDescription()+"\nHEALTH = "+player.getHealth()+", SCORE = "+player.getPoints());
		while (fin==false){
			System.out.print("> ");
			line= sc.nextLine();
			comm=Parser.nextCommand(line);
			fin=this.processCommand(comm);
		}
		String LINE_SEPARATOR = System.getProperty("line.separator");
		System.out.println("GAME OVER" +LINE_SEPARATOR+ " Thank you for playing, goodbye.\nHEALTH = "+player.getHealth()+", SCORE ="+player.getPoints());
	 }
	
	
	
}