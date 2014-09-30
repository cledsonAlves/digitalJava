package com.cadastro.servelets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cadastro.banco.ManipulaBanco;
import com.cadastro.objetos.Usuario;

public class Formulario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Formulario() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

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

		// teste de cadastro
		PrintWriter out = response.getWriter();
		ManipulaBanco b = new ManipulaBanco();
		b.cadastraUsuario(usuario);
			out.println("<br>Bem vindo Sr(a) , " + usuario.getNome());
			out.println("<br>foi enviado um e-mail de confirmação para  : "
					+ usuario.getEmail());
			out.println("<br><a href='./index.html'>Voltar</a></center>");

		

	}

}
