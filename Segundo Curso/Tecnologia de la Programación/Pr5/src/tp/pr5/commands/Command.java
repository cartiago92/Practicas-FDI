package tp.pr5.commands;

import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.*;

public interface Command {
 //METODOS
	public void execute()throws CommandExecutionException;
		//Ejecuta el comando. Se debe implementar en cada subclase abstracta no.
	public String getHelp();
	//Devuelve una descripci�n de la sintaxis de comandos. La cadena no termina con
	//el separador de l�nea. Es hasta la persona que llama a�adiendo que antes de imprimir.

	public Command parse(String cad)throws WrongCommandFormatException;
	//Analizadores de la cadena de devolver una instancia de su subclase correspondiente,
	//si la cadena se ajusta la sintaxis del comando. De lo contrario, se produce una WrongCommandFormatException.
	//Cada subclase abstracta no debe poner en pr�ctica su correspondiente an�lisis sint�ctico
	
	public void configureContext(Game g, Map m, Player p);
	//Establecer el contexto de ejecuci�n. El m�todo recibe todo el juego 
	//(el juego, el mapa y el jugador)
	//a pesar de que la aplicaci�n real de ejecuci�n () no lo requiera.	
		
	
	
}
	

