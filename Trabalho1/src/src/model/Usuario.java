package model;

public class Usuario {
	private int id;
	private String nome;
	private String cpf;
	private String endereco;
	private String email;
	private String senha;
	private boolean ativo;
	protected TipoUsuario tipoUsuario;
	public Usuario(){}
	public Usuario(int id, String nome, String cpf, String endereco, String email, String senha, TipoUsuario tipoUsuario) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.ativo = true;
	}
	public Usuario(String nome, String cpf, String endereco, String email, String senha, TipoUsuario tipoUsuario) {

		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.ativo = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public void setCpf(String cpf){
		this.cpf = cpf;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


}
