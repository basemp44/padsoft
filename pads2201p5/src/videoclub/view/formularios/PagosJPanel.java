package videoclub.view.formularios;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import videoclub.controller.Controlador;
import videoclub.view.MiJClass.JSpinnerOnlyNumbers;

public class PagosJPanel extends Formulario {

	/* Componentes del formulario */

	private JTextField         	numTarjeta 	= new JTextField(12);
	private JPasswordField     	password   	= new JPasswordField(4);
	private JTextField         	usuario    	= new JTextField(10);
	private JSpinnerOnlyNumbers	precio     	= new JSpinnerOnlyNumbers(0, 0, Double.POSITIVE_INFINITY, 0.01);
	private JTextField         	descripcion	= new JTextField(20);

	public PagosJPanel(Controlador control) {
		super(control);

		this.añadirComponente("Número de tarjeta",	this.numTarjeta 	);
		this.añadirComponente("Contraseña",       	this.password   	);
		this.añadirComponente("Usuario",          	this.usuario    	);
		this.añadirComponente("Precio",           	this.precio     	);
		//this.añadirComponente("Contraseña",     	this.contraseña 	);
		this.añadirComponente("Descripcion",      	this.descripcion	);
	}

	@Override
	protected void accion() {
		/*
		String comando = e.getActionCommand();
		if (comando.equals("Pagar")) {

		} else if (comando.equals("Cancelar")) {

		}
		*/
	}

}