package entidades;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class ItemTest {

	Item item;
	@BeforeEach
	void setUp() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("macio");
		list.add("quentinho");
		item = new Item(1, 10, "cobertor", list);
	}

	@DisplayName("Testa construtor ok")
	@Test
	void testItem() {
		assertEquals("1 - cobertor, tags: [macio, quentinho], quantidade: 10", item.toString());
	}
	
	@DisplayName("Testa construtor quantidade < 0")
	@Test
	void testItem1() {
		List<String> list = new ArrayList<String>();
		list.add("macio");
		assertThrows(IllegalArgumentException.class, () -> new Item(1, -10, "cobertor", list));
	}
	
	@DisplayName("Testa construtor descritor null ")
	@Test
	void testItem2() {
		List<String> list = new ArrayList<String>();
		list.add("macio");
		assertThrows(IllegalArgumentException.class, () -> new Item(1, 10, null , list));
	}
	
	@DisplayName("Testa construtor descritor vazio ")
	@Test
	void testItem3() {
		List<String> list = new ArrayList<String>();
		list.add("macio");
		assertThrows(IllegalArgumentException.class, () -> new Item(1, 10, "" , list));
	}

	@DisplayName("Testa atualizacao de item ok ")
	@Test
	void testAtualizaItem() {
		item.atualizaItem("cheiroso, lindo", 20);
		assertEquals("1 - cobertor, tags: [cheiroso, lindo], quantidade: 20", item.toString());
	}
	
	@DisplayName("Testa atualizacao de item quantidade < 0 ")
	@Test
	void testAtualizaItem1() {
		item.atualizaItem("cheiroso, lindo", -20);
		assertEquals("1 - cobertor, tags: [cheiroso, lindo], quantidade: 10", item.toString());
	}
	
	@DisplayName("Testa atualizacao de item tags vazia")
	@Test
	void testAtualizaItem2() {
		item.atualizaItem("", 20);
		assertEquals("1 - cobertor, tags: [macio, quentinho], quantidade: 20", item.toString());
	}
	
	@DisplayName("Testa atualizacao de item tags nula")
	@Test
	void testAtualizaItem3() {
		item.atualizaItem(null, 20);
		assertEquals("1 - cobertor, tags: [macio, quentinho], quantidade: 20", item.toString());
	}

	@DisplayName("Testa toString ok ")
	@Test
	void testToString() {
		assertEquals("1 - cobertor, tags: [macio, quentinho], quantidade: 10", item.toString());
	}
	
	@DisplayName("Testa toString de item com tags vazia")
	@Test
	void testToString1() {
		assertEquals("2 - roupa, quantidade: 10", new Item(2, 10, "roupa", null).toString());
	}

	@DisplayName("Testa equals ok")
	@Test
	void testEqualsObject() {
		List<String> list = new ArrayList<String>();
		list.add("macio");
		list.add("quentinho");
		Item i2 = new Item(1, 10, "cobertor", list);
		assertEquals(true, item.equals(i2));
	}
	
	@DisplayName("Testa equals de itens diferentes")
	@Test
	void testEqualsObject1() {
		List<String> list = new ArrayList<String>();
		list.add("grosso");
		list.add("quentinho");
		Item i2 = new Item(1, 10, "cobertor", list);
		assertEquals(false, item.equals(i2));
	}

	@DisplayName("Testa compareTo ok")
	@Test
	void testCompareTo() {
		List<String> list = new ArrayList<String>();
		list.add("grosso");
		list.add("quentinho");
		Item i2 = new Item(1, 10, "cobertor", list);
		assertEquals(0, item.compareTo(i2));
	}

}
