package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Livro;
public class LivroDao  extends GenericDao {
	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.
	private PreparedStatement comando; // Atributo usado para preparar e executar instrções SQL.
	private ResultSet registros; // Atributo que recebe os dados retornados por uma instrução SQL.
	private static String excecao = null; // Atributo para armazenar mensagens de excecao.
	public List<Livro> consultaLivros(String instrucaoSql){
		Livro livro;
		List<Livro> livros = new ArrayList<Livro>();
		this.instrucaoSql = instrucaoSql;

		try {
			excecao = ConnectionDatabase.conectarBd();
			if (excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);

				registros = comando.executeQuery();

				if (registros.next()) {
					registros.beforeFirst();
					while (registros.next()) {
						livro = new Livro();
						livro.setId(registros.getInt("PK_Livro"));
						livro.setTitulo(registros.getString("Titulo"));
						livro.setAutor(registros.getString("Autor"));
						livro.setEditora(registros.getString("Editora"));
						livro.setAnoPublicacao(registros.getInt("Ano"));
						livro.setExemplares(registros.getInt("Exemplares"));
						livro.setAtivo(registros.getBoolean("Ativo"));
						livros.add(livro);


					}
				}

				registros.close();
				comando.close();

				ConnectionDatabase.getConexaoBd().close();
			}
		} catch (Exception e) {
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
			livros = null;
		}

		return livros;
	}

	public List<Livro> consultaLivros(String instrucaoSql, int id){
		Livro livro;
		List<Livro> livros = new ArrayList<Livro>();
		this.instrucaoSql = instrucaoSql;

		try {
			excecao = ConnectionDatabase.conectarBd();
			if (excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);

				registros = comando.executeQuery();

				if (registros.next()) {
					registros.beforeFirst();
					while (registros.next()) {
						livro = new Livro();
						livro.setId(registros.getInt("PK_Livro"));
						livro.setTitulo(registros.getString("Titulo"));
						livro.setAutor(registros.getString("Autor"));
						livro.setEditora(registros.getString("Editora"));
						livro.setAnoPublicacao(registros.getInt("Ano"));
						livro.setExemplares(registros.getInt("Exemplares"));
						livro.setAtivo(registros.getBoolean("Ativo"));
						livros.add(livro);


					}
				}

				registros.close();
				comando.close();

				ConnectionDatabase.getConexaoBd().close();
			}
		} catch (Exception e) {
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
			livros = null;
		}

		return livros;
	}
	public String inserirLivro(Livro livro) {
		instrucaoSql = "INSERT INTO TB_LIVRO (autor, titulo, editora, ano, exemplares, ativo) VALUES (?,?,?,?,?,?)";
		livro.setAtivo(true);
		return inserir(instrucaoSql, livro.getAutor(), livro.getTitulo(), livro.getEditora(), livro.getAnoPublicacao(), livro.getExemplares(), livro.isAtivo());
	}

	public List<Livro> retornarLivros() {
		instrucaoSql = "SELECT * FROM TB_LIVRO";
		return consultaLivros(instrucaoSql);
	}

	public List<Livro> retornarLivroPorId(int id){
		Livro livro;
		List<Livro> livros = new ArrayList<Livro>();
		instrucaoSql = "SELECT * FROM TB_LIVRO where PK_LIVRO = " + id;

		try {
			excecao = ConnectionDatabase.conectarBd();
			if (excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);

				registros = comando.executeQuery();

				if (registros.next()) {
					registros.beforeFirst();
					while (registros.next()) {

						livro = new Livro();
						livro.setId(registros.getInt("PK_Livro"));
						livro.setTitulo(registros.getString("Titulo"));
						livro.setAutor(registros.getString("Autor"));
						livro.setEditora(registros.getString("Editora"));
						livro.setAnoPublicacao(registros.getInt("Ano"));
						livro.setExemplares(registros.getInt("Exemplares"));
						livro.setAtivo(registros.getBoolean("Ativo"));
						livros.add(livro);

					}
				}
				registros.close();
				comando.close();
				ConnectionDatabase.getConexaoBd().close();
			}
		} catch (Exception e) {
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
			livros = null; //
		}
		return livros;
	}


	public void desativarLivro(Livro livro){
		instrucaoSql = "UPDATE TB_LIVRO SET Ativo = false WHERE PK_Livro = ? ";
		inserir(instrucaoSql, livro.getId());
	}

	public List<Livro> filtrarLivros(String atributo, String valor){
		instrucaoSql = "SELECT * FROM TB_LIVRO WHERE " + atributo + " like '%" + valor + "%'";
		System.out.println(instrucaoSql);
		return consultaLivros(instrucaoSql);
	}

	public String getExcecao() {
		return excecao;
	}

	public List<Livro> ordenarLivros(String atributo){
		instrucaoSql = "select * from tb_livro order by " + atributo;

		return consultaLivros(instrucaoSql);
	}



}
