package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the BUKUGIRO database table.
 * 
 */
@Entity
@Table(name="bukugiro")
@NamedQuery(name="Bukugiro.findAll", query="SELECT b FROM Bukugiro b")
public class Bukugiro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long refno;
	
	@Column(name="nasabah", length=50)
	private String nasabah;
	
	@Column(name="amount")
	private double amount;

	@Temporal(TemporalType.DATE)
	@Column(name="girodate")
	private Date girodate;

	@Temporal(TemporalType.DATE)
	@Column(name="giroduedate")
	private Date giroduedate;

	@Column(name="gironame", length=50)
	private String gironame;

	@Column(name="gironumber", length=50)
	private String gironumber;

	@Column(name="amountused")
	private double amountused;

	@OneToMany(mappedBy="bukugiroBean")
	private Set<FtArpaymentd> arpaymentdetails;
	
	//bi-directional many-to-one association to Bank
	@ManyToOne
	@JoinColumn(name="bank")
	private Bank bankBean;

//	//bi-directional many-to-one association to Division
//	@ManyToOne
//	@JoinColumn(name="division", referencedColumnName = "id")
//	private Division divisionBean;
	
	
//CONSTRUCTOR NEEDED	
	public Bukugiro() {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getRefno() {
		return refno;
	}

	public String getNasabah() {
		return nasabah;
	}

	public double getAmount() {
		return amount;
	}

	public Date getGirodate() {
		return girodate;
	}

	public Date getGiroduedate() {
		return giroduedate;
	}

	public String getGironame() {
		return gironame;
	}

	public String getGironumber() {
		return gironumber;
	}

	public double getAmountused() {
		return amountused;
	}

	public Set<FtArpaymentd> getArpaymentdetails() {
		return arpaymentdetails;
	}

	public Bank getBankBean() {
		return bankBean;
	}

	public void setRefno(Long refno) {
		this.refno = refno;
	}

	public void setNasabah(String nasabah) {
		this.nasabah = nasabah;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setGirodate(Date girodate) {
		this.girodate = girodate;
	}

	public void setGiroduedate(Date giroduedate) {
		this.giroduedate = giroduedate;
	}

	public void setGironame(String gironame) {
		this.gironame = gironame;
	}

	public void setGironumber(String gironumber) {
		this.gironumber = gironumber;
	}

	public void setAmountused(double amountused) {
		this.amountused = amountused;
	}

	public void setArpaymentdetails(Set<FtArpaymentd> arpaymentdetails) {
		this.arpaymentdetails = arpaymentdetails;
	}

	public void setBankBean(Bank bankBean) {
		this.bankBean = bankBean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((refno == null) ? 0 : refno.hashCode());
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
		Bukugiro other = (Bukugiro) obj;
		if (refno == null) {
			if (other.refno != null)
				return false;
		} else if (!refno.equals(other.refno))
			return false;
		return true;
	}

	
}