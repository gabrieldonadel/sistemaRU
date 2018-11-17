/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.entidades;

import br.ufsc.ine5605.sistemaru.entidades.UsuarioUFSC;

/**
 *
 * @author 12041789921
 */
public class Estudante extends UsuarioUFSC{
    private boolean isencao;

    public Estudante(String nome, int matricula, boolean admin, boolean isencao) {
        super(nome, matricula, admin);
        this.isencao = isencao;
    }

    public boolean isIsencao() {
        return isencao;
    }

    public void setIsencao(boolean isencao) {
        this.isencao = isencao;
    }
}
