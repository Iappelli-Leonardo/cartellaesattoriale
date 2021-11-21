package it.prova.cartellaesattoriale.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cartellaesattoriale.model.Contribuente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContribuenteDTO {
	
	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotNull(message = "{dataDiNascita.notnull}")
	private Date dataDiNascita;
	
	@NotBlank(message = "{codiceFiscale.notblank}")
	private String codiceFiscale;
	
	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;
	
	private boolean contezioso;

	@JsonIgnoreProperties(value = { "contribuente" })
	private Set<CartellaEsattorialeDTO> cartelleEsattoriali = new HashSet<CartellaEsattorialeDTO>(0);

	public ContribuenteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContribuenteDTO(Long id, String nome, String cognome, Date dataDiNascita, String codiceFiscale,
			String indirizzo, Set<CartellaEsattorialeDTO> cartelleEsattoriali) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelleEsattoriali = cartelleEsattoriali;
	}

	public ContribuenteDTO(String nome, String cognome, Date dataDiNascita, String codiceFiscale, String indirizzo,
			Set<CartellaEsattorialeDTO> cartelleEsattoriali) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelleEsattoriali = cartelleEsattoriali;
	}

	public ContribuenteDTO(Long id, String nome, String cognome, Date dataDiNascita, String codiceFiscale,
			String indirizzo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public ContribuenteDTO(String nome, String cognome, Date dataDiNascita, String codiceFiscale, String indirizzo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Set<CartellaEsattorialeDTO> getCartelleEsattoriali() {
		return cartelleEsattoriali;
	}

	public void setCartelleEsattoriali(Set<CartellaEsattorialeDTO> cartelleEsattoriali) {
		this.cartelleEsattoriali = cartelleEsattoriali;
	}
	
	public boolean isContezioso() {
		return contezioso;
	}

	public void setContezioso(boolean contezioso) {
		this.contezioso = contezioso;
	}

	public Contribuente buildContribuenteModel() {
		return new Contribuente(this.id, this.nome, this.cognome, this.dataDiNascita, this.codiceFiscale, this.indirizzo);
	}
	
	public static ContribuenteDTO buildContribuenteDTOFromModel(Contribuente registaModel, boolean includeCartelleEsattoriali) {
		ContribuenteDTO result = new ContribuenteDTO(registaModel.getId(), registaModel.getNome(), 
				registaModel.getCognome(), registaModel.getDataDiNascita(), registaModel.getCodiceFiscale(),
				registaModel.getIndirizzo());
		
		if(includeCartelleEsattoriali)
			result.setCartelleEsattoriali(CartellaEsattorialeDTO.createCartellaEsattorialeDTOSetFromModelSet(registaModel.getCartelleEsattoriali(), false));
		return result;
	}
	
	public static List<ContribuenteDTO> createContribuenteDTOListFromModelList(List<Contribuente> modelListInput, boolean includeCartelleEsattoriali) {
		return modelListInput.stream().map(registaEntity -> {
			ContribuenteDTO result = ContribuenteDTO.buildContribuenteDTOFromModel(registaEntity,includeCartelleEsattoriali);
			if(includeCartelleEsattoriali)
				result.setCartelleEsattoriali(CartellaEsattorialeDTO.createCartellaEsattorialeDTOSetFromModelSet(registaEntity.getCartelleEsattoriali(), false));
			return result;
		}).collect(Collectors.toList());
	}
	
}
