package org.erp.distribution.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="ftarpaymentd")
public class FtArpaymentd {

	@EmbeddedId
	protected FtArpaymentdPK id;
	
	private Integer nourut;
	
	private Double cashamountpay;
	private Double returamountpay;
	private Double giroamountpay;
	private Double transferamountpay;
	private Double potonganamount;
	
	private Double kelebihanbayaramount;
	private Double subtotalpay;
	
	@ManyToOne
	@JoinColumn(name="refnopayment", referencedColumnName="refno")
	private FtArpaymenth ftarpaymenthBean;
	
	@ManyToOne
	@JoinColumn(name="refnosales", referencedColumnName="refno")
	private FtSalesh ftsaleshBean;
	

	@ManyToOne
	@JoinColumn(name="returBean", referencedColumnName="refno")
	private FtSalesh returBean;
	
	
	@ManyToOne
	@JoinColumn(name="refnoBukugiroBean", referencedColumnName="refno")
	private Bukugiro bukugiroBean;
	
	@ManyToOne
	@JoinColumn(name="refnoBukutransferBean", referencedColumnName="refno")
	private Bukutransfer bukutransferBean;
	
	public FtArpaymentdPK getId() {
		return id;
	}
	
	
	public Integer getNourut() {
		return nourut;
	}



	public Double getCashamountpay() {
		return cashamountpay;
	}




	public Double getGiroamountpay() {
		return giroamountpay;
	}



	public Double getTransferamountpay() {
		return transferamountpay;
	}



	public Double getPotonganamount() {
		return potonganamount;
	}



	public FtArpaymenth getFtarpaymenthBean() {
		return ftarpaymenthBean;
	}



	public FtSalesh getFtsaleshBean() {
		return ftsaleshBean;
	}



	public FtSalesh getReturBean() {
		return returBean;
	}



	public Bukugiro getBukugiroBean() {
		return bukugiroBean;
	}



	public Bukutransfer getBukutransferBean() {
		return bukutransferBean;
	}



	public void setId(FtArpaymentdPK id) {
		this.id = id;
	}



	public void setNourut(Integer nourut) {
		this.nourut = nourut;
	}



	public void setCashamountpay(Double cashamountpay) {
		this.cashamountpay = cashamountpay;
	}



	public Double getReturamountpay() {
		return returamountpay;
	}


	public void setReturamountpay(Double returamountpay) {
		this.returamountpay = returamountpay;
	}


	public void setGiroamountpay(Double giroamountpay) {
		this.giroamountpay = giroamountpay;
	}



	public void setTransferamountpay(Double transferamountpay) {
		this.transferamountpay = transferamountpay;
	}



	public void setPotonganamount(Double potonganamount) {
		this.potonganamount = potonganamount;
	}



	public void setFtarpaymenthBean(FtArpaymenth ftarpaymenthBean) {
		this.ftarpaymenthBean = ftarpaymenthBean;
	}



	public void setFtsaleshBean(FtSalesh ftsaleshBean) {
		this.ftsaleshBean = ftsaleshBean;
	}



	public void setReturBean(FtSalesh returBean) {
		this.returBean = returBean;
	}



	public void setBukugiroBean(Bukugiro bukugiroBean) {
		this.bukugiroBean = bukugiroBean;
	}



	public void setBukutransferBean(Bukutransfer bukutransferBean) {
		this.bukutransferBean = bukutransferBean;
	}



	public Double getKelebihanbayaramount() {
		return kelebihanbayaramount;
	}


	public void setKelebihanbayaramount(Double kelebihanbayaramount) {
		this.kelebihanbayaramount = kelebihanbayaramount;
	}


	public Double getSubtotalpay() {
		return subtotalpay;
	}


	public void setSubtotalpay(Double subtotalpay) {
		this.subtotalpay = subtotalpay;
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
		FtArpaymentd other = (FtArpaymentd) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}