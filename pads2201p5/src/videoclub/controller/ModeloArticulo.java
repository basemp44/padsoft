package videoclub.controller;

import java.util.Collection;

import videoclub.model.Articulo;

class ModeloArticulo extends ModeloBase<Articulo> {
	
	protected ModeloArticulo() {}
	protected ModeloArticulo(Collection<? extends Articulo> col) {
		super(col);
	}

	@Override protected String[] getEtiquetas() { return ModeloArticulo.etiquetas; }
	private static final String[] etiquetas = {
		"Tipo",
		"Título",
		"Categorias"
	};

	@Override protected Class<?>[] getTipos() { return ModeloArticulo.tipos; }
	private static final Class<?>[]  tipos = {
		String.class,
		String.class,
		String.class
	};

	@Override
	protected Object getAtributo(Articulo obj, int col) {
		switch (col) {
			case 0: return obj.getClass().getName();
			case 1: return obj.getTitulo();
			case 2:
			StringBuilder sb = new StringBuilder();
			for (String str : obj.getCategorias()) sb.append(str).append(", ");
			return sb.toString();
			default: return null;
		}
	}

	@Override
	protected void setAtributo(Articulo obj, int col, Object attr) {
		switch (col) {
			case 0: return;
			case 1: obj.setTitulo((String)attr);
			case 2: return;
			default: return;
		}
	}
}