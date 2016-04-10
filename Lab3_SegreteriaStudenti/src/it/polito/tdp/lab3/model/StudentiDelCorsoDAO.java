package it.polito.tdp.lab3.model;

import java.util.*;

public class StudentiDelCorsoDAO {
	
	private String corso;
	private Map<Integer,StudenteDAO> listaStudentiCorso;
	

	
	
	public StudentiDelCorsoDAO(String corso) {
		super();
		this.corso = corso;
		listaStudentiCorso=new TreeMap<Integer,StudenteDAO>();
	}


	public List<StudenteDAO> getListaStudentiCorso() {
		List<StudenteDAO> list=new LinkedList<StudenteDAO>(listaStudentiCorso.values());
		return list;
	}


	public void setListaStudentiCorso(int matricola,String nome,String cognome,String cds) {
		if(listaStudentiCorso.size()==0){
	    	this.listaStudentiCorso.put(matricola,new StudenteDAO(matricola,nome,cognome,cds));
		}
		else{
			if(listaStudentiCorso.containsKey(matricola)){
				this.listaStudentiCorso.remove(matricola);
				this.listaStudentiCorso.put(matricola,new StudenteDAO(matricola,nome,cognome,cds));
			}
	    	this.listaStudentiCorso.put(matricola,new StudenteDAO(matricola,nome,cognome,cds));
		}
	}
	
	
	
	
	

}
