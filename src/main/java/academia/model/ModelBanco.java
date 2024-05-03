package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name="tbbanco")
public class ModelBanco {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @Size(min = 3)
    @NotNull
    private String nome;
    
    private Integer codigo;
    private Integer agencia;
    private Integer digitoAgencia;
    private Integer conta;
    private Integer digitoConta;
    
    private String dataCadastro;
    
    private Integer loja;

    public ModelBanco(Integer id, String nome, Integer codigo, Integer agencia, Integer digitoAgencia, Integer conta, Integer digitoConta, String dataCadastro, Integer loja) {
        super();
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.agencia = agencia;
        this.digitoAgencia = digitoAgencia;
        this.conta = conta;
        this.digitoConta = digitoConta;
        this.dataCadastro = dataCadastro;
        this.loja = loja;
    }

    public ModelBanco() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getDigitoAgencia() {
        return digitoAgencia;
    }

    public void setDigitoAgencia(Integer digitoAgencia) {
        this.digitoAgencia = digitoAgencia;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public Integer getDigitoConta() {
        return digitoConta;
    }

    public void setDigitoConta(Integer digitoConta) {
        this.digitoConta = digitoConta;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    @Override
    public String toString() {
        return "ModelBanco{" + "id=" + id + ", nome=" + nome + ", codigo=" + codigo + '}';
    }
    
    
}
