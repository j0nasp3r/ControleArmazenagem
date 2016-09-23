package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.Pessoa;
import javax.swing.JOptionPane;

/**
 *
 * @author j0nas
 */
public class PessoaDao {

    Statement st;
    PreparedStatement prepst;

    static String INSERT = "INSERT INTO pessoas("
            + "idpessoa, nome, cpf, rg, endereco, numero, bairro, "
            + "complemento, cidade, estado, cep, telefonefixo, telefonecelular, email, sexo) "
            + "  VALUES ((SELECT COALESCE(max(idpessoa)+1,1) FROM pessoas),?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    static String SELECTALL = "SELECT idpessoa, nome, cpf, rg, endereco, numero, bairro, "
            + "complemento, cidade, estado, cep, telefonefixo, telefonecelular, email, sexo"
            + " FROM pessoas order by idpessoa;";
    static String UPDATE = "UPDATE pessoas SET idpessoa = ?, nome = ?, cpf = ?, rg = ?, "
            + "endereco = ?, numero = ?, bairro = ? , complemento = ?, cidade = ?, estado = ? , "
            + "cep = ?, telefonefixo = ?, telefonecelular = ?, email = ?, sexo = ?  WHERE idpessoa = ? ;";
    static String DELETE = "DELETE FROM pessoas  WHERE idpessoa = ?;";

    public boolean insereCadastroPessoa(Pessoa pessoa) {
        ResultSet rs;
        int id = 0;
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(INSERT);
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getCpf());
            preparedStatement.setString(3, pessoa.getRg());
            preparedStatement.setString(4, pessoa.getEndereco());
            preparedStatement.setInt(5, pessoa.getNumero());
            preparedStatement.setString(6, pessoa.getBairro());
            preparedStatement.setString(7, pessoa.getComplemento());
            preparedStatement.setString(8, pessoa.getCidade());
            preparedStatement.setString(9, pessoa.getEstado());
            preparedStatement.setString(10, pessoa.getCep());
            preparedStatement.setString(11, pessoa.getTelfixo());
            preparedStatement.setString(12, pessoa.getTelcell());
            preparedStatement.setString(13, pessoa.getEmail());
            preparedStatement.setString(14, String.valueOf(pessoa.getSexo()));
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problema ao inserir Cadastro Pessoa: " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return false;
    }

    public ArrayList<Pessoa> buscaCadastroPessoa() {
        ArrayList<Pessoa> lista = new ArrayList<Pessoa>();

        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(SELECTALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("IdPessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setRg(rs.getString("rg"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setNumero(rs.getInt("numero"));
                pessoa.setBairro(rs.getString("bairro"));
                pessoa.setComplemento(rs.getString("complemento"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));
                pessoa.setCep(rs.getString("cep"));
                pessoa.setTelfixo(rs.getString("telefonefixo"));
                pessoa.setTelcell(rs.getString("telefonecelular"));
                pessoa.setSexo(rs.getString("Sexo").charAt(0));
                pessoa.setEmail(rs.getString("email"));
                lista.add(pessoa);
            }

        } catch (Exception ex) {
            System.out.println("Problema ao carregar cadastro de pessoas : " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return lista;
    }

    public boolean updateCadastroPessoa(Pessoa pessoa) {
        try {

            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(UPDATE);
            preparedStatement.setInt(1, pessoa.getIdPessoa());
            preparedStatement.setString(2, pessoa.getNome());
            preparedStatement.setString(3, pessoa.getCpf());
            preparedStatement.setString(4, pessoa.getRg());
            preparedStatement.setString(5, pessoa.getEndereco());
            preparedStatement.setInt(6, pessoa.getNumero());
            preparedStatement.setString(7, pessoa.getBairro());
            preparedStatement.setString(8, pessoa.getComplemento());
            preparedStatement.setString(9, pessoa.getCidade());
            preparedStatement.setString(10, pessoa.getEstado());
            preparedStatement.setString(11, pessoa.getCep());
            preparedStatement.setString(12, pessoa.getTelfixo());
            preparedStatement.setString(13, pessoa.getTelcell());
            preparedStatement.setString(14, pessoa.getEmail());
            preparedStatement.setString(15, String.valueOf(pessoa.getSexo()));
            preparedStatement.setInt(16, pessoa.getIdPessoa());
//            System.out.println(""+ preparedStatement.toString());
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao fazer update do update do cadastro de pessoa: " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return false;
    }

    public boolean excluirCadastroPessoa(int id) {
        try {
            PreparedStatement preparedStatement = Conexao.getConexao().prepareStatement(DELETE);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao deletar o pessoa do cadastro pessoa: " + ex);
            JOptionPane.showMessageDialog(null, "Erro:" + ex);
        }
        return false;
    }
}