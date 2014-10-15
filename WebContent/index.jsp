
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
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
             <a class="brand" href="#">
            <img src="img/logo.png" alt="Digital">
            </a>
        </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                   
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="E-mail">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="password" placeholder="Senha">
                    </div>
                    <button type="submit" class="btn btn-success">Entrar</button>
                    <div class="linkSenha"><a href="#">&nbsp;Esqueceu a sua senha?</a></div>
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
                	<input id="nome" data-toggle="tooltip" class="form-control input-normal" value="${nome}"
                    	placeholder="Nome" name="nome" type="text"
                        data-original-title="Informe seu Primeiro Nome, Mínimo 3 caracteres sem espaço" />
                </div>
  				<div class="col-md-4"></div>
            </div>
            <div class="row rowRegistro">
            	<div class="col-md-4"></div>
  				<div class="col-md-4">
                	<input id="sobrenome" class="form-control" data-toggle="tooltip"
                    	placeholder="Sobrenome" value="${sobrenome}" name="sobrenome" type="text"
                        data-original-title="Informe seu Sobrenome, Mínimo 3 caracteres sem espaço" />
                </div>
  				<div class="col-md-4"></div>
            </div>
            <div class="row rowRegistro">
            	<div class="col-md-4"></div>
  				<div class="col-md-4">
                	<input id="email" data-toggle="tooltip" class="form-control"
                		placeholder="Email" value="${email}" name="email"  type="text"
                        data-original-title="Informe um e-mail válido. Enviaremos um link para confirmar seu registro através deste e-mail."
                        onblur="validarDados('email', document.getElementById('email').value);" />
                </div>
                  <div class="input-group">
                        ${msgValida}
                    
                  </div>
  				<div class="col-md-4"></div>
            </div>
            <div class="row rowRegistro">
            	<div class="col-md-4"></div>
  				<div class="col-md-4">
                	<input id="senha" class="form-control" data-toggle="tooltip"
                    	placeholder="Senha (6 ou mais caracteres)" name="senha" type="password"
                        data-original-title="Informe uma senha com no mínimo 6 caracteres, sem espaços." />
                </div>
  				<div class="col-md-4"></div>
            </div>
            <div class="row rowRegistro">
            	<div class="col-md-4"></div>
  				<div class="col-md-4">
                	<input id="confSenha" class="form-control" data-toggle="tooltip"
                    	placeholder="Repita a senha" name="confSenha" type="password"
                    	data-original-title="Repita a senha para verificarmos que não possue erros." />
                </div>
  				<div class="col-md-4"></div>
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
</div>

</body>
</html>