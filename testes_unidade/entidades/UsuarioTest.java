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
	
	@DisplayName("Testa construtor nome invalido")
	@Test
	void testUsuario1() {
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", null, "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", "DOADOR", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", "DOADOR", 1));
	}
	
	@DisplayName("Testa construtor id invalido")
	@Test
	void testUsuario2() {
		assertThrows(IllegalArgumentException.class, () -> new Usuario("", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", "DOADOR", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", "DOADOR", 1));
	}
	
	@DisplayName("Testa construtor email invalido")
	@Test
	void testUsuario3() {
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "Elizabeth Ashe", "", "(83) 92918-0211", "PESSOA_FISICA", "DOADOR", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "Elizabeth Ashe", null, "(83) 92918-0211", "PESSOA_FISICA", "DOADOR", 1));
	}
	
	@DisplayName("Testa construtor telefone invalido")
	@Test
	void testUsuario4() {
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "", "PESSOA_FISICA", "DOADOR", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", null, "PESSOA_FISICA", "DOADOR", 1));
	}
	
	@DisplayName("Testa construtor classe invalida")
	@Test
	void testUsuario5() {
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "", "DOADOR", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", null, "DOADOR", 1));
	}
	
	@DisplayName("Testa construtor status invalida")
	@Test
	void testUsuario6() {
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", "", 1));
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", null, 1));
	}
	
	@DisplayName("Testa atualiza usuario ok")
	@Test
	void testAtualizaUsuario() {
		usuario.atualizaUsuario("deb", "novo@email", "(83) 0000-1111");
		assertEquals("deb/1001, novo@email, (83) 0000-1111, status: doador", usuario.toString());
	}
	
	@DisplayName("Testa atualiza usuario nome null")
	@Test
	void testAtualizaUsuario1() {
		usuario.atualizaUsuario(null, "novoEmaik@deadlock.com", "(83) 92918-0211");
		assertEquals("Elizabeth Ashe/1001, novoEmaik@deadlock.com, (83) 92918-0211, status: doador", usuario.toString());
	}
	
	@DisplayName("Testa atualiza usuario nome vazio")
	@Test
	void testAtualizaUsuario2() {
		usuario.atualizaUsuario("", "novoEmaik@deadlock.com", "(83) 92918-0211");
		assertEquals("Elizabeth Ashe/1001, novoEmaik@deadlock.com, (83) 92918-0211, status: doador", usuario.toString());
	}
	
	@DisplayName("Testa atualiza usuario telefone null")
	@Test
	void testAtualizaUsuario3() {
		usuario.atualizaUsuario("Elizabeth Ashe", "novoEmaik@deadlock.com", null);
		assertEquals("Elizabeth Ashe/1001, novoEmaik@deadlock.com, (83) 92918-0211, status: doador", usuario.toString());
	}
	
	@DisplayName("Testa atualiza usuario telefone vazio")
	@Test
	void testAtualizaUsuario4() {
		usuario.atualizaUsuario("Elizabeth Ashe", "novoEmaik@deadlock.com", "");
		assertEquals("Elizabeth Ashe/1001, novoEmaik@deadlock.com, (83) 92918-0211, status: doador", usuario.toString());
	}
	
	@DisplayName("Testa atualiza usuario email null")
	@Test
	void testAtualizaUsuario5() {
		usuario.atualizaUsuario("Elizabeth Ashe", null, "(83) 92918-0211");
		assertEquals("Elizabeth Ashe/1001, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", usuario.toString());
	}
	
	@DisplayName("Testa atualiza usuario email vazio")
	@Test
	void testAtualizaUsuario6() {
		usuario.atualizaUsuario("Elizabeth Ashe", "", "(83) 92918-0211");
		assertEquals("Elizabeth Ashe/1001, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", usuario.toString());
	}
	
	@DisplayName("Testa cadastra item ok")
	@Test
	void testCadastraItem() {
		usuario.cadastraItem(2, "cadeira de rodas", 5, "roda grande,cadeira");
		assertEquals("2 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5", usuario.exibeItem(2));
	}
	
	@DisplayName("Testa cadastra item descritor null")
	@Test
	void testCadastraItem1() {
		assertThrows(IllegalArgumentException.class, () -> usuario.cadastraItem(18, null, 5, "roda grande,cadeira"));
	}
	
	@DisplayName("Testa cadastra item descritor vazio")
	@Test
	void testCadastraItem2() {
		assertThrows(IllegalArgumentException.class, () -> usuario.cadastraItem(3, "", 6, ""));	
	}
	
	@DisplayName("Testa cadastra item quantidade invalida")
	@Test
	void testCadastraItem3() {
		assertThrows(IllegalArgumentException.class, () -> usuario.cadastraItem(18, "cadeira de rodas", -5, "roda grande,cadeira"));	
	}
	
	@DisplayName("Testa cadastra item que ja existe, apenas atualiza a quantidade.")
	@Test
	void testCadastraItem4() {
		assertEquals(0, usuario.cadastraItem(6, "colchao", 7, "colchao kingsize,conforto,dormir"));
		assertEquals("0 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 7", usuario.exibeItem(0));
	}
	
	@DisplayName("Testa exibe item ok ")
	@Test
	void testExibeItem() {
		assertEquals("0 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 5", usuario.exibeItem(0));
		assertEquals("1 - cobertor, tags: [lencol, conforto], quantidade: 18", usuario.exibeItem(1));
	}
	
	@DisplayName("Testa exibe item invalido")
	@Test
	void testExibeItem1() {
		assertThrows(IllegalArgumentException.class, () -> usuario.exibeItem(6));
	}
	
	@DisplayName("Testa exibe item de usuario que nao possui itens")
	@Test
	void testExibeItem2() {
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1003", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", "DOADOR", 1).exibeItem(0));
	}
	
	@DisplayName("Testa atualiza item")
	@Test
	void testAtualizaItem() {
		usuario.atualizaItem(0, "colchao dormir", 1);
		usuario.atualizaItem(1, "lencol, conforto", 2);
		assertEquals("0 - colchao, tags: [colchao dormir], quantidade: 1", usuario.exibeItem(0));
		assertEquals("1 - cobertor, tags: [lencol, conforto], quantidade: 2", usuario.exibeItem(1));
	}
	
	@DisplayName("Testa atualiza item id < 0")
	@Test
	void testAtualizaItem1() {
		assertThrows(IllegalArgumentException.class, () -> usuario.atualizaItem(-3, "colchao dormir", 1));

	}
	
	@DisplayName("Testa atualiza item id inexistente")
	@Test
	void testAtualizaItem2() {
		assertThrows(IllegalArgumentException.class, () -> usuario.atualizaItem(17, "lencol, conforto", 2));

	}
	
	@DisplayName("Testa atualiza item quantidade < 0")
	@Test
	void testAtualizaItem3() {
		usuario.atualizaItem(0, "", -1);
		assertEquals("0 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 5", usuario.exibeItem(0));

	}
	
	@DisplayName("Testa remove item ok")
	@Test
	void testRemoveItem() {
		usuario.removeItem(0);
		assertThrows(IllegalArgumentException.class, () -> usuario.exibeItem(0));
	}
	
	@DisplayName("Testa remove item id invalido")
	@Test
	void testRemoveItem1() {
		assertThrows(IllegalArgumentException.class, () -> usuario.removeItem(-1));
	}
	
	@DisplayName("Testa remove item id inexistente")
	@Test
	void testRemoveItem2() {
		assertThrows(IllegalArgumentException.class, () -> usuario.removeItem(8));
	}
	
	@DisplayName("Testa remove item de usuario que nao possui itens")
	@Test
	void testRemoveItem3() {
		assertThrows(IllegalArgumentException.class, () -> new Usuario("1003", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA", "DOADOR", 1).removeItem(0));
	}
	
	@DisplayName("Testa compareTo")
	@Test
	void testCompareTo() {
		Usuario usuario3 = new Usuario("1003" ,"Arthur Morgan", "arthur.morgan@vanderlyne.com", "(63) 99983-1899", "PESSOA_FISICA", "DOADOR", 3);
		assertEquals(1, usuario3.compareTo(usuario2));
		assertEquals(-1, usuario.compareTo(usuario2));
	}
	
	@DisplayName("Testa equals")
	@Test
	void testEquals() {
		Usuario usuarioLocal = new Usuario("1002", "Satya Vaswani", "satya@vishkarcorp.com", "(83) 99221-2571", "PESSOA_FISICA", "DOADOR", 2);
		assertEquals(false, usuario.equals(usuario2));
		assertEquals(true, usuario2.equals(usuarioLocal));
		assertEquals(false, usuarioLocal.equals(usuario));
	}
	
	@DisplayName("Testa equals")
	@Test
	void testEquals1() {
		assertEquals(false, usuario.equals(null));
	}
	
	@DisplayName("Testa toString ok")
	@Test
	void testToString() {
		usuario.atualizaUsuario("deb", "elizabethcalamity@deadlock.com", "(83) 92918-0211");
		assertEquals("deb/1001, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", usuario.toString());
	}
	

}
