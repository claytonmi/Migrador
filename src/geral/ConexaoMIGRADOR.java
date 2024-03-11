package geral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexaoMIGRADOR {

    private static Connection con;
    private static Statement stm;

    public Connection conectar() {
        String driver = "jdbc:postgresql:";
        String bancoDados = "//localhost:5432/BancoMIGRADOR";
        String user = "root";
        String pass = "db97!#!!";

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(driver + bancoDados, user, pass);
            stm = con.createStatement();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Leia o arquivo Instruções.txt!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Leia o arquivo Instruções.txt!");
        }
        return con;
    }
}
