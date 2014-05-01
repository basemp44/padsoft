package videoclub.model;


/**
 * Clase abstracta base para todos los predicados del buscador.
 * Tiene un solo metodo, comprobar, que evalua el predicado
 * sobre un articulo en concreto.
 * Objeto publico que contiene fabricas de todas los predicados. Su fin
 * es poder incluir estaticamente los predicados.
 */
public abstract class Predicado {
	/** Aplica el predicado sobre un Articulo.
	 * @return Booleano.
	 */
	public abstract boolean comprobar(Articulo articulo);
	/** Convierte un predicado a SQL si es posible.
	 * @return Cadena que contiene el SQL.
	 */
	public abstract String toString();

	/** Algebra de booleanos */
	public final Predicado and(Predicado p2) {
		return new PredicadoAND(this, p2);
	}
	public final Predicado or(Predicado p2) {
		return new PredicadoOR(this, p2);
	}
	public final Predicado xor(Predicado p2) {
		return new PredicadoXOR(this, p2);
	}
	public final static Predicado not(Predicado pp) {
		return new PredicadoNOT(pp);
	}

	/** Siempre devuelve true. Es una especie de elemento id. */
	public final static Predicado tautologico = new Predicado() {
		@Override public boolean comprobar(Articulo articulo) {
			return true;
		}
		@Override public String toString() {
			return "true";
		}
	};

	/** Titulo */
	public static final Predicado titulo(String titulo) {
		return new PredicadoTitulo(titulo);
	}

	/** Categoria */
	public static final Predicado categorias(String categoria) {
		return new PredicadoCategorias(categoria);
	}

	/** Tipo */
	public static final String[] Tipos = { "Musica", "Pelicula", "Serie" };

	public static final Predicado tipo(String tipo) {
		     if (tipo.equals("Musica"  )) return musica();
		else if (tipo.equals("Pelicula")) return pelicula();
		else if (tipo.equals("Serie"   )) return serie();
		throw new RuntimeException("Tipo no reconocido");
	}


	private static final Predicado pMusica = new PredicadoClase(Musica.class);
	public static final Predicado musica() { return pMusica; }

	private static final Predicado pPelicula = new PredicadoClase(Pelicula.class);
	public static final Predicado pelicula() { return pPelicula; }

	private static final Predicado pSerie = new PredicadoClase(Serie.class);
	public static final Predicado serie() { return pSerie; }

}

/** El predicado NOT recibe un predicado y lo niega. */
final class PredicadoNOT extends Predicado {
	private Predicado p;
	PredicadoNOT(Predicado p) {
		this.p = p;
	}
	@Override public boolean comprobar(Articulo articulo) {
		return ! this.p.comprobar(articulo);
	}
	@Override public String toString() {
		return "(NOT " + this.p.toString() + ")";
	}
};

/** Base para los predicados que actuan de nexo entre otros. */
abstract class PredicadoBinario extends Predicado {
	protected Predicado p1, p2;
	protected PredicadoBinario(Predicado p1, Predicado p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
}
/** Predicado que efectua la operacion AND entre otros dos predicados.* */
final class PredicadoAND extends PredicadoBinario {
	PredicadoAND(Predicado p1, Predicado p2) {
		super(p1, p2);
	}
	@Override public boolean comprobar(Articulo articulo) {
		return this.p1.comprobar(articulo) && this.p2.comprobar(articulo);
	}
	@Override public String toString() {
		return "(" + this.p1.toString() + " AND " + this.p2.toString() + ")";
	}
}

/** Predicado que efectua la operacion OR entre otros dos predicados. */
final class PredicadoOR extends PredicadoBinario {
	PredicadoOR(Predicado p1, Predicado p2) {
		super(p1, p2);
	}
	@Override public boolean comprobar(Articulo articulo) {
		return this.p1.comprobar(articulo) || this.p2.comprobar(articulo);
	}
	@Override public String toString() {
		return "(" + this.p1.toString() + " OR " + this.p2.toString() + ")";
	}
}

/** Predicado que efectua la operacion XOR entre otros dos predicados. */
final class PredicadoXOR extends PredicadoBinario {
	PredicadoXOR(Predicado p1, Predicado p2) {
		super(p1, p2);
	}
	@Override public boolean comprobar(Articulo articulo) {
		return this.p1.comprobar(articulo) != this.p2.comprobar(articulo);
	}
	@Override public String toString() {
		return "(" + this.p1.toString() + " XOR " + this.p2.toString() + ")";
	}
}

/** Predicado que comprueba el titulo de un articulo. */
final class PredicadoTitulo extends Predicado {
	private String titulo;
	PredicadoTitulo(String titulo) {
		this.titulo = titulo;
	}
	@Override public boolean comprobar(Articulo articulo) {
		return this.titulo.equals(articulo.getTitulo());
	}
	@Override public String toString() {
		return "titulo = \"" + this.titulo + "\"";
	}
}

/** Predicado que comprueba el id de un articulo */
final class PredicadoId extends Predicado {
	private long id;
	PredicadoId(long id) {
		this.id = id;
	}
	@Override public boolean comprobar(Articulo articulo) {
		return articulo.getId() == this.id;
	}
	@Override public String toString() {
		return "id = \"" + this.id + "\"";
	}
}

/** Predicado que comprueba las categorias de un articulo. */
final class PredicadoCategorias extends Predicado {
	private String categoria;
	PredicadoCategorias(String categoria) {
		this.categoria = categoria;
	}
	@Override public boolean comprobar(Articulo articulo) {
		return articulo.tieneCategoria(this.categoria);
	}
	@Override public String toString() {
		return "categoria = \"" + this.categoria + "\"";
	}
}

/** Predicado que comprueba si el articulo es de tipo Musica */
final class PredicadoClase extends Predicado {
	private Class<? extends Articulo> clase;
	PredicadoClase(Class<? extends Articulo> clase) {
		this.clase = clase;
	}
	@Override public boolean comprobar(Articulo articulo) {
		return clase.isInstance(articulo);
	}
	@Override public String toString() {
		return "clase = \"" + this.clase.getSimpleName() + "\"";
	}
}

