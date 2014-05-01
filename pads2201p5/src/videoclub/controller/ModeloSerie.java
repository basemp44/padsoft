package videoclub.controller;

import java.util.Collection;

import videoclub.model.Serie;

class ModeloSerie extends ModeloBase<Serie> {

	protected ModeloSerie() {}
	protected ModeloSerie(Collection<? extends Serie> col) {
		super(col);
	}

	@Override protected String[] getEtiquetas() { return etiquetas; }
	private static final String[] etiquetas = {
		"Título",
		"Temporada",
		"Volumen",
		"Veces Prestado",
		"Categorias"
	};

	@Override protected Class<?>[] getTipos() { return tipos; }
	private static final Class<?>[]  tipos = {
		String.class,
		Integer.class,
		Integer.class,
		Integer.class,
		String.class,
	};

	@Override
	protected Object getAtributo(Serie obj, int col) {
		switch (col) {
			case 0: return obj.getTitulo();
			case 1: return obj.getTemporada();
			case 2: return obj.getVolumen();
			case 3: return obj.getVecesPrestado();
			case 4: return obj.getCategorias().toString();
			default: return null;
		}
	}

	@Override
	protected void setAtributo(Serie obj, int col, Object attr) {
		switch (col) {
			case 0: obj.setTitulo((String)attr);
			case 1: obj.setTemporada((int)attr);
			case 2: obj.setVolumen((int)attr);
			case 3: return;
			case 4: return;
			default: return;
		}
	}
}