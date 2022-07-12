package com.Inventario.servicio;

import org.springframework.http.ResponseEntity;

import com.Inventario.model.Producto;
import com.Inventario.response.ProductoResponseRest;

public interface IProductoService {
	
	public ResponseEntity<ProductoResponseRest> save(Producto producto, Long categoryId);
	public ResponseEntity<ProductoResponseRest> searchById(Long id);
}
