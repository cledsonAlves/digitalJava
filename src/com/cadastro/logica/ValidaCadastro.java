package com.cadastro.logica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cadastro.banco.ManipulaBanco;
import com.cadastro.objetos.Usuario;

public class ValidaCadastro {
	private Usuario usuario;
	private String[] Erros = new String[10];
	private ManipulaBanco mb;

	// construtor
	public ValidaCadastro(Usuario usuario) {
		this.usuario = usuario;
		mb = new ManipulaBanco();
	}

	// M�todo valida usuario
	public String validaUsuario() {
		String msg = "";
		if (validaEmail(usuario.getEmail()) == false) {
			msg = "Erro ! O E-mail informado &eacute; inv&aacute;lido.";
		} else if (validaSenha(usuario.getSenha(), usuario.getConfSenha()) == false) {
			msg = "Erro ! As senhas informadas n&atilde;o conferem .";
		} else if (validaNome(usuario.getNome()) == false) {
			msg = "Erro! Nome informado n&atilde;o &eacute; um nome valido !";
		} 
		else if (validaNome(usuario.getSobrenome())== false){
			msg = "Erro ! Sobrenome informado n&atilde;o &eacute; um nome valido !";
		}
		else if (verificaEmail(usuario.getEmail())) {
			// verifica se o e-mail j� existe no banco
			msg = "Erro ! Este e-mail j&aacute; esta cadastrado no sistema.";
		} else {
			// cadastra o usuario no banco

			if (mb.cadastraUsuario(usuario)) {
				msg = "Usuario cadastrado com sucesso!";
				// Envia o email de confirma��o
				Email email = new Email("Email", "destinatario", "remetente",
						"assunto", "msg", usuario);

			} else {
				msg = "Erro ao efetuar o cadastro!";
			}

		}
		return msg;

	}

	// M�todo valida e-mail
	private boolean validaEmail(String email) {
		boolean isValido = false;
		if (email != null && email.length() > 0) {
			String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expressao,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isValido = true;
			}
		}
		return isValido;
	}

	// M�todo valida senha 
	private boolean validaSenha(String senha, String confSenha) {
		boolean valido = false;
		if (senha.length() >= 6) {
			if (senha.equals(confSenha)) {
				valido = true;
			} else {
				valido = false;
			}
		}
		return valido;

	}

	// M�todo valida nome usando Express�o Regular
	private boolean validaNome(String nome) {
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(nome);
		return m.matches();
	}

	

	// M�todo verifica se o e-mail existe no banco
	private boolean verificaEmail(String email) {
		if (mb.verificaEmail(email)) {
			return true;
		} else {
			return false;
		}
	}

}
