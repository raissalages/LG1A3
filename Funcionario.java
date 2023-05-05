public class Funcionario extends Usuario implements IGerenciamentoDeLivros, IGerenciamentoDeUsuarios {

    public Funcionario(String nome, String cpf, String endereco, String email, String senha) {
        super(nome, cpf, endereco, email, senha);
    }

    @Override
    public void cadastrarLivro(Livro livro) {

    }

    @Override
    public void removerLivro(Livro livro) {

    }

    @Override
    public void buscarLivro(Livro livro) {

    }

    @Override
    public Livro atualizarLivro(int id) {
        return null;
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {

    }

    @Override
    public void removerUsuario(Usuario usuario) {

    }

    @Override
    public void buscarUsuario(Usuario usuario) {

    }

    @Override
    public Livro atualizarUsuario(String cpf) {
        return null;
    }
    public void emprestar(Funcionario funcionario, Cliente cliente){

    }
    private void realizarEmprestimo(Livro livro, Cliente cliente){
        if (livro.validaDisponibilidade() == true){
            Emprestimo emprestimo = new Emprestimo(livro, cliente, )
        }

    }
}
