
	package it.polito.tdp.lab3.controller;

	import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab3.db.CorsoDAO;
import it.polito.tdp.lab3.db.StudenteDAO;
import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.Studente;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.TextArea;
	import javafx.scene.control.TextField;

	public class SegreteriaStudentiController {
		
		StudenteDAO sd;
		CorsoDAO cd;


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
	    	
	    	if(txtMatricola.getText().matches("[0-9]")){
	    	int matricola=Integer.parseInt(txtMatricola.getText());

	        sd.letturaStudentiDB(matricola,true);

	    	txtNome.setText(sd.getS().getNome());
	    	txtCognome.setText(sd.getS().getCognome());
	    	}
	    	else
	    		txtContent.setText("Errore la stringa inserita  deve essere un numero");


	    }

	    @FXML
	    void doCerca(ActionEvent event) {
	    	if(txtMatricola.getText().isEmpty()){
	    	Corso c=null;
	        for(Corso d:cd.getListaCorsi()){
	        	if(d.getNome().equals(comboBox.getValue())){
	        		c=d;
	    	        sd.letturaStudentiInCorsiDB(c);
	        }
	        }
	        String s1="";
	        for(Studente s:c.getListaStudentiInCorso()){
	        	s1+=s.toString()+"\n";
	        }
	        s1+=" "+sd.getJ();
	        txtContent.setText(s1);
	    	}
	    	 if(!txtMatricola.getText().isEmpty() && comboBox.getValue().equals(" ")){
		    	int matricola=Integer.parseInt(txtMatricola.getText());
	    		boolean flag=sd.letturaStudentiDB(matricola,false);
	    		
	    		if(flag==false){
	    		String s1="";
		        for(Corso c:sd.getS().getListaCorsiStudente()){
		        	s1+=c.toString()+"\n";
		        }
		        	txtContent.setText(s1);
		    	}
	    		else
	    			txtContent.setText("Errore:Studente inesistente");
	    	}
	    	if(!txtMatricola.getText().isEmpty() && !comboBox.getValue().equals(" ")) {
	    		Corso c=null;
		        for(Corso d:cd.getListaCorsi()){
		        	if(d.getNome().equals(comboBox.getValue())){
		        		c=d;
		        	}
		        }
	    		boolean flag=sd.PresenzaStudenteInCorso(c, Integer.parseInt(txtMatricola.getText()));
	    		Studente s=sd.letturaAttributiStudenteDB(Integer.parseInt(txtMatricola.getText()));
	    		if(flag==true){
	    			txtContent.setText("Errore:"+s.getNome()+" "+s.getCognome()+" "+"("+s.getMatricola()+")"+" "+"non iscritto al corso"+" "+c.getNome());
	    		}
	    		else
	    			txtContent.setText(s.getNome()+" "+s.getCognome()+" "+"("+s.getMatricola()+")"+" "+"iscritto al corso"+" "+c.getNome());

	    	}

	    }


	    @FXML
	    void doIscrivi(ActionEvent event) {
	    	
	    	Corso c=null;
	        for(Corso d:cd.getListaCorsi()){
	        	if(d.getNome().equals(comboBox.getValue())){
	        		c=d;	    	         
	        }
	        }
	        boolean f=sd.updateStudenteInCorsoDB(Integer.parseInt(txtMatricola.getText()),c);

	        Studente s=sd.letturaAttributiStudenteDB(Integer.parseInt(txtMatricola.getText()));
    		if(f==false){
    			txtContent.setText("Errore:"+s.getNome()+" "+s.getCognome()+" "+"("+s.getMatricola()+")"+" "+"è già iscritto al corso"+" "+c.getNome());
    		}
    		else
    			txtContent.setText(s.getNome()+" "+s.getCognome()+" "+"("+s.getMatricola()+")"+" "+"è stato iscritto al corso"+" "+c.getNome());


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

	        sd= new StudenteDAO();
			cd= new CorsoDAO();

	        
	        cd.letturaCorsiDB();
        	comboBox.getItems().add(" ");
	        for(Corso d:cd.getListaCorsi())
	        	comboBox.getItems().add(d.getNome());
	        comboBox.setValue(" ");
	    }
	}


