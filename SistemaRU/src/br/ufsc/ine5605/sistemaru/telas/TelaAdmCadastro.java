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
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author franca
 */
public class TelaAdmCadastro extends TelaPadrao{
    private JLabel labelIsencao;
    private JComboBox boxIsento;
    private JLabel labelAdm;
    private JLabel labelMatricula;
    private JFormattedTextField textFieldMatricula;
    private JLabel labelNome;
    private JLabel labelTitulo;
    private JFormattedTextField textFieldNome;
    private JButton buttonCadastrar;
    private GerenciadorBotoes gerenciadorBotoes;
    private JButton buttonVoltar;
    private JComboBox box;
    private JComboBox boxAdm;
    private Container container;
    private GridBagConstraints gbc;
    
    public TelaAdmCadastro(){
        this.gerenciadorBotoes = new GerenciadorBotoes();
    }
    @Override
    public void mostraConteudoTela() {
        getContentPane().removeAll();
        
        //CABEÇALHO    
        container = getContentPane();
        container.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        labelTitulo = new JLabel();
        labelTitulo.setText("Selecione o tipo de usuário:        ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(labelTitulo, gbc);
        String [] tiposDeCadastros = {"USUÁRIO UFSC", "ESTUDANTE", "VISITANTE"};
        box = new JComboBox(tiposDeCadastros);
        gbc.gridx = 1;
        gbc.gridy = 0;
        container.add(box,gbc);
        box.setSelectedIndex(0);
        box.addActionListener(gerenciadorBotoes);
        
        //BOTAO VOLTAR 
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weighty = -1;
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.addActionListener(gerenciadorBotoes);
        container.add(buttonVoltar, gbc);
        
        setSize(new Dimension(600, 400));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    public void AdicionaCamposUsuario(){
        removeComponentes();
        //CAMPO NOME       
        labelNome = new JLabel();
        labelNome.setText("Nome: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        container.add(labelNome, gbc);
        textFieldNome = new JFormattedTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        container.add(textFieldNome, gbc);
       
        //CAMPO MATRICULA
        labelMatricula = new JLabel();
        labelMatricula.setText("Matricula: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        container.add(labelMatricula, gbc);
        
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
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        container.add(textFieldMatricula, gbc);
        
        //CAMPO ADM
        labelAdm = new JLabel();
        labelAdm.setText("Administrador: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        container.add(labelAdm, gbc);
        String [] ehAdm = {"SIM", "NÃO"};
        boxAdm = new JComboBox(ehAdm);
        gbc.gridx = 1;
        gbc.gridy = 3;
        container.add(boxAdm,gbc);
        
        //BOTAO CADASTRAR
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weighty = -1;
        buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.addActionListener(gerenciadorBotoes);
        container.add(buttonCadastrar, gbc);
        
        setVisible(true);
    }
    public void AdicionaCamposEstudante(){
        removeComponentes();
        //CAMPO NOME       
        labelNome = new JLabel();
        labelNome.setText("Nome: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        container.add(labelNome, gbc);
        textFieldNome = new JFormattedTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        container.add(textFieldNome, gbc);
       
        //CAMPO MATRICULA
        labelMatricula = new JLabel();
        labelMatricula.setText("Matricula: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        container.add(labelMatricula, gbc);
        
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
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        container.add(textFieldMatricula, gbc);
        
        //CAMPO ADM
        labelAdm = new JLabel();
        labelAdm.setText("Administrador: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        container.add(labelAdm, gbc);
        
        String [] ehAdm = {"SIM", "NÃO"};
        boxAdm = new JComboBox(ehAdm);
        gbc.gridx = 1;
        gbc.gridy = 3;
        container.add(boxAdm,gbc);
        
        //CAMPO ISENCAO
        labelIsencao = new JLabel();
        labelIsencao.setText("Isento: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        container.add(labelIsencao, gbc);
        String [] ehIsento = {"SIM", "NÃO"};
        boxIsento = new JComboBox(ehIsento);
        gbc.gridx = 1;
        gbc.gridy = 4;
        container.add(boxIsento,gbc);
        
        //BOTAO CADASTRAR
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weighty = -1;
        buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.addActionListener(gerenciadorBotoes);
        container.add(buttonCadastrar, gbc);
        
        setVisible(true);
        
    }
    public void AdicionaCamposVisitante(){
        removeComponentes();
        //CAMPO NOME
        labelNome = new JLabel();
        labelNome.setText("Nome: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        container.add(labelNome, gbc);
        textFieldNome = new JFormattedTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        container.add(textFieldNome, gbc);
        
        //BOTAO CADASTRAR
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weighty = -1;
        buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.addActionListener(gerenciadorBotoes);
        container.add(buttonCadastrar, gbc);
        
        setVisible(true);
    }
    
    private void removeComponentes(){
        if (labelNome != null && labelNome.getParent() == getContentPane()){
            getContentPane().remove(labelNome);
            getContentPane().remove(textFieldNome);
        }
        if (labelMatricula != null && labelMatricula.getParent()== getContentPane()){
            getContentPane().remove(labelMatricula);
            getContentPane().remove(textFieldMatricula);
        }
        if (labelAdm != null && labelAdm.getParent() == getContentPane()){
            getContentPane().remove(labelAdm);
            getContentPane().remove(boxAdm);
        }
        if (labelIsencao != null && labelIsencao.getParent() == getContentPane()){
            getContentPane().remove(labelIsencao);
            getContentPane().remove(boxIsento);
        }
        if (buttonCadastrar != null && buttonCadastrar.getParent() == getContentPane()){
            getContentPane().remove(buttonCadastrar);
        }
    }
    private class GerenciadorBotoes implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            TelaPadrao telaCadastro = ControladorAdm.getInstance().getTelaAdmListar();
            if(ae.getSource() instanceof JButton){
                JButton botao = (JButton) ae.getSource();

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
            }else{
                JComboBox botaoBox = (JComboBox) ae.getSource();
                String tipo = (String)botaoBox.getSelectedItem();
                System.out.println("clicou: "+tipo);
                switch (tipo){
                    case "USUÁRIO UFSC": AdicionaCamposUsuario();
                        break;
                    case "VISITANTE": AdicionaCamposVisitante();
                        break;
                    case "ESTUDANTE": AdicionaCamposEstudante();
                        break;
                        
                }
            }
        }
    }
}
