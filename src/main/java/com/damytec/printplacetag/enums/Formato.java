package com.damytec.printplacetag.enums;

import java.util.EnumSet;

/**
 * @author lgdamy@raiadrogasil.com on 26/01/2021
 */
public enum Formato {
    RETO (true),
    OVAL (false),
    DISNEY(false);

    Formato(boolean dualBorders) {
        this.dualBorders = dualBorders;
    }

    boolean dualBorders;

    public boolean isDualBorders() {
        return dualBorders;
    }

    public static Formato from(String str) {
        return EnumSet.allOf(Formato.class).stream()
                .filter(f -> f.name().equalsIgnoreCase(str))
                .findAny().orElse(RETO);
    }

    public Formato next() {
        if (this.ordinal() >= Formato.values().length - 1) {
            return Formato.values()[0];
        }
        return Formato.values()[this.ordinal() + 1];
    }
}
