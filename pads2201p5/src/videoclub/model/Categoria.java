package videoclub.model;

import java.io.*;
import java.util.*;

/**
 * Clase encargada de gestionar categorias, es decir, leer categorias de archivo,
 * guardarlas en memoria y realizar busquedas sobre ellas.
 * @author Victor Macias
 */
public class Categoria {
	
	/* ================================
	 *   Atributos
	 * ================================
	 */

	/** Ruta de donde se cargaron las categorias. */
	private String path;
	/** Categorias contenidas */
	private Set<String> categorias;

	/* ================================
	 *   Constructores
	 * ================================
	 */
	
	/**
	 * Constructor que guarda un path que se le pasa e inicializa el conjunto de categorias.
	 * @param path Path del fichero del cual se leeran las categorias.
	 */
	public Categoria(String path) {
		this.path = path;
		this.categorias = new HashSet<String>();
		this.leerCategoria();
	}

	/* ================================
	 *   Metodos
	 * ================================
	 */
	
	/**
	 * Lee del fichero path las categorias, guardandolas en el conjunto de categorias.
	 */
	private void leerCategoria() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(
			     new InputStreamReader(
			     new FileInputStream(
			     new File(this.path)
			), "UTF8"));
		}
		catch (FileNotFoundException  e) {
			throw new RuntimeException("Fichero no encontrado en el path " + this.path + " .Contacte con su administrator.", e);
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Formato de fichero no soportado. Contacte con su administrator.", e);
		}

		try {
			String linea = in.readLine();
			while (linea != null) {
				this.categorias.add(linea);
				linea = in.readLine();
			}
			in.close();
		}
		catch (IOException e) {
			throw new RuntimeException("Error reading file. Please contact your Administrator.", e);
		}
	}


	/**
	 * Devuelve la lista de categorias en un String[]
	 * @return String[] de categorias
	 */
	public String[] categorias() {
		return this.categorias.toArray(new String[0]);
	}

	/**
	 * Comprueba si la String que se pasa como argumento pertenece a la lista de categorias.
	 * @param c String que se quiere comprobar si es categoria o no.
	 * @return booleano.
	 */
	public boolean esValida(String c) {
		return this.categorias.contains(c);
	}
	
}
