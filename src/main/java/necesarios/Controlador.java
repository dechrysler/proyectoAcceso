package necesarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import maven.chrysler.com.paneles.Login;
import maven.chrysler.com.paneles.PanelPokemon;
import maven.chrysler.com.paneles.PanelTipo;
import maven.chrysler.com.paneles.Login.Accion;
import maven.chrysler.com.vista.Vista;


public class Controlador implements ActionListener,ChangeListener{
	Vista vista;
	Modelo modelo;
	PanelPokemon pokemon ;
	PanelTipo tipo;
	public Controlador(Vista vista,Modelo modelo) {
		this.vista = vista;
		anadirListener();
		this.modelo = modelo;
		iniciarSesion();
	}
	
private void iniciarSesion() {	
		boolean autenticado = false;
		Login login = new Login();
		int intentos = 1;
		
		do {
			if(login.conectarBase()==Accion.SALIR)
				System.exit(0);
			String usuario = login.getUsuario();
			String contrasena = login.getContrasena();
			
			try {
				autenticado = modelo.iniciarSesion(usuario, contrasena);
				if (!autenticado) {
					if (intentos > 2) {
						Util.mensajeError("Limite de intentos superado");
						System.exit(0);
					}
					intentos++;
					continue;
				}
					
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				Util.mensajeError("No se ha podido conectar por algo");
			}
		} while (!autenticado);
	}
	public void anadirListener() {
		vista.mntmPokemon.addActionListener(this);
		vista.mntmTipo.addActionListener(this);
		vista.tabbedPane.addChangeListener(this);
		vista.salir.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("panel_pokemon")) {
			 pokemon = new PanelPokemon(modelo);
			vista.tabbedPane.addTab("Pokemon", pokemon);
		}
		else if(e.getActionCommand().equals("SALIR"))
			System.exit(0);
		else
		{
			 tipo = new PanelTipo(modelo);
			vista.tabbedPane.addTab("Tipo", tipo);
		}
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if(pokemon!=null) {
			pokemon.actualizarPersonajes();
			pokemon.count=0;}
		if(tipo!=null)
			tipo.refrescarLista();
	}
}
