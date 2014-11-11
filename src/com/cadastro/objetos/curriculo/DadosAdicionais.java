package com.cadastro.objetos.curriculo;

import java.io.Serializable;
import java.util.ArrayList;

public class DadosAdicionais implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<String> habilitacoes;
	private ArrayList<String> veiculos;
	private String disponibilidadeViagem;
	private String disponibilidadeMudanca;
	private String observacoes;

	public DadosAdicionais() {
		
	}
	
	public ArrayList<String> getHabilitacoes() {
		return habilitacoes;
	}

	public void setHabilitacoes(ArrayList<String> habilitacoes) {
		this.habilitacoes = habilitacoes;
	}

	public ArrayList<String> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(ArrayList<String> veiculos) {
		this.veiculos = veiculos;
	}

	public String getDisponibilidadeViagem() {
		return disponibilidadeViagem;
	}

	public void setDisponibilidadeViagem(String disponibilidadeViagem) {
		this.disponibilidadeViagem = disponibilidadeViagem;
	}

	public String getDisponibilidadeMudanca() {
		return disponibilidadeMudanca;
	}

	public void setDisponibilidadeMudanca(String disponibilidadeMudanca) {
		this.disponibilidadeMudanca = disponibilidadeMudanca;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

}
