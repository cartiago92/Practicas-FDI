package tp.pr5.gui;

import tp.pr5.Room;

public class MapCell extends javax.swing.JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6903627170813850047L;

 //ATRIBUTOS
	private Room room;
	
 //CONTRUCTORA
	
	public MapCell(){
		this.room = null;
	}
	
	public MapCell(Room room){
		this.room=room;
	}

	public Room getCell(){
		return this.room;
	}
	 
	public void setCell(Room inroom){
		this.room=inroom;
	}
	
}
