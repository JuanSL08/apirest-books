package com.tismart.books.backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.tismart.books.backend.model.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {

}
