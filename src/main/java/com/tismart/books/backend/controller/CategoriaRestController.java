package com.tismart.books.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tismart.books.backend.model.Categoria;
import com.tismart.books.backend.response.CategoriaResponseRest;
import com.tismart.books.backend.service.ICategoriaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class CategoriaRestController {

	@Autowired
	private ICategoriaService serviceCategoria;
	
	@GetMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> consultaCat() {
		ResponseEntity<CategoriaResponseRest> response = serviceCategoria.buscarCategorias();
		return response;
	}
	
	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> consultaPorId(@PathVariable Long id) {
		ResponseEntity<CategoriaResponseRest> response = serviceCategoria.buscarPorId(id);
		return response;
	}
	
	@PostMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> crear(@RequestBody Categoria request){
		ResponseEntity<CategoriaResponseRest> response = serviceCategoria.crear(request);
		return response;
	}
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> actualizar(@RequestBody Categoria request, @PathVariable Long id){
		ResponseEntity<CategoriaResponseRest> response = serviceCategoria.actualizar(request, id);
		return response;
	}
	
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> eliminar(@PathVariable Long id) {
		ResponseEntity<CategoriaResponseRest> response = serviceCategoria.eliminar(id);
		return response;
	}
	
}
