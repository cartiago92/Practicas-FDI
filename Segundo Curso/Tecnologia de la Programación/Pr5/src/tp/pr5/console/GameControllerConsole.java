package tp.pr5.console;

import java.util.Scanner;

import tp.pr5.Game;
import tp.pr5.GameController;
import tp.pr5.Parser;
import tp.pr5.commands.Command;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class GameControllerConsole extends GameController {
	
	//ATRIBUTOS
	//CONTRUCTORA
	public GameControllerConsole(Game game) {
		super(game);
		
	}
	
	//METODOS
	
	public void runGame(){
	/* Esta funci�n implementa el bucle principal del juego. 
	 * La cadena de entrada se lee utilizando el m�todo de esc�ner sc = new Scanner (System.in);
	 * La cadena obtenida en cada bucle debe ser analizada para obtener un comando, 
	 * el cual ser� ejecutado por el juego. 
	 * Si la cadena de entrada tiene un formato incorrecto,
	 * este m�todo le solicitar� el juego para notificar que un error ha ocurrido 
	 * este m�todo debe ser llamado una vez.
	 */	
		
		String line="";
		Command comm;
		Scanner sc=new Scanner(System.in);
		
		this.game.requestStart();
		
		 while (this.game.isOver()==false){
			System.out.print("> \n");
			line= sc.nextLine();
			try{
			comm=Parser.parseCommand(line);
			this.game.executeCommand(comm);
			}
			
			catch(WrongCommandFormatException e){
				this.game.requestError(e.getMessage());
			}
		  }
		
	  }	
		
	

}
