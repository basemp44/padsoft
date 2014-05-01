package videoclub.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;


/**
 * Extension de Articulo que añade los campos especificos a series (temporada y volumen).
 * @author Victor Macias
 * @author Manuel Blanc
 */
@Entity
@DiscriminatorValue("Serie")
public class Serie extends Articulo {

	@Transient
	private static Categoria categoriasValidas = new Categoria("data/CategoriasSeries.txt");
	public static String[] getCategoriasValidas() { return categoriasValidas.categorias(); }

	/* ================================
	 *   Atributos
	 * ================================
	 */

	/** Temporada */
	private int temporada;
	/** Volumen */
	private int volumen;

	/* ================================
	 *   Constructores
	 * ================================
	 */

	/**
	 * Constructor vacio para poder usar JPA.
	 */
	protected Serie() { }

	/**
	 * Constructor minimo de la clase
	 * @param titulo titulo de la serie.
	 */
	public Serie(String titulo) {
		super(titulo);
	}

	/**
	 * Constructor completo.
	 * @param titulo Titulo de la pieza.
	 * @param temporada Temporada
	 * @param volumen Volumen
	 */
	public Serie(String titulo, int temporada, int volumen) {
		super(titulo);
		this.temporada = temporada;
		this.volumen = volumen;
	}

	/* ================================
	 *   Setters & getters
	 * ================================
	 */

	/**
	 * Get temporada
	 * @return temporada
	 */
	public int getTemporada() {
		return this.temporada;
	 }
	/**
	 * Set temporada
	 * @return temporada
	 */
	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}


	/**
	 * Get volumen
	 * @return volumen
	 */
	public int getVolumen() {
		return this.volumen;
	 }
	/**
	 * Set volumen
	 * @return volumen
	 */
	public void setVolumen(int volumen) {
		this.volumen = volumen;
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
		return Serie.categoriasValidas.esValida(categoria);
	}

	@Override
	public String toString() {
		return super.toString() + " Temporada=" + this.temporada + " Volumen=" + this.volumen;
	}
}

