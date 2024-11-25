package edu.senai.techshop.repository.dto;

import java.util.List;

public class AdicionarProdutoPedidoDTO {

    private Long pedido;
    private List<Long> produtos;

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public List<Long> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Long> produtos) {
        this.produtos = produtos;
    }
}
