package it.prova.cartellaesattoriale.repository.contribuente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.cartellaesattoriale.model.Contribuente;

public class CustomContribuenteRepositoryImpl implements CustomContribuenteRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Contribuente> findByExample(Contribuente example) {
		
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select c from Contribuente c where c.id = c.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" c.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" c.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (example.getDataDiNascita() != null) {
			whereClauses.add("c.dataDiNascita >= :dataDiNascita ");
			paramaterMap.put("dataDiNascita", example.getDataDiNascita());
		}
		if (StringUtils.isNotEmpty(example.getCodiceFiscale())) {
			whereClauses.add(" c.codiceFiscale  like :codiceFiscale ");
			paramaterMap.put("codiceFiscale", "%" + example.getCodiceFiscale() + "%");
		}
		if (StringUtils.isNotEmpty(example.getIndirizzo())) {
			whereClauses.add(" c.indirizzo like :indirizzo ");
			paramaterMap.put("indirizzo", "%" + example.getCognome() + "%");
		}
	
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Contribuente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Contribuente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
