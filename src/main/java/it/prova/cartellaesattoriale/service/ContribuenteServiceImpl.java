package it.prova.cartellaesattoriale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.cartellaesattoriale.model.Contribuente;
import it.prova.cartellaesattoriale.repository.contribuente.ContribuenteRepository;

@Service
public class ContribuenteServiceImpl implements ContribuenteService{

	@Autowired
	private ContribuenteRepository repository;
	
	@Override
	public List<Contribuente> listAllElements() {
		return (List<Contribuente>)repository.findAll();
	}

	@Override
	public List<Contribuente> listAllElementsEager() {
		return (List<Contribuente>)repository.findAllEager();
	}

	@Override
	public Contribuente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Contribuente caricaSingoloElementoConCartelle(Long id) {
		return repository.findByIdEager(id);
	}

	@Override
	public Contribuente aggiorna(Contribuente registaInstance) {
		return repository.save(registaInstance);
	}

	@Override
	public Contribuente inserisciNuovo(Contribuente registaInstance) {
		return repository.save(registaInstance);
	}

	@Override
	public void rimuovi(Contribuente registaInstance) {
		repository.delete(registaInstance);
	}

	@Override
	public List<Contribuente> findByExample(Contribuente example) {
		return repository.findByExample(example);
	}

	@Override
	public List<Contribuente> cercaByCognomeENomeILike(String term) {
		return repository.findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(term, term);
	}

	@Override
	public Contribuente findByNomeAndCognome(String nome, String cognome) {
		return repository.findByNomeAndCognome(nome, cognome);
	}

}
