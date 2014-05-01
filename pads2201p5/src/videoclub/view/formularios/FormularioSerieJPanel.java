package videoclub.view.formularios;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import videoclub.controller.Controlador;
import videoclub.view.MiJClass.JSpinnerOnlyNumbers;
import videoclub.view.MiJClass.MiJList;

public class FormularioSerieJPanel extends Formulario {

	/* Componentes del formulario */
	private JTextField         	titulo    	= new JTextField(10);
	private JSpinnerOnlyNumbers	temporada 	= new JSpinnerOnlyNumbers(0, 0, Integer.MAX_VALUE, 1);
	private JSpinnerOnlyNumbers	volumen   	= new JSpinnerOnlyNumbers(0, 0, Integer.MAX_VALUE, 1);
	private MiJList            	categorias	= new MiJList();

	public FormularioSerieJPanel(Controlador control) {
		super(control);
		
		for (String s : control.getCategoriasMusica()) {
			this.categorias.addElement(s);
		}

		this.añadirComponente("Título (*)",	this.titulo);
		this.añadirComponente("Temporada", 	this.temporada);
		this.añadirComponente("Volumen",   	this.volumen);
		this.añadirComponente("Categorías",	new JScrollPane(this.categorias));
	}

	@Override
	protected void accion() {
		String      	titulo    	= this.titulo.getText();
		int         	temporada 	= (int)this.temporada.getValue();
		int         	volumen   	= (int)this.volumen.getValue();
		List<String>	categorias	= this.categorias.getSelectedValuesList();
		this.control.registrarSerie(titulo, temporada, volumen, categorias);
	}
}
