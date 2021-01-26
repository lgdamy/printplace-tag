package com.damytec.printplacetag.pojo;

/**
 * @author lgdamy@raiadrogasil.com on 26/01/2021
 */
public class TagsPorFolha {
    private int qtdX;
    private int qtdY;

    public int getQtdX() {
        return qtdX;
    }

    public void setQtdX(int qtdX) {
        this.qtdX = qtdX;
    }

    public int getQtdY() {
        return qtdY;
    }

    public void setQtdY(int qtdY) {
        this.qtdY = qtdY;
    }

    public int getTotal() {
        return qtdX * qtdY;
    }

    @Override
    public String toString() {
        return String.format("%d tags por folha: %d x %d", getTotal(), qtdX, qtdY);
    }
}
