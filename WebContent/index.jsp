
<!DOCTYPE html>
<html lang=”pt-br”>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Recrutamento Digital</title>

<!-- Incluindo o CSS do Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet" media="screen" />
<!-- Incluindo o jQuery que é requisito do JavaScript do Bootstrap -->
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<!-- Incluindo o JavaScript do Bootstrap -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- Incluindo o JavaScript do Jquery para Validações -->
<script type="text/javascript" src="js/jquery.validate.js"></script>

<script type="text/javascript" src="js/funcs.js"></script>




</head>
<body>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
             <a class="brand" href="./">
            <img src="img/logo.png" alt="Digital">
            </a>
        </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                </ul>
                <form  action="./autentica" name="login" id="login" method="POST" class="navbar-form navbar-right" role="search">
                   <div class="msgErroLogin">
                    <span class="label label-danger">${retorno}</span>
                    
                    
                    </div>
                    <div class="form-group">
                        <input type="email" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" class="form-control" name="email" value="${emailLogin}" placeholder="E-mail">
                    </div>
                    
                    <div class="form-group">
                        <input required="required" type="password" class="form-control" name="senha" placeholder="Senha">
                    </div>
                    
                    <button type="submit" class="btn btn-success">Entrar</button>
                    <div class="linkSenha" id= "sienha" >
                   <a data-toggle="modal"  href="#restore" >Esqueceu a sua senha?</a>
                    </div>   
                </form>
            </div>
    </div>
</div>

<div class="clearfix"></div>
<div class="gradiente_bkg">
	<div class="conteudoRegistro">
        <h1>[Recru<span>IT</span>]</h1>
        <p>Programa de recrutamento da Digital Sistemas</p>
        <div>
         	<img class="imagemPropagandaRegistro" alt="Imagem propaganda" src="img/bolinha_moca.png" />
        </div>
        <div>
        	<p><span class="textoRegistroTitulo"><strong>Cadastre-se em nosso Banco de Talentos!</strong></span></p>
            <p><span class="textoRegistro">Dedique alguns minutinhos para dar <strong>alguns passos a mais</strong> na sua <strong>carreira</strong>.</span></p>
            <p><span class="textoRegistroCitacao">"Cada sonho que voc&ecirc; deixa para tr&aacute;s &eacute; um peda&ccedil;o do seu futuro que deixa de existir." (Steve Jobs)</span></p>
        </div>
        <div class="formularioRegistro">
        <!-- Formulário de Cadastro -->
		<form name="form" id="form" action="./Formulario" method="POST">		
        	    <div class="row rowRegistro">
        	     <div class="col-md-4"></div>
        			<div class="col-md-4">
					<div class="input-group" data-validate="length" data-length="3">
						<input type="text" class="form-control" value="${nome}" name="nome" id="nome" placeholder="Nome" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
					</div>
				</div>
				<div class="row rowRegistro">
				 <div class="col-md-4"></div>
        			<div class="col-md-4">
					<div class="input-group" data-validate="length" data-length="3">
						<input type="text" class="form-control" value="${sobrenome}" data-validate="length" data-length="3" name="sobrenome" id="sobrenome" placeholder="Sobrenome" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
					</div>
				</div>
				<div class="row rowRegistro">
        		  <div class="col-md-4"></div>
        			<div class="col-md-4">
					<div class="input-group" data-validate="email">
						<input type="text" class="form-control" value="${email}" name="email" id="email" placeholder="E-mail" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
					</div>
				</div>
				
        		<div class="row rowRegistro">
        		 <div class="col-md-4"></div>
        		    <div class="col-md-4">
					<div class="input-group" data-validate="length" data-length="6">
						<input type="password" class="form-control" name="senha" id="senha" placeholder="Senha (6 ou mais caracteres)" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
					</div>
				</div>
				<div class="row rowRegistro">
				<div class="col-md-4"></div>
        		    <div class="col-md-4">
					<div class="input-group" data-validate="length" data-length="6">
						<input type="password" class="form-control" name="confSenha" id="confSenha" placeholder="Repita a senha" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-ok"></span></span>
					</div>
					</div>
				</div>
				<div class="row rowRegistro">
            	<div class="col-md-4"></div>
            	<div class="col-md-4">
                	<button type="submit" class="btn btn-warning btn-registrar">REGISTRAR</button>
                </div>
                <div class="col-md-4"></div>
            </div>
                <div class="row rowRegistro">
            	 <div class="col-md-4"></div>
            	  <div class="col-md-4">
                	<!--   Erros -->
                	${variavelRequestMsgErro}
					<h4><span class="label label-danger">${msgErro}</span></h4>
              	</div>
                <div class="col-md-4"></div>
               </div>
				
            </form>
			<!-- Fim Formulário de Cadastro -->
        </div>
    </div>
    <div>
    </div>
    
   
<!--    Formulario modal para recuperar a senha -->
<!-- Modal -->
  <div class="modal fade" id="restore" tabindex="-1" >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-body">
    		<div class="thumbnail center well well-small text-center">
    		<span><button type="button" class="close" data-dismiss="modal">&times;</button></span>
                <h3><font color= "white">Solicitar Recuperação de Senha</font></h3>
                <p><font color= "white">Digite o e-mail de cadastro, <br> que enviaremos seus dados para este e-mail.</font></p>
                <form action="./restore" method="post">
                
                  <div class="input-group">
                     <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span>
                     </span>
                     <input type="text" class="form-control" id="email" name="email" placeholder="E-mail">
                  </div>
                    <br />
                    <input type="submit" value="Solicitar Senha" class="btn btn-success" />
               </form>
           </div>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
    
</div>

</body>
</html>