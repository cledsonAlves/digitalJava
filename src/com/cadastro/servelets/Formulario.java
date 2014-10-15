package com.cadastro.servelets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cadastro.logica.ValidaCadastro;
import com.cadastro.objetos.Usuario;

public class Formulario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Formulario() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("valor"));

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		// seta novo usuario
		usuario = new Usuario();
		usuario.setNome(request.getParameter("nome"));
		usuario.setSobrenome(request.getParameter("sobrenome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setConfSenha(request.getParameter("confSenha"));

		// Valida os dados para cadastro
		ValidaCadastro valida = new ValidaCadastro(usuario);
		String msg = valida.validaUsuario();
		response.setContentType("text/html");

		if (msg.equals("Usuario cadastrado com sucesso!")) {
			String pagina = "./sucess.jsp";
			request.setAttribute("msg","sucess");
			RequestDispatcher dis = request.getRequestDispatcher(pagina);
			dis.forward(request, response);
		

		}

		else {

			if (msg.equals("Erro! Nome informado não é um nome valido !")) {
				request.setAttribute("variavelRequestMsgErro","<body onLoad='document.form.nome.focus();'>");

			} else if (msg.equals("Erro! Sobrenome informado não é um nome valido !")) {
				request.setAttribute("variavelRequestMsgErro","<body onLoad='document.form.sobrenome.focus();'>");

			} else if (msg.equals("Erro! O E-mail informado é inválido.")) {
				request.setAttribute("variavelRequestMsgErro","<body onLoad='document.form.email.focus();'>");

			} else if (msg.equals("Erro. As senhas informadas não conferem !")) {
				request.setAttribute("variavelRequestMsgErro","<body onLoad='document.form.senha.focus();'>");

			}else if (msg.equals("Erro! Este e-mail já esta cadastrado no sistema!")) {
				request.setAttribute("variavelRequestMsgErro","<body onLoad='document.form.email.focus();'>");

			}
			request.setAttribute("msgErro",msg);
			request.setAttribute("nome", usuario.getNome());
			request.setAttribute("sobrenome", usuario.getSobrenome());
			request.setAttribute("email", usuario.getEmail());

			RequestDispatcher dis = request.getRequestDispatcher("./");
			dis.forward(request, response);

		}

	}

}
