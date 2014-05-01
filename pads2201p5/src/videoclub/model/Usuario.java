package videoclub.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.EnumSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Clase encargada del login de usuarios al sistema. Cada tipo de usuario tendra sus permisos definidos.
 * @author victor
 */
@Entity
public class Usuario {

	/* ================================
	 *   Constantes & globales
	 * ================================
	 */
	public enum Permisos {
		CREAR_ARTICULO,  	/** Crear/modificar articulos     	*/
		BORRAR_ARTICULO, 	/** Borrar articulos              	*/
		CREAR_COPIA,     	/** Crear/modificar copias        	*/
		BORRAR_COPIA,    	/** Borrar copias                 	*/
		CREAR_SOCIO,     	/** Crear/modificar socios        	*/
		BORRAR_SOCIO,    	/** Borrar socios                 	*/
		CREAR_TARIFA,    	/** Crear/modificar tarifas       	*/
		BORRAR_TARIFA,   	/** Borrar tarifas                	*/
		PRESTAR_DEVOLVER,	/** Prestar y devolver            	*/
		CAMBIAR_TASAS,   	/** Cambiar las tasas             	*/
		VER_MOROSOS,     	/** Ver la lista de socios morosos	*/
		VER_TOP10,       	/** Ver los articulos mas sacados 	*/
	};

	/* ================================
	 *   Atributos
	 * ================================
	 */
	/** Identificador interno de JPA */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	/** Nombre del usuario usado para el login. */
	private String username;
	/** Contraseña generada a partir de la contraseña inicial. */
	private String password;
	/** Conjunto de permisos que tiene un usuario. */
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<Permisos> permisos;

	/* ================================
	 *   Constructor
	 * ================================
	 */

	/**
	 * Constructor vacio para poder usar JPA.
	 */
	protected Usuario() { }

	/**
	 * Constructor de usuario, que recibe un nombre y una contraseña y guarda el nombre y el codigo hash
	 * asociado a la contraseña.
	 * @param username
	 * @param password
	 */
	public Usuario(String username, String password) {
		this.username = username;
		this.password = Usuario.HashFunction(password);
		this.permisos = EnumSet.noneOf(Permisos.class);
	}

	/* ================================
	 *   Metodos
	 * ================================
	 */

	/**
	 * Comprueba que si unos credenciales encajan con los de este usuario.
	 * @param username Nombre de usuario
	 * @param password Contraseña
	 * @return Booleano.
	 */
	public boolean login(String username, String password) {
		return this.username.equals(username) &&
		       this.password.equals(Usuario.HashFunction(password));
	}

	/**
	 * Comprueba si el usuario tiene permiso para tomar cierta acción.
	 * @return Booleano que indica si se añadio el permiso.
	 */
	public boolean puede(Permisos permiso) {
		return this.permisos.contains(permiso);
	}

	/**
	 * Concede a un usuario cierto permiso.
	 * @return Booleano.
	 */
	public boolean permitir(Permisos permiso) {
		return this.permisos.add(permiso);
	}
	/**
	 * Revoca un permiso de un usuario.
	 * @return Booleano que indica si se revoco.
	 */
	public boolean revocar(Permisos permiso) {
		return this.permisos.remove(permiso);
	}

	/**
	 * Devuelve los permisos a un array
	 * @return array de permisos.
	 */
	public String[] getPermisos() {
		return this.permisos.toArray(new String[0]);
	}

	/**
	 * Funcion hash usada para generar el codigo hash de la contraseña.
	 * @param password String de la contraseña
	 * @return Hash de la contraseña
	 */
	private static String HashFunction(String password) {
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(password.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	/* ================================
	 *   Metodos (overrides)
	 * ================================
	 */

	@Override
	public String toString() {
		String line = "Username=" + this.username + " Permisos:";
		if (this.permisos.size() == 0) {
			line += "Ninguno";
		}
                else {
			for (Permisos p: this.permisos)
				line += p;
			line += " ";
		}

		return line;
	}

        @Override
        public int hashCode() {
        	return 0; //TODO sino no me compila
        }

        @Override
        public boolean equals(Object o) {
        	return false; //TODO sino no me compila
        }
}
