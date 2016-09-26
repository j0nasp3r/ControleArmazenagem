/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Produto;
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
public class ProdutoDao {

    Statement st;
    PreparedStatement prepst;

    static String INSERT = "INSERT INTO produtos(idProduto, idEmbalagem, nome, descricao, classetox "
            + "VALUES ((SELECT COALESCE(max(idproduto)+1,1) FROM produtos),?,?,?,?);";
    static String SELECTALL = "SELECT idProduto, idEmbalagem, nome, descricao, classetox"
            + " FROM produtos order by idProduto;";
    static String UPDATE = "UPDATE produtos SET idProduto = ?, idEmbalagem = ?, nome = ?, descricao = ?, classetox = ? "
            + "WHERE idProduto = ? ;";
    static String DELETE = "DELETE FROM produtos  WHERE idProduto = ?;";
    static String SELECTbuscaEmbalagem = "SELECT a.descricao FROM produtos INNER JOIN embalagens a "
            + "USING (idEmbalagem) WHERE idembalagem =?;";

    public boolean insereCadastroProduto(Produto produto) {
        ResultSet rs;
        int id = 0;
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(INSERT);
            preparedStatement.setInt(1, produto.getIdEmbalagem());
            preparedStatement.setString(2, produto.getNome());
            preparedStatement.setString(3, produto.getDescricao());
            preparedStatement.setString(4, produto.getClassetox());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problema ao inserir Cadastro Produto: " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return false;
    }

    public ArrayList<Produto> buscaCadastroProduto() {
        ArrayList<Produto> lista = new ArrayList<Produto>();

        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(SELECTALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setIdEmbalagem(rs.getInt("idEmbalagem"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setClassetox(rs.getString("classetox"));
                lista.add(produto);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problema ao carregar cadastro de produtos :" + ex);
        }
        return lista;
    }

    public boolean updateCadastroProduto(Produto produto) {
        try {

            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(UPDATE);
            preparedStatement.setInt(1, produto.getIdProduto());
            preparedStatement.setInt(2, produto.getIdEmbalagem());
            preparedStatement.setString(3, produto.getNome());
            preparedStatement.setString(4, produto.getDescricao());
            preparedStatement.setString(5, produto.getClassetox());
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problema ao fazer update do update do cadastro de produto:" + ex);
        }
        return false;
    }

    public boolean excluirCadastroProduto(int id) {
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problema ao deletar o produto do cadastro produto:" + ex);
        }
        return false;
    }

    public String buscarEmbalagem(int idEmbalagem) {
        String embalagem = "";
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(SELECTbuscaEmbalagem);
            preparedStatement.setInt(1, idEmbalagem);
            //System.out.println("" + preparedStatement.toString());
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
