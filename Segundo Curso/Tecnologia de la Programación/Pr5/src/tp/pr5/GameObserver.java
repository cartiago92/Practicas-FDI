package tp.pr5;


public interface GameObserver {

 public void gameStart(RoomInfo initialRoom, int playerPoints, int playerHealth);
	 //Avisa de que empiece el juego. Se proporciona el estado inicial del juego
 
 public void gameError(java.lang.String msg);
	 //Notifica que el juego no puede ejecutar un comando
 
 public void gameHelp();
	 //Notifica que las solicitudes de información ayudan a los jugadores
 
 public void gameOver(boolean win);
	 //Notifica que el juego se termina y si el jugador gana o es la muerte
 
 public void gameQuit();
	 //Notifica las peticiones de jugadores para salir del juego.
}
