package it.prova.cartellaesattoriale.service;

import java.util.List;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;


public interface CartellaEsattorialeService {

	List<CartellaEsattoriale> listAllElements(boolean eager);

	CartellaEsattoriale caricaSingoloElemento(Long id);

	CartellaEsattoriale caricaSingoloElementoEager(Long id);

	CartellaEsattoriale aggiorna(CartellaEsattoriale filmInstance);

	CartellaEsattoriale inserisciNuovo(CartellaEsattoriale filmInstance);

	void rimuovi(CartellaEsattoriale filmInstance);

	List<CartellaEsattoriale> findByExample(CartellaEsattoriale example);

	List<CartellaEsattoriale> findByDescrizione(String descrizione);

}
