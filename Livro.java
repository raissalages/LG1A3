public class Livro {
    private int id;
    private int anoPublicacao;
    private static int numExemplaresTotal;
    private static int numExemplaresDisponiveis;
    private String titulo;
    private String autor;
    private String editora;
    private boolean disponibilidade;

    public Livro(int id, int anoPublicacao, int numExemplaresTotal, String titulo, String autor, String editora) {
        this.id = id;
        this.anoPublicacao = anoPublicacao;
        this.numExemplaresTotal = numExemplaresTotal;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
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

    public int getNumExemplaresDisponiveis() {
        return numExemplaresDisponiveis;
    }

    public void setNumExemplaresDisponiveis(int numExemplaresDisponiveis) {
        this.numExemplaresDisponiveis = numExemplaresDisponiveis;
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

    public boolean validaDisponibilidade(){
        if(getNumExemplaresDisponiveis() > 0)
            return true;
        else
            return false;
    }

    private void devolver(){}
    
}
