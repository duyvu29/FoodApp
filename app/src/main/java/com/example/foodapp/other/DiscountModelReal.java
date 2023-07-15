package com.example.foodapp.other;

public class DiscountModelReal {
    private String description;
    private int id;
    private int iduser;
    private ImageDiscount imageDiscount;
    private int money;
    private String name;
    private String time;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public ImageDiscount getImageDiscount() {
        return imageDiscount;
    }

    public void setImageDiscount(ImageDiscount imageDiscount) {
        this.imageDiscount = imageDiscount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DiscountModelReal(String description, int id, int iduser, ImageDiscount imageDiscount, int money, String name, String time) {
        this.description = description;
        this.id = id;
        this.iduser = iduser;
        this.imageDiscount = imageDiscount;
        this.money = money;
        this.name = name;
        this.time = time;
    }

    @Override
    public String toString() {
        return "DiscountModelReal{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", iduser=" + iduser +
                ", imageDiscount=" + imageDiscount +
                ", money=" + money +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
