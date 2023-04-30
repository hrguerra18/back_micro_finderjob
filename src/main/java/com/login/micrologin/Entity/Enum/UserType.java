package com.login.micrologin.Entity.Enum;

public enum UserType {
    Company(1), Postulante(2);
    private int number;

    UserType(int number){
        this.number = number;
    }

    public int returnId() {
        return number;
    }

}
