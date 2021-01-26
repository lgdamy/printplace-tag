package com.damytec.printplacetag.view;

import com.damytec.printplacetag.enums.Folha;
import com.damytec.printplacetag.enums.Orientacao;
import com.damytec.printplacetag.pojo.ResultadoCalculo;
import com.damytec.printplacetag.service.PrintplacetagService;
import com.damytec.printplacetag.ui.BaseWindow;
import com.damytec.printplacetag.util.ImageUtil;
import com.damytec.printplacetag.util.KeyboardUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EnumSet;

/**
 * @author lgdamy on 25/01/2021
 */
public class PrintplacetagPanel implements BaseWindow.ContentForm {
    private JPanel root;
    private JTextField larguraField;
    private JTextField alturaField;
    private JComboBox folhaCombo;
    private JTextField margemField;
    private JTextField espacoField;
    private JButton calcularButton;
    private JButton limparButton;
    private JRadioButton retratoRadio;
    private JRadioButton paisagemRadio;
    private JPanel spacer;
    private JLabel pageImage;
    private JPanel imagePanel;
    private JSplitPane splitPane;
    private JLabel resultLabel;

    private static final String DEFAULT_LARGURA = "40";
    private static final String DEFAULT_ALTURA = "15";
    private static final String DEFAULT_MARGEM = "7";
    private static final String DEFAULT_ESPACO = "0";

    private PrintplacetagService service;

    public PrintplacetagPanel() {
        EnumSet.allOf(Folha.class).forEach(folhaCombo::addItem);
        defaults(null);
        service = PrintplacetagService.getInstance();
        retratoRadio.addActionListener(this::togglePaisagem);
        paisagemRadio.addActionListener(this::toggleRetrato);
        calcularButton.addActionListener(this::calcular);
        limparButton.addActionListener(this::defaults);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this.dispatcher());
    }

    private void calcular(ActionEvent e) {
        try {
            ResultadoCalculo res = service.calcularQuantidade(
                    Integer.parseInt(margemField.getText()),
                    Integer.parseInt(espacoField.getText()),
                    Integer.parseInt(larguraField.getText()),
                    Integer.parseInt(alturaField.getText()),
                    (Folha) folhaCombo.getSelectedItem(),
                    retratoRadio.isSelected() ? Orientacao.RETRATO : Orientacao.PAISAGEM);

            pageImage.setIcon(new ImageIcon(ImageUtil.resize(res.getImg(),imagePanel.getHeight() - 10,imagePanel.getWidth() -10)));
            resultLabel.setText(res.getTpf().toString());
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private void togglePaisagem(ActionEvent e) {
        paisagemRadio.setSelected(!paisagemRadio.isSelected());
    }

    private void toggleRetrato(ActionEvent e) {
        retratoRadio.setSelected(!retratoRadio.isSelected());
    }

    private KeyEventDispatcher dispatcher() {
        return new KeyboardUtil(){
                    @Override
                    public void enter() {
                        calcular(null);
                    }
                }.shortcuts();
    }

    private void defaults(ActionEvent e) {
        this.retratoRadio.setSelected(true);
        this.paisagemRadio.setSelected(false);
        this.alturaField.setText(DEFAULT_ALTURA);
        this.larguraField.setText(DEFAULT_LARGURA);
        this.margemField.setText(DEFAULT_MARGEM);
        this.espacoField.setText(DEFAULT_ESPACO);
        this.folhaCombo.setSelectedItem(Folha.A4);
    }

    @Override
    public JPanel root() {
        return this.root;
    }

//    Sobrescreva esse metodo apenas se sua janela vai mudar de titulo
//    @Override
//    public String title() {
//        return "Meu titulo especial";
//    }
}
