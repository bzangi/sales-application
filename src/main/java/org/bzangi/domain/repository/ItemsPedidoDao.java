package org.bzangi.domain.repository;

import org.bzangi.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedidoDao extends JpaRepository<ItemPedido, Integer> {
}
