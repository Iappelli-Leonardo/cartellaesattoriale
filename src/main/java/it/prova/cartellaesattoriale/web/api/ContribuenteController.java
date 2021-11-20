package it.prova.cartellaesattoriale.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import it.prova.cartellaesattoriale.dto.ContribuenteDTO;
import it.prova.cartellaesattoriale.model.Contribuente;
import it.prova.cartellaesattoriale.service.ContribuenteService;
import it.prova.cartellaesattoriale.web.api.exeption.AssociazionCartelleException;
import it.prova.cartellaesattoriale.web.api.exeption.ContribuenteNotFoundException;
import it.prova.cartellaesattoriale.web.api.exeption.IdNotNullForInsertException;

@RestController
@RequestMapping("api/contribuente")
public class ContribuenteController {
	
	@Autowired
	ContribuenteService contribuenteService;
	
	@GetMapping
	public List<ContribuenteDTO> getAll() {
		return ContribuenteDTO.createContribuenteDTOListFromModelList(contribuenteService.listAllElementsEager(), true);
	}

	@GetMapping("/{id}")
	public ContribuenteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Contribuente contribuente = contribuenteService.caricaSingoloElementoConCartelle(id);

		if (contribuente == null)
			throw new ContribuenteNotFoundException("Contribuente not found con id: " + id);

		return ContribuenteDTO.buildContribuenteDTOFromModel(contribuente, true);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ContribuenteDTO createNew(@Valid @RequestBody ContribuenteDTO contribuenteInput) {
		
		if(contribuenteInput.getId() != null)
			throw new IdNotNullForInsertException("Un id non è valido per la creazione!");

 		
		Contribuente contribuenteInserito = contribuenteService.inserisciNuovo(contribuenteInput.buildContribuenteModel());
		return ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteInserito, false);
	}

	@PutMapping("/{id}")
	public ContribuenteDTO update(@Valid @RequestBody ContribuenteDTO contribuenteInput, @PathVariable(required = true) Long id) {
		Contribuente contribuente = contribuenteService.caricaSingoloElemento(id);

		if (contribuente == null)
			throw new ContribuenteNotFoundException("Contribuente not found con id: " + id);

		contribuenteInput.setId(id);
		Contribuente contribuenteAggiornato = contribuenteService.aggiorna(contribuenteInput.buildContribuenteModel());
		return ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteAggiornato, false);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		Contribuente contribuente = contribuenteService.caricaSingoloElemento(id);

		if (contribuente == null)
			throw new ContribuenteNotFoundException("Contribuente not found con id: " + id);

		if (contribuente.getCartelleEsattoriali() != null)
			throw new AssociazionCartelleException("Ci sono ancora delle cartelleEsattoriali associate al contribuente!");
		
		contribuenteService.rimuovi(contribuente);
	}

	@PostMapping("/search")
	public List<ContribuenteDTO> search(@RequestBody ContribuenteDTO example) {
		return ContribuenteDTO.createContribuenteDTOListFromModelList(contribuenteService.findByExample(example.buildContribuenteModel()),
				false);
	}

}