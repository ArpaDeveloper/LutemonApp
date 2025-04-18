package com.example.lutemongo.lutemonhandling;

public class Lutemon {

    //Variables
    private String name;
    private String color;
    private int attack;
    private int health;
    private int max_health;
    private int defence;
    private int experience;
    private boolean isSelected = false;

    //Constructor
    public Lutemon(String name, String  color, int attack,int health, int max_health, int defence, int experience){
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.max_health = max_health;
        this.defence = defence;
        this.experience = experience;
    }

    //Used for the lutemon selection
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    //Getters
    public String getName(){
        return name;
    }

    public String getColor(){
        return color;
    }

    public int getAttack(){
        return attack;
    }

    public int getHealth(){
        return health;
    }

    public int getMaxHealth(){
        return max_health;
    }

    public int getDefence(){
        return defence;
    }

    public int getExperience(){
        return experience;
    }


    //Setters
    public void setName(String name){
        this.name = name;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setMaxHealth(int max_health){
        this.max_health = max_health;
    }

    public void setDefence(int defence){
        this.defence = defence;
    }

    public void setExperience(int experience){
        this.experience = experience;
    }


}
