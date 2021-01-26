package com.damytec.printplacetag.service;

import com.damytec.printplacetag.enums.Folha;
import com.damytec.printplacetag.enums.Orientacao;
import com.damytec.printplacetag.pojo.TagsPorFolha;

/**
 * @author lgdamy on 25/01/2021
 */
public class PrintplacetagService {
    private static PrintplacetagService INSTANCE;

    private PrintplacetagService() {}

    public static PrintplacetagService getInstance() {
        return INSTANCE = INSTANCE == null ? new PrintplacetagService() : INSTANCE;
    }

    public TagsPorFolha calcularQuantidade(int margem,
                                           int espaco,
                                           int largura,
                                           int altura,
                                           Folha folha,
                                           Orientacao orientacao) {
        TagsPorFolha tpf = new TagsPorFolha();
        int larguraFolha = orientacao == Orientacao.PAISAGEM ? folha.getAltura() : folha.getLargura();
        int alturaFolha = orientacao == Orientacao.PAISAGEM ? folha.getLargura() : folha.getAltura();
        tpf.setQtdX((larguraFolha - (margem * 2) - espaco) / largura);
        tpf.setQtdY((alturaFolha - (margem * 2) - espaco)/altura);
        return tpf;

    }

}
