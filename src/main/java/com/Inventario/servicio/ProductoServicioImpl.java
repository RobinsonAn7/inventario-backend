package com.Inventario.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Inventario.dao.ICategoria;
import com.Inventario.dao.IProducto;
import com.Inventario.model.Categoria;
import com.Inventario.model.Producto;
import com.Inventario.response.ProductoResponseRest;

@Service
public class ProductoServicioImpl implements IProductoService{
	
	private ICategoria categoriaDao;
	private IProducto productoDao;
	
	
	public ProductoServicioImpl(ICategoria categoriaDao,IProducto productoDao) {
		super();
		this.categoriaDao = categoriaDao;
		this.productoDao = productoDao;
	}


	@Override
	public ResponseEntity<ProductoResponseRest> save(Producto producto, Long categoryId) {
		ProductoResponseRest response = new ProductoResponseRest(); 
		List<Producto> list = new ArrayList<>();
		try {
			//search category to set in the product object
			Optional<Categoria> categoria = categoriaDao.findById(categoryId);
			
			if( categoria.isPresent()) {
				producto.setCategoria(categoria.get());
				
			} else {
				response.setMetadata("respuesta nok", "-1", "Categoria no encontrada");
				return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			//save the product
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

}
