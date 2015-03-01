package tp.pr4.gui;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.ScrollPaneConstants;



public class MapPanel extends javax.swing.JPanel{
	
	public MapPanel() {
		
		final MapCell [][] matriz = new MapCell[11][11];
		
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
				.addComponent(mapa, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
				.addComponent(room, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(mapa, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(room, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
					.addContainerGap())
		);
		room.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 22, 814, 137);
		room.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		mapa.setLayout(new GridLayout(11, 11, 0, 0));
		setLayout(groupLayout);
	}
}
