package com.damytec.printplacetag.pojo;

import java.awt.*;

/**
 * @author lgdamy@raiadrogasil.com on 26/01/2021
 */
public class ResultadoCalculo {

    private Image img;

    private TagsPorFolha tpf;

    private boolean best;

    public ResultadoCalculo(Image img, TagsPorFolha tpf, boolean best) {
        this.img = img;
        this.tpf = tpf;
        this.best = best;
    }

    public Image getImg() {
        return img;
    }

    public TagsPorFolha getTpf() {
        return tpf;
    }

    public boolean isBest() {
        return best;
    }
}
