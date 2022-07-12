package com.Inventario.controlador;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	 * endpoint save products
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
	
	/*
	 * endpoint search by id
	 */
	@GetMapping("/productos/{id}")
	public ResponseEntity<ProductoResponseRest>searchById(@PathVariable Long id){
		 ResponseEntity<ProductoResponseRest> response = productoServicio.searchById(id);
		return response;
	}
	
	/*
	 * endpoint search by name
	 */
	@GetMapping("/productos/filter/{nombre}")
	public ResponseEntity<ProductoResponseRest>searchByNombre(@PathVariable String nombre){
		 ResponseEntity<ProductoResponseRest> response = productoServicio.searchByNombre(nombre);
		return response;
	}
	
	/*
	 * endpoint delete by id
	 */
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<ProductoResponseRest>deleteByNombre(@PathVariable Long id){
		 ResponseEntity<ProductoResponseRest> response = productoServicio.deleteById(id);
		return response;
	}
	
	/*
	 * endpoint get products
	 */
	@GetMapping("/productos")
	public ResponseEntity<ProductoResponseRest>search(){
		 ResponseEntity<ProductoResponseRest> response = productoServicio.search();
		return response;
	}
	
	/*
	 * endpoint update products
	 */
	@PutMapping("productos/{id}")
	public ResponseEntity<ProductoResponseRest>update(
			@RequestParam("picture") MultipartFile picture,
			@RequestParam("nombre")String nombre,
			@RequestParam("precio")int precio,
			@RequestParam("cantidad")int cantidad,
			@RequestParam("categoriaId")Long categoriaId,
			@PathVariable Long id) throws IOException
	{
		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setCantidad(cantidad);
		producto.setPicture(Util.compressZLib(picture.getBytes()));
	
		ResponseEntity<ProductoResponseRest> response = productoServicio.update(producto, categoriaId, id);
		return response;
	}
}
