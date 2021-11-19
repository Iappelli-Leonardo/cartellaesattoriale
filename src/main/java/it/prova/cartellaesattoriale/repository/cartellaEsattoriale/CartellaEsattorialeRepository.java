package it.prova.cartellaesattoriale.repository.cartellaEsattoriale;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;


public interface CartellaEsattorialeRepository  extends CrudRepository<CartellaEsattoriale, Long>, CustomCartellaEsattorialeRepository {

	@Query("from CartellaEsattoriale c join fetch c.contribuente where c.id = ?1")
	CartellaEsattoriale findSingleCartellaEsattorialeEager(Long id);
	
	@Query("select c from CartellaEsattoriale c join fetch c.contribuente")
	List<CartellaEsattoriale> findAllCartellaEsattorialeEager();
	
	List<CartellaEsattoriale> findByDescrizione(String descrizione);
	
}
