package tp.pr4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import tp.pr4.commands.exceptions.CommandExecutionException;
import tp.pr4.commands.exceptions.WrongCommandFormatException;
import tp.pr4.maploader.MapLoaderFromTxtFile;
import tp.pr4.maploader.exceptions.WrongMapFormatException;

public class Main {

	 /*
	 * Ejemplo de mapa del enunciado.
	 * 
	|---------------------+---------------|
	| *Entrada*           | *Salida*      |
	| Agua                |               |
	| Carne en mal estado |               |
	|------  BID   -------+---- BID  -----|
	| *Pasillo*           | *Salón*       |
	| Llave puerta 3     UNID             |
	|                     | Moneda de oro |
	|---------------------+---------------|
	 * 
	 * 
	 * @author Marco Antonio
	 * @y.exclude*/
	

		/**
		 * Main method
		 * @throws WrongCommandFormatException 
		 * @throws CommandExecutionException 
		 * @throws IOException 
		 * @throws FileNotFoundException 
		 * @throws WrongMapFormatException 
		 */ 
  public static void main(String[] args) throws CommandExecutionException, WrongCommandFormatException, FileNotFoundException, WrongMapFormatException{
	  
	  
	 if(args.length!=1){
		 System.err.println("No map file specified.\n");
		 System.err.println("Usage: tp.pr3.Main <mapFile>");
		 System.exit(1);
	 }
	 else{
		 try{
		 String filename=args[0];/*"C:/Users/carlos/Desktop/TP3/Pr3/src/Mapa.txt"*/;
		 
			 MapLoaderFromTxtFile loader=new MapLoaderFromTxtFile();
			 Map m=loader.loadMap(new FileInputStream(filename));
			 Game g=new Game(m);
			 g.runGame();
			 System.exit(0);
		 }
		
	     catch(Exception e){
		     throw new WrongMapFormatException("Error:The file will not charge",e);
	     }      
	 }
  }

 }
  



