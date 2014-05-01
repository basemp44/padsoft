package videoclub.view.formularios;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import videoclub.controller.Controlador;

public class FormularioContratarTarifa extends Formulario {

	private JTextField       	socio 		= new JTextField(10);
	private JComboBox<String>	tarifa		= new JComboBox<>();

	public FormularioContratarTarifa(Controlador control) {
		super(control);
		this.añadirComponente("Socio", 	socio);
		this.añadirComponente("Tarifa",	tarifa);
	}

	/**
	 * Coloca en el panel la lista de categorias
	 * @param categoriasValidas
	 * @return
	 */
	public void setCategorias(String[] categoriasValidas) {
		for (String s : categoriasValidas) this.tarifa.addItem(s);
	}

	@Override
	protected void accion() {
		String socio 	= this.socio.getText();
		String tarifa	= String.valueOf(this.tarifa.getSelectedItem());

		this.control.contratarTarifa(tarifa, socio);
	}
}