package com.tismart.books.backend.service;

import org.springframework.http.ResponseEntity;

import com.tismart.books.backend.model.Libro;
import com.tismart.books.backend.response.LibroResponseRest;

public interface ILibroService {

	public ResponseEntity<LibroResponseRest> buscarLibros();
	public ResponseEntity<LibroResponseRest> buscarPorId(Long id);
	public ResponseEntity<LibroResponseRest> crear(Libro libro);
	public ResponseEntity<LibroResponseRest> actualizar(Libro libro, Long id);
	public ResponseEntity<LibroResponseRest> eliminar(Long id);
	
}
