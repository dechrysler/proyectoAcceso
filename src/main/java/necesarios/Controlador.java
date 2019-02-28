package necesarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import maven.chrysler.com.paneles.PanelPokemon;
import maven.chrysler.com.paneles.PanelTipo;
import maven.chrysler.com.vista.Vista;


public class Controlador implements ActionListener{
	Vista vista;
	Modelo modelo;
	public Controlador(Vista vista,Modelo modelo) {
		this.vista = vista;
		anadirListener();
		this.modelo = modelo;
	}
	public void anadirListener() {
		vista.mntmPokemon.addActionListener(this);
		vista.mntmTipo.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("panel_pokemon")) {
			PanelPokemon pokemon = new PanelPokemon(modelo);
			vista.tabbedPane.addTab("Pokemon", pokemon);
		}
		else
		{
			PanelTipo tipo = new PanelTipo(modelo);
			vista.tabbedPane.addTab("Tipo", tipo);
		}
	}
}
