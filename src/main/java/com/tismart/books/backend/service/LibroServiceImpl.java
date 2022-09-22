package com.tismart.books.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tismart.books.backend.dao.ILibrosDao;
import com.tismart.books.backend.model.Libro;
import com.tismart.books.backend.response.LibroResponseRest;

@Service
public class LibroServiceImpl implements ILibroService {

	private static final Logger log = LoggerFactory.getLogger(LibroServiceImpl.class);
	
	@Autowired
	private ILibrosDao libroDao;
	
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<LibroResponseRest> buscarLibros() {
		log.info("inicio de metodo buscarLibros()");
		LibroResponseRest response = new LibroResponseRest();
		try {
			List<Libro> list = (List<Libro>) libroDao.findAll();
			response.getLibroResponse().setLibro(list);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar libros");
			log.error("error al consultar libros: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<LibroResponseRest> buscarPorId(Long id) {
		log.info("inicio de metodo buscarPorId");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		try {
			Optional<Libro> libro = libroDao.findById(id);
			if(libro.isPresent()) {
				list.add(libro.get());
				response.getLibroResponse().setLibro(list);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				log.error("Error en consultar libro");
				response.setMetadata("Respuesta nok", "-1", "Libro no encontrado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error en consultar libro");
			response.setMetadata("Respuesta nok", "-1", "Error al consultar libro por id");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<LibroResponseRest> crear(Libro libro) {
		log.info("inicio de metodo crear libro");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		try {
			Libro libroGuardado = libroDao.save(libro);
			if(libroGuardado != null) {
				list.add(libroGuardado);
				response.getLibroResponse().setLibro(list);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				log.error("Error en grabar libro");
				response.setMetadata("Respuesta nok", "-1", "Libro no guardado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Error en grabar libro");
			response.setMetadata("Respuesta nok", "-1", "Error al grabar libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<LibroResponseRest> actualizar(Libro libro, Long id) {
		log.info("inicio de metodo actualizar libro");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		try {
			Optional<Libro> libroBuscado = libroDao.findById(id);
			if(libroBuscado.isPresent()) {
				libroBuscado.get().setNombre(libro.getNombre());
				libroBuscado.get().setDescripcion(libro.getDescripcion());
				libroBuscado.get().setCategoria(libro.getCategoria());
				
				Libro libroActualizar = libroDao.save(libroBuscado.get());
				
				if(libroActualizar != null) {
					response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
					list.add(libroActualizar);
					response.getLibroResponse().setLibro(list);
				} else {
					log.error("Error en actualizar libro");
					response.setMetadata("Respuesta nok", "-1", "Libro no actualizado");
					return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				log.error("Error en actualizar libro");
				response.setMetadata("Respuesta nok", "-1", "Libro no actualizado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error en actualizar libro", e.getMessage());
			response.setMetadata("Respuesta nok", "-1", "Error al actualizar libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<LibroResponseRest> eliminar(Long id) {
		log.info("inicio de metodo eliminar libro");
		LibroResponseRest response = new LibroResponseRest();
		try {
			libroDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Respuesta eliminada");
		} catch (Exception e) {
			log.error("Error al eliminar libro", e.getMessage());
			response.setMetadata("Respuesta nok", "-1", "Error al eliminar libro por id");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

}
