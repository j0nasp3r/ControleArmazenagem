/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Estado;
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
public class EstadoDao {

    Statement st;
    PreparedStatement prepst;

    static String INSERT = "INSERT INTO estados(idEstado, nome, sigla) "
            + "VALUES ((SELECT COALESCE(max(idEstado)+1,1) FROM estados),?,?);";
    static String SELECTALL = "SELECT idEstado, nome, sigla "
            + "FROM estados order by idEstado;";
    static String UPDATE = "UPDATE estados SET idEstado = ?, nome = ?, sigla = ? "
            + "WHERE idEstado = ? ;";
    static String DELETE = "DELETE FROM estados  WHERE idEstado = ?;";
    
    static String SELECbuscaSigla = "SELECT sigla FROM estados "
            + "INNER JOIN cidades USING (idEstado) WHERE idCidade = ?;";

    public boolean insereCadastroEstado(Estado estado) {
        ResultSet rs;
        int id = 0;
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(INSERT);
            preparedStatement.setString(1, estado.getNome());
            preparedStatement.setString(2, estado.getSigla());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao inserir Cadastro Estado:" + ex);
        }
        return false;
    }

    public ArrayList<Estado> buscaCadastroEstado() {
        ArrayList<Estado> lista = new ArrayList<Estado>();

        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(SELECTALL);
            System.out.println(""+ preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Estado estado = new Estado();
                estado.setIdEstado(rs.getInt("IdEstado"));
                estado.setNome(rs.getString("nome"));
                estado.setSigla(rs.getString("sigla"));
                lista.add(estado);
            }

        } catch (Exception ex) {
            System.out.println("Problema ao carregar cadastro de estados : " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return lista;
    }

    public boolean updateCadastroEstado(Estado estado) {
        try {

            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(UPDATE);
            preparedStatement.setInt(1, estado.getIdEstado());
            preparedStatement.setString(2, estado.getNome());
            preparedStatement.setString(3, estado.getSigla());
            preparedStatement.setInt(4, estado.getIdEstado());
            //System.out.println(""+ preparedStatement.toString());
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problema ao fazer update do update do cadastro de estado:" + ex);
        }
        return false;
    }

    public boolean excluirCadastroEstado(int id) {
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problema ao deletar o estado do cadastro estado:" + ex);
        }
        return false;
    }

    public String buscarSiglaEstado(int idCidade) {
        String sigla = "";
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(SELECbuscaSigla);
            preparedStatement.setInt(1, idCidade);
            //System.out.println("" + preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                sigla = rs.getString("sigla");
            }

        } catch (Exception ex) {
            System.out.println("Problema ao carregar cadastro de cidades : " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return sigla;
    }
}
