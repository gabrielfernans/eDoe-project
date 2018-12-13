package entidades;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsuarioTest {

	Usuario usuario;
	Usuario usuario2;
	@BeforeEach
	void setUp() throws Exception { 
		usuario = new Usuario("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", "DOADOR", 1);
		usuario2 = new Usuario("1002", "Satya Vaswani", "satya@vishkarcorp.com", "(83) 99221-2571", "PESSOA_FISICA", "DOADOR", 2);
		usuario.cadastraItem(0, "colchao", 5, "colchao kingsize,conforto,dormir");
		usuario.cadastraItem(1, "cobertor", 18, "lencol,conforto");
	}
	
	@DisplayName("Testa construtor ok")
	@Test
	void testUsuario() {
		assertEquals("Elizabeth Ashe/1001, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", usuario.toString());
	}
	
	@DisplayName("Testa construtor inválido")
	@Test
	void testUsuario1() {
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", "DOADOR", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", "DOADOR", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "Elizabeth Ashe", "", "(83) 92918-0211", "PESSOA_FISICA", "DOADOR", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "", "PESSOA_FISICA", "DOADOR", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", null, "DOADOR", 1));
	}
	
	@DisplayName("Testa atualiza usuario ok")
	@Test
	void testAtualizaUsuario() {
		usuario.atualizaUsuario("Elizabeth Ashe", "novoEmaik@deadlock.com", "(83) 92918-0211");
		assertEquals("Elizabeth Ashe/1001, novoEmaik@deadlock.com, (83) 92918-0211, status: doador", usuario.toString());
	}
	
	@DisplayName("Testa atualiza usuario nome null")
	@Test
	void testAtualizaUsuario1() {
		usuario.atualizaUsuario(null, "novoEmaik@deadlock.com", "(83) 92918-0211");
		assertEquals("Elizabeth Ashe/1001, novoEmaik@deadlock.com, (83) 92918-0211, status: doador", usuario.toString());
	}
	
	@DisplayName("Testa atualiza usuario telefone null")
	@Test
	void testAtualizaUsuario2() {
		usuario.atualizaUsuario("Elizabeth Ashe", "novoEmaik@deadlock.com", null);
		assertEquals("Elizabeth Ashe/1001, novoEmaik@deadlock.com, (83) 92918-0211, status: doador", usuario.toString());
	}
	
	@DisplayName("Testa atualiza usuario email null")
	@Test
	void testAtualizaUsuario3() {
		usuario.atualizaUsuario("Elizabeth Ashe", null, "(83) 92918-0211");
		assertEquals("Elizabeth Ashe/1001, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", usuario.toString());
	}
	
	@DisplayName("Testa cadastra item")
	@Test
	void testUsuario3() {
		usuario.cadastraItem(2, "cadeira de rodas", 5, "roda grande,cadeira");
		assertEquals("2 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5", usuario.exibeItem(2));
	}
	
	@DisplayName("Testa cadastra item descritor invalido")
	@Test
	void testCadastraItem() {
		assertThrows(IllegalArgumentException.class, () -> usuario.cadastraItem(18, null, 5, "roda grande,cadeira"));
		assertThrows(IllegalArgumentException.class, () -> usuario.cadastraItem(3, "", 6, ""));	
	}
	
	@DisplayName("Testa cadastra item quantidade invalida")
	@Test
	void testCadastraItem1() {
		assertThrows(IllegalArgumentException.class, () -> usuario.cadastraItem(18, "cadeira de rodas", -5, "roda grande,cadeira"));	
	}
	
	@DisplayName("Testa exibe item")
	@Test
	void testUsuario5() {
		assertEquals("0 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 5", usuario.exibeItem(0));
		assertEquals("1 - cobertor, tags: [lencol, conforto], quantidade: 18", usuario.exibeItem(1));
	}
	
	@DisplayName("Testa exibe item invalido")
	@Test
	void testUsuario6() {
		assertThrows(IllegalArgumentException.class, () -> usuario2.exibeItem(0));
		assertThrows(IllegalArgumentException.class, () -> usuario.exibeItem(8));
	}
	
	@DisplayName("Testa atualiza item")
	@Test
	void testUsuario7() {
		usuario.atualizaItem(0, "colchao dormir", 1);
		usuario.atualizaItem(1, "lencol, conforto", 2);
		assertEquals("0 - colchao, tags: [colchao dormir], quantidade: 1", usuario.exibeItem(0));
		assertEquals("1 - cobertor, tags: [lencol, conforto], quantidade: 2", usuario.exibeItem(1));
	}
	
	@DisplayName("Testa atualiza item invalido")
	@Test
	void testUsuario8() {
		assertThrows(IllegalArgumentException.class, () -> usuario.atualizaItem(-3, "colchao dormir", 1));
		assertThrows(IllegalArgumentException.class, () -> usuario.atualizaItem(17, "lencol, conforto", 2));

	}
	
	@DisplayName("Testa remove item")
	@Test
	void testUsuario9() {
		usuario.removeItem(0);
		assertThrows(IllegalArgumentException.class, () -> usuario.exibeItem(0));
	}
	
	@DisplayName("Testa remove item invalido")
	@Test
	void testUsuario10() {
		assertThrows(IllegalArgumentException.class, () -> usuario.exibeItem(-1));
		assertThrows(IllegalArgumentException.class, () -> usuario2.exibeItem(0));
		assertThrows(IllegalArgumentException.class, () -> usuario.exibeItem(88));
	}
	
	@DisplayName("Testa compareTo")
	@Test
	void testUsuario11() {
		Usuario usuario3 = new Usuario("1003" ,"Arthur Morgan", "arthur.morgan@vanderlyne.com", "(63) 99983-1899", "PESSOA_FISICA", "DOADOR", 3);
		assertEquals(1, usuario3.compareTo(usuario2));
		assertEquals(-1, usuario.compareTo(usuario2));
	}
	
	@DisplayName("Testa equals")
	@Test
	void testUsuario12() {
		Usuario usuarioLocal = new Usuario("1002", "Satya Vaswani", "satya@vishkarcorp.com", "(83) 99221-2571", "PESSOA_FISICA", "DOADOR", 2);
		assertEquals(false, usuario.equals(usuario2));
		assertEquals(true, usuario2.equals(usuarioLocal));
		assertEquals(false, usuarioLocal.equals(usuario));
	}
	

}
