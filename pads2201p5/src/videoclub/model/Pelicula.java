package videoclub.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *  * Extension de Articulo que añade los campos especificos a peliculas(director y fecha de publicacion).
 * @author Victor Macias
 * @author Manuel Blanc
 */
@Entity
@DiscriminatorValue("Pelicula")
public class Pelicula extends Articulo {

	@Transient
	private static Categoria categoriasValidas = new Categoria("data/CategoriasPeliculas.txt");
	public static String[] getCategoriasValidas() { return categoriasValidas.categorias(); }

	/* ================================
	 *   Atributos
	 * ================================
	 */
	
	/** Director de la pelicula */
	private String director;
	/** Fecha en la que se publico esta pelicula */
	private int fechaPublicacion;

	/* ================================
	 *   Constructores
	 * ================================
	 */

	/**
	 * Constructor vacio para poder usar JPA.
	 */
	protected Pelicula() { }

	/**
	 * Constructor minimo de la clase.
	 * @param titulo Titulo de la pelicula.
	 */
	public Pelicula(String titulo) {
		super(titulo);
	}

	/**
	 * Constructor completo.
	 * @param titulo Titulo de la pelicula.
	 * @param director Director(es)
	 * @param fechaPublicacion Cuando fue publicada
	 */
	public Pelicula(String titulo, String director, int añoPublicacion) {
		super(titulo);
		this.director = director;
		this.fechaPublicacion = añoPublicacion;
	}

	/* ================================
	 *   Setters & getters
	 * ================================
	 */

	/**
	 * Get director
	 * @return director
	 */
	public String getDirector() {
		return this.director;
	 }
	/**
	 * Set director
	 * @return director
	 */
	public void setDirector(String director) {
		this.director = director;
	}


	/**
	 * Get fechaPublicacion
	 * @return fechaPublicacion
	 */
	public int getFechaPublicacion() {
		return this.fechaPublicacion;
	 }
	/**
	 * Set fechaPublicacion
	 * @return fechaPublicacion
	 */
	public void setFechaPublicacion(int fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	/* ================================
	 *   Metodos (@Override)
	 * ================================
	 */

	/**
	 * Comprueba si el formato es valido para este tipo de articulo.
	 * @param formato Formato del articulo.
	 * @return Booleano.
	 */
	@Override
	public boolean esFormatoValido(Formato formato) {
		return formato == Formato.DVD || formato == Formato.BLURAY;
	}

	/**
	 * Comprueba si una categoria proporcionada es valida para una serie.
	 * @param categoria
	 * @return Booleano.
	 */
	@Override
	public boolean esCategoriaValida(String categoria) {
		return Pelicula.categoriasValidas.esValida(categoria);
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return super.toString() + " Director=" + this.director+ " Fecha de publicacion=" + this.fechaPublicacion;
	}
}
