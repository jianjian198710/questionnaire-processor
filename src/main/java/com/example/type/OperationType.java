package com.example.type;

public enum OperationType
{
    SELECT("select"),FILL("fill");
    
    private final String name;
    OperationType( String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    @Override
    public String toString(){
        return this.getName();
    }
}
