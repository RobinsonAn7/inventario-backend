package com.Inventario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Inventario.model.Producto;


public interface IProducto extends CrudRepository<Producto, Long> {
	
	@Query("select p from Producto p where p.nombre like %?1%")
	List<Producto> findByNombreLike(String nombre);
	
	List<Producto> findByNombreContainingIgnoreCase(String nombre);
	
}
