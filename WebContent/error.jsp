
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
	<div class="gradiente_fundo">
		<div class="conteudoRegistro">
			<h1>
				<br>
			</h1>
			<p></p>
			<div></div>
			<div>
				<h2>Ops! Isso não é nada bom. Algo deu errado ao comunicar com
					nossos servidores Codigo erro : ${pageContext.errorData.statusCode}</h2>
				<p>
					<a href="./"><strong style="text-decoration: underline;">Clique
							aqui</strong> </a><span class="textoRegistro">para acessar o sistema.</span>
				</p>

			</div>
		</div>
		<div></div>
	</div>

</body>
</html>