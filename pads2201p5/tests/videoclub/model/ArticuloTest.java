package videoclub.model;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import videoclub.model.Articulo;
import videoclub.model.Copia;
import videoclub.model.Musica;
import videoclub.model.Pelicula;
import videoclub.model.Serie;
import videoclub.model.Articulo.Formato;

public class ArticuloTest {
	static Articulo musica1, pelicula1, serie1, pelicula2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		musica1    = new Musica("Untitled (Led Zeppelin IV)");
		pelicula1  = new Pelicula("Cadena perpetua");
		serie1     = new Serie("Breaking Bad");
		pelicula2  = new Pelicula("Untitled (Led Zeppelin IV)");
	}

	@Test
	public void testEsCategoriaValida() {
		assertTrue("Categoria valida musica",   musica1.esCategoriaValida("Salsa"));
		assertTrue("Categoria valida pelicula", pelicula1.esCategoriaValida("XXX"));
		assertTrue("Categoria valida serie",    serie1.esCategoriaValida("Terror"));

		assertFalse("Categoria no valida musica",   musica1.esCategoriaValida("dsfa"));
		assertFalse("Categoria no valida pelicula", pelicula1.esCategoriaValida("dsfa"));
		assertFalse("Categoria no valida serie",    serie1.esCategoriaValida("dsfa"));

		assertFalse("Categoria nula", serie1.esCategoriaValida(null));
	}

	@Test
	public void testOperacionesCategoria() {
		assertTrue("Añadir categoria valida al articulo musica",      musica1.añadirCategoria("Salsa"));
		assertFalse("Añadir categoria ya añadida al articulo musica", musica1.añadirCategoria("Salsa"));
		assertFalse("Añadir categoria no valida al articulo musica",  musica1.añadirCategoria("XXX"));
		assertFalse("Añadir categoria nula", serie1.añadirCategoria(null));

		assertTrue("Comprobar que tiene la categoria añadida",        musica1.tieneCategoria("Salsa"));
		assertFalse("Comprobar que no tiene categorias no añadidas",  musica1.tieneCategoria("XXX"));
		assertFalse("Comprobar categoria nula", serie1.tieneCategoria(null));

		assertEquals("Comprobamos getCategorias", "Salsa", musica1.getCategorias()[0]);
		assertEquals("Comprobamos getCategorias", 1, musica1.getCategorias().length);

		assertTrue("Quitar categoria añadida",     musica1.quitarCategoria("Salsa"));
		assertFalse("Quitar categoria ya quitada", musica1.quitarCategoria("Salsa"));
		assertFalse("Quitar categoria no añadida", musica1.quitarCategoria("XXX"));
		assertFalse("Quitar categoria nula", serie1.quitarCategoria(null));

	}


	@Test(expected=IllegalArgumentException.class)
	public void testNuevaCopia1() {
		// Insertamos una copia con un formato no valido
		musica1.nuevaCopia(Formato.DVD);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNuevaCopia2() {
		// Insertamos una copia con un formato nulo
		musica1.nuevaCopia(null);
	}

	@Test
	public void testEsFormatoValido() {
		assertTrue("Formato musica valido",      musica1.esFormatoValido(Formato.CD));
		assertFalse("Formato musica no valido",  musica1.esFormatoValido(Formato.DVD));
		assertFalse("Formato pelicula valido",   pelicula1.esFormatoValido(Formato.CD));
		assertTrue("Formato pelicula no valido", pelicula1.esFormatoValido(Formato.DVD));
		assertFalse("Formato serie valido",      serie1.esFormatoValido(Formato.CD));
		assertTrue("Formato serie no valido",    serie1.esFormatoValido(Formato.DVD));
		assertFalse("Formato nulo",              serie1.esFormatoValido(null));
	}

	@Test
	public void testGetCopiaDisponible() {
		assertNotNull("Añade una copia al articulo de musica",      musica1.nuevaCopia(Formato.CD));
		assertNotNull("Comprueba que se extrae la copia creada",    musica1.getCopiaDisponible());
		assertNull("Se extrae una copia de un articulo sin copias", pelicula1.getCopiaDisponible());
	}

	@Test
	public void testEqualsArticulo() {
		assertFalse("Son articulos de distinto tipo", musica1.equals(pelicula1));
		assertTrue("Mismo articulo", musica1.equals(musica1));
		assertFalse("Comparar articulo con otro objeto", musica1.equals(new Copia()));
	}

}
