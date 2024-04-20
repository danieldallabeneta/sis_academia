package academia.dao;

import academia.jpa.ProdutoPedidoRepository;
import academia.jpa.ProdutoRepository;
import academia.model.ModelProduto;
import academia.model.ModelProdutoPedido;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

public class ProdutoDao {

    /*
	 * Retorna os sucessos de venda
     */
    public static List<ModelProduto> getSucessos(ProdutoRepository repo) {
        List<ModelProduto> sucess = new ArrayList<>();
        List<ModelProduto> res = repo.findAll();
        int contagem = 0;
        for (int i = 0; i < res.size(); i++) {
            if (contagem < 5 && res.get(i).getQuantidade() > 0) {
                sucess.add(res.get(i));
            }
            contagem++;
        }
        return sucess;
    }

    /*
	 * Retorna os 5 produtos mais vendidos
     */
    public static List<ModelProduto> getTop5(ProdutoRepository repository, ProdutoPedidoRepository prodPedRepository) {
        Map<Integer, Integer> contagem = new HashMap<>();
        List<ModelProdutoPedido> prodPed = prodPedRepository.findAll();
        for (ModelProdutoPedido objeto : prodPed) {
            boolean exist = contagem.containsKey(objeto.getProduto());
            if (!exist) {
                contagem.put(objeto.getProduto(), 1);
            } else {
                Integer valor = contagem.get(objeto.getProduto()) + 1;
                contagem.replace(objeto.getProduto(), valor);
            }
        }
        return getTopFiveProdutcs(repository, contagem);
    }

    private static List<ModelProduto> getTopFiveProdutcs(ProdutoRepository repository, Map<Integer, Integer> map) {
        List<ModelProduto> sucess = new ArrayList<>();
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Ordena a lista de acordo com os valores em ordem decrescente
        Collections.sort(entryList, Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Adiciona as chaves dos cinco primeiros elementos na lista
        for (int i = 0; i < Math.min(entryList.size(), 5); i++) {
            Optional<ModelProduto> prod = repository.findById(entryList.get(i).getKey());
            sucess.add(prod.get());
        }
        return sucess;
    }

}
