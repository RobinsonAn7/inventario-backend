package com.Inventario.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Inventario.dao.ICategoria;
import com.Inventario.dao.IProducto;
import com.Inventario.model.Categoria;
import com.Inventario.model.Producto;
import com.Inventario.response.ProductoResponseRest;
import com.Inventario.util.Util;

@Service
public class ProductoServicioImpl implements IProductoService {

	private ICategoria categoriaDao;
	private IProducto productoDao;

	public ProductoServicioImpl(ICategoria categoriaDao, IProducto productoDao) {
		super();
		this.categoriaDao = categoriaDao;
		this.productoDao = productoDao;
	}

	@Override
	@Transactional
	public ResponseEntity<ProductoResponseRest> save(Producto producto, Long categoryId) {
		ProductoResponseRest response = new ProductoResponseRest();
		List<Producto> list = new ArrayList<>();
		try {
			// search category to set in the product object
			Optional<Categoria> categoria = categoriaDao.findById(categoryId);

			if (categoria.isPresent()) {
				producto.setCategoria(categoria.get());

			} else {
				response.setMetadata("respuesta nok", "-1", "Categoria no encontrada");
				return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
			}

			// save the product
			Producto productoSaved = productoDao.save(producto);

			if (productoSaved != null) {
				list.add(productoSaved);
				response.getProducto().setProducto(list);
				response.setMetadata("respuesta ok", "00", "Producto Guardado");
			} else {
				response.setMetadata("respuesta nok", "-1", "Producto no guardado");
				return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al guardar producto");
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductoResponseRest> searchById(Long id) {

		ProductoResponseRest response = new ProductoResponseRest();

		List<Producto> list = new ArrayList<>();
		try {
			// search products by id
			Optional<Producto> producto = productoDao.findById(id);

			if (producto.isPresent()) {

				byte[] imagesDescompressed = Util.decompressZLib(producto.get().getPicture());
				producto.get().setPicture(imagesDescompressed);
				list.add(producto.get());
				response.getProducto().setProducto(list);
				response.setMetadata("Respuesta ok", "00", "Producto encontrado");

			} else {

				response.setMetadata("respuesta nok", "-1", "Producto no encontrado");
				return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {

			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al guardar producto");
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional (readOnly = true)
	public ResponseEntity<ProductoResponseRest> searchByNombre(String nombre) {
		
		ProductoResponseRest response = new ProductoResponseRest();

		List<Producto> list = new ArrayList<>();
		List<Producto> listAux = new ArrayList<>();
		try {
			// search products by name
			listAux = productoDao.findByNombreContainingIgnoreCase(nombre);

			if (listAux.size() > 0) {
				
				listAux.stream().forEach((p) -> {
					
					byte[] imagesDescompressed = Util.decompressZLib(p.getPicture());
					p.setPicture(imagesDescompressed);
					list.add(p);
				});
				response.getProducto().setProducto(list);
				response.setMetadata("Respuesta ok", "00", "Producto encontrado");

			} else {

				response.setMetadata("respuesta nok", "-1", "Producto no encontrado");
				return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {

			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al buscar producto");
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
	}

}
