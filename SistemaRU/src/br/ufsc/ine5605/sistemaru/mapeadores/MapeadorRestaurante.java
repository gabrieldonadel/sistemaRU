/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.mapeadores;

import br.ufsc.ine5605.sistemaru.controladores.Restaurante;
import br.ufsc.ine5605.sistemaru.entidades.Pessoa;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author Dall Agnol
 */
public class MapeadorRestaurante {
    
    private Restaurante cacheRestaurante;
    private final String filename = "restaurantes.doni";
    
    public MapeadorRestaurante(){
        this.cacheRestaurante = new Restaurante();
        load();
    }
    public void load(){
        try{
            FileInputStream fIS = new FileInputStream(filename);
            ObjectInputStream oIS = new ObjectInputStream(fIS);
        
            this.cacheRestaurante =  (Restaurante) oIS.readObject();
            
            fIS.close();
            oIS.close();
            
        }catch(FileNotFoundException e){
            System.out.println("Arquivo nao encontrado.");
            persist();
        }catch(IOException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }
    public void persist(){
        
        try{
            
            FileOutputStream fOS = new FileOutputStream(filename);
            
            ObjectOutputStream oOS = new ObjectOutputStream(fOS);
            
            oOS.writeObject(cacheRestaurante);
            
            
            fOS.flush();
            oOS.flush();
            fOS.close();
            oOS.close();
            
        
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }
        load();
    }
    public Restaurante getResturante(){
        return (cacheRestaurante != null) ? cacheRestaurante : null;
    }
    
    public void setRestaurante(Restaurante restaurante){
        this.cacheRestaurante = restaurante;
    }
    
}
