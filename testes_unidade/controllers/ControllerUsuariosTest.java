package controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ControllerUsuariosTest {

	ControllerUsuarios controllerUsuarios;
	
	@BeforeEach
	void setUp() throws Exception {
		controllerUsuarios = new ControllerUsuarios();
		controllerUsuarios.adicionaDoador("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA");
		controllerUsuarios.adicionaDoador("1002", "Satya Vaswani", "satya@vishkarcorp.com", "(83) 99221-2571", "PESSOA_FISICA");
		controllerUsuarios.adicionaDoador("70594610435", "Gabriel Fernandes", "gabrielwebr@gmail.com", "(83) 98606-6330", "PESSOA_FISICA");
		controllerUsuarios.adicionaDoador("1234", "Manel", "manel@gmail.com", "(83) 98000-0000", "PESSOA_FISICA");
		controllerUsuarios.adicionaDoador("9090", "Fulano", "fulano@gmail.com", "(83) 98900-0000", "PESSOA_FISICA");
		controllerUsuarios.cadastraItem("1001", "cadeira de rodas", 5, "roda grande,cadeira");
		controllerUsuarios.cadastraItem("1002", "colchao", 10, "colchao kingsize,conforto,dormir");
		controllerUsuarios.cadastraItem("1234", "calca jeans", 2, "azul");
		controllerUsuarios.cadastraItem("9090", "travesseiro", 10, "travesseiro de pena");
		controllerUsuarios.cadastraItem("1001", "Livro", 10, "Infantil,Matematica,Didatico");
		controllerUsuarios.lerReceptores("arquivos_sistema/novosReceptores.csv");
		controllerUsuarios.cadastraItem("84473712044", "Livro", 1, "Infantil,Matematica,Didatico");
		
	}
	
	@DisplayName("Testa adicionaDoador null")
	@Test
	void testAdicionaDoadorNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.adicionaDoador(null, "Arthur Morgan", "arthur.morgan@vanderlyne.com", "(63) 99983-1899", "PESSOA_FISICA"));	
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.adicionaDoador("1003", "Arthur Morgan", "arthur.morgan@vanderlyne.com", "(63) 99983-1899", null));	
	}
	
	@DisplayName("Testa adicionaDoador vazio")
	@Test
	void testAdicionaDoadorVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.adicionaDoador("", "Arthur Morgan", "arthur.morgan@vanderlyne.com", "(63) 99983-1899", "PESSOA_FISICA"));	
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.adicionaDoador("1003", "Arthur Morgan", "arthur.morgan@vanderlyne.com", "(63) 99983-1899", ""));	
		
	}
	
	@DisplayName("Testa adicionaDoador doador existente")
	@Test
	void testAdicionaDoadorExistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.adicionaDoador("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA"));	
	}
	
	@DisplayName("Testa adicionaDoador classe invalida")
	@Test
	void testAdicionaDoadorClasseInvalida() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.adicionaDoador("1087", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_JUDICIARIA"));	
	}
	
	@DisplayName("Testa adicionaDoador ok")
	@Test
	void testAdicionaDoadorOk() {
		assertEquals(controllerUsuarios.adicionaDoador("1010", "Vaas Montenegro", "vaas@rook.com", "(83) 99348-2481", "PESSOA_FISICA"), "1010");	
	}
	
	
	
	
	
	
	@DisplayName("Testa pesquisaUsuarioPorId null")
	@Test
	void testPesquisaUsuarioPorIdNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorId(null));	
	}
	
	@DisplayName("Testa pesquisaUsuarioPorId vazio")
	@Test
	void testPesquisaUsuarioPorIdVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorId(""));	
	}
	
	@DisplayName("Testa pesquisaUsuarioPorId usuario inexistente")
	@Test
	void testPesquisaUsuarioPorIdInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorId("1009"));	
	}
	
	@DisplayName("Testa pesquisaUsuarioPorId ok")
	@Test
	void testPesquisaUsuarioPorIdOk() {
		assertEquals(controllerUsuarios.pesquisaUsuarioPorId("1001"), "Elizabeth Ashe/1001, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador");	
	}
	
	
	
	
	
	
	@DisplayName("Testa pesquisaUsuarioPorNome null")
	@Test
	void testPesquisaUsuarioPorNomeNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorNome(null));	
	}
	
	@DisplayName("Testa pesquisaUsuarioPorNome vazio")
	@Test
	void testPesquisaUsuarioPorNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorNome(""));	
	}
	
	@DisplayName("Testa pesquisaUsuarioPorNome usuario inexistente")
	@Test
	void testPesquisaUsuarioPorNomeInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorNome("1085"));	
	}
	
	@DisplayName("Testa pesquisaUsuarioPorNome ok")
	@Test
	void testPesquisaUsuarioPorNomeOk() {
		assertEquals(controllerUsuarios.pesquisaUsuarioPorNome("Elizabeth Ashe"), "Elizabeth Ashe/1001, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador");	
	}
	
	
	
	
	@DisplayName("Testa atualizaUsuario null")
	@Test
	void testAtualizaUsuarioNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.atualizaUsuario(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211"));	
	}
	
	@DisplayName("Testa atualizaUsuario vazio")
	@Test
	void testAtualizaUsuarioVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.atualizaUsuario("", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211"));	
	}
	
	@DisplayName("Testa atualizaUsuario usuario inexistente")
	@Test
	void testAtualizaUsuarioInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.atualizaUsuario("1562", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211"));	
	}
	
	@DisplayName("Testa atualizaUsuario ok")
	@Test
	void testAtualizaUsuarioOk() {
		assertEquals(controllerUsuarios.atualizaUsuario("1001", "Elizabeth Ashe", "elizabethcalamity@hotmail.com", "(83) 92918-0211"), "Elizabeth Ashe/1001, elizabethcalamity@hotmail.com, (83) 92918-0211, status: doador");	
	}
	
	
	
	
	@DisplayName("Testa removeUsuario null")
	@Test
	void testRemoveUsuarioNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.removeUsuario(null));	
	}
	
	@DisplayName("Testa removeUsuario vazio")
	@Test
	void testRemoveUsuarioVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.removeUsuario(""));	
	}
	
	@DisplayName("Testa removeUsuario usuario inexistente")
	@Test
	void testRemoveUsuarioInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.removeUsuario("1526352"));	
	}
	
	@DisplayName("Testa removeUsuario ok")
	@Test
	void testRemoveUsuarioOk() {
		controllerUsuarios.removeUsuario("70594610435");
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorNome("70594610435"));
	}
	
	
	
	
	@DisplayName("Testa lerReceptores ok")
	@Test
	void testLerReceptoresOk() throws IOException {
		controllerUsuarios.lerReceptores("arquivos_sistema/novosReceptores.csv");	
	}
	
	@DisplayName("Testa lerReceptores invalido")
	@Test
	void testLerReceptoresInvalido() throws IOException {
		assertThrows(IOException.class, () -> controllerUsuarios.lerReceptores("arquivos_sistemas/invalid_file"));
	}
	
	
	
	
	@DisplayName("Testa cadastraItem null")
	@Test
	void testCadastraItemNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.cadastraItem("1001", null, 2, "all-in-one"));	
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.cadastraItem(null, "computador", 2, "all-in-one"));	
	}
	
	@DisplayName("Testa cadastraItem vazio")
	@Test
	void testCadastraItemVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.cadastraItem("1001", "", 2, "all-in-one"));	
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.cadastraItem("", "computador", 2, "all-in-one"));	
	}
	
	@DisplayName("Testa cadastraItem quantidade menor que zero")
	@Test
	void testCadastraItemMenorQueZero() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.cadastraItem("1001", "computador", -1, "all-in-one"));	
	}
	
	@DisplayName("Testa cadastraItem doador inexistente")
	@Test
	void testCadastraItemInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.cadastraItem("5625", "computador", 7, "all-in-one"));	
	}
	
	@DisplayName("Testa cadastraItem ok")
	@Test
	void testCadastraItemOk() {
		assertEquals(controllerUsuarios.cadastraItem("1234", "computador", 7, "all-in-one"), 7);	
	}
	
	
	
	
	@DisplayName("Testa exibeItem quantidade menor que zero")
	@Test
	void testExibeItemMenorQueZero() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.exibeItem(-1, "1234"));	
	}
	
	@DisplayName("Testa exibeItem null")
	@Test
	void testExibeItemNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.exibeItem(1, null));		
	}
	
	@DisplayName("Testa exibeItem vazio")
	@Test
	void testExibeItemVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.exibeItem(1, ""));	
	}
	
	@DisplayName("Testa exibeItem doador inexistente")
	@Test
	void testExibeItemInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.exibeItem(4, "12543"));	
	}
	
	@DisplayName("Testa exibeItem ok")
	@Test
	void testExibeItemOk() {
		assertEquals(controllerUsuarios.exibeItem(3, "1234"), "3 - calca jeans, tags: [azul], quantidade: 2");	
	}
	
	
	
	
	
	@DisplayName("Testa atualizaItem quantidade menor que zero")
	@Test
	void testAtualizaItemMenorQueZero() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.atualizaItem(-1, "1234", 4, "azul, listrada"));	
	}
	
	@DisplayName("Testa atualizaItem null")
	@Test
	void testAtualizaItemNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.atualizaItem(3, null, 3, "azul, listrada"));		
		
	}
	
	@DisplayName("Testa atualizaItem vazio")
	@Test
	void testAtualizaItemVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.atualizaItem(3, "", 3, "azul, listrada"));	
	}
	
	@DisplayName("Testa atualizaItem doador inexistente")
	@Test
	void testAtualizaItemInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.atualizaItem(3, "25632", 3, "azul, listrada"));	
	}
	
	@DisplayName("Testa atualizaItem ok")
	@Test
	void testAtualizaItemOk() {
		assertEquals(controllerUsuarios.atualizaItem(3, "1234", 5, "azul, listrada"), "3 - calca jeans, tags: [azul, listrada], quantidade: 5");	
	}
	
	
	
	
	
	@DisplayName("Testa removeItem quantidade menor que zero")
	@Test
	void testRemoveItemMenorQueZero() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.removeItem(-1, "1234"));	
	}
	
	@DisplayName("Testa removeItem null")
	@Test
	void testRemoveItemNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.removeItem(3, null));		
	}
	
	@DisplayName("Testa removeItem vazio")
	@Test
	void testRemoveItemVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.removeItem(3, ""));	
	}
	
	@DisplayName("Testa removeItem doador inexistente")
	@Test
	void testRemoveItemInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.removeItem(3, "526541"));	
	}
	
	@DisplayName("Testa removeItem ok")
	@Test
	void testRemoveItemOk() {
		controllerUsuarios.removeItem(3, "1234");
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.removeItem(3, "1234"));	
	}
	
	
	
	
	
	
	@DisplayName("Testa pesquisaItemParaDoacaoPorDescricao null")
	@Test
	void testPesquisaItemParaDoacaoPorDescricaoNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaItemParaDoacaoPorDescricao(null));	
	}
	
	@DisplayName("Testa pesquisaItemParaDoacaoPorDescricao vazio")
	@Test
	void testPesquisaItemParaDoacaoPorDescricaoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaItemParaDoacaoPorDescricao(""));
	}
	
	@DisplayName("Testa pesquisaItemParaDoacaoPorDescricao ok")
	@Test
	void testPesquisaItemParaDoacaoPorDescricaoOk() {
		assertEquals(controllerUsuarios.pesquisaItemParaDoacaoPorDescricao("travesseiro"), "4 - travesseiro, tags: [travesseiro de pena], quantidade: 10");
	}
	
	
	
	@DisplayName("Testa match null")
	@Test
	void testMatchNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.match(null, 2));	
	}
	
	@DisplayName("Testa match vazio")
	@Test
	void testMatchVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.match("", 2));	
	}
	
	@DisplayName("Testa match usuario nao receptor")
	@Test
	void testMatchUsuarioNaoReceptor() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.match("1234", 3));	
	}
	
	@DisplayName("Testa match id negativo")
	@Test
	void testMatchIdNegativo() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.match("84473712044", -1));	
	}
	
	@DisplayName("Testa match id ok")
	@Test
	void testMatchIdOk() {
		assertEquals(controllerUsuarios.match("84473712044", 3), "3 - calca jeans, tags: [azul], quantidade: 2, doador: Manel/1234");	
	}
	
	@DisplayName("Testa match item inexistente")
	@Test
	void testMatchIdItemInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.match("84473712044", 85));
	}
	
	
	
	
	@DisplayName("Testa realizaDoacao null")
	@Test
	void testRealizaDoacaoNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.realizaDoacao(3, 3, null));
	}
	
	@DisplayName("Testa realizaDoacao vazio")
	@Test
	void testRealizaDoacaoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.realizaDoacao(3, 3, ""));
	}
	
	@DisplayName("Testa realizaDoacao descricao diferente")
	@Test
	void testRealizaDoacaoDescricaoDiferente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.realizaDoacao(3, 4, "13/12/2018"));
	}
	
	@DisplayName("Testa realizaDoacao descricao ok")
	@Test
	void testRealizaDoacaoOk() {
		assertEquals(controllerUsuarios.realizaDoacao(5, 6, "13/12/2018"), "13/12/2018 - doador: Murilo Luiz Brito/84473712044, item: livro, quantidade: 1, receptor: Elizabeth Ashe/1001");
	}
	
	
}
