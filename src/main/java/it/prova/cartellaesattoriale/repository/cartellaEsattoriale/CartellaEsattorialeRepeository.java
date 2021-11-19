package it.prova.cartellaesattoriale.repository.cartellaEsattoriale;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;


public interface CartellaEsattorialeRepeository  extends CrudRepository<CartellaEsattoriale, Long> {

	@Query("from CartellaEsattoriale c join fetch c.contribuente where f.id = ?1")
	CartellaEsattoriale findSingleCartellaEsattorialeEager(Long id);
	
	List<CartellaEsattoriale> findByDescrizione(String descrizione);
	
	@Query("select c from CartellaEsattoriale c join fetch c.contribuente")
	List<CartellaEsattoriale> findAllCartellaEsattorialeEager();
	
}
