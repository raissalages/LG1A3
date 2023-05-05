public interface IGerenciamentoDeUsuarios {

    public void cadastrarUsuario(Usuario usuario);
    public void removerUsuario(Usuario usuario);
    public void buscarUsuario(Usuario usuario);
    public Livro atualizarUsuario(String cpf);
}
