package tp.pr5.gui;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import tp.pr5.gui.MapCell;
import tp.pr5.Directions;
import tp.pr5.MapObserver;
import tp.pr5.RoomInfo;

public class MapPanel extends javax.swing.JPanel implements MapObserver{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MapCell [][]  matriz;
	private JTextArea textArea;
	private int fila;
	private int columna;
	
	public MapPanel() {
		
		matriz = new MapCell[11][11];
		
		JPanel mapa = new JPanel();
		mapa.setBorder(new TitledBorder(null, "Map", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		for (int i=0;i < 11; i++){
			for(int j=0; j < 11;j++){
				matriz[i][j] = new MapCell();
				mapa.add(matriz[i][j]);
			}
		}
		
		JPanel room = new JPanel();
		room.setBorder(new TitledBorder(null, "Room", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(room, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(mapa, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(mapa, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(room, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
					.addContainerGap())
		);
		room.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 22, 851, 137);
		room.add(scrollPane);
		
	    textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		mapa.setLayout(new GridLayout(11, 11, 0, 0));
		setLayout(groupLayout);
		this.fila=5;
		this.columna=5;
		
	}

	 public void playerHasExaminedRoom(RoomInfo r){
		 //Notifica que el jugador ha examinado una habitación
		 textArea.setText(r.getDescription());
	 }
	
	public void roomEntered(Directions direction, final RoomInfo targetRoom) {

		this.matriz [this.fila][this.columna].setBackground(Color.LIGHT_GRAY);
		
		if (direction.equals(Directions.NORTH)) this.fila --;
		else if (direction.equals(Directions.EAST)) this.columna ++;
		else if (direction.equals(Directions.SOUTH)) this.fila ++;
		else if (direction.equals(Directions.WEST)) this.columna --;
	   
		this.entraRoom(targetRoom,this.fila,this.columna);
	}
	
	public void entraRoom(final RoomInfo targetRoom,int fil, int colum){
		
		this.matriz [fil][colum].setText(targetRoom.getName());
		this.matriz [fil][colum].setBackground(Color.GREEN);
		this.matriz [fil][colum].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(targetRoom.getDescription());
			}
		});
		textArea.setText(targetRoom.getDescription());
	}
	
}
	