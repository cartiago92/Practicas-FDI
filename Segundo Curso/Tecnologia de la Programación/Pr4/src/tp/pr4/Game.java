package tp.pr4;
import java.util.*;

import tp.pr4.commands.Command;
import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;

public class Game {
	
//ATRIBUTOS
  private Map map;
  private Player player;
  private boolean fin;
	
//CONTRUCTOR
	 
  public Game(Map map){ //DEPENDE DE LOS ATRIBUTOS		
   this.map= map;
   this.player = new Player();
   this.fin=false;
  }
	
//METODOS
  
  public Map getCurrentMap(){
		 return this.map;
	 }
  
  public Player getPlayer(){
	 return this.player;
  }
  
  public void requestQuit(){ //Pide al juego para dejar de jugar. Es utilizado por el comando quit
	  this.fin=true;
  }
  
  public void runGame()throws CommandExecutionException,WrongCommandFormatException{ //Esta función implementa el bucle principal del juego. La cadena de entrada 
                          //se lee mediante el método de escáner sc = new Scanner (System.in); La cadena 
                           //obtenida en cada bucle debe ser analizada para obtener un comando que debe ser 
                                  //ejecutado Este método debe ser llamado una vez.
	  
	String line="";
	Command comm;
	Scanner sc=new Scanner(System.in);
	if (this.map==null) return;
	System.out.println(this.map.getCurrentRoom().getDescription()+"\nHEALTH = "+player.getHealth()+", SCORE ="+player.getPoints());
	
	 while (this.fin==false){
		System.out.print("> ");
		line= sc.nextLine();
		try{
		comm=Parser.parseCommand(line,this);
		comm.execute();
		}
		
		catch(WrongCommandFormatException e){
			System.out.println(e.getMessage());
	    }
	    
	    catch(CommandExecutionException e){
	        System.out.println(e.getMessage());
	    }
		
	  }
	String LINE_SEPARATOR = System.getProperty("line.separator");
	System.out.println("GAME OVER" +LINE_SEPARATOR+ " Thank you for playing, goodbye.\nHEALTH = "+player.getHealth()+", SCORE ="+player.getPoints());
  }
    
	
}