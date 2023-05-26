package com.william.finalpi.objetos;

public class ObjTarefa {
    private boolean concluida;
    private String title;
    private int id;

    private int lista_fk_id;

    public ObjTarefa(int id, String title, boolean concluida, int lista_fk_id) {
        this.concluida = concluida;
        this.title = title;
        this.id = id;
        this.lista_fk_id = lista_fk_id;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLista_fk_id() {
        return lista_fk_id;
    }

    public void setLista_fk_id(int lista_fk_id) {
        this.lista_fk_id = lista_fk_id;
    }
}
