package com.cadastro.objetos.curriculo;

import java.io.Serializable;

public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String grauConhecimento;

	public Curso() {
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGrauConhecimento() {
		return grauConhecimento;
	}

	public void setGrauConhecimento(String grauConhecimento) {
		this.grauConhecimento = grauConhecimento;
	}

}
