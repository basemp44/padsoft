package videoclub.view.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import videoclub.controller.Controlador;
import videoclub.view.MiJClass.JSpinnerOnlyNumbers;

public class FormularioDevolverJPanel extends Formulario {
	
	private final JTextField  	socio 	 			= new JTextField(10);
	private final JSpinnerOnlyNumbers 		precio 	= new JSpinnerOnlyNumbers();
	protected String 			ACCION_PRINCIPAL    = "Devolver";

	public FormularioDevolverJPanel(Controlador control) {
		super(control);
		precio.setEnabled(true);
		this.añadirComponente("Socio", this.socio);
		this.añadirComponente("Precio", this.precio);
		this.añadirBoton("Calcular", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evd) {
				String socio 	= ((FormularioDevolverJPanel) self).socio.getText();
				precio.setValue(self.control.devolver(socio));	
			}
		});
		
		this.añadirBoton("Devolver", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evd) {
				String socio 	= ((FormularioDevolverJPanel) self).socio.getText();
				double price 	= ((Integer)precio.getValue()).doubleValue();
				((FormularioDevolverJPanel) self).control.devolver(socio, price);
				((FormularioDevolverJPanel) self).precio.setValue(0);
			}
		});
	}
	
	@Override
	protected void rellenarBotonera() {	}

	@Override
	protected void accion() { }



}
