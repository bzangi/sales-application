package org.bzangi.domain.repository;

import org.bzangi.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosDao extends JpaRepository<Produto, Integer> {
}
