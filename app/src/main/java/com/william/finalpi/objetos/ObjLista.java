package com.william.finalpi.objetos;

public class ObjLista {
    private int id;
    private String name;
    private String dateEnd;
    private String dateInit;
    private String hourEnd;
    private String img;



    public ObjLista(int id, String name, String date_init) {
        this.setId(id);
        this.setName(name);
        this.setDateInit(date_init);
    }
    public ObjLista(int id, String name, String date_end, String hour_end) {
        this.setId(id);
        this.setName(name);
        this.setDateEnd(date_end);
        this.setHourEnd(hour_end);
    }

    public ObjLista(int id, String name, String date_end, String hour_end, String img) {
        this.setId(id);
        this.setName(name);
        this.setDateEnd(date_end);
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
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(String hour) {
        this.hourEnd = hour;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public String getDateInit() {
        return dateInit;
    }

    public void setDateInit(String dateInit) {
        this.dateInit = dateInit;
    }
}
