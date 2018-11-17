/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.exceptions;

/**
 *
 * @author franca
 */
public class PrencheCampoNomeException extends Exception{
    public PrencheCampoNomeException(){
        super("O campo NOME não pode estar vázio");
    }
    
}
