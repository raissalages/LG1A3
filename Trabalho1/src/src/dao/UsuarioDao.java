package dao;

import model.TipoUsuario;
import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao extends GenericDao{

	private String instrucaoSql; // Atributo para armazenar a instrução SQL a ser executada.
	private PreparedStatement comando; // Atributo usado para preparar e executar instrções SQL.
	private ResultSet registros; // Atributo que recebe os dados retornados por uma instrução SQL.
	private static String excecao = null; // Atributo para armazenar mensagens de excecao.

	public List<Usuario> consultaUsuario(String instrucaoSql){
		Usuario usuario;
		List<Usuario> usuarios = new ArrayList<Usuario>();
		this.instrucaoSql = instrucaoSql;

		try {
			excecao = ConnectionDatabase.conectarBd();
			if (excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);

				registros = comando.executeQuery();

				if (registros.next()) {
					registros.beforeFirst();
					while (registros.next()) {
						usuario = new Usuario();
						usuario.setId(registros.getInt("PK_Usuario"));
						usuario.setNome(registros.getString("Nome"));
						usuario.setCpf(registros.getString("CPF"));
						usuario.setEndereco(registros.getString("Endereco"));
						usuario.setEmail(registros.getString("Email"));
						usuario.setSenha(registros.getString("Senha"));
						usuario.setTipoUsuario(TipoUsuario.valueOf(registros.getString("Tipo")));


						usuarios.add(usuario);
					}
				}

				registros.close();
				comando.close();

				ConnectionDatabase.getConexaoBd().close();
			}
		} catch (Exception e) {
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
			usuarios = null;
		}

		return usuarios;
	}

	public List<Usuario> consultaUsuario(String instrucaoSql, int id){
		Usuario usuario;
		List<Usuario> usuarios = new ArrayList<Usuario>();
		this.instrucaoSql = instrucaoSql;

		try {
			excecao = ConnectionDatabase.conectarBd();
			if (excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);

				registros = comando.executeQuery();

				if (registros.next()) {
					registros.beforeFirst();
					while (registros.next()) {
						usuario = new Usuario();
						usuario.setId(registros.getInt("PK_Usuario"));
						usuario.setNome(registros.getString("Nome"));
						usuario.setCpf(registros.getString("CPF"));
						usuario.setEndereco(registros.getString("Endereco"));
						usuario.setEmail(registros.getString("Email"));
						usuario.setSenha(registros.getString("Senha"));
						usuario.setTipoUsuario(TipoUsuario.valueOf(registros.getString("Tipo")));


						usuarios.add(usuario);
					}
				}

				registros.close();
				comando.close();

				ConnectionDatabase.getConexaoBd().close();
			}
		} catch (Exception e) {
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
			usuarios = null;
		}

		return usuarios;
	}
	public boolean validarSecao(String email, String senha){
		Usuario usuario;
		int countRegistros = 0;
		instrucaoSql = "SELECT Email, Senha FROM TB_USUARIO WHERE Email = \"" + email + "\" && Senha = \"" + senha + "\"";
		//Isto resultaria em uma querry como
		//SELECT Email, Senha FROM TB_USUARIO WHERE Email = "exemplo@exemplo" && Senha = "exemplo"

		try {
			excecao = ConnectionDatabase.conectarBd(); // Abre a conexão com o banco de dados.
			if (excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql); // Obtem os dados de conexão com o banco de dados e prepara a instrução SQL.
				registros = comando.executeQuery(); // Executa a instrção SQL e retorna os dados ao objeto ResultSet.
				boolean validRow = registros.next();

				registros.close();
				comando.close();
				ConnectionDatabase.getConexaoBd().close();

				if(validRow) {
					return true;
				}else {
					return false;
				}
			}else{
				return false;
			}
		}catch (Exception e) {
			excecao = "Tipo de Exceção: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
			return false;
		}
	}

	public String inserirUsuario(Usuario usuario) {
		instrucaoSql = "INSERT INTO TB_USUARIO (nome, cpf, endereco, email, senha, tipo, ativo) VALUES (?,?,?,?,?,?,?)";
		usuario.setAtivo(true);
		return inserir(instrucaoSql, usuario.getNome(), usuario.getCpf(), usuario.getEndereco(), usuario.getEmail(), usuario.getSenha(), usuario.getTipoUsuario().name(), usuario.isAtivo());
	}

	public static void main(String[] args) {
		Usuario usuario = new Usuario("nome", "2323", "iefji", "kwdkasi", "jidadaoj", TipoUsuario.USUARIO);
		UsuarioDao ud = new UsuarioDao();

		ud.inserirUsuario(usuario);
	}
	public List<Usuario> retornarUsuarios() {

		instrucaoSql = "SELECT * FROM TB_USUARIO";
		return consultaUsuario(instrucaoSql);
	}

	public TipoUsuario retornarTipoUsuario(String email, String senha) {
		instrucaoSql = "select tipo from tb_usuario where email = " + email + " and senha = " + senha + ";";

		return TipoUsuario.ADMIN; //ignorar
	}

	public void modificarUsuario(Usuario usuarioAlvo, Usuario usuarioModificacao){
		instrucaoSql = "UPDATE TB_USUARIO SET Nome = \"" + usuarioModificacao.getNome() + "\", CPF = \"" + usuarioModificacao.getCpf() + "\", Endereco = \"" + usuarioModificacao.getEndereco() + "\", Email = \"" + usuarioModificacao.getEmail() +"\", Senha = \"" + usuarioModificacao.getSenha() + "\"  WHERE PK_Usuario = " + usuarioAlvo.getId();
		inserir(instrucaoSql);
	}

	public List<Usuario> retornarUsuarioPorCpf(String cpf) {
		instrucaoSql = "SELECT * FROM TB_USUARIO where cpf = ?";
		return consultaUsuario(instrucaoSql);

	}

	public String desativarUsuario(Usuario usuario){
		instrucaoSql = "UPDATE TB_USUARIO SET Ativo = false WHERE PK_Usuario = ? ";
		return inserir(instrucaoSql, usuario.getId());
	}

	public List<Usuario> filtrarUsuarios(String atributo, String valor){
		instrucaoSql = "SELECT * FROM TB_Usuario WHERE " + atributo + " like '%" + valor + "%'";

		return consultaUsuario(instrucaoSql);
	}
	public List<Usuario> retornarUsuarioPorId(int id){

		Usuario usuario;
		List<Usuario> usuarios = new ArrayList<Usuario>();
		instrucaoSql = "SELECT * FROM TB_usuario where PK_usuario = " + id;

		try {
			excecao = ConnectionDatabase.conectarBd();
			if (excecao == null) {
				comando = ConnectionDatabase.getConexaoBd().prepareStatement(instrucaoSql);

				registros = comando.executeQuery();

				if (registros.next()) {
					registros.beforeFirst(); // Retorna o cursor para antes do 1� registro.
					while (registros.next()) {
						// Atribui o Id e a Descri��o do cargo ao objeto Cargo por meio dos m�todos set e
						// adiciona este objeto ao ArrayList funcionarios.
						usuario = new Usuario();
						usuario.setId(registros.getInt("PK_usuario"));
						usuario.setNome(registros.getString("Nome"));
						usuario.setCpf(registros.getString("CPF"));
						usuario.setEndereco(registros.getString("Endereco"));
						usuario.setEmail(registros.getString("Email"));
						usuario.setSenha(registros.getString("Senha"));
						usuario.setTipoUsuario(TipoUsuario.valueOf(registros.getString("Tipo")));
						usuario.setAtivo(registros.getBoolean("Ativo"));
						usuarios.add(usuario);

					}
				}
				registros.close(); // Libera os recursos usados pelo objeto ResultSet.
				comando.close(); // Libera os recursos usados pelo objeto PreparedStatement.
				// Libera os recursos usados pelo objeto Connection e fecha a conex�o com o banco de dados.
				ConnectionDatabase.getConexaoBd().close();
			}
		} catch (Exception e) {
			excecao = "Tipo de Exce��o: " + e.getClass().getSimpleName() + "\nMensagem: " + e.getMessage();
			usuarios = null; // Caso ocorra qualquer exce��o.
		}
		return usuarios; 
	}
	public List<Usuario> ordenarUsuarios(String atributo){
		instrucaoSql = "SELECT * FROM TB_Usuario order by " + atributo;
		return consultaUsuario(instrucaoSql);
	}

	public String getExcecao() {
		return excecao;
	}
}
