package com.cadastro.servelets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cadastro.banco.ManipulaBanco;
import com.cadastro.logica.CandidatoBean;
import com.cadastro.logica.Criptografia;
import com.cadastro.objetos.Usuario;

/*
 * Classe autentica a senha  
 */
public class Autentica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Autentica() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = Criptografia.criptografa(request.getParameter("senha"));

		ManipulaBanco mb = new ManipulaBanco();
		Usuario usuario = mb.autentica(email);

		if (usuario != null) {
			// valido se usuario já ativou o registro
			if (usuario.getAtivo().equals("10")) {
				request.setAttribute(
						"retorno",
						"Voc&ecirc; ainda n&atilde;o ativou o seu registro, acesse seu e-mail para ativ&aacute;-lo!");
				request.setAttribute("emailLogin", email);
				RequestDispatcher dis = request.getRequestDispatcher("./");
				dis.forward(request, response);
			}
			// valido se a senha informada é valida
			else if (!usuario.getSenha().equals(senha)) {
				request.setAttribute("retorno",
						"A Senha informada &eacute; inv&aacute;lida !");
				request.setAttribute("emailLogin", email);
				RequestDispatcher dis = request.getRequestDispatcher("./");
				dis.forward(request, response);
			}
			// loga no sistema
			else {
				// cria a sessão do usuario
				HttpSession sessao = request.getSession();
				sessao.setAttribute("nome", usuario.getNome());
				sessao.setAttribute("sobrenome", usuario.getSobrenome());
				sessao.setAttribute("email", usuario.getEmail());
	
				// redireciona para pagina de editor de CV...
				String pagina = "./curriculo.xhtml";
				response.sendRedirect(pagina);
			}
		} else {
			// usuario sem cadastro
			request.setAttribute("retorno",
					"E-mail n&atilde;o esta cadastrado em nosso sistema !");
			request.setAttribute("emailLogin", email);
			RequestDispatcher dis = request.getRequestDispatcher("./");
			dis.forward(request, response);

		}

	}

}
