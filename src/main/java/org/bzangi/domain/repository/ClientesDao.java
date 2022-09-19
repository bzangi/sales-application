package org.bzangi.domain.repository;

import org.bzangi.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesDao extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeContaining(String nome);

    @Query(value = " select * from cliente c where c.nome like %?% ", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    void deleteByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
    Cliente findPedidosById( @Param("id") Integer id );
}
