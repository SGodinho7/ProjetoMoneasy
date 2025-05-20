package com.example.projetomoneasy;

public class User {
    private String nome;
    private String email;
    private String senha;
    private float saldo_total;

    public void setInfo(String nome, String email, String senha, float saldo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.saldo_total = saldo;
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
