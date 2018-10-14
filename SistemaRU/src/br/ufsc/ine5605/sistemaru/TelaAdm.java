/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jfranca
 */
public class TelaAdm extends TelaPadrao{
    private ControladorAdm controlador;
    
    public TelaAdm(ControladorAdm controlador){
        this.controlador = controlador;
    }
    
    
    @Override
    public void mostraConteudoTela() {
        
        int opcao;
        do{
            System.out.println("");
            System.out.println("###################################");
            System.out.println("######  MENU ADMINISTRATIVO  ######");
            System.out.println("###################################");
            System.out.println();
            System.out.println("[1] CADASTRAR USUÁRIO-UFSC");
            System.out.println("[2] CADASTRAR ESTUDANTE");
            System.out.println("[3] CADASTRAR VISITANTE");
            System.out.println("[4] EXCLUIR PESSOA CADASTRADA");
            System.out.println("[5] EDITAR PESSOA CADASTRADA");
            System.out.println("[6] LISTAR TODOS OS CADASTROS");
            System.out.println("[7] ADICIONAR SALDO");
            System.out.println("[8] GERAR RELATÓRIO DE ACESSO AO RU");
            System.out.println();
            System.out.println("[0] VOLTAR PARA O MENU DE USUÁRIO");
            System.out.println("");
            opcao = leInteiro();
            
            switch(opcao){
                case 1: {
                try {
                    mostraTelaCadastroUsuarioUFSC();
                } catch (MatriculainvalidaException ex) {
                    System.out.println(ex);
                }
            }
                        break;
                case 2: {
                try {
                    mostraTelaCadastroEstudante();
                } catch (MatriculainvalidaException ex) {
                    System.out.println(ex);
                }
            }
                        break;
                case 3: {
                try {
                    mostraTelaCadastroVisitante();
                } catch (MatriculainvalidaException ex) {
                    System.out.println(ex);
                }
            }
                        break;
                case 4: {
                try {
                    mostraTelaExcluirUsuario();
                } catch (MatriculainvalidaException ex) {
                    System.out.println(ex);
                }
            }
                        break;
                case 5: {
                try {
                    mostraEditarPessoa();
                } catch (MatriculainvalidaException ex) {
                    System.out.println(ex);
                }
            }
                        break;
                case 6: controlador.listarUsuariosCadastrados();
                        break;
                case 7: {
                try {
                    monstraAdicionaSaldo();
                } catch (MatriculainvalidaException ex) {
                    System.out.println(ex);
                }
            }
                        break;
            } 
        }while (opcao!=0);
        //controlador.getControladorPrincipal().getControladorUsuarios().getTelaUsuario().mostraConteudoTelaAdm();
        
    }
    
    public void mostraTelaCadastroUsuarioUFSC() throws MatriculainvalidaException{
        clear();
        ConteudoTelaAdm conteudoTela = new ConteudoTelaAdm();
        System.out.println("");
        System.out.println("#####################################");
        System.out.println("######  CADASTRO USUARIO UFSC  ######");
        System.out.println("#####################################");
        System.out.println("");
        System.out.print("NOME: ");
        conteudoTela.nome = leString();
        System.out.print("MATRICULA: ");
        conteudoTela.codigo = leInteiro();
        System.out.print("É ADMINISTRADOR (TRUE OU FALSE): ");
        conteudoTela.admin = leBoolean();
        
        controlador.cadastraUsuarioUFSC(conteudoTela);
        mostraConteudoTela();
        
        
    }
    
    public void mostraTelaCadastroEstudante() throws MatriculainvalidaException{
        clear();
        ConteudoTelaAdm conteudoTela = new ConteudoTelaAdm();
        System.out.println("");
        System.out.println("##########################################");
        System.out.println("######  CADASTRO USUARIO ESTUDANTE  ######");
        System.out.println("##########################################");
        System.out.println("");
        System.out.print("NOME: ");
        conteudoTela.nome = leString();
        System.out.print("MATRICULA: ");
        conteudoTela.codigo = leInteiro();
        System.out.print("É ADMINISTRADOR? (TRUE OU FALSE): ");
        conteudoTela.admin = leBoolean();
        System.out.print("ESTUDANTE É INSENTO?(TRUE OU FALSE): ");
        conteudoTela.isencao = leBoolean();
        
        controlador.cadastraEstudante(conteudoTela);
        mostraConteudoTela();
        
    }
    
    public void mostraTelaCadastroVisitante() throws MatriculainvalidaException{
        clear();
        ConteudoTelaAdm conteudoTela = new ConteudoTelaAdm();
        System.out.println("");
        System.out.println("##################################");
        System.out.println("######  CADASTRO VISITANTE  ######");
        System.out.println("##################################");
        System.out.println("");
        System.out.print("NOME: ");
        conteudoTela.nome = leString();
                
        controlador.cadastraVisitante(conteudoTela);
        mostraConteudoTela();
        
        
    }
    
    public void mostraTelaExcluirUsuario() throws MatriculainvalidaException{
        ConteudoTelaAdm conteudoTela = new ConteudoTelaAdm();
        clear();
        System.out.println("");
        System.out.println("###############################");
        System.out.println("######  EXCLUIR USUÁRIO  ######");
        System.out.println("###############################");
        System.out.println("");
        System.out.print("MATRÍCULA OU ID: ");
        conteudoTela.codigo = leInteiro();
        controlador.excluirUsiario(conteudoTela.codigo);
        clear();
        mostraConteudoTela();
        
    }
    
     
    public void mostraListaCadastro(ArrayList<String> relatorioCadastro){
        clear();
        System.out.println("###################################");
        System.out.println("#  LISTA DE USUÁRIOS CADASTRADOS  #");
        System.out.println("###################################");
        System.out.println();
        for (String linhaDoRelatorio: relatorioCadastro){
            System.out.println(linhaDoRelatorio);
        }
        System.out.println();
        System.out.println("###################################");
        System.out.println();
    }
    public void mostraMatriculaExistente(){
        System.out.println("");
        System.out.println("-> USUÁRIO JÁ CADASTRADO COM ESSA MATRÍCULA OU ID");
        System.out.println("");
    }
    
    
    public void mostraEditarPessoa() throws MatriculainvalidaException{
        clear();
        ConteudoTelaAdm conteudoTela = new ConteudoTelaAdm();
        System.out.println("");
        System.out.println("##############################");
        System.out.println("#####   EDITAR USUARIO   #####");
        System.out.println("##############################");
        System.out.println("");
        System.out.println("DIGITE A MATRÍCULA OU ID: ");
        conteudoTela.codigo = leInteiro();
        
        controlador.editarUsuario(conteudoTela.codigo);
    }    
    
    public void mostraTelaEditarUsuarioUFSC(Pessoa pessoa) throws MatriculainvalidaException{
        clear();
        ConteudoTelaAdm conteudoTela = new ConteudoTelaAdm();
        System.out.println("");
        System.out.println("###################################");
        System.out.println("######  EDITAR USUARIO UFSC  ######");
        System.out.println("###################################");
        System.out.println("");
        System.out.println("NOME ATUAL: "+ ((UsuarioUFSC)pessoa).getNome());
        System.out.print("NOVO NOME: ");
        conteudoTela.nome = leString();
        System.out.println("MATRICULA ATUAL: "+ ((UsuarioUFSC)pessoa).getMatricula());
        System.out.print("MATRICULA: ");
        conteudoTela.codigo = leInteiro();
        String adm = "ADMIN: "+ ((UsuarioUFSC)pessoa).isAdmin();
        System.out.println(adm.toUpperCase());
        System.out.print("É ADMINISTRADOR (TRUE OU FALSE): ");
        conteudoTela.admin = leBoolean();
        controlador.getPessoas().remove(pessoa);
        controlador.cadastraUsuarioUFSC(conteudoTela);
        mostraConteudoTela();
    }

    public void mostraTelaEditarVisitante(Pessoa pessoa) throws MatriculainvalidaException {
        clear();
        ConteudoTelaAdm conteudoTela = new ConteudoTelaAdm();
        System.out.println("");
        System.out.println("###############################");
        System.out.println("######  EDITAR VISTANTE  ######");
        System.out.println("###############################");
        System.out.println("");
        System.out.println("NOME ATUAL: "+ ((Visitante)pessoa).getNome());
        System.out.print("NOVO NOME: ");
        conteudoTela.nome = leString();
        System.out.println("ID ATUAL: "+ ((Visitante)pessoa).getId());
        System.out.print("ID: ");
        conteudoTela.codigo = leInteiro();
        controlador.getPessoas().remove(pessoa);
        controlador.cadastraVisitante(conteudoTela);
        
        mostraConteudoTela();
    }

    public void mostraTelaEditarEstudante(Pessoa pessoa) throws MatriculainvalidaException {
        clear();
        ConteudoTelaAdm conteudoTela = new ConteudoTelaAdm();
        System.out.println("");
        System.out.println("################################");
        System.out.println("######  EDITAR ESTUDANTE  ######");
        System.out.println("################################");
        System.out.println("");
        System.out.println("NOME ATUAL: "+ ((Estudante)pessoa).getNome());
        System.out.print("NOVO NOME: ");
        conteudoTela.nome = leString();
        System.out.println("MATRICULA ATUAL: "+ ((Estudante)pessoa).getMatricula());
        System.out.print("MATRICULA: ");
        conteudoTela.codigo = leInteiro();
        String adm = "ADMIN: "+ ((Estudante)pessoa).isAdmin();
        System.out.println(adm.toUpperCase());
        System.out.print("É ADMINISTRADOR (TRUE OU FALSE): ");
        conteudoTela.admin = leBoolean();
        controlador.getPessoas().remove(pessoa);
        controlador.cadastraEstudante(conteudoTela);
        
        mostraConteudoTela();
    }
    
    public void mostraDevolucaoDinheiro(Pessoa pessoa){
        System.out.println("-> REEMBOLSO DE R$: "+ pessoa.getSaldo());
    }
    
    public void monstraAdicionaSaldo() throws MatriculainvalidaException{
        clear();
        ConteudoTelaAdm conteudoTela = new ConteudoTelaAdm();
        System.out.println("###############################");
        System.out.println("######  ADICIONAR SALDO  ######");
        System.out.println("###############################");
        System.out.println("");
        System.out.println("DIGITE A MATRÍCULA OU ID: ");
        conteudoTela.codigo = leInteiro();
        System.out.print("VALOR: R$ ");
        conteudoTela.saldo = leFloat();
        
        controlador.adicionarSaldo(conteudoTela);
    }


}
