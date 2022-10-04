package org.bzangi.rest.controller;

import org.bzangi.domain.entity.ItemPedido;
import org.bzangi.domain.entity.Pedido;
import org.bzangi.rest.dto.InformacaoItemPedidoDTO;
import org.bzangi.rest.dto.InformacoesPedidoDTO;
import org.bzangi.rest.dto.PedidoDTO;
import org.bzangi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer savePedido(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = service.salvarPedido(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service.obterPedidoCompleto(id)
                .map( pedido -> converterPedido(pedido))
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    private InformacoesPedidoDTO converterPedido(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .id(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .itens(converterItens(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converterItens(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(
                itemPedido -> InformacaoItemPedidoDTO
                        .builder().descricaoProduto(itemPedido.getProduto().getDescricao())
                        .precoUnitario(itemPedido.getProduto().getPreco())
                        .quantidade(itemPedido.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }
}
