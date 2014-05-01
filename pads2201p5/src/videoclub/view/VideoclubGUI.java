package videoclub.view;
//Copia en conflicto que no se borrará

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.jdesktop.swingx.JXFrame;

import videoclub.controller.Controlador;
import videoclub.view.MiJClass.MiJPanel;
import videoclub.view.formularios.BusquedaBuscadorJPanel;
import videoclub.view.formularios.FormularioContratarTarifa;
import videoclub.view.formularios.FormularioDevolverJPanel;
import videoclub.view.formularios.FormularioLoginJPanel;
import videoclub.view.formularios.FormularioMusicaJPanel;
import videoclub.view.formularios.FormularioPeliculaJPanel;
import videoclub.view.formularios.FormularioPrestamoJPanel;
import videoclub.view.formularios.FormularioSerieJPanel;
import videoclub.view.formularios.FormularioSocioJPanel;
import videoclub.view.formularios.FormularioTarifaJPanel;
import videoclub.view.formularios.MorososJPanel;
import videoclub.view.formularios.Top10JPanel;



/**
 * JFrame de la aplicacion
 */
public class VideoclubGUI extends JXFrame {

	// Paneles de tabs
	JTabbedPane tabsPrincipal = new JTabbedPane();

	/* Layouts */
	private CardLayout layoutGerente = new CardLayout();
	private MiJPanel   panelGerente;   // Creado posteriormente

	JDialog dialogo_busqueda = new JDialog(this, true);
	BusquedaBuscadorJPanel formulario_busqueda;

	Controlador control;

	public VideoclubGUI(Controlador control) {
		super("Videoclub");
		this.control = control;
		this.inicializarTabs(control);
		this.inicializarMenu();
		this.inicializarFrame();
		this.crearDialogoBusqueda();

		this.logout();
	}

	private void inicializarFrame() {
		// Caracteristicas
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				control.cerrar();
			}
		});
		this.setResizable(true);
		this.setVisible(true);
		this.pack();

		int x = 800; //Math.max(800, (int)this.getSize().getWidth());
		int y = 600; //Math.max(600, (int)this.getSize().getHeight());

		this.setSize(new Dimension(x, y));
		this.setMinimumSize(new Dimension(x, y));
		// cambiando el icono del Jframe
		this.setIconImage(new ImageIcon("data/icon.png").getImage());
	}

	private void inicializarTabs(Controlador control) {

		Top10JPanel              	top10                      	= new Top10JPanel(control);
		MorososJPanel            	morosos                    	= new MorososJPanel(control);
		FormularioLoginJPanel    	formulario_login           	= new FormularioLoginJPanel(control);
		FormularioMusicaJPanel   	formulario_musica          	= new FormularioMusicaJPanel(control);
		FormularioPeliculaJPanel 	formulario_pelicula        	= new FormularioPeliculaJPanel(control);
		FormularioSerieJPanel    	formulario_serie           	= new FormularioSerieJPanel(control);
		FormularioSocioJPanel    	formulario_socio           	= new FormularioSocioJPanel(control);
		FormularioTarifaJPanel   	formulario_tarifa          	= new FormularioTarifaJPanel(control);
		FormularioPrestamoJPanel 	formulario_prestamo        	= new FormularioPrestamoJPanel(control);
		FormularioDevolverJPanel 	formulario_devolver 		= new FormularioDevolverJPanel(control);
		FormularioContratarTarifa	formulario_contratar_tarifa	= new FormularioContratarTarifa(control);

		// Tabs Panel principal
		this.getContentPane().setLayout(new BorderLayout());
		this.add(this.tabsPrincipal);

		this.panelGerente = this.panelIntermedio(this.tabsPrincipal, "Gerente");
		this.panelGerente.setLayout(this.layoutGerente);

		MiJPanel gerenteNoLogged	= new MiJPanel();
		MiJPanel gerenteLogged  	= new MiJPanel();

		panelGerente.add(gerenteNoLogged, "NoLogged");
		panelGerente.add(gerenteLogged,     "Logged");

		gerenteNoLogged.setLayout(new BorderLayout());
		gerenteLogged.setLayout(new BorderLayout());

		// Panel Gerente No Logged
		gerenteNoLogged.add(formulario_login);

		// Tabs Panel Gerente Logged
		JTabbedPane	tabGerente 	= gerenteLogged.añadir(new JTabbedPane());
		JTabbedPane	tabCatalogo	= this.panelIntermedio(this.tabsPrincipal, "Catalogo" ).añadir(new JTabbedPane());
		JTabbedPane	tabSocios  	= this.panelIntermedio(this.tabsPrincipal, "Socios"   ).añadir(new JTabbedPane());

		this.panelIntermedio(tabGerente, "Top 10"          ).add(top10);
		this.panelIntermedio(tabGerente, "Morosos"         ).add(morosos);
		this.panelIntermedio(tabGerente, "Gestión tarifas" ).add(new JScrollPane(formulario_tarifa));

		// Tabs Panel Catalogo
		this.panelIntermedio(tabCatalogo, "Devolver" ).add(formulario_devolver);
		this.panelIntermedio(tabCatalogo, "Prestar").add(formulario_prestamo);
		JTabbedPane tabCrearArticulo = this.panelIntermedio(tabCatalogo, "Añadir").añadir(new JTabbedPane());

			// Tabs Panel AñadirArticulo
			CardCombo cc = this.panelIntermedio(tabCrearArticulo, "Añadir nuevo artículo").añadir(new CardCombo());
				cc.añadirPanel("Musica", formulario_musica);
				cc.añadirPanel("Pelicula", formulario_pelicula);
				cc.añadirPanel("Sere", formulario_serie);
			this.panelIntermedio(tabCrearArticulo, "Añadir copia a artículo existente");


		this.panelIntermedio(tabSocios, "Nuevo socio"   ).add(formulario_socio);
		this.panelIntermedio(tabSocios, "Añadir tarifas").add(formulario_contratar_tarifa);
	}

	private MiJPanel panelIntermedio(JTabbedPane tabs, String label) {
		MiJPanel panel = new MiJPanel();
		panel.setLayout(new BorderLayout());
		tabs.add(label, panel);
		return panel;
	}

	private void inicializarMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = menuBar.add(new JMenu("Fichero"));
			JMenu fileOpen = (JMenu)file.add(new JMenu("Nuevo"));
				fileOpen.add(new JMenuItem("Musica"));
				fileOpen.add(new JMenuItem("Pelicula"));
				fileOpen.add(new JMenuItem("Serie"));
				fileOpen.add(new JMenuItem("Socio"));
				fileOpen.add(new JMenuItem("Tarifa"));
			file.add(new JMenuItem("Cerrar"));
			file.addSeparator();
			file.add(new JMenuItem("Guardar"));
		this.setJMenuBar(menuBar);
	}

	public void login(boolean exitoso) {
		if (exitoso) {
			this.layoutGerente.show(this.panelGerente, "Logged");
			return;
		}
		this.layoutGerente.show(this.panelGerente, "NoLogged");
		JOptionPane.showMessageDialog (null, "Login incorrecto!", "Logon message", JOptionPane.WARNING_MESSAGE);

	}

	public void logout() {
		this.layoutGerente.show(this.panelGerente, "NoLogged");
	}
	
	private void crearDialogoBusqueda() {
		this.formulario_busqueda = new BusquedaBuscadorJPanel(this.control);
		this.dialogo_busqueda.add(this.formulario_busqueda);
		this.dialogo_busqueda.pack();
		this.dialogo_busqueda.setLocationByPlatform(true);
		
		//TODO
		//this.panelIntermedio(this.tabsPrincipal, "Busqueda" ).add(formulario_busqueda);
	}


	public void mostrarBusqueda() {
		this.dialogo_busqueda.setVisible(true);
	}

	public void mostrarPago(String uid, double precio) {
		int opcion = JOptionPane.showConfirmDialog(null, "Uid: "+ uid + " Precio: " + precio, "Confirmar pago", JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			int opcion2 = JOptionPane.showConfirmDialog(null, "¿Pagar con tarjeta?" + precio, "Confirmar pago", JOptionPane.QUESTION_MESSAGE);
			if (opcion2 == JOptionPane.YES_OPTION) {
				JOptionPane.showConfirmDialog(null, "No se aceptan tarjetas de crédito" + precio, "Confirmar pago", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Pago realizado con éxito");
			}
		}
	}
}

