/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Empresa;
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
public class EmpresaDao {
    Statement st;
    PreparedStatement prepst;

    static String INSERT = "INSERT INTO empresas("
            + "idEmpresa, idCidadenome, cnpj, endereco, numero, bairro, "
            + "complemento, cep, telefonefixo, telefonecelular, email) "
            + "  VALUES ((SELECT COALESCE(max(idempresa)+1,1) FROM empresas),?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    static String SELECTALL = "SELECT idEmpresa, idCidade, nome, cnpj, endereco, numero, bairro, "
            + "complemento, cep, telefonefixo, telefonecelular, email"
            + " FROM empresas order by idEmpresa;";
    static String UPDATE = "UPDATE empresas SET idEmpresa = ?, idCidade = ?, nome = ?, cnpj = ?, "
            + "endereco = ?, numero = ?, bairro = ? , complemento = ?, "
            + "cep = ?, telefonefixo = ?, telefonecelular = ?, email = ? WHERE idEmpresa = ? ;";
    static String DELETE = "DELETE FROM empresas  WHERE idEmpresa = ?;";

    public boolean insereCadastroEmpresa(Empresa empresa) {
        ResultSet rs;
        int id = 0;
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(INSERT);
            preparedStatement.setInt(1, empresa.getIdCidade());
            preparedStatement.setString(2, empresa.getNome());
            preparedStatement.setString(3, empresa.getCnpj());
            preparedStatement.setString(4, empresa.getEndereco());
            preparedStatement.setInt(5, empresa.getNumero());
            preparedStatement.setString(6, empresa.getBairro());
            preparedStatement.setString(7, empresa.getComplemento());
            preparedStatement.setString(8, empresa.getCep());
            preparedStatement.setString(9, empresa.getTelfixo());
            preparedStatement.setString(10, empresa.getTelcell());
            preparedStatement.setString(11, empresa.getEmail());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problema ao inserir Cadastro Empresa: " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return false;
    }

    public ArrayList<Empresa> buscaCadastroEmpresa() {
        ArrayList<Empresa> lista = new ArrayList<Empresa>();

        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(SELECTALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setIdEmpresa(rs.getInt("IdEmpresa"));
                empresa.setIdCidade(rs.getInt("IdCidade"));
                empresa.setNome(rs.getString("nome"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setEndereco(rs.getString("endereco"));
                empresa.setNumero(rs.getInt("numero"));
                empresa.setBairro(rs.getString("bairro"));
                empresa.setComplemento(rs.getString("complemento"));
                empresa.setCep(rs.getString("cep"));
                empresa.setTelfixo(rs.getString("telefonefixo"));
                empresa.setTelcell(rs.getString("telefonecelular"));
                empresa.setEmail(rs.getString("email"));
                lista.add(empresa);
            }

        } catch (Exception ex) {
            System.out.println("Problema ao carregar cadastro de empresas : " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return lista;
    }

    public boolean updateCadastroEmpresa(Empresa empresa) {
        try {

            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(UPDATE);
            preparedStatement.setInt(1, empresa.getIdEmpresa());
            preparedStatement.setInt(2, empresa.getIdCidade());
            preparedStatement.setString(3, empresa.getNome());
            preparedStatement.setString(4, empresa.getCnpj());
            preparedStatement.setString(5, empresa.getEndereco());
            preparedStatement.setInt(6, empresa.getNumero());
            preparedStatement.setString(7, empresa.getBairro());
            preparedStatement.setString(8, empresa.getComplemento());
            preparedStatement.setString(10, empresa.getCep());
            preparedStatement.setString(11, empresa.getTelfixo());
            preparedStatement.setString(12, empresa.getTelcell());
            preparedStatement.setString(13, empresa.getEmail());
            preparedStatement.setInt(14, empresa.getIdEmpresa());
//            System.out.println(""+ preparedStatement.toString());
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao fazer update do update do cadastro de empresa: " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return false;
    }

    public boolean excluirCadastroEmpresa(int id) {
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(DELETE);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao deletar o empresa do cadastro empresa: " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return false;
    }
}
