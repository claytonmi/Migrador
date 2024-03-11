package dao;

import geral.Dados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Grupo_migracaoDAO {

    private Dados dados;
    private ResultSet rs;
    private PreparedStatement stmt;

    public Grupo_migracaoDAO(Dados dados) {
        this.dados = dados;
        this.stmt = null;
        this.rs = null;
    }

    public void inserirGrupo(String nome) throws SQLException{
        String sql = "insert into grupo_migracao values (default,?)";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.executeUpdate();
    }
    
    public ResultSet retornaListaGrupos() throws SQLException {
        String SQL = "select * from grupo_migracao";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        rs = stmt.executeQuery();
        return rs;
    }

    public int retornaIdGrupoPorNome(String nome) throws SQLException {
        int id = 0;
        String SQL = "select id from grupo_migracao where nome = ?";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setString(1, nome);
        rs = stmt.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }
        public String retornaNomeGrupoPorId(int id) throws SQLException {
        String nome = "";
        String SQL = "select * from grupo_migracao where id = ?";
        stmt = this.dados.getConexaoMIGRADOR().prepareStatement(SQL);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        while (rs.next()) {
            nome = rs.getString("nome");
        }
        return nome;
    }
    
    
}
