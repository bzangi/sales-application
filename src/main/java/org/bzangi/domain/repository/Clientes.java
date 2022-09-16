package org.bzangi.domain.repository;

import org.bzangi.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    private static String SELECT_ALL = "SELECT * FROM CLIENTE ";
    private static String UPDATE = "UPDATE CLIENTE SET nome = ? WHERE id = ? ";
    private static String DELETE = "DELETE FROM CLIENTE WHERE id = ? ";

    @Transactional
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE,
                new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void deletar(Cliente cliente){
        jdbcTemplate.update(DELETE, new Object[]{cliente.getId()});
    }

    public List<Cliente> buscarPorNome (String nome){
        return jdbcTemplate.query(
                SELECT_ALL.concat("WHERE nome LIKE ? "),
                new Object[]{"%" + nome + "%"},
                getClienteMapper());
    }

    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, getClienteMapper());
    }

    private static RowMapper<Cliente> getClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
}
