package org.bzangi.domain.repository;

import org.bzangi.domain.entity.Cliente;
import org.bzangi.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PedidosDao extends JpaRepository <Pedido, Integer> {

    Set<Pedido> findByCliente(Cliente cliente);
}
