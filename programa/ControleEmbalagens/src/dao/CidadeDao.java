/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Cidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author j0nas
 */
public class CidadeDao {

    Statement st;
    PreparedStatement prepst;

    static String INSERT = "INSERT INTO cidades("
            + "idCidade, idEstado, nome"
            + "  VALUES ((SELECT COALESCE(max(idcidade)+1,1) FROM cidades),?,?);";
    static String SELECTALL = "SELECT idCidade, idEstado, nome"
            + " FROM cidades order by idCidade;";
    static String UPDATE = "UPDATE cidades SET idCidade = ?, idEstado = ?, nome = ?, "
            + "WHERE idCidade = ? ;";
    static String DELETE = "DELETE FROM cidades  WHERE idCidade = ?;";

    static String SELECTestadoCidade = "SELECT a.nome FROM cidades a "
            + " INNER JOIN estados USING (idEstado) where estados.sigla = ?;";

    static String SELECretornoCidadePesqEmp = "SELECT nome FROM cidades WHERE idCidade = ?;";

    //select a.nome, b.sigla from cidades a inner join estados b using (idEstado) where idCidade = 53
    public boolean insereCadastroCidade(Cidade cidade) {
        ResultSet rs;
        int id = 0;
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(INSERT);
            preparedStatement.setInt(1, cidade.getIdCidade());
            preparedStatement.setInt(2, cidade.getIdEstado());
            preparedStatement.setString(3, cidade.getNome());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problema ao inserir Cadastro Cidade: " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return false;
    }

    public ArrayList<Cidade> buscaCadastroCidade() {
        ArrayList<Cidade> lista = new ArrayList<Cidade>();

        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(SELECTestadoCidade);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setNome(rs.getString("nome"));
                lista.add(cidade);
            }

        } catch (Exception ex) {
            System.out.println("Problema ao carregar cadastro de cidades : " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return lista;
    }

    public boolean updateCadastroCidade(Cidade cidade) {
        try {

            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(UPDATE);
            preparedStatement.setInt(1, cidade.getIdCidade());
            preparedStatement.setInt(2, cidade.getIdEstado());
            preparedStatement.setString(3, cidade.getNome());
//            System.out.println(""+ preparedStatement.toString());
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao fazer update do update do cadastro de cidade: " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return false;
    }

    public boolean excluirCadastroCidade(int id) {
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao deletar o cidade do cadastro cidade: " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return false;
    }

    public ArrayList<Cidade> buscaEstadoCidade(String estado) {
        ArrayList<Cidade> lista = new ArrayList<Cidade>();

        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(SELECTestadoCidade);
            preparedStatement.setString(1, estado);
            //System.out.println("" + preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setNome(rs.getString("nome"));
                lista.add(cidade);
            }

        } catch (Exception ex) {
            System.out.println("Problema ao carregar cadastro de cidades : " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return lista;
    }

    public String buscarRetornoPesqEmp(int idCidade) {
        String cidade = "";
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(SELECretornoCidadePesqEmp);
            preparedStatement.setInt(1, idCidade);
            //System.out.println("" + preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                cidade = rs.getString("nome");
            }

        } catch (Exception ex) {
            System.out.println("Problema ao carregar cadastro de cidades : " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return cidade;
    }
}
