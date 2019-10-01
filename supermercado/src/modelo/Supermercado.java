/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.time.LocalDate;

/**
 *
 * @author Administrador
 */
public class Supermercado {
    private Integer codigo;
    private String nome_fantasia;
    private String razao_social;
    private Integer nr_de_funcionarios;
    private Double valor_na_bolsa;
    private LocalDate fundacao;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public Integer getNr_de_funcionarios() {
        return nr_de_funcionarios;
    }

    public void setNr_de_funcionarios(Integer nr_de_funcionarios) {
        this.nr_de_funcionarios = nr_de_funcionarios;
    }

    public Double getValor_na_bolsa() {
        return valor_na_bolsa;
    }

    public void setValor_na_bolsa(Double valor_na_bolsa) {
        this.valor_na_bolsa = valor_na_bolsa;
    }

    public LocalDate getFundacao() {
        return fundacao;
    }

    public void setFundacao(LocalDate fundacao) {
        this.fundacao = fundacao;
    }

    @Override
    public String toString() {
        return "ModeloSupermercado{" + "nome_fantasia=" + nome_fantasia + '}';
    }

    public void setFundacao(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
