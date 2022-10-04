package org.bzangi.rest.controller;

import org.bzangi.domain.entity.Pedido;
import org.bzangi.rest.dto.PedidoDTO;
import org.bzangi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
