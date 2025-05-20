package com.example.projetomoneasy;

public class User {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private float saldo_total;

    public void setInfo(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return this.id;
    }

    public float getSaldo() {
        return this.saldo_total;
    }

    public void addSaldo(float saldo) {
        saldo = Math.abs(saldo);
        this.saldo_total += saldo;
    }

    public void subSaldo(float saldo) {
        saldo = Math.abs(saldo);
        this.saldo_total -= saldo;
    }
}
