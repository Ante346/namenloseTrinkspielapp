package com.example.namenlosetrinkspielapp;

public class Player {
    private String name = "";
    private int number;

    public Player(int number){
        this.number = number;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getNumber(){
        return number;
    }
}
