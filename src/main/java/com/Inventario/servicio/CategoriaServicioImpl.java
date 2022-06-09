package com.Inventario.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Inventario.dao.ICategoria;
import com.Inventario.model.Categoria;
import com.Inventario.response.CategoriaResponseRest;

@Service
public class CategoriaServicioImpl implements ICategoriaServicio{
	
	@Autowired
	private ICategoria categoriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> search() {
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			
			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
			
			response.getCategoriaResponse().setCategoria(categoria);
			response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
			
		}catch (Exception e) {
			
			response.setMetadata("Respuesta NO OK", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.OK);
	}

}
