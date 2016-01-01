package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the GIROTRANSFER database table.
 * 
 */
@Entity
@Table(name="bukutransfer")
@NamedQuery(name="Bukutransfer.findAll", query="SELECT g FROM Bukutransfer g")
public class Bukutransfer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long refno;
	
	@Column(name="transfernumber", length=10)
	private String transfernumber;

	@Column(name="nasabah", length=50)
	private String nasabah;
	
	@Column(name="amount")
	private double amount;

	@Column(name="amountused")
	private double amountused;

	@Temporal(TemporalType.DATE)
	@Column(name="transferdate")
	private Date transferdate;

	@OneToMany(mappedBy="bukutransferBean",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
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
	public Bukutransfer() {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getRefno() {
		return refno;
	}

	public String getTransfernumber() {
		return transfernumber;
	}

	public String getNasabah() {
		return nasabah;
	}

	public double getAmount() {
		return amount;
	}

	public double getAmountused() {
		return amountused;
	}

	public Date getTransferdate() {
		return transferdate;
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

	public void setTransfernumber(String transfernumber) {
		this.transfernumber = transfernumber;
	}

	public void setNasabah(String nasabah) {
		this.nasabah = nasabah;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setAmountused(double amountused) {
		this.amountused = amountused;
	}

	public void setTransferdate(Date transferdate) {
		this.transferdate = transferdate;
	}

	public void setArpaymentdetails(Set<FtArpaymentd> arpaymentdetails) {
		this.arpaymentdetails = arpaymentdetails;
	}

	public void setBankBean(Bank bankBean) {
		this.bankBean = bankBean;
	}


}