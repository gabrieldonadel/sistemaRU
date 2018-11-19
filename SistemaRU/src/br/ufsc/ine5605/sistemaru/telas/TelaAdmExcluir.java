/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.telas;

import br.ufsc.ine5605.sistemaru.controladores.ControladorAdm;
import br.ufsc.ine5605.sistemaru.entidades.Pessoa;
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
import javax.swing.text.NumberFormatter;

/**
 *
 * @author franca
 */
public class TelaAdmExcluir extends TelaPadrao {
    
    private JLabel labelTitulo;
    private JLabel labelNome;
    private JLabel labelMatricula;
    private JButton buttonExcluir;
    private JButton buttonVoltar;
    private GerenciadorBotoes gerenciadorBotoes;
    private Pessoa pessoa;
    
    public TelaAdmExcluir(){
        this.gerenciadorBotoes = new GerenciadorBotoes();
    }
    
    public void mostraConteudoTela(Pessoa pessoa) {
        getContentPane().removeAll();
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.pessoa = pessoa;
        //TITULO
        labelTitulo = new JLabel();
        labelTitulo.setText("Você realmente deseja excluir:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        container.add(labelTitulo, gbc);
        //NOME
        labelNome = new JLabel();
        labelNome.setText("Nome: "+ pessoa.getNome());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        container.add(labelNome, gbc);
        //MATRICULA
        labelMatricula = new JLabel();
        labelMatricula.setText("Matrícula: "+ ControladorAdm.getInstance().getMatricula(pessoa));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        container.add(labelMatricula, gbc);
      
        //BOTÃO EXCLUIR
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        buttonExcluir = new JButton("Excluir");
        buttonExcluir.addActionListener(gerenciadorBotoes);
        buttonExcluir.setPreferredSize(new Dimension(100, 50));
        buttonExcluir.setBackground(Color.red);
        container.add(buttonExcluir, gbc);
               
        //BOTAO VOLTAR 
        gbc.gridx = 0;
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

    @Override
    public void mostraConteudoTela() {
    }
    
    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton botao = (JButton) ae.getSource();
            TelaPadrao telaExcluir = ControladorAdm.getInstance().getTelaAdmExcluir();
            
            if(botao.equals(buttonVoltar)){
                try{
                    getContentPane().removeAll();
                    escondeTela();
                    ControladorAdm.getInstance().chamaTelaAdmListar();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.out.println(e);
                }
            }else if (botao.equals(buttonExcluir)){
                try{
                    
                    ControladorAdm.getInstance().excluirUsiario(ControladorAdm.getInstance().getMatricula(pessoa));
                    JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
                    escondeTela();
                    getContentPane().removeAll();
                    ControladorAdm.getInstance().chamaTelaAdmListar();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.out.println(e);
                }
            }
        }
        
    }
    
}
