package tp.pr2;

public class Parser {
	
 //Atributos 
	static private Command tmp;
  	
 public Parser(){ 
	 tmp=new Command();
 };	
	
 public static Command nextCommand(String line){ //Analizar el siguiente comando en la cadena. Devuelve un comando.
   String []palabras=line.split(" ");
   if (palabras.length==1){
	   if (palabras[0].equalsIgnoreCase("HELP")) tmp=new Command(VerbCommands.HELP);
	   else if (palabras[0].equalsIgnoreCase("QUIT")) tmp=new Command(VerbCommands.QUIT);
	   else if (palabras[0].equalsIgnoreCase("LOOK")) tmp=new Command(VerbCommands.LOOK);
	   else if (palabras[0].equalsIgnoreCase("EXAMINE")) tmp= new Command(VerbCommands.EXAMINE);
	   else if (palabras[0].equalsIgnoreCase("USE")){ tmp=new Command(VerbCommands.USE,"");System.out.println("What?");}
	   else if (palabras[0].equalsIgnoreCase("PICK")){ tmp=new Command(VerbCommands.PICK,"");System.out.println("What?");}
	   else if (palabras[0].equalsIgnoreCase("GO")){ tmp=new Command(VerbCommands.GO);System.out.println("What?");}
	   else {tmp=new Command();System.out.println("What?");}
   }	   
   else if(palabras.length==2){
	   if (palabras[0].equalsIgnoreCase("GO")){
		   tmp=new Command(VerbCommands.GO);
		  if (palabras[1].equalsIgnoreCase("NORTH")) tmp=new Command(VerbCommands.GO,Directions.NORTH);
		  else if (palabras[1].equalsIgnoreCase("SOUTH")) tmp=new Command(VerbCommands.GO,Directions.SOUTH);
		  else if (palabras[1].equalsIgnoreCase("EAST")) tmp=new Command(VerbCommands.GO,Directions.EAST);
		  else if (palabras[1].equalsIgnoreCase("WEST")) tmp=new Command(VerbCommands.GO,Directions.WEST);
		  else System.out.println("What?");
	   }
	   else if(palabras[0].equalsIgnoreCase("PICK")) tmp=new Command(VerbCommands.PICK, palabras[1]);
	   else if(palabras[0].equalsIgnoreCase("LOOK")) tmp=new Command(VerbCommands.LOOK, palabras[1]);
	   else if(palabras[0].equalsIgnoreCase("USE")) tmp=new Command(VerbCommands.USE, palabras[1]);
	   else {
		   tmp=new Command();
		   System.out.println("What?");
	   }
   }
   else {
		  tmp=new Command();
		  System.out.println("What?");
		  } 
   return tmp;
 }
 
 public static String getHelp(){  //Devuelve información acerca de todos los comandos que entiende el juego
   String LINE_SEPARATOR = System.getProperty("line.separator");
   String aux=" You are lost. You are alone. You wander around."+ LINE_SEPARATOR +"Your command words are:"+ LINE_SEPARATOR +  "    GO { NORTH|SOUTH|EAST|WEST }" + LINE_SEPARATOR + "    HELP" + LINE_SEPARATOR + "    QUIT"+LINE_SEPARATOR+
   "    PICK <<id>>"+ LINE_SEPARATOR + "    USE <<id>>" + LINE_SEPARATOR + "    LOOK [<<id>>]" + LINE_SEPARATOR + "    EXAMINE";
   return aux;
 }
} 
