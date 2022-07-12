package com.Inventario.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoResponseRest extends ResponseRest{
	private ProductoResponse producto = new ProductoResponse();
}
