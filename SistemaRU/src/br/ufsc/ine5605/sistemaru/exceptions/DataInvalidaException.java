/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.exceptions;

/**
 *
 * @author 12041789921
 */
public class DataInvalidaException extends Exception{
    
    public DataInvalidaException(){
        super("A data digitada é invalida, siga o padrão dd/mm/yyyy");
    }
}
