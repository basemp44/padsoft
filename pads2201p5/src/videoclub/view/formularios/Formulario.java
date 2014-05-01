package videoclub.view.formularios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

import videoclub.controller.Controlador;
import videoclub.view.MiJClass.MiJPanel;
import videoclub.view.MiJClass.SpringUtilities;

abstract public class Formulario extends MiJPanel {

	protected Controlador control;
	protected final Formulario self;

	private MiJPanel         formulario          = new MiJPanel(new SpringLayout());
	private MiJPanel         botonera            = new MiJPanel();
	private List<JComponent> componentes         = new ArrayList<>();
	private int              filas               = 0;
	protected String         ACCION_PRINCIPAL    = "Aceptar";


	protected Formulario(Controlador control) {

		this.self    = this;
		this.control = control;

		this.setLayout(new BorderLayout());
		this.add(formulario, BorderLayout.NORTH);
		this.add(botonera, BorderLayout.CENTER);
		this.rellenarBotonera();
	}

	abstract protected void accion();
	protected void cancelar() {
		this.resetearFormulario();
	}

	protected void añadirBoton(String etiqueta, ActionListener callb) {
		JButton boton = new JButton(etiqueta);
		boton.addActionListener(callb);
		botonera.add(boton);
	}

	protected void rellenarBotonera() {
		this.añadirBoton(ACCION_PRINCIPAL, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				self.accion();
			}
		});

		// Botton de cancelar: limpia todos los JComponentes
		this.añadirBoton("Cancelar", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				self.cancelar();
			}
		});
	}

	protected void resetearFormulario() {
		for (JComponent comp : this.componentes) {
			if (comp instanceof JTextField) {
				JTextField text = (JTextField) comp;
				text.setText("");
			}
			else if (comp instanceof JSpinner) {
				JSpinner spin = (JSpinner) comp;
				int min = (Integer) ((SpinnerNumberModel)spin.getModel()).getMinimum();
				spin.setValue(min);
			}
			else if (comp instanceof JList) {
				JList<?> list = (JList<?>) comp;
				list.clearSelection();
			}
		}
	}

	/**
	 * Añade un componente al formulario.
	 * @param texto Texto para la etiqueta
	 * @param componente Un componente asociado
	 */
	protected <T extends JComponent> T añadirComponente(String texto, T componente) {

		// Preparar etiqueta
		JLabel etiqueta = new JLabel(texto, JLabel.TRAILING);
		etiqueta.setLabelFor(componente);
		this.formulario.add(etiqueta);
		this.formulario.add(componente);

		this.filas++;
		this.componentes.add(componente);

		SpringUtilities.makeCompactGrid(
			this.formulario,	// Contenedor
			this.filas, 2,  	// Filas y cols
			20, 20,         	// initX, initY
			20, 20);        	// xPad, yPad (separación x e y)

		return componente;
	}
}
