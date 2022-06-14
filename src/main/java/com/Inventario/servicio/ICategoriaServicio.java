package com.Inventario.servicio;

import org.springframework.http.ResponseEntity;

import com.Inventario.model.Categoria;
import com.Inventario.response.CategoriaResponseRest;

public interface ICategoriaServicio {

	public ResponseEntity<CategoriaResponseRest> search();
	public ResponseEntity<CategoriaResponseRest> searchById(Long id);
	public ResponseEntity<CategoriaResponseRest> save(Categoria categoria);
	public ResponseEntity<CategoriaResponseRest> update(Categoria categoria, Long id);
	public ResponseEntity<CategoriaResponseRest> deleteById(Long id);
}				
