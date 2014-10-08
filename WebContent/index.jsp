<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Incluindo o CSS do Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen" />
<!-- Incluindo o jQuery que é requisito do JavaScript do Bootstrap -->
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<!-- Incluindo o JavaScript do Bootstrap -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- Incluindo o JavaScript do Jquery para Validações -->
<script type="text/javascript" src="js/jquery.validate.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("input").tooltip({
			placement : 'right'
		});
	});
</script>


<title>Formulário de Cadastro</title>
</head>
<body>
	<div class="container">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand">Formulário de Cadastro</a>
					<ul class="nav navbar-nav">

					</ul>
				</div>
			</div>
		</div>
		<br />
		<div>
			<legend></legend>
		</div>
		<div id="content">
			<div class="container">
				<div class="navbar-text navbar-letf">
					<div>
						<!-- Formulário de Cadastro -->
						<form name="form" action="./Formulario" method="POST">
							<h2>Conecte-se conosco!</h2>
							<h2>
								<small>O registro leva menos de 2 minutos</small>
							</h2>
							<br> <br>
							<div class="form-group">
								<input id="nome" data-toggle="tooltip"
									class="form-control input-normal" value="${nome}"
									placeholder="Nome" name="nome" type="text"
									data-original-title="Informe seu Primeiro Nome, Mínimo 3 caracteres sem espaço" />
							</div>

							<div class="form-group">
								<input id="sobrenome" class="form-control" data-toggle="tooltip"
									placeholder="Sobrenome" value="${sobrenome}" name="sobrenome"
									type="text"
									data-original-title="Informe seu Sobrenome, Mínimo 3 caracteres sem espaço" />
							</div>

							<div class="form-group">
								<input id="email" data-toggle="tooltip" class="form-control"
									placeholder="Email" value="${email}" name="email" type="text"
									data-original-title="Informe um e-mail válido. Enviaremos um link para confirmar seu registro através deste e-mail." />
							</div>

							<div class="form-group">
								<input id="senha" class="form-control" data-toggle="tooltip"
									placeholder="Senha (6 ou mais caracteres)" name="senha"
									type="password"
									data-original-title="Informe uma senha com no mínimo 6 caracteres, sem espaços." />
							</div>

							<div class="form-group">
								<input id="confSenha" class="form-control" data-toggle="tooltip"
									placeholder="Repita a senha" name="confSenha" type="password"
									data-original-title="Repita a senha para verificarmos que não possue erros." />
							</div>

							<!--   Erros -->
							${variavelRequestMsgErro}
							<h4><span class="label label-danger">${msgErro}</span></h4>
			
							<button type="submit"  class="btn btn-primary">Enviar Agora!</button>

						</form>
						<!-- Fim Formulário de Cadastro -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  Script para validar os inputs no cliente / Usuando API JQUERY VALIDATE  -->
	<script type="text/javascript">
		$('form').validate({
			rules : {
				nome : {
					minlength : 3,
					maxlength : 15,
					required : true
				},
				sobrenome : {
					minlength : 3,
					maxlength : 15,
					required : true
				},
				email : {
					required : true,
					email : true
				},
				senha : {
					minlength : 6,
					required : true

				},
				confSenha : {
					minlength : 6,
					required : true,
					equalTo : '#senha'
				}
			},

			highlight : function(element) {
				$(element).closest('.form-group').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error');
			},
			errorElement : 'span',
			errorClass : 'help-block',
			errorPlacement : function(error, element) {
				if (element.parent('.input-group').length) {
					error.insertAfter(element.parent());
				} else {
					error.insertAfter(element);
				}
			}

		});
	</script>
</body>
</html>