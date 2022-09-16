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
            clientes.salvar(new Cliente("Bruno"));
            clientes.salvar(new Cliente("Giuseppe"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);
//
//            todosClientes.forEach(c -> {
//                c.setNome(c.getNome() + " 100% atualizado ");
//                clientes.atualizar(c);
//            });
//
//            System.out.println(clientes.buscarPorNome("Giu"));
//
//            todosClientes.forEach(System.out::println);
//            clientes.obterTodos().forEach(c -> {
//                clientes.deletar(c);
//            });
//            todosClientes = clientes.obterTodos();
//            if(todosClientes.isEmpty()){
//                System.out.println("Nenhum cliente na base de dados");
//            } else {
//                System.out.println(todosClientes);
//            }
        };
    }

}
