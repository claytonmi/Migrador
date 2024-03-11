package model;

public class Tabela_vinculoMODEL {
    private int id;
    private String origem;
    private String destino;
    private String script;
    private int id_migracao;
    private String nome_vinculo;

    public Tabela_vinculoMODEL(int id, String origem, String destino, String script, int id_migracao, String nome_vinculo) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.script = script;
        this.id_migracao = id_migracao;
        this.nome_vinculo = nome_vinculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public int getId_migracao() {
        return id_migracao;
    }

    public void setId_migracao(int id_migracao) {
        this.id_migracao = id_migracao;
    }

    public String getNome_vinculo() {
        return nome_vinculo;
    }

    public void setNome_vinculo(String nome_vinculo) {
        this.nome_vinculo = nome_vinculo;
    }
    
    
    
}
