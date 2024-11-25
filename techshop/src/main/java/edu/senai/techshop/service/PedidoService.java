package edu.senai.techshop.service;

import edu.senai.techshop.entity.Pedido;
import edu.senai.techshop.entity.Produto;
import edu.senai.techshop.repository.PedidoRepository;
import edu.senai.techshop.repository.dto.AdicionarProdutoPedidoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
    }

    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> getPedidoPorCodigo(Long codigo) {
        return pedidoRepository.findById(codigo);
    }

    public Pedido criarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public String adicionarProdutosPedido(AdicionarProdutoPedidoDTO adicionarProdutoPedidoDTO){
        Optional<Pedido> pedido = getPedidoPorCodigo(adicionarProdutoPedidoDTO.getPedido());
        if(pedido.isEmpty())
            return "Pedido não encontrado!";
        adicionarProdutoPedidoDTO.getProdutos().stream().forEach(codigoProduto -> {
            Optional<Produto> produto = produtoService.getProdutoPorCodigo(codigoProduto);
            if(produto.isEmpty())
                return;
            pedido.get().getProdutos().add(produto.get());
        });

        pedidoRepository.save(pedido.get());

        return "Produto(s) adicionado(s) com sucesso!";
    }

    public Pedido atualizarPedido(Pedido pedidoAlteracao) {
        return pedidoRepository.save(pedidoAlteracao);
    }

    public void excluirPedido(Long codigo) {
        pedidoRepository.deleteById(codigo);
    }

}
