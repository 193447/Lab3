package it.polito.tdp.lab3.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


import it.polito.tdp.lab3.model.Corso;

public class CorsoDAO {
	
	List<Corso> listaCorsi=new LinkedList<Corso>();

	
	public void letturaCorsiDB(){
		try {

			Connection conn = DBConnect.getConnection();

			String sql = "select * from  corso";
			
			Statement st=conn.createStatement();
			
			ResultSet res = st.executeQuery(sql);
			
			while(res.next()){
				String nome=res.getString("nome");
				String codIns=res.getString("codIns");
				int crediti=res.getInt("crediti");
				int pd=res.getInt("pd");

				Corso cdao= new Corso(codIns,crediti,nome,pd);
				listaCorsi.add(cdao);
			}
			
			res.close();
			st.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Corso> getListaCorsi() {
		return listaCorsi;
	}
}
