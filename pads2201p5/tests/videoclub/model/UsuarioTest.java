package videoclub.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;

import videoclub.model.Usuario;
import videoclub.model.Usuario.Permisos;

public class UsuarioTest {

	static Usuario usr1, usr2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		usr1 = new Usuario("Gerente", "Juan_de_Lara");
		usr2 = new Usuario("root", "rootñ");
	}

	@Test
	public void test_login() {
		// Logins normales
		assertTrue("Login gerente correcto", usr1.login("Gerente", "Juan_de_Lara") );
		assertTrue("Login root correcto",    usr2.login("root", "rootñ") );

		// Estos logins deben fallar
		assertFalse("Login gerente fallido (no password)",    usr1.login("Gerente", ""));
		assertFalse("Login gerente fallido (password root)",  usr2.login("Gerente", "rootñ"));
		assertFalse("Login gerente fallido (pasword random)", usr1.login("Gerente", "añesartnoc"));

	}
	
	@Test
	public void testPuedePermitirRevocarPermiso() {
		for (Permisos perm: Permisos.values()) {
			assertFalse("Usuario creado sin permisos", usr1.puede(perm));
		}
		
		assertTrue("Añadimos cierto permiso",                     usr1.permitir(Permisos.VER_TOP10));
		assertTrue("Comprobamos que tiene el permiso",            usr1.puede(Permisos.VER_TOP10));
		assertTrue("Quitamos ese permiso",                        usr1.revocar(Permisos.VER_TOP10));
		assertFalse("Comprobamos que ya no tiene el permiso",     usr1.puede(Permisos.VER_TOP10));     
	}
	
	
}