package it.prova.cartellaesattoriale.repository.cartellaEsattoriale;

import java.util.List;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;


public interface CustomCartellaEsattorialeRepository {
	List<CartellaEsattoriale> findByExample(CartellaEsattoriale example);
}
