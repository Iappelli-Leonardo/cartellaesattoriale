package it.prova.cartellaesattoriale.repository.contribuente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.cartellaesattoriale.model.Contribuente;

public interface ContribuenteRepository extends CrudRepository<Contribuente, Long>{

	Contribuente findByNomeAndCognome(String nome, String cognome);

	List<Contribuente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String cognome,
			String nome);

	@Query("select distinct r from Contribuente r left join fetch c.cartelleEsattoriali ")
	List<Contribuente> findAllEager();

	@Query("from Contribuente r left join fetch r.cartelleEsattoriali where r.id=?1")
	Contribuente findByIdEager(Long idContribuente);
	
}
