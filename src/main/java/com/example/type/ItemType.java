package com.example.type;

public enum ItemType
{
    SELECTION_ITEM("selection_item"),COMPLETION("completion");
    
    private final String name;
    ItemType( String name){
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
