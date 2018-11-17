/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.telas;

import br.ufsc.ine5605.sistemaru.controladores.ControladorAdm;
import br.ufsc.ine5605.sistemaru.controladores.ControladorUsuario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jfranca
 */
public class TelaAdm extends TelaPadrao{
    
    
    private JButton buttonListar;
    private JButton buttonAdicionarSaldo;
    private JButton buttonRelatorioRu;
    private JButton buttonPassarDia;
    private JButton buttonPassarMes;
    private JButton buttonVoltar;
    private GerenciadorBotoes gerenciadorBotoes;

    public TelaAdm(){
        this.gerenciadorBotoes = new GerenciadorBotoes();
    }
    
    
    
    @Override
    public void mostraConteudoTela(){
        getContentPane().removeAll();
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        
        
        //BOTAO Cadastros 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        buttonListar = new JButton("Cadastros");
        buttonListar.addActionListener(gerenciadorBotoes);
        buttonListar.setPreferredSize(new Dimension(500, 50));
        container.add(buttonListar, gbc);
        
        //BOTAO ADICIONAR SALDO 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        buttonAdicionarSaldo = new JButton("Adicionar Saldo");
        buttonAdicionarSaldo.addActionListener(gerenciadorBotoes);
        buttonAdicionarSaldo.setPreferredSize(new Dimension(250, 50));
        container.add(buttonAdicionarSaldo, gbc);
        
        //BOTAO RELATORIO RU 
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        buttonRelatorioRu = new JButton("Relatório RU");
        buttonRelatorioRu.addActionListener(gerenciadorBotoes);
        buttonRelatorioRu.setPreferredSize(new Dimension(250, 50));
        container.add(buttonRelatorioRu, gbc);
        
        //BOTAO PASSAR DIA 
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        buttonPassarDia = new JButton("Passar Dia");
        buttonPassarDia.addActionListener(gerenciadorBotoes);
        buttonPassarDia.setPreferredSize(new Dimension(250, 50));
        container.add(buttonPassarDia, gbc);
        
        //BOTAO PASSAR MÊS  
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        buttonPassarMes = new JButton("Passar Mês");
        buttonPassarMes.addActionListener(gerenciadorBotoes);
        buttonPassarMes.setPreferredSize(new Dimension(250, 50));
        container.add(buttonPassarMes, gbc);
        
        //BOTAO VOLTAR 
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.addActionListener(gerenciadorBotoes);
        buttonVoltar.setPreferredSize(new Dimension(500, 50));
        container.add(buttonVoltar, gbc);
        
        
        
        
        setSize(new Dimension(600, 400));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton botao = (JButton) ae.getSource();
            TelaPadrao telAdm = ControladorAdm.getInstance().getTelaAdm();
            System.out.println("clicou: "+botao.getText());
            if(botao.equals(buttonListar)){
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
            }else if(botao.equals(buttonPassarDia)){
                ControladorAdm.getInstance().passarProximoDia();
                JOptionPane.showMessageDialog(null, "Operação realizada com sucesso");
            }else if(botao.equals(buttonPassarMes)){
                ControladorAdm.getInstance().passarProximoMes();
                JOptionPane.showMessageDialog(null, "Operação realizada com sucesso");
            }else if (botao.equals(buttonAdicionarSaldo)){
                ControladorAdm.getInstance().chamaTelaAddSaldo();
            }else if (botao.equals(buttonRelatorioRu)){
                getContentPane().removeAll();
                escondeTela();
                ControladorAdm.getInstance().gerarRelatorioRu();
            }   
            

        }
        
    }
}       