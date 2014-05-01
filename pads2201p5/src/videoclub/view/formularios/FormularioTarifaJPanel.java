package videoclub.view.formularios;

import javax.swing.JTextField;

import videoclub.controller.Controlador;
import videoclub.view.MiJClass.JSpinnerOnlyNumbers;

public class FormularioTarifaJPanel extends Formulario {

	private static int MAX_VAL = 1000;

	/* Componentes del formulario */
	private JTextField         	nombre             	= new JTextField(10);
	private JSpinnerOnlyNumbers	duracion           	= new JSpinnerOnlyNumbers(30, 0, MAX_VAL, 1);
	private JSpinnerOnlyNumbers	precioTarifa       	= new JSpinnerOnlyNumbers(45, 0, MAX_VAL, 0.01);
	private JSpinnerOnlyNumbers	maxPrestamos       	= new JSpinnerOnlyNumbers( 3, 0, MAX_VAL, 1);
	private JSpinnerOnlyNumbers	duracionMusica     	= new JSpinnerOnlyNumbers( 3, 0, MAX_VAL, 1);
	private JSpinnerOnlyNumbers	duracionPelicula   	= new JSpinnerOnlyNumbers( 3, 0, MAX_VAL, 1);
	private JSpinnerOnlyNumbers	duracionSerie      	= new JSpinnerOnlyNumbers( 3, 0, MAX_VAL, 1);
	private JSpinnerOnlyNumbers	tasaMusica         	= new JSpinnerOnlyNumbers( 3, 0, MAX_VAL, 0.01);
	private JSpinnerOnlyNumbers	tasaPelicula       	= new JSpinnerOnlyNumbers( 3, 0, MAX_VAL, 0.01);
	private JSpinnerOnlyNumbers	tasaSerie          	= new JSpinnerOnlyNumbers( 3, 0, MAX_VAL, 0.01);
	private JSpinnerOnlyNumbers	duracionRetrasoLeve	= new JSpinnerOnlyNumbers(10, 0, MAX_VAL, 1);
	private JSpinnerOnlyNumbers	tasaRetrasoLeve    	= new JSpinnerOnlyNumbers( 1, 0, MAX_VAL, 0.01);
	private JSpinnerOnlyNumbers	tasaRetrasoGrave   	= new JSpinnerOnlyNumbers( 5, 0, MAX_VAL, 0.01);

	public FormularioTarifaJPanel(Controlador control) {

		super(control);

		this.añadirComponente("Nombre",                           	this.nombre             	);
		this.añadirComponente("Duración",                         	this.duracion           	);
		this.añadirComponente("Precio tarifa",                    	this.precioTarifa       	);
		this.añadirComponente("Préstamos máximos",                	this.maxPrestamos       	);
		this.añadirComponente("Duración alquiler música (días)",  	this.duracionMusica     	);
		this.añadirComponente("Duración alquiler película (días)",	this.duracionPelicula   	);
		this.añadirComponente("Duración alquiler serie (días)",   	this.duracionSerie      	);
		this.añadirComponente("Precio alquiler música (€)",       	this.tasaMusica         	);
		this.añadirComponente("Precio alquiler película (€)",     	this.tasaPelicula       	);
		this.añadirComponente("Precio alquiler serie (€)",        	this.tasaSerie          	);
		this.añadirComponente("Duracion retraso leve (días)",     	this.duracionRetrasoLeve	);
		this.añadirComponente("Tasa retraso leve (€)",            	this.tasaRetrasoLeve    	);
		this.añadirComponente("Tasa retraso grave (€)",           	this.tasaRetrasoGrave   	);

	}


	@Override
	protected void accion() {
		String	nombre             	= (String) this.nombre             	.getText();
		int   	duracion           	=    (int) this.duracion           	.getValue();
		double	precioTarifa       	= (double) this.precioTarifa       	.getValue();
		int   	maxPrestamos       	=    (int) this.maxPrestamos       	.getValue();
		int   	duracionMusica     	=    (int) this.duracionMusica     	.getValue();
		int   	duracionPelicula   	=    (int) this.duracionPelicula   	.getValue();
		int   	duracionSerie      	=    (int) this.duracionSerie      	.getValue();
		double	tasaMusica         	= (double) this.tasaMusica         	.getValue();
		double	tasaPelicula       	= (double) this.tasaPelicula       	.getValue();
		double	tasaSerie          	= (double) this.tasaSerie          	.getValue();
		int   	duracionRetrasoLeve	=    (int) this.duracionRetrasoLeve	.getValue();
		double	tasaRetrasoLeve    	= (double) this.tasaRetrasoLeve    	.getValue();
		double	tasaRetrasoGrave   	= (double) this.tasaRetrasoGrave   	.getValue();
		this.control.registrarTarifa(nombre, duracion, precioTarifa, maxPrestamos,
		                              duracionMusica, duracionPelicula, duracionSerie,
		                              tasaMusica, tasaPelicula, tasaSerie,
		                              duracionRetrasoLeve, tasaRetrasoLeve, tasaRetrasoGrave);
	}


}

