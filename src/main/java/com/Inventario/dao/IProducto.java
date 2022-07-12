package com.Inventario.dao;

import org.springframework.data.repository.CrudRepository;

import com.Inventario.model.Producto;


public interface IProducto extends CrudRepository<Producto, Long> {

}
