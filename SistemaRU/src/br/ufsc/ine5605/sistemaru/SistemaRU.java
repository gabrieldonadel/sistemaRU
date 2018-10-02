/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 12041789921
 */
public class SistemaRU {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControladorPrincipal a = new ControladorPrincipal ();
        Restaurante r = new Restaurante (a);
        DateFormat dataSimple = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dataSimple.format(r.getDiaAtual()));
    }
    
}
