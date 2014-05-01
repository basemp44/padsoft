package videoclub.model;

import javax.persistence.*;

import java.util.List;
import java.util.ArrayList;

import org.joda.time.DateTime;

/**
 * Clase encargada de gestionar socios
 * @author victor
 */
@Entity
public class Socio {

	/* ================================
	 *   Atributos
	 * ================================
	 */
	/** Identificado para JPA */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	/** Identificador unico del socio */
	@Column(nullable = false, unique = true)
	private String uid;
	/** DNI o NIA */
	@Column(nullable = false, unique = true)
	private String dni;
	/** Nombre completo del socio */
	private String nombre;
	/** Numero de telefono */
	private String telefono;
	/** Direccion completa */
	private String direccion;
	/** Email */
	private String email;
	/** Fecha en la que contrato la ultima tarifa */
	private DateTime fechaFinContrato;
	/** Ultima tarifa contratada por el socio */
	@OneToOne
	private Tarifa tarifa;
	/** Lista de copias que tiene el socio */
	@OneToMany(mappedBy="socio")
	private List<Copia> copias;

	/** Videoclub al que pertenence este socio. */
	@Transient
	private Videoclub videoclub;


	/* ================================
	 *   Constructores
	 * ================================
	 */

	/**
	 * Constructor vacio para poder usar JPA.
	 */
	protected Socio() { }

	/**
	 * Constructor minimo de la clase.
	 * @param vc Videoclub al que esta asociado.
	 * @param uid Identificador unico.
	 * @param dni DNI o NIA del socio.
	 */
	protected Socio(Videoclub vc, String uid, String dni) {
		this.uid                = uid;
		this.dni                = dni;
		this.copias             = new ArrayList<Copia>();
		this.nombre             = ""; // Para simplificar
		this.telefono           = null;
		this.direccion          = null;
		this.fechaFinContrato   = null;
		this.videoclub          = vc;
		this.tarifa             = null;
	}

	/* ================================
	 *   Metodos
	 * ================================
	 */

	/**
	 * Devuelve un booleano indicando si el socio es moroso.
	 * @return Booleano
	 */
	public boolean esMoroso() {
		return (this.gradoMorosidad() > 0);
	}

	/**
	 * Devuelve un numero indicando el numero de dias totales de retraso,
	 * es decir, la suma de los dias de retraso de cada copia en su poder.
	 * Para ello, debe iterar a traves de todos sus prestamos.
	 * @return Booleano
	 */
	public int gradoMorosidad() {
		int grado = 0;
		for (Copia copia: this.copias) {
			grado += copia.diasRetraso();
		}
		return grado;
	}

	/**
	 * Devuelve la tarifa activa del socio.
	 * Solo cuando se llama a esta funcion se comprueba si la
	 * tarifa ha expirado. Esta funcion no debe ser llamada muy a menudo.
	 * @return Tarifa
	 */
	public Tarifa getTarifa() {
		if (null == this.fechaFinContrato) return this.videoclub.getTarifaEstandar();

		DateTime now = new DateTime();
		if (now.compareTo(this.fechaFinContrato) > 0) {
			this.tarifa = null;
			return this.videoclub.getTarifaEstandar();
		}

		return this.tarifa;
	}

	/**
	 * Contrata una tarifa.
	 * @param tarifa Tarifa a contratar
	 * @return Booleano que indica si se contrato
	 */
	public boolean contratarTarifa(Tarifa tarifa) {
		if (null != this.fechaFinContrato) return false;

		this.tarifa = tarifa;
		this.fechaFinContrato = new DateTime().plus(tarifa.getDuracion());
		return true;
	}

	/**
	 * Presta una lista de copias al usuario
	 * Solo podran prestarse si esas copias no estaban prestadas
	 * @return
	 */
	public boolean alquilar(List<Copia> copias) {
		return this.alquilar(copias.toArray(new Copia[0]));
	}

	/**
	 * Presta una lista de copias al usuario
	 * Solo podran prestarse si esas copias no estaban prestadas
	 * @return
	 */
	public boolean alquilar(Copia... copias) {
		if (copias.length >= this.getTarifa().getMaxPrestamos()) return false;

		for (Copia c : copias) {
			if (c.estaPrestada()) return false;
		}

		for (Copia c : copias) {
			c.prestar(this);
			this.copias.add(c);
		}
		return true;
	}

	/**
	 * Devuelve las copias prestadas dejandolas en el almacen, y quitandoselas al usuario.
	 * @return
	 */
	public void devolverTodo() {
		for (Copia c : this.copias) {
			c.devolver();
		}
		this.copias.clear();
	}

	/* ================================
	 *   Setters & getters
	 * ================================
	 */

	/**
	 * Get uid
	 * @return uid
	 */
	public String getUid() {
		return this.uid;
	}
	/**
	 * Get dni
	 * @return dni
	 */
	public String getDni() {
		return this.dni;
	}
	/**
	 * Get nombre
	 * @return nombre
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * Get nombre
	 * @return nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Get telefono
	 * @return telefono
	 */
	public String getTelefono() {
		return this.telefono;
	}
	/**
	 * Set telefono
	 * @return telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * Get direccion
	 * @return direccion
	 */
	public String getDireccion() {
		return this.direccion;
	}
	/**
	 * Set direccion
	 * @param direccion
	 * @return direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Get email
	 * @return email
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * Set email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Devuelve la lista de prestamos de este usuario.
	 * @return Lista de prestamos.
	 */
	public List<Copia> getPrestamos() {
		return this.copias;
	}

	/* ================================
	 *   Metodos (overrides)
	 * ================================
	 */

	@Override
	public String toString() {
		return "Uid=" + this.uid + " Dni=" + this.dni + " Nombre=" + this.nombre;
	}
}

