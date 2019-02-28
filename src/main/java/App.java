import maven.chrysler.com.vista.Vista;
import necesarios.Controlador;
import necesarios.Modelo;

public class App {
	public static  void main(String[]args) {
		Vista vista = new Vista();
		Controlador control;
		Modelo modelo = new Modelo();
		control= new Controlador(vista,modelo);
	}

}
