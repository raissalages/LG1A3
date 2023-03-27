import java.util.ArrayList;
import java.util.List;

public class ListaProdutos {
    private Produto produto;
    private List<Produto> listaDeProdutos;
    private String format = "%-40s%s%n";
    public ListaProdutos(){
        listaDeProdutos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto){
        listaDeProdutos.add(produto);
    }

    public void exibirLista(){
        for (int i = 0; i < listaDeProdutos.size(); i++) {
            System.out.printf(format, listaDeProdutos.get(i).getNome(), listaDeProdutos.get(i). getPreco(), "\n");
        }
    }

    public void venderProduto(Produto produto, Double preco){
        try{
            listaDeProdutos.remove(produto);
            System.out.println("Produto removido com sucesso!");
        }
        catch(Exception ex){
            if (!listaDeProdutos.contains(produto)){
                System.out.println("O produto não está na lista!");
            }
            if(produto.getPreco() <=  preco){
                System.out.println("O preço fornecido é menor que o valor do produto.");
            }
        }
    }

}
