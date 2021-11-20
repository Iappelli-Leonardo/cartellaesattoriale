package it.prova.cartellaesattoriale;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;
import it.prova.cartellaesattoriale.model.Contribuente;
import it.prova.cartellaesattoriale.model.Stato;
import it.prova.cartellaesattoriale.service.CartellaEsattorialeService;
import it.prova.cartellaesattoriale.service.ContribuenteService;

@SpringBootApplication
public class CartellaesattorialeApplication implements CommandLineRunner {

	@Autowired
	CartellaEsattorialeService cartellaEsattorialeService;

	@Autowired
	ContribuenteService contribuenteService;

	public static void main(String[] args) {
		SpringApplication.run(CartellaesattorialeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String nome = "leonardo";
		String cognome = "Iappelli";

		Contribuente contribuente = contribuenteService.findByNomeAndCognome(nome, cognome);

		if (contribuente == null) {
			contribuente = new Contribuente("leonardo", "Iappelli",
					new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2001"), "ccdrlla201O", "Via roma 321");
			contribuenteService.inserisciNuovo(contribuente);
		}

		String descrizione = "facsimile";

		CartellaEsattoriale cartella = new CartellaEsattoriale(descrizione, 10000, Stato.IN_CONTENZIOSO, contribuente);
		if (cartellaEsattorialeService.findByDescrizione(descrizione) != null)
			cartellaEsattorialeService.inserisciNuovo(cartella);

	}
}