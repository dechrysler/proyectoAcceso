package maven.chrysler.com.paneles;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import maven.chrysler.com.Proyecto1.Tipo;
import necesarios.Modelo;

public class PanelTipo extends JPanel implements ActionListener,ListSelectionListener {
	private Tipo tipoSeleccionado;
	private JTextField tfNombre;
	private JTextField tfataqueEspecial;
	private JTextField tfDebilidad;
	public BotonesCrud botones;
	private Modelo modelo;
	private JList list ;
	public DefaultListModel<Tipo>mlistTipos;
	/**
	 * Create the panel.
	 */
	public PanelTipo(Modelo modelo) {
		setLayout(null);
		this.modelo = modelo;
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(6, 34, 46, 14);
		add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		tfNombre.setBounds(137, 31, 86, 20);
		add(tfNombre);
		
		tfataqueEspecial = new JTextField();
		tfataqueEspecial.setColumns(10);
		tfataqueEspecial.setBounds(137, 55, 86, 20);
		add(tfataqueEspecial);
		
		tfDebilidad = new JTextField();
		tfDebilidad.setColumns(10);
		tfDebilidad.setBounds(137, 84, 86, 20);
		add(tfDebilidad);
		
		JLabel lblAtaqueespecial = new JLabel("Ataque_especial");
		lblAtaqueespecial.setBounds(5, 58, 111, 14);
		add(lblAtaqueespecial);
		
		JLabel lblDebilidad = new JLabel("Debilidad");
		lblDebilidad.setBounds(6, 87, 66, 14);
		add(lblDebilidad);
		
		JLabel lblNewLabel = new JLabel("Panel Tipo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(172, 11, 103, 14);
		add(lblNewLabel);
		botones = new BotonesCrud();
		botones.setToolTipText("LUL\r\n");
		botones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		botones.setBackground(Color.BLUE);
		botones.setBounds(10, 184, 213, 71);
		add(botones);
		botones.btnEditar.setActionCommand("EDITAR");
		botones.btnGuardar.setActionCommand("GUARDAR");
		botones.btnEliminar.setActionCommand("ELIMINAR");
		botones.btnNuevo.setActionCommand("NUEVO");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(290, 34, 150, 141);
		add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		mlistTipos = new DefaultListModel<>(); 
		list.setModel(mlistTipos);
		inicializar();
		list.addListSelectionListener(this);
	}
	public void inicializar() {
		botones.addListeners(this);
		refrescarLista();
		botones.modoEdicion(false);
	}
	public void actionPerformed(ActionEvent e) {
	
		switch(e.getActionCommand()) {
			case "ELIMINAR":
				modelo.eliminar(tipoSeleccionado); 
				limpiar();
				refrescarLista();
				botones.modoEdicion(false);
				break;
			case "GUARDAR":
				String nombre =tfNombre.getText();
				String ataqueEspecial= tfataqueEspecial.getText();
				String debilidad= tfDebilidad.getText();
				Tipo tipo = new Tipo(); 
				tipo.setAtaque_especial(ataqueEspecial);
				tipo.setDebilidad(debilidad);
				tipo.setNombre(nombre);
				modelo.guardar(tipo);
				limpiar();
				refrescarLista();
				botones.modoEdicion(false);
				break;
			case "EDITAR":
				modelo.modificar(tipoSeleccionado);	
				limpiar();
				refrescarLista();
				botones.modoEdicion(true);
				break;
			case "NUEVO":
				limpiar();
				tfNombre.grabFocus();
				botones.modoEdicion(true);
				break;
			case "CANCELAR":
				limpiar();
				botones.modoEdicion(false);
				break;
				
		}
		
	}
	public void refrescarLista() {
		mlistTipos.removeAllElements();
		for(Tipo tipo : modelo.getTipo())
			mlistTipos.addElement(tipo);
	}
	
	private void limpiar() {
		tfNombre.setText("");
		tfataqueEspecial.setText("");
		tfDebilidad.setText("");
	}
	
	private void rellenarDatos() {
		tfNombre.setText(tipoSeleccionado.getNombre());
		tfataqueEspecial.setText(tipoSeleccionado.getAtaque_especial());
		tfDebilidad.setText(tipoSeleccionado.getDebilidad());
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(list.getSelectedIndex()==-1)
			return ;
		tipoSeleccionado = (Tipo)list.getSelectedValue();
		 rellenarDatos();
	}
	
}
