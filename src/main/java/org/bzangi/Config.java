package org.bzangi;

import org.bzangi.domain.entity.Cliente;
import org.bzangi.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Development
public class Config {

    @Bean
    public CommandLineRunner execute() {
        return args -> {
            System.out.println("-------------------RUNNING IN DEVELOPMENT ENVIRONMENT-------------------");
        };
    }
    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            clientes.save(new Cliente("Bruno"));
            clientes.save(new Cliente("Giuseppe"));

            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("buscando clientes");
            List<Cliente> oi = clientes.findByNomeContaining("Giusep");
            System.out.println(oi);
        };
    }

}
