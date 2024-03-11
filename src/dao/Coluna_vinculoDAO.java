package dao;

import geral.Dados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Coluna_vinculoDAO {

    private Dados dados;
    private ResultSet rs;
    private PreparedStatement stmt;

    public Coluna_vinculoDAO(Dados dados) {
        this.dados = dados;
        this.stmt = null;
        this.rs = null;
    }

    public ResultSet retornaListaVinculoColunaPorNome(int id_vinculo) throws SQLException {
        String SQL = "select * from tabela_vinculo where id_vinculo = ?";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setInt(1, id_vinculo);
        rs = stmt.executeQuery();
        return rs;
    }

    public void inserirVinculoColuna(String origem, String destino, int id_tabela, String tipo_dado) throws SQLException {
        String SQL = "insert into coluna_vinculo (id,origem,destino,id_tabela,nome_vinculo,tipo_dado) values (default,?,?,?,?,?);";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setString(1, origem);
        stmt.setString(2, destino);
        stmt.setInt(3, id_tabela);
        stmt.setString(4, origem + "->" + destino);
        stmt.setString(5, tipo_dado);
        stmt.executeUpdate();
    }

    public void removerVinculoColuna(String nome_vinculo) throws SQLException {
        String SQL = "delete from coluna_vinculo where nome_vinculo = ?";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setString(1, nome_vinculo);
        stmt.executeUpdate();
    }

    public ResultSet retornaListaVinculoColunaPorIdTabela(int id_tabela) throws SQLException {
        String SQL = "select * from coluna_vinculo where id_tabela = ?";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setInt(1, id_tabela);
        rs = stmt.executeQuery();
        return rs;
    }

}
