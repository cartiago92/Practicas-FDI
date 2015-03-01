package tp.pr5;


public abstract class GameController {
 
 protected Game game;

 public GameController(Game game){
	 this.game=game;
 }
 
 public abstract void runGame();
	 

 public void registerGameObserver(GameObserver gameObserver){
	 //registra el GameObserver en el modelo
	 this.game.addGameObserver(gameObserver);
	 
 }
 
 public void registerMapObserver(MapObserver mapObserver){
	//registra el MapObserver en el modelo 
	 this.game.addMapObserver(mapObserver);
 }
 
 public void registerPlayerObserver(PlayerObserver playerObserver){
	//registra el PlayerObserver en el modelo
	 this.game.addPlayerObserver(playerObserver);
 }
 
}
