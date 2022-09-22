package com.tismart.book.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.tismart.books.backend.dao.ICategoriaDao;
import com.tismart.books.backend.model.Categoria;
import com.tismart.books.backend.response.CategoriaResponseRest;
import com.tismart.books.backend.service.CategoriaServiceImpl;

public class CategoriaServiceImplTest {

	@InjectMocks
	CategoriaServiceImpl service;
	
	//Dependencia o Interfaz inyectada
	@Mock
	ICategoriaDao categoriaDao;
	
	List<Categoria> lista = new ArrayList<Categoria>();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.cargarCategorias();
	}
	
	@Test
	public void buscarCategoriasTest() {
		when(categoriaDao.findAll()).thenReturn(lista);
		
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
		
		assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size());
	}
	
	public void cargarCategorias() {
		Categoria cat1 = new Categoria(Long.valueOf(1), "Acción", "Libros de Acción");
		Categoria cat2 = new Categoria(Long.valueOf(2), "Aventura", "Libros de Aventura");
		Categoria cat3 = new Categoria(Long.valueOf(3), "Novela", "Libros de Novela");
		Categoria cat4 = new Categoria(Long.valueOf(4), "Suspenso", "Libros de Suspenso");
		
		lista.add(cat1);
		lista.add(cat2);
		lista.add(cat3);
		lista.add(cat4);
	}
	
}
