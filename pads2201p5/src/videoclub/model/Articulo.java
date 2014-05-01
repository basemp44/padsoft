package videoclub.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

/**
 * Articulo es una clase abstracta que engloba peliculas, series y musica.
 * @author Victor Macias
 */
@Entity
@Inheritance
@DiscriminatorColumn(name="TIPO_ARTICULO")
public abstract class Articulo {

	/* ================================
	 *   Constantes & globales
	 * ================================
	 */

	/** Lista de formatos soportados para las copias */
	public enum Formato { NINGUNO, BLURAY, CD, DVD, VINILO };

	/* ================================
	 *   Atributos
	 * ================================
	 */

	/** Identificador unico del articulo */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	/** Titulo del articulo */
	private String titulo = "";

	/** Categorias a las que pertenece el artículo */
	@ElementCollection(fetch=FetchType.EAGER)
	private Set<String> categorias;

	/** Copias asociadas a este articulo */
	@OneToMany
	private Set<Copia> copias;

	/** Veces que se ha prestado este articulo */
	private int vecesPrestado;

	/* ================================
	 *   Constructores
	 * ================================
	 */

	/**
	 * Constructor vacio para poder usar JPA.
	 */
	protected Articulo() { }

	/**
	 * Constructor minimo de la clase.
	 * @param titulo Titulo del articulo.
	 */
	protected Articulo(String titulo) {
		this.titulo = titulo;
		categorias = new HashSet<String>();
		copias = new HashSet<Copia>();
	}

	/* ================================
	 *   Metodos (categorias)
	 * ================================
	 */

	/**
	 * Determina si una categoria es valida.
	 * @param categoria Categoria que comprobar
	 * @return Booleano.
	 */
	public abstract boolean esCategoriaValida(String categoria);

	/**
	 * Asigna una nueva categoria a este articulo.
	 * Para ello, debe existir en la lista de categorias disponibles.
	 * Indica si se ha podido asignar.
	 * @param categoria
	 * @return Booleano que indica si se tomo la accion.
	 */
	public boolean añadirCategoria(String categoria) {
		if (! this.esCategoriaValida(categoria)) return false;
		return this.categorias.add(categoria);
	}

	/**
	 * Quita una categoria a este articulo. Para ello, debe tener la categoria.
	 * @param categoria
	 * @return Booleano que indica si se tomo la accion.
	 */
	public boolean quitarCategoria(String categoria) {
		return this.categorias.remove(categoria);
	}

	/**
	 * Comprueba si este articulo tiene determinada categoria.
	 * @param categoria Categoria que comprobar.
	 * @return Booleano.
	 */
	public boolean tieneCategoria(String categoria) {
		return this.categorias.contains(categoria);
	}

	/**
	 * Devuelve la lista de categorias como un array.
	 * @param categoria Categoria que comprobar.
	 * @return Booleano.
	 */
	public String[] getCategorias() {
		return this.categorias.toArray(new String[0]);
	}


	/* ================================
	 *   Metodos (copias)
	 * ================================
	 */

	/**
	 * Crea una nueva copia asociada a este articulo.
	 * @param formato Formato del articulo.
	 * @return Copia recien creada.
	 */
	public final Copia nuevaCopia(Formato formato) {
		if (!this.esFormatoValido(formato)) {
			throw new IllegalArgumentException("Formato no valido.");
		}
		Copia copia = new Copia(this, formato);
		this.copias.add(copia);
		return copia;
	}

	/**
	 * Comprueba si el formato es valido para este tipo de articulo.
	 * @param formato Formato del articulo.
	 * @return Booleano.
	 */
	protected abstract boolean esFormatoValido(Formato formato);

	/**
	 * Devuelve la primera copia que puede ser prestada.
	 * @return Copia.
	 */
	public Copia getCopiaDisponible() {
		for (Copia c : this.copias) {
			if (c.estaDisponible()) return c;
		}
		return null;
	}

	/**
	 * Devuelve la lista de copias asociadas a este articulo.
	 * @return Copia[].
	 */
	public Copia[] getCopias() {
		return this.copias.toArray(new Copia[0]);
	}

	/* ================================
	 *   Metodos (overrides)
	 * ================================
	 */
	
	public boolean equals(Object obj) {
		if (this.getClass() != obj.getClass()) return false;
		Articulo art = (Articulo) obj;
		return art.titulo.equals(this.titulo);
	}

	@Override
	public int hashCode() {
		return this.titulo.hashCode()*7;
	}

	@Override
	public String toString() {
		return " Id=" + this.id + " Titulo=" + this.titulo + "Categorias=" + this.categorias + " ";
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
	 * Get titulo
	 * @return titulo
	 */
	public String getTitulo() {
		return this.titulo;
	}
	
	/**
	 * Set titulo
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Aumenta una unidad el numero de veces que se ha prestado el articulo
	 */
	protected void nuevoPrestamo() {
		this.vecesPrestado++;
	}

	/**
	 * Get vecesPrestado
	 * @return vecesPrestado
	 */
	public int getVecesPrestado() {
		return this.vecesPrestado;
	}
	/**
	 * Set vecesPrestado
	 * @return vecesPrestado
	 */
	protected void setVecesPrestado(int vecesPrestado) {
		this.vecesPrestado = vecesPrestado;
	}
	
}
