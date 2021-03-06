package com.Inventario.controlador;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Inventario.model.Producto;
import com.Inventario.response.ProductoResponseRest;
import com.Inventario.servicio.IProductoService;
import com.Inventario.util.Util;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductoRestController {
	private IProductoService productoServicio;
	
	
	
	public ProductoRestController(IProductoService productoServicio) {
		super();
		this.productoServicio = productoServicio;
	}



	/*
	 * 
	 */
	@PostMapping("productos")
	public ResponseEntity<ProductoResponseRest>save(
			@RequestParam("picture") MultipartFile picture,
			@RequestParam("nombre")String nombre,
			@RequestParam("precio")int precio,
			@RequestParam("cantidad")int cantidad,
			@RequestParam("categoriaId")Long categoriaId) throws IOException
	{
		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setCantidad(cantidad);
		producto.setPicture(Util.compressZLib(picture.getBytes()));
		System.out.println(Util.compressZLib(picture.getBytes()));
		
		ResponseEntity<ProductoResponseRest> response = productoServicio.save(producto, categoriaId);
		return response;
	}
}
