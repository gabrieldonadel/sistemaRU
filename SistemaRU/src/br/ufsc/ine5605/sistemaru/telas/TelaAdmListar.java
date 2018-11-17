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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author franca
 */
public class TelaAdmListar extends TelaPadrao {
    private GerenciadorBotoes gerenciadorBotoes;
    private JButton buttonVoltar;
    private JButton buttonCadastrar;
    private JButton buttonExcluir;
    private JButton buttonEditar;        
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JPanel painelFundo;
    
    
    public TelaAdmListar(){
        this.gerenciadorBotoes = new GerenciadorBotoes();
    }
    
    public JTable getTabela() {
        return tabela;
    }
    
    @Override
    public void mostraConteudoTela() {
    
    }
    
    public void mostraConteudoTela(Object[][] dados) {
        getContentPane().removeAll();
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //DADOS DA TABELA
        Object [][] pessoas = dados;
        String [] colunas = {"Nome","Tipo de Cadastro", "Matrícula", "Saldo"};
        
        //TABELA
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = MAXIMIZED_HORIZ;
        gbc.gridwidth = 3;
        painelFundo = new JPanel();
        tabela = new JTable(pessoas,colunas);
        tabela.setPreferredScrollableViewportSize(new Dimension(600, 100));
        tabela.setFillsViewportHeight(true);
        barraRolagem = new JScrollPane(tabela);
        painelFundo.add(barraRolagem); 
        //container.add(painelFundo);
        container.add(painelFundo,gbc);
        
        //BOTAO CADASTRAR
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        
        buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.addActionListener(gerenciadorBotoes);
        buttonCadastrar.setPreferredSize(new Dimension(200, 50));
        container.add(buttonCadastrar, gbc);
                     
        //BOTAO EDITAR 
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonEditar = new JButton("Editar");
        buttonEditar.addActionListener(gerenciadorBotoes);
        buttonEditar.setPreferredSize(new Dimension(200, 50));
        container.add(buttonEditar, gbc);
     
        //BOTAO EXCLUIR 
        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonExcluir = new JButton("Excluir");
        buttonExcluir.addActionListener(gerenciadorBotoes);
        buttonExcluir.setPreferredSize(new Dimension(200, 50));
        container.add(buttonExcluir, gbc);
        
        //BOTAO VOLTAR 
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        buttonVoltar = new JButton("Voltar");
        buttonVoltar.addActionListener(gerenciadorBotoes);
        buttonVoltar.setPreferredSize(new Dimension(600, 50));
        container.add(buttonVoltar, gbc);
        
        
        setSize(new Dimension(800, 600));
        mostraTela();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }

    
    
    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton botao = (JButton) ae.getSource();
            TelaPadrao telaListar = ControladorAdm.getInstance().getTelaAdmListar();
            int linhaSelecionada = ControladorAdm.getInstance().getTelaAdmListar().getTabela().getSelectedRow();
            if(botao.equals(buttonVoltar)){
                try{
                    getContentPane().removeAll();
                    escondeTela();
                    ControladorAdm.getInstance().mostraTela();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.out.println(e);
                }
            }else if(botao.equals(buttonCadastrar)){
                try{
                    getContentPane().removeAll();
                    escondeTela();
                    ControladorAdm.getInstance().chamaTelaAdmCadastro();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.out.println(e);
                }
            
            }else if(botao.equals(buttonExcluir)){
                try{
                    getContentPane().removeAll();
                    escondeTela();
                    ControladorAdm.getInstance().chamaTelaAdmExcluir(linhaSelecionada);
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Você não selecionou nenhum usuário!");
                    System.out.println(e);
                    ControladorAdm.getInstance().getTelaAdmListar();
                }
            }else if(botao.equals(buttonEditar)){
                try{
                    ControladorAdm.getInstance().chamaTelaAdmEditar(linhaSelecionada);
                    getContentPane().removeAll();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Você não selecionou nenhum usuário!");
                    System.out.println(e);
                    ControladorAdm.getInstance().getTelaAdmListar();
                }
            }
            
            
        }
    }
    
}
