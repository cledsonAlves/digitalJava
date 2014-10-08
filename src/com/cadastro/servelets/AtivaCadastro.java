package com.cadastro.servelets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
	
		PrintWriter out = response.getWriter();
		
		ManipulaBanco mb = new ManipulaBanco();
		String token = request.getParameter("token");
		
		
		if(mb.ativaRegistro(token)){
			response.setContentType("text/html");

			
			// redireciona para pagina de sucesso ...
			String pagina = "./sucess-ativacao.jsp";
			RequestDispatcher dis = request.getRequestDispatcher(pagina);
			dis.forward(request, response);

			
		}
		// cadastro já foi efetivado
		else {
			out.println("<script>");  
	        out.println("alert('Este token não é válido! Registro já foi ativado ou token expirou');");      
	        out.println("</script>");
	       
					
		}
		 out.close();
	
		    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
