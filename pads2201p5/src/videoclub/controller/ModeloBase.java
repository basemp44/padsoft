package videoclub.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

abstract class ModeloBase<T> extends AbstractTableModel {
	
	private ArrayList<T> objetos;
	
	protected ModeloBase() {
		this.objetos = new ArrayList<>();
	}
	protected ModeloBase(Collection<? extends T> col) {
		this.objetos = new ArrayList<>(col);
	}
	
	private boolean editable = false;
	public void setEditable(boolean bool) { this.editable = bool; }

	@Override public boolean isCellEditable(int row, int col) {
		return editable;
	}
	
	/* ============================
	 * COLUMNAS
	 * ============================
	 */

	protected abstract String[]    getEtiquetas();
	protected abstract Class<?>[]  getTipos();
	
	@Override public int getColumnCount() {
		return this.getEtiquetas().length;
	}

	@Override public String getColumnName(int col) {
		return this.getEtiquetas()[col];
	}

	@Override public Class<?> getColumnClass(int col) {
		return this.getTipos()[col];
	}
	
	/* ============================
	 * FILAS
	 * ============================
	 */

	@Override
	public int getRowCount() {
		return this.objetos.size();
	}
	
	protected abstract Object getAtributo(T obj, int col);
	protected abstract void   setAtributo(T obj, int col, Object attr);
	
	@Override
	public Object getValueAt(int y, int x) {
		return this.getAtributo(this.objetos.get(y), x);
	}
	
	@Override
	public void setValueAt(Object attr, int y, int x) {
		this.setAtributo(
			this.objetos.get(y),
			x,
			this.getColumnClass(x).cast(attr)
		);
	}
}