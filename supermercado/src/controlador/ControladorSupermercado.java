/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import dao.DaoSupermercado;
import javax.swing.JOptionPane;
import modelo.Supermercado;
import tela.manutencao.ManutencaoSupermercado;
import  java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class ControladorSupermercado {

   public static void inserir(ManutencaoSupermercado man){
        Supermercado objeto = new Supermercado();
        objeto.setNome_fantasia(man.jtfnomefantasia.getText());
        objeto.setRazao_social(man.jtfrazaosocial.getText());
        objeto.setFundacao(LocalDate.parse(man.jtffundacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setNr_de_funcionarios(Integer.parseInt(man.jtfnrfuncionarios.getText()));
        objeto.setValor_na_bolsa(Double.parseDouble(man.jtfvalorbolsa.getText()));
        
        boolean resultado = DaoSupermercado.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

    public static void alterar(ManutencaoSupermercado man){
        Supermercado objeto = new Supermercado();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfcodigo.getText()));
        objeto.setNome_fantasia(man.jtfnomefantasia.getText());
        objeto.setRazao_social(man.jtfrazaosocial.getText());
        objeto.setFundacao(LocalDate.parse(man.jtffundacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setNr_de_funcionarios(Integer.parseInt(man.jtfnrfuncionarios.getText()));
        objeto.setValor_na_bolsa(Double.parseDouble(man.jtfvalorbolsa.getText()));
        
        boolean resultado = DaoSupermercado.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

    public static void excluir(ManutencaoSupermercado man){
        Supermercado objeto = new Supermercado();
        objeto.setCodigo(Integer.parseInt(man.jtfcodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoSupermercado.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome Fantasia");
        modelo.addColumn("Razão social");
        modelo.addColumn ("Fundação");
        modelo.addColumn ("Número de Funcionários");
        modelo.addColumn ("Valos na bolsa");
        
        List<Supermercado> resultados = DaoSupermercado.consultar();
        for (Supermercado objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome_fantasia());
            linha.add(objeto.getRazao_social());
            linha.add(objeto.getFundacao());
            linha.add(objeto.getNr_de_funcionarios());
            linha.add(objeto.getValor_na_bolsa());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
        public static void atualizaCampos(ManutencaoSupermercado man, int pk){ 
        Supermercado objeto = DaoSupermercado.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfcodigo.setText(objeto.getCodigo().toString());
        man.jtfnomefantasia.setText(objeto.getNome_fantasia());
        man.jtfrazaosocial.setText(objeto.getRazao_social());
        man.jtfnrfuncionarios.setText(objeto.getNr_de_funcionarios().toString());
        man.jtfvalorbolsa.setText(objeto.getValor_na_bolsa().toString());
        man.jtffundacao.setText(objeto.getFundacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfcodigo.setEnabled(false); //desabilitando o campo código
        man.btnadicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
