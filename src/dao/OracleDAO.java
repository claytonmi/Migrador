package dao;

import geral.Dados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleDAO {
    private Dados dados;
    private ResultSet rs;
    private PreparedStatement stmt;
    
    public OracleDAO(Dados dados) {
        this.dados = dados;
        this.stmt = null;
        this.rs = null;
    }
    
    public ResultSet retornaColunasPorTabela(String nome_tabela) throws SQLException {
        ResultSet rs = null;
        String SQL = "select * from information_schema.columns where table_name = ? order by column_name;";
        stmt = this.dados.getConexaoDESTINO().prepareStatement(SQL);
        stmt.setString(1, nome_tabela);
        rs = stmt.executeQuery(); 
        return rs;        
    }    
}