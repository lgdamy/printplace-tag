package com.damytec.printplacetag.service;

import com.damytec.printplacetag.enums.Folha;
import com.damytec.printplacetag.enums.Formato;
import com.damytec.printplacetag.enums.Orientacao;
import com.damytec.printplacetag.pojo.ResultadoCalculo;
import com.damytec.printplacetag.pojo.ShowOptions;
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
                                               Orientacao orientacao,
                                               Formato formato,
                                               ShowOptions options
    ) {
        if (margem < 0 || espaco < 0 || largura <= 0 || altura <= 0) {
            throw new IllegalArgumentException();
        }
        int larguraFolha = orientacao == Orientacao.PAISAGEM ? folha.getAltura() : folha.getLargura();
        int alturaFolha = orientacao == Orientacao.PAISAGEM ? folha.getLargura() : folha.getAltura();
        TagsPorFolha original = calcular(margem, espaco, largura, altura,larguraFolha, alturaFolha);
        Image image = PageCreator.getInstance().createPage(alturaFolha,larguraFolha,altura,largura,espaco, margem, formato, original, options);
        TagsPorFolha alternativo = calcular(margem,espaco,largura,altura,alturaFolha,larguraFolha);

        return new ResultadoCalculo(image, original, original.getTotal() >= alternativo.getTotal());
    }

    private TagsPorFolha calcular(int margem,
                                  int espaco,
                                  int largura,
                                  int altura,
                                  int larguraFolha,
                                  int alturaFolha) {
        TagsPorFolha tpf = new TagsPorFolha();
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
        return tpf;
    }


}
