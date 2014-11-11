package com.cadastro.servelets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cadastro.banco.ManipulaBanco;
import com.cadastro.logica.Email;
import com.cadastro.objetos.Usuario;

/**
 * Servlet implementation class RecuperaSenha
 */
public class RecuperaSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RecuperaSenha() {
        super();
    }

	
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println(request.getParameter("token"));
	RequestDispatcher dis = request.getRequestDispatcher("./recupera-senha.jsp");
	dis.forward(request, response);
	
   }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		ManipulaBanco mb = new ManipulaBanco();
		Usuario usuario = mb.autentica(email);
		if (usuario != null){
			Email.enviaDados(usuario);
			RequestDispatcher dis = request.getRequestDispatcher("./index.jsp");
			dis.forward(request, response);

		}else {
			System.out.println("usuario invalido");
		}
		
		
	}

}
