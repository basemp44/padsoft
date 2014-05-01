package videoclub.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import videoclub.view.MiJClass.MiJPanel;


/**
 * Un CardCombo es una burda imitacion de JTabbedPane.
 * Contiene un JComboBox para seleccionar paneles en un CardLayout
 */
class CardCombo extends MiJPanel {

	private CardLayout       	cartas        	= new CardLayout(); 	// Manejador de lo que se muestra
	private JPanel           	panelContenido	= new MiJPanel();     	// Panel con los contenidos
	private JPanel           	panelSeleccion	= new MiJPanel();     	// Panel con el selector
	private JComboBox<String>	comboSelector 	= new JComboBox<>();	// Control selector

	public CardCombo() {
		this.setLayout(new BorderLayout());
		this.add(this.panelContenido, BorderLayout.CENTER);
		this.add(this.panelSeleccion, BorderLayout.NORTH);

		/* Panel del contenido */
		this.panelContenido.setLayout(cartas);

		/* Panel de seleccion */
		this.panelSeleccion.add(comboSelector);

		/* Listeners propios */
		comboSelector.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged (ItemEvent e) {
				String opcion = (String) e.getItem();
				cartas.show(panelContenido, opcion);
			}
		});
	}

	/**
	 * Añade un panel nuevo asociado al nombre dado.
	 * @param nombre Nombre del panel
	 * @param panel Panel en si
	 */
	protected void añadirPanel(String nombre, JPanel panel) {
		this.panelContenido.add(nombre, panel);
		this.comboSelector.addItem(nombre);
	}
}