package org.erp.distribution.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

//@Entity
//@Table(name="fcustomerspecdisc")
public class FCustomerSpecDisc {

	@EmbeddedId
	private FCustomerSpecDiscPK  id;
	
	@Column(name="NOURUT")
	private Integer nourut;
	
	@Column(name="DISC1")
	private Double disc1;
	
	@Column(name="QTYMIN")
	private Double qtymin;

	@Column(name="DATEBERLAKUFROM")
	private Date dateberlakufrom;
	@Column(name="DATEBERLAKUTO")
	private Date dateberlakuto;

	@ManyToOne
	@JoinColumn(name="idCust", referencedColumnName="id", insertable=true, updatable=true)
	private FCustomer  fCustomerBean;
	
	@ManyToOne
	@JoinColumn(name="idProduct", referencedColumnName="id", insertable=true, updatable=true)
	private FProduct fproductBean;

	public FCustomerSpecDiscPK getId() {
		return id;
	}

	public Integer getNourut() {
		return nourut;
	}

	public Double getDisc1() {
		return disc1;
	}

	public Double getQtymin() {
		return qtymin;
	}

	public Date getDateberlakufrom() {
		return dateberlakufrom;
	}

	public Date getDateberlakuto() {
		return dateberlakuto;
	}

	public FCustomer getfCustomerBean() {
		return fCustomerBean;
	}

	public FProduct getFproductBean() {
		return fproductBean;
	}

	public void setId(FCustomerSpecDiscPK id) {
		this.id = id;
	}

	public void setNourut(Integer nourut) {
		this.nourut = nourut;
	}

	public void setDisc1(Double disc1) {
		this.disc1 = disc1;
	}

	public void setQtymin(Double qtymin) {
		this.qtymin = qtymin;
	}

	public void setDateberlakufrom(Date dateberlakufrom) {
		this.dateberlakufrom = dateberlakufrom;
	}

	public void setDateberlakuto(Date dateberlakuto) {
		this.dateberlakuto = dateberlakuto;
	}

	public void setfCustomerBean(FCustomer fCustomerBean) {
		this.fCustomerBean = fCustomerBean;
	}

	public void setFproductBean(FProduct fproductBean) {
		this.fproductBean = fproductBean;
	}

	

}