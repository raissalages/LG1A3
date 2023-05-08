package view;

import java.util.List;

import model.*;

public class Utilities {

    public static String[][] parseLivroListParaStringArray(List<Livro> livros){
        String[][] livrosString = new String[livros.size()][5];
        int index = 0;

        for(Livro livro: livros) {
            if(livro.isAtivo()) {
                String[] strings = {livro.getTitulo(), livro.getAutor(), livro.getEditora(), Integer.toString(livro.getAnoPublicacao()), Integer.toString(livro.getExemplares())};
                livrosString[index] = strings;
                index++;
            }
        }

        return livrosString;
    }

    public static String[][] parseUsuarioListParaStringArray(List<Usuario> usuarios){
        String[][] usuariosString = new String[usuarios.size()][5];
        int index = 0;

        for(Usuario usuario: usuarios) {
            if(true) {
                String[] strings = {usuario.getNome(), usuario.getCpf(), usuario.getEndereco(), usuario.getEmail(), usuario.getTipoUsuario().toString()};
                usuariosString[index] = strings;
                index++;
            }
        }

        return usuariosString;
    }

    public static String[][] parseEmprestimoListParaStringArray(List<Emprestimo> emprestimos){
        String[][] emprestimosString = new String[emprestimos.size()][4];

        for(Emprestimo emprestimo: emprestimos) {
            String[] strings = new String[4];
            if(emprestimo.getDataDevolucao() != null) {
                strings[0] = emprestimo.getLivro().getTitulo();
                strings[1] = emprestimo.getUsuario().getNome();
                strings[2] = emprestimo.getDataEmprestimo().toString().replace('-', '/');
                strings[3] = emprestimo.getDataDevolucao().toString().replace('-', '/');

            }else {
                strings[0] = emprestimo.getLivro().getTitulo();
                strings[1] = emprestimo.getUsuario().getNome();
                strings[2] = emprestimo.getDataEmprestimo().toString().replace('-', '/');
                strings[3] = "-";
            }

            emprestimosString[emprestimos.indexOf(emprestimo)] = strings;
        }
        return emprestimosString;
    }
}
