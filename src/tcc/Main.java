package tcc;

import geral.ConexaoMIGRADOR;
import geral.Dados;
import java.sql.Connection;
import java.sql.SQLException;
import view.MenuMigracao;

public class Main {

    public static void main(String[] args) throws SQLException {
        //instanciar       
        ConexaoMIGRADOR cm = new ConexaoMIGRADOR();
        Dados d = new Dados();
        //setar valores
        d.setConexaoMIGRADOR(cm.conectar());
        //iniciar visualização
        MenuMigracao mm = new MenuMigracao(d);
        mm.setVisible(true);
    }
}
