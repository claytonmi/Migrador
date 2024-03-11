package dao;

import geral.Dados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MigracaoDAO {

    private Dados dados;
    private ResultSet rs;
    private PreparedStatement stmt;

    public MigracaoDAO(Dados dados) {
        this.dados = dados;
        this.stmt = null;
        this.rs = null;
    }

    public void alterarMigracao(String nome, String descricao, int id_grupo, int id) throws SQLException {
        ResultSet rs = null;
        String sql = "update migracao set nome = ?, descricao = ?, id_grupo = ? where id = ?";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, descricao);
        stmt.setInt(3, id_grupo);
        stmt.setInt(4, id);
        stmt.executeUpdate();
    }

    public void inserirMigracao(String nome, String descricao, int id_grupo, int id_origem, int id_destino) throws SQLException {
        ResultSet rs = null;
        String sql = "insert into migracao values (default,?,?,?,?,?);";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, descricao);
        stmt.setInt(3, id_grupo);
        stmt.setInt(4, id_origem);
        stmt.setInt(5, id_destino);
        stmt.executeUpdate();

    }

    public ResultSet retornaListaMigracao() throws SQLException {
        ResultSet rs = null;
        String SQL = "select * from migracao";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        rs = stmt.executeQuery();
        return rs;
    }

    public ResultSet retornaListaMigracaoPorIdGrupo(int id_grupo) throws SQLException {
        ResultSet rs = null;
        String SQL = "select * from migracao where id_grupo = (?)";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setInt(1, id_grupo);
        rs = stmt.executeQuery();
        return rs;
    }

    public ResultSet retornaListaMigracaoPorNome(String nome) throws SQLException {
        ResultSet rs = null;
        String SQL = "select * from migracao where nome = (?)";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setString(1, nome);
        rs = stmt.executeQuery();
        return rs;
    }

}
