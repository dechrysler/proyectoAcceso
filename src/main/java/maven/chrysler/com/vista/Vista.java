package maven.chrysler.com.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Panel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class Vista extends JFrame {

	public JPanel contenedor;
	public JMenu mnNewMenu,mnNewMenu_1;
	public JMenuItem salir,mntmPokemon,mntmTipo;
	public JTabbedPane tabbedPane;
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 424);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
	    salir = new JMenuItem("Salir");
	    salir.setActionCommand("SALIR");
		mnNewMenu.add(salir);
		
		mnNewMenu_1 = new JMenu("Editar");
		menuBar.add(mnNewMenu_1);
		
		mntmPokemon = new JMenuItem("Pokemon");
		mntmPokemon.setHorizontalAlignment(SwingConstants.CENTER);
		mntmPokemon.setActionCommand("panel_pokemon");
		mnNewMenu_1.add(mntmPokemon);
		
		
		mntmTipo = new JMenuItem("Tipo");
		mntmTipo.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_1.add(mntmTipo);
		mntmTipo.setActionCommand("panel_tipo");
		contenedor = new JPanel();
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedor);
		contenedor.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 497, 353);
		contenedor.add(tabbedPane);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
