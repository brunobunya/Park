package com.example.crudcarros.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Carro implements Serializable {

    public Carro(){

    }

    public Carro(String nome, String modelo, String placa){
        this.nome = nome;
        this.modelo = modelo;
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDados() {
        return "ID: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Modelo: " + modelo + "\n" +
                "Placa: " + placa + "\n";
    }

    @NonNull
    @Override
    public String toString() {
        return this.getNome();
    }

    private Long id;
    private String nome, modelo, placa;
}
