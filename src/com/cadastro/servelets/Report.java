package com.cadastro.servelets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cadastro.banco.ManipulaBanco;
import com.cadastro.logica.Email;
import com.cadastro.objetos.Usuario;

/**
 * Servlet implementation class CadastroIndevido
 */
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Report() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ManipulaBanco mb = new ManipulaBanco();
		String token = request.getParameter("token");
		PrintWriter out = response.getWriter();
		
		//  resgata usuario do banco
		Usuario usuario = mb.consultaUsuario(token);
		if (mb.deletaRegistro(token)){
			Email email = new Email();
			email.enviaReport(usuario);
		
			response.setContentType("text/html");

			// redireciona para pagina de sucesso ...
			String pagina = "./report.jsp";
			RequestDispatcher dis = request.getRequestDispatcher(pagina);
			dis.forward(request, response);
			
		}else {
			out.println("<script>");  
	        out.println("alert('Erro ao excluir usuario');");      
	        out.println("</script>");
		}
		out.close();
		
		
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
