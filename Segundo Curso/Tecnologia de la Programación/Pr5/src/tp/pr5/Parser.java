package tp.pr5;

import tp.pr5.commands.*;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class Parser {

//METODOS
	
 public static Command parseCommand(String line)throws WrongCommandFormatException{
	 //line: cadena k contiene el comando
     //executeContext: juego en el k se analiza una cadena
	 Command aux;  
	 Command []comandos=
	 {  new GoCommand(),
	    new DropCommand(),
	    new ExamineCommand(),
	    new HelpCommand(),
	    new LookCommand(),
	    new PickCommand(),
	    new QuitCommand(),
	    new UseCommand() };

	for(int i=0;i<comandos.length;i++){
		try{
			aux=comandos[i];
			aux=aux.parse(line);
			return aux;
		 }
	 
		catch(Exception e){
		 }
	}
	throw new WrongCommandFormatException("What?");
		  
 }
	
	
 public static String getHelp(){  //Devuelve información acerca de todos los comandos que entiende el juego
 
	 Command aux;  
	 String LINE_SEPARATOR = System.getProperty("line.separator");
	 String help="You are lost. You are alone. You wander around"+ LINE_SEPARATOR +"Your command words are:";
	 Command []comandos=
	 {  new ExamineCommand(),
	    new GoCommand(),
	    new HelpCommand(),
	    new LookCommand(),
	    new PickCommand(),
	    new DropCommand(),
	    new QuitCommand(),
	    new UseCommand() };

	for(int i=0;i<comandos.length;i++){
			aux=comandos[i];
			help=help + LINE_SEPARATOR +"   "+ aux.getHelp();
		 }
	return help+ LINE_SEPARATOR;
 }
} 
