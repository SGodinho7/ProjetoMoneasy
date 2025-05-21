package com.example.projetomoneasy;

public class Transaction {
    private float value;
    private String desc;
    private String date;
    private int id_category;

    public Transaction(float value, String desc, String date, int id_category) {
        this.value = value;
        this.desc = desc;
        this.date = date;
        this.id_category = id_category;
    }

    public float getValue() {
        return this.value;
    }
}
