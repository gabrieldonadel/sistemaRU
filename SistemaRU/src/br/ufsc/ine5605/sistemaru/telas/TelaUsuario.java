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
        
        /*
        int opcao = -1;
        do{
            System.out.println("");
            System.out.println("############################");
            System.out.println("######  MENU USUARIO  ######");
            System.out.println("############################");
            System.out.println();
            System.out.println("DIA ATUAL: " + ControladorUsuario.getInstance().diaAtual());
            System.out.println();
            System.out.println("[1] ALMOÇAR");
            System.out.println("[2] JANTAR");
            System.out.println("[3] CONSULTAR SALDO");
            System.out.println("[4] GERAR RELATÓRIO ");
            System.out.println("[0] DESLOGAR");
            
            try{
                opcao = leInteiro();
            }catch(InputInvalidoException e){
                System.out.println(e);
                continue;
            }
            
            switch(opcao){
                case 1: 
                        try{
                            ControladorUsuario.getInstance().validaRefeicao(0);
                        }catch(Exception e){System.out.println(e);}
                        break;
                case 2: 
                        try{
                            ControladorUsuario.getInstance().validaRefeicao(1);
                        }catch(Exception e){System.out.println(e);}
                        break;
                        
                case 3: mostraTelaSaldo();
                        break;
                        
                case 4: ControladorUsuario.getInstance().relatorioRefeicao();
                        break;
                
            }
            
         
        }while (opcao!=0);
        */
    }
    
   /* public void mostraConteudoTelaAdm() {
        int opcao = -1;
        do{
            System.out.println("");
            System.out.println("############################");
            System.out.println("######  MENU USUARIO  ######");
            System.out.println("############################");
            System.out.println();
            System.out.println("DIA ATUAL: " + ControladorUsuario.getInstance().diaAtual());
            System.out.println();
            System.out.println("[1] ALMOÇAR");
            System.out.println("[2] JANTAR");
            System.out.println("[3] CONSULTAR SALDO");
            System.out.println("[4] GERAR RELATÓRIO");
            System.out.println("[5] ENTRAR NO MENU DE ADMINISTRADOR");
            System.out.println("[0] DESLOGAR");
            
            try{
                opcao = leInteiro();
            }catch(InputInvalidoException e){
                System.out.println(e);
                continue;
            }
            
            switch(opcao){
                case 1: 
                        try{
                            ControladorUsuario.getInstance().validaRefeicao(0);
                        }catch(Exception e){System.out.println(e);}
                        break;
                case 2: 
                        try{
                            ControladorUsuario.getInstance().validaRefeicao(1);
                        }catch(Exception e){System.out.println(e);}
                        break;
                        
                case 3: mostraTelaSaldo();
                        break;
                        
                case 4: ControladorUsuario.getInstance().relatorioRefeicao();
                        break;
                        
                case 5: ControladorUsuario.getInstance().getControladorPrincipal().getControladorAdm().getTelaAdm().mostraConteudoTela();
                
            }
         
        }while (opcao!=0);
    }*/

    public String mostraTelaSaldo() {
        return "SALDO ATUAL DISPONIVEL: R$ "+ControladorUsuario.getInstance().consultarSaldo();
    }

    public String mostraSucessoRefeicao() {
        return "SUA REFEIÇÃO FOI REALIZADA COM SUCESSO\n"+mostraTelaSaldo();
    }

    public void mostraRelatorioUsuario(int nRefeicoesMes1, int nRefeicoesMes2, int nRefeicoesMes3) {
        String s ="REFEIÇÕES NO MES: "+nRefeicoesMes1+"\n";
        s +="REFEIÇÕES NO ÚLTIMO MES: "+nRefeicoesMes2+"\n";
        s +="REFEIÇÕES NO PENÚLTIMO MES: "+nRefeicoesMes3+"\n";
        s +="\n";
        s +="#######################";
        
       JOptionPane.showMessageDialog(null, s);
    }

    
    
    
    
    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae){
            JButton botao = (JButton) ae.getSource();
            System.out.println("clicou: "+botao.getText());
            if(botao.equals(buttonAlmoco)){
                try{
                    ControladorUsuario.getInstance().validaRefeicao(0);
                    JOptionPane.showMessageDialog(null, mostraTelaSaldo());
                    
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    System.out.println(e);
                }
            
            }else if(botao.equals(buttonJantar)){
                try{
                    ControladorUsuario.getInstance().validaRefeicao(1);
                    JOptionPane.showMessageDialog(null, mostraTelaSaldo());
                    
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
