package com.example.lutemongo;

public class Lutemon {

    //Variables
    private String name;
    private int color;
    private int attack;
    private int health;
    private int max_health;
    private int defence;
    private int id;
    private int id_counter;

    //Constructor
    public Lutemon(String name, int  color, int attack, int max_health, int defence, int id){
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.max_health = max_health;
        this.defence = defence;
        this.id = id;
    }

    //Getters
    public String getName(){
        return name;
    }

    public int getColor(){
        return color;
    }

    public int getAttack(){
        return attack;
    }

    public int getMaxHealth(){
        return max_health;
    }

    public int getDefence(){
        return defence;
    }

    public int getId(){
        return id;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }

    public void setColor(int color){
        this.color = color;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }

    public void setMaxHealth(int max_health){
        this.max_health = max_health;
    }

    public void setDefence(int defence){
        this.defence = defence;
    }

    public void setId(int id){
        this.id = id;
    }

}
