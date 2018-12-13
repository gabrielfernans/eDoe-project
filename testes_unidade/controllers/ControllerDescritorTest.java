package controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controllers.ControllerDescritor;

class ControllerDescritorTest {
	
	ControllerDescritor controllerDescritor;
	
	@BeforeEach
	void setUp() throws Exception {
		controllerDescritor = new ControllerDescritor();
		controllerDescritor.cadastraDescritor("cadeira de rodas");
		controllerDescritor.cadastraDescritor("Playstation 4");
	}

	@DisplayName("Testa cadastrarDescritor null")
	@Test
	void testCadastraDescritorNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerDescritor.cadastraDescritor(null));	
	}
	
	@DisplayName("Testa cadastrarDescritor vazio")
	@Test
	void testCadastraDescritorVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerDescritor.cadastraDescritor(""));	
	}
	
	@DisplayName("Testa cadastrarDescritor ja existente")
	@Test
	void testCadastraDescritorExistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerDescritor.cadastraDescritor("cadeira de rodas"));	
	}
	
	@DisplayName("Testa contemDescritor ok")
	@Test
	void testContemDescritor() {
		assertTrue(controllerDescritor.contemDescritor("cadeira de rodas"));
		assertTrue(controllerDescritor.contemDescritor("playstation 4"));
		assertFalse(controllerDescritor.contemDescritor("Playstation 4"));
	}
	
	@DisplayName("Testa getDescritores ok")
	@Test
	void testGetDescritores() {
		Set<String> desc = new HashSet<String>();
		desc.add("cadeira de rodas");
		desc.add("Playstation 4".toLowerCase());
		assertEquals(desc, controllerDescritor.getDescritores());
	}

}