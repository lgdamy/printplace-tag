package com.damytec.printplacetag.view;

import com.damytec.printplacetag.enums.Folha;
import com.damytec.printplacetag.enums.Formato;
import com.damytec.printplacetag.enums.Orientacao;
import com.damytec.printplacetag.enums.Tema;
import com.damytec.printplacetag.pojo.ResultadoCalculo;
import com.damytec.printplacetag.pojo.ShowOptions;
import com.damytec.printplacetag.service.PrintplacetagService;
import com.damytec.printplacetag.ui.BaseWindow;
import com.damytec.printplacetag.ui.CustomButton;
import com.damytec.printplacetag.util.ImageUtil;
import com.damytec.printplacetag.util.KeyboardUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lgdamy on 25/01/2021
 */
public class PrintplacetagPanel implements BaseWindow.ContentForm {
    private JPanel root;
    private JTextField larguraField;
    private JTextField alturaField;
    private JComboBox<Folha> folhaCombo;
    private JTextField margemField;
    private JTextField espacoField;
    private JButton calcularButton;
    private JButton limparButton;
    private JLabel pageImage;
    private JPanel lowerPanel;
    private JSplitPane vSplit;
    private JLabel resultLabel;
    private JCheckBox showTags;
    private JCheckBox showCorte;
    private JCheckBox showFolha;
    private JCheckBox showMargem;
    private JPanel customButtonHub;
    private JPanel menusPanel;
    private JPanel resultPanel;
    private JTextPane resultText;
    private JSplitPane hSplit;

    private CustomButton retratoBtn;
    private CustomButton paisagemBtn;
    private CustomButton retoBtn;
    private CustomButton ovalBtn;
    private CustomButton disneyBtn;
    private CustomButton temaBtn;


    private ImageIcon ok;
    private ImageIcon nok;

    private Orientacao orientacao = Orientacao.RETRATO;
    private Formato formato = Formato.RETO;
    private Tema tema = Tema.YELLOW_BLACK;

    private static final Map<Orientacao, CustomButton> orientacaoMap = new HashMap<>();
    private static final Map<Formato, CustomButton> formatoMap = new HashMap<>();

    private static final String DEFAULT_LARGURA = "40";
    private static final String DEFAULT_ALTURA = "15";
    private static final String DEFAULT_MARGEM = "7";
    private static final String DEFAULT_ESPACO = "0";

    private PrintplacetagService service;

    public PrintplacetagPanel() {
        icones();
        service = PrintplacetagService.getInstance();
        EnumSet.allOf(Folha.class).stream().forEach(folhaCombo::addItem);
        defaults(null);
        acoes();
        customButtonMaps();
        customButtonShow();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this.dispatcher());
        hSplit.setResizeWeight(0.3);
    }

    private void calcular(Object e) {
        try {
            ResultadoCalculo res = service.calcularQuantidade(
                    Integer.parseInt(margemField.getText()),
                    Integer.parseInt(espacoField.getText()),
                    Integer.parseInt(larguraField.getText()),
                    Integer.parseInt(alturaField.getText()),
                    (Folha) folhaCombo.getSelectedItem(),
                    orientacao,
                    formato,
                    new ShowOptions(showTags.isSelected(), showFolha.isSelected(), showCorte.isSelected(), showMargem.isSelected(), tema));

            pageImage.setIcon(new ImageIcon(ImageUtil.resize(res.getImg(), lowerPanel.getHeight() - 10, lowerPanel.getWidth() - 10)));
            resultText.setText(res.getTpf().toHtml());

            this.resultColor(res.getTpf().isLimiar() ? Color.PINK : UIManager.getColor("Panel.background"));
            resultLabel.setIcon(res.isBest() ? ok : nok);
            customButtonShow();
        } catch (Exception ex) {
            ex.printStackTrace();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private void acoes() {
        calcularButton.addActionListener(this::calcular);
        limparButton.addActionListener(this::defaults);
        showTags.addActionListener(this::calcular);
        showMargem.addActionListener(this::calcular);
        showFolha.addActionListener(this::calcular);
        showCorte.addActionListener(this::calcular);
        folhaCombo.addActionListener(this::calcular);
        paisagemBtn = new CustomButton("images/paisagem.png") {
            @Override
            public void actionPerformed() {
                toggleOrientacao();
            }
        };
        paisagemBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        retratoBtn = new CustomButton("images/retrato.png") {
            @Override
            public void actionPerformed() {
                toggleOrientacao();
            }
        };
        retoBtn = new CustomButton("images/reto.png") {
            @Override
            public void actionPerformed() {
                toggleFormato();
            }
        };
        ovalBtn = new CustomButton("images/oval.png") {
            @Override
            public void actionPerformed() {
                toggleFormato();
            }
        };
        disneyBtn = new CustomButton("images/disney.png") {
            @Override
            public void actionPerformed() {
                toggleFormato();
            }
        };

        temaBtn = new CustomButton("images/cmyk.png") {
            @Override
            public void actionPerformed() {
                toggleTema();
            }
        };
    }

    private void toggleFormato() {
        formato = formato.next();
        calcular(null);
    }

    private void toggleTema() {
        tema = tema.next();
        calcular(null);
    }

    private void toggleOrientacao() {
        orientacao = orientacao == Orientacao.RETRATO ? Orientacao.PAISAGEM : Orientacao.RETRATO;
        calcular(null);
    }

    private void icones() {
        this.ok = new ImageIcon(PrintplacetagPanel.class.getClassLoader().getResource("images/ok.gif"));
        this.nok = new ImageIcon(PrintplacetagPanel.class.getClassLoader().getResource("images/nok.png"));
    }


    private KeyEventDispatcher dispatcher() {
        return new KeyboardUtil() {
            @Override
            public void enter() {
                calcular(null);
            }
        }.shortcuts();
    }

    private void customButtonMaps() {
        orientacaoMap.put(Orientacao.RETRATO, retratoBtn);
        orientacaoMap.put(Orientacao.PAISAGEM, paisagemBtn);
        formatoMap.put(Formato.RETO, retoBtn);
        formatoMap.put(Formato.OVAL, ovalBtn);
        formatoMap.put(Formato.DISNEY, disneyBtn);
    }

    private void defaults(ActionEvent e) {
        this.alturaField.setText(DEFAULT_ALTURA);
        this.larguraField.setText(DEFAULT_LARGURA);
        this.margemField.setText(DEFAULT_MARGEM);
        this.espacoField.setText(DEFAULT_ESPACO);
        this.folhaCombo.setSelectedItem(Folha.A4);
    }

    private void customButtonShow() {
        customButtonHub.removeAll();
        customButtonHub.add(orientacaoMap.get(orientacao));
        customButtonHub.add(formatoMap.get(formato));
        customButtonHub.add(temaBtn);
        customButtonHub.updateUI();
    }

    private void resultColor(Color c) {
        this.customButtonHub.setBackground(c);
        this.resultText.setBackground(c);
        this.resultPanel.setBackground(c);
    }

    @Override
    public JPanel root() {
        return this.root;
    }

    @Override
    public String title() {
        return PrintplacetagPanel.class.getPackage().getImplementationTitle() + " - " + PrintplacetagPanel.class.getPackage().getImplementationVersion();
    }

    private void createUIComponents() {
        customButtonHub  = new JPanel();
        customButtonHub.setLayout(new BoxLayout(customButtonHub,BoxLayout.Y_AXIS));
//        customButtonHub.setBackground(Color.WHITE);
    }


}
