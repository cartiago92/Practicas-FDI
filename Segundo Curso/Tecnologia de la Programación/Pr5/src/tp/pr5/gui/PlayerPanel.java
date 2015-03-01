package tp.pr5.gui;

import java.util.List;

import javax.swing.JPanel;

import tp.pr5.PlayerObserver;
import tp.pr5.items.Item;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PlayerPanel extends JPanel implements PlayerObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private int vida;
	private int puntos;
	final JLabel health; 
	final JLabel score; 
	
	//CONSTRUCTOR
	
	public PlayerPanel (){
		this.vida=100;
		this.puntos=0;
		setBorder(new TitledBorder(null, "Player Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		health = new JLabel("Healt: "+this.vida);
		health.setFont(new Font("Tahoma", Font.BOLD, 11));
		health.setBounds(243, 11, 62, 20);
		add(health);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 601, 91);
		add(scrollPane);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Description"
			}
		)/* {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		}*/);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(63);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(238);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(table);
		
		score = new JLabel("Score: "+ this.puntos);
		score.setFont(new Font("Tahoma", Font.BOLD, 11));
		score.setBounds(309, 13, 62, 17);
		add(score);
		
	}
	
	public String getSelectedItem(){
	//Devuelve el nombre del elemento seleccionado por el usuario sobre la tabla
		int fil;
		String elem="";
		
		if (table.getRowCount() > 0){
			fil=table.getSelectedRow();
			elem=""+table.getValueAt(fil,0 );
			return elem;
		}
		else return elem; 
	}
		
	//METODOS AUXILIARES
	public JTable getTable(){
		return this.table;
	}
	
	
	
	public void playerLookedInventory(List<Item> inventory){
		//Notifica que el jugador miraba el inventario
		this.inventoryUpdate(inventory);
	 }
	 
	 public void inventoryUpdate(List<Item> inventory){
		 //Notifica que el inventario del jugador ha sido cambiado
		 /*table.setCellSelectionEnabled(true);
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id", "Description"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
						false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			
			DefaultTableModel modelo=(DefaultTableModel) table.getModel();
			for (int i=0;i<inventory.size();i++){
				 modelo.addRow(new Object[i]);
				 table.setValueAt(inventory.get(i).getId(),i , 0);
				 table.setValueAt(inventory.get(i).getDescription(),i , 1);
			} 
			
	 }*/
		 table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Id", "Description"
					}
				) {
				
					private static final long serialVersionUID = 1L;
					boolean[] columnEditables = new boolean[] {
						false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
			for (int i = 0; i < inventory.size(); i++){
				((DefaultTableModel)table.getModel()).addRow(new Object[]{
			    	     inventory.get(i).getId(),
			    	     inventory.get(i).getDescription()}
			    	    );
			}}
	 
	 public void playerUpdate(int newHealth, int newScore){
	 	//Notifica que los atributos del jugador (la salud y la puntuación) han cambiado
		 vida=newHealth;
		 puntos=newScore;
		 health.setText("Healt: "+ this.vida);
		 score.setText("Score: "+ this.puntos);
	 }
	 
	 public void playerDead(){
		 //Notifica si el jugador esta muerto
	 }
	 
	 public void itemLooked(String description){
	 	//Notifica que el jugador desea mirar un item
	 }

	 public void itemUsed(String itemName){
		 //Notifica que un elemento se ha utilizado
		 
	 }
	 
	 public void itemEmpty(java.lang.String itemName){
		 //Notifica si el item esta vacio
	 }
	 
}
