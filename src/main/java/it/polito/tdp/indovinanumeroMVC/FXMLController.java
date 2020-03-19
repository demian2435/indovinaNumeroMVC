package it.polito.tdp.indovinanumeroMVC;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.indovinanumeroMVC.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_inizia;

    @FXML
    private Label lbl_nTentativi;
    
    @FXML
    private HBox layout_prova;

    @FXML
    private Button btn_prova;

    @FXML
    private TextField txt_prova;

    @FXML
    private Label lbl_risposta;

    @FXML
    void click_inizia(ActionEvent event) {
    	// Informa Model
    	this.model.nuovaPartita();
    	// Gestione interfaccia
    	layout_prova.setDisable(false);
    	txt_prova.setText("");
    	lbl_risposta.setText("");
    	lbl_nTentativi.setText(Integer.toString(this.model.getTentativiUtenteRimasti()));
    }

    @FXML
    void click_prova(ActionEvent event) {
    	// Leggere input utente
    	String prova = txt_prova.getText();
    	int provaInt;
    	try {
    	provaInt = Integer.parseInt(prova);
    	} catch (NumberFormatException e) {
    		lbl_risposta.setText("Inserisci un numero!");
    		return;
    	}
    	
    	int risultato;
    	try {
    		risultato = this.model.tentativo(provaInt);
    	} catch (IllegalStateException se){
    		lbl_risposta.setText(se.getMessage());
    		return;
    	} catch (InvalidParameterException pe) {
    		lbl_risposta.setText(pe.getMessage());
    		return;
    	};
    	
    	lbl_nTentativi.setText(Integer.toString(this.model.getTentativiUtenteRimasti()));

    	if(risultato == 0) {
    		// Indovinato il Numero
    		lbl_risposta.setText("Hai indovinato con " + this.model.getTentativiUtente() + " tentativi");
        	layout_prova.setDisable(true);
    		return;
    	}
    	
    	if(risultato == 99) {
    		// Finito i tentativi
    		lbl_risposta.setText("Hai Perso, il numero era " + this.model.getSegreto());
        	layout_prova.setDisable(true);
    		return;
    	}
    	
    	if(risultato == -1) {
    		// Numero piccolo
    		lbl_risposta.setText("Numero troppo piccolo");
    	} else {
    		// Numero grande
    		lbl_risposta.setText("Numero troppo grande");
    	}
    }

    @FXML
    void initialize() {
        assert btn_inizia != null : "fx:id=\"btn_inizia\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lbl_nTentativi != null : "fx:id=\"lbl_nTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btn_prova != null : "fx:id=\"btn_prova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txt_prova != null : "fx:id=\"txt_prova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lbl_risposta != null : "fx:id=\"lbl_risposta\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel (Model model) {
    	this.model = model;
    }
}
