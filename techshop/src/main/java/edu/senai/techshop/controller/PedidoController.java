package edu.senai.techshop.controller;

import edu.senai.techshop.entity.Pedido;
import edu.senai.techshop.entity.Produto;
import edu.senai.techshop.repository.dto.AdicionarProdutoPedidoDTO;
import edu.senai.techshop.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<?> getPedidos() {
        return new ResponseEntity<>(pedidoService.getPedidos(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> getPedidoPorCodigo(@PathVariable Long codigo) {
        Optional<Pedido> pedidoPorCodigo = pedidoService.getPedidoPorCodigo(codigo);
        if(pedidoPorCodigo.isPresent())
            return new ResponseEntity<>(pedidoPorCodigo.get(), HttpStatus.OK);
        return new ResponseEntity<>("Pedido não encontrado!", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody Pedido pedido) {
        return new ResponseEntity<>(pedidoService.criarPedido(pedido), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> atualizarPedido(@RequestBody Pedido pedidoDetails) {
        return new ResponseEntity<>(pedidoService.atualizarPedido(pedidoDetails), HttpStatus.OK);
    }

    @PatchMapping("/add_produto")
    public ResponseEntity<?> adicionarProdutoPedido(@RequestBody AdicionarProdutoPedidoDTO adicionarProdutoPedidoDTO) {
        return new ResponseEntity<>(pedidoService.adicionarProdutosPedido(adicionarProdutoPedidoDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> deletePedido(@PathVariable Long codigo) {
        pedidoService.excluirPedido(codigo);
        return new ResponseEntity<>("Pedido Excluído do Sucesso!", HttpStatus.OK);
    }
}
