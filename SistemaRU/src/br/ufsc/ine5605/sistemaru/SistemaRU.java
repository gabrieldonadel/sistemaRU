/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru;

import br.ufsc.ine5605.sistemaru.exceptions.MatriculainvalidaException;
import br.ufsc.ine5605.sistemaru.controladores.ControladorPrincipal;
import br.ufsc.ine5605.sistemaru.controladores.ControladorAdm;
import br.ufsc.ine5605.sistemaru.exceptions.MatriculaJahExisteException;
import br.ufsc.ine5605.sistemaru.exceptions.PrencheCampoNomeException;
import br.ufsc.ine5605.sistemaru.telas.ConteudoTelaAdm;


/**
 *
 * @author 12041789921
 */
public class SistemaRU {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PrencheCampoNomeException {
        
        ControladorPrincipal a = ControladorPrincipal.getInstance();
        ControladorAdm b = a.getControladorAdm();
        ConteudoTelaAdm x = new ConteudoTelaAdm("José Augusto", 111, false, true);
        ConteudoTelaAdm y = new ConteudoTelaAdm("Gabriel Donadel",222, true,false);
        ConteudoTelaAdm z = new ConteudoTelaAdm("Paulo");
        ConteudoTelaAdm c = new ConteudoTelaAdm("Georgia",333,false,false);
        ConteudoTelaAdm d = new ConteudoTelaAdm("Geremias",444,false,false);
        try{
            b.cadastraEstudante(x);
            b.cadastraEstudante(y);
            b.cadastraVisitante(z);
            b.cadastraEstudante(c);
            b.cadastraUsuarioUFSC(d);
        }catch (MatriculaJahExisteException e){System.out.println(e);};
        
        a.getTelaPrincipal().mostraConteudoTela();
        
        
        /*TelaAdm c = b.getTelaAdm();
        c.mostraConteudoTela();*/
    }
    
}
