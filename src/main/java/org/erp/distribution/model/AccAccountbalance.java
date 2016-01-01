package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the ACCOUNTBALANCE database table.
 * 
 */
@Entity
@Table(name="accountbalance")
public class AccAccountbalance implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AccAccountbalancePK id;


	@Column(name="YEAR")
	private int year;
	@Column(name="PERIODE")
	private int periode;
	
	@Column(name="FYEAR")
	private int fyear;
	@Column(name="FPERIODE")
	private int fperiode;	
	@Column(name="FWEEK")
	private int fweek;
	@Column(name="FDAY")
	private int fday;
	
	@Column(name="AMOUNT")
	private double amount;
	
	@Column(name="AMOUNTDEBET")
	private double amountDebet;
	@Column(name="AMOUNTKREDIT")	
	private double amountKredit;
	
	@Temporal(TemporalType.DATE)
	@Column(name="LASTREVISIONDATE")
	private Date lastrevisiondate;

	@Column(name="LASTREVISIONUSERID", length=10)
	private String lastrevisionuserid;

	@Column(name="REVISIONCOUNTER")
	private int revisioncounter;

	//bi-directional many-to-one association to Account
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="account")
	private AccAccount accountBean;

	
//	//bi-directional many-to-one association to Division
//	@ManyToOne
//	@JoinColumn(name="division", referencedColumnName = "id")
//	private Division divisionBean;
	
	
	public AccAccountbalance() {
	}

	public AccAccountbalancePK getId() {
		return this.id;
	}

	public void setId(AccAccountbalancePK id) {
		this.id = id;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPeriode() {
		return periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}

	public int getFyear() {
		return fyear;
	}

	public void setFyear(int fyear) {
		this.fyear = fyear;
	}

	public int getFperiode() {
		return fperiode;
	}

	public void setFperiode(int fperiode) {
		this.fperiode = fperiode;
	}

	public int getFweek() {
		return fweek;
	}

	public void setFweek(int fweek) {
		this.fweek = fweek;
	}

	public int getFday() {
		return fday;
	}

	public void setFday(int fday) {
		this.fday = fday;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmountDebet() {
		return amountDebet;
	}

	public void setAmountDebet(double amountDebet) {
		this.amountDebet = amountDebet;
	}

	public double getAmountKredit() {
		return amountKredit;
	}

	public void setAmountKredit(double amountKredit) {
		this.amountKredit = amountKredit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getLastrevisiondate() {
		return this.lastrevisiondate;
	}

	public void setLastrevisiondate(Date lastrevisiondate) {
		this.lastrevisiondate = lastrevisiondate;
	}

	public String getLastrevisionuserid() {
		return this.lastrevisionuserid;
	}

	public void setLastrevisionuserid(String lastrevisionuserid) {
		this.lastrevisionuserid = lastrevisionuserid;
	}

	public int getRevisioncounter() {
		return this.revisioncounter;
	}

	public void setRevisioncounter(int revisioncounter) {
		this.revisioncounter = revisioncounter;
	}

	public AccAccount getAccountBean() {
		return this.accountBean;
	}

	public void setAccountBean(AccAccount accountBean) {
		this.accountBean = accountBean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AccAccountbalance other = (AccAccountbalance) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}