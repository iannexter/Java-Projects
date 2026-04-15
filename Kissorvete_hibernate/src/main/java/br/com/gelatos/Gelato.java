package br.com.gelatos;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Gelato implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nome;
	
	private String tipo;
	
	private BigDecimal preco;
	

	public Gelato() {
		super();
		
	}

	

	
	public int getId() {
		return id;
	}







	public void setId(int id) {
		this.id = id;
	}







	public String getNome() {
		return nome;
	}







	public void setNome(String nome) {
		this.nome = nome;
	}







	public String getTipo() {
		return tipo;
	}







	public void setTipo(String tipo) {
		this.tipo = tipo;
	}







	public BigDecimal getPreco() {
		return preco;
	}







	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
	/////////////////////////////////////////////////////////
	
	








	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gelato other = (Gelato) obj;
		return id == other.id;
	}
	
	
	/////////////////////////////////////
	
}
