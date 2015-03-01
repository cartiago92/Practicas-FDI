package tp.pr4.gui;

import java.util.List;

import javax.swing.JPanel;

import tp.pr4.items.Item;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PlayerPanel extends JPanel {
	private JTable table;
	final JLabel health; 
	final JLabel score; 
	
	//CONSTRUCTOR
	
	public PlayerPanel (){
		setBorder(new TitledBorder(null, "Player Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		health = new JLabel("Healt: 100");
		health.setFont(new Font("Tahoma", Font.BOLD, 11));
		health.setBounds(253, 11, 62, 20);
		add(health);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 588, 77);
		add(scrollPane);
		
		table = new JTable();
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
		});
	
		DefaultTableModel modelo=(DefaultTableModel) table.getModel();
		for (int i=0;i<5;i++){
		 modelo.addRow(new Object[i]);   
		 table.setValueAt("juanito"+i,i , 0);
		 table.setValueAt("anita"+i,i , 1);
		} 
		
		scrollPane.setViewportView(table);
		
		score = new JLabel("Score: 0");
		score.setFont(new Font("Tahoma", Font.BOLD, 11));
		score.setBounds(322, 13, 45, 17);
		add(score);
		
	}
	
	public void updateInventory(List<Item> inventory){
	
	//Informa al panel sobre los cambios en el inventario del jugador
		DefaultTableModel modelo=(DefaultTableModel) table.getModel();
		for (int i=0;i<inventory.size();i++){
		 modelo.addRow(new Object[i]);   
		 table.setValueAt(inventory.get(i).getId(),i , 0);
		 table.setValueAt(inventory.get(i).getDescription(),i , 1);
		} 
	}
	
	public void updatePlayerHealth(int playerHealth){
		//Informa al panel sobre los cambios en la salud de los jugadores
		health.setName("Healt: "+ playerHealth);
	}
	
	public void updatePlayerScore(int playerScore){
		
	//Informa el panel sobre los cambios en la puntuación de jugador
		score.setName("Healt: "+ playerScore);
	}
	
	public String getSelectedItem(){
	//Devuelve el nombre del elemento seleccionado por el usuario sobre la tabla
		return "";
	}
}
