package com.cadastro.servelets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cadastro.banco.ManipulaBanco;

public class AtivaCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AtivaCadastro() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// teste de cadastro
		PrintWriter out = response.getWriter();
		
		ManipulaBanco mb = new ManipulaBanco();
		String token = request.getParameter("token");
		if(mb.ativaRegistro(token)){
			response.setContentType("text/html");
			
			out.print("</html>");
			
			// redireciona para pagina de cadastros ...
			String pagina = "./sucess-ativacao.jsp";
			response.sendRedirect(pagina); 
			out.close(); 
			
		}else {
			out.println("<html><body>");
			out.println("<br>Não foi possível ativar seu cadastro !");	
		}
	
		
		    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
