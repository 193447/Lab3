
	package it.polito.tdp.lab3.controller;

	import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab3.db.DBConnect;
import it.polito.tdp.lab3.model.CorsoDAO;
import it.polito.tdp.lab3.model.StudenteDAO;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.TextArea;
	import javafx.scene.control.TextField;

	public class SegreteriaStudentiController {
		
		DBConnect dbc;


	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ComboBox<String> comboBox;

	    @FXML
	    private TextField txtMatricola;

	    @FXML
	    private Button btnAuto;

	    @FXML
	    private TextField txtNome;

	    @FXML
	    private TextField txtCognome;

	    @FXML
	    private Button btnCerca;

	    @FXML
	    private Button btnIscrivi;

	    @FXML
	    private TextArea txtContent;

	    @FXML
	    private Button btnReset;

	    @FXML
	    void doAuto(ActionEvent event) {
	    	
	    	int matricola=Integer.parseInt(txtMatricola.getText());
	        dbc.Connessione();
	        dbc.letturaStudentiDB(matricola);

	    	txtNome.setText(dbc.getS().getNome());
	    	txtCognome.setText(dbc.getS().getCognome());


	    }

	    @FXML
	    void doCerca(ActionEvent event) {

	        dbc.Connessione();
	        for(CorsoDAO d:dbc.getListaCorsi()){
	        	if(d.getNome().equals(comboBox.getValue()))
	    	        dbc.letturaStudentiInCorsiDB(d.getCodIns());
	        }
	        String s="";
	        for(StudenteDAO sc:dbc.getSc().getListaStudentiCorso()){
	        	s+=sc.toString()+"\n";
	        }
	        	txtContent.setText(s);

	    }

	    @FXML
	    void doIscrivi(ActionEvent event) {

	    }

	    @FXML
	    void doReset(ActionEvent event) {

	    }

	    @FXML
	    void initialize() {
	        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert btnAuto != null : "fx:id=\"btnAuto\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert txtContent != null : "fx:id=\"txtContent\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

	        dbc=new DBConnect();
	        
	        dbc.Connessione();
	        dbc.letturaCorsiDB();
        	comboBox.getItems().add(" ");
	        for(CorsoDAO d:dbc.getListaCorsi())
	        	comboBox.getItems().add(d.getNome());
	    }
	}


