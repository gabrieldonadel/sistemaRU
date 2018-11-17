/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.controladores;

import br.ufsc.ine5605.sistemaru.enuns.TipoRefeicao;
import br.ufsc.ine5605.sistemaru.entidades.Pessoa;
import br.ufsc.ine5605.sistemaru.controladores.ControladorPrincipal;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Usuario
 */
public class Restaurante implements Serializable{
    
    private static Restaurante restaurante;
    
    private Date diaAtual;
    private HashMap<Date, Integer> acessosRU;
            
    public Restaurante(){
        Date dataDate = new Date();
        SimpleDateFormat dateFormatMonthYear = new SimpleDateFormat("dd-MM-yyyy");        
        String dateString = dateFormatMonthYear.format(dataDate);
        try{
            this.diaAtual = dateFormatMonthYear.parse(dateString); 
        }catch(Exception e){System.out.println(e);}     
        acessosRU = new HashMap();
    }

    public Date getDiaAtual() {
        return diaAtual;
    }

    public void proximoDia() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.diaAtual);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        this.diaAtual = cal.getTime();
    }
    
    public void proximoMes() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDiaAtual());
        int max = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=0;i<max;i++){
            proximoDia();
        }
    }

    public HashMap<Date, Integer> getAcessosRU() {
        return acessosRU;
    }   
    
    public static Restaurante getInstance(){
        return (restaurante == null)? restaurante = new Restaurante() : restaurante;

    }
}
