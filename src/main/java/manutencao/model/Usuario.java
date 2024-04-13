package manutencao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="tbusuario")
public class Usuario {

    @Id
    @GeneratedValue
    private Integer id;
    
    //@Size(min=3, message="O nome deve conter ao menos 3 letras")
    private String nome;
    private String nascimento;
    private String cpf;
    private String sexo;
    private String ativo;
    private String fone;
    private String celular;
    private String email;
    private String cidade;
    private String uf;
    private String cep;
    private String rua;
    private Integer numero;
    private String bairro;
    private String tipo;

    public Usuario(String nome, String nascimento, String sexo, String ativo, String fone, String celular, String email, String cidade, String uf, String cep, String rua, Integer numero, String bairro) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.ativo = ativo;
        this.fone = fone;
        this.celular = celular;
        this.email = email;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
    }
    
    public Usuario() {
            super();
    }

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

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", nascimento=" + nascimento + ", email=" + email
                + ", ativo=" + ativo + ", tipo=" + tipo + "]";
    }
	
}
