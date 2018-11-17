/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.sistemaru.controladores;

import br.ufsc.ine5605.sistemaru.mapeadores.MapeadorPessoa;
import br.ufsc.ine5605.sistemaru.exceptions.MatriculaJahExisteException;
import br.ufsc.ine5605.sistemaru.exceptions.MatriculainvalidaException;
import br.ufsc.ine5605.sistemaru.telas.*;
import br.ufsc.ine5605.sistemaru.entidades.*;
import br.ufsc.ine5605.sistemaru.exceptions.NaoSelecionadoException;
import br.ufsc.ine5605.sistemaru.exceptions.PrencheCampoNomeException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author jfranca
 */
public class ControladorAdm {
    
    private static ControladorAdm controladorAdm;
    private MapeadorPessoa mapeadorPessoa;
    private TelaAdm telaAdm;
    private TelaAdmCadastro telaAdmCadastro;
    private TelaAdmExcluir telaAdmExcluir;
    private TelaAdmEditar telaAdmEditar;
    private TelaAdmListar telaAdmListar;
    private TelaAdmAddSaldo telaAdmAddSaldo;
    private ConteudoTelaAdm conteudoTelaAdm;

    private ControladorAdm() {
        this.mapeadorPessoa = new MapeadorPessoa();
        this.telaAdm = new TelaAdm();
        this.telaAdmCadastro = new TelaAdmCadastro();
        this.telaAdmExcluir = new TelaAdmExcluir();
        this.telaAdmEditar = new TelaAdmEditar();
        this.telaAdmListar = new TelaAdmListar();
        this.conteudoTelaAdm = new ConteudoTelaAdm();
        this.telaAdmAddSaldo = new TelaAdmAddSaldo();
    }

    public ArrayList<Pessoa> getPessoas() {
        return mapeadorPessoa.getList();
    }
    public void cadastraUsuarioUFSC (ConteudoTelaAdm conteudoTelaAdm) throws MatriculaJahExisteException, PrencheCampoNomeException{
        UsuarioUFSC usuario = desempacotaUsuarioUFSC(conteudoTelaAdm);
        if(!conteudoTelaAdm.nome.equals("")){
            if (!idJaExiste(conteudoTelaAdm.codigo)){
                mapeadorPessoa.put(usuario);
            }else{
                throw new MatriculaJahExisteException();
            }
        }else{
            throw new PrencheCampoNomeException();
        }    
    }
    public void cadastraEstudante (ConteudoTelaAdm conteudoTelaAdm) throws MatriculaJahExisteException, PrencheCampoNomeException{
        Estudante estudante = desempacotaEstudante(conteudoTelaAdm);
        if(!conteudoTelaAdm.nome.equals("")){
            if (!idJaExiste(conteudoTelaAdm.codigo)){
                mapeadorPessoa.put(estudante);
            }else{
                throw new MatriculaJahExisteException();
            }
        }else{
            throw new PrencheCampoNomeException();
        }    
    }    
            
    public void cadastraVisitante(ConteudoTelaAdm conteudoTelaAdm) throws MatriculaJahExisteException, PrencheCampoNomeException{
        Visitante visitante = desempacotaVisitante(conteudoTelaAdm);
        if(!conteudoTelaAdm.nome.equals("")){
            int codigo = geraID(); 
            visitante.setId(codigo);
            if (!idJaExiste(conteudoTelaAdm.codigo)){
                mapeadorPessoa.put(visitante);
            }else{
                throw new MatriculaJahExisteException();
            }
        }else{
            throw new PrencheCampoNomeException();
        }
    }
    
    public void excluirUsiario(int id) throws MatriculainvalidaException, Exception{
        if (idJaExiste(id)){
            Pessoa logado = ControladorPrincipal.getInstance().getControladorUsuarios().getPessoa();
            if(logado instanceof UsuarioUFSC && ((UsuarioUFSC) logado).getMatricula() != id){
                for (Pessoa pessoa: mapeadorPessoa.getList()){
                    if(pessoa instanceof Visitante){
                        if(((Visitante)pessoa).getId() == id){
                            if (pessoa.getSaldo()> 0){
                                mapeadorPessoa.remove(pessoa);
                                telaAdm.operacaoRealizada();
                                return;
                            }else{
                                mapeadorPessoa.remove(pessoa);
                                return;
                            }
                        }
                    }else{
                        if(((UsuarioUFSC)pessoa).getMatricula() == id){
                             if (pessoa.getSaldo()> 0){
                                mapeadorPessoa.remove(pessoa);
                                return;
                            }else{
                                mapeadorPessoa.remove(pessoa);
                                return;
                            }
                        }
                    }         
                }
            }else{
                throw new Exception("Não é possivel excluir o usuário logado!");
            }
        }else{
            throw new MatriculainvalidaException();
        }    
    }
  
    public void editarUsuarioUFSC(ConteudoTelaAdm conteudoTelaAdm, Pessoa pessoa) throws MatriculaJahExisteException{
        UsuarioUFSC usuarioUFSC = desempacotaUsuarioUFSC(conteudoTelaAdm);
        if(mapeadorPessoa.get(usuarioUFSC.getMatricula()) == null || mapeadorPessoa.get(usuarioUFSC.getMatricula()) == pessoa){
            mapeadorPessoa.remove(pessoa);
            mapeadorPessoa.put(usuarioUFSC);
        }else{
            throw new MatriculaJahExisteException();
        }
        mapeadorPessoa.load();
    }
    public void editarEstudante(ConteudoTelaAdm conteudoTelaAdm, Pessoa pessoa) throws MatriculaJahExisteException{
        Estudante estudante = desempacotaEstudante(conteudoTelaAdm);
        if(mapeadorPessoa.get(estudante.getMatricula()) == null || mapeadorPessoa.get(estudante.getMatricula()) == pessoa){
            mapeadorPessoa.remove(pessoa);
            mapeadorPessoa.put(estudante);
        }else{
            throw new MatriculaJahExisteException();
        }
        mapeadorPessoa.load();
    }
    public void editarVisitante(ConteudoTelaAdm conteudoTelaAdm, Pessoa pessoa) throws MatriculaJahExisteException{
        Visitante visitante = desempacotaVisitante(conteudoTelaAdm);
        if(mapeadorPessoa.get(visitante.getId()) == null || mapeadorPessoa.get(visitante.getId()) == pessoa){
            mapeadorPessoa.remove(pessoa);
            mapeadorPessoa.put(visitante);
        }else{
            throw new MatriculaJahExisteException();
        }
        mapeadorPessoa.put(visitante);
        mapeadorPessoa.load();
    }

    private Estudante desempacotaEstudante(ConteudoTelaAdm conteudoTelaAdm){
        return new Estudante (conteudoTelaAdm.nome,conteudoTelaAdm.codigo, conteudoTelaAdm.admin, conteudoTelaAdm.isencao);
    }
    
    private UsuarioUFSC desempacotaUsuarioUFSC(ConteudoTelaAdm conteudoTelaAdm){
        return new UsuarioUFSC (conteudoTelaAdm.nome,conteudoTelaAdm.codigo, conteudoTelaAdm.admin);
    }

    private Visitante desempacotaVisitante(ConteudoTelaAdm conteudoTelaAdm){
        return new Visitante (conteudoTelaAdm.codigo, conteudoTelaAdm.nome);
    }

    public int geraID (){
        int id = 0;
        while(idJaExiste(id) || id == 0){
            Random random = new Random();
            id = random.nextInt((999999 - 100000) + 1) + 100000;
        }
        return id;
    }

    public TelaAdm getTelaAdm() {
        return this.telaAdm;
    }
    
    public TelaAdmCadastro getTelaAdmCadastro() {
        return this.telaAdmCadastro;
    }
    
    public TelaAdmExcluir getTelaAdmExcluir() {
        return this.telaAdmExcluir;
    }
    public TelaAdmEditar getTelaAdmEditar() {
        return this.telaAdmEditar;
    }
    public TelaAdmListar getTelaAdmListar() {
        return this.telaAdmListar;
    }

    
    public boolean idJaExiste(int id){
        if( mapeadorPessoa.getList() != null){
            for (Pessoa pessoa: mapeadorPessoa.getList()){
                if(pessoa instanceof Visitante){
                    if(((Visitante)pessoa).getId() == id){
                        return true;
                    }
                }else{
                    if(((UsuarioUFSC) pessoa).getMatricula() == id){
                        return true;
                    }
                }
            }
        }
        return false;
    }    

    public ControladorPrincipal getControladorPrincipal() {
        return ControladorPrincipal.getInstance();
    }
    public void adicionarSaldo(ConteudoTelaAdm conteudoTela) throws MatriculainvalidaException{
        if (mapeadorPessoa.get(conteudoTela.codigo) != null){
            Pessoa pessoa = mapeadorPessoa.get(conteudoTela.codigo);
            pessoa.adicionarSaldo(conteudoTela.saldo);
            mapeadorPessoa.put(pessoa);
        } else{
            throw new MatriculainvalidaException();
        }   
    } 
    
    public void gerarRelatorioRu(){
        ControladorPrincipal.getInstance().getControladorRelatorioAdm().getTelaRelatorioAdm().mostraConteudoTela();
    }
    
    public void passarProximoDia(){
        System.out.println("-> PROXIMO DIA");
        ControladorPrincipal.getInstance().getRestaurante().proximoDia();
        TelaPadrao.setData(ControladorPrincipal.getInstance().dateToString(ControladorPrincipal.getInstance().diaAtual()));
        telaAdm.mostraTela();
        telaAdm.operacaoRealizada();
    }
    
    public void passarProximoMes(){
        System.out.println("-> PROXIMO MÊS");
        ControladorPrincipal.getInstance().getRestaurante().proximoMes();
        TelaPadrao.setData(ControladorPrincipal.getInstance().dateToString(ControladorPrincipal.getInstance().diaAtual()));
        telaAdm.mostraTela();
        telaAdm.operacaoRealizada();
    }
    
    public String diaAtual (){
        Date diaAtual = ControladorPrincipal.getInstance().diaAtual();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  
        String hoje = formato.format(diaAtual);
        return hoje;
    }
    
    public static ControladorAdm getInstance(){
        return (controladorAdm == null)? controladorAdm = new ControladorAdm() : controladorAdm;

    }
    public void mostraTela(){
        telaAdm.mostraConteudoTela();
        telaAdm.setVisible(true);
        
    }
    public void escondeTela(TelaPadrao tela){
        tela.getContentPane().removeAll();
        tela.setVisible(false);
    }
    
    public MapeadorPessoa getMapeadorPessoa(){
        return this.mapeadorPessoa;
    }
    
    public void chamaTelaAdmListar(){
        Object [][] dados = new Object[mapeadorPessoa.getList().size()][4];
        for(int i=0;i<mapeadorPessoa.getList().size();i++){
            Pessoa pessoa = mapeadorPessoa.getList().get(i);
            dados[i][0] = pessoa.getNome();
            dados[i][1] = pessoa.getClass().getSimpleName();
            if(pessoa instanceof Visitante){
                dados[i][2] = ((Visitante)pessoa).getId();
            }else{
                dados[i][2] = ((UsuarioUFSC)pessoa).getMatricula();
            }
            dados[i][3] = pessoa.getSaldo();
        }
        telaAdmListar.mostraConteudoTela(dados);
    }

    public void chamaTelaAdmCadastro(){
        telaAdmCadastro.mostraConteudoTela();
    }

    public void chamaTelaAdmEditar(int linha) throws NaoSelecionadoException{
        if (linha <0 ){
            throw new NaoSelecionadoException(); 
        }else{
            Pessoa pessoa = mapeadorPessoa.getList().get(linha);
            telaAdmEditar.mostraConteudoTela(pessoa);
        }    
    }

    public void chamaTelaAdmExcluir(int linha) throws NaoSelecionadoException{
        if (linha <0 ){
            throw new NaoSelecionadoException(); 
        }else{
            Pessoa pessoa = mapeadorPessoa.getList().get(linha);
            telaAdmExcluir.mostraConteudoTela(pessoa);
        }
    }

    public void chamaTelaAddSaldo(){
        telaAdmAddSaldo.mostraConteudoTela();
    }

    public void chamaTelaAdm(){
        telaAdm.mostraConteudoTela();
    }

    public int getMatricula(Pessoa pessoa){
        if(pessoa instanceof Visitante){
             return((Visitante)pessoa).getId();
        }else{
            return ((UsuarioUFSC)pessoa).getMatricula();
        }
    }
}
