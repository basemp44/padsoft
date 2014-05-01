package videoclub.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.SwingUtilities;

import videoclub.model.Videoclub;
import videoclub.view.MiJClass.MiJFrame;
import videoclub.view.formularios.BuscadorMusicaJPanel;
import videoclub.view.formularios.BuscadorPeliculaJPanel;
import videoclub.view.formularios.BuscadorSerieJPanel;
import videoclub.view.formularios.FormularioMusicaJPanel;
import videoclub.view.formularios.FormularioSocioJPanel;
import videoclub.view.formularios.FormularioTarifaDiasextraJPanel;
import videoclub.view.formularios.FormularioTarifaEspecificaJPanel;
import videoclub.view.formularios.FormularioTarifaJPanel;

public class FormularioTest {
	public static void main(String[] args) {
		Runnable runner = new Runnable() {
			public void run() {
				
				int x = 0; int y = 0;
				
				// Ventana principal
				MiJFrame ventana = new MiJFrame("Videoclub", "data/icon.png");
				ventana.setResizable(true);
				
				/* descomentar el que se va a probar */
			
				//BuscadorMusicaJPanel p = new BuscadorMusicaJPanel();//TODO categorias
				//BuscadorPeliculaJPanel p = new BuscadorPeliculaJPanel();//TODO categorias
				//BuscadorSerieJPanel p = new BuscadorSerieJPanel();//TODO categorias

				//FormularioLoginJPanel p = new FormularioLoginJPanel();
				FormularioMusicaJPanel p = new FormularioMusicaJPanel();//TODO categorias
				//FormularioPeliculaJPanel p = new FormularioPeliculaJPanel();//TODO categorias
				//FormularioSerieJPanel p = new FormularioSerieJPanel();//TODO categorias
				//FormularioSocioJPanel p = new FormularioSocioJPanel();
				/*FormularioTarifaEstandarJPanel p = new FormularioTarifaEstandarJPanel("nombre",
						3, 3, 3,
                        2.5, 4.5, 3.5,
                        10, 1.0, 5.0);
				*/
				//FormularioTarifaDiasextraJPanel p = new FormularioTarifaDiasextraJPanel("nombre", 40, 4, 4, 4);
				//FormularioTarifaEspecificaJPanel p = new FormularioTarifaEspecificaJPanel("nombre", 30, 50.5, 5, 0, 0);
				
				Container c = ventana.getContentPane();
				c.setLayout (new BorderLayout());
				c.add(p,BorderLayout.CENTER);
				
				// Ajustando tamaños
				ventana.pack();
				ventana.setMinimumSize(new Dimension(Math.max(x, ventana.getSize().width), Math.max(y, ventana.getSize().height)));

			}
		};
		SwingUtilities.invokeLater(runner);		
	}
}
