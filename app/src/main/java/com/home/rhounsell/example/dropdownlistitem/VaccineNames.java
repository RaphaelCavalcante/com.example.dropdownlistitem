package com.home.rhounsell.example.dropdownlistitem;

/**
 * Created by RHounsell on 15/03/2017.
 */

public enum VaccineNames {
    V8("V8"),
    RAIVA("Raiva"),
    GIARDIA("Giardia"),
    PNEUMONIA("Pneumonia");
    private String name;
    VaccineNames(String name){
        this.name= name;
    }
    public String create(){
        return this.name;
    }
}
