package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;


public class StudenteDAO {
	
	private Studente s=null;
	private Corso c=null;
	private int j=1;

	

	public boolean letturaStudentiDB(int mat,boolean toc){
		boolean flag=false;
		
			Connection conn = DBConnect.getConnection();

			String sql = "select * from  studente where matricola=?;";

			PreparedStatement st;
	  try {
				st = conn.prepareStatement(sql);

				st.setInt(1,mat);
				
			ResultSet res = st.executeQuery();
				
			if(res.first()==false){
				flag=true;
			}
			else{				
				String nome=res.getString("nome");
				String cognome=res.getString("cognome");
				int matricola=res.getInt("matricola");
				String cds=res.getString("CDS");
				
				s= new Studente(matricola,nome,cognome,cds);
				if(toc==false)
					this.leggiCorsiStudente(s);
				}
			
			res.close();
			st.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;


}
	
	private void leggiCorsiStudente(Studente s2)  {
			Connection conn = DBConnect.getConnection();

			String sql = "select codIns from  iscrizione where matricola=?;";

			PreparedStatement st;
			  try {
						st = conn.prepareStatement(sql);

						st.setInt(1,s2.getMatricola());
						
			ResultSet res = st.executeQuery();
			
			List<String> list=new LinkedList<String>();

			while(res.next()){
				String codIns=res.getString("codIns");
				
				list.add(codIns);
			}
			
			for(String i:list){					
				
				String sql1 = "select * from  corso where codIns=?;";

				st = conn.prepareStatement(sql1);
				
				st.setString(1,i);

				res = st.executeQuery();
				
				while(res.next()){

					String nome=res.getString("nome");
					int crediti=res.getInt("crediti");
					String codIns=res.getString("codIns");
					int pd=res.getInt("pd");
					
					c= new Corso(codIns,crediti,nome,pd);
					s2.setListaCorsiStudente(c);					
				}
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}

	public void letturaStudentiInCorsiDB(Corso value) {
			Connection conn = DBConnect.getConnection();

			String sql = "select matricola from iscrizione where codins=?;";

			PreparedStatement st;
			  try {
						st = conn.prepareStatement(sql);

						st.setString(1,value.getCodIns());
						
			ResultSet res = st.executeQuery();
			
			List<Integer> list=new LinkedList<Integer>();
			
			while(res.next()){
				int matricola=res.getInt("matricola");
				
				list.add(matricola);
			}
			
			
			for(Integer i:list){					
				
				String sql1 = "select * from  studente where matricola=?;";

				st = conn.prepareStatement(sql1);
				
				st.setInt(1,i);
				
				res = st.executeQuery();
				
				while(res.next()){

					String nome=res.getString("nome");
					String cognome=res.getString("cognome");
					String cds=res.getString("CDS");
					int mat=res.getInt("matricola");
					
					s= new Studente(mat,nome,cognome,cds);
					value.setListaStudentiInCorso(s);					
				}
				j++;
				}

			res.close();
			st.close();
			conn.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean PresenzaStudenteInCorso(Corso d,Integer s) {
		
		boolean flag=false;
			Connection conn = DBConnect.getConnection();

			String sql = "select matricola from iscrizione where codins=? && matricola=?";

			PreparedStatement st;
			  try {
						st = conn.prepareStatement(sql);
						
						st.setString(1, d.getCodIns());
						st.setInt(2,s);
						
			ResultSet res = st.executeQuery();
			
			if(res.first()==false){
				flag=true;
			}
			else
				flag=false;
			res.close();
			st.close();
			conn.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public Studente letturaAttributiStudenteDB(int mat){
			Connection conn = DBConnect.getConnection();

			String sql = "select * from  studente where matricola=?";

			PreparedStatement st;
			  try {
						st = conn.prepareStatement(sql);

						st.setInt(1,mat);
						
			ResultSet res = st.executeQuery();
				
			while (res.next()){				
				String nome=res.getString("nome");
				String cognome=res.getString("cognome");
				int matricola=res.getInt("matricola");
				String cds=res.getString("CDS");
				
				s= new Studente(matricola,nome,cognome,cds);
				}
			
			res.close();
			st.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public Studente getS() {
		return s;
	}

	public int getJ() {
		return j;
	}

	public boolean updateStudenteInCorsoDB(int mat,Corso c2) {
		boolean flag=false;
		
		Connection conn = DBConnect.getConnection();

		String sql = "INSERT INTO iscrizione (matricola, codIns) VALUES (?, ?);";

		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);

			st.setInt(1,mat);
			st.setString(2,c2.getCodIns());

			int r=st.executeUpdate();
			
			if(r!=0){
				flag=true;
			}
			
			st.close();
			conn.close();
			


	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
	}
	
	
	


}
