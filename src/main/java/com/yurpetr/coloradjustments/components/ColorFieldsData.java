package com.yurpetr.coloradjustments.components;

public class ColorFieldsData {

    private ColorValue required, target, obtained, initial;

    protected ColorFieldsData(ColorValue required, ColorValue target, ColorValue obtained,
            ColorValue initial) {

        this.required = required;
        this.target   = target;
        this.obtained = obtained;
        this.initial  = initial;
    }

    public ColorValue getRequired() {
        return required;
    }

    public ColorValue getTarget() {
        return target;
    }

    public ColorValue getObtained() {
        return obtained;
    }

    public ColorValue getInitial() {
        return initial;
    }

}
