package com.damytec.printplacetag.service;

import com.damytec.printplacetag.enums.Folha;
import com.damytec.printplacetag.enums.Orientacao;
import com.damytec.printplacetag.pojo.ResultadoCalculo;
import com.damytec.printplacetag.pojo.TagsPorFolha;
import com.damytec.printplacetag.ui.PageCreator;

import java.awt.*;

/**
 * @author lgdamy on 25/01/2021
 */
public class PrintplacetagService {
    private static PrintplacetagService INSTANCE;

    private PrintplacetagService() {}

    public static PrintplacetagService getInstance() {
        return INSTANCE = INSTANCE == null ? new PrintplacetagService() : INSTANCE;
    }

    public ResultadoCalculo calcularQuantidade(int margem,
                                               int espaco,
                                               int largura,
                                               int altura,
                                               Folha folha,
                                               Orientacao orientacao) {
        TagsPorFolha tpf = new TagsPorFolha();
        if (margem < 0 || espaco < 0 || largura <= 0 || altura <= 0) {
            throw new IllegalArgumentException();
        }
        int larguraFolha = orientacao == Orientacao.PAISAGEM ? folha.getAltura() : folha.getLargura();
        int alturaFolha = orientacao == Orientacao.PAISAGEM ? folha.getLargura() : folha.getAltura();
        int mm = margem;
        int i = 0;
        while (mm + largura + margem < larguraFolha) {
            i++;
            mm = mm + largura + espaco;
        }
        mm = margem;
        tpf.setQtdX(i);
        i=0;
        while (mm + altura + margem < alturaFolha) {
            i++;
            mm = mm + altura + espaco;
        }
        tpf.setQtdY(i);

        Image image = PageCreator.getInstance().createPage(alturaFolha,larguraFolha,altura,largura,espaco,tpf);

        return new ResultadoCalculo(image, tpf);
    }
}
