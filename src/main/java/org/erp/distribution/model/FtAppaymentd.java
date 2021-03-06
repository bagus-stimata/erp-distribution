package org.erp.distribution.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="ftappaymentd")
public class FtAppaymentd {

	@EmbeddedId
	protected FtAppaymentdPK id;
	
	@Column(name="NOURUT")
	private Integer nourut;
	
	@Column(name="CASHAMOUNTPAY")
	private Double cashamountpay;

	@Column(name="MRVAMOUNTPAY")
	private Double mrvamountpay;
	@Column(name="DCVAMOUNTPAY")
	private Double dcvamountpay;
	
	@Column(name="GIROAMOUNTPAY")
	private Double giroamountpay;
	@Column(name="TRANSFERAMOUNTPAY")
	private Double transferamountpay;
	
	@Column(name="KELEBIHANBAYARAMOUNT")
	private Double kelebihanbayaramount;
	@Column(name="SUBTOTALPAY")
	private Double subtotalpay;
	
	@ManyToOne
	@JoinColumn(name="refnopayment", referencedColumnName="refno")
	private FtAppaymenth ftappaymenthBean;
	
	@ManyToOne
	@JoinColumn(name="refnopurchase", referencedColumnName="refno")
	private FtPurchaseh ftpurchasehBean;
	

	@ManyToOne
	@JoinColumn(name="mrvBean", referencedColumnName="refno")
	private FtPurchaseh mrvBean;
	
	
//	@ManyToOne
//	@JoinColumn(name="refnoBukugiroBean", referencedColumnName="refno")
//	private Bukugiro bukugiroBean;
//	
//	@ManyToOne
//	@JoinColumn(name="refnoBukutransferBean", referencedColumnName="refno")
//	private Bukutransfer bukutransferBean;
	


	
	public FtPurchaseh getMrvBean() {
		return mrvBean;
	}


	public void setMrvBean(FtPurchaseh mrvBean) {
		this.mrvBean = mrvBean;
	}


	public FtAppaymentdPK getId() {
		return id;
	}
	public Integer getNourut() {
		return nourut;
	}
	public Double getCashamountpay() {
		return cashamountpay;
	}
	public Double getMrvamountpay() {
		return mrvamountpay;
	}
	public Double getDcvamountpay() {
		return dcvamountpay;
	}
	public Double getGiroamountpay() {
		return giroamountpay;
	}
	public Double getTransferamountpay() {
		return transferamountpay;
	}
	public Double getKelebihanbayaramount() {
		return kelebihanbayaramount;
	}
	public Double getSubtotalpay() {
		return subtotalpay;
	}
	public FtAppaymenth getFtappaymenthBean() {
		return ftappaymenthBean;
	}
	public FtPurchaseh getFtpurchasehBean() {
		return ftpurchasehBean;
	}
	public void setId(FtAppaymentdPK id) {
		this.id = id;
	}
	public void setNourut(Integer nourut) {
		this.nourut = nourut;
	}
	public void setCashamountpay(Double cashamountpay) {
		this.cashamountpay = cashamountpay;
	}
	public void setMrvamountpay(Double mrvamountpay) {
		this.mrvamountpay = mrvamountpay;
	}
	public void setDcvamountpay(Double dcvamountpay) {
		this.dcvamountpay = dcvamountpay;
	}
	public void setGiroamountpay(Double giroamountpay) {
		this.giroamountpay = giroamountpay;
	}
	public void setTransferamountpay(Double transferamountpay) {
		this.transferamountpay = transferamountpay;
	}
	public void setKelebihanbayaramount(Double kelebihanbayaramount) {
		this.kelebihanbayaramount = kelebihanbayaramount;
	}
	public void setSubtotalpay(Double subtotalpay) {
		this.subtotalpay = subtotalpay;
	}
	public void setFtappaymenthBean(FtAppaymenth ftappaymenthBean) {
		this.ftappaymenthBean = ftappaymenthBean;
	}
	public void setFtpurchasehBean(FtPurchaseh ftpurchasehBean) {
		this.ftpurchasehBean = ftpurchasehBean;
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
		FtAppaymentd other = (FtAppaymentd) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}