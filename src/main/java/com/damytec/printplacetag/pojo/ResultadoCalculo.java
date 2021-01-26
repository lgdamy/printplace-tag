package com.damytec.printplacetag.pojo;

import java.awt.*;

/**
 * @author lgdamy@raiadrogasil.com on 26/01/2021
 */
public class ResultadoCalculo {

    private Image img;

    private TagsPorFolha tpf;

    public ResultadoCalculo(Image img, TagsPorFolha tpf) {
        this.img = img;
        this.tpf = tpf;
    }

    public Image getImg() {
        return img;
    }

    public TagsPorFolha getTpf() {
        return tpf;
    }
}
