package model;

public class Livro {
	private int id;
	private int anoPublicacao;
	private int exemplares;

	private String titulo;
	private String autor;
	private String editora;

	public int getExemplares() {
		return exemplares;
	}

	public void setExemplares(int exemplares) {
		this.exemplares = exemplares;
	}

	public boolean isAtivo() {
		return ativo;
	}

	private boolean ativo;

	public Livro(){
	}
	public Livro(int id, String editora, String autor, String titulo, int anoPublicacao, int exemplares) {
		this.id = id;
		this.anoPublicacao = anoPublicacao;
		this.exemplares = exemplares;
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.ativo = true;
	}

	public Livro(String editora, String autor, String titulo, int anoPublicacao, int exemplares) {
		this.anoPublicacao = anoPublicacao;
		this.exemplares = exemplares;
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.ativo = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {

		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean validaDisponibilidade(){
		if(getExemplares() > 0)
			return true;
		else
			return false;
	}

	private void devolver(){}

}
