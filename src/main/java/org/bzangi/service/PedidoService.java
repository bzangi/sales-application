package org.bzangi.service;

import org.bzangi.domain.entity.Pedido;
import org.bzangi.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvarPedido(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
