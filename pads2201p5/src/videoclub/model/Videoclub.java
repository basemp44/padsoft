package videoclub.model;
//copiaenconflictoaborrar
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/** Clase central del modelo.
 * @author Victor Macias
 */
public class Videoclub {

	/* ================================
	 *   Globales & constantes
	 * ================================
	 */

	/** Comparador para ordenar por el número de veces que un articulo
	 * ha sido prestado.
	 */
	private final static Comparator<Articulo> comparador_vecesPrestado =
	new Comparator<Articulo>() {
		public int compare(Articulo a1, Articulo a2) {
			return a1.getVecesPrestado() - a2.getVecesPrestado();
		}
	};

	/* ================================
	 *   Atributos
	 * ================================
	 */

	/** Conexion con la persistencia. */
	EntityManagerFactory emf;
	/** Lista de articulos */
	private Set<Articulo> articulos;
	/** Lista de empleados */
	private Usuario usuario;
	/** Lista de socios */
	private Set<Socio> socios;
	/** Tarifas validas */
	private Set<Tarifa> tarifas;
	/** Tarifa basica */
	private Tarifa tarifaEstandar = new Tarifa();

	/* ================================
	 *   Constructores
	 * ================================
	 */

	/**
	 * Constructor estandar.
	 * @param url Direccion de la base de datos a la que conectarse.
	 */
	public Videoclub(String url) {
		this.articulos = new HashSet<Articulo>();
		this.socios    = new HashSet<Socio>();
		this.tarifas   = new HashSet<Tarifa>();
		this.conectar(url);
		cargar();
		this.usuario   = new Usuario("admin", "admin");
	}

	/**
	 * Crea un videoclub que guarda sus datos en memoria.
	 */
	public Videoclub() {
		this("mem:base_de_datos_en_memoria");
	}


	/* ================================
	 *   Metodos
	 * ================================
	 */

	/**
	 * Registra un articulo nuevo en la base de datos.
	 * @param articulo Articulo nuevo
	 */
	public boolean registrarArticulo(Articulo articulo) {
		this.persistir(articulo);
		return this.articulos.add(articulo);
	}

	/**
	 * Da de alta un socio en el videoclub.
	 * @param socio Socio nuevo
	 */
	public boolean registrarSocio(String uid, String dni, String nombre, String telefono, String direccion, String email) {
		Socio socio = new Socio(this, uid, dni);
		socio.setNombre(nombre);
		socio.setTelefono(telefono);
		socio.setDireccion(direccion);
		socio.setEmail(email);
		this.persistir(socio);
		return this.socios.add(socio);
	}

	/**
	 * Da de alta un socio en el videoclub.
	 * @param socio Socio nuevo
	 */
	public String registrarSocio(String dni, String nombre, String telefono, String direccion, String email) {
		String uid = UUID.randomUUID().toString().substring(0, 6);
		this.registrarSocio(uid, dni, nombre, telefono, direccion, email);
		return uid;
	}

	/**
	 * Registra una tarifa nueva en la base de datos.
	 * @param articulo Articulo nuevo
	 */
	public boolean registrarTarifa(Tarifa tarifa) {
		this.persistir(tarifa);
		return this.tarifas.add(tarifa);
	}

	/**
	 * Busca un articulo en la base de datos.
	 * @param pred Predicado de busqueda.
	 */
	public Set<Articulo> buscarArticulos(Predicado pred) {
		Set<Articulo> articulos = new HashSet<>();
		for (Articulo art : this.articulos) {
			if (pred.comprobar(art)) {
				articulos.add(art);
			}
		}
		return articulos;
	}

	/**
	 * Busca un socio en la base de datos.
	 * @param UID Numero de socio
	 */
	public Socio buscarSocio(String uid) {
		for (Socio soc : this.socios) {
			if (soc.getUid() == uid) return soc;
		}
		return null;
	}

	/**
	 * Devuelve una lista con los articulos mas prestados.
	 * Si hay menos de 10 articulos, habra nulls en la lista.
	 * @return Lista de articulos.
	 */
	public List<Articulo> top10(int n) {		
		ArrayList<Articulo> articulos_ordenados = new ArrayList<Articulo>(this.articulos);
		Collections.sort(articulos_ordenados, Videoclub.comparador_vecesPrestado);
		
		return articulos_ordenados.subList(0, Math.min(10, this.articulos.size()));
	}

	/**
	 * Devuelve una lista con los 10 articulos mas prestados.
	 * Vease la documentacion de top10(n).
	 * @return Lista de articulos.
	 */
	public List<Articulo> top10() {
		return this.top10(10);
	}

	/**
	 * Devuelve una lista con los morosos.
	 * @return List<Socio>
	 */
	public List<Socio> morosos() {
		List<Socio> lista = new ArrayList<Socio>();
		for (Socio socio : this.socios) {
			if (socio.esMoroso()) lista.add(socio);
		}
		return lista;
	}

	public boolean login(String username, String password) {
		return this.usuario.login(username, password);
	}

        /* ================================
         *   Persistencia
         * ================================
         */

	/**
	 * Carga los datos de la base de datos a la que esta conectado.
	 */
	public void cargar() {
		if (null == this.emf) {
			throw new RuntimeException("Videoclub no asociado a ninguna base de datos.");
		}

		EntityManager em = this.emf.createEntityManager();
		em.getTransaction().begin();

		try {
			// ARTICULOS
			List<?> articulos = em.createQuery("SELECT o FROM Articulo o").getResultList();
			for (Object elem : articulos) this.articulos.add((Articulo) elem);
			// USUARIOS
			List<?> usuarios = em.createQuery("SELECT o FROM Usuario o").getResultList();
			for (Object elem : usuarios) this.usuario = ((Usuario) elem);
			// SOCIOS
			List<?> socios = em.createQuery("SELECT o FROM Socio o").getResultList();
			for (Object elem : socios) this.socios.add((Socio) elem);
			// TARIFAS
			List<?> tarifas = em.createQuery("SELECT o FROM Tarifa o").getResultList();
			for (Object elem : tarifas) this.tarifas.add((Tarifa) elem);

		} finally {
			em.getTransaction().commit();
			em.close();
		}
	}

	/**
	 * Persiste un objeto (funcion interna de videoclub).
	 * @param obj Objeto a ser persistido.
	 */
	private void persistir(Object obj) {
		try {
			EntityManager em = this.emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		}
		catch (Exception e) {
			// Encapsulamos la excepcion con un mensaje de error de mas alto
			// nivel, pero con la excepcion encadenada para poder depurar.
			throw new RuntimeException("Error persistiendo un objeto.", e);
		}
	}

	/**
	 * Conecta el videoclub a una base de datos.
	 * @param url Direccion de la base de datos.
	 */
	protected void conectar(String url) {
		if (null != this.emf) {
			throw new RuntimeException("Videoclub ya esta asociado a una base de datos.");
		}

		HashMap<String, Object> configOverrides = new HashMap<String, Object>();
		configOverrides.put("javax.persistence.jdbc.url", "jdbc:hsqldb:" + url);
		this.emf = Persistence.createEntityManagerFactory("videoclub", configOverrides);
	}

	/**
	 * Cierra la conexion del videoclub con la base de datos.
	 * Esta funcion deberia ser llamada al finalizar el programa
	 * para asegurar la integridad de los datos.
	 */
	public void cerrar() {
		if (null == this.emf) {
			throw new RuntimeException("Videoclub no asociado a ninguna base de datos.");
		}
		emf.close();
		emf = null;
	}

	/* ================================
	 *   Setters & getters
	 * ================================
	 */
	/**
	 * Get articulo
	 * @return articulo
	 */
	public Set<Articulo> getArticulos() {
		return Collections.unmodifiableSet(this.articulos);
	}
	/**
	 * Get usuario
	 * @return usuario
	 */
	public Usuario getUsuario() {
		return this.usuario;
	}
	/**
	 * Set usuario
	 * @return usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
	 * Get socio
	 * @return socio
	 */
	public Set<Socio> getSocios() {
		return Collections.unmodifiableSet(this.socios);
	}
	/**
	 * Get tarifa
	 * @return tarifa
	 */
	public Set<Tarifa> getTarifas() {
		return Collections.unmodifiableSet(this.tarifas);
	}

	/**
	 * Get tarifaEstandar
	 * @return tarifaEstandar
	 */
	public Tarifa getTarifaEstandar() {
		return this.tarifaEstandar;
	}
	/**
	 * Set tarifaEstandar
	 * @param tarifaEstandar.
	 */
	public void setTarifaEstandar(Tarifa tarifa) {
		this.tarifaEstandar = tarifa;
	}
}
