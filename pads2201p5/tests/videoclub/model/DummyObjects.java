package videoclub.model;


/**
 * Este fichero contiene objetos de prueba. Descienden de los objetos verdaderos,
 * pero tienen metodos sobre-escritos para poder hacer pruebas de comportamiento.
 */

class CopiaDummy extends Copia {
	public int retrasoPrueba = 10;
	@Override public int diasRetraso() {
		return retrasoPrueba;
	}
	@Override protected void prestar(Socio socio) { /* NOP */ }
	
}

class TarifaDummy extends Tarifa {
	public int pruebaDuracion = 30;
	@Override public int getDuracion() {
		// El prestamo finaliza justo al crearse.
		return (pruebaDuracion);
	}
}
