package videoclub.model;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import videoclub.model.Categoria;

public class CategoriaTest {

	static Categoria peliculas;
	static Categoria musica;
	static Categoria series;

	@BeforeClass
	public static void setUpBeforeClass() {
		peliculas = new Categoria("data/CategoriasPeliculas.txt");
		musica    = new Categoria("data/CategoriasMusica.txt");
		series    = new Categoria("data/CategoriasSeries.txt");
	}

	@Test(expected=RuntimeException.class)
	public void testCategorias1() {
		new Categoria("data/filenotfound"); // Path erroneo
	}

	@Test
	public void esValidaTest() {
		assertTrue("Categoria de musica valida",   musica.esValida("Salsa"));
		assertTrue("Categoria de pelicula valida", peliculas.esValida("XXX"));
		assertTrue("Categoria de serie valida",    series.esValida("Terror"));

		assertFalse("Categoria de musica no valida",   musica.esValida("salsa"));
		assertFalse("Categoria de pelicula no valida", peliculas.esValida("xxx"));
		assertFalse("Categoria de serie no valida",    series.esValida("terror"));
	}

	@Test
	public void esValidaTestTildes() {
		assertTrue("Tilde musica",    musica.esValida("Poesía"));
		assertTrue("Tilde peliculas", peliculas.esValida("Biográfico"));
		assertTrue("Tilde series",    series.esValida("Histórica"));

		assertFalse("Sin tilde musica",    musica.esValida("Poesia"));
		assertFalse("Sin tilde peliculas", peliculas.esValida("Biografico"));
		assertFalse("Sin tilde series",    series.esValida("Historica"));
	}

	@Test
	public void esValidaTestPrimera() {
		assertTrue("Primera categoria musica",    musica.esValida("Barroca"));
		assertTrue("Primera categoria peliculas", peliculas.esValida("Acción"));
		assertTrue("Primera categoria series",    series.esValida("Acción"));
	}

	@Test
	public void esValidaTestUltima() {
		assertTrue("Ultima categoria musica",    musica.esValida("Zarzuela"));
		assertTrue("Ultima categoria peliculas", peliculas.esValida("3-D"));
		assertTrue("Ultima categoria series",    series.esValida("Western"));
	}

}
