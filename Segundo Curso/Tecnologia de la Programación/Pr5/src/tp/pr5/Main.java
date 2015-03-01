package tp.pr5;

import jargs.gnu.CmdLineParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.console.Console;
import tp.pr5.console.GameControllerConsole;
import tp.pr5.gui.GameControllerGUI;
import tp.pr5.gui.MainWindow;
import tp.pr5.maploader.MapLoaderFromTxtFile;
import tp.pr5.maploader.exceptions.WrongMapFormatException;

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
	
	private static void printUsage() {
        System.err.println(
       "java tp.pr4.Main [-i,--interface] [-m,--map] ");
        //-i swing -m "C:\Users\Carlos\Desktop\eclipse\Workspace\Pr5\src\Mapa.txt"
    }

	 public static void main(String[] args) throws CommandExecutionException, WrongCommandFormatException, FileNotFoundException, WrongMapFormatException{ 
		 
        CmdLineParser parser = new CmdLineParser();
        CmdLineParser.Option interfaz = parser.addStringOption('i', "interface");
        CmdLineParser.Option mapa = parser.addStringOption('m', "map");

        try {
			 parser.parse(args);
		 }
	
        catch ( CmdLineParser.OptionException e ) {
            System.err.println(e.getMessage());
            printUsage();
            System.exit(2);
        }

        String interfazValue = (String)parser.getOptionValue(interfaz);
        final String mapaValue = (String)parser.getOptionValue(mapa);
        
        String [] aux=new String[args.length-3];;
	    	for (int i=3;i<args.length;i++)
	    	aux[i-3]=args[i];
		if(aux.length!=1){
			 System.err.println("No map file specified.\n");
			 System.err.println("Usage: tp.pr5.Main <mapFile>");
			 System.exit(1);
		 }
		
        try{
           MapLoaderFromTxtFile loader=new MapLoaderFromTxtFile();
	       Map m=loader.loadMap(new FileInputStream(mapaValue));
		   final Game g=new Game(m);
        
    		if (interfazValue.equalsIgnoreCase("console")){
    		 GameControllerConsole control=new GameControllerConsole(g);
    		 Console consola= new Console();
   			 control.registerGameObserver(consola);
   			 control.registerMapObserver(consola);
   			 control.registerPlayerObserver(consola);
   			 control.runGame();
   			 System.exit(0);
   			}
    		
   		    else if (interfazValue.equalsIgnoreCase("swing")){
   		    	
   		    	GameControllerGUI controlador = new GameControllerGUI(g);
				MainWindow frame = new MainWindow(controlador);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				controlador.runGame();
   		
   		    }
    		
   		 else if (interfazValue.equalsIgnoreCase("both")){
				GameControllerGUI controlador = new GameControllerGUI(g);
				MainWindow frame = new MainWindow(controlador);
				Console consola = new Console();
				controlador.registerGameObserver(consola);
				controlador.registerMapObserver(consola);
				controlador.registerPlayerObserver(consola);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				controlador.runGame();
			}
    		
   		    else System.exit(1);
        }
   	    catch(Exception e){
   		     throw new WrongMapFormatException("Error:The file will not charge",e);
   	     }      
        
        /*String[] otherArgs = parser.getRemainingArgs();

        System.out.println("interface: " + interfazValue);
        System.out.println("map: " + mapaValue);

        System.out.println("remaining args: ");
        for ( int i = 0; i < otherArgs.length; ++i ) {
            System.out.println(otherArgs[i]);
        }*/
	 }	
  }

 
  



