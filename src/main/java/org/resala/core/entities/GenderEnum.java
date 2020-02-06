package org.resala.core.entities;

public enum GenderEnum {
    female(1), male(2);

    private int value;

    GenderEnum(int value) { this.value  = value; }

    public int getValue(){return  this.value;}
}
