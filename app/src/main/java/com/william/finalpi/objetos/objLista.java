package com.william.finalpi.objetos;

public class objLista {
    private int id;
    private String name;
    private String date;
    private String hour;
    private String img;


    public objLista(int id, String name, String date, String hour) {
        this.setId(id);
        this.setName(name);
        this.setDate(date);
        this.setHour(hour);
    }



    public objLista(int id, String name, String date, String hour, String img) {
        this.setId(id);
        this.setName(name);
        this.setDate(date);
        this.setHour(hour);
        this.setImg(img);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
