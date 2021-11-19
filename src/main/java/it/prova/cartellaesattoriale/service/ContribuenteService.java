package it.prova.cartellaesattoriale.service;

import java.util.List;

import it.prova.cartellaesattoriale.model.Contribuente;


public interface ContribuenteService {
	
	List<Contribuente> listAllElements();
	
	List<Contribuente> listAllElementsEager();

	Contribuente caricaSingoloElemento(Long id);
	
	Contribuente caricaSingoloElementoConCartelle(Long id);

	Contribuente aggiorna(Contribuente registaInstance);

	Contribuente inserisciNuovo(Contribuente registaInstance);

	void rimuovi(Contribuente registaInstance);
	
	List<Contribuente> findByExample(Contribuente example);
	
	List<Contribuente> cercaByCognomeENomeILike(String term);
	
	Contribuente findByNomeAndCognome(String nome, String cognome);

}
