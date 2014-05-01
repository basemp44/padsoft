package videoclub.controller;

import java.util.Collection;

import videoclub.model.Socio;

class ModeloSocio extends ModeloBase<Socio> {

	protected ModeloSocio() {}
	protected ModeloSocio(Collection<? extends Socio> col) {
		super(col);
	}

	@Override protected String[] getEtiquetas() { return etiquetas; }
	private static final String[] etiquetas = {
		"UID",
		"DNI",
		"Nombre",
		"Telefono",
		"Direccion",
		"eMail"
	};

	@Override protected Class<?>[] getTipos() { return tipos; }
	private static final Class<?>[]  tipos = {
		String.class,
		String.class,
		String.class,
		String.class,
		String.class,
		String.class,
	};

	@Override
	protected Object getAtributo(Socio obj, int col) {
		switch (col) {
			case 0: return obj.getUid();
			case 1: return obj.getDni();
			case 2: return obj.getNombre();
			case 3: return obj.getTelefono();
			case 4: return obj.getDireccion();
			case 5: return obj.getEmail();
			default: return null;
		}
	}

	@Override
	protected void setAtributo(Socio obj, int col, Object attr) {
		switch (col) {
			case 0: return;
			case 1: return;
			case 2: obj.setNombre((String)attr);
			case 3: obj.setTelefono((String)attr);
			case 4: obj.setDireccion((String)attr);
			case 5: obj.setEmail((String)attr);
			default: return;
		}
	}
}