package videoclub.controller;

import java.util.Collection;

import videoclub.model.Musica;

class ModeloMusica extends ModeloBase<Musica> {

	protected ModeloMusica() {}
	protected ModeloMusica(Collection<? extends Musica> col) {
		super(col);
	}

	@Override protected String[] getEtiquetas() { return ModeloMusica.etiquetas; }
	private static final String[] etiquetas = {
		"Título",
		"Interprete",
		"Año creación",
		"Veces Prestado",
		"Categorias"
	};

	@Override protected Class<?>[] getTipos() { return ModeloMusica.tipos; }
	private static final Class<?>[]  tipos = {
		String.class,
		String.class,
		Integer.class,
		Integer.class,
		String.class,
	};

	@Override
	protected Object getAtributo(Musica obj, int col) {
		switch (col) {
			case 0: return obj.getTitulo();
			case 1: return obj.getInterprete();
			case 2: return obj.getFechaPublicacion();
			case 3: return obj.getTitulo();
			case 4: return obj.getCategorias().toString();
			default: return null;
		}
	}

	@Override
	protected void setAtributo(Musica obj, int col, Object attr) {
		switch (col) {
			case 0: obj.setTitulo((String)attr);
			case 1: obj.setInterprete((String)attr);
			case 2: obj.setFechaPublicacion((int)attr);
			case 3: obj.setTitulo((String)attr);
			default: return;
		}
	}
}