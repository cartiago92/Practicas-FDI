package tp.pr5.gui;
import tp.pr5.Directions;
import tp.pr5.Game;
import tp.pr5.GameController;
import tp.pr5.commands.Command;
import tp.pr5.commands.DropCommand;
import tp.pr5.commands.GoCommand;
import tp.pr5.commands.PickCommand;
import tp.pr5.commands.QuitCommand;
import tp.pr5.commands.UseCommand;

public class GameControllerGUI extends GameController{
 
 public GameControllerGUI(Game game){
	//constructor que usa el modelo
	 super(game);
 }
 
 public void runGame(){
  //Este metodo solo requiere el juego para empezar
	 game.requestStart();
	 while (!this.game.isOver());
 }
 
 public void executeUseAction(String itemName) {
	 //ejecuta el comando USE
	 Command com= new UseCommand(itemName);
	 game.executeCommand(com);
 }
 
 public void executeDropAction(String item) {
    //ejecuta un comando DROP
	 Command com= new DropCommand(item);
	 game.executeCommand(com);
 }
 
 public void executePickAction(String item) {
	 //ejecuta un comando PICK
	 Command com= new PickCommand(item);
	 game.executeCommand(com);
 }
 
 public void executeQuitAction() {
	 //ejecuta un comando QUIT
	 Command com= new QuitCommand();
	 game.executeCommand(com);
 }
 
 public void executeGoAction(Directions direction) {
	 //ejecuta un comando QUIT
	 Command com= new GoCommand(direction);
	 game.executeCommand(com);
 }
}
