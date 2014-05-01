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
 * Panel Top10
 */
public class Top10JPanel extends MiJPanel {

	private JTable	top10Tabla = new JTable();
	
	Controlador control;

	public Top10JPanel(Controlador control)  {
		this.control = control;
		
		this.setLayout(new BorderLayout());

		// Lamantabla
		this.top10Tabla.setAutoCreateRowSorter(true);
		this.add(new JScrollPane(top10Tabla), BorderLayout.NORTH);
		
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
		this.top10Tabla.setModel(this.control.getTop10());
	}
}
