package videoclub.view.formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import videoclub.controller.Controlador;
import videoclub.view.MiJClass.MiJPanel;
import videoclub.view.MiJClass.SpringUtilities;

class ParBusqueda extends MiJPanel {
	private JTextField  id     = new JTextField(10);
	private JButton     buscar = new JButton("Buscar");
	private Controlador control;
	ParBusqueda(Controlador control) {
		this.control = control;

		// Layout
		this.setLayout(new SpringLayout());

		// Componentes
		this.add(id);
		this.add(buscar);
		SpringUtilities.makeCompactGrid(
			this,   	// Contenedor
			1, 2,   	// Filas y cols
			20, 20, 	// initX, initY
			20, 20);	// xPad, yPad (separación x e y)
		
		// Configuracion
		this.id.setEnabled(false);
		final ParBusqueda self = this;
		this.buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				long resultado = self.control.buscarArticulo();
				self.id.setText(new Long(resultado).toString());
			}
		});
	}
}
