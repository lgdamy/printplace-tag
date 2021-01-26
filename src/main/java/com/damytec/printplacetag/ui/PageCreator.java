package com.damytec.printplacetag.ui;

import com.damytec.printplacetag.pojo.ShowOptions;
import com.damytec.printplacetag.pojo.TagsPorFolha;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author lgdamy@raiadrogasil.com on 26/01/2021
 */
public class PageCreator {

    private static final int DPI = 2;

    private static PageCreator INSTANCE;

    public PageCreator() {}

    public static PageCreator getInstance() {
        return INSTANCE = INSTANCE == null ? new PageCreator() : INSTANCE;
    }

    public Image createPage (int hPage, int wPage, int hTag, int wTag, int space, int margin, TagsPorFolha qty, ShowOptions opts) {
        hPage = hPage * DPI;
        wPage = wPage * DPI;
        hTag = hTag * DPI;
        wTag = wTag * DPI;
        space = space * DPI;
        BufferedImage bi = new BufferedImage(wPage, hPage, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setColor(new Color(0,0,0,0));
        g2d.drawRect(0,0,wPage,hPage);

        if (opts.showFolha()) {
            page(g2d, hPage, wPage);
        }
        if (opts.showTags()) {
            tags(g2d, hPage, wPage, hTag, wTag, space, qty);
        }
        if (opts.showCorte()) {
            strokes(g2d, hPage, wPage, hTag, wTag, space, qty);
        }
        if (opts.showMargem()) {
            margin(g2d, hPage, wPage, margin);
        }

        g2d.dispose();
        return bi;
    }

    private void strokes(Graphics2D g2d, int hPage, int wPage, int hTag, int wTag, int space, TagsPorFolha qty) {
        if (qty.getQtdX() == 0 || qty.getQtdY() == 0) {
            return;
        }
        float dash[] = {1f,4f};
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_SQUARE,BasicStroke.CAP_BUTT, 10f, dash, 0 ));
        g2d.setPaint(Color.BLACK);
        int x,y;
        y = (hPage - (qty.getQtdY() * hTag) - (space * (qty.getQtdY() - 1)))/2;
        for (int i = 0; i < qty.getQtdY(); i++) {
            x = (wPage - (qty.getQtdX() * wTag) - (space * (qty.getQtdX() - 1)))/2;
            for (int j = 0; j < qty.getQtdX(); j++) {
                g2d.drawLine(x , 0, x , hPage );
                g2d.drawLine(x + wTag, 0, x + wTag, hPage );
                x = x+ wTag + space;
            }
            g2d.drawLine(0, y, wPage , y);
            g2d.drawLine(0, y + hTag, wPage , y + hTag);
            y = y+ hTag + space;
        }
    }

    private void tags(Graphics2D g2d, int hPage, int wPage, int hTag, int wTag, int space, TagsPorFolha qty) {
        if (qty.getQtdX() == 0 || qty.getQtdY() == 0) {
            return;
        }
        g2d.setPaint(Color.CYAN);
        int x,y;
        y = (hPage - (qty.getQtdY() * hTag) - (space * (qty.getQtdY() - 1)))/2;
        for (int i = 0; i < qty.getQtdY(); i++) {
            x = (wPage - (qty.getQtdX() * wTag) - (space * (qty.getQtdX() - 1)))/2;
            for (int j = 0; j < qty.getQtdX(); j++) {
                g2d.fillRect(x,y,wTag, hTag);
                x = x+ wTag + space;
            }
            y = y+ hTag + space;
        }
    }

    private void page(Graphics2D g2d, int hPage, int wPage) {
        g2d.setPaint(Color.PINK);
        g2d.fillRect(0,0, wPage, hPage);
    }

    private void margin(Graphics2D g2d, int hPage, int wPage, int margin ) {
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, wPage, margin);
        g2d.fillRect(0,0, margin, hPage);
        g2d.fillRect(wPage - margin, 0, margin, hPage);
        g2d.fillRect(0, hPage - margin, wPage, margin);
    }

}
