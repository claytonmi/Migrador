package geral;

import dao.Coluna_vinculoDAO;
import dao.Tabela_vinculoDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Tabela_vinculoMODEL;

public class ExecutarMigracao {

    private Dados dados;
    private ResultSet rs;
    private PreparedStatement stmt;
    private Coluna_vinculoDAO coluna_vinculoDAO;
    private Tabela_vinculoDAO tabela_vinculoDAO;
    private Tabela_vinculoMODEL tabela_vinculo;

    public ExecutarMigracao(Dados dados) throws SQLException {
        this.dados = dados;
        this.stmt = null;
        this.rs = null;
        this.coluna_vinculoDAO = new Coluna_vinculoDAO(dados);
        this.tabela_vinculoDAO = new Tabela_vinculoDAO(dados);
        this.rs = tabela_vinculoDAO.retornaListaVinculoTabelaPorNome(this.dados.getVinculo_atual());
        while (rs.next()) {
            this.tabela_vinculo = new Tabela_vinculoMODEL(rs.getInt("id"), rs.getString("origem"), rs.getString("destino"), rs.getString("script"), rs.getInt("id_migracao"), rs.getString("nome_vinculo"));
        }
    }

    public ArrayList<String> executarMigracao() throws SQLException {
        ArrayList<String> linhas_migracao = new ArrayList();
        String sql_inicial = "INSERT INTO " + this.tabela_vinculo.getDestino() + " (";
        String sql_final = "";
        this.rs = coluna_vinculoDAO.retornaListaVinculoColunaPorIdTabela(this.tabela_vinculo.getId());
        while (rs.next()) {
            sql_inicial = sql_inicial + rs.getString("destino") + ",";

        }
        sql_inicial = sql_inicial.substring(0, sql_inicial.length() - 1);
        sql_inicial = sql_inicial + ") VALUES (";
        ResultSet rs2 = null;
        stmt = this.dados.getConexaoORIGEM().prepareStatement(this.tabela_vinculo.getScript());
        rs2 = stmt.executeQuery();
        while (rs2.next()) {
            this.rs = coluna_vinculoDAO.retornaListaVinculoColunaPorIdTabela(this.tabela_vinculo.getId());
            sql_final = sql_inicial;
            while (rs.next()) {
                sql_final = sql_final + "'" + rs2.getString(rs.getString("origem")) + "',";
            }
            sql_final = sql_final.substring(0, sql_final.length() - 1);
            sql_final = sql_final + ");";
            linhas_migracao.add(sql_final);
        }
        return linhas_migracao;

    }

}
