package maven.chrysler.com.paneles;

import javax.swing.*;
import java.util.List;

public class JComboGenerico<T> extends JComboBox<T>{
	
	//Atributos
	private List<T> datos;
	
	//Constructor
	public JComboGenerico() {
		super(); //heredo todo
	}
	
	//Metodos
	public void inicializar(List<T> datos) {
		this.datos = datos;
		listar();
	}
	
	public void refrescar(List<T> tiposLibres) {
		limpiar();
		this.datos=tiposLibres;
		listar();
	}
	
	public void listar() {
		if(datos == null)
			return;
		for(T dato : datos)
			addItem(dato);
	}
	
	public void limpiar() {
		removeAllItems();
	}
	
	public T getTipoSeleccionada() {
		T dato = (T) getSelectedItem();
		return dato;
	}
}
