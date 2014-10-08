package com.cadastro.logica;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.cadastro.banco.ManipulaBanco;
import com.cadastro.objetos.Usuario;

/**
 * 
 * Classe genérica para envio de e-mails
 * ultiliza a API Comons-net da Jakarta
 *
 */

public class Email {
	private ManipulaBanco mb;
	
	
	public Email(){
		
	}

	//construtor 
	public Email(String Email, String destinatario, String remetente,
			String assunto, String msg, Usuario usuario) {
		
		//
		mb = new ManipulaBanco();
		usuario.setToken(mb.buscaToken(usuario.getEmail()));	

		HtmlEmail email = new HtmlEmail();
		email.setSSLOnConnect(true);
		email.setHostName( "smtp.gmail.com" );
		email.setSslSmtpPort( "465" );
		email.setStartTLSRequired(true);
		email.setSSLOnConnect(true);
		
	
		// conta de e-mail para autenticação
		email.setAuthenticator( new DefaultAuthenticator( "sistemacadastro0@gmail.com" ,  "sistema@2014" ) );

		try { 
			  

			    email.setFrom( "clsddd@hotmail.com");
			    email.setDebug(true);
			    email.setSubject( "Bem vindo  "+usuario.getNome()+" : Sistema de Cadastro ");
			    
			    //mensagem
			    StringBuilder builder = new StringBuilder();
			    builder.append("<h1>Bem Vindo "+usuario.getNome()+",</h1>");
			    builder.append("<p>Obrigado por se conectar conosco. <b>Seu cadastramento foi efetuado com sucesso!</b><br> Para confirmar seu registro acesse o link abaixo para efetivar seu cadastramento.</p>");
			    builder.append("<a href='http://localhost:8090/Cadastro/confirma-cadastro?token="+usuario.getToken()+"'>Clique aqui para confirmar seu cadastramento</a> <br> ");
			    builder.append("<p><br>Se você não solicitou o cadastramento, clique no link abaixo para nos informar :</p>");
			    builder.append("<a href='http://localhost:8090/Cadastro/Report?token="+usuario.getToken()+"'>Clique aqui para informar um cadastramento indevido</a> <br> ");
			    builder.append("<p><br>Mais uma vez, obrigado por se conectar!</p>");
			    builder.append("<p><b>Sistema de Cadastro</b><br></p>");
			    //builder.append("<img src=\"http://www.botecodigital.info/wp-content/themes/boteco/img/logo.png\">");
			    
			    email.addTo(usuario.getEmail());
			    email.setHtmlMsg( builder.toString() );
			    
			    email.send();

		} catch (EmailException e) {
			System.out.println("Não foi possível enviar a mensagem!");
			e.printStackTrace();
		}

	}
	
	//  Report ( Cadastramento Indevido)
	public void enviaReport(Usuario usuario){
		HtmlEmail email = new HtmlEmail();
		email.setSSLOnConnect(true);
		email.setHostName( "smtp.gmail.com" );
		email.setSslSmtpPort( "465" );
		email.setStartTLSRequired(true);
		email.setSSLOnConnect(true);
		
	
		// conta de e-mail para autenticação
		email.setAuthenticator( new DefaultAuthenticator( "sistemacadastro0@gmail.com" ,  "sistema@2014" ) );
		try { 
			  
            
		    email.setFrom( "clsddd@hotmail.com");
		    email.setDebug(true);
		    email.setSubject( "Report : Cadastramento Indevido ");
		    
		    //mensagem
		    StringBuilder builder = new StringBuilder();
		    builder.append("<h1>Usuario : "+usuario.getNome()+"  "+usuario.getSobrenome()+"</h1>");
		    builder.append("<p>Email : "+usuario.getEmail()+"</p>");
		    builder.append("<p>Informa através do link de ativação que o cadastramento foi indevido.</p>");
		    builder.append("<p>Os dados foram apagados do banco!.</p>");
		    builder.append("<p><b>Sistema de Cadastro</b><br></p>");
		    //email para recebimento dos reports
		    email.addTo("clsddd@hotmail.com");
		    
		    
		    email.setHtmlMsg( builder.toString() );
		    
		    email.send();

	} catch (EmailException e) {
		System.out.println("Não foi possível enviar a mensagem!");
		e.printStackTrace();
	}

		
	}
	

}
