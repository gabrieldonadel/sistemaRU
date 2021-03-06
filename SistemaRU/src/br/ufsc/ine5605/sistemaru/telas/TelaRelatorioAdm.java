/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.telas;

import br.ufsc.ine5605.sistemaru.controladores.ControladorAdm;
import br.ufsc.ine5605.sistemaru.controladores.ControladorPrincipal;
import br.ufsc.ine5605.sistemaru.controladores.ControladorRelatorioAdm;
import br.ufsc.ine5605.sistemaru.controladores.ControladorUsuario;
import br.ufsc.ine5605.sistemaru.exceptions.MatriculainvalidaException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Usuario
 */
public class TelaRelatorioAdm extends TelaPadrao{

    private JLabel labelTitulo;
    private JLabel labelDataInicial;
    private JFormattedTextField textFieldDataInicial;
    private JLabel labelDataFinal;
    private JFormattedTextField textFieldDataFinal;
    private JButton buttonRelatorio;
    private JButton buttonVoltar;
    private GerenciadorBotoes gerenciadorBotoes;
    

    public TelaRelatorioAdm() {
        this.gerenciadorBotoes = new GerenciadorBotoes();
    }
    
    @Override
    public void mostraConteudoTela() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        labelTitulo = new JLabel();
        labelTitulo.setText("Relatório RU");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        
        container.add(labelTitulo, gbc);
        //DATA INICIAL
        labelDataInicial = new JLabel();
        labelDataInicial.setText("Data Inicial: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        container.add(labelDataInicial, gbc);
        
        try{
            textFieldDataInicial = new JFormattedTextField(new MaskFormatter("##/##/####"));
        }catch(Exception e){System.out.println(e);}
        textFieldDataInicial.setValue(null);
        textFieldDataInicial.setColumns(6);
    
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        container.add(textFieldDataInicial, gbc);
        
        //DATA FINAL
        labelDataFinal = new JLabel();
        labelDataFinal.setText("Data Final: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        container.add(labelDataFinal, gbc);
        
        try{
            textFieldDataFinal = new JFormattedTextField(new MaskFormatter("##/##/####"));
        }catch(Exception e){System.out.println(e);}
        textFieldDataFinal.setValue(null);
        textFieldDataFinal.setColumns(6);
    
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        container.add(textFieldDataFinal, gbc);
        
        //RELATÓRIO
        buttonRelatorio = new JButton();
        buttonRelatorio.setText("Gerar Relatório");
        buttonRelatorio.addActionListener(gerenciadorBotoes);
        buttonRelatorio.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth =2;
        container.add(buttonRelatorio, gbc);
        
        
        //VOLTAR
        buttonVoltar = new JButton();
        buttonVoltar.setText("Voltar");
        buttonVoltar.addActionListener(gerenciadorBotoes);
        buttonVoltar.setPreferredSize(new Dimension(80, 40));
        gbc.gridx = 2;
        container.add(buttonVoltar, gbc);
        
        setSize(new Dimension(600, 400));
        mostraTela();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void mostraRelatorio(String dataInicial, String dataFinal, int count){
        String s = "DE "+dataInicial+" ATÉ "+ dataFinal + "\nFORAM REGISTRADOS " + count + " ACESSOS AO RESTAURANTE.";
        JOptionPane.showMessageDialog(null, s);
    }
    
    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton botao = (JButton) ae.getSource();
            if(botao.equals(buttonRelatorio)){
                try{
                    ControladorRelatorioAdm.getInstance().getConteudoTelaRelatorioAdm().dataInicial = textFieldDataInicial.getText();
                    ControladorRelatorioAdm.getInstance().getConteudoTelaRelatorioAdm().dataFinal = textFieldDataFinal.getText();
                    ControladorRelatorioAdm.getInstance().relatorioRefeicao();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.out.println(e);
                }
            }else if(botao.equals(buttonVoltar)){
                try{
                    getContentPane().removeAll();
                    escondeTela();
                    ControladorUsuario.getInstance().chamaTelaUsuario();
                    
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.out.println(e);
                }
            }   
        }
        
    }
    
}
