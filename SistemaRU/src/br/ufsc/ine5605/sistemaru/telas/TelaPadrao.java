/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.telas;

import br.ufsc.ine5605.sistemaru.exceptions.InputInvalidoException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 12041789921
 */
public abstract class TelaPadrao extends JFrame{
    
    private Scanner teclado;
    private JLabel labelDiaHoje;
    private static String data;
    private JPanel panel;

    

    public TelaPadrao() {
        super("SISTEMA DE CONTROLE DO RESTAURANTE UNIVERSITÁRIO");
        this.teclado = new Scanner(System.in);
        labelDiaHoje = new JLabel(data);
        super.getContentPane().setLayout(new BorderLayout());
        
        panel = new JPanel();
        
        super.getContentPane().add(labelDiaHoje, BorderLayout.SOUTH);
        super.getContentPane().add(panel, BorderLayout.CENTER);
        setSize(600,400);
        
    }
    
    public JPanel getPanel() {
        return panel;
    }
    
    
    public JLabel getLabelDiaHoje() {
        labelDiaHoje = new JLabel(data);
        return labelDiaHoje;
    }
    
    public int leInteiro() throws InputInvalidoException{
        String i = teclado.nextLine();
        try{
            int x = Integer.parseInt(i);
            return x;
        }catch(NumberFormatException e){
            throw new InputInvalidoException();
        }
    }
    
    public float leFloat() {
        String i = teclado.nextLine();
        try{
            float x = Float.valueOf(i);
            return x;
        }catch(NumberFormatException e){
            System.out.println("ENTRADA INVÁLIDA, DIGITE APENAS NÚMEROS");
        }
        return 0;
    }
    
    public String leString() {
        return teclado.nextLine();
    }
    public boolean leBoolean() {
        return teclado.nextBoolean();
    }
     
    public abstract void mostraConteudoTela();
    
    public void operacaoRealizada(){
        System.out.println("");
        System.out.println("-> OPERAÇÃO REALIZADA COM SUCESSO");
        System.out.println("");
    }
    
    public void mostraTela(){
        labelDiaHoje.setText(data);
        setVisible(true);
    }
    public void escondeTela(){
        setVisible(false);
    }
    
    public void clear(){
        for (int i=0; i<=40;i++){
            System.out.println("");
        }
    }
    
    public static void setData(String dataRecebida){
        data = dataRecebida;
    }
    
    public static String getData(){
        return data;
    }
    
    @Override
    public Container getContentPane(){
        return panel;
    }
}
