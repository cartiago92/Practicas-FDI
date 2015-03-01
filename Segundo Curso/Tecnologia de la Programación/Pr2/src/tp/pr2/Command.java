package tp.pr2;

public class Command {
 //Atributos
 private VerbCommands verbCommand;
 private Directions direction;
 private String id;
	
 public Command(){
	this.verbCommand=VerbCommands.UNKNOWN;
	this.direction=Directions.UNKNOWN;
	this.id="";
 };
 
 public Command(VerbCommands verbCommand){   //Crea un nuevo comando sobre la base de un verbo
  this.verbCommand=verbCommand;
  this.direction=Directions.UNKNOWN;
  this.id="";
 }
 
 public Command(VerbCommands verbCommand, Directions direction){
  this.verbCommand=verbCommand;
  this.direction=direction;
 }
 
 public Command(VerbCommands verbCommand, String id){
  this.verbCommand=verbCommand;
  this.direction=Directions.UNKNOWN;
  this.id=id;
 }
	
 public Directions getDirection(){ //Retorna una direccion contenida en el comando
	return this.direction;
 }
		
 public VerbCommands getVerb(){ //Retorna un verbo contenido en el comando
	return this.verbCommand;
 }
	
 public String getIdItem(){ 
	return id;
 }
 
 public boolean isValid(){   //Comprueba si el comando es un comando válido
  	if (((this.verbCommand==VerbCommands.GO)&&(this.direction==Directions.UNKNOWN))||
  	   ((this.direction==Directions.UNKNOWN)&&(this.verbCommand==VerbCommands.UNKNOWN))||
  	   ((this.verbCommand==VerbCommands.PICK)&&(this.id.equalsIgnoreCase("")))||
  	   ((this.verbCommand==VerbCommands.USE)&&(this.id.equalsIgnoreCase("")))) return false;
  	else return true;
 }
	
 /*public void setDirection(Directions direction){  //Establece la dirección del comando	
	this.direction=direction;
 }*/
	
}
