/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Supermercado;
import java.sql.Date ;
import java.time.format.DateTimeFormatter; 
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Administrador
 */
public class DaoSupermercado {
     public static boolean inserir(Supermercado objeto) {
        String sql = "INSERT INTO supermercado (nome_fantasia, razao_social, nr_de_funcionarios, fundacao, valor_na_bolsa) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome_fantasia());
            ps.setString(2, objeto.getRazao_social());
            ps.setInt(3, objeto.getNr_de_funcionarios());
            ps.setDate(4, Date.valueOf(objeto.getFundacao()));
            ps.setDouble(5, objeto.getValor_na_bolsa());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     public static void main(String[] args) {
        Supermercado objeto = new Supermercado();
        objeto.setNome_fantasia("Mercado Horizonte");
        objeto.setRazao_social("Carlos Almeida");
        objeto.setFundacao(LocalDate.parse("11/01/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setNr_de_funcionarios(100);
        objeto.setValor_na_bolsa(10.5);
        
        
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    public static boolean alterar(Supermercado objeto) {
        String sql = "UPDATE supermercado SET nome_fantasia = ?, razao_social = ?, fundacao = ?, nr_de_funcionarios = ?, valor_na_bolsa = ?  WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome_fantasia()); 
            ps.setString(2, objeto.getRazao_social());
            ps.setDate(3, Date.valueOf(objeto.getFundacao()));
            ps.setInt(4, objeto.getNr_de_funcionarios());
            ps.setDouble(5, objeto.getValor_na_bolsa());
            ps.setInt(6 , objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


     public static boolean excluir(Supermercado objeto) {
        String sql = "DELETE FROM supermercado WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     public static List<Supermercado> consultar() {
        List<Supermercado> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome_fantasia, razao_social, fundacao, nr_de_funcionarios, valor_na_bolsa FROM supermercado";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Supermercado objeto = new Supermercado();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome_fantasia(rs.getString("nome_fantasia"));
                objeto.setRazao_social(rs.getString("razao_social"));
                objeto.setFundacao(rs.getDate("fundacao").toLocalDate());
                objeto.setNr_de_funcionarios(rs.getInt("nr_de_funcionarios"));
                objeto.setValor_na_bolsa(rs.getDouble("valor_na_bolsa"));
                
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}

     public static Supermercado consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome_fantasia, razao_social, fundacao, nr_de_funcionarios, valor_na_bolsa  FROM supermercado WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Supermercado objeto = new Supermercado();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome_fantasia(rs.getString("nome_fantasia"));
                objeto.setRazao_social(rs.getString("razao_social"));
                objeto.setNr_de_funcionarios(rs.getInt("nr_de_funcionarios"));
                objeto.setValor_na_bolsa(rs.getDouble("valor_na_bolsa"));
                objeto.setFundacao(rs.getDate("fundacao").toLocalDate());
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
