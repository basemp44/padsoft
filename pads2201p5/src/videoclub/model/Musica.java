package videoclub.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Extension de Articulo que añade los campos especificos a Musica:
 * - Temporada
 * - Volumen
 * @author Victor Macias
 */

@Entity
@DiscriminatorValue("Musica")
public class Musica extends Articulo {

	@Transient
	private static Categoria categoriasValidas = new Categoria("data/CategoriasMusica.txt");
	public static String[] getCategoriasValidas() { return categoriasValidas.categorias(); }


	/* ================================
	 *   Atributos
	 * ================================
	 */
	
	/** Interprete del disco*/
	private String interprete;
	/** Fecha en la que publico este disco */
	private int fechaPublicacion;

	/* ================================
	 *   Constructores
	 * ================================
	 */

	/**
	 * Constructor vacio para poder usar JPA.
	 */
	protected Musica() { }

	/**
	 * Constructor minimo de la clase
	 * @param titulo titulo de la serie.
	 */
	public Musica(String titulo) {
		super(titulo);
	}

	/**
	 * Constructor completo.
	 * @param titulo Titulo de la pieza.
	 * @param interprete Interprete(s)
	 * @param fechaPublicacion Cuando fue publicada
	 */
	public Musica(String titulo, String interprete, int fechaPublicacion) {
		super(titulo);
		this.interprete = interprete;
		this.fechaPublicacion = fechaPublicacion;
	}

	/* ================================
	 *   Setters & getters
	 * ================================
	 */

	/**
	 * Get interprete
	 * @return interprete
	 */
	public String getInterprete() {
		return this.interprete;
	}
	/**
	 * Set interprete
	 * @return interprete
	 */
	public void setInterprete(String interprete) {
		this.interprete = interprete;
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
		return formato == Formato.CD || formato == Formato.VINILO;
	}

	/**
	 * Comprueba si una categoria proporcionada es valida para una serie.
	 * @param categoria
	 * @return Booleano.
	 */
	@Override
	public boolean esCategoriaValida(String categoria) {
		return Musica.categoriasValidas.esValida(categoria);
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return super.toString() + " Interprete=" + this.interprete + " Fecha de publicacion=" + this.fechaPublicacion;
	}
}
