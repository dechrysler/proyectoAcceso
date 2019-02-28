package maven.chrysler.com.paneles;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BotonesCrud extends JPanel {
	 public JButton btnNuevo,btnEditar,btnEliminar,btnGuardar,btnCancelar;
	 public BotonesCrud() {
		setLayout(null);
		
		 btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(6, 0, 89, 23);
		add(btnNuevo);
		
		 btnEditar = new JButton("Editar");
		btnEditar.setBounds(114, 0, 89, 23);
		add(btnEditar);
		
		 btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(6, 22, 89, 23);
		add(btnEliminar);
		
		 btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGuardar.setBounds(114, 22, 89, 23);
		add(btnGuardar);
		
		 btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(59, 45, 89, 23);
		add(btnCancelar);
		this.setVisible(true);
	}
	 public void addListeners(ActionListener listener) {
		 	btnEditar.setActionCommand("EDITAR");
			btnGuardar.setActionCommand("GUARDAR");
			btnEliminar.setActionCommand("ELIMINAR");
			btnNuevo.setActionCommand("NUEVO");
			btnCancelar.setActionCommand("CANCELAR");
			btnNuevo.addActionListener(listener);
			btnGuardar.addActionListener(listener);
			btnEditar.addActionListener(listener);
			btnEliminar.addActionListener(listener);
			btnCancelar.addActionListener(listener);
	}
	 public void modoEdicion(boolean edicion) {
		 btnNuevo.setEnabled(!edicion);
		 btnGuardar.setEnabled(edicion);
			btnEditar.setEnabled(!edicion);
			btnEliminar.setEnabled(!edicion);
	}
}
