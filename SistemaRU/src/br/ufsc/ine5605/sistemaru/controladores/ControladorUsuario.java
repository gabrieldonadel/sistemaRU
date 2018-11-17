/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.controladores;

import br.ufsc.ine5605.sistemaru.entidades.Estudante;
import br.ufsc.ine5605.sistemaru.entidades.Pessoa;
import br.ufsc.ine5605.sistemaru.exceptions.SaldoInsuficienteException;
import br.ufsc.ine5605.sistemaru.enuns.TipoRefeicao;
import br.ufsc.ine5605.sistemaru.telas.TelaUsuario;
import br.ufsc.ine5605.sistemaru.entidades.UsuarioUFSC;
import br.ufsc.ine5605.sistemaru.entidades.Visitante;
import br.ufsc.ine5605.sistemaru.mapeadores.MapeadorRestaurante;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author 12041789921
 */
public class ControladorUsuario {
    
    private TelaUsuario telaUsuario;
    private Pessoa pessoa;
    
    private static ControladorUsuario controladorUsuario;

    private ControladorUsuario() {
        this.telaUsuario = new TelaUsuario();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public TelaUsuario getTelaUsuario() {
        return telaUsuario;
    }
    
    public float consultarSaldo(){
        return pessoa.getSaldo();
    }
    
    public void validaRefeicao(int i) throws SaldoInsuficienteException{
        TipoRefeicao tipo = null;
        Date hoje = ControladorPrincipal.getInstance().getRestaurante().getDiaAtual();
        switch(i){
            case 1: tipo = TipoRefeicao.JANTA;
                    break;
            default:tipo = TipoRefeicao.ALMOCO;
                    break;
        }
        
        float preco = 0;
        
        if(pessoa instanceof Visitante){
            preco = 6.1f;
        }else if(pessoa instanceof UsuarioUFSC){
            if(!((Estudante)pessoa).isIsencao()){
                preco = 1.5f;
            }else{
                pessoa.adicionaRefeicao(hoje, tipo);
                telaUsuario.mostraSucessoRefeicao();
                ControladorAdm.getInstance().getMapeadorPessoa().put(pessoa);
                
                
                
                int nRefeicoes = (ControladorPrincipal.getInstance().getRestaurante().getAcessosRU().get(hoje) != null) ? ControladorPrincipal.getInstance().getRestaurante().getAcessosRU().get(hoje) :0;
                System.out.println("add data");
                ControladorPrincipal.getInstance().getRestaurante().getAcessosRU().put(hoje, nRefeicoes+1);
                System.out.println("acessos: " +ControladorPrincipal.getInstance().getRestaurante().getAcessosRU().toString());
                
                //persistencia
                ControladorPrincipal.getInstance().getMapeadorRestaurante().setRestaurante(ControladorPrincipal.getInstance().getRestaurante());
                ControladorPrincipal.getInstance().getMapeadorRestaurante().persist();
               
                return;
            }
        }else{
            preco = 2.9f;
        }
        
        if(consultarSaldo() >= preco ){
            pessoa.descontaSaldo(preco);
            
            pessoa.adicionaRefeicao(hoje, tipo);
            telaUsuario.mostraSucessoRefeicao();
        }else{
            throw new SaldoInsuficienteException();
        }
        ControladorAdm.getInstance().getMapeadorPessoa().put(pessoa);
        
        
        int nRefeicoes = (ControladorPrincipal.getInstance().getRestaurante().getAcessosRU().get(hoje) != null) ? ControladorPrincipal.getInstance().getRestaurante().getAcessosRU().get(hoje) :0;
        System.out.println("add data");
        ControladorPrincipal.getInstance().getRestaurante().getAcessosRU().put(hoje, nRefeicoes+1);
        System.out.println("acessos: " +ControladorPrincipal.getInstance().getRestaurante().getAcessosRU().toString());

        //persistencia
        //ControladorPrincipal.getInstance().getMapeadorRestaurante().setRestaurante(ControladorPrincipal.getInstance().getRestaurante());
        ControladorPrincipal.getInstance().getMapeadorRestaurante().persist();

    }
    
    public void mostraTela(){
        telaUsuario.setVisible(true);
    }

    public void escondeTela(){
        telaUsuario.getContentPane().removeAll();
        telaUsuario.setVisible(false);
    }
    
    public void relatorioRefeicao(){
        int countMes = 0;
        int countMesUltimo = 0;
        int countMesPenultimo = 0;
        
        HashMap<Date, ArrayList<TipoRefeicao>> refeicoes = pessoa.getRegistrosRefeicoes();
        
        Date hoje = ControladorPrincipal.getInstance().getRestaurante().getDiaAtual();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormatMonthYear = new SimpleDateFormat("MM-yyyy");
        String mesAnoAtual = dateFormatMonthYear.format(hoje);
      
        Date dia = null;
        Date mesAtual = null;
        Date mesUltimo = null;
        Date mesPenultimo = null;
        Date mesQVem = null;
        
        try{
            mesAtual = dateFormat.parse("01-"+mesAnoAtual);
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(mesAtual);
            cal.add(Calendar.MONTH, 1);
            mesQVem = cal.getTime();
            cal.add(Calendar.MONTH, -2);
            mesUltimo = cal.getTime();
            cal.add(Calendar.MONTH, -1);
            mesPenultimo = cal.getTime();
            dia = new Date(mesPenultimo.getTime());
            
        }catch(ParseException e){
            System.out.println(e);
        }
        Calendar cal = Calendar.getInstance();
        while(dia.before(mesUltimo)){
            System.out.println(dia);
            ArrayList mes = new ArrayList();
            try{
                mes = (ArrayList) refeicoes.get(dateFormat.parse(dia.getDate()+"-"+(dia.getMonth()+1)+"-"+(dia.getYear()+1900)));                
            }catch(Exception e){System.out.println(e);}
            if(mes != null){
                countMesPenultimo += mes.size();
            }
            
            cal.setTime(dia);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            dia = cal.getTime();
        }
        while(dia.before(mesAtual)){
            System.out.println(dia);
            ArrayList mes = new ArrayList();
            try{
                mes = (ArrayList) refeicoes.get(dateFormat.parse(dia.getDate()+"-"+(dia.getMonth()+1)+"-"+(dia.getYear()+1900)));                
            }catch(Exception e){System.out.println(e);}
            if(mes != null){
                countMesUltimo += mes.size();
            }
            cal.setTime(dia);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            dia = cal.getTime();
        }        
        while(dia.before(mesQVem)){
            System.out.println(dia);
            ArrayList mes = new ArrayList();
            try{
                mes = (ArrayList) refeicoes.get(dateFormat.parse(dia.getDate()+"-"+(dia.getMonth()+1)+"-"+(dia.getYear()+1900)));                
            }catch(Exception e){System.out.println(e);}
            if(mes != null){
                countMes += mes.size();
            }
            cal.setTime(dia);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            dia = cal.getTime();
        }
        telaUsuario.mostraRelatorioUsuario(countMes,countMesUltimo,countMesPenultimo);
    }
    
    public String diaAtual (){
        Date diaAtual = ControladorPrincipal.getInstance().diaAtual();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  
        String hoje = formato.format(diaAtual);
        return hoje;
    }
    
    public static ControladorUsuario getInstance(){
        return (controladorUsuario == null)? controladorUsuario = new ControladorUsuario() : controladorUsuario;
    }

    public void chamaTelaUsuario(){
        telaUsuario.mostraConteudoTela();
    }

    public void chamaTelaAdm(){
        ControladorPrincipal.getInstance().getControladorAdm().chamaTelaAdm();
    }
}
