package com.cadastro.logica;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.cadastro.banco.ManipulaBanco;
import com.cadastro.objetos.Usuario;
import com.cadastro.objetos.curriculo.Curriculo;
import com.cadastro.objetos.curriculo.Curso;
import com.cadastro.objetos.curriculo.DadosAdicionais;
import com.cadastro.objetos.curriculo.Endereco;
import com.cadastro.objetos.curriculo.Experiencia;
import com.cadastro.objetos.curriculo.Formacao;
import com.cadastro.objetos.curriculo.Objetivo;
import com.cadastro.objetos.curriculo.Telefone;

@ManagedBean(name = "candidato")
@ViewScoped
public class CandidatoBean implements Serializable {
	private static final long serialVersionUID = 7505372806285876428L;
	public Usuario usuario;
	private ManipulaBanco mb = new ManipulaBanco();
	public Curriculo curriculo;
	public Endereco endereco;
	public ArrayList<Telefone> telefones;
	public ArrayList<Curso> cursos;
	public ArrayList<Experiencia> experiencias;
	public ArrayList<Formacao> formacoes;
	public Telefone telefone = new Telefone();
	public Curso curso = new Curso();
	public Experiencia experiencia = new Experiencia();
	public Formacao formacao = new Formacao();
	DadosAdicionais dadosAdicionais;

	public Objetivo objetivos = new Objetivo();

	public CandidatoBean() {

		curriculo = new Curriculo();
		dadosAdicionais = new DadosAdicionais();
		endereco = new Endereco();
		telefones = new ArrayList<Telefone>();
		cursos = new ArrayList<Curso>();

		experiencias = new ArrayList<Experiencia>();
		formacoes = new ArrayList<Formacao>();

		// Recupera a sessão do usuario
		if (usuario == null) {
			usuario = getSessao();

			mb = new ManipulaBanco();
			dadosAdicionais = mb.getDadosAdicionais(usuario.getEmail());
			objetivos = mb.getObjetivos(usuario.getEmail());
			endereco = mb.getEndereco(usuario.getEmail());
			formacoes = mb.getFormacoes(usuario.getEmail());
			telefones = mb.getTelefones(usuario.getEmail());
			cursos = mb.getCursos(usuario.getEmail());
			experiencias = mb.getExperiencias(usuario.getEmail());

			curriculo = mb.getCurriculo(usuario.getEmail());
			curriculo.setEndereco(endereco);
			curriculo.setTelefones(telefones);
			curriculo.setCursos(cursos);
			curriculo.setExperiencias(experiencias);
			curriculo.setFormacoes(formacoes);
			curriculo.setObjetivos(objetivos);

			curriculo.setDadosAdicionais(dadosAdicionais);
		}

	}

	// retorna usuario logado
	public Usuario getUsuario() {
		return usuario;
	}

	// retorna cv
	public Curriculo getCurriculo() {
		return curriculo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public Curso getCurso() {
		return curso;
	}

	public Experiencia getExperiencia() {
		return experiencia;
	}

	public Formacao getFormacao() {
		return formacao;
	}

	public DadosAdicionais getDadosAdicionais() {
		return dadosAdicionais;
	}

	public Objetivo getObjetivos() {
		return objetivos;
	}

	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}

	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public ArrayList<Experiencia> getExperiencias() {
		return experiencias;
	}

	public ArrayList<Formacao> getFormacoes() {
		return formacoes;
	}

	public void addTelefone() {
		telefones.add(telefone);
	}

	public void addCurso() {

		System.out.println("ok");
		cursos.add(curso);

	}

	public void addExperiencia() {
		experiencias.add(experiencia);
	}

	public void addFormacao() {
		formacoes.add(formacao);
	}

	public String salvarCurriculo() {
		curriculo.setEndereco(endereco);
		curriculo.setTelefones(telefones);
		curriculo.setFormacoes(formacoes);
		curriculo.setExperiencias(experiencias);
		curriculo.setCursos(cursos);
		curriculo.setDadosAdicionais(dadosAdicionais);
		curriculo.setObjetivos(objetivos);

		// salva o cv no banco
		mb.salvaCurriculo(curriculo);

		return "";
	}

	public void removeTelefone(Telefone telefone) {
		telefones.remove(telefone);
	}

	public void removeExperiencia(Experiencia experiencia) {
		experiencias.remove(experiencia);
	}

	public void removeCurso(Curso curso) {
		cursos.remove(curso);
	}

	public void removeFormacao(Formacao formacao) {
		formacoes.remove(formacao);
	}

	public void editarTelefone(Telefone telefone) {
		telefones.set(telefones.indexOf(telefone), telefone);
	}

	public void editarExperiencia(Experiencia experiencia) {
		experiencias.set(experiencias.indexOf(experiencia), experiencia);
	}

	public void editarCurso(Curso curso) {
		cursos.set(cursos.indexOf(curso), curso);
	}

	public void editarFormacao(Formacao formacao) {
		formacoes.set(formacoes.indexOf(formacao), formacao);
	}

	// pegando a sessão do usuario logado..
	public Usuario getSessao() {
		this.usuario = new Usuario();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(true);
		this.usuario.setNome(String.valueOf(session.getAttribute("nome")));
		this.usuario.setSobrenome(String.valueOf(session
				.getAttribute("sobrenome")));
		this.usuario.setEmail(String.valueOf(session.getAttribute("email")));
		return usuario;

	}

	// Logout - encerrando a sessão do usuario
	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		session.invalidate();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("./");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
