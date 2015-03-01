package tp.pr5.commands.exceptions;

public class WrongCommandFormatException extends Exception {
	
	//CONTRUTORAS
	private static final long serialVersionUID = -3609259991472296446L;

  public WrongCommandFormatException(){  //Constructor sin par�metros (no aparece ning�n mensaje dado)
    super();
  }
	
  public WrongCommandFormatException(String arg0){  //La excepci�n que se crea con un mensaje de problema.
	super(arg0);
  }
		
  public WrongCommandFormatException(Throwable arg0){   //Constructor para crear la excepci�n con una causa anidada.
    super(arg0);	
  }
		
  public WrongCommandFormatException(String arg0, Throwable arg1){   //Constructor para crear la excepci�n anidada con una causa y un mensaje de error.
	super(arg0,arg1);
  }

}
