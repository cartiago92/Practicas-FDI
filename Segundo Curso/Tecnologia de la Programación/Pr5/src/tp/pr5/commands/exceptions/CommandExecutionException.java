package tp.pr5.commands.exceptions;

public class CommandExecutionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8445898765339219957L;

	//CONTRUCTORAS
	public CommandExecutionException(){
		super(); 
	//Constructor sin parámetros (no aparece ningún mensaje dado)
		
	}
	public CommandExecutionException(String arg0){
	//La excepción que se crea con un mensaje de problema.
	super(arg0);
		
	}
	
	public CommandExecutionException(Throwable arg0){
	//Constructor para crear la excepción con una causa anidada.
	super(arg0);	
	}
	
	public CommandExecutionException(String arg0, Throwable arg1){
		//Constructor para crear la excepción anidada con una causa y un mensaje de error.
		super(arg0,arg1);
	}
}
