package com.Inventario.response;

import java.util.List;

import com.Inventario.model.Producto;

import lombok.Data;

@Data
public class ProductoResponse {
	List<Producto> producto;
}
