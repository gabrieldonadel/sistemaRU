/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.telas;


import br.ufsc.ine5605.sistemaru.controladores.ControladorPrincipal;
import br.ufsc.ine5605.sistemaru.exceptions.MatriculainvalidaException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class TelaPrincipal extends TelaPadrao{
    
    private JLabel labelTitulo;
    private JLabel labelLogin;
    private JFormattedTextField textFieldLogin;
    private JButton buttonEntrar;

    public TelaPrincipal() {
        
    }

    
    @Override
    public void mostraConteudoTela() {
              
        //Container container = super.getPanel();
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        labelTitulo = new JLabel();
        labelTitulo.setText("SISTEMA DE CONTROLE DO RESTAURANTE UNIVERSITÁRIO");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        
        container.add(labelTitulo, gbc);
        
        labelLogin = new JLabel();
        labelLogin.setText("Matrícula: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        container.add(labelLogin, gbc);
        
        
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
        
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        textFieldLogin = new JFormattedTextField(formatter);
        textFieldLogin.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
    
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        container.add(textFieldLogin, gbc);
        
        buttonEntrar = new JButton();
        buttonEntrar.setText("Entrar");
        buttonEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(!textFieldLogin.getText().equals("")){
                    ConteudoTelaPrincipal conteudoTelaPrincipal = new ConteudoTelaPrincipal((int)textFieldLogin.getValue());
                    try{
                        ControladorPrincipal.getInstance().validaLogin(conteudoTelaPrincipal);
                        escondeTela();
                    }catch(MatriculainvalidaException ex){
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Preencha o campo com sua matricula");
                }
            }
        });
        buttonEntrar.setPreferredSize(new Dimension(80, 40));
        buttonEntrar.setBackground(Color.CYAN);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth =2;
        
        
        container.add(buttonEntrar, gbc);
        
      
        
        setSize(new Dimension(600, 400));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        
        
    }    
    
    @Override
    public void escondeTela(){
        limpaLogin();
        setVisible(false);
    }
    public void limpaLogin(){
        textFieldLogin.setText("");
    
    }
    
}
