/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */
package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCercaIscrittoCorsi;

    @FXML
    private Button btnCompletamento;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnReset;
    
    @FXML
    private Button btnCerca;

    @FXML
    private ComboBox<Corso> cmbCorsi;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void premiCercaCorsi(ActionEvent event) {
    	txtRisultato.setText("");
    	String input= txtMatricola.getText();
    	int iNum=0;
    	
    	try {
        	iNum= Integer.parseInt(input);
		} catch (Exception e) {
			
			txtRisultato.setText("Il valore inserito non è un intero");
			return;
		}
    	
    	Studente s= model.getStudente(iNum);
    	
    	if(s==null) {
    		txtRisultato.setText("La matricola inserita non corrisponde a nessuno studente!");
    		return;
    	}
    	else {
    		if(model.getCorsiPerMatrciola(iNum).isEmpty()) {
    			txtRisultato.setText("La matricola selezionata non frequenta nessun corso");
    			return;
    		}
    		for(Corso c: model.getCorsiPerMatrciola(iNum)) {
    			txtRisultato.appendText("" + c.getCodins()+" "+c.getCrediti()+" "+c.getNome()+" "+c.getPd()+"\n");
    		}
    	}

    }

    @FXML
    void premiCercaIscrittoCorsi(ActionEvent event) {
    	txtRisultato.setText("");
    	Corso c= cmbCorsi.getValue();
    	
    	if(c.getCodins().compareTo("")==0 || model.getStudentiIscrittiAlCorso(c).isEmpty()) {
    		txtRisultato.setText("Errore nessun corso selezionato o corso vuoto");
    		return;
    	}
    	
    	for(Studente s: model.getStudentiIscrittiAlCorso(c)){
    		txtRisultato.appendText("" + s.getMatricola() + " " + s.getNome() + " " + s.getCognome() + " " + s.getCDS()+"\n");
    	}
    }

    @FXML
    void premiCompletamento(ActionEvent event) {
    	String input= txtMatricola.getText();
    	txtNome.setText("");
		txtCognome.setText("");
		txtRisultato.setText("");
    	
    	int iNum=0;
    	
    	try {
        	iNum= Integer.parseInt(input);
		} catch (Exception e) {
			
			txtRisultato.setText("Il valore inserito non è un intero");
			return;
		}
    	
    	Studente s= model.getStudente(iNum);
    	
    	if(s==null) {
    		txtRisultato.setText("La matricola inserita non corrisponde a nessuno studente!");
    		return;
    	}
    	else {
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    		return;
    	}
    }

    @FXML
    void premiIscrivi(ActionEvent event) {
    	String input= txtMatricola.getText();
    	Corso c= cmbCorsi.getValue();
    	txtNome.setText("");
		txtCognome.setText("");
		txtRisultato.setText("");
    	
    	int iNum=0;
    	
    	try {
        	iNum= Integer.parseInt(input);
		} catch (Exception e) {
			
			txtRisultato.setText("Il valore inserito non è un intero");
			return;
		}
    	
    	Studente s= model.getStudente(iNum);
    	
    	if(s==null) {
    		txtRisultato.setText("La matricola inserita non corrisponde a nessuno studente!");
    		return;
    	}
    	else if(c.getCodins().compareTo("")==0) {
    		txtRisultato.setText("Nessun corso selezionato");
    		return;
    		
    	}
    	else {
    		if(model.inscriviStudenteACorso(s, c)) {
    			txtRisultato.setText("Studente già iscritto a questo corso");
    			return;
    		}
    		else {
    			model.putMatricolaNelCorso(c, s);
    			txtRisultato.setText("Studente iscritto al corso");
    			return;
    		}
    	}
    	
    	
    	
			

    }

    @FXML
    void premiReset(ActionEvent event) {
    	txtCognome.setText("");
    	txtMatricola.setText("");
    	txtNome.setText("");
    	txtRisultato.setText("");

    }
    
    @FXML
    void premiCerca(ActionEvent event) {
    	String input= txtMatricola.getText();
    	Corso c= cmbCorsi.getValue();
    	txtNome.setText("");
		txtCognome.setText("");
		txtRisultato.setText("");
    	
    	int iNum=0;
    	
    	try {
        	iNum= Integer.parseInt(input);
		} catch (Exception e) {
			
			txtRisultato.setText("Il valore inserito non è un intero");
			return;
		}
    	
    	Studente s= model.getStudente(iNum);
    	
    	if(s==null) {
    		txtRisultato.setText("La matricola inserita non corrisponde a nessuno studente!");
    		return;
    	}
    	else if(c.getCodins().compareTo("")==0) {
    		txtRisultato.setText("Nessun corso selezionato");
    		return;
    		
    	}
    	else {
    		if(model.inscriviStudenteACorso(s, c)) {
    			txtRisultato.setText("Studente già iscritto a questo corso");
    			return;
    		}
    	}
    	txtRisultato.setText("Studente non iscritto al corso");
    }

    @FXML
    void initialize() {
    	assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittoCorsi != null : "fx:id=\"btnCercaIscrittoCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCompletamento != null : "fx:id=\"btnCompletamento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		cmbCorsi.getItems().add(new Corso("", 0, "", 0));
		cmbCorsi.getItems().addAll( model.getTuttiICorsi());
		
	}

}

