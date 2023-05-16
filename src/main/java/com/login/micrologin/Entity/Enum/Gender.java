package com.login.micrologin.Entity.Enum;

public enum Gender {
    MALE(1), FEMALE(2);

    private int genderId;

    Gender(int genderId){
        this.genderId = genderId;
    }

    public int returnGenderId(){
        return this.genderId;
    }
}
