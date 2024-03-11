package geral;

import java.sql.Connection;
import model.MigracaoMODEL;

public class Dados {

    private Connection conexaoMIGRADOR;
    private Connection conexaoORIGEM;
    private Connection conexaoDESTINO;
    private MigracaoMODEL migracao;
    private Dados_conexao dados_conexao;
    private String vinculo_atual;

    public Connection getConexaoORIGEM() {
        return conexaoORIGEM;
    }

    public void setConexaoORIGEM(Connection conexaoORIGEM) {
        this.conexaoORIGEM = conexaoORIGEM;
    }

    public Connection getConexaoDESTINO() {
        return conexaoDESTINO;
    }

    public void setConexaoDESTINO(Connection conexaoDESTINO) {
        this.conexaoDESTINO = conexaoDESTINO;
    }

    public MigracaoMODEL getMigracao() {
        return migracao;
    }

    public void setMigracao(MigracaoMODEL migracao) {
        this.migracao = migracao;
    }

    public String getVinculo_atual() {
        return vinculo_atual;
    }

    public void setVinculo_atual(String vinculo_atual) {
        this.vinculo_atual = vinculo_atual;
    }

    public Dados_conexao getDados_conexao() {
        return dados_conexao;
    }

    public void setDados_conexao(Dados_conexao dados_conexao) {
        this.dados_conexao = dados_conexao;
    }

    public Connection getConexaoMIGRADOR() {
        return conexaoMIGRADOR;
    }

    public void setConexaoMIGRADOR(Connection conexaoMIGRADOR) {
        this.conexaoMIGRADOR = conexaoMIGRADOR;
    }

}
