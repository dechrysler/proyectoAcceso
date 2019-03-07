package maven.chrysler.com.paneles;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JDialog implements ActionListener, KeyListener {

	private final JPanel contentPanel = new JPanel();
	public JTextField tfUsuario;
	public JPasswordField tfContrasena;
	public JLabel lblUsuario;
	public JLabel lblContrasena;
	public JButton btnEntrar;
	public JButton btnSalir;
	
	private String usuario;
	private String contrasena;

	public enum Accion{
		SALIR,
		CONECTAR
		
	}
	private Accion accion;
	public Login() {
		setBounds(100, 100, 311, 182);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(99, 22, 143, 26);
		contentPanel.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfContrasena = new JPasswordField();
		tfContrasena.setBounds(99, 59, 143, 26);
		contentPanel.add(tfContrasena);
		tfContrasena.setColumns(10);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(26, 28, 46, 14);
		contentPanel.add(lblUsuario);
		
		lblContrasena = new JLabel("Contrase\u00F1a");
		lblContrasena.setBounds(26, 65, 63, 14);
		contentPanel.add(lblContrasena);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEntrar = new JButton("Entrar");
				btnEntrar.setActionCommand("entrar");
				buttonPane.add(btnEntrar);
				getRootPane().setDefaultButton(btnEntrar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.setActionCommand("salir");
				buttonPane.add(btnSalir);
				

			}
		}
		
		tfContrasena.addKeyListener(this);
		tfUsuario.addKeyListener(this);
		btnEntrar.addActionListener(this);
		btnSalir.addActionListener(this);
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setModal(true);
	}
	public Accion conectarBase() {
		this.setVisible(true);
		return accion;
	}
	public String getUsuario() {return usuario;}
	
	public String getContrasena() {return contrasena;}
	public void activarVentana(){
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case "entrar":
			usuario = tfUsuario.getText();
			contrasena = String.valueOf(tfContrasena.getPassword());
			setVisible(false);
			accion=Accion.CONECTAR;
			setVisible(false);
			break;
		case "salir":
			accion=Accion.SALIR;
			setVisible(false);
			break;
		default:
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}
}
