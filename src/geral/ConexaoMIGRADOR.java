package geral;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

public class ConexaoMIGRADOR {

    private static Connection con;
    private static Statement stm;

      public Connection conectar() {
        Properties prop = new Properties();
        try {
            criarArquivoIniSeNecessario();

            try (FileInputStream input = new FileInputStream("config.ini")) {
                prop.load(input);

                String driver = prop.getProperty("driver");
                String bancoDados = prop.getProperty("bancoDados");
                String user = prop.getProperty("user");
                String pass = prop.getProperty("pass");

                try {
                    Class.forName("org.postgresql.Driver");
                    con = DriverManager.getConnection(driver + bancoDados, user, pass);
                    stm = con.createStatement();
                } catch (ClassNotFoundException | SQLException e) {
                    mostrarErro("Erro ao estabelecer conexão com o banco de dados. Em caso de dúvidas, consulte o arquivo Instrucoes para obter informações adicionais e garantir a utilização do sistema.");
                    System.exit(1);
                }
                return con;
            }
        } catch (IOException e) {
            mostrarErro("Erro ao ler o arquivo config.ini, verifique os valores no arquivo!");
            System.exit(1);
        }
        return con;
    }

    private void criarArquivoIniSeNecessario() {
        String nomeArquivoIni = "config.ini";
        File arquivoIni = new File(nomeArquivoIni);

        // Verificar se o arquivo INI já existe
        if (!arquivoIni.exists()) {
            // Se não existir, criar e configurar com valores padrão
            Properties prop = new Properties();
            prop.setProperty("driver", "jdbc:postgresql:");
            prop.setProperty("bancoDados", "//localhost:5432/BancoMIGRADOR");
            prop.setProperty("user", "root");
            prop.setProperty("pass", "db97!#!!");

            // Gravar as propriedades no arquivo
            try (OutputStream output = new FileOutputStream(nomeArquivoIni)) {
                prop.store(output, "Configurações do Migrador de Banco de Dados");
            } catch (IOException e) {
                mostrarErro("Erro ao criar o arquivo INI: " + e.getMessage());
                System.exit(1);
            }
        }
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
