package com.Inventario.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Inventario.response.CategoriaResponseRest;
import com.Inventario.servicio.ICategoriaServicio;

@RestController
@RequestMapping("/api/v1")
public class CategoriaRestController {

	@Autowired
	private ICategoriaServicio servicio;

	@GetMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> searchCategorias() {

		ResponseEntity<CategoriaResponseRest> response = servicio.search();
		return response;

	}
}
