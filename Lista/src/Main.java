import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Produto roupinha = new Produto("vestido rosa", 120.90);
        Produto sapatinho = new Produto("sapato preto", 50.90);
        ListaProdutos lista = new ListaProdutos();

        lista.adicionarProduto(sapatinho);
        lista.exibirLista();

        lista.venderProduto(roupinha, 40.90);

        lista.exibirLista();
    }
}