package it.polito.tdp.lab3.model;

import java.util.LinkedList;
import java.util.List;

public class Corso {

	
	String codIns;
	int crediti;
	String nome;
	int pd;
	
	List<Studente> listaStudentiInCorso;

	public Corso(String codIns, int crediti, String nome, int pd) {
		this.codIns = codIns;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
		listaStudentiInCorso=new LinkedList<Studente>();
	}
	
	public String getCodIns() {
		return codIns;
	}

	public void setCodIns(String codIns) {
		this.codIns = codIns;
	}

	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPd() {
		return pd;
	}

	public void setPd(int pd) {
		this.pd = pd;
	}
	
	public List<Studente> getListaStudentiInCorso() {
		return listaStudentiInCorso;
	}

	public void setListaStudentiInCorso(Studente s) {
		listaStudentiInCorso.add(s);
	}
	
	@Override
	public String toString() {
		return " " + codIns + " " + crediti + " " + nome + " " +" pd:"+ pd ;
	}


	
	

}
