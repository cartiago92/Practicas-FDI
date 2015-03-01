package tp.pr5.commands.exceptions;

public class WrongCommandFormatException extends Exception {
	
	//CONTRUTORAS
	private static final long serialVersionUID = -3609259991472296446L;

  public WrongCommandFormatException(){  //Constructor sin parámetros (no aparece ningún mensaje dado)
    super();
  }
	
  public WrongCommandFormatException(String arg0){  //La excepción que se crea con un mensaje de problema.
	super(arg0);
  }
		
  public WrongCommandFormatException(Throwable arg0){   //Constructor para crear la excepción con una causa anidada.
    super(arg0);	
  }
		
  public WrongCommandFormatException(String arg0, Throwable arg1){   //Constructor para crear la excepción anidada con una causa y un mensaje de error.
	super(arg0,arg1);
  }

}
