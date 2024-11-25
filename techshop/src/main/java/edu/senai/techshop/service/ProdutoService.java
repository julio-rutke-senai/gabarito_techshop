package edu.senai.techshop.service;

import edu.senai.techshop.entity.Produto;
import edu.senai.techshop.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService{

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> getProdutoPorCodigo(Long codigo) {
        return produtoRepository.findById(codigo);
    }

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Produto produtoAlteracao) {
        return produtoRepository.save(produtoAlteracao);
    }

    public void excluirProduto(Long codigo) {
        produtoRepository.deleteById(codigo);
    }
}
