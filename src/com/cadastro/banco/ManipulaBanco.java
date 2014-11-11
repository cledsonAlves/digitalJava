package com.cadastro.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.cadastro.logica.Criptografia;
import com.cadastro.objetos.Usuario;
import com.cadastro.objetos.curriculo.Curriculo;
import com.cadastro.objetos.curriculo.Curso;
import com.cadastro.objetos.curriculo.DadosAdicionais;
import com.cadastro.objetos.curriculo.Endereco;
import com.cadastro.objetos.curriculo.Experiencia;
import com.cadastro.objetos.curriculo.Formacao;
import com.cadastro.objetos.curriculo.Objetivo;
import com.cadastro.objetos.curriculo.Telefone;

/**
 * Classe responsável pela manipulação direta no banco de dados contendo
 * (insert, delete,update, drop, etc..)
 */

public class ManipulaBanco {
	private Banco banco;
	private ResultSet result;
	// private Statement stmt;
	private PreparedStatement stmt;
	private Connection conexao;

	/*
	 * Método Construtor
	 * Na construção do objeto bre a conexão com o banco
	 */
	public ManipulaBanco() {
		this.banco = new Banco();
		this.conexao = this.banco.getConexao();
	}

	/*
	 * Método verificaEmail()
	 * Responsável por verificar se um deterinado email já existe na tabela USUARIOS
	 */
	public boolean verificaEmail(String email) {
		try {
			this.stmt = this.conexao.prepareStatement("SELECT * FROM USUARIOS WHERE EMAIL = ?");
			this.stmt.clearParameters();
			this.stmt.setString(1, email);
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			this.result = this.stmt.executeQuery();
			if (this.result.next())
				return true;
			else 
				return false;
		} catch (SQLException exception) {
			exception.printStackTrace();
			return false;
		} finally {
			//fechaConexao(this.conexao, this.stmt, this.result);
		}
	}

	/*
	 *  Método cadastraUsuario()
	 *  Responsável por inserir os dados do Usuário na tabela USUARIOS
	 */
	public boolean cadastraUsuario(Usuario usuario) {
		// Criptografa a senha e gera o token (com email) para ativar o registro
		String novaSenha = "";
		String token = "";
		try {
			novaSenha = Criptografia.criptografa(usuario.getSenha());
			token = Criptografia.criptografa(usuario.getEmail());
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		try {
			this.stmt = this.conexao.prepareStatement("INSERT INTO USUARIOS VALUES (?,?,?,?,?,?)");
			this.stmt.clearParameters();
			this.stmt.setString(1, usuario.getEmail());
			this.stmt.setString(2, usuario.getNome());
			this.stmt.setString(3, usuario.getSobrenome());
			this.stmt.setString(4, novaSenha);
			this.stmt.setString(5, token);
			this.stmt.setString(6, "0");
			
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			
			// Se cadastrou o usuário, cadastra os dados básicos dele na tabela CURRICULOS
			    this.stmt.execute();
			
				 Curriculo cv = new Curriculo();
				 cv.setNome(usuario.getNome());
				 cv.setSobrenome(usuario.getSobrenome());
				 cv.setEmail(usuario.getEmail());
				 cadastraCv(cv);

			    
			
			return true;
		} catch (SQLException exception) {
			exception.printStackTrace();
			return false;
		} finally {
			// fechaConexao(conexao, stmt);
		}
	}

	/* 
	 * Método buscaToken(String email)
	 * Busca o token do usuário no banco
	 */
	public String buscaToken(String email) {
		String token = "";
		try {
			this.stmt = this.conexao.prepareStatement("SELECT TOKEN FROM USUARIOS WHERE EMAIL = ?");
			this.stmt.clearParameters();
			this.stmt.setString(1, email);
			
			if (this.conexao.isClosed()) 
				this.conexao = this.banco.getConexao();
			this.result = this.stmt.executeQuery();
			
			if (this.result.next())
				token = this.result.getString(1);
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			// fechaConexao(conexao, stmt, result);
		}
		return token;

	}

	/* 
	 * Método ativaRegistro()
	 * Ativa o registro do usuário
	 */
	public boolean ativaRegistro(String token) {
		if (consultaRegistro(token, "0")) {
			try {
				this.stmt = this.conexao.prepareStatement("UPDATE USUARIOS SET ATIVO = '1' WHERE TOKEN = ?");
				this.stmt.clearParameters();
				this.stmt.setString(1, token);
				if (this.conexao.isClosed())
					this.conexao = this.banco.getConexao();
				this.stmt.execute();
				return true;
			} catch (SQLException exception) {
				exception.printStackTrace();
			} finally {
				//fechaConexao(conexao, stmt);
			}

		} else
			return false;
		return false;
	}

	/*
	 * Método consultaRegistro()
	 * Verifica se o registro do usuário está ativo ou inativo
	 */
	private boolean consultaRegistro(String token, String ativo) {
		try {
			this.stmt = this.conexao.prepareStatement("SELECT * FROM USUARIOS WHERE TOKEN = ? AND ATIVO = ?;");
			this.stmt.clearParameters();
			this.stmt.setString(1, token);
			this.stmt.setString(2, ativo);
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			this.result = this.stmt.executeQuery();
			if (this.result.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
			return false;
		} finally {
			// fechaConexao(conexao, stmt, result);
		}
	}

	/* 
	 * Método consultaUsuario()
	 * Retorna os dados do usuário
	 */
	public Usuario consultaUsuario(String token) {
		try {
			this.stmt = this.conexao.prepareStatement("SELECT * FROM USUARIOS WHERE TOKEN = ?;");
			this.stmt.clearParameters();
			this.stmt.setString(1, token);
			Usuario usuario = new Usuario();
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			this.result = this.stmt.executeQuery();
			if(this.result.next());
			{
				usuario.setEmail(this.result.getString(1));
				usuario.setNome(this.result.getString(2));
				usuario.setSobrenome(this.result.getString(3));
				usuario.setSenha(this.result.getString(4));
				usuario.setToken(this.result.getString(5));
				usuario.setAtivo(this.result.getString(6));
			}
			return usuario;

		} catch (SQLException exception) {
			exception.printStackTrace();
			return null;

		} finally {
			// fechaConexao(conexao, stmt, result);
		}
	}

	/*
	 * Método deletaRegistro()
	 * Responsável por apagar um usuário do banco
	 */
	public boolean deletaRegistro(String token) {

		try {
			this.stmt = this.conexao.prepareStatement("DELETE FROM USUARIOS WHERE TOKEN = ?;");
			this.stmt.clearParameters();
			this.stmt.setString(1, token);
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			this.stmt.execute();
			return true;
		} catch (SQLException exception) {
			exception.printStackTrace();
			return false;
		} finally {
			// fechaConexao(conexao, stmt);
		}
	}

	/*
	 * Método autentica()
	 * Autentica o email do usuário
	 */
	public Usuario autentica(String email) {
		// Válida se o e-mail existe no banco
		if (verificaEmail(email)) {
			// Criptografa o email para comparar o token e retorna o usuario.
			Usuario user = consultaUsuario(Criptografia.criptografa(email));
			return user;
		} else {
			// E-mail é invalido
			return null;
		}
	}

	/*
	 * Método getCursos()
	 * Retorna um ArrayList<Curso> com os cursos do usuário cadastrados no banco
	 */
	public ArrayList<Curso> getCursos(String email) {

		try {
			this.stmt = this.conexao.prepareStatement("SELECT NOME, GRAU_DE_CONHECIMENTO FROM CURSOS WHERE EMAIL = ?;");
			this.stmt.clearParameters();
			this.stmt.setString(1, email);
			ArrayList<Curso> lista_cursos = new ArrayList<Curso>();;
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			this.result = this.stmt.executeQuery();
			while (this.result.next()) {
				Curso curso = new Curso();
				curso.setNome(this.result.getString(1));
				curso.setGrauConhecimento(this.result.getString(2));
				lista_cursos.add(curso);
			}

			return lista_cursos;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Método getCurriculo()
	 * Carrega os dados do Curriculo de um usuário
	 */
	public Curriculo getCurriculo(String email) {
		try {
			this.stmt = this.conexao.prepareStatement("SELECT * FROM CURRICULOS WHERE EMAIL = ?;");
			this.stmt.clearParameters();
			this.stmt.setString(1, email);
			Curriculo curriculo = new Curriculo();
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();

			this.result = this.stmt.executeQuery();
			if (this.result.next()) {
				curriculo.setEmail(this.result.getString(2));
				curriculo.setNome(this.result.getString(3));
				curriculo.setSobrenome(this.result.getString(4));
				curriculo.setCpf(this.result.getString(5)); 
				curriculo.setDataNascimento(this.result.getString(6));
				curriculo.setSexo(this.result.getString(7));
				curriculo.setEstadoCivil(this.result.getString(8));
				curriculo.setNacionalidade(this.result.getString(9));
				curriculo.setFoto(this.result.getString(10));
			}
			// carrega os cursos
			curriculo.setCursos(getCursos(email));
			// carrega o endereco
			curriculo.setEndereco(getEndereco(email));
			// carrega telefones
			curriculo.setTelefones(getTelefones(email));
			// carrega as formações escolares
			curriculo.setFormacoes(getFormacoes(email));
			// carrega as experiencias profissionais
			curriculo.setExperiencias(getExperiencias(email));
			return curriculo;

		} catch (SQLException exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/*
	 * Método getEndereco()
	 * Carrega os dados do Endereço de um usuário
	 */
	public Endereco getEndereco(String email) {
		try {
			this.stmt = this.conexao.prepareStatement("SELECT CEP, ENDERECO, NUMERO, BAIRRO, CIDADE, UF, PAIS, COMPLEMENTO FROM ENDERECOS WHERE EMAIL = ?;");
			this.stmt.clearParameters();
			this.stmt.setString(1, email);
			Endereco endereco = new Endereco();
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			this.result = this.stmt.executeQuery();
			if (this.result.next()) {
				endereco.setCep(this.result.getString(1));
				endereco.setEndereco(this.result.getString(2));
				endereco.setNumero(this.result.getInt(3));
				endereco.setBairro(this.result.getString(4));
				endereco.setCidade(this.result.getString(5));
				endereco.setUf(this.result.getString(6));
				endereco.setPais(this.result.getString(7));
				endereco.setComplemento(this.result.getString(8));
			}
			return endereco;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Método getTelefones()
	 * Carrega os telefones de um usuário
	 */
	public ArrayList<Telefone> getTelefones(String email) {
		try {
			this.stmt = this.conexao.prepareStatement("SELECT TIPO, DDD, NUM_TELEFONE FROM TELEFONES WHERE EMAIL = ?;");
			this.stmt.clearParameters();
			this.stmt.setString(1, email);
			ArrayList<Telefone> listaTelefones = new ArrayList<Telefone>();;
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			this.result = this.stmt.executeQuery();
			while (this.result.next()) {
				Telefone telefone = new Telefone();
				telefone.setTipo(this.result.getString(1));
				telefone.setDdd(this.result.getString(2));
				telefone.setNumero(this.result.getString(3));
				listaTelefones.add(telefone);
			}
			return listaTelefones;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Método getFormacoes()
	 * Carrega as Formações Acadêmcias do usuário
	 */
	public ArrayList<Formacao> getFormacoes(String email) {
		try {
			this.stmt = this.conexao.prepareStatement("SELECT TIPO_FORMACAO, INSTITUICAO, CURSO, INICIO, FIM, DESCRICAO FROM FORMACOES WHERE EMAIL = ?;");
			this.stmt.clearParameters();
			this.stmt.setString(1, email);
			ArrayList<Formacao> listaFormacao = new ArrayList<Formacao>();
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			this.result = this.stmt.executeQuery();
			while (this.result.next()) {
				Formacao formacao = new Formacao();
				formacao.setTipo(this.result.getString(1));
				formacao.setInstituicao(this.result.getString(2));
				formacao.setCurso(this.result.getString(3));
				formacao.setDtInicio(this.result.getString(4));
				formacao.setDtFim(this.result.getString(5));
				formacao.setDescricao(this.result.getString(6));
				// adciona na lista
				listaFormacao.add(formacao);
			}
			return listaFormacao;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Método getExperiencias()
	 * Carrega as Experiências Profissionais de um usuário
	 */
	public ArrayList<Experiencia> getExperiencias(String email) {
		try {
			this.stmt = this.conexao.prepareStatement("SELECT EMPRESA,LOCAL,CARGO,INICIO,FIM,OBSERVACOES FROM EXPERIENCIAS WHERE EMAIL = ?;");
			this.stmt.clearParameters();
			this.stmt.setString(1, email);
			ArrayList<Experiencia> listaExperiencia = new ArrayList<Experiencia>();
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			this.result = this.stmt.executeQuery();
			while (this.result.next()) {
				Experiencia experiencia = new Experiencia();
				experiencia.setEmpresa(this.result.getString(1));
				experiencia.setLocal(this.result.getString(2));
				experiencia.setCargo(this.result.getString(3));
				experiencia.setDtInicio(this.result.getString(4));
				experiencia.setDtFim(this.result.getString(5));
				experiencia.setObservacoes(this.result.getString(6));
				// adciona na lista
				listaExperiencia.add(experiencia);
			}
			return listaExperiencia;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Método verificaCurriculo()
	 * Verifica se o currículo existe no banco
	 */
	public boolean verificaCurriculo(String email) {
		try {
			this.stmt = this.conexao.prepareStatement("SELECT * FROM CURRICULOS WHERE EMAIL = ?;");
			this.stmt.clearParameters();
			this.stmt.setString(1, email);
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			this.result = this.stmt.executeQuery();
			if (this.result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	// cadastra dados basicos iniciais do cv
		public void cadastraCv(Curriculo cv){
			PreparedStatement stmt;
			
			
			try {
				if (conexao.isClosed()) {
					conexao = banco.getConexao();
				}
					stmt = conexao
		.prepareStatement("INSERT INTO CURRICULOS (EMAIL,NOME,SOBRENOME) VALUES(?,?,?)");
					stmt.setString(1, cv.getEmail());
					stmt.setString(2, cv.getNome());
					stmt.setString(3, cv.getSobrenome());
					stmt.execute();
					
		
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}


	/*
	 * Método salvaCurriculo
	 * Salva um currículo no banco
	 */
	public void salvaCurriculo(Curriculo curriculo) {
		if (verificaCurriculo(curriculo.getEmail())) {
			try {
				this.stmt = this.conexao.prepareStatement("UPDATE CURRICULOS SET NOME = ?, SOBRENOME= ?, CPF = ?, DT_NASCIMENTO = ?, SEXO = ?, ESTADO_CIVIL = ?, NACIONALIDADE = ?, FOTO = ? WHERE EMAIL = ?;");
				this.stmt.clearParameters();
				this.stmt.setString(1, curriculo.getNome());
				this.stmt.setString(2, curriculo.getSobrenome());
				this.stmt.setString(3, curriculo.getCpf());
				this.stmt.setString(4, curriculo.getDataNascimento());
				this.stmt.setString(5, curriculo.getSexo());
				this.stmt.setString(6, curriculo.getEstadoCivil());
				this.stmt.setString(7, curriculo.getNacionalidade());
				this.stmt.setString(8, curriculo.getFoto());
				this.stmt.setString(9, curriculo.getEmail());
				if (this.conexao.isClosed()) 
					this.conexao = this.banco.getConexao();
				this.stmt.execute();
				//salva o endereço
				salvaEndereco(curriculo.getEndereco(), curriculo.getEmail());
				salvaTelefones(curriculo.getTelefones(), curriculo.getEmail());
				salvaFormacoes(curriculo.getFormacoes(), curriculo.getEmail());
				salvaCursos(curriculo.getCursos(), curriculo.getEmail());
				salvaExperiencias(curriculo.getExperiencias(), curriculo.getEmail());
				salvaDadosAdicionais(curriculo.getDadosAdicionais(), curriculo.getEmail());
				salvaObjetivos(curriculo.getObjetivos(), curriculo.getEmail());
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		} else {
			try {
				this.stmt = this.conexao.prepareStatement("INSERT INTO CURRICULOS (EMAIL, NOME, SOBRENOME, CPF, DT_NASCIMENTO, SEXO, ESTADO_CIVIL, NACIONALIDADE, FOTO) VALUES(?,?,?,?,?,?,?,?,?)");
				this.stmt.clearParameters();
				this.stmt.setString(1, curriculo.getEmail());
				this.stmt.setString(2, curriculo.getNome());
				this.stmt.setString(3, curriculo.getSobrenome());
				this.stmt.setString(4, curriculo.getCpf());
				this.stmt.setString(5, curriculo.getDataNascimento());
				this.stmt.setString(6, curriculo.getSexo());
				this.stmt.setString(7, curriculo.getEstadoCivil());
				this.stmt.setString(8, curriculo.getNacionalidade());
				this.stmt.setString(9, curriculo.getFoto());
				// faz o cadastro no banco
				this.stmt.execute();
				// salva o endereço
				salvaEndereco(curriculo.getEndereco(), curriculo.getEmail());
				salvaTelefones(curriculo.getTelefones(), curriculo.getEmail());
				salvaFormacoes(curriculo.getFormacoes(), curriculo.getEmail());
				salvaCursos(curriculo.getCursos(), curriculo.getEmail());
				salvaExperiencias(curriculo.getExperiencias(), curriculo.getEmail());
				salvaDadosAdicionais(curriculo.getDadosAdicionais(), curriculo.getEmail());
				salvaObjetivos(curriculo.getObjetivos(), curriculo.getEmail());
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}

	/*
	 * 
	 * Método verificaDado()
	 * Verifica se o curriculo existe no banco
	 */
	 public boolean verificaDado(String comandoSQL) {
		try {
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			
			this.stmt = this.conexao.prepareStatement(comandoSQL);
			this.result = this.stmt.executeQuery();
			if (this.result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/*
	 * * Método insereEndereco()
	 *  Salva um endereço no banco de dados
	 */
	public void salvaEndereco(Endereco endereco, String email) {
		String comandoSQL = "SELECT * FROM ENDERECOS WHERE EMAIL = '" + email + "'";

		if (verificaDado(comandoSQL)) {
			try {
				this.stmt = this.conexao.prepareStatement("UPDATE ENDERECOS SET CEP = ?, ENDERECO = ?, NUMERO = ?, BAIRRO = ?, CIDADE = ?, UF = ?, PAIS = ?, COMPLEMENTO = ? WHERE EMAIL = ?;");
				this.stmt.clearParameters();
				this.stmt.setString(1, endereco.getCep());
				this.stmt.setString(2, endereco.getEndereco());
				this.stmt.setInt(3, endereco.getNumero());
				this.stmt.setString(4, endereco.getBairro());
				this.stmt.setString(5, endereco.getCidade());
				this.stmt.setString(6, endereco.getUf());
				this.stmt.setString(7, endereco.getPais());
				this.stmt.setString(8, endereco.getComplemento());
				this.stmt.setString(9, email);
				if (this.conexao.isClosed())
					this.conexao = this.banco.getConexao();
				this.stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try{
				this.stmt = this.conexao.prepareStatement("INSERT INTO ENDERECOS (EMAIL, CEP, ENDERECO, NUMERO, BAIRRO, CIDADE, UF, PAIS, COMPLEMENTO) VALUES (?,?,?,?,?,?,?,?,?);");
				this.stmt.clearParameters();
				this.stmt.setString(1, email);
				this.stmt.setString(2, endereco.getCep());
				this.stmt.setString(3, endereco.getEndereco());
				this.stmt.setInt(4, endereco.getNumero());
				this.stmt.setString(5, endereco.getBairro());
				this.stmt.setString(6, endereco.getCidade());
				this.stmt.setString(7, endereco.getUf());
				this.stmt.setString(8, endereco.getPais());
				this.stmt.setString(9, endereco.getComplemento());

				// faz o cadastro no banco
				this.stmt.execute();
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
	}
	
	/*
	 * * Método salvaTelefones()
	 *  Salva os telefones no banco de dados
	 */
	public void salvaTelefones(ArrayList<Telefone> telefones, String email) {
			try {
				this.stmt = this.conexao.prepareStatement("DELETE FROM TELEFONES WHERE EMAIL = ?;");
				this.stmt.clearParameters();
				this.stmt.setString(1, email);
				if (this.conexao.isClosed())
					this.conexao = this.banco.getConexao();
				this.stmt.execute();
				
				this.stmt = this.conexao.prepareStatement("INSERT INTO TELEFONES (EMAIL, TIPO, DDD, NUM_TELEFONE) VALUES (?, ?, ?, ?);");
				for (Telefone telefone : telefones) {
					this.stmt.clearParameters();
					this.stmt.setString(1, email);
					this.stmt.setString(2, telefone.getTipo());
					this.stmt.setString(3, telefone.getDdd());
					this.stmt.setString(4, telefone.getNumero());
					if (this.conexao.isClosed())
						this.conexao = this.banco.getConexao();
					this.stmt.execute();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/*
	 * * Método salvaFormacoes()
	 *  Salva as formações acadêmicas no banco de dados
	 */
	public void salvaFormacoes(ArrayList<Formacao> formacoes, String email) {
			try {
				this.stmt = this.conexao.prepareStatement("DELETE FROM FORMACOES WHERE EMAIL = ?;");
				this.stmt.clearParameters();
				this.stmt.setString(1, email);
				if (this.conexao.isClosed())
					this.conexao = this.banco.getConexao();
				this.stmt.execute();
				
				this.stmt = this.conexao.prepareStatement("INSERT INTO FORMACOES (EMAIL, TIPO_FORMACAO, INSTITUICAO, CURSO, INICIO, FIM, DESCRICAO) VALUES (?, ?, ?, ?, ?, ?, ?);");
				for (Formacao formacao : formacoes) {
					this.stmt.clearParameters();
					this.stmt.setString(1, email);
					this.stmt.setString(2, formacao.getTipo());
					this.stmt.setString(3, formacao.getInstituicao());
					this.stmt.setString(4, formacao.getCurso());
					this.stmt.setString(5, formacao.getDtInicio());
					this.stmt.setString(6, formacao.getDtFim());
					this.stmt.setString(7, "");
					if (this.conexao.isClosed())
						this.conexao = this.banco.getConexao();
					this.stmt.execute();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/*
	 * * Método salvaCursos()
	 *  Salva as cursos do usuário no banco de dados
	 */
	public void salvaCursos(ArrayList<Curso> cursos, String email) {
			try {
				this.stmt = this.conexao.prepareStatement("DELETE FROM CURSOS WHERE EMAIL = ?;");
				this.stmt.clearParameters();
				this.stmt.setString(1, email);
				if (this.conexao.isClosed())
					this.conexao = this.banco.getConexao();
				this.stmt.execute();
				
				this.stmt = this.conexao.prepareStatement("INSERT INTO CURSOS (EMAIL, NOME, GRAU_DE_CONHECIMENTO) VALUES (?, ?, ?);");
				for (Curso curso : cursos) {
					this.stmt.clearParameters();
					this.stmt.setString(1, email);
					this.stmt.setString(2, curso.getNome());
					this.stmt.setString(3, curso.getGrauConhecimento());
					if (this.conexao.isClosed())
						this.conexao = this.banco.getConexao();
					this.stmt.execute();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/*
	 * * Método salvaExperiencias()
	 *  Salva as experiências profissionais do usuário no banco de dados
	 */
	public void salvaExperiencias(ArrayList<Experiencia> experiencias, String email) {
			try {
				this.stmt = this.conexao.prepareStatement("DELETE FROM EXPERIENCIAS WHERE EMAIL = ?;");
				this.stmt.clearParameters();
				this.stmt.setString(1, email);
				if (this.conexao.isClosed())
					this.conexao = this.banco.getConexao();
				this.stmt.execute();
				
				this.stmt = this.conexao.prepareStatement("INSERT INTO EXPERIENCIAS (EMAIL, EMPRESA, LOCAL, CARGO, INICIO, FIM, OBSERVACOES) VALUES (?, ?, ?, ?, ?, ?, ?);");
				for (Experiencia experiencia : experiencias) {
					this.stmt.clearParameters();
					this.stmt.setString(1, email);
					this.stmt.setString(2, experiencia.getEmpresa());
					this.stmt.setString(3, experiencia.getLocal());
					this.stmt.setString(4, experiencia.getCargo());
					this.stmt.setString(5, experiencia.getDtInicio());
					this.stmt.setString(6, experiencia.getDtFim());
					this.stmt.setString(7, experiencia.getObservacoes());
					if (this.conexao.isClosed())
						this.conexao = this.banco.getConexao();
					this.stmt.execute();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/*
	 * * Método salvaDadosAdicionais()
	 *  Salva os dados adicionais do usuário no banco de dados
	 */
	public void salvaDadosAdicionais(DadosAdicionais dadosAdicionais, String email) {
		String comandoSQL = "SELECT * FROM DADOS_ADICIONAIS WHERE EMAIL = '" + email + "'";

		if (verificaDado(comandoSQL)) {
			try {
				this.stmt = this.conexao.prepareStatement("UPDATE DADOS_ADICIONAIS SET HABILITACAO = ?, VEICULO = ?, DISP_VIAJAR = ?, DISP_RESID = ?, OBSERVACOES = ? WHERE EMAIL = ?;");
				String strHabilit = "";
				String strVeiculo = "";
				for(String habilitacao : dadosAdicionais.getHabilitacoes()) {
					strHabilit = strHabilit + habilitacao + ";";
				}
				for(String veiculo : dadosAdicionais.getVeiculos()) {
					strVeiculo = strVeiculo + veiculo + ";";
				}
				this.stmt.clearParameters();
				this.stmt.setString(1, strHabilit);
				this.stmt.setString(2, strVeiculo);
				this.stmt.setString(3, dadosAdicionais.getDisponibilidadeViagem());
				this.stmt.setString(4, dadosAdicionais.getDisponibilidadeMudanca());
				this.stmt.setString(5, "");
				this.stmt.setString(6, email);
				if (this.conexao.isClosed())
					this.conexao = this.banco.getConexao();
				this.stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try{
				this.stmt = this.conexao.prepareStatement("INSERT INTO DADOS_ADICIONAIS (EMAIL, HABILITACAO, VEICULO, DISP_VIAJAR, DISP_RESID, OBSERVACOES) VALUES (?, ?, ?, ?, ?, ?);");
				String strHabilit = "";
				String strVeiculo = "";
				for(String habilitacao : dadosAdicionais.getHabilitacoes()) {
					strHabilit = strHabilit + habilitacao + ";";
				}
				for(String veiculo : dadosAdicionais.getVeiculos()) {
					strVeiculo = strVeiculo + veiculo + ";";
				}
				this.stmt.clearParameters();
				this.stmt.setString(1, email);
				this.stmt.setString(2, strHabilit);
				this.stmt.setString(3, strVeiculo);
				this.stmt.setString(4, dadosAdicionais.getDisponibilidadeViagem());
				this.stmt.setString(5, dadosAdicionais.getDisponibilidadeMudanca());
				this.stmt.setString(6, "");

				// faz o cadastro no banco
				this.stmt.execute();
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
	}
	
	/*
	 * * Método salvaObjetivos()
	 *  Salva os objetivos do usuário no banco de dados
	 */
	public void salvaObjetivos(Objetivo objetivos, String email) {
		String comandoSQL = "SELECT * FROM OBJETIVOS WHERE EMAIL = '" + email + "'";

		if (verificaDado(comandoSQL)) {
			try {
				this.stmt = this.conexao.prepareStatement("UPDATE OBJETIVOS SET JORNADA = ?, TP_CONTRATO = ?, NIV_HIERARQ_MIN = ?, NIV_HIERARQ_MAX = ?, PRETENSAO_SALARIAL = ? WHERE EMAIL = ?;");
				this.stmt.clearParameters();
				this.stmt.setString(1, objetivos.getJornadaTrabalho());
				this.stmt.setString(2, objetivos.getTipoContrato());
				this.stmt.setString(3, objetivos.getNivelHierarquicoMin());
				this.stmt.setString(4, objetivos.getNivelHierarquicoMax());
				this.stmt.setDouble(5, objetivos.getPretensaoSalarial());
				this.stmt.setString(6, email);
				if (this.conexao.isClosed())
					this.conexao = this.banco.getConexao();
				this.stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try{
				this.stmt = this.conexao.prepareStatement("INSERT INTO OBJETIVOS (EMAIL, JORNADA, TP_CONTRATO, NIV_HIERARQ_MIN, NIV_HIERARQ_MAX, PRETENSAO_SALARIAL) VALUES (?, ?, ?, ?, ?, ?);");
				this.stmt.clearParameters();
				this.stmt.setString(1, email);
				this.stmt.setString(2, objetivos.getJornadaTrabalho());
				this.stmt.setString(3, objetivos.getTipoContrato());
				this.stmt.setString(4, objetivos.getNivelHierarquicoMin());
				this.stmt.setString(5, objetivos.getNivelHierarquicoMax());
				this.stmt.setDouble(6, objetivos.getPretensaoSalarial());
				// faz o cadastro no banco
				this.stmt.execute();
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
	}

	// método fecha as conexões
	public void fechaConexao(Connection conexao, Statement st, ResultSet rs) {
		try {
			if (conexao != null || st != null) {
				st.close();
				conexao.close();
			}
			if (rs != null) {
				rs.close();
			}
			System.out.println("Conexões fechadas");

		} catch (Exception e) {
			System.out.println("Não foi possivel fechar as conexões de banco");
		}

	}

	// método fecha as conexões
	public void fechaConexao(Connection conexao, Statement st) {
		try {
			if (conexao != null || st != null) {
				st.close();
				conexao.close();
				System.out.println("Conexãoes fechadas");
			}

		} catch (Exception e) {
			System.out.println("Não foi possivel fechar as conexões de banco");
		}

	}

	public static void main(String[] args) {
		ManipulaBanco mb = new ManipulaBanco();
		Endereco end = new Endereco();
		mb.salvaEndereco(end, "clsddd@hotmail.com");
	}

	/*
	 * Método getDadosAdicionais()
	 * Carrega os dados adicionais de um usuário
	 */
	public DadosAdicionais getDadosAdicionais(String email) {
		try {
			this.stmt = this.conexao.prepareStatement("SELECT HABILITACAO, VEICULO, DISP_VIAJAR, DISP_RESID, OBSERVACOES FROM DADOS_ADICIONAIS WHERE EMAIL = ?;");
			this.stmt.clearParameters();
			this.stmt.setString(1, email);
			DadosAdicionais dadosAdicionais = new DadosAdicionais();
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			this.result = this.stmt.executeQuery();
			if (this.result.next()) {
				String strHabilitacoes = this.result.getString(1);
				ArrayList<String> habilitacoes = new ArrayList<String>();
				ArrayList<String> veiculos = new ArrayList<String>();
				while (strHabilitacoes.contains(";")) {
					int pos = strHabilitacoes.indexOf(";") - 1;
					habilitacoes.add(strHabilitacoes.substring(pos, pos + 1));
					strHabilitacoes = strHabilitacoes.replaceFirst(";", "");
				}
				strHabilitacoes = this.result.getString(2);
				while (strHabilitacoes.contains(";")) {
					int pos = strHabilitacoes.indexOf(";") - 2;
					veiculos.add(strHabilitacoes.substring(pos, pos + 2));
					strHabilitacoes = strHabilitacoes.replaceFirst(";", "");
				}
				dadosAdicionais.setHabilitacoes(habilitacoes);
				dadosAdicionais.setVeiculos(veiculos);
				dadosAdicionais.setDisponibilidadeViagem(this.result.getString(3));
				dadosAdicionais.setDisponibilidadeMudanca(this.result.getString(4));
				dadosAdicionais.setObservacoes("");
			}
			return dadosAdicionais;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Objetivo getObjetivos(String email) {
		try {
			this.stmt = this.conexao.prepareStatement("SELECT JORNADA, TP_CONTRATO,  NIV_HIERARQ_MIN, NIV_HIERARQ_MAX, PRETENSAO_SALARIAL FROM OBJETIVOS WHERE EMAIL = ?;");
			this.stmt.clearParameters();
			this.stmt.setString(1, email);
			Objetivo objetivos = new Objetivo();
			if (this.conexao.isClosed())
				this.conexao = this.banco.getConexao();
			this.result = this.stmt.executeQuery();
			if (this.result.next()) {
				objetivos.setJornadaTrabalho(this.result.getString(1));
				objetivos.setTipoContrato(this.result.getString(2));
				objetivos.setNivelHierarquicoMin(this.result.getString(3));
				objetivos.setNivelHierarquicoMax(this.result.getString(4));
				objetivos.setPretensaoSalarial(this.result.getDouble(5));
			}
			return objetivos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
