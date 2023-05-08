package model;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Emprestimo {


	private int Id;
	private Livro livro;
	private Usuario usuario;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;

	public Emprestimo(){}
	public Emprestimo(Livro livro, Usuario usuario) {
		this.livro = livro;
		this.usuario = usuario;
		this.dataEmprestimo = LocalDate.now();
		livro.setExemplares(livro.getExemplares() - 1);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public boolean estaAtrasado() {
		LocalDate dataAtual = LocalDate.now();

		long tempoDeEmprestimo = ChronoUnit.DAYS.between(dataEmprestimo, dataAtual);

		if (tempoDeEmprestimo > 14){
			return true;
		}
		return false;
	}



}
