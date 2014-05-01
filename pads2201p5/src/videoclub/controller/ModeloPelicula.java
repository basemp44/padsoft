package videoclub.controller;

import java.util.Collection;

import videoclub.model.Pelicula;

class ModeloPelicula extends ModeloBase<Pelicula> {

	protected ModeloPelicula() {}
	protected ModeloPelicula(Collection<? extends Pelicula> col) {
		super(col);
	}

	@Override protected String[] getEtiquetas() { return etiquetas; }
	private static final String[] etiquetas = {
		"Título",
		"Director",
		"Año creación",
		"Veces Prestado",
		"Categorias"
	};

	@Override protected Class<?>[] getTipos() { return tipos; }
	private static final Class<?>[]  tipos = {
		String.class,
		String.class,
		Integer.class,
		Integer.class,
		String.class,
	};

	@Override
	protected Object getAtributo(Pelicula obj, int col) {
		switch (col) {
			case 0: return obj.getTitulo();
			case 1: return obj.getDirector();
			case 2: return obj.getFechaPublicacion();
			case 3: return obj.getVecesPrestado();
			case 4: return obj.getCategorias().toString();
			default: return null;
		}
	}

	@Override
	protected void setAtributo(Pelicula obj, int col, Object attr) {
		switch (col) {
			case 0: obj.setTitulo((String)attr);
			case 1: obj.setDirector((String)attr);
			case 2: obj.setFechaPublicacion((int)attr);
			case 3: return;
			case 4: return;
			default: return;
		}
	}
}