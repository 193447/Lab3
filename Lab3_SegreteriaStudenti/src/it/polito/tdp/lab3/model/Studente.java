package it.polito.tdp.lab3.model;

import java.util.LinkedList;
import java.util.List;

public class Studente {
	private int matricola;
	private String nome;
	private String cognome;
	private String CDS;
	
	List<Corso> listaCorsiStudente;


	

	public Studente(int matricola, String nome, String cognome, String cDS) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		CDS = cDS;
		listaCorsiStudente=new LinkedList<Corso>();

	}

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCDS() {
		return CDS;
	}

	public void setCDS(String cDS) {
		CDS = cDS;
	}
	
	@Override
	public String toString() {
		return " "+matricola+" "+ nome +" "+  cognome +" "+  CDS ;
	}

	public List<Corso> getListaCorsiStudente() {
		return listaCorsiStudente;
	}

	public void setListaCorsiStudente(Corso c) {
		listaCorsiStudente.add(c);
	}
	
	
}
