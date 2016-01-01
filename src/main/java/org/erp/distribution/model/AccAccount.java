package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;


/**
 * The persistent class for the ACCOUNT database table.
 * 
 */
@Entity
@Table(name="AccAccount")
public class AccAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Id
	@Column(name="ID", length=10)
	private String id;

	@Column(name="ACTUALBALANCE")
	private double actualbalance;

	@Size(min=2, max=50)
	@Column(name="NAME", length=50)
	private String name;

	@NotNull(message="Tidak boleh null atau kosong")
	@NotEmpty(message="Tidak boleh null atau kosong")
	@Column(name="TIPEDEBETKREDIT", length=10)
	private String tipedebetkredit;

	//bi-directional many-to-one association to Accountgroup
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="accountgroup")
	private AccAccountgroup accountgroup;

	//bi-directional many-to-one association to Bank
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bank")
	private Bank bankBean;

//	//bi-directional many-to-one association to Division
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="division")
//	private Division divisionBean;
//
//	//bi-directional many-to-one association to Accountbalance
//	@OneToMany(mappedBy="accountBean")
//	private Set<Accountbalance> accountbalances;
//
//	//bi-directional many-to-one association to Accountbalance
//	@OneToMany(mappedBy="accountBean")
//	private Set<Bkbdetail> bkbdetails;
//
//	//bi-directional many-to-one association to Accountbalance
//	@OneToMany(mappedBy="accountBean")
//	private Set<Bkkdetail> bkkdetails;
	

//	//bi-directional many-to-one association to Accountbalance
//	@OneToMany(mappedBy="accountBean")
//	private Set<Bbankdetail> bbankdetails;
//
//	//bi-directional many-to-one association to Accountbalance
//	@OneToMany(mappedBy="accountBean")
//	private Set<Bkbtranstype> bkbtranstypes;
//	
//	@OneToMany(mappedBy="accountBean")
//	private Set<Bkktranstype> bkktranstypes;
//	
//	@OneToMany(mappedBy="accountBean")
//	private Set<Bbanktranstype> bbanktranstypes;
	




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public double getActualbalance() {
		return actualbalance;
	}

	public String getName() {
		return name;
	}

	public String getTipedebetkredit() {
		return tipedebetkredit;
	}

	public AccAccountgroup getAccountgroup() {
		return accountgroup;
	}

	public Bank getBankBean() {
		return bankBean;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setActualbalance(double actualbalance) {
		this.actualbalance = actualbalance;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTipedebetkredit(String tipedebetkredit) {
		this.tipedebetkredit = tipedebetkredit;
	}

	public void setAccountgroup(AccAccountgroup accountgroup) {
		this.accountgroup = accountgroup;
	}

	public void setBankBean(Bank bankBean) {
		this.bankBean = bankBean;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccAccount other = (AccAccount) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " - " + name;
	}
	
	

}