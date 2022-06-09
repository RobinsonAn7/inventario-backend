package com.Inventario.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Inventario.dao.ICategoria;
import com.Inventario.model.Categoria;
import com.Inventario.response.CategoriaResponseRest;

@Service
public class CategoriaServicioImpl implements ICategoriaServicio {

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

		} catch (Exception e) {

			response.setMetadata("Respuesta NO OK", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> searchById(Long id) {

		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		try {

			Optional<Categoria> categoria = categoriaDao.findById(id);

			if (categoria.isPresent()) {

				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
				response.setMetadata("Respuesta OK", "-1", "Categoria encontrada");
			} else {

				response.setMetadata("Respuesta NO OK", "-1", "Categoria no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {

			response.setMetadata("Respuesta NO OK", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> save(Categoria categoria) {

		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		try {

			Categoria categoriaSaved = categoriaDao.save(categoria);
			
			if (categoriaSaved != null) {
				list.add(categoriaSaved);
				response.getCategoriaResponse().setCategoria(list);
			} else {
				
				response.setMetadata("Respuesta NO OK", "-1", "categoria no guardada ");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {

			response.setMetadata("Respuesta NO OK", "-1", "Error al guardar categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

}
