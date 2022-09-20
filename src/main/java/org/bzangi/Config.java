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
}
