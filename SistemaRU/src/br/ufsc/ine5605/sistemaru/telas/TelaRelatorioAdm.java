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
    private JButton buttonRelatorio;
    private JButton buttonVoltar;
    private GerenciadorBotoes gerenciadorBotoes;
    

    public TelaRelatorioAdm() {

    }
    
    @Override
    public void mostraConteudoTela() {
        
                      
        //Container container = super.getPanel();
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
        
        labelDataInicial = new JLabel();
        labelDataInicial.setText("Data Inicial: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
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
        
        //RELATÓRIO
        buttonRelatorio = new JButton();
        buttonRelatorio.setText("Gerar Relatório");
        buttonRelatorio.addActionListener(gerenciadorBotoes);
        buttonRelatorio.setPreferredSize(new Dimension(80, 40));
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
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
        clear();
        System.out.println("##################################");
        System.out.println("######  MENU RELATORIO ADM  ######");
        System.out.println("##################################");
        System.out.println("");
        System.out.println("FORMATO DE ENTRADA DAS DATAS (DD/MM/AAAA)");
        System.out.print("DIGITE DA INICIAL: ");
        ControladorRelatorioAdm.getInstance().getConteudoTelaRelatorioAdm().dataInicial = leString();
        System.out.print("DIGITE DA FINAL: ");
        ControladorRelatorioAdm.getInstance().getConteudoTelaRelatorioAdm().dataFinal = leString();
        
        ControladorRelatorioAdm.getInstance().relatorioRefeicao();    */  
    }
    
    public void mostraRelatorio(String dataInicial, String dataFinal, int count){
        System.out.println("");
        System.out.println("#############################");
        System.out.println("######  RELATORIO ADM  ######");
        System.out.println("#############################");
        System.out.println("");
        System.out.println("DE "+dataInicial+" ATÉ "+ dataFinal + "\nFORAM REGISTRADOS " + count + " ACESSOS AO RESTAURANTE.");
    
    }
    
    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton botao = (JButton) ae.getSource();
            System.out.println("clicou: "+botao.getText());
            if(botao.equals(buttonRelatorio)){
                try{
                    getContentPane().removeAll();
                    escondeTela();
                    ControladorAdm.getInstance().chamaTelaAdmListar();
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
