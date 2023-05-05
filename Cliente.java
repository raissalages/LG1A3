public class Cliente extends Usuario{
    public Cliente(String nome, String cpf, String endereco, String email, String senha) {
        super(nome, cpf, endereco, email, senha);
        super.userType = UserType.CLIENTE;
    }
}
