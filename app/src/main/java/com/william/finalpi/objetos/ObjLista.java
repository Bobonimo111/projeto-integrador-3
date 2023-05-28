package com.william.finalpi.objetos;

public class ObjLista {
    private int id;
    private String name;
    private String date_end;
    private String hour_end;
    private String img;



    public ObjLista(int id, String name, String date_end) {
        this.setId(id);
        this.setName(name);
        this.setDate(date_end);
    }
    public ObjLista(int id, String name, String date_end, String hour_end) {
        this.setId(id);
        this.setName(name);
        this.setDate(date_end);
        this.setHourEnd(hour_end);
    }

    public ObjLista(int id, String name, String date_end, String hour_end, String img) {
        this.setId(id);
        this.setName(name);
        this.setDate(date_end);
        this.setHourEnd(hour_end);
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

    public String getDateEnd() {
        return date_end;
    }

    public void setDate(String date) {
        this.date_end = date;
    }

    public String getHourEnd() {
        return hour_end;
    }

    public void setHourEnd(String hour) {
        this.hour_end = hour;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


}
