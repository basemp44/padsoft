package videoclub;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import videoclub.controller.Controlador;
import videoclub.model.Videoclub;
import videoclub.view.VideoclubGUI;

public class Applicacion implements Runnable {

	@Override
	public void run() {

		// Cambiamos el Look and Feel
		try {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			// La de cosas que lanza esto ...
			// Ignoramos todas las excepciones del LaF porque
			// no afectan a la funcionalidad de la aplicacion.
		}

		// Casi tan elegante como abrir un fichero en Java. solo 3 news!
		Videoclub   	modelo     	= new Videoclub("file:data/db/jdl");
		Controlador 	controlador	= new Controlador(modelo);
		VideoclubGUI	vista      	= new VideoclubGUI(controlador);
		controlador.setVista(vista);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Applicacion());
	}
}
