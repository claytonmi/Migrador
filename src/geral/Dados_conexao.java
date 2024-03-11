package geral;

public class Dados_conexao {
    private String database1;
    private String database2;
    private String host1;
    private String host2;
    private String username1;
    private String username2;
    private String senha1;
    private String senha2;
    private String porta1;
    private String porta2;

    public Dados_conexao(String database1, String database2,String host1, String host2, String username1, String username2, String senha1, String senha2, String porta1, String porta2) {
        this.database1 = database1;
        this.database2 = database2;
        this.host1 = host1;
        this.host2 = host2;
        this.username1 = username1;
        this.username2 = username2;
        this.senha1 = senha1;
        this.senha2 = senha2;
        this.porta1 = porta1;
        this.porta2 = porta2;
    }

    public String getDatabase1() {
        return database1;
    }

    public void setDatabase1(String database1) {
        this.database1 = database1;
    }

    public String getDatabase2() {
        return database2;
    }

    public void setDatabase2(String database2) {
        this.database2 = database2;
    }

    
    
    public String getHost1() {
        return host1;
    }

    public void setHost1(String host1) {
        this.host1 = host1;
    }

    public String getHost2() {
        return host2;
    }

    public void setHost2(String host2) {
        this.host2 = host2;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public String getSenha1() {
        return senha1;
    }

    public void setSenha1(String senha1) {
        this.senha1 = senha1;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

    public String getPorta1() {
        return porta1;
    }

    public void setPorta1(String porta1) {
        this.porta1 = porta1;
    }

    public String getPorta2() {
        return porta2;
    }

    public void setPorta2(String porta2) {
        this.porta2 = porta2;
    }
    
    
    
    
    
}
