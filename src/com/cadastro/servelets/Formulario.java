package com.cadastro.servelets;



import java.io.IOException;
import java.io.PrintWriter;

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
		
	
		
		// Valida  os dados para cadastro
		ValidaCadastro valida = new ValidaCadastro(usuario);
		String msg = valida.validaUsuario();
		
		// teste de cadastro
		PrintWriter out = response.getWriter();
		out.println(msg);
		
		
		response.setContentType("text/html");
		
		out.print("</html>");
		
		// redireciona para pagina de cadastros ...
		String pagina = "./sucess.jsp";
		response.sendRedirect(pagina); 
		out.close(); 

		
		out.println("<br>Bem vindo Sr(a) , " + usuario.getNome());
		out.println("<br>foi enviado um e-mail de confirmação para  : "
				+ usuario.getEmail());
		out.println("<br><a href='./index.html'>Voltar</a></center>");

		

	}

}
