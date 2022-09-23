package org.bzangi.rest.controller;

import org.bzangi.domain.entity.Cliente;
import org.bzangi.domain.repository.ClientesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClientesDao clientesDao;

//    @GetMapping
//    public ResponseEntity<?> getAllClientes(){
//        List<Cliente> clientes = clientesDao.findAll();
//        return ResponseEntity.ok().body(clientes);
//    }
//    BUSCA SIMPLES

//    BUSCA AVANÇADA QUE PERMITE PASSAR FILTROS VIA QUERY PARAMS ex: /api/clientes?name=bru&cpf=22
    @GetMapping
    public List<Cliente> advancedFindCliente(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro,matcher);
        return clientesDao.findAll(example);
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable("id") Integer id ){ // a variavel dentro de chaves esta sendo injetada pela annotation pathvariable
        return clientesDao
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente(@RequestBody Cliente cliente ){
        return clientesDao.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id){
        clientesDao.findById(id)
                .map( cliente -> {
                    clientesDao.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCliente(@PathVariable Integer id,
                                           @RequestBody Cliente cliente){
        clientesDao
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientesDao.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
}
