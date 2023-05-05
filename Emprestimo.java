import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private Cliente cliente;
    private Funcionario funcionario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Cliente cliente, Funcionario funcionario) {
        this.livro = livro;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.dataEmprestimo = ControleCalendario.getDataAtual();
        this.dataDevolucao = dataEmprestimo.plusDays(14);
        livro.setNumExemplaresDisponiveis(livro.getNumExemplaresDisponiveis() - 1);
    }
}
