package Model;

import java.util.List;

public class ItemPedido {
    private Long id;
    private Peca peca;
    private Tamanho tamanho;
    private Modelo modelo;
    private Tecido tecido;
    //private String cor;
    private List<Adicional> adicionais;
    private Double valorItem;
}
