/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.controladores;

import br.ufsc.ine5605.sistemaru.exceptions.DataInvalidaException;
import br.ufsc.ine5605.sistemaru.telas.ConteudoTelaRelatorioAdm;
import br.ufsc.ine5605.sistemaru.telas.TelaRelatorioAdm;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author 12041789921
 */
public class ControladorRelatorioAdm {
    
    private static ControladorRelatorioAdm controladorRelatorioAdm;
    
    private TelaRelatorioAdm telaRelatorioAdm; 
    private ConteudoTelaRelatorioAdm conteudoTelaRelatorioAdm;
    

    private ControladorRelatorioAdm() {
        this.telaRelatorioAdm = new TelaRelatorioAdm();
        this.conteudoTelaRelatorioAdm = new ConteudoTelaRelatorioAdm();
    }

    public TelaRelatorioAdm getTelaRelatorioAdm() {
        return telaRelatorioAdm;
    }

    public ConteudoTelaRelatorioAdm getConteudoTelaRelatorioAdm() {
        return conteudoTelaRelatorioAdm;
    }
    
    public void relatorioRefeicao() throws DataInvalidaException{
        
        Date dataInicial = null;
        Date dataFinal = null;
        try{
            dataInicial = stringToDate(conteudoTelaRelatorioAdm.dataInicial);
            dataFinal = stringToDate(conteudoTelaRelatorioAdm.dataFinal);
        
        
            Date atual = dataInicial;
            int contadorRefeicoes = 0;
            HashMap<Date,Integer> acessosRU = ControladorPrincipal.getInstance().getMapeadorRestaurante().getResturante().getAcessosRU();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            Calendar cal = Calendar.getInstance();
            while(atual.before(dataFinal)){
                if(acessosRU.get(dateFormat.parse(atual.getDate()+"-"+(atual.getMonth()+1)+"-"+(atual.getYear()+1900))) != null){
                    contadorRefeicoes += acessosRU.get(dateFormat.parse(atual.getDate()+"-"+(atual.getMonth()+1)+"-"+(atual.getYear()+1900)));
                    System.out.println("Atual: "+dateFormat.parse(atual.getDate()+"-"+(atual.getMonth()+1)+"-"+(atual.getYear()+1900)));
                }
                cal.setTime(atual);
                cal.add(Calendar.DAY_OF_MONTH, 1);
                atual = cal.getTime();
               
            }

            telaRelatorioAdm.mostraRelatorio(conteudoTelaRelatorioAdm.dataInicial, conteudoTelaRelatorioAdm.dataFinal, contadorRefeicoes);
        }catch(DataInvalidaException e){
            throw e;
        }catch(Exception e){System.out.println(e);}
    }
    
    public Date stringToDate(String data) throws DataInvalidaException{
        
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            return dateFormat.parse(data);
        } catch(Exception e) {
            throw new DataInvalidaException();
        }
        
        
    }
    
    public static ControladorRelatorioAdm getInstance(){
        return (controladorRelatorioAdm == null)? controladorRelatorioAdm = new ControladorRelatorioAdm() : controladorRelatorioAdm;

    }
}
