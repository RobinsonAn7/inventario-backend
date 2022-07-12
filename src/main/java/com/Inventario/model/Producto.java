package com.Inventario.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="producto")
public class Producto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8166279441392378994L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private int precio;
	private int cantidad;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonIgnoreProperties ({"hibernateLazyInitializer","handler"})
	private Categoria categoria;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name="picture")
	private byte[] picture;
	

}
