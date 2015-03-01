package tp.pr5.gui;

import java.util.List;

import tp.pr5.Directions;
import tp.pr5.GameObserver;
import tp.pr5.MapObserver;
import tp.pr5.PlayerObserver;
import tp.pr5.RoomInfo;
import tp.pr5.items.Item;
import javax.swing.JLabel;

public class InfoPanel extends javax.swing.JPanel implements GameObserver, MapObserver, PlayerObserver{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	final JLabel pan;
	
 public InfoPanel(){
	 pan=new JLabel();
	 pan.setToolTipText("");
	add(pan);
 	
 }

	public void gameError(String msg) { // Muestra que el juego no puede ejecutar un comando
		pan.setText(msg);
	}
	
	public void gameHelp() { // Muestra qe el jugador ha pedido ayuda
	}
	
	public void gameOver(boolean win) { // Muestra que el juego ha terminado, pone una cosa o otra segun si ha ganado o ha muerto
		if (win) pan.setText("Congratulations, you win");
		else pan.setText("You are dead, Game Over");
	}
	
	public void gameQuit() { // Muestra que el jugador ha pedido quitar el juego
	}
	
	public void gameStart(RoomInfo initialRoom, int playerPoints, int playerHealth) { // Notifica que el juego comienza
		pan.setText("Game Starts");		
	}
	
	public void inventoryUpdate(List<Item> inventory) { // Notifica que se ha actualizado el inventario
		pan.setText("The inventory now contains "+inventory.size()+" items");
	}
	
	public void itemEmpty(String itemName) { // Notifica que un item esta vacio
		pan.setText(itemName + " is Empty");
	}
	
	public void itemLooked(String description) { // Muestra la descripcion del item que el jugador ha mirado
	}
	
	public void itemUsed(String itemName) { // Muestra que un item ha sido usado
		pan.setText("You have used " + itemName);
	}
	
	public void playerDead() { // Notifica que el jugador ha muerto
		pan.setText("The player is dead");
	}
	
	public void playerHasExaminedRoom(RoomInfo r) { // Notifica que el jugador ha mirado una habitacion
	}
	
	public void playerLookedInventory(List<Item> inventory) { // Notifica que el jugador ha mirado el inventario
	}
	
	public void playerUpdate(int newHealth, int newScore) { // Notifica que la vida y salud del jugador han cambiado
		pan.setText("Player atributes has been updated: ("+newHealth+","+newScore+")");
	}
	
	public void roomEntered(Directions direction, RoomInfo targetRoom) { // Notifica que el jugador ha entrado en una habitacion y desde donde
		if (!direction.equals(Directions.UNKNOWN))
		pan.setText("The player has entered a room to the "+direction);
		
	}
		
}