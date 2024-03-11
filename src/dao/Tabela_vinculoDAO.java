package dao;

import geral.Dados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tabela_vinculoDAO {

    private Dados dados;
    private ResultSet rs;
    private PreparedStatement stmt;

    public Tabela_vinculoDAO(Dados dados) {
        this.dados = dados;
        this.stmt = null;
        this.rs = null;
    }

    public void removerVinculoTabela(String vinculo) throws SQLException {
        String SQL = "delete from tabela_vinculo where nome_vinculo = ?";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setString(1, vinculo);
        stmt.executeUpdate();

    }

    public void inserirVinculoTabela(String origem, String destino) throws SQLException {
        String SQL = "insert into tabela_vinculo (id,origem,destino,id_migracao,nome_vinculo) values (default,?,?,?,?);";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setString(1, origem);
        stmt.setString(2, destino);
        stmt.setInt(3, this.dados.getMigracao().getId());
        stmt.setString(4, origem + "->" + destino);
        stmt.executeUpdate();

    }

    public ResultSet retornaListaVinculoTabela() throws SQLException {
        String SQL = "select * from tabela_vinculo where id_migracao = ?";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setInt(1, this.dados.getMigracao().getId());
        rs = stmt.executeQuery();
        return rs;
    }
    
    public void salvarScript(String script, String vinculo) throws SQLException{
        String SQL = "update tabela_vinculo set script = ? where id_migracao = ? and nome_vinculo = ?;";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setString(1, script);
        stmt.setInt(2, this.dados.getMigracao().getId());
        stmt.setString(3, vinculo);
        stmt.executeUpdate();
    }

    public ResultSet retornaListaVinculoTabelaPorNome(String vinculo) throws SQLException {
        String SQL = "select * from tabela_vinculo where id_migracao = ? and nome_vinculo = ?";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setInt(1, this.dados.getMigracao().getId());
        stmt.setString(2, vinculo);
        rs = stmt.executeQuery();
        return rs;
    }

}
