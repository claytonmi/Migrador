package model;

public class MigracaoMODEL {
    private int id;
    private String nome;
    private String descricao;
    private int id_grupo;
    private int id_origem;
    private int id_destino;

    public MigracaoMODEL(int id, String nome, String descricao, int id_grupo, int id_origem, int id_destino) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.id_grupo = id_grupo;
        this.id_origem = id_origem;
        this.id_destino = id_destino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getId_origem() {
        return id_origem;
    }

    public void setId_origem(int id_origem) {
        this.id_origem = id_origem;
    }

    public int getId_destino() {
        return id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }
    
    
    
}
