package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;

/**
 * The persistent class for the BANK database table.
 * 
 */

@Entity
@Table(name="bank")
@NamedQuery(name="Bank.findAll", query="SELECT b FROM Bank b")
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Id
	@Column(name="IDBANK", length=20)
	private String idbank;

	@Column(name="DESCRIPTION", length=50)
	private String description;

	@Column(name="REKENING", length=50)
	private String rekening;
	
	@Column(name="GL")
	private boolean gl;
	
	
	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="bankBean")
	private Set<AccAccount> accounts;

	//bi-directional many-to-one association to Bukugiro
	@OneToMany(mappedBy="bankBean")
	private Set<Bukugiro> bukugiros;

	//bi-directional many-to-one association to Girotransfer
	@OneToMany(mappedBy="bankBean")
	private Set<Bukutransfer> girotransfers;

	public Bank() {
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Set<AccAccount> getAccounts() {
		return accounts;
	}


	public void setAccounts(Set<AccAccount> accounts) {
		this.accounts = accounts;
	}


	public String getIdbank() {
		return idbank;
	}


	public String getDescription() {
		return description;
	}


	public String getRekening() {
		return rekening;
	}


	public boolean isGl() {
		return gl;
	}


	public Set<Bukugiro> getBukugiros() {
		return bukugiros;
	}


	public Set<Bukutransfer> getGirotransfers() {
		return girotransfers;
	}


	public void setIdbank(String idbank) {
		this.idbank = idbank;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setRekening(String rekening) {
		this.rekening = rekening;
	}


	public void setGl(boolean gl) {
		this.gl = gl;
	}


	public void setBukugiros(Set<Bukugiro> bukugiros) {
		this.bukugiros = bukugiros;
	}


	public void setGirotransfers(Set<Bukutransfer> girotransfers) {
		this.girotransfers = girotransfers;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idbank == null) ? 0 : idbank.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		if (idbank == null) {
			if (other.idbank != null)
				return false;
		} else if (!idbank.equals(other.idbank))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return idbank + " - " + description;
	}

	
}