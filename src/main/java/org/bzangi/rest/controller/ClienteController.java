package org.bzangi.rest.controller;

import org.bzangi.domain.entity.Cliente;
import org.bzangi.domain.repository.ClientesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClientesDao clientesDao;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllClientes(){
        List<Cliente> clientes = clientesDao.findAll();
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getClienteById(@PathVariable("id") Integer id ){ // a variavel dentro de chaves esta sendo injetada pela annotation pathvariable
        Optional<Cliente> cliente = clientesDao.findById(id);
        try{
            return ResponseEntity.ok().body(cliente.get());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente ){
        Cliente clienteSalvo = clientesDao.save(cliente);
        return ResponseEntity.ok().body(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteCliente(@PathVariable Integer id){
        Optional<Cliente> cliente = clientesDao.findById(id);

        if (cliente.isPresent()){
            clientesDao.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity updateCliente(@PathVariable Integer id,
                                           @RequestBody Cliente cliente){
        return clientesDao
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientesDao.save(cliente);
                    return ResponseEntity.ok().body(cliente);
                }).orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
