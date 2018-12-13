package controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controllers.ControllerDoacao;

class ControllerDoacaoTest {
	
	ControllerDoacao cd;
	
	@BeforeEach
	void setUp() throws Exception {
		cd = new ControllerDoacao();
	}

	@DisplayName("Testa adicionaDoacao ok")
	@Test
	void testAdiciona() {
		assertEquals("11/10/2018 - doador: Cave Johnson/18304715242, item: cadeira de rodas, quantidade: 7, receptor: Luiza Elisa Lopes/72859801000118", cd.adicionaDoacao("11/10/2018 - doador: Cave Johnson/18304715242, item: cadeira de rodas, quantidade: 7, receptor: Luiza Elisa Lopes/72859801000118"));	
	}
	
	@DisplayName("Testa adicionaDoacao null")
	@Test
	void testAdicionaDoacaoNull() {
		assertThrows(IllegalArgumentException.class, () -> cd.adicionaDoacao(null));	
	}
	
	@DisplayName("Testa adicionaDoacao vazia")
	@Test
	void testAdicionaDoacaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> cd.adicionaDoacao(""));	
	}
	
	@DisplayName("Testa listaDoacoes ok")
	@Test
	void testListaDoacoes() {
		cd.adicionaDoacao("15/09/2016 - doador: Lucas Fernandes/13507190272, item: camiseta, quantidade: 100, receptor: Murilo Luiz Brito/84473712044");
		cd.adicionaDoacao("11/10/2018 - doador: Cave Johnson/18304715242, item: cadeira de rodas, quantidade: 7, receptor: Luiza Elisa Lopes/72859801000118");
		
		assertEquals("15/09/2016 - doador: Lucas Fernandes/13507190272, item: camiseta, quantidade: 100, receptor: Murilo Luiz Brito/84473712044" + 
				" | 11/10/2018 - doador: Cave Johnson/18304715242, item: cadeira de rodas, quantidade: 7, receptor: Luiza Elisa Lopes/72859801000118", cd.listaDoacoes());	
	}
	

}