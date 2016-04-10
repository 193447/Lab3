package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab3.model.CorsoDAO;
import it.polito.tdp.lab3.model.StudenteDAO;
import it.polito.tdp.lab3.model.StudentiDelCorsoDAO;

public class DBConnect {
	
	private List<CorsoDAO> listaCorsi;
	private StudenteDAO s;
	private StudentiDelCorsoDAO sc;

	Statement st;
	Connection conn;
	String jdbcURL;

	
	public DBConnect() {
		listaCorsi=new LinkedList<CorsoDAO>();
		//listaStudenti=new LinkedList<StudenteDAO>();
		s=null;
	}
		
	public void Connessione(){
		try {
			
			jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root&password=";

			conn = DriverManager.getConnection(jdbcURL);

			st = conn.createStatement();
		}
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public void letturaCorsiDB(){
		try {

			String sql = "select * from  corso";

			ResultSet res = st.executeQuery(sql);
			
			while(res.next()){
				String nome=res.getString("nome");
				String codIns=res.getString("codIns");
				int crediti=res.getInt("crediti");
				int pd=res.getInt("pd");

				CorsoDAO cdao= new CorsoDAO(codIns,crediti,nome,pd);
				listaCorsi.add(cdao);
			}
			
			res.close();
			conn.close();			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		

		public void letturaStudentiDB(int mat){
			try {

				String sql = "select * from  studente where matricola="+mat;

				ResultSet res = st.executeQuery(sql);
				
				while(res.next()){
					String nome=res.getString("nome");
					String cognome=res.getString("cognome");
					int matricola=res.getInt("matricola");

					s= new StudenteDAO(matricola,nome,cognome,"");
				}
				
				res.close();
				conn.close();			

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


}
		
		public void letturaStudentiInCorsiDB(String value) {
			try {

				String sql = "select matricola from iscrizione where codins like '"+value+"'";

				ResultSet res = st.executeQuery(sql);
				
				sc= new StudentiDelCorsoDAO(value);

				while(res.next()){
					int matricola=res.getInt("matricola");
					
					sc.setListaStudentiCorso(matricola,"","","");
				}
					
					for(StudenteDAO sct:sc.getListaStudentiCorso()){

						Statement st1 = conn.createStatement();
						
						String sql1 = "select * from  studente where matricola like '"+sct.getMatricola()+"'";

						ResultSet res1 = st1.executeQuery(sql1);
						
						String nome=res1.getString("nome");
						String cognome=res1.getString("cognome");
						String cds=res1.getString("CDS");
						int mat=res1.getInt("matricola");
						
						sc.setListaStudentiCorso(mat, nome, cognome, cds);
						res1.close();
					}
					
				res.close();
				conn.close();			

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	public StudentiDelCorsoDAO getSc() {
			return sc;
		}

	public List<CorsoDAO> getListaCorsi() {
		return listaCorsi;
	}


	public StudenteDAO getS() {
		return s;
	}


	
	



}
