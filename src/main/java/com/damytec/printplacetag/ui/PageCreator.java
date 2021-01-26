package com.damytec.printplacetag.ui;

import com.damytec.printplacetag.pojo.TagsPorFolha;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author lgdamy@raiadrogasil.com on 26/01/2021
 */
public class PageCreator {

    private static final int DPI = 2;
    private static final Color ROSA = new Color(255, 165, 165);
    private static final Color VERDE = new Color(121, 255, 120);

    private static PageCreator INSTANCE;

    public PageCreator() {}

    public static PageCreator getInstance() {
        return INSTANCE = INSTANCE == null ? new PageCreator() : INSTANCE;
    }

    public Image createPage (int hPage, int wPage, int hTag, int wTag, int space, TagsPorFolha qty) {
        hPage = hPage * DPI;
        wPage = wPage * DPI;
        hTag = hTag * DPI;
        wTag = wTag * DPI;
        space = space * DPI;
        BufferedImage bi = new BufferedImage(wPage, hPage, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setPaint(ROSA);
        g2d.fillRect(0,0, wPage, hPage);
        float dash[] = {2f};
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND,BasicStroke.CAP_BUTT, 10f, dash, 0 ));
        int x,y;
        y = (hPage - (qty.getQtdY() * hTag) - (space * (qty.getQtdY() - 1)))/2;;
        for (int i = 0; i < qty.getQtdY(); i++) {
            x = (wPage - (qty.getQtdX() * wTag) - (space * (qty.getQtdX() - 1)))/2;
            for (int j = 0; j < qty.getQtdX(); j++) {
                g2d.setPaint(VERDE);
                g2d.fillRect(x,y,wTag, hTag);
                if (space < 1) {
                    g2d.setPaint(Color.BLACK);
                    g2d.drawRect(x, y, wTag, hTag);
                    g2d.setColor(VERDE);
                }
                x = x+ wTag + space;
            }
            y = y+ hTag + space;
        }
        g2d.dispose();
        return bi;
    }
}
