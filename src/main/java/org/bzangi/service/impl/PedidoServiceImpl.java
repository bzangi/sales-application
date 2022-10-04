package org.bzangi.service.impl;

import org.bzangi.domain.entity.Cliente;
import org.bzangi.domain.entity.ItemPedido;
import org.bzangi.domain.entity.Pedido;
import org.bzangi.domain.entity.Produto;
import org.bzangi.domain.repository.ClientesDao;
import org.bzangi.domain.repository.ItemsPedidoDao;
import org.bzangi.domain.repository.PedidosDao;
import org.bzangi.domain.repository.ProdutosDao;
import org.bzangi.exception.CustomException;
import org.bzangi.rest.dto.ItemPedidoDTO;
import org.bzangi.rest.dto.PedidoDTO;
import org.bzangi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidosDao pedidosDao;
    @Autowired
    private ClientesDao clientesDao;
    @Autowired
    private ProdutosDao produtosDao;
    @Autowired
    private ItemsPedidoDao itemsPedidoDao;

    @Override
    @Transactional
    public Pedido salvarPedido(PedidoDTO pedidoDTO) {
        Integer clienteId = pedidoDTO.getCliente();
        Cliente cliente = clientesDao
                .findById(clienteId)
                .orElseThrow(() -> new CustomException("Código de cliente inexistente."));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());

        List<ItemPedido> itemPedidos = converterItens(pedido, pedidoDTO.getItens());
        pedidosDao.save(pedido);
        itemsPedidoDao.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);

        return pedido;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if (itens.isEmpty()){
            throw new CustomException("Não é possível realizar um pedido sem itens.");
        }
        return itens
                .stream()
                .map( itemPedidoDTO -> {
                    Integer idProduto = itemPedidoDTO.getProduto();
                    Produto produto = produtosDao
                            .findById(idProduto)
                            .orElseThrow(() -> new CustomException("Código de produto inexistente: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
