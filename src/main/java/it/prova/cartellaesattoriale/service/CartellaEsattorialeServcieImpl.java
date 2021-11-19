package it.prova.cartellaesattoriale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;
import it.prova.cartellaesattoriale.repository.cartellaEsattoriale.CartellaEsattorialeRepository;

@Service
public class CartellaEsattorialeServcieImpl implements CartellaEsattorialeService{

	@Autowired
	private CartellaEsattorialeRepository repository;
	
	@Override
	public List<CartellaEsattoriale> listAllElements(boolean eager) {
		if (eager)
			return (List<CartellaEsattoriale>) repository.findAllCartellaEsattorialeEager();

		return (List<CartellaEsattoriale>) repository.findAll();
	}

	@Override
	public CartellaEsattoriale caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public CartellaEsattoriale caricaSingoloElementoEager(Long id) {
		return repository.findSingleCartellaEsattorialeEager(id);
	}

	@Override
	public CartellaEsattoriale aggiorna(CartellaEsattoriale filmInstance) {
		return repository.save(filmInstance);
	}

	@Override
	public CartellaEsattoriale inserisciNuovo(CartellaEsattoriale filmInstance) {
		return repository.save(filmInstance);
	}

	@Override
	public void rimuovi(CartellaEsattoriale filmInstance) {
		repository.delete(filmInstance);
	}

	@Override
	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example) {
		return this.listAllElements(false);
	}

	@Override
	public List<CartellaEsattoriale> findByDescrizione(String descrizione) {
		return repository.findByDescrizione(descrizione);
	}

}
