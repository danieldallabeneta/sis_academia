package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity(name = "tbaluno")
public class ModelAluno {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 3)
    @NotNull
    private String nome;

    private String dataNascimento;

    @Max(value = 2)
    @Min(value = 1)
    private Integer sexo;

    @Max(value = 1)
    @Min(value = 0)
    private Integer ativo;

    private String celular;
    private String email;
    private String cidade;
    private String uf;
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private Integer loja;
    private String planoSaude;
    private String esporte;
    private String academia;
    private String motivoAcademia;
    private String comoSoube;
    private String quemInformou;

    public ModelAluno(Integer id, String nome, String dataNascimento, Integer sexo, Integer ativo, String celular, String email, String cidade, String uf, String cep, String rua, String numero, String bairro, Integer loja, String planoSaude, String esporte, String academia, String motivoAcademia, String comoSoube, String quemInformou) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.ativo = ativo;
        this.celular = celular;
        this.email = email;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.loja = loja;
        this.planoSaude = planoSaude;
        this.esporte = esporte;
        this.academia = academia;
        this.motivoAcademia = motivoAcademia;
        this.comoSoube = comoSoube;
        this.quemInformou = quemInformou;
    }
    
    public ModelAluno() {
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    public String getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }

    public String getEsporte() {
        return esporte;
    }

    public void setEsporte(String esporte) {
        this.esporte = esporte;
    }

    public String getAcademia() {
        return academia;
    }

    public void setAcademia(String academia) {
        this.academia = academia;
    }

    public String getMotivoAcademia() {
        return motivoAcademia;
    }

    public void setMotivoAcademia(String motivoAcademia) {
        this.motivoAcademia = motivoAcademia;
    }

    public String getComoSoube() {
        return comoSoube;
    }

    public void setComoSoube(String comoSoube) {
        this.comoSoube = comoSoube;
    }

    public String getQuemInformou() {
        return quemInformou;
    }

    public void setQuemInformou(String quemInformou) {
        this.quemInformou = quemInformou;
    }

    @Override
    public String toString() {
        return "ModelAluno{" + "id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", ativo=" + ativo + '}';
    }

}
