package videoclub.view.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import videoclub.controller.Controlador;
import videoclub.view.MiJClass.JSpinnerOnlyNumbers;

public class FormularioQuitarCopias extends Formulario {

	private final ParBusqueda articulo;
	private JComboBox<String> formato = new JComboBox<>();
	private JSpinnerOnlyNumbers numCopias = new JSpinnerOnlyNumbers(1, 1, Integer.MAX_VALUE, 1);

	public FormularioQuitarCopias(Controlador control) {
		super(control);
		this.articulo = new ParBusqueda(control);
		this.añadirComponente("Articulo 1", this.articulo);
		this.añadirComponente("Articulo 2", this.formato);
		this.añadirComponente("Articulo 3", this.numCopias);
	}
	
	@Override
	protected void rellenarBotonera() {
		this.añadirBoton("Quitar", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				self.accion();
			}
		});
	}

	@Override
	protected void accion() {
		// Tratar de alquilar
		//String socio_uid   = this.socio.getText();
		//String articulo_id = this.articulo.getText();
		//this.control.prestamo(socio, articulo);
	}
}