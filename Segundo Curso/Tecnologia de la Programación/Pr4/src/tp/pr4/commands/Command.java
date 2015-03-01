package tp.pr4.commands;
import tp.pr4.Game;
import tp.pr4.commands.exceptions.*;

public interface Command {
 //METODOS
	public void execute()throws CommandExecutionException;
		//Ejecuta el comando. Se debe implementar en cada subclase abstracta no.
	public String getHelp();
	//Devuelve una descripción de la sintaxis de comandos. La cadena no termina con
	//el separador de línea. Es hasta la persona que llama añadiendo que antes de imprimir.

	public Command parse(String cad, Game execContext)throws WrongCommandFormatException;
	//Analizadores de la cadena de devolver una instancia de su subclase correspondiente,
	//si la cadena se ajusta la sintaxis del comando. De lo contrario, se produce una WrongCommandFormatException.
	//Cada subclase abstracta no debe poner en práctica su correspondiente análisis sintáctico

}
	

