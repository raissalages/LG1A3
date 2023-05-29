package Model;

public class Usuario {
    private Long id;
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;

    public Usuario(String nomeUsuario, String emailUsuario, String senhaUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
    }
}
