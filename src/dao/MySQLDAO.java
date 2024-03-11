package dao;

import geral.Dados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLDAO {

    private Dados dados;
    private ResultSet rs;
    private PreparedStatement stmt;

    public MySQLDAO(Dados dados) {
        this.dados = dados;
        this.stmt = null;
        this.rs = null;
    }

    public ResultSet retornaColunasPorTabela(String nome_tabela) throws SQLException {
        ResultSet rs = null;
        String SQL = "describe " + nome_tabela + ";";
        stmt = this.dados.getConexaoDESTINO().prepareStatement(SQL);
        rs = stmt.executeQuery(); 
        return rs;

    }

}
