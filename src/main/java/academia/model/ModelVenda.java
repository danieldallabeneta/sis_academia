package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity(name = "tbvenda")
public class ModelVenda {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private Integer caixa;
    
    private Integer aluno;
    
    private String nome;
    
    @Max(value = 3)/* Loja */
    @Min(value = 1)/* Bar*/
    private Integer tipoVenda;
    
    @Max(value = 2)/* A prazo*/
    @Min(value = 1)/* A vista*/
    private Integer tipoPagamento;
    
    private String data;
    
    private float valorTotal;
    
    private Integer usuario;
    
    private Integer loja;
    
    @Transient
    private String nomeAluno;

    public ModelVenda(Integer id, Integer caixa, Integer aluno, String nome, Integer tipoVenda, Integer tipoPagamento, String data, float valorTotal, Integer usuario, Integer loja) {
        this.id = id;
        this.caixa = caixa;
        this.aluno = aluno;
        this.nome = nome;
        this.tipoVenda = tipoVenda;
        this.tipoPagamento = tipoPagamento;
        this.data = data;
        this.valorTotal = valorTotal;
        this.usuario = usuario;
        this.loja = loja;
    }

    public ModelVenda() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaixa() {
        return caixa;
    }

    public void setCaixa(Integer caixa) {
        this.caixa = caixa;
    }

    public Integer getAluno() {
        return aluno;
    }

    public void setAluno(Integer aluno) {
        this.aluno = aluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(Integer tipoVenda) {
        this.tipoVenda = tipoVenda;
    }

    public Integer getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(Integer tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }
    
    public void carregaDadosModelo(ModelVendaAuxiliar aux){
        this.setAluno(aux.getAluno());
        this.setCaixa(aux.getCaixa());
        this.setNome(aux.getNome());
        this.setTipoVenda(aux.getTipoVenda());
        this.setTipoPagamento(aux.getTipoPagamento());
        this.setData(aux.getData());
        this.setValorTotal(aux.getValorTotal());
        this.setUsuario(aux.getUsuario());
        this.setLoja(aux.getLoja());
    }

    @Override
    public String toString() {
        return "ModelVenda{" + "id=" + id + ", caixa=" + caixa + ", aluno=" + aluno + ", nome=" + nome + ", tipoVenda=" + tipoVenda + ", tipoPagamento=" + tipoPagamento + ", data=" + data + ", valorTotal=" + valorTotal + ", usuario=" + usuario + '}';
    }
        
    
}
