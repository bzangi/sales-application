package org.bzangi.service;

import org.bzangi.domain.entity.Pedido;
import org.bzangi.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvarPedido(PedidoDTO pedidoDTO);
}
