package tp.pr5.commands.exceptions;

public class CommandExecutionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8445898765339219957L;

	//CONTRUCTORAS
	public CommandExecutionException(){
		super(); 
	//Constructor sin par�metros (no aparece ning�n mensaje dado)
		
	}
	public CommandExecutionException(String arg0){
	//La excepci�n que se crea con un mensaje de problema.
	super(arg0);
		
	}
	
	public CommandExecutionException(Throwable arg0){
	//Constructor para crear la excepci�n con una causa anidada.
	super(arg0);	
	}
	
	public CommandExecutionException(String arg0, Throwable arg1){
		//Constructor para crear la excepci�n anidada con una causa y un mensaje de error.
		super(arg0,arg1);
	}
}
