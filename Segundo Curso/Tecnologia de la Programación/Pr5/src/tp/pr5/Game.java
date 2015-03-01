package tp.pr5;

import tp.pr5.commands.Command;
import tp.pr5.commands.exceptions.CommandExecutionException;


public class Game extends Observable<GameObserver>{
	
//ATRIBUTOS
  private Map map;
  private Player player;
	
//CONTRUCTOR
	 
  public Game(Map map){ //DEPENDE DE LOS ATRIBUTOS		
   super();
   this.map= map;
   this.player = new Player();
   
  }
 
//METODOS
  
  public void executeCommand(Command c) {
	/* Se ejecuta un comando e informa a los observadores si el juego ha terminado 
	 * después de ejecutar el comando. El comando se debe configurar con el contexto antes de ejecutarlo.
	 */
	  try{
	  c.configureContext(this,this.map, this.player);
	  c.execute();
	  
	  if (isOver())
		  if (this.player.getHealth() <= 0) {
				for (int i = 0; i < this.getObservers().size(); i++){
					this.getObservers().get(i).gameOver(false);
				}
			}
			else {
				for (int i = 0; i < this.getObservers().size(); i++){
					this.getObservers().get(i).gameOver(true);
				}
			}
	  }
	  
	  catch(CommandExecutionException e){
	    this.requestError(e.getMessage());}
  }
   
  public boolean isOver(){
	//Comprueba si el juego se acaba
	  return (this.player.dead() || this.map.getCurrentRoom().isExit());
  }
  
  public void requestQuit(){ //Pide al juego para dejar de jugar. Es utilizado por el comando quit
	  for (int i = 0; i < this.getObservers().size(); i++) {
			this.getObservers().get(i).gameQuit();
		}
  }
  
  public void requestHelp(){
	//Pide al juego de informar a los observadores acerca de la información de ayuda
	  for(int i=0; i < this.getObservers().size(); i++ ){
			this.getObservers().get(i).gameHelp();
			}
	  
  }
  
  public void requestError(String msg){
	//Pide al juego de informar a los observadores que se ha producido un error
	for(int i=0; i < this.getObservers().size(); i++ ){
		this.getObservers().get(i).gameError(msg);
	
		}
  }
  
   public void requestStart(){
	//Pide al juego de informar a los observadores de que comience la partida
	   for(int i=0; i < this.getObservers().size(); i++ ){
			this.getObservers().get(i).gameStart(this.map.getCurrentRoom(), this.player.getPoints(), this.player.getHealth());
		
			} 
	   
		for (int i = 0; i < this.map.getObservers().size(); i++) {
			this.map.getObservers().get(i).roomEntered(Directions.UNKNOWN, this.map.getCurrentRoom());
		}
  }
  
  public void addMapObserver(MapObserver observer){
	//Registra un MapObserver para el mapa que figura en el modelo de  
	this.map.addObserver(observer);  
  }
  
  public void removeMapObserver(MapObserver observer){
	//Elimina un MapObserver adjunta al mapa que figura en el modelo  
	this.map.deleteObserver(observer);  
  }
  
  public void addPlayerObserver(PlayerObserver observer){
	//Registra una PlayerObserver al jugador que figura en el modelo de  
	this.player.addObserver(observer);  
  }
  
  public void removePlayerObserver(PlayerObserver observer){
	 //Elimina un PlayerObserver conectado al reproductor que figura en el modelo
	  this.player.deleteObserver(observer);
  }
  
  public void addGameObserver(GameObserver observer){
	//Registers a GameObserver to the model  
	  this.addObserver(observer);
  }
  
  public void removeGameObserver(GameObserver observer){
	//Elimina un GameObserver unido al modelo  
	this.deleteObserver(observer);  
  }
  
}