package com.cadastro.objetos.curriculo;

import java.io.Serializable;
import java.util.ArrayList;

public class Curriculo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String email;
	private String nome;
	private String sobrenome;
	private String cpf;
	private String dataNascimento;
	private String sexo;
	private String estadoCivil;
	private String nacionalidade;
	private String foto;
	private Endereco endereco;
	private ArrayList<Telefone> telefones;
	private ArrayList<Formacao> formacoes;
	private ArrayList<Experiencia> experiencias;
	private ArrayList<Curso> cursos;
	private DadosAdicionais dadosAdicionais;
	private Objetivo objetivos;

	public Curriculo() {
		
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ArrayList<Telefone> telefones) {
		this.telefones = telefones;
	}

	public ArrayList<Formacao> getFormacoes() {
		return formacoes;
	}

	public void setFormacoes(ArrayList<Formacao> formacoes) {
		this.formacoes = formacoes;
	}

	public ArrayList<Experiencia> getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(ArrayList<Experiencia> experiencias) {
		this.experiencias = experiencias;
	}

	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}

	public DadosAdicionais getDadosAdicionais() {
		return dadosAdicionais;
	}

	public void setDadosAdicionais(DadosAdicionais dadosAdicionais) {
		this.dadosAdicionais = dadosAdicionais;
	}

	public Objetivo getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(Objetivo objetivos) {
		this.objetivos = objetivos;
	}

}
