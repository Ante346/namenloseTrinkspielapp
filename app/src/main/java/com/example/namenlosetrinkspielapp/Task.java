package com.example.namenlosetrinkspielapp;

public class Task implements Comparable<Task>{

    int id;
    String name;
    String description;

    public Task(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public int compareTo(Task o) {
        if(id<o.id){
            return 1;
        }else{
            return -1;
        }
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }
}
