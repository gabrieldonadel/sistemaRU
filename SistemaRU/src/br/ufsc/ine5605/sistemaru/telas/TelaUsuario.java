/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.telas;

import br.ufsc.ine5605.sistemaru.controladores.ControladorAdm;
import br.ufsc.ine5605.sistemaru.controladores.ControladorPrincipal;
import br.ufsc.ine5605.sistemaru.controladores.ControladorUsuario;
import br.ufsc.ine5605.sistemaru.entidades.UsuarioUFSC;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author 12041789921
 */
public class TelaUsuario extends TelaPadrao{


    private JButton buttonAlmoco;
    private JButton buttonJantar;
    private JButton buttonAdm;
    private JButton buttonSaldo;
    private JButton buttonRelatorio;
    private JButton buttonSair;
    
    private GerenciadorBotoes gerenciadorBotoes;
    
    
    public TelaUsuario() {

        this.gerenciadorBotoes = new GerenciadorBotoes();
    }

    
    
    
    
    @Override
    public void mostraConteudoTela() {
        
        getContentPane().removeAll();
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonAlmoco = new JButton("Almoçar");
        buttonAlmoco.addActionListener(gerenciadorBotoes);
        buttonAlmoco.setPreferredSize(new Dimension(150, 100));
        container.add(buttonAlmoco, gbc);
        
        
        gbc.gridx = 1;
        buttonJantar = new JButton("Jantar");
        buttonJantar.addActionListener(gerenciadorBotoes);
        buttonJantar.setPreferredSize(new Dimension(150, 100));
        container.add(buttonJantar, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonSaldo = new JButton("Saldo");
        buttonSaldo.addActionListener(gerenciadorBotoes);
        buttonSaldo.setPreferredSize(new Dimension(150, 100));
        container.add(buttonSaldo, gbc);
        
        gbc.gridx = 1;
        buttonRelatorio = new JButton("Relatório");
        buttonRelatorio.addActionListener(gerenciadorBotoes);
        buttonRelatorio.setPreferredSize(new Dimension(150, 100));
        container.add(buttonRelatorio, gbc);
        
        if(ControladorUsuario.getInstance().getPessoa() instanceof UsuarioUFSC && ((UsuarioUFSC)ControladorUsuario.getInstance().getPessoa()).isAdmin()){
            gbc.gridx = 0;
            gbc.gridy = 2;
            buttonAdm = new JButton("Menu ADM");
            buttonAdm.addActionListener(gerenciadorBotoes);
            buttonAdm.setPreferredSize(new Dimension(150, 100));
            container.add(buttonAdm, gbc);
        }
        
        gbc.gridy = 2;
        gbc.gridx = 1;
        buttonSair = new JButton("Deslogar");
        buttonSair.addActionListener(gerenciadorBotoes);
        buttonSair.setPreferredSize(new Dimension(150, 100));
        container.add(buttonSair, gbc);
        
        
        setSize(new Dimension(600, 400));
        mostraTela();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    } 
    
    public String mostraTelaSaldo() {
        return "Saldo atual disponível: R$ "+ControladorUsuario.getInstance().consultarSaldo();
    }

    public String mostraSucessoRefeicao() {
        return "Refeição foi realizada com sucesso\n"+mostraTelaSaldo();
    }

    public void mostraRelatorioUsuario(int nRefeicoesMes1, int nRefeicoesMes2, int nRefeicoesMes3) {
        String s ="Refeições no mês: "+nRefeicoesMes1+"\n";
        s +="Refeições no último mês: "+nRefeicoesMes2+"\n";
        s +="Refeições no penúltimo mês: "+nRefeicoesMes3+"\n";
        
       JOptionPane.showMessageDialog(null, s);
    }

    
    
    
    
    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae){
            JButton botao = (JButton) ae.getSource();
            if(botao.equals(buttonAlmoco)){
                try{
                    ControladorUsuario.getInstance().validaRefeicao(0);
                    JOptionPane.showMessageDialog(null, "Refeição foi realizada com sucesso\n\n"+mostraTelaSaldo());
                    
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.out.println(e);
                }
            
            }else if(botao.equals(buttonJantar)){
                try{
                    ControladorUsuario.getInstance().validaRefeicao(1);
                    JOptionPane.showMessageDialog(null, "Refeição foi realizada com sucesso\n\n"+mostraTelaSaldo());
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.out.println(e);
                }
            
            }else if(botao.equals(buttonSaldo)){
                    JOptionPane.showMessageDialog(null, mostraTelaSaldo());
                
            }else if(botao.equals(buttonRelatorio)){
                ControladorUsuario.getInstance().relatorioRefeicao();
                
                
            }else if(botao.equals(buttonAdm)){
                ControladorUsuario.getInstance().escondeTela();
                ControladorUsuario.getInstance().chamaTelaAdm();
                
            }else if(botao.equals(buttonSair)){
                ControladorUsuario.getInstance().escondeTela();
                ControladorPrincipal.getInstance().getTelaPrincipal().mostraTela();
                
            }

            
        }
    }
   
}
