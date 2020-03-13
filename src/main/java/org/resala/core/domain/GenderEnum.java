package org.resala.core.domain;

public enum GenderEnum {
    female(1), male(2);

    private int value;

    GenderEnum(int value) { this.value  = value; }

    public int getValue(){return  this.value;}

    public String firstLatter() {
        if(value==1) {
            return "F";
        }else {
            return "M";
        }
    }
}
