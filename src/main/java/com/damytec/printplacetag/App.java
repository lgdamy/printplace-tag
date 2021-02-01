package com.damytec.printplacetag;

import com.damytec.printplacetag.ui.BaseWindow;
import com.damytec.printplacetag.view.PrintplacetagPanel;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * @author lgdamy@ on 22/01/2021
 */
public class App {

    public static void main(String[] args) {
        FlatLightLaf.install();
        new BaseWindow(new PrintplacetagPanel(), 900,600);
    }
}
