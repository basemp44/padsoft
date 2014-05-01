package videoclub.model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import videoclub.model.Articulo;
import videoclub.model.Copia;
import videoclub.model.Pelicula;
import videoclub.model.Socio;
import videoclub.model.Videoclub;
import videoclub.model.Articulo.Formato;

public class CopiaTest {

	static Articulo a;
	static Videoclub v;
	static Copia c1;
	static Copia c2;
	static Socio socio;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		a = new Pelicula();
		v = new Videoclub("mem:db");

		a.nuevaCopia(Formato.DVD);
		c1 = a.getCopiaDisponible();

		socio = new Socio(v,"654", "6464");
	}

	@Test
	public void testEstaPrestada() {
		c2 = a.nuevaCopia(Formato.DVD);
		assertFalse("No esta prestada al crearla", c2.estaPrestada());
		assertFalse("No esta prestada cuando obtenemos copias disponibles", c1.estaPrestada());

		socio.alquilar(c1);

		assertTrue("Esta prestada si el socio la alquila", c1.estaPrestada());
		socio.devolverTodo();
		assertFalse("Ya no esta prestada si el socio la devuelve", c1.estaPrestada());

		c2 = null;
	}

	@Test
	public void testDisponible() {
		c2 = a.nuevaCopia(Formato.DVD);
		assertTrue("Esta disponible al crearla", c2.estaDisponible());
		assertTrue("Cuando obtenemos copias disponibles", c1.estaDisponible());

		socio.alquilar(c1);

		assertFalse("No esta disponible si el socio la alquila", c1.estaDisponible());
		socio.devolverTodo();
		assertTrue("Esta disponible si el socio la devuelve", c1.estaDisponible());

		c1.setRota(true);
		assertFalse("Si esta rota no esta disponible", c1.estaDisponible());
		c1.setRota(false);
		c2 = null;

	}

	@Test
	public void testPrestarDevolver() {
		int nprestamos = a.getVecesPrestado();
		c1.prestar(socio);
		assertEquals("Aumentamos las devoluciones", nprestamos+1, a.getVecesPrestado());
		assertTrue(c1.estaPrestada());

		c1.devolver();
		assertNull("Ya no hay socio asociado a la copia", c1.getSocio());
	}

	@AfterClass
	public static void setUpAfterClass() {
		v.cerrar();
	}
}
