package org.bzangi;

import org.bzangi.domain.entity.Cliente;
import org.bzangi.domain.entity.Pedido;
import org.bzangi.domain.repository.ClientesDao;
import org.bzangi.domain.repository.PedidosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Development
public class Config {

    @Bean
    public CommandLineRunner execute() {
        return args -> {
            System.out.println("-------------------RUNNING IN DEVELOPMENT ENVIRONMENT-------------------");
        };
    }
    @Bean
    public CommandLineRunner init(@Autowired ClientesDao clientesDao, @Autowired PedidosDao pedidosDao) {
        return args -> {
            Cliente cliente = new Cliente("Bruno");
            clientesDao.save(cliente);

            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setDataPedido(LocalDate.now());
            pedido.setTotal(BigDecimal.valueOf(100));
            pedidosDao.save(pedido);

//            Cliente newCliente = clientesDao.findPedidosById(cliente.getId());
//            System.out.println(newCliente.getPedidos());

            pedidosDao.findByCliente(cliente).forEach(System.out::println);
        };
    }

}
