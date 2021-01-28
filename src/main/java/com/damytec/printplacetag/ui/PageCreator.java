package com.damytec.printplacetag.ui;

import com.damytec.printplacetag.enums.Formato;
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

    public PageCreator() {
    }

    public static PageCreator getInstance() {
        return INSTANCE = INSTANCE == null ? new PageCreator() : INSTANCE;
    }

    public Image createPage(int hPage, int wPage, int hTag, int wTag, int space, int margin, Formato formato, TagsPorFolha qty, ShowOptions opts) {
        hPage = hPage * DPI;
        wPage = wPage * DPI;
        hTag = hTag * DPI;
        wTag = wTag * DPI;
        space = space * DPI;
        margin = margin * DPI;
        BufferedImage bi = new BufferedImage(wPage, hPage, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.drawRect(0, 0, wPage, hPage);

        if (opts.showFolha()) {
            g2d.setColor(opts.getTema().getPagina());
            page(g2d, hPage, wPage);
        }
        if (opts.showTags()) {
            g2d.setColor(opts.getTema().getTag());
            tags(g2d, hPage, wPage, hTag, wTag, space, formato, qty);
        }
        if (opts.showCorte()) {
            g2d.setColor(opts.getTema().getCorte());
            strokes(g2d, hPage, wPage, hTag, wTag, space, formato, qty);
        }
        if (opts.showMargem()) {
            g2d.setColor(opts.getTema().getMargem());
            margin(g2d, hPage, wPage, margin);
        }

        g2d.dispose();
        return bi;
    }

    private void strokes(Graphics2D g2d, int hPage, int wPage, int hTag, int wTag, int space, Formato formato, TagsPorFolha qty) {
        if (qty.getQtdX() == 0 || qty.getQtdY() == 0) {
            return;
        }
        int x, y;
        y = (hPage - (qty.getQtdY() * hTag) - (space * (qty.getQtdY() - 1))) / 2;
        for (int i = 0; i < qty.getQtdY(); i++) {
            x = (wPage - (qty.getQtdX() * wTag) - (space * (qty.getQtdX() - 1))) / 2;
            for (int j = 0; j < qty.getQtdX(); j++) {
                if (!formato.isDualBorders() && j < qty.getQtdX() - 1) {
                    g2d.drawLine(x + wTag + (space / 2), 0, x + wTag + (space / 2), hPage);
                }
                if (formato.isDualBorders()) {
                    g2d.drawLine(x, 0, x, hPage);
                    g2d.drawLine(x + wTag, 0, x + wTag, hPage);
                }
                x = x + wTag + space;
            }

            if (!formato.isDualBorders() && i < qty.getQtdY() - 1) {
                g2d.drawLine(0, y + hTag + (space / 2), wPage, y + hTag + (space / 2));
            }
            if (formato.isDualBorders()) {
                g2d.drawLine(0, y, wPage, y);
                g2d.drawLine(0, y + hTag, wPage, y + hTag);
            }
            y = y + hTag + space;
        }

    }

    private void tags(Graphics2D g2d, int hPage, int wPage, int hTag, int wTag, int space, Formato formato, TagsPorFolha qty) {
        if (qty.getQtdX() == 0 || qty.getQtdY() == 0) {
            return;
        }
        int x, y;
        y = (hPage - (qty.getQtdY() * hTag) - (space * (qty.getQtdY() - 1))) / 2;
        for (int i = 0; i < qty.getQtdY(); i++) {
            x = (wPage - (qty.getQtdX() * wTag) - (space * (qty.getQtdX() - 1))) / 2;
            for (int j = 0; j < qty.getQtdX(); j++) {
                if (formato == Formato.DISNEY) {
                    this.mickey(g2d, x, wTag, y, hTag);
                } else if (formato == Formato.OVAL) {
                    g2d.fillOval(x, y, wTag, hTag);
                } else if (formato == Formato.RETO) {
                    g2d.fillRect(x, y, wTag, hTag);
                }
                x = x + wTag + space;
            }
            y = y + hTag + space;
        }
    }

    private void page(Graphics2D g2d, int hPage, int wPage) {
        g2d.fillRect(0, 0, wPage, hPage);
    }

    private void margin(Graphics2D g2d, int hPage, int wPage, int margin) {
        g2d.fillRect(0, 0, wPage, margin);
        g2d.fillRect(0, 0, margin, hPage);
        g2d.fillRect(wPage - margin, 0, margin, hPage);
        g2d.fillRect(0, hPage - margin, wPage, margin);
    }

    private void mickey(Graphics2D g2d, int x, int wMickey, int y, int hMickey) {
        g2d.fillOval(x, y, 5 * wMickey / 12, 5 * hMickey / 12);
        g2d.fillOval(x + 7 * wMickey / 12, y, 5 * wMickey / 12, 5 * hMickey / 12);
        g2d.fillOval(x + wMickey / 10, y + 2 * hMickey / 10, 8 * wMickey / 10, 8 * hMickey / 10);
    }

}
