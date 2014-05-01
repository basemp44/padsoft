package videoclub.view.formularios;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import videoclub.controller.Controlador;

public class FormularioSocioJPanel extends Formulario {

	/* Componentes del formulario */
	private JTextField nombre   	= new JTextField(10);
	private JTextField dni      	= new JTextField(10);
	private JTextField telefono 	= new JTextField(10);
	private JTextField direccion	= new JTextField(10);
	private JTextField email    	= new JTextField(10);

	public FormularioSocioJPanel(Controlador control) {
		super(control);

		this.añadirComponente("Nombre (*)",   	this.nombre);
		this.añadirComponente("Dni (*)",      	this.dni);
		this.añadirComponente("Teléfono (*)", 	this.telefono);
		this.añadirComponente("Dirección (*)",	this.direccion);
		this.añadirComponente("E-mail",       	this.email);
	}

	@Override
	protected void accion() {
		String nombre    = this.nombre.getText();
		String dni       = this.dni.getText();
		String telefono  = this.telefono.getText();
		String direccion = this.direccion.getText();
		String email     = this.email.getText();

		String uid = this.control.registrarSocio(nombre, dni, telefono, direccion, email);
		JOptionPane.showMessageDialog(null, "Se ha registrado el usuario, su uid es " + uid);
		this.resetearFormulario();
	}

}
