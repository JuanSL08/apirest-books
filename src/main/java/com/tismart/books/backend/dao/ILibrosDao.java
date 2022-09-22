package com.tismart.books.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.tismart.books.backend.model.Libro;

public interface ILibrosDao extends CrudRepository<Libro, Long> {

}
