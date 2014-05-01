package videoclub.view.formularios;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import videoclub.controller.Controlador;
import videoclub.view.MiJClass.JSpinnerOnlyNumbers;
import videoclub.view.MiJClass.MiJList;

public class FormularioPeliculaJPanel extends Formulario {

	/* Componentes del formulario */
	private JTextField         	titulo          	= new JTextField(10);
	private JTextField         	director        	= new JTextField(10);
	private JSpinnerOnlyNumbers	fechaPublicacion	= new JSpinnerOnlyNumbers(2000, 0, Integer.MAX_VALUE, 1);
	private MiJList            	categorias      	= new MiJList();

	public FormularioPeliculaJPanel(Controlador control) {
		super(control);
		
		for (String s : control.getCategoriasMusica()) {
			this.categorias.addElement(s);
		}

		this.añadirComponente("Título (*)",        this.titulo);
		this.añadirComponente("Director",          this.director);
		this.añadirComponente("Fecha publicación", this.fechaPublicacion);
		this.añadirComponente("Categorías",        new JScrollPane(this.categorias));
	}

	@Override
	protected void accion() {
		String      	titulo          	= this.titulo.getText();
		String      	director        	= this.director.getText();
		int         	fechaPublicacion	= (int)this.fechaPublicacion.getValue();
		List<String>	categorias      	= this.categorias.getSelectedValuesList();
		
		this.control.registrarPelicula(titulo, director, fechaPublicacion, categorias);
	}

}

