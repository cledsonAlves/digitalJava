package com.cadastro.objetos.curriculo;

import java.io.Serializable;

public class Telefone implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tipo;
	private String ddd;
	private String numero;

	public Telefone() {

	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
