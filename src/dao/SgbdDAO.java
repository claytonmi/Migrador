package dao;

import geral.Dados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SgbdDAO {

    private Dados dados;
    private ResultSet rs;
    private PreparedStatement stmt;

    public SgbdDAO(Dados dados) {
        this.dados = dados;
        this.stmt = null;
        this.rs = null;
    }

    public ResultSet retornaPorId(int id) throws SQLException {
        ResultSet rs = null;
        String SQL = "select * from sgbd where id = ?";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        return rs;

    }

    public int retornaIdPorNome(String nome) throws SQLException {
        int id = 0;
        ResultSet rs = null;
        String SQL = "select * from sgbd where nome = ?";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setString(1, nome);
        rs = stmt.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id");
        }

        return id;
    }

}
