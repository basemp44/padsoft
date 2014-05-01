package videoclub.view.formularios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import videoclub.controller.Controlador;
import videoclub.view.MiJClass.MiJPanel;

/**
 * Panel Morosos
 */
public class MorososJPanel extends MiJPanel {

	private JTable 	morosos = new JTable();

	Controlador control;

	public MorososJPanel(Controlador control)  {
		this.control = control;
		
		this.setLayout(new BorderLayout());
		
		// Lamantabla
		this.morosos.setAutoCreateRowSorter(true);
		this.add(new JScrollPane(morosos), BorderLayout.NORTH);
		
		JButton actualizar = new JButton("Actualizar");
		actualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				accion();
			}
		});
		this.add(actualizar, BorderLayout.CENTER);
	}

	protected void accion() {
		this.morosos.setModel(this.control.getMorosos());
	}
}
