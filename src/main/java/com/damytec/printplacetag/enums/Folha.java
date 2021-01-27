package com.damytec.printplacetag.enums;

/**
 * @author lgdamy@raiadrogasil.com on 26/01/2021
 */
public enum Folha {
    A4(210,297),
    A3(297, 420),
    A2(420,594),
    A1(594,841),
    MEIO_METRO(500,1000),
    UM_METRO(1000,1000);

    private Folha(int largura, int altura){
        this.largura = largura;
        this.altura = altura;
    }

    private int largura;
    private int altura;

    @Override
    public String toString() {
        return String.format("%s - %dmm x %dmm", name(), largura, altura).replace("_"," ");
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }
}
