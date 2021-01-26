package com.damytec.printplacetag.pojo;

/**
 * @author lgdamy@raiadrogasil.com on 26/01/2021
 */
public class ShowOptions {

    private boolean tags;
    private boolean folha;
    private boolean corte;
    private boolean margem;

    public ShowOptions(boolean tags, boolean folha, boolean corte, boolean margem) {
        this.tags = tags;
        this.folha = folha;
        this.corte = corte;
        this.margem = margem;
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
}
