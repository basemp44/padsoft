package videoclub.view.formularios;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import videoclub.controller.Controlador;
import videoclub.view.MiJClass.JSpinnerOnlyNumbers;
import videoclub.view.MiJClass.MiJList;

public class FormularioMusicaJPanel extends Formulario {

	/* Componentes del formulario */
	private JTextField         	titulo          	= new JTextField(10);
	private JTextField         	interprete      	= new JTextField(10);
	private JSpinnerOnlyNumbers	fechaPublicacion	= new JSpinnerOnlyNumbers(2000, 0, Integer.MAX_VALUE, 1);
	private MiJList            	categorias      	= new MiJList();

	public FormularioMusicaJPanel(Controlador control) {
		super(control);
		
		for (String s : control.getCategoriasMusica()) {
			this.categorias.addElement(s);
		}

		this.añadirComponente("Título (*)",        this.titulo);
		this.añadirComponente("Intérprete",        this.interprete);
		this.añadirComponente("Fecha publicación", this.fechaPublicacion);
		this.añadirComponente("Categorías",        new JScrollPane(this.categorias));
	}

	@Override
	protected void accion() {
		String      	titulo          	= this.titulo.getText();
		String      	interprete      	= this.interprete.getText();
		int         	fechaPublicacion	= (int)this.fechaPublicacion.getValue();
		List<String>	categorias      	= this.categorias.getSelectedValuesList();

		this.control.registrarMusica(titulo, interprete, fechaPublicacion, categorias);
	}

}
