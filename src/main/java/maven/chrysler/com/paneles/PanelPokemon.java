package maven.chrysler.com.paneles;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import maven.chrysler.com.Proyecto1.Pokemon;
import maven.chrysler.com.Proyecto1.Tipo;
import necesarios.Modelo;

public class PanelPokemon extends JPanel implements ActionListener,ListSelectionListener {
	private JTextField tfNombre;
	private JTextField tfVida;
	private JTextField tfDanio;
	private JTextField textField_4;
	private BotonesCrud botones;
	public Modelo modelo;
	private JList list,listaPokemon;
	private DefaultListModel <Pokemon>mlist;
	private Pokemon pokemonSeleccionado;
	private boolean editar=false;
	private PanelAnadirTipos tiposPanel;
	public PanelPokemon(Modelo modelo) {
		setLayout(null);
		this.modelo = modelo;
		JLabel label = new JLabel("Panel Pokemon");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(172, 11, 95, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Tipo");
		label_1.setBounds(218, 36, 46, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("Nombre");
		label_2.setBounds(9, 49, 55, 14);
		add(label_2);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		tfNombre.setBounds(62, 46, 86, 20);
		add(tfNombre);
		
		JLabel lblVida = new JLabel("Vida");
		lblVida.setBounds(9, 90, 46, 14);
		add(lblVida);
		
		tfVida = new JTextField();
		tfVida.setBounds(62, 87, 86, 20);
		add(tfVida);
		tfVida.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Daño");
		lblNewLabel.setBounds(9, 127, 46, 14);
		add(lblNewLabel);
		
		tfDanio = new JTextField();
		tfDanio.setBounds(62, 124, 86, 20);
		add(tfDanio);
		tfDanio.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(307, 23, 143, 141);
		add(scrollPane);
		
		 listaPokemon = new JList();
		scrollPane.setViewportView(listaPokemon);
		mlist = new DefaultListModel<>();
		listaPokemon.setModel(mlist);
		listaPokemon.addListSelectionListener(this);
		textField_4 = new JTextField();
		
		textField_4.setBounds(339, 182, 111, 20);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setBounds(288, 185, 46, 14);
		add(lblBuscar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(280, 213, 170, 76);
		add(scrollPane_1);
		
		 list = new JList();
		scrollPane_1.setViewportView(list);
		botones = new BotonesCrud();
		botones.btnEditar.setLocation(99, 0);
		botones.btnGuardar.setLocation(99, 22);
		botones.btnEliminar.setLocation(0, 22);
		botones.btnNuevo.setLocation(0, 0);
		botones.setToolTipText("LUL\r\n");
		botones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		botones.setBackground(Color.BLUE);
		botones.setBounds(47, 198, 190, 67);
		add(botones);
		
		JButton btnNewButton = new JButton("ELIMINAR TODO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(100, 266, 164, 23);
		add(btnNewButton);
		 inicializar();
		 tiposPanel = new PanelAnadirTipos();
		 tiposPanel.setBounds(172,52, 106, 135);
		 add(tiposPanel);
	}
	public void inicializar() {
		botones.addListeners(this);
		actualizarPersonajes();
	}
	public void actionPerformed(ActionEvent e) {
	
		switch(e.getActionCommand()) {
			case "ELIMINAR":
				modelo.eliminar(pokemonSeleccionado);
				actualizarPersonajes();
				limpiar();
				break;
			case "GUARDAR":
				if(!editar)
					modelo.guardar(recogerDatos());
				else
					modelo.modificar(pokemonSeleccionado);
				editar = false;
				limpiar();
				actualizarPersonajes();
				break;
			case "EDITAR":
				editar=true;
				break;
			case "NUEVO":
				limpiar();
				tfNombre.grabFocus();
				break;
			case "CANCELAR":
				limpiar();
				break;
			default:
				System.out.println("error");
		}
		
	}
	 private void rellenarDatos(Pokemon pokemon){
	        tfNombre.setText(pokemon.getNombre());
	        tfVida.setText(String.valueOf(pokemon.getVida()));
	        tfDanio.setText(String.valueOf(pokemon.getDaño()));
	    }
	 public Pokemon recogerDatos(){
		 String nombre =tfNombre.getText();
			int vida= Integer.parseInt(tfVida.getText());
			int danio= Integer.parseInt(tfDanio.getText());
			Pokemon pokemon =new Pokemon();
			pokemon.setNombre(nombre);
			pokemon.setVida(vida);
			pokemon.setDaño(danio);
	        if(editar)
	            pokemon.setId(pokemonSeleccionado.getId());
	       pokemon.setTipo(tiposPanel.getListaTipos().get(0));
	        return pokemon;
	    }

	public void actualizarPersonajes() {
		limpiar();
		mlist.removeAllElements();
		for(Pokemon pokemon : modelo.getPokemones())
			mlist.addElement(pokemon);
	}
	public void limpiar() {
		tfNombre.setText("");
		tfVida.setText("");
		tfDanio.setText("");
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(listaPokemon.getSelectedIndex()==-1)
			return;
		pokemonSeleccionado =(Pokemon) listaPokemon.getSelectedValue();
		rellenarDatos(pokemonSeleccionado);
		tiposPanel.mlista.removeAllElements();
		tiposPanel.comboTipo.refrescar(modelo.getArmasLibres());
	}
}
