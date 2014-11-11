package com.cadastro.logica;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.cadastro.banco.ManipulaBanco;
import com.cadastro.objetos.Usuario;

/**
 * 
 * Classe gen�rica para envio de e-mails
 * ultiliza a API Comons-net da Jakarta
 *
 */

public class Email {
	private ManipulaBanco mb;
	private static final String url = "http://www.desagil.com.br:8080";
	
	
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
		
	
		// conta de e-mail para autentica��o
		email.setAuthenticator( new DefaultAuthenticator( "cledson199@gmail.com" ,  "ti@kelma.com.br" ) );

		try { 
			  

			    email.setFrom( "clsddd@hotmail.com");
			    email.setDebug(true);
			    email.setSubject( "Bem vindo  "+usuario.getNome()+" : Equipe Recruit ");
			    
			    //mensagem
			    StringBuilder builder = new StringBuilder();
			    builder.append("<p><h1><b><center><font color ='#89A0AD'>Bem-Vindo, "+usuario.getNome()+",</font></b></h1></p>");
			    builder.append("<p><h1><center>Agradecemos o seu interesse em se cadastrar no <br>Banco de Talentos RecruIT.</b><br></p>");
			    builder.append("<p><h1><b><center>Voc� esta a um passo do sucesso profissional :</b><br>Para confirmar o seu cadastro, <a href='"+url+"/Cadastro/confirma-cadastro?token="+usuario.getToken()+"'><u>Clique aqui.</u></a></b></h1></p>");	    
			    builder.append("<p>Se voc� n�o solicitou o cadastramento, <a href='"+url+"/Cadastro/Report?token="+usuario.getToken()+"'><u>Clique aqui.</u></a>  :</p>");	    
			    builder.append("<p><h3><center>At� mais!<br><font color='#89A0AD'>Equipe Recruit.</font></b></h3></p>");		 
			    builder.append("<img src=\"http://www.desagil.com.br/img/email.png\">");
			    
			    email.addTo(usuario.getEmail());
			    email.setHtmlMsg( builder.toString() );
			    
			    email.send();

		} catch (EmailException e) {
			System.out.println("N�o foi poss�vel enviar a mensagem!");
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
		
	
		// conta de e-mail para autentica��o
		email.setAuthenticator( new DefaultAuthenticator( "cledson199@gmail.com" ,  "ti@kelma.com.br" ) );
		try { 
			  
            
		    email.setFrom( "clsddd@hotmail.com");
		    email.setDebug(true);
		    email.setSubject( "Report : Cadastramento Indevido ");
		    
		    //mensagem
		    StringBuilder builder = new StringBuilder();
		    builder.append("<h1>Usuario : "+usuario.getNome()+"  "+usuario.getSobrenome()+"</h1>");
		    builder.append("<p>Email : "+usuario.getEmail()+"</p>");
		    builder.append("<p>Informa atrav�s do link de ativa��o que o cadastramento foi indevido.</p>");
		    builder.append("<p>Os dados foram apagados do banco!.</p>");
		    builder.append("<p><b>Sistema de Cadastro</b><br></p>");
		    //email para recebimento dos reports
		    email.addTo("clsddd@hotmail.com");
		    
		    
		    email.setHtmlMsg( builder.toString() );
		    
		    email.send();

	} catch (EmailException e) {
		System.out.println("N�o foi poss�vel enviar a mensagem!");
		e.printStackTrace();
	}

		
	}
	// Envia recupera��o de senha
	public static void enviaDados(Usuario usuario){
		HtmlEmail email = new HtmlEmail();
		email.setSSLOnConnect(true);
		email.setHostName( "smtp.gmail.com" );
		email.setSslSmtpPort( "465" );
		email.setStartTLSRequired(true);
		email.setSSLOnConnect(true);
		
	
		// conta de e-mail para autentica��o
		email.setAuthenticator( new DefaultAuthenticator( "cledson199@gmail.com" ,  "ti@kelma.com.br" ) );
		try { 
			  
            
		    email.setFrom( "clsddd@hotmail.com");
		    email.setDebug(true);
		    email.setSubject( "Recruit : Recupera��o de Senha ");
		    
		    //mensagem
		    StringBuilder builder = new StringBuilder();
		    builder.append("<p><h1><b><center><font color ='#89A0AD'>Sr(a) , "+usuario.getNome()+" "+usuario.getSobrenome() +",</font></b></h1></p>");
		    builder.append("<p><h1><center>Para prosseguir com a <br>Recupera��o de Senha acesse o link abaixo.</p>");
		    builder.append("<p><center> <a href='"+url+"/Cadastro/restore?token="+usuario.getToken()+"'><u>Clique aqui para continuar com o processo de recupera��o de senha</u></a></p>");	    
		    builder.append("<p>� muito importante voc� prosseguir com a recupera��o de senha, pois o c�digo de <br>" +
		    		"acesso ir� expirar em 30 minutos da data de abertura de solicita��o. <br>" +
		    		"Agradecemos o seu interesse em manter seu cadastro atualizado conosco =)</p>");	    
		    builder.append("<p><h3><center>At� mais!<br><font color='#89A0AD'>Equipe Recruit.</font></b></h3></p>");		 
		    builder.append("<img src=\"http://www.desagil.com.br/img/email.png\">");
		    
		    //envia os dados para o usuario
		    email.addTo(usuario.getEmail());
		    
		    
		    email.setHtmlMsg( builder.toString() );
		    
		    email.send();

	} catch (EmailException e) {
		System.out.println("N�o foi poss�vel enviar a mensagem!");
		e.printStackTrace();
	}
		
		
	}
	

}
