package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab3.model.CorsoDAO;

public class DBConnect {
	
	private List<CorsoDAO> listaCorsi;

	
	public DBConnect() {
		listaCorsi=new LinkedList<CorsoDAO>();
	}
		
	
	public void letturaDB(){
		try {
			
			String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root&password=";

			Connection conn = DriverManager.getConnection(jdbcURL);

			Statement st = conn.createStatement();

			String sql = "select * from  corso";

			ResultSet res = st.executeQuery(sql);
			
			while(res.next()){
				String nome=res.getString("nome");
				String codIns=res.getString("codIns");
				int crediti=res.getInt("crediti");
				int pd=res.getInt("pd");

				CorsoDAO cdao= new CorsoDAO(codIns,crediti,nome,pd);
				listaCorsi.add(cdao);
				//m.add(cdao);
			}
			
			res.close();
			conn.close();			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


}


	public List<CorsoDAO> getListaCorsi() {
		return listaCorsi;
	}

}
