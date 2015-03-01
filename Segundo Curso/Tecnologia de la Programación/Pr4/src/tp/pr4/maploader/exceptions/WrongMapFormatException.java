package tp.pr4.maploader.exceptions;
import java.io.*;

public class WrongMapFormatException extends IOException{
	private static final long serialVersionUID = 1L;

public WrongMapFormatException(){
  super();
 }
 
 public WrongMapFormatException(String arg0){	 
  super(arg0);	
  
 }
 
 public WrongMapFormatException(String arg0, java.lang.Throwable arg1){
	 
  super(arg0,arg1);
 }
 
 public WrongMapFormatException(java.lang.Throwable arg0){
	 
  super(arg0); 
 }
 
}
