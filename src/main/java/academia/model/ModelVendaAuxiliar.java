package academia.model;

import jakarta.persistence.Lob;

public class ModelVendaAuxiliar extends ModelVenda {
    
    @Lob
    private String produtos;

    public ModelVendaAuxiliar(String produtos, Integer id, Integer caixa, Integer aluno, String nome, Integer tipoVenda, Integer tipoPagamento, String data, float valorTotal, Integer usuario, Integer loja) {
        super(id, caixa, aluno, nome, tipoVenda, tipoPagamento, data, valorTotal, usuario, loja);
        this.produtos = produtos;
    }

    public ModelVendaAuxiliar() {}
    
    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }
    
}
