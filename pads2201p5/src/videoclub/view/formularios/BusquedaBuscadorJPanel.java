package videoclub.view.formularios;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SpringLayout;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import videoclub.controller.Controlador;
import videoclub.model.Predicado;
import videoclub.view.MiJClass.MiJPanel;
import videoclub.view.MiJClass.SpringUtilities;

public class BusquedaBuscadorJPanel extends MiJPanel implements ActionListener {

	private String[] nexos = { "AND", "OR", "XOR" };
	private Predicado componerNexo(String nexo, Predicado nuevo) {
		     if (("AND").equals(nexo)) return this.predicado.and(nuevo);
		else if (("OR" ).equals(nexo)) return this.predicado.or(nuevo);
		else if (("XOR").equals(nexo)) return this.predicado.xor(nuevo);
		return null;
	}
	Predicado predicado = Predicado.tautologico;

	CardLayout   	panelLayout       	= new CardLayout();
	MiJPanel     	busquedaYresultado	= new MiJPanel();
	MiJPanel     	busqueda          	= new MiJPanel();
	JTable       	resultado         	= new JTable();

	DefaultMutableTreeNode	raiz          	= new DefaultMutableTreeNode("Búsquedas añadidas");
	DefaultTreeModel      	modeloDatos   	= new DefaultTreeModel(raiz);
	JTree                 	arbolBusquedas	= new JTree(modeloDatos);
	int                   	indiceArbol   	= 0;

	/* == TITULO ==              	                	*/
	private JLabel               	tituloLabel     	= new JLabel("Título", JLabel.TRAILING);
	private JTextField           	tituloField     	= new JTextField(10);
	private JComboBox<String>    	tituloChoice    	= new JComboBox<>(nexos);
	private JButton              	tituloAdd       	= new JButton("Añadir");
	/* == TIPO ==                	                	*/
	private JLabel               	tipoLabel       	= new JLabel("Tipo", JLabel.TRAILING);
	private JComboBox<String>    	tipoField       	= new JComboBox<>(Predicado.Tipos);
	private JComboBox<String>    	tipoChoice      	= new JComboBox<>(nexos);
	private JButton              	tipoAdd         	= new JButton("Añadir");
	/* == CATEGORIAS ==          	                	*/
	private JLabel               	categoriasLabel 	= new JLabel("Categorías", JLabel.TRAILING);
	private JComboBox<String>    	categoriasField 	= new JComboBox<>();
	private JComboBox<String>    	categoriasChoice	= new JComboBox<>(nexos);
	private JButton              	categoriasAdd   	= new JButton("Añadir");
	/* Componentes de la botonera	                	*/
	private JButton              	buscarButton    	= new JButton("Buscar");
	private JButton              	cancelarButton  	= new JButton("Reiniciar");

	/* Listener del panel */
	private Controlador listener;


	public BusquedaBuscadorJPanel(Controlador control) {

		this.listener = control;

		/* busqueda */
		SpringLayout layout = new SpringLayout();
		busqueda.setLayout(layout);

		this.tituloLabel.setLabelFor(this.tituloField);
		this.categoriasLabel.setLabelFor(this.categoriasField);

		busqueda.add(this.tituloLabel);
		busqueda.add(this.tituloField);
		busqueda.add(this.tituloChoice);
		busqueda.add(this.tituloAdd);

		busqueda.add(this.tipoLabel);
		busqueda.add(this.tipoField);
		busqueda.add(this.tipoChoice);
		busqueda.add(this.tipoAdd);

		busqueda.add(this.categoriasLabel);
		busqueda.add(this.categoriasField);
		busqueda.add(this.categoriasChoice);
		busqueda.add(this.categoriasAdd);

		/* Resultado */
		this.add(new JScrollPane(this.resultado));

		SpringUtilities.makeCompactGrid(
			busqueda,	// Contenedor donde vamos a colocar los componentes
			3, 4,    	// número de filas y cols
			20, 20,  	// initX, initY
			20, 20); 	// xPad, yPad (separación x e y)


		/* Busqueda y resultado */
		busquedaYresultado.setLayout(this.panelLayout);
		busquedaYresultado.add(busqueda);
		busquedaYresultado.add(resultado);
		this.mostrarBusqueda();

		/* botonera */
		MiJPanel botonera = new MiJPanel();

		botonera.add(this.buscarButton);
		botonera.add(this.cancelarButton);

		/* arbol de busquedas */
		MiJPanel arbol = new MiJPanel();
		arbol.setLayout(new BorderLayout());
		arbol.add(new JScrollPane(arbolBusquedas), null);


		/* añadimos todo al jpanel */
		MiJPanel panelIntermedio = new MiJPanel();
		panelIntermedio.setLayout(new BorderLayout());
		panelIntermedio.add(busquedaYresultado, BorderLayout.NORTH);
		panelIntermedio.add(botonera, BorderLayout.SOUTH);
		this.add(panelIntermedio,	BorderLayout.CENTER);
		this.add(arbol,   			BorderLayout.WEST);


		/* añadimos listeners */
		//this.buscarButton.addActionListener(this);
		final BusquedaBuscadorJPanel self = this;

		this.tituloAdd.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent arg0) {
				String titulo = self.tituloField.getText();
				String nexo = String.valueOf(self.tituloChoice.getSelectedItem());
				Predicado p = Predicado.titulo(titulo);
				self.predicado = componerNexo(nexo, p);
				insertarArbol(nexo +" "+ p);
			}
		});
		this.tipoAdd.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent arg0) {
				String tipo = String.valueOf(self.tipoField.getSelectedItem());
				String nexo = String.valueOf(self.tipoChoice.getSelectedItem());
				Predicado p = Predicado.tipo(tipo);
				self.predicado = componerNexo(nexo, p);
				insertarArbol(nexo +" "+ p);
			}
		});
		this.categoriasAdd.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent arg0) {
				String categoria = String.valueOf(self.categoriasField.getSelectedItem());
				String nexo = String.valueOf(self.categoriasChoice.getSelectedItem());
				Predicado p = Predicado.categorias(categoria);
				self.predicado = componerNexo(nexo, p);
				insertarArbol(nexo +" "+ p);
			}
		});

		this.buscarButton.addActionListener(this);

		this.cancelarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				self.tituloField.setText("");
				self.predicado = Predicado.tautologico;
				limpiarArbol();
				mostrarBusqueda();
			}
		});

		// Pijeria: iconos JTree
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		Icon personIcon = null;
		renderer.setLeafIcon(personIcon);
		renderer.setClosedIcon(personIcon);
		renderer.setOpenIcon(personIcon);
		arbolBusquedas.setCellRenderer(renderer);
		arbolBusquedas.expandRow(0);
	}

	private void insertarArbol(String mensaje) {
		modeloDatos.insertNodeInto(new DefaultMutableTreeNode(mensaje), raiz, this.indiceArbol);
		this.indiceArbol++;
	}

	private void limpiarArbol() {
		this.raiz.removeAllChildren();
		this.modeloDatos.reload();
		this.indiceArbol = 0;
	}

	private void mostrarBusqueda() {
		panelLayout.first(busquedaYresultado);
	}

	private void mostrarResultado() {
		panelLayout.last(busquedaYresultado);
	}

	public void setModelo(TableModel modelo) {
		this.resultado.setModel(modelo);
	}

	/**
	 * Coloca en el panel la lista de categorias
	 * @param categoriasValidas
	 * @return
	 */
	public BusquedaBuscadorJPanel setCategorias(Set<String> categorias) {
		for (String s : categorias) {
			this.categoriasField.addItem(s);
		}
		return this;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setModelo(this.listener.hacerBusqueda(this.predicado));
		this.mostrarResultado();
	}
}
