package geral;

import dao.SgbdDAO;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexaoMIGRACAO {

    private static Connection con;
    private static Statement stm;
    private SgbdDAO sgbdDAO;
    private Dados dados;

    public ConexaoMIGRACAO(Dados dados) {
        this.dados = dados;
        this.sgbdDAO = new SgbdDAO(this.dados);
    }
 
    public Connection conectar(String database, String host, String porta, String username, String senha, int id_sgbd, String tipo) throws SQLException {
        
        con = null;
        
        String driver = "";
        String classe = "";
        String credenciais = "";
        
        ResultSet rs = this.sgbdDAO.retornaPorId(id_sgbd);
        
        while(rs.next()){
            driver = rs.getString("driver");
            classe = rs.getString("classe");
        }  
        if(driver.equals("jdbc:sqlserver:")){
            credenciais = driver + "//" + host + ":" + porta + ";" + database + "?useSSL=false&allowPublicKeyRetrieval=true";  
        }else{
            credenciais = driver + "//" + host + ":" + porta + "/" + database + "?useSSL=false&allowPublicKeyRetrieval=true";            
        }
          
        try {
            Class.forName(classe);            
            con = DriverManager.getConnection(credenciais, username, senha);
            stm = con.createStatement();
   
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, " Conexão com problema!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, tipo + ": \n" + e.getMessage());
        }
        return con;
    }
}
