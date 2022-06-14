package com.Inventario.controlador;

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

import com.Inventario.model.Categoria;
import com.Inventario.response.CategoriaResponseRest;
import com.Inventario.servicio.ICategoriaServicio;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class CategoriaRestController {

	@Autowired
	private ICategoriaServicio servicio;

	/**
	 * get all the categories
	 * 
	 * @return
	 */
	@GetMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> searchCategorias() {

		ResponseEntity<CategoriaResponseRest> response = servicio.search();
		return response;

	}

	/**
	 * get categories by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> searchCategoriasById(@PathVariable Long id) {

		ResponseEntity<CategoriaResponseRest> response = servicio.searchById(id);
		return response;

	}
	
	/**
	 * save categories
	 * @param Categoria
	 * @return
	 */
	@PostMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> save(@RequestBody Categoria categoria) {

		ResponseEntity<CategoriaResponseRest> response = servicio.save(categoria);
		return response;

	}
	
	/**
	 * update de categoria
	 * @param categoria
	 * @param id
	 * @return
	 */
	@PutMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> update(@RequestBody Categoria categoria,@PathVariable Long id) {

		ResponseEntity<CategoriaResponseRest> response = servicio.update(categoria,id);
		return response;

	}
	
	/**
	 * delete categoria por id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> delete(@PathVariable Long id) {

		ResponseEntity<CategoriaResponseRest> response = servicio.deleteById(id);
		return response;

	}
}
