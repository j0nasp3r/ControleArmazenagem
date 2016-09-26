/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Embalagem;
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
public class EmbalagemDao {

    Statement st;
    PreparedStatement prepst;

    static String INSERT = "INSERT INTO embalagens(idEmbalagem, descricao) "
            + "VALUES ((SELECT COALESCE(max(idEmbalagem)+1,1) FROM embalagens),?);";
    static String SELECTALL = "SELECT idEmbalagem, descricao FROM embalagens order by idEmbalagem;";
    static String UPDATE = "UPDATE embalagens SET idEmbalagem = ?, descricao = ? WHERE idEmbalagem = ? ;";
    static String DELETE = "DELETE FROM embalagens  WHERE idEmbalagem = ?;";
    static String SELECTbuscaEmbalagem = "SELECT descricao FROM embalagens WHERE idEmbalagem = ?;";

    public boolean insereCadastroEmbalagem(Embalagem embalagem) {
        ResultSet rs;
        int id = 0;
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(INSERT);
            preparedStatement.setString(1, embalagem.getDescricao());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao inserir Cadastro embalagem:" + ex);
        }
        return false;
    }

    public ArrayList<Embalagem> buscaCadastroEmbalagem() {
        ArrayList<Embalagem> lista = new ArrayList<Embalagem>();

        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(SELECTALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Embalagem embalagem = new Embalagem();
                embalagem.setIdEmbalagem(rs.getInt("idEmbalagem"));
                embalagem.setDescricao(rs.getString("descricao"));
                lista.add(embalagem);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problema ao carregar cadastro de embalagem :" + ex);
        }
        return lista;
    }

    public boolean updateCadastroEmbalagem(Embalagem embalagem) {
        try {

            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(UPDATE);
            preparedStatement.setInt(1, embalagem.getIdEmbalagem());
            preparedStatement.setString(2, embalagem.getDescricao());
            preparedStatement.setInt(3, embalagem.getIdEmbalagem());
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problema ao fazer update do update do cadastro de embalagem:" + ex);
        }
        return false;
    }

    public boolean excluirCadastroEmbalagem(int id) {
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problema ao deletar o cidade do cadastro de embalagem:" + ex);
        }
        return false;
    }

    public String buscarEmbalagem(int idEmbalagem) {
        String embalagem = "";
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(SELECTbuscaEmbalagem);
            preparedStatement.setInt(1, idEmbalagem);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                embalagem = rs.getString("descricao");
            }

        } catch (Exception ex) {
            System.out.println("Problema ao carregar cadastro de cidades : " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return embalagem;
    }

}
