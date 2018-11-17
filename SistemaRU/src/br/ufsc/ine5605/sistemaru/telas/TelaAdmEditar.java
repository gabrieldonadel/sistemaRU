/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.telas;

import br.ufsc.ine5605.sistemaru.controladores.ControladorAdm;
import br.ufsc.ine5605.sistemaru.entidades.Estudante;
import br.ufsc.ine5605.sistemaru.exceptions.MatriculaJahExisteException;
import br.ufsc.ine5605.sistemaru.entidades.Pessoa;
import br.ufsc.ine5605.sistemaru.entidades.UsuarioUFSC;
import br.ufsc.ine5605.sistemaru.entidades.Visitante;
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
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Doniss
 */
public class TelaAdmEditar extends TelaPadrao{
    
    private GerenciadorBotoes gerenciadorBotoes;
    private JButton buttonVoltar;
    private JButton buttonEditar;
    private JLabel labelNome;
    private JTextField textFieldNome;
    private JFormattedTextField formattedTextFieldMatricula;
    private JLabel labelMatricula;
    private JLabel labelAdmin;
    private JComboBox comboBoxAdmin;
    private JLabel labelIsento;
    private JComboBox comboBoxIsento;
    private Pessoa pessoa;
    
    public TelaAdmEditar(){
        this.gerenciadorBotoes = new GerenciadorBotoes(this);
    }
    
    public void mostraConteudoTela(Pessoa pessoa) {
        this.pessoa = pessoa;
        getContentPane().removeAll();
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        int linha = 0;

        //Nome
        labelNome = new JLabel();
        labelNome.setText("Nome:");
        gbc.gridx = 0;
        gbc.gridy = linha;
        linha++;
        container.add(labelNome, gbc);
        
        textFieldNome = new JTextField(pessoa.getNome());
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth=3;
        container.add(textFieldNome, gbc);
        int matricula;
        //Matricula
        labelMatricula = new JLabel();
        if(pessoa instanceof UsuarioUFSC){
            labelMatricula.setText("Matricula:");
            matricula = ((UsuarioUFSC)pessoa).getMatricula();
        }else{
            labelMatricula.setText("Id:");
            matricula = ((Visitante)pessoa).getId();
        }
        gbc.gridx = 0;
        gbc.gridy = linha;
        linha++;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
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
        formattedTextFieldMatricula = new JFormattedTextField(formatter);
        formattedTextFieldMatricula.setText(matricula+"");
        formattedTextFieldMatricula.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth=3;
        container.add(formattedTextFieldMatricula, gbc);
        //ADMIN
        if(pessoa instanceof UsuarioUFSC){
            labelIsento = new JLabel();
            labelIsento.setText("Admin:");
            gbc.gridx = 0;
            gbc.gridy = linha;
            linha++;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;

            container.add(labelIsento, gbc);

            String[] bool = {"Sim","Não"};
            comboBoxAdmin = new JComboBox(bool);
            int isAdm = (((UsuarioUFSC) pessoa).isAdmin()) ? 0 : 1;
            comboBoxAdmin.setSelectedIndex(isAdm);
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            container.add(comboBoxAdmin, gbc);
        }
        //ISENÇÃO
        if(pessoa instanceof Estudante){
            labelAdmin = new JLabel();
            labelAdmin.setText("Isento:");
            gbc.gridx = 0;
            gbc.gridy = linha;
            linha++;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;

            container.add(labelAdmin, gbc);

            String[] bool = {"Sim","Não"};
            comboBoxIsento = new JComboBox(bool);
            int isIsent = ((((Estudante) pessoa)).isIsencao()) ? 0 : 1;
            comboBoxIsento.setSelectedIndex(isIsent);
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            container.add(comboBoxIsento, gbc);
        }

        //BOTAO EDITAR
        gbc.gridx = 0;
        gbc.gridy = linha;
        linha++;
        gbc.gridwidth = 2;
        buttonEditar = new JButton("Editar");
        buttonEditar.addActionListener(gerenciadorBotoes);
        buttonEditar.setPreferredSize(new Dimension(100, 50));
        container.add(buttonEditar, gbc);
        
        //BOTAO VOLTAR 
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.addActionListener(gerenciadorBotoes);
        buttonVoltar.setPreferredSize(new Dimension(100, 50));
        container.add(buttonVoltar, gbc);
        
        setSize(new Dimension(600, 400));
        mostraTela();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
    public boolean StringToBoolean(String s){
        return s.equals("Sim");
    }

    @Override
    public void mostraConteudoTela() {

    }
    
    private class GerenciadorBotoes implements ActionListener{
        TelaAdmEditar tela;

        public GerenciadorBotoes(TelaAdmEditar tela) {
            this.tela = tela;
            
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton botao = (JButton) ae.getSource();
            TelaPadrao telaEditar = ControladorAdm.getInstance().getTelaAdmEditar();
            if(botao.equals(buttonVoltar)){
                try{
                    getContentPane().removeAll();
                    escondeTela();
                    ControladorAdm.getInstance().chamaTelaAdmListar();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.out.println(e);
                }
            }else if(botao.equals(buttonEditar)){
                if(pessoa instanceof Visitante){
                    try{
                        ControladorAdm.getInstance().editarVisitante(new ConteudoTelaAdm((int)formattedTextFieldMatricula.getValue(),textFieldNome.getText()), pessoa);
                        ControladorAdm.getInstance().escondeTela(ControladorAdm.getInstance().getTelaAdmEditar());
                        JOptionPane.showMessageDialog(null, "Alterações realizadas com sucesso.");
                        ControladorAdm.getInstance().chamaTelaAdmListar();
                    }catch(MatriculaJahExisteException e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }else if(pessoa instanceof Estudante){
                    try{
                        ControladorAdm.getInstance().editarEstudante(new ConteudoTelaAdm(textFieldNome.getText(), (int)formattedTextFieldMatricula.getValue(), StringToBoolean(comboBoxAdmin.getSelectedItem().toString()), StringToBoolean(comboBoxIsento.getSelectedItem().toString())), pessoa);
                        ControladorAdm.getInstance().escondeTela(ControladorAdm.getInstance().getTelaAdmEditar());
                        JOptionPane.showMessageDialog(null, "Alterações realizadas com sucesso.");
                        ControladorAdm.getInstance().chamaTelaAdmListar();
                    }catch(MatriculaJahExisteException e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }else{
                    try{
                        ControladorAdm.getInstance().editarUsuarioUFSC(new ConteudoTelaAdm(textFieldNome.getText(), (int)formattedTextFieldMatricula.getValue(), StringToBoolean(comboBoxAdmin.getSelectedItem().toString())),pessoa);
                        ControladorAdm.getInstance().escondeTela(ControladorAdm.getInstance().getTelaAdmEditar());
                        JOptionPane.showMessageDialog(null, "Alterações realizadas com sucesso.");
                        ControladorAdm.getInstance().chamaTelaAdmListar();
                    }catch(MatriculaJahExisteException e){
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
            }
        }
    }
    
}
