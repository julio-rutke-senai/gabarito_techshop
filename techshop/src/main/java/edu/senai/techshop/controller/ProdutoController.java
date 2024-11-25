package edu.senai.techshop.controller;
import edu.senai.techshop.entity.Produto;
import edu.senai.techshop.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<?> getProdutos() {
        return new ResponseEntity<>(produtoService.getProdutos(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> getProdutoPorCodigo(@PathVariable Long codigo) {
        Optional<Produto> produtoPorCodigo = produtoService.getProdutoPorCodigo(codigo);
        if(produtoPorCodigo.isPresent())
            return new ResponseEntity<>(produtoPorCodigo.get(), HttpStatus.OK);
        return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {
        return new ResponseEntity<>(produtoService.criarProduto(produto), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> atualizarProduto(@RequestBody Produto productDetails) {
        return new ResponseEntity<>(produtoService.atualizarProduto(productDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long codigo) {
        produtoService.excluirProduto(codigo);
        return new ResponseEntity<>("Produto Excluído do Sucesso!", HttpStatus.OK);
    }
}
