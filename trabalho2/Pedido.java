package Model;

import java.util.Date;
import java.util.List;

public class Pedido extends Orcamento{
    private Date dataEntrega;
    private Boolean pago;
    private Date dataPagamento;
    private TipoPagamento tipoPagamento;
    private Situacao situacao;
}
