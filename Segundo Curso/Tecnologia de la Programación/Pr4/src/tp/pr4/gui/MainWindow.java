package tp.pr4.gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tp.pr4.Game;
import tp.pr4.Map;
import tp.pr4.maploader.MapLoaderFromTxtFile;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	//private Game juego;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					String filename="C:/hlocal/Pr4/src/Mapa.txt";
					MapLoaderFromTxtFile loader=new MapLoaderFromTxtFile();
					Map m=loader.loadMap(new FileInputStream(filename));
					Game g=new Game(m);
					MainWindow frame = new MainWindow(g);
					frame.setLocation(70, 50);
					frame.setVisible(true);
					//g.runGame();
					//System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow(Game theGame) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 868, 627);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("File");
		menuBar.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel actions = new JPanel();
		actions.setBorder(new TitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		PlayerPanel player = new PlayerPanel();
		theGame.getPlayer().setPlayerPanel(player);
		
		MapPanel map = new MapPanel();
		map.setSize(100, 100);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(map, GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(actions, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(player, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(actions, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(player, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(map, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		player.setLayout(null);
		actions.setLayout(null);
		
		JButton button_go = new JButton("GO");
		button_go.setBounds(10, 23, 101, 22);
		actions.add(button_go);
		
		JButton button_pick = new JButton("PICK");
		button_pick.setBounds(10, 47, 101, 22);
		actions.add(button_pick);
		
		JButton button_drop = new JButton("DROP");
		button_drop.setBounds(10, 72, 101, 22);
		actions.add(button_drop);
		
		JButton btnNewButton = new JButton("USE");
		btnNewButton.setBounds(114, 72, 93, 23);
		actions.add(btnNewButton);
		
		JButton button_quit = new JButton("QUIT");
		button_quit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				final JFrame frameq=new JFrame();
				frameq.setBounds(100, 100, 214, 102);
				frameq.setLocation(400, 250);
				contentPane = new JPanel();
				frameq.setContentPane(contentPane);
				contentPane.setLayout(null);
				
				frameq.setVisible(true);
				
				JLabel mensaje = new JLabel("\u00BFDeseas Salir?");
				mensaje.setFont(new Font("Tahoma", Font.BOLD, 11));
				mensaje.setBounds(66, 3, 83, 20);
				contentPane.add(mensaje);
				
				JButton btnSi = new JButton("SI");
				btnSi.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						System.exit(1);
					}
				});
				btnSi.setBounds(46, 34, 58, 23);
				contentPane.add(btnSi);
				
				JButton btnNo= new JButton("NO");
				btnNo.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
					    frameq.setVisible(false);
					}
				});
				btnNo.setBounds(108, 34, 58, 23);
				contentPane.add(btnNo);
			}	
		});
		
		button_quit.setBounds(10, 98, 101, 22);
		actions.add(button_quit);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"NORTH", "SOUTH", "EAST", "WEST"}));
		comboBox.setBounds(114, 24, 93, 20);
		actions.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(114, 48, 93, 20);
		actions.add(textField);
		textField.setColumns(10);
		
		contentPane.setLayout(gl_contentPane);
		
						
	}
}
