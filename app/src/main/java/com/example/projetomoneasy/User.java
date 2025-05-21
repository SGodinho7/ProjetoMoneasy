package com.example.projetomoneasy;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {
    private int id;
    private String nome;
    private String email;
    private String senha;
    ArrayList<Transaction> transactions;

    public void setInfo(int id, String nome, String email, String senha, JSONArray array) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        setTransactions(array);
    }

    public int getId() {
        return this.id;
    }

    public float getTotalBalance() {
        float balance = 0.0f;

        for (int i = 0; i < transactions.size(); i++) {
            balance += transactions.get(i).getValue();
        }

        return balance;
    }

    public void addTransaction(int id, float value, String desc, String date, int id_category) {
        transactions.add(new Transaction(id, value, desc, date, id_category));
    }

    private void setTransactions(JSONArray array) {
        JSONObject json;
        int id;
        float value;
        String desc;
        String date;
        int id_category;

        if (array.length() == 0) {
            transactions = new ArrayList<Transaction>();
            return;
        }

        transactions = new ArrayList<Transaction>();
        for(int i = 0; i < array.length(); i++) {
            try {
                json = new JSONObject(array.get(i).toString());
                id = json.getInt("id_transaction");
                value = (float) json.getDouble("value");
                desc = json.getString("desc");
                date = json.getString("date");
                id_category = json.getInt("id_category");
                transactions.add(new Transaction(id, value, desc, date, id_category));
            } catch (Exception e) {
                e.getCause().printStackTrace();
            }
        }
    }
}
