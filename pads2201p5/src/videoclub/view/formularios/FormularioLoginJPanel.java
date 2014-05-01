package videoclub.view.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import videoclub.controller.Controlador;

public class FormularioLoginJPanel extends Formulario {

	/* Componentes del formulario */
	private JTextField    	username	= new JTextField(10);
	private JPasswordField	password	= new JPasswordField(10);

	public FormularioLoginJPanel(Controlador control) {
		super(control);

		this.añadirComponente("Usuario",    this.username);
		this.añadirComponente("Contraseña", this.password);
	}

	@Override
	protected void rellenarBotonera() {
		this.añadirBoton("Login", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				self.accion();
			}
		});
	}

	@Override
	protected void accion() {
		String username = this.username.getText();
		char[] password = this.password.getPassword();
		
		this.control.login(username, password);
	}

}

