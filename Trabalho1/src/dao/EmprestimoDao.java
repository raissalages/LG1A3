package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Emprestimo;
import model.Livro;
import model.Usuario;

public class EmprestimoDao extends GenericDao{
	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.
	private PreparedStatement comando; // Atributo usado para preparar e executar instrções SQL.
	private ResultSet registros; // Atributo que recebe os dados retornados por uma instrução SQL.
	private static String excecao = null; // Atributo para armazenar mensagens de excecao.

	public String inserirEmprestimo(Emprestimo emprestimo) {
		instrucaoSql = "INSERT INTO TB_EMPRESTIMO (FK_Livro, Fk_Usuario, Data_Emprestimo) VALUES (" + emprestimo.getLivro().getId() + ", " + emprestimo.getUsuario().getId()+ ", curdate())";
		System.out.println(emprestimo.getLivro().getId());
		System.out.println(emprestimo.getUsuario().getId());

		//atualizarExemplares(emprestimo);
		//System.out.println("2");
		//java.sql.Date sqlDate = java.sql.Date.valueOf( emprestimo.getDataEmprestimo() );
		System.out.println("3");

		return inserir(instrucaoSql);
	}
	public void atualizarExemplares(Emprestimo emprestimo) {
		instrucaoSql = "UPDATE TB_LIVRO set Exemplares = Exemplares - 1 where PK_LIVRO = " + emprestimo.getLivro().getId();
		inserir(instrucaoSql);
	}


	public List<Emprestimo> consultaEmprestimos(String instrucaoSql){
		Emprestimo emprestimo;
		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

		try {
			excecao = ConnectionDatabase.conectarBd();
			if (excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);

				registros = comando.executeQuery();

				if (registros.next()) {
					registros.beforeFirst();
					while (registros.next()) {
						emprestimo = new Emprestimo();
						emprestimo.setId(registros.getInt("PK_Emprestimo"));
						LivroDao ld = new LivroDao();
						Livro livro = ld.retornarLivroPorId(registros.getInt("FK_LIVRO")).get(0);
						emprestimo.setLivro(livro);
						UsuarioDao ud = new UsuarioDao();
						Usuario usuario = ud.retornarUsuarioPorId(registros.getInt("FK_USUARIO")).get(0);
						emprestimo.setUsuario(usuario);
						emprestimo.setDataEmprestimo(registros.getDate("Data_Emprestimo").toLocalDate());


						emprestimos.add(emprestimo);
					}
				}

				registros.close();
				comando.close();
				ConnectionDatabase.getConexaoBd().close();
			}
		} catch (Exception e) {
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
			emprestimos = null;
		}

		return emprestimos;
	}
	public List<Emprestimo> retornarEmprestimos() {

		instrucaoSql = "SELECT * FROM TB_EMPRESTIMO";

		return consultaEmprestimos(instrucaoSql);
	}

	public String devolver(Emprestimo emprestimo){
		instrucaoSql = "update tb_emprestimo e, tb_livro l set e.data_devolucao = curdate(), l.exemplares = exemplares + 1 WHERE PK_Emprestimo = ? ";
		return inserir(instrucaoSql, emprestimo.getId());

	}

	public List<Emprestimo> filtrarEmprestimos(String atributo, String valor){
		int valorInt;

		switch(atributo){
			case "Livro":
				atributo = "pk_livro";
				valorInt = Integer.parseInt(valor);
				instrucaoSql = "SELECT * FROM TB_EMPRESTIMO WHERE " + atributo + " = " + valorInt;

				break;
			case "Usuario":
				atributo = "pk_usuario";
				valorInt = Integer.parseInt(valor);
				instrucaoSql = "SELECT * FROM TB_EMPRESTIMO WHERE " + atributo + " = " + valorInt;

				break;
			default:
				instrucaoSql = "SELECT * FROM TB_EMPRESTIMO WHERE " + atributo + " like '%" + valor + "%'";

		}
		return consultaEmprestimos(instrucaoSql);
	}

	public String getExcecao() {
		return excecao;
	}

	public List<Emprestimo> ordenarEmprestimos(String atributo){
		switch(atributo){
			case "Livro":
				atributo = "pk_livro";
				break;
			case "Usuario":
				atributo = "pk_usuario";
				break;

		}
		instrucaoSql = "select * from tb_emprestimo order by " + atributo;

		return consultaEmprestimos(instrucaoSql);
	}


}
