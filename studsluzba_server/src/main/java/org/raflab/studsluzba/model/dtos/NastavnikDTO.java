package org.raflab.studsluzba.model.dtos;



public class NastavnikDTO {

	private Long id;
	private String ime;	  // not null
	private String prezime;  // not null
	private String srednjeIme;   // not null 
	private String email;   // not null
	private String brojTelefona;
	private String adresa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getSrednjeIme() {
		return srednjeIme;
	}
	public void setSrednjeIme(String srednjeIme) {
		this.srednjeIme = srednjeIme;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}	
	
	
	
	
}
