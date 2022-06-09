package com.Inventario.response;

import java.util.List;

import com.Inventario.model.Categoria;

import lombok.Data;

@Data
public class CategoriaResponse {
	
	private List<Categoria> categoria;

}
