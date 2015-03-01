package tp.pr5.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tp.pr5.Directions;
import tp.pr5.Game;
import tp.pr5.GameObserver;
import tp.pr5.Map;
import tp.pr5.RoomInfo;
import tp.pr5.maploader.MapLoaderFromTxtFile;
import tp.pr5.maploader.exceptions.WrongMapFormatException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.io.FileInputStream;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame implements GameObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JPanel contentPane;
	static JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void main(final String args) throws WrongMapFormatException {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
						 try{
						 String filename=args;
							MapLoaderFromTxtFile loader=new MapLoaderFromTxtFile();
							Map m=loader.loadMap(new FileInputStream(filename));
							final Game g=new Game(m);
							
							final GameControllerGUI control=new GameControllerGUI(g);
							
							MainWindow frame = new MainWindow(control);
							frame.setLocation(250, 50);
							frame.setVisible(true);
						
						 }
				 catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow(final GameControllerGUI control) {
		
		final PlayerPanel player= new PlayerPanel();;
		final MapPanel map= new MapPanel();; 
		map.setSize(100, 100);
		
		control.registerGameObserver(this);
		control.registerMapObserver(map);
		control.registerPlayerObserver(player);
		
		final InfoPanel gui= new InfoPanel();
		
		control.registerGameObserver(gui);
		control.registerMapObserver(gui);
		control.registerPlayerObserver(gui);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 649);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		//OPCION QUIT DEL MENU
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.executeQuitAction();
			}
		});
		menu.add(mntmQuit);
		
	//PANEL DE ACCIONES
		JPanel actions = new JPanel();
		actions.setBorder(new TitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(actions, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(player, GroupLayout.PREFERRED_SIZE, 624, GroupLayout.PREFERRED_SIZE))
						.addComponent(map, GroupLayout.PREFERRED_SIZE, 864, Short.MAX_VALUE)
						.addComponent(gui, GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(player, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
						.addComponent(actions, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(map, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(gui, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		player.setLayout(null);
		actions.setLayout(null);
		
		//COMBOBOX DONDE ESTARAN LAS DIRECCIONES
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "NORTH", "SOUTH", "EAST", "WEST"}));
		comboBox.setBounds(114, 24, 93, 20);
		actions.add(comboBox);
		
		//TEXTFIELD DONDE SE ESCRIBIRA UN OBJETO YA SE PARA COGER SOLTAR O USAR
		textField = new JTextField();
		textField.setBounds(114, 48, 93, 20);
		actions.add(textField);
		textField.setColumns(10);
		
		
		//BOTON GO
		JButton button_go = new JButton("GO");
		button_go.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				Directions direccion;
				if (comboBox.getSelectedIndex() == 0) direccion = Directions.NORTH;
				else if (comboBox.getSelectedIndex() == 1) direccion = Directions.SOUTH;
				else if (comboBox.getSelectedIndex() == 2) direccion = Directions.EAST;
				else direccion = Directions.WEST;
				control.executeGoAction(direccion); 
			}
		});
		button_go.setBounds(10, 23, 101, 22);
		actions.add(button_go);
		
		//BOTON PICK
		JButton button_pick = new JButton("PICK");
		button_pick.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String nam=textField.getText().toString();
				control.executePickAction(nam); 
			}
		});
		button_pick.setBounds(10, 47, 101, 22);
		actions.add(button_pick);
		
		//BOTON DROP
		JButton button_drop = new JButton("DROP");
		button_drop.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String nam=textField.getText().toString();
				control.executeDropAction(nam); 
			}
		});
		button_drop.setBounds(10, 72, 101, 22);
		actions.add(button_drop);
		
		//BOTON USE
		JButton btnNewButton = new JButton("USE");
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String nam=textField.getText().toString();
				control.executeUseAction(nam); 
			}
		});
		btnNewButton.setBounds(114, 72, 93, 23);
		actions.add(btnNewButton);
		
		//BOTON QUIT
		JButton button_quit = new JButton("QUIT");
		button_quit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				control.executeQuitAction();
			}	
		});
		
		button_quit.setBounds(10, 98, 101, 22);
		actions.add(button_quit);
		
		contentPane.setLayout(gl_contentPane);
		
		//SELECCIONAR ALGUN ITEM DEL PLAYERPANEL Y QUE LO ESCRIBA EN EL EXTFIELD
        JTable tabaux = player.getTable();
		tabaux.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String s = player.getSelectedItem();
				textField.setText(s);
			}
		});		
	}
	
	
	
 public void gameStart(RoomInfo initialRoom, int playerPoints, int playerHealth){
		 //Avisa de que empiece el juego. Se proporciona el estado inicial del juego
	 JOptionPane.showConfirmDialog(null, "             Game Starts", "Welcome", JOptionPane.CLOSED_OPTION);
 }
 
 public void gameError(String msg){
	 //Notifica que el juego no puede ejecutar un comando
	 JOptionPane.showMessageDialog(null, msg, "Error",JOptionPane.YES_OPTION );
 }
 
 public void gameHelp(){
	 //Notifica que las solicitudes de información ayudan a los jugadores
 }
 
 public void gameOver(boolean win){
	 //Notifica que el juego se termina y si el jugador gana o es la muerte
	 if (win) JOptionPane.showConfirmDialog(null, "    Congratulations, you win", "Game Over", JOptionPane.CLOSED_OPTION);
		else  JOptionPane.showConfirmDialog(null, "You are dead, better luck next time", "Game Over", JOptionPane.CLOSED_OPTION);

 }
 
 public void gameQuit(){
	 int respuesta = JOptionPane.showConfirmDialog(null, "Like to leave?", "Exit", JOptionPane.YES_NO_OPTION);
		switch(respuesta) {
		case JOptionPane.YES_OPTION:
			System.exit(0);
		break;
		}
 }
}
