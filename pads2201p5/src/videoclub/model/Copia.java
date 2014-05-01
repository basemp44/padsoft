package videoclub.model;

import javax.persistence.*;

import org.joda.time.*;

import videoclub.model.Articulo.Formato;

/**
 * Una copia representa un ejemplar concreto de un articulo.
 * @author Victor Macias
 */
@Entity
public class Copia {

	/* ================================
	 *   Atributos
	 * ================================
	 */

	/** Identificador de la copia. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	/** Observaciones del estado de la copia. */
	private String observaciones;

	/** Observaciones del estado de la copia. */
	private boolean rota;

	/** Socio que tiene la copia. */
	@ManyToOne
	private Socio socio;

	/** Informacion general sobre la copia. */
	@ManyToOne
	private Articulo articulo;

	/** Formato de la copia. */
	private Formato formato;

	/** Fecha fin del prestamo (si esta prestado). */
	private DateTime fechaFin;

	/* ================================
	 *   Constructores
	 * ================================
	 */

	/**
	 * Constructor vacio para poder usar JPA.
	 */
	protected Copia() { }

	/* Visibilidad de paquete, una copia solo
	 * puede ser creada por un Articulo.
	 */
	protected Copia(Articulo art, Formato formato) {
		this.articulo = art;
		this.formato = formato;
	}

	/* ================================
	 *   Metodos
	 * ================================
	 */

	/**
	 * Devuelve un booleano en funcion si la copia esta prestada o no
	 * @return booleano.
	 */
	public boolean estaPrestada() {
		return (null != this.fechaFin);
	}

	/**
	 * Devuelve un booleano en funcion si la copia esta prestada o no.
	 * @return Booleano.
	 */
	public boolean estaDisponible() {
		return !this.estaPrestada() && !this.estaRota();
	}

	/**
	 * Devuelve un booleano en funcion si la copia esta rota o no.
	 * @return Booleano.
	 */
	public int diasRetraso() {
		DateTime now = new DateTime();
		int days = Days.daysBetween(this.fechaFin, now).getDays() + 1;
		if (days < 0) return 0;
		return days;
	}

	/**
	 * Presta una copia a un socio (funcion protegida).
	 * @param socio Socio al que prestar
	 */
	protected void prestar(Socio socio) {
		this.articulo.nuevoPrestamo();
		this.socio = socio;

		int dias = socio.getTarifa().getDuracionAlquiler(this.articulo);
		this.fechaFin = new DateTime().plusDays(dias);
	}

	/**
	 * Devuelve la copia prestada dejandola en el almacen, y quitandosela al usuario.
	 */
	protected void devolver() {
		this.fechaFin = null;
		this.socio = null;
	}

	/**
	 * Devuelve la tasa a pagar por devolver este articulo.
	 * @return Tasa.
	 */
	public double getTasaAlquiler(Socio socio) {
		if (this.estaPrestada()) throw new RuntimeException("Copia ya prestada");
		return socio.getTarifa().getTasaAlquiler(this.articulo);
	}

	/**
	 * Devuelve la tasa a pagar por devolver este articulo.
	 * @return Tasa.
	 */
	public double getTasaDevolucion() {
		if (!this.estaPrestada()) throw new RuntimeException("Copia no prestada");
		int d = this.diasRetraso();
		Tarifa t = this.socio.getTarifa();
		return t.getTasaRetrasoLeve()  * Math.min(d,     t.getDuracionRetrasoLeve())
		     + t.getTasaRetrasoGrave() * Math.max(0, d - t.getDuracionRetrasoLeve());
	}

	/* ================================
	 *   Setters & getters
	 * ================================
	 */

	/**
	 * Get id
	 * @return id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Get observaciones
	 * @return observaciones
	 */
	public String getObservaciones() {
		return this.observaciones;
	}
	/**
	 * Set observaciones
	 * @return observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Get rota
	 * @return rota
	 */
	public boolean estaRota() {
		return this.rota;
	 }
	/**
	 * Set rota
	 * @return rota
	 */
	public void setRota(boolean rota) {
		this.rota = rota;
	}

	/**
	 * Get socio
	 * @return socio
	 */
	public Socio getSocio() {
		return this.socio;
	 }

	/**
	 * Get articulo
	 * @return articulo
	 */
	public Articulo getArticulo() {
		return this.articulo;
	}

	/**
	 * Get formato
	 * @return formato
	 */
	public Formato getFormato() {
		return this.formato;
	}

	/* ================================
	 *   Metodos (overrides)
	 * ================================
	 */

	/**
	 * Devuelve la representacion de esta copia como una cadena.
	 * @return String.
	 */
	@Override
	public String toString() {
		return "Copia: " + this.codigoDeCopia();
	}

	/**
	 * Devuelve un codigo auto-generado para marcar esta copia.
	 * @return Codigo.
	 */
	public String codigoDeCopia() {
		String line;
		     if (this.articulo instanceof Musica  ) line = "MUS-";
		else if (this.articulo instanceof Pelicula) line = "PEL-";
		else if (this.articulo instanceof Serie   ) line = "SER-";
		else line = "ART-";

		line += this.getArticulo().getTitulo();
		line += "-" + this.hashCode();
		return line;
	}
}

