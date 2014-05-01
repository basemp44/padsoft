package videoclub.model;

import javax.persistence.*;

import videoclub.model.excepciones.ArticuloInvalidoException;

/**
 * Una Tarifa es un contrato que puede adquirir un socio. Mientras se tenga una tarifa activa, el
 * socio dispondra de una serie de ventajas como descuentos sobre peliculas
 * @author Victor Macias
 */
@Entity
public class Tarifa {

	/* ================================
	 *   Atributos
	 * ================================
	 */

	/** ID de la tarifa */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	/** Nombre de la tarifa */
	private String nombre = "Tarifa estandar";

	/** Duracion de la tarifa */
	private int duracion = Integer.MAX_VALUE;

	/** Precio de esta tarifa */
	private double precioTarifa = 0.00;

	/** Maximo numero de prestamos */
	private int maxPrestamos = 3;

	/** Duracion de los prestamos */
	private int duracionAlquilerMusica   = 3;
	private int duracionAlquilerPelicula = 3;
	private int duracionAlquilerSerie    = 3;

	/** Tasas alquiler */
	private double tasaAlquilerMusica   = 3.00;
	private double tasaAlquilerPelicula = 3.00;
	private double tasaAlquilerSerie    = 3.00;

	/** Duracion retraso leve a grave */
	private int duracionRetrasoLeve = 10;

	/** Tasas retraso */
	private double tasaRetrasoLeve  = 1.00;
	private double tasaRetrasoGrave = 5.00;

	/* ================================
	 *   Constructores
	 * ================================
	 */

	/**
	 * Constructor vacio para poder usar JPA.
	 */
	protected Tarifa() {}

	/**
	 * Constructor clonador de la clase.
	 * @param base Otra tarifa sobre la que basarse.
	 */
	public Tarifa(Tarifa base) {
		this.nombre      	= base.nombre;
		this.duracion    	= base.duracion;
		this.precioTarifa	= base.precioTarifa;

		this.duracionAlquilerMusica  	= base.duracionAlquilerMusica;
		this.duracionAlquilerPelicula	= base.duracionAlquilerPelicula;
		this.duracionAlquilerSerie   	= base.duracionAlquilerSerie;

		this.tasaAlquilerMusica  	= base.tasaAlquilerMusica;
		this.tasaAlquilerPelicula	= base.tasaAlquilerPelicula;
		this.tasaAlquilerSerie   	= base.tasaAlquilerSerie;

		this.duracionRetrasoLeve	= base.duracionRetrasoLeve;
		this.tasaRetrasoLeve    	= base.tasaRetrasoLeve;
		this.tasaRetrasoGrave   	= base.tasaRetrasoGrave;
	}

	/**
	 * Getter del precio 'mejorado' que selecciona en funcion de la clase.
	 * @param art Articulo.
	 * @return Precio.
	 * @throws NuevaExcepcion
	 */
	public double getTasaAlquiler(Articulo art) throws ArticuloInvalidoException {
		if (art instanceof Musica)   return this.getTasaAlquilerMusica();
		if (art instanceof Serie)    return this.getTasaAlquilerSerie();
		if (art instanceof Pelicula) return this.getTasaAlquilerPelicula();
		throw new ArticuloInvalidoException("Tipo de articulo no reconocido.");
	}

	/**
	 * Getter de la duracion 'mejorado' que selecciona en funcion de la clase.
	 * @param art Articulo.
	 * @return Precio.
	 * @throws NuevaExcepcion
	 */
	public int getDuracionAlquiler(Articulo art) throws ArticuloInvalidoException {
		if (art instanceof Musica)   return this.getDuracionAlquilerMusica();
		if (art instanceof Serie)    return this.getDuracionAlquilerSerie();
		if (art instanceof Pelicula) return this.getDuracionAlquilerPelicula();
		throw new ArticuloInvalidoException("Tipo de articulo no reconocido.");
	}

	/* ================================
	 *   Setters & getters
	 * ================================
	 */

	/**
	 * Get nombre
	 * @return nombre
	 *
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * Set nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Get duracion
	 * @return duracion
	 *
	 */
	public int getDuracion() {
		return this.duracion;
	}
	/**
	 * Set duracion
	 * Precaucion, operacion potencialmente peligrosa.
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	/**
	 * Get precioTarifa
	 * @return precioTarifa
	 *
	 */
	public double getPrecioTarifa() {
		return this.precioTarifa;
	}
	/**
	 * Set precioTarifa
	 */
	public void setPrecioTarifa(double precioTarifa) {
		if (precioTarifa < 0) throw new IllegalArgumentException("El argumento no puede ser negativo");
		this.precioTarifa = precioTarifa;
	}

	/* ================================
	 *   Setters & getters (tarifa)
	 * ================================
	 */

	/**
	 * Set maxPrestamos
	 * @param maxPrestamos
	 */
	public void setMaxPrestamos(int maxPrestamos) {
		if (maxPrestamos < 0) throw new IllegalArgumentException("El argumento no puede ser negativo");
		this.maxPrestamos = maxPrestamos;
	}
	/**
	 * Get maxPrestamos
	 * @return maxPrestamos
	 */
	public int getMaxPrestamos() {
		return this.maxPrestamos;
	}


	/**
	 * Set duracionAlquilerMusica
	 * @param duracionAlquilerMusica
	 */
	public void setDuracionAlquilerMusica(int duracionAlquilerMusica) {
		if (duracionAlquilerMusica < 0) throw new IllegalArgumentException("El argumento no puede ser negativo");
		this.duracionAlquilerMusica = duracionAlquilerMusica;
	}
	/**
	 * Get duracionAlquilerMusica
	 * @return duracionAlquilerMusica
	 */
	public int getDuracionAlquilerMusica() {
		return this.duracionAlquilerMusica;
	}


	/**
	 * Set duracionAlquilerPelicula
	 * @param duracionAlquilerPelicula
	 */
	public void setDuracionAlquilerPelicula(int duracionAlquilerPelicula) {
		if (duracionAlquilerPelicula < 0) throw new IllegalArgumentException("El argumento no puede ser negativo");
		this.duracionAlquilerPelicula = duracionAlquilerPelicula;
	}
	/**
	 * Get duracionAlquilerPelicula
	 * @return duracionAlquilerPelicula
	 */
	public int getDuracionAlquilerPelicula() {
		return this.duracionAlquilerPelicula;
	}


	/**
	 * Set duracionAlquilerSerie
	 * @param duracionAlquilerSerie
	 */
	public void setDuracionAlquilerSerie(int duracionAlquilerSerie) {
		if (duracionAlquilerSerie < 0) throw new IllegalArgumentException("El argumento no puede ser negativo");
		this.duracionAlquilerSerie = duracionAlquilerSerie;
	}
	/**
	 * Get duracionAlquilerSerie
	 * @return duracionAlquilerSerie
	 */
	public int getDuracionAlquilerSerie() {
		return this.duracionAlquilerSerie;
	}


	/**
	 * Set duracionRetrasoLeve
	 * @param duracionRetrasoLeve
	 */
	public void setDuracionRetrasoLeve(int duracionRetrasoLeve) {
		if (duracionRetrasoLeve < 0) throw new IllegalArgumentException("El argumento no puede ser negativo");
		this.duracionRetrasoLeve = duracionRetrasoLeve;
	}
	/**
	 * Get duracionRetrasoLeve
	 * @return duracionRetrasoLeve
	 */
	public int getDuracionRetrasoLeve() {
		return this.duracionRetrasoLeve;
	}


	/**
	 * Set tasaAlquilerMusica
	 * @param tasaAlquilerMusica
	 */
	public void setTasaAlquilerMusica(double tasaAlquilerMusica) {
		if (tasaAlquilerMusica < 0) throw new IllegalArgumentException("El argumento no puede ser negativo");
		this.tasaAlquilerMusica = tasaAlquilerMusica;
	}
	/**
	 * Get tasaAlquilerMusica
	 * @return tasaAlquilerMusica
	 */
	public double getTasaAlquilerMusica() {
		return this.tasaAlquilerMusica;
	}


	/**
	 * Set tasaAlquilerPelicula
	 * @param tasaAlquilerPelicula
	 */
	public void setTasaAlquilerPelicula(double tasaAlquilerPelicula) {
		if (tasaAlquilerPelicula < 0) throw new IllegalArgumentException("El argumento no puede ser negativo");
		this.tasaAlquilerPelicula = tasaAlquilerPelicula;
	}
	/**
	 * Get tasaAlquilerPelicula
	 * @return tasaAlquilerPelicula
	 */
	public double getTasaAlquilerPelicula() {
		return this.tasaAlquilerPelicula;
	}


	/**
	 * Set tasaAlquilerSerie
	 * @param tasaAlquilerSerie
	 */
	public void setTasaAlquilerSerie(double tasaAlquilerSerie) {
		if (tasaAlquilerSerie < 0) throw new IllegalArgumentException("El argumento no puede ser negativo");
		this.tasaAlquilerSerie = tasaAlquilerSerie;
	}
	/**
	 * Get tasaAlquilerSerie
	 * @return tasaAlquilerSerie
	 */
	public double getTasaAlquilerSerie() {
		return this.tasaAlquilerSerie;
	}


	/**
	 * Set tasaRetrasoLeve
	 * @param tasaRetrasoLeve
	 */
	public void setTasaRetrasoLeve(double tasaRetrasoLeve) {
		if (tasaRetrasoLeve < 0) throw new IllegalArgumentException("El argumento no puede ser negativo");
		this.tasaRetrasoLeve = tasaRetrasoLeve;
	}
	/**
	 * Get tasaRetrasoLeve
	 * @return tasaRetrasoLeve
	 */
	public double getTasaRetrasoLeve() {
		return this.tasaRetrasoLeve;
	}


	/**
	 * Set tasaRetrasoGrave
	 * @param tasaRetrasoGrave
	 */
	public void setTasaRetrasoGrave(double tasaRetrasoGrave) {
		if (tasaRetrasoGrave < 0) throw new IllegalArgumentException("El argumento no puede ser negativo");
		this.tasaRetrasoGrave = tasaRetrasoGrave;
	}
	/**
	 * Get tasaRetrasoGrave
	 * @return tasaRetrasoGrave
	 */
	public double getTasaRetrasoGrave() {
		return this.tasaRetrasoGrave;
	}

	/* ================================
	 *   Metodos (overrides)
	 * ================================
	 */

	/**
	 * Devuelve una representacion de esta tarifa como un String.
	 */
	@Override
	public String toString() {
		return "Nombre=" + this.nombre + "Precio=" + this.precioTarifa;
	}
}
