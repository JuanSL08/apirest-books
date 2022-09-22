package com.tismart.books.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tismart.books.backend.model.Libro;
import com.tismart.books.backend.response.LibroResponseRest;
import com.tismart.books.backend.service.ILibroService;

@RestController
@RequestMapping("/v1")
public class LibroRestController {

	@Autowired
	private ILibroService serviceLibro;
	
	@GetMapping("/libros")
	public ResponseEntity<LibroResponseRest> consultaLib() {
		//ResponseEntity<LibroResponseRest> response = serviceLibro.buscarLibros();
		//return response;
		return serviceLibro.buscarLibros();
	}
	
	@GetMapping("/libros/{id}")
	public ResponseEntity<LibroResponseRest> consultaPorId(@PathVariable Long id) {
		return serviceLibro.buscarPorId(id);
	}
	
	@PostMapping("/libros")
	public ResponseEntity<LibroResponseRest> crear(@RequestBody Libro request) {
		return serviceLibro.crear(request);
	}
	
	@PutMapping("/libros/{id}")
	public ResponseEntity<LibroResponseRest> actualizar(@RequestBody Libro request, @PathVariable Long id) {
		return serviceLibro.actualizar(request, id);
	}
	
	@DeleteMapping("libros/{id}")
	public ResponseEntity<LibroResponseRest> eliminar(@PathVariable Long id){
		return serviceLibro.eliminar(id);
	}
	
}
