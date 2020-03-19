package it.polito.tdp.indovinanumeroMVC.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {

	private final int NMAX = 100;
	private final int TMAX = 10;
	private int segreto;
	private int tentativiUtente;
	private boolean inGioco;
	
	private Set<Integer> tentativi;
	
	public Model() {
		this.inGioco = false;
		this.tentativiUtente = 0;
	}
	
	public void nuovaPartita() {
    	this.segreto = (int)(Math.random() * NMAX + 1);
    	this.tentativiUtente = 0;
    	this.inGioco = true;
    	this.tentativi = new HashSet<Integer>();
	}
	
	public int tentativo(int _tentativo) {
		
		// Controllo se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è già terminata");
		}
		
		// Controllo Input
		if(!tentativoValido(_tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero nuovo, compreso tra 1 e " + NMAX);
		}
		
		// Il tentativo è valido ---->
    	tentativiUtente++;
    	this.tentativi.add(_tentativo);
    	
    	if(tentativiUtente == TMAX) {
    		this.inGioco = false;
    		return 99;
    	}
    	
    	if(_tentativo == this.segreto) {
    		this.inGioco = false;
    		return 0;
    	}
    	
    	if(_tentativo < this.segreto) {
    		return -1;
    	} else {
    		return 1;
    	}
		
	}
	
	private boolean tentativoValido(int _tentativo) {
		
		if(_tentativo < 1 || _tentativo > NMAX) {
			return false;
		} else {
			
			if(this.tentativi.contains(_tentativo)){
				return false;
			}
			
			return true;
		}
	}

	public int getTMAX() {
		return TMAX;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiUtente() {
		return tentativiUtente;
	}
	
	public int getTentativiUtenteRimasti() {
		return TMAX - tentativiUtente;
	}
	
}
