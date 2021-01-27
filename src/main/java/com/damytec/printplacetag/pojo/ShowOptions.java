package com.damytec.printplacetag.pojo;

import com.damytec.printplacetag.enums.Tema;

/**
 * @author lgdamy@raiadrogasil.com on 26/01/2021
 */
public class ShowOptions {

    private boolean tags;
    private boolean folha;
    private boolean corte;
    private boolean margem;
    private Tema tema;

    public ShowOptions(boolean tags, boolean folha, boolean corte, boolean margem, Tema tema) {
        this.tags = tags;
        this.folha = folha;
        this.corte = corte;
        this.margem = margem;
        this.tema = tema;
    }

    public boolean showTags() {
        return tags;
    }

    public boolean showFolha() {
        return folha;
    }

    public boolean showCorte() {
        return corte;
    }

    public boolean showMargem() {
        return margem;
    }

    public Tema getTema() {
        return tema;
    }
}
