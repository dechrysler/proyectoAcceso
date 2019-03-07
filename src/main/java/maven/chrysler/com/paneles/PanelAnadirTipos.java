package maven.chrysler.com.paneles;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import maven.chrysler.com.Proyecto1.Tipo;
import necesarios.Modelo;

public class PanelAnadirTipos extends JPanel implements ActionListener{

	public JList list;
	private JButton anadir,eliminar;
	public DefaultListModel <Tipo>mlista;
	public JComboGenerico<Tipo> comboTipo;
	public PanelAnadirTipos() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 108, 93);
		add(scrollPane);
		
		 list = new JList();
		scrollPane.setViewportView(list);
		mlista = new DefaultListModel<>();
		list.setModel(mlista);
		
		anadir = new JButton("+");
		anadir.setBounds(0, 112, 41, 23);
		add(anadir);
		
		eliminar = new JButton("-");
		eliminar.setBounds(67, 112, 41, 23);
		add(eliminar);
		comboTipo = new JComboGenerico<>();
		comboTipo.setBounds(0, 91, 108, 20);
		comboTipo.setPreferredSize(new Dimension(100, 20));
		add(comboTipo);
		inicializar();
	}
	private void inicializar() {
		Modelo modelo = new Modelo();
		anadir.addActionListener(this);
		eliminar.addActionListener(this);
		refrescar();
	}
	
	public void anadirTipos(List<Tipo> tipos) {
		limpiar();
		for(Tipo tipin : tipos)
			mlista.addElement(tipin);
	}
	public List<Tipo> getListaTipos(){
		return(List<Tipo>) Collections.list(mlista.elements());
	}
	public void pokemonesLibres() {
		
	}
	public void refrescar() {
		Modelo modelo= new Modelo();
		mlista.removeAllElements();
		comboTipo.refrescar(modelo.getTipo());
	}
	public void limpiar() {
		mlista.removeAllElements();
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(anadir)) {
			 Tipo tipoSelect = comboTipo.getTipoSeleccionada();
			 if(tipoSelect== null)
				 return;
			 mlista.addElement(tipoSelect);
			 comboTipo.removeItem(tipoSelect);
			 comboTipo.setEnabled(false);
			 }
			 else if(event.getSource().equals(eliminar)) {
				 if(list.getSelectedIndex()==-1)
					 return ;
				 comboTipo.addItem((Tipo)mlista.remove(list.getSelectedIndex()));
				 mlista.clear();
				 comboTipo.setEnabled(true);
			 }
		}
	
	
}

