package com.damytec.printplacetag.pojo;

/**
 * @author lgdamy@raiadrogasil.com on 26/01/2021
 */
public class TagsPorFolha {
    private int qtdX;
    private int qtdY;
    boolean limiar;

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

    public boolean isLimiar() {
        return limiar;
    }

    public void setLimiar(boolean limiar) {
        this.limiar = limiar;
    }

    public String toHtml() {
        StringBuilder sb = new StringBuilder("<html>");
        sb.append("<p style=\"text-align\":center;\"margin-top\":0><b>" + getTotal() + " unidades por folha</b></span>");
        sb.append("<p style=\"text-align\":center;>" +qtdX + " x " + qtdY);
        if (limiar) {
            sb.append("<br/>Margem atingida<br/>");
        }
        sb.append("</p></body></html>");
        return sb.toString();
    }
}
