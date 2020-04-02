package org.resala.core.domain;

public enum GenderEnum {
    female(0), male(1);

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
