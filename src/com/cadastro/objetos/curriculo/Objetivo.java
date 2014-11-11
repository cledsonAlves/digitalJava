package com.cadastro.objetos.curriculo;

import java.io.Serializable;

public class Objetivo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String jornadaTrabalho;
	private String tipoContrato;
	private String nivelHierarquicoMin;
	private String nivelHierarquicoMax;
	private double pretensaoSalarial;
	
	public Objetivo() {
		
	}
	
	public String getJornadaTrabalho() {
		return jornadaTrabalho;
	}

	public void setJornadaTrabalho(String jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}


	public String getNivelHierarquicoMin() {
		return nivelHierarquicoMin;
	}

	public void setNivelHierarquicoMin(String nivelHierarquicoMin) {
		this.nivelHierarquicoMin = nivelHierarquicoMin;
	}

	public String getNivelHierarquicoMax() {
		return nivelHierarquicoMax;
	}

	public void setNivelHierarquicoMax(String nivelHierarquicoMax) {
		this.nivelHierarquicoMax = nivelHierarquicoMax;
	}

	public double getPretensaoSalarial() {
		return pretensaoSalarial;
	}

	public void setPretensaoSalarial(double pretensaoSalarial) {
		this.pretensaoSalarial = pretensaoSalarial;
	}

}
