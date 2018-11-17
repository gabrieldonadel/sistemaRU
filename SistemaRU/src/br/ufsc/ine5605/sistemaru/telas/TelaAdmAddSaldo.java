/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.telas;

import br.ufsc.ine5605.sistemaru.controladores.ControladorAdm;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Usuario
 */
public class TelaAdmAddSaldo extends TelaPadrao{
    private GerenciadorBotoes gerenciadorBotoes;
    private JButton buttonVoltar;
    private JLabel labelTitulo;
    private JLabel labelMatricula;
    private JFormattedTextField textFieldMatricula;
    private JButton buttonAddSaldo;
    private JFormattedTextField valor;  
    
     public TelaAdmAddSaldo(){
        this.gerenciadorBotoes = new GerenciadorBotoes();
    }
    @Override
    public void mostraConteudoTela() {
        getContentPane().removeAll();
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //TITULO
        labelTitulo = new JLabel();
        labelTitulo.setText("ADICIONAR SALDO");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        container.add(labelTitulo, gbc);
        
        //MATRICULA
        labelMatricula = new JLabel();
        labelMatricula.setText("Matrícula: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(labelMatricula, gbc);
        
        //CAMPO MATRICULA        
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format) {
            public Object stringToValue(String string)
                throws ParseException {
                if (string == null || string.length() == 0) {
                    return null;
                }
                return super.stringToValue(string);
            }
        };
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        textFieldMatricula = new JFormattedTextField(formatter);
        textFieldMatricula.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        container.add(textFieldMatricula, gbc);
        
        //SALDO
        labelMatricula = new JLabel();
        labelMatricula.setText("Valor: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        container.add(labelMatricula, gbc);
        
        //CAMPO SALDO
        valor = new JFormattedTextField();
        valor.setFormatterFactory(new AbstractFormatterFactory() {
            @Override
            public AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
                format.setRoundingMode(RoundingMode.HALF_UP);
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setMinimum(0.0);
                formatter.setMaximum(999999.00);
                return formatter;
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        container.add(valor, gbc);
        
        //BOTAO ADICIONAR SALDO 
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        buttonAddSaldo = new JButton("Adicionar Saldo");
        buttonAddSaldo.addActionListener(gerenciadorBotoes);
        buttonAddSaldo.setPreferredSize(new Dimension(200, 50));
        container.add(buttonAddSaldo, gbc);
        
        //BOTAO VOLTAR 
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.addActionListener(gerenciadorBotoes);
        buttonVoltar.setPreferredSize(new Dimension(100, 50));
        container.add(buttonVoltar, gbc);
        
        setSize(new Dimension(600, 400));
        mostraTela();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private class GerenciadorBotoes implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton botao = (JButton) ae.getSource();
            TelaPadrao telaExcluir = ControladorAdm.getInstance().getTelaAdmExcluir();
            System.out.println("clicou: "+botao.getText());
            if(botao.equals(buttonVoltar)){
                try{
                    getContentPane().removeAll();
                    escondeTela();
                    ControladorAdm.getInstance().chamaTelaAdmListar();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.out.println(e);
                }
            }
            if(botao.equals(buttonAddSaldo)){
                try{
                    ControladorAdm.getInstance().adicionarSaldo(new ConteudoTelaAdm((int)textFieldMatricula.getValue(),Float.parseFloat(valor.getValue().toString())));
                    ControladorAdm.getInstance().chamaTelaAdm();
                    JOptionPane.showMessageDialog(null, "Saldo adicionado com sucesso!");
                    getContentPane().removeAll();
                    escondeTela();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.out.println(e);                    
                }
            }
        }
    }
}
