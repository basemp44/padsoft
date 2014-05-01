package videoclub.view.formularios;

import javax.swing.JTextField;

import videoclub.controller.Controlador;


public class FormularioPrestamoJPanel extends Formulario {

	private final JTextField  socio = new JTextField(10);
	private final ParBusqueda articulo1;
	private final ParBusqueda articulo2;
	private final ParBusqueda articulo3;

	public FormularioPrestamoJPanel(Controlador control) {
		super(control);
		this.articulo1 = new ParBusqueda(control);
		this.articulo2 = new ParBusqueda(control);
		this.articulo3 = new ParBusqueda(control);
		this.añadirComponente("Socio",      this.socio);
		this.añadirComponente("Articulo 1", this.articulo1);
		this.añadirComponente("Articulo 2", this.articulo2);
		this.añadirComponente("Articulo 3", this.articulo3);
	}

	@Override
	protected void accion() {
		// Tratar de alquilar
		//String socio_uid   = this.socio.getText();
		//String articulo_id = this.articulo.getText();
		//this.control.prestamo(socio, articulo);
	}
}
