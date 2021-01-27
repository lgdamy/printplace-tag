package com.damytec.printplacetag.enums;

import java.awt.*;

/**
 * @author lgdamy@raiadrogasil.com on 27/01/2021
 */
public enum Tema {

    YELLOW_BLACK(Color.RED, Color.LIGHT_GRAY, Color.YELLOW, Color.BLACK),
    BLUE_AMBER(Color.WHITE,Color.PINK,new Color(48,16,107),new Color(255,191,0)),
    LIME_PURPLE(Color.BLACK,Color.DARK_GRAY, new Color(191,255,0), new Color(191,0,255)),
    MAGENTA_CYAN(Color.BLUE,Color.BLACK,Color.MAGENTA,Color.CYAN);


    Tema(Color corte, Color margem, Color pagina, Color tag) {
        this.corte = corte;
        this.margem = margem;
        this.pagina = pagina;
        this.tag = tag;
    }

    private Color corte;
    private Color margem;
    private Color pagina;
    private Color tag;

    public Color getCorte() {
        return corte;
    }

    public Color getMargem() {
        return margem;
    }

    public Color getPagina() {
        return pagina;
    }

    public Color getTag() {
        return tag;
    }

    public Tema next() {
        if (this.ordinal() >= Tema.values().length - 1) {
            return Tema.values()[0];
        }
        return Tema.values()[this.ordinal() + 1];
    }
}
