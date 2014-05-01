package videoclub.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import videoclub.model.Articulo;
import videoclub.model.Copia;
import videoclub.model.Musica;
import videoclub.model.Pelicula;
import videoclub.model.Predicado;
import videoclub.model.Serie;
import videoclub.model.Socio;
import videoclub.model.Tarifa;
import videoclub.model.Videoclub;
import videoclub.view.VideoclubGUI;

/**
 * El Controlador de la aplicacion encapsula el modelo
 */
public class Controlador {

	private Videoclub   	modelo;
	private VideoclubGUI	vista;

	public Controlador(Videoclub modelo) {
		this.modelo = modelo;

		String[] categoriasMusica          	= Musica.getCategoriasValidas();
		String[] categoriasPelicula        	= Pelicula.getCategoriasValidas();
		String[] categoriasSerie           	= Serie.getCategoriasValidas();
		Set<String> categorias             	= new HashSet<>();
		for (String s : categoriasMusica)  	categorias.add(s);
		for (String s : categoriasPelicula)	categorias.add(s);
		for (String s : categoriasSerie)   	categorias.add(s);
	}

	public void setVista(VideoclubGUI vista) {
		this.vista = vista;
	}

	public String[] getCategoriasMusica() {
		return Musica.getCategoriasValidas();
	}
	public String[] getCategoriasPelicula() {
		return Pelicula.getCategoriasValidas();
	}
	public String[] getCategoriasSerie() {
		return Serie.getCategoriasValidas();
	}

	public boolean registrarMusica(String titulo, String interprete, int fechaPublicacion, List<String> categorias) {
		Musica articulo = new Musica(titulo, interprete, fechaPublicacion);
		for (String c : categorias) articulo.añadirCategoria(c);
		return modelo.registrarArticulo(articulo);
	}

	public boolean registrarPelicula(String titulo, String director, int fechaPublicacion, List<String> categorias) {
		Pelicula articulo = new Pelicula(titulo, director, fechaPublicacion);
		for (String c : categorias) articulo.añadirCategoria(c);
		return modelo.registrarArticulo(articulo);
	}

	public boolean registrarSerie(String titulo, int temporada, int volumen, List<String> categorias) {
		Serie articulo = new Serie(titulo, temporada, volumen);
		for (String c : categorias) articulo.añadirCategoria(c);
		return modelo.registrarArticulo(articulo);
	}

	public String registrarSocio(String dni, String nombre, String telefono, String direccion, String email) {
		return modelo.registrarSocio(dni, nombre, telefono, direccion, email);
	}

	public boolean registrarTarifa(String nombre, int duracion, double precioTarifa, int maxPrestamos,
			int duracionMusica, int duracionPelicula, int duracionSerie,
			double tasaMusica, double tasaPelicula, double tasaSerie,
			int duracionRetrasoLeve, double tasaRetrasoLeve, double tasaRetrasoGrave) {

		Tarifa tarifa = new Tarifa(modelo.getTarifaEstandar());

		tarifa.setNombre(nombre);
		tarifa.setDuracion(duracion);
		tarifa.setPrecioTarifa(precioTarifa);
		tarifa.setMaxPrestamos(maxPrestamos);

		tarifa.setDuracionAlquilerMusica(duracionMusica);
		tarifa.setDuracionAlquilerPelicula(duracionPelicula);
		tarifa.setDuracionAlquilerSerie(duracionSerie);

		tarifa.setTasaAlquilerMusica(tasaMusica);
		tarifa.setTasaAlquilerPelicula(tasaPelicula);
		tarifa.setTasaAlquilerSerie(tasaSerie);

		tarifa.setDuracionRetrasoLeve(duracionRetrasoLeve);
		tarifa.setTasaRetrasoLeve(tasaRetrasoLeve);
		tarifa.setTasaRetrasoGrave(tasaRetrasoGrave);

		return modelo.registrarTarifa(tarifa);
	}

	public void cerrar() {
		this.modelo.cerrar();
		System.exit(0);
	}

	public ModeloSocio getMorosos() {
		return new ModeloSocio(modelo.morosos());
	}

	public ModeloArticulo getTop10() {
		return new ModeloArticulo(modelo.top10());
	}

	public ModeloArticulo hacerBusqueda(Predicado pred) {
		Set<Articulo> resultado = this.modelo.buscarArticulos(pred);
		return new ModeloArticulo(resultado);
	}

	public double devolver(String uid) {
		Socio socio = this.modelo.buscarSocio(uid);
		if (socio == null) {
			return 0;
		}
		List<Copia> copias = socio.getPrestamos();
		double precio=0;
		
		for (Copia c: copias) {
			precio+=c.getTasaDevolucion();
		}
		return precio;
	}
	
	public void devolver(String uid, double precio) {
		Socio socio = this.modelo.buscarSocio(uid);
		if (socio == null) {
			return;	
		}
		
		socio.devolverTodo();
		this.vista.mostrarPago(uid, precio);
	}
	
	public long buscarArticulo() {
		vista.mostrarBusqueda();
		return 0;
	}

	public boolean prestamo(String uid, String codigo_articulo) {
		Socio soc = modelo.buscarSocio(uid);
		if (null == soc) return false;
		
		return false;
	}

	public void login(String username, char[] password) {
		vista.login( modelo.login(username, new String(password)) );
	}

	public boolean contratarTarifa(String uid_socio, String nombre_tarifa) {
		Socio soc = modelo.buscarSocio(uid_socio);
		if (null == soc) return false;

		Tarifa tarifa = null;
		for (Tarifa tar : modelo.getTarifas()) {
			if (tar.getNombre() == nombre_tarifa) {
				tarifa = tar;
				break;
			}
		}
		if (null == tarifa) return false;

		soc.contratarTarifa(tarifa);
		return true;
	}
}
