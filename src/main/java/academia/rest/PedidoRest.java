package academia.rest;

import academia.jpa.CarrinhoRepository;
import academia.jpa.EntregaPedidoRepository;
import academia.jpa.EntregaRepository;
import academia.jpa.PedidoRepository;
import academia.jpa.ProdutoPedidoRepository;
import academia.jpa.ProdutoRepository;
import academia.model.ModelCarrinho;
import academia.model.ModelEntrega;
import academia.model.ModelEntregaPedido;
import academia.model.ModelPedido;
import academia.model.ModelProduto;
import academia.model.ModelProdutoPedido;
import academia.model.ModelProdutoPedidoReview;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class PedidoRest {

    private PedidoRepository repository;
    private CarrinhoRepository carrinhoRepository;
    private ProdutoPedidoRepository prodPedidoRepository;
    private ProdutoRepository prodRepository;
    private EntregaRepository entregaRepository;
    private EntregaPedidoRepository entregaPedRepository;

    public PedidoRest(PedidoRepository repository, CarrinhoRepository carrinhoRepository,
            ProdutoPedidoRepository prodPedidoRepository, ProdutoRepository prodRepository,
            EntregaRepository entregaRepository, EntregaPedidoRepository entregaPedRepository) {
        super();
        this.repository = repository;
        this.carrinhoRepository = carrinhoRepository;
        this.prodPedidoRepository = prodPedidoRepository;
        this.prodRepository = prodRepository;
        this.entregaRepository = entregaRepository;
        this.entregaPedRepository = entregaPedRepository;
    }

    @GetMapping("/pedidos/{id}")
    public ModelPedido getPedidoById(@PathVariable int id) throws Exception {
        Optional<ModelPedido> pedido = repository.findById(id);
        if (pedido.isEmpty()) {
            throw new Exception("Erro: Id do pedido não encontrado");
        }
        return pedido.get();
    }

    @PostMapping("/pedidos")
    public ModelPedido createPedido(@Valid @RequestBody ModelPedido pedido) throws Exception {
        List<ModelCarrinho> carrinho = carrinhoRepository.findByUsuarioOrderByIdAsc(pedido.getUsuario());
        if (!carrinho.isEmpty()) {
            ModelPedido savedPedido = repository.save(pedido);
            for (ModelCarrinho modelCarrinho : carrinho) {
                Optional<ModelProduto> prod = prodRepository.findById(modelCarrinho.getProduto());
                ModelProduto product = prod.get();
                //Salva o Produto relacionado ao pedido
                ModelProdutoPedido prodPedido = new ModelProdutoPedido();
                prodPedido.setPedido(savedPedido.getId());
                prodPedido.setProduto(product.getId());
                prodPedido.setQuantidade(modelCarrinho.getQuantidade());
                prodPedido.setPreco(product.getPreco());
                prodPedidoRepository.save(prodPedido);
                //Diminui a quantidade do pedido do produto
                product.setQuantidade(product.getQuantidade() - prodPedido.getQuantidade());
                prodRepository.save(product);
                //Remove o produto do carrinho
                carrinhoRepository.delete(modelCarrinho);
            }
            cadastrarEntrega(savedPedido);
            return savedPedido;
        }
        throw new Exception("Não existe produtos no carrinho!");
    }

    private void cadastrarEntrega(ModelPedido pedido) {
        List<ModelProdutoPedido> lista = prodPedidoRepository.findByPedido(pedido.getId());
        //conta a quantidade de camisetar do pedido
        Integer contagem = 0;
        for (ModelProdutoPedido modelProdutoPedido : lista) {
            contagem += modelProdutoPedido.getQuantidade();
        }
        //busca a última entrega cadastrada
        Optional<ModelEntrega> lastEntrega = entregaRepository.findTopByOrderByIdDesc();
        //verifica se existe uma entrega
        if (!lastEntrega.isEmpty()) {
            //Busca a lista de pedidos da ultima entrega
            ModelEntrega model = lastEntrega.get();
            List<ModelEntregaPedido> listaEntrega = entregaPedRepository.findAllByEntrega(model.getId());
            //Conta a quantidade de itens a serem entregues 
            Integer contEntrega = 0;
            for (ModelEntregaPedido entregaPedido : listaEntrega) {
                contEntrega += entregaPedido.getQuantidade();
            }
            //Verifica se a última entrega ainda não saiu e se ainda tem espaço
            if (model.getDataSaida().isAfter(LocalDateTime.now()) && contEntrega < 10) {
                //verifica a disponibilidade a ser inclusa
                Integer disponivel = 10 - contEntrega;
                //verifica se a disponibilidade é maior que a quantidade do pedido
                if (disponivel > contagem) {
                    ModelEntregaPedido entPedido = new ModelEntregaPedido();
                    entPedido.setEntrega(model.getId());
                    entPedido.setPedido(pedido.getId());
                    entPedido.setQuantidade(contagem);
                    entregaPedRepository.save(entPedido);
                    //Acresenta 1 hora devido ao novo pedido a ser entregue na viagem do drone
                    model.setDataRetorno(model.getDataRetorno().plusHours(1));
                    entregaRepository.save(model);
                    contagem = 0;
                } else {
                    //Enquanto precisar gerar entrega para os produtos do pedido
                    while (contagem > 0) {
                        //Se ainda existe disponibilidade de entrega e se a quantidade de prod do pedido é maior que a disponibilidade
                        if (disponivel > 0 && contagem > disponivel) {
                            //diminui a disponibilidade da contagem de produtos do pedido
                            contagem = contagem - disponivel;
                            ModelEntregaPedido entPedido = new ModelEntregaPedido();
                            entPedido.setEntrega(model.getId());
                            entPedido.setPedido(pedido.getId());
                            entPedido.setQuantidade(disponivel);
                            entregaPedRepository.save(entPedido);
                            //Acresenta 1 hora devido ao novo pedido a ser entregue na viagem do drone
                            model.setDataRetorno(model.getDataRetorno().plusHours(1));
                            entregaRepository.save(model);
                            //zera a disponibilidade
                            disponivel = 0;
                        } else {
                            //Busca a ultima entrega cadastrada
                            Optional<ModelEntrega> lastEntregaFull = entregaRepository.findTopByOrderByIdDesc();
                            ModelEntrega modelBase = lastEntregaFull.get();

                            //cria uma nova entrega que terá sua saida assim que a ultima entrega retornar
                            ModelEntrega entrega = new ModelEntrega();
                            entrega.setDataSaida(modelBase.getDataRetorno());
                            entrega.setDataRetorno(entrega.getDataSaida().plusHours(1));
                            ModelEntrega newEntrega = entregaRepository.save(entrega);

                            //Se a contagem que sobrou for menor que a capacidade do drone
                            if (contagem < 10) {
                                //Cria um modelo de entregaPedido com a contagem que sobrou							
                                ModelEntregaPedido newEntregaPedido = new ModelEntregaPedido();
                                newEntregaPedido.setEntrega(newEntrega.getId());
                                newEntregaPedido.setPedido(pedido.getId());
                                newEntregaPedido.setQuantidade(contagem);
                                entregaPedRepository.save(newEntregaPedido);
                                //zera a contagem e sairá do while
                                contagem = 0;
                            } else {
                                //Se a contagem for maior que 10 
                                //Cria um modelo de entregaPedido com a capacidade máxima do drone
                                ModelEntregaPedido newEntregaPedido = new ModelEntregaPedido();
                                newEntregaPedido.setEntrega(newEntrega.getId());
                                newEntregaPedido.setPedido(pedido.getId());
                                newEntregaPedido.setQuantidade(10);
                                entregaPedRepository.save(newEntregaPedido);
                                //remove 10 da contagem
                                contagem = contagem - 10;
                            }
                        }
                    }
                }

            } //Se não tiver mais espaço ou se a última entrega já saiu
            else {
                //Busca a última entrega cadastrada
                Optional<ModelEntrega> lastEntregaFull = entregaRepository.findTopByOrderByIdDesc();
                ModelEntrega modelBase = lastEntregaFull.get();

                //cria uma nova entrega que terá sua saida assim que a ultima entrega retornar
                ModelEntrega entrega = new ModelEntrega();
                if (modelBase.getDataRetorno().isAfter(LocalDateTime.now())) {
                    entrega.setDataSaida(modelBase.getDataRetorno());
                } else {
                    entrega.setDataSaida(LocalDateTime.now());
                }
                entrega.setDataRetorno(entrega.getDataSaida().plusHours(1));
                ModelEntrega newEntrega = entregaRepository.save(entrega);

                if (contagem < 10) {
                    ModelEntregaPedido newEntregaPedido = new ModelEntregaPedido();
                    newEntregaPedido.setEntrega(newEntrega.getId());
                    newEntregaPedido.setPedido(pedido.getId());
                    newEntregaPedido.setQuantidade(contagem);
                    entregaPedRepository.save(newEntregaPedido);
                    contagem = 0;
                } else {
                    ModelEntregaPedido newEntregaPedido = new ModelEntregaPedido();
                    newEntregaPedido.setEntrega(newEntrega.getId());
                    newEntregaPedido.setPedido(pedido.getId());
                    newEntregaPedido.setQuantidade(10);
                    entregaPedRepository.save(newEntregaPedido);
                    contagem = contagem - 10;
                }
                while (contagem > 0) {
                    //Busca a última entrega cadastrada
                    Optional<ModelEntrega> lastEntregaFull1 = entregaRepository.findTopByOrderByIdDesc();
                    ModelEntrega modelBase1 = lastEntregaFull1.get();

                    //cria uma nova entrega que terá sua saida assim que a ultima entrega retornar
                    ModelEntrega entrega1 = new ModelEntrega();
                    entrega1.setDataSaida(modelBase1.getDataRetorno());
                    entrega1.setDataRetorno(entrega1.getDataSaida().plusHours(1));
                    ModelEntrega newEntrega1 = entregaRepository.save(entrega1);
                    //Se a contagem que sobrou for menor que a capacidade do drone
                    if (contagem < 10) {
                        //Cria um modelo de entregaPedido com a contagem que sobrou							
                        ModelEntregaPedido newEntregaPedido = new ModelEntregaPedido();
                        newEntregaPedido.setEntrega(newEntrega1.getId());
                        newEntregaPedido.setPedido(pedido.getId());
                        newEntregaPedido.setQuantidade(contagem);
                        entregaPedRepository.save(newEntregaPedido);
                        //zera a contagem e sairá do while
                        contagem = 0;
                    } else {
                        //Se a contagem for maior que 10 
                        //Cria um modelo de entregaPedido com a capacidade máxima do drone
                        ModelEntregaPedido newEntregaPedido = new ModelEntregaPedido();
                        newEntregaPedido.setEntrega(newEntrega1.getId());
                        newEntregaPedido.setPedido(pedido.getId());
                        newEntregaPedido.setQuantidade(10);
                        entregaPedRepository.save(newEntregaPedido);
                        //remove 10 da contagem
                        contagem = contagem - 10;
                    }
                }
            }

        } //Se ainda não existe uma entrega cadastrada. Primeira entrega
        else {
            ModelEntrega entrega = new ModelEntrega();
            entrega.setDataSaida(LocalDateTime.now());
            entrega.setDataRetorno(entrega.getDataSaida().plusHours(1));
            ModelEntrega newEntrega = entregaRepository.save(entrega);
            if (contagem < 10) {
                ModelEntregaPedido newEntregaPedido = new ModelEntregaPedido();
                newEntregaPedido.setEntrega(newEntrega.getId());
                newEntregaPedido.setPedido(pedido.getId());
                newEntregaPedido.setQuantidade(contagem);
                entregaPedRepository.save(newEntregaPedido);
                contagem = 0;
            } else {
                ModelEntregaPedido newEntregaPedido = new ModelEntregaPedido();
                newEntregaPedido.setEntrega(newEntrega.getId());
                newEntregaPedido.setPedido(pedido.getId());
                newEntregaPedido.setQuantidade(10);
                entregaPedRepository.save(newEntregaPedido);
                contagem = contagem - 10;
            }
            while (contagem > 0) {
                //Busca a ultima entrega cadastrada
                Optional<ModelEntrega> lastEntregaFull = entregaRepository.findTopByOrderByIdDesc();
                ModelEntrega modelBase = lastEntregaFull.get();
                //cria uma nova entrega que terá sua saida assim que a ultima entrega retornar
                ModelEntrega entrega1 = new ModelEntrega();
                entrega1.setDataSaida(modelBase.getDataRetorno());
                entrega1.setDataRetorno(entrega1.getDataSaida().plusHours(1));
                ModelEntrega newEntrega1 = entregaRepository.save(entrega1);
                //Se a contagem que sobrou for menor que a capacidade do drone
                if (contagem < 10) {
                    //Cria um modelo de entregaPedido com a contagem que sobrou							
                    ModelEntregaPedido newEntregaPedido = new ModelEntregaPedido();
                    newEntregaPedido.setEntrega(newEntrega1.getId());
                    newEntregaPedido.setPedido(pedido.getId());
                    newEntregaPedido.setQuantidade(contagem);
                    entregaPedRepository.save(newEntregaPedido);
                    //zera a contagem e sairá do while
                    contagem = 0;
                } else {
                    //Se a contagem for maior que 10 
                    //Cria um modelo de entregaPedido com a capacidade máxima do drone
                    ModelEntregaPedido newEntregaPedido = new ModelEntregaPedido();
                    newEntregaPedido.setEntrega(newEntrega1.getId());
                    newEntregaPedido.setPedido(pedido.getId());
                    newEntregaPedido.setQuantidade(10);
                    entregaPedRepository.save(newEntregaPedido);
                    //remove 10 da contagem
                    contagem = contagem - 10;
                }
            }
        }
    }

    @GetMapping("/pedidos/{id}/product")
    public List<ModelProdutoPedidoReview> getListProdutoPedido(@PathVariable int id) throws Exception {
        List<ModelProdutoPedidoReview> res = new ArrayList<>();

        List<ModelProdutoPedido> lista = prodPedidoRepository.findByPedido(id);

        if (!lista.isEmpty()) {
            for (ModelProdutoPedido modelProdutoPedido : lista) {
                Optional<ModelProduto> produto = prodRepository.findById(modelProdutoPedido.getProduto());

                ModelProdutoPedidoReview prod = new ModelProdutoPedidoReview();
                prod.setId(modelProdutoPedido.getId());
                prod.setPreco(modelProdutoPedido.getPreco());
                prod.setQuantidade(modelProdutoPedido.getQuantidade());
                prod.setProduto(produto.get());
                prod.setTotal(modelProdutoPedido.getPreco() * modelProdutoPedido.getQuantidade());
                res.add(prod);
            }
        }
        return res;
    }

    @GetMapping("/pedidos")
    public List<ModelPedido> getListPedido() {
        List<ModelPedido> lista = repository.findAll();
        System.out.println(lista);
        return lista;
    }

    @GetMapping("/pedidouser/{id}")
    public List<ModelPedido> getPedidoUsuarioById(@PathVariable int id) throws Exception {
        System.out.println(id);
        List<ModelPedido> pedido = repository.findAllByUsuario(id);
        if (pedido.isEmpty()) {
            throw new Exception("Usuário não possui pedido");
        } else {
            for (ModelPedido modelPedido : pedido) {
                Optional<ModelEntregaPedido> entrPed = entregaPedRepository.findTopByPedidoOrderByIdDesc(modelPedido.getId());
                ModelEntregaPedido entregaPed = entrPed.get();
                Optional<ModelEntrega> entr = entregaRepository.findById(entregaPed.getEntrega());
                ModelEntrega entrega = entr.get();
                if (entrega.getDataSaida().isAfter(LocalDateTime.now())) {
                    modelPedido.setSituacao("Preparando Pedido");
                } else if (entrega.getDataRetorno().isAfter(LocalDateTime.now()) && entrega.getDataSaida().isBefore(LocalDateTime.now())) {
                    modelPedido.setSituacao("Em trânsito");
                } else {
                    modelPedido.setSituacao("Entregue");
                }
            }
        }
        return pedido;
    }

}
