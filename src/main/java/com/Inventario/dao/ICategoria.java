package com.Inventario.dao;

import org.springframework.data.repository.CrudRepository;

import com.Inventario.model.Categoria;

public interface ICategoria extends CrudRepository<Categoria, Long>{
	
	
}
