package org.erp.distribution.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ftsaleshrekaptampungan")
public class FtSaleshRekapTampungan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long refno;
	
	@Column(name="RECAPNO", length=15)
	private String recapno;
	
	@ManyToOne
	@JoinColumn(name="division", referencedColumnName = "id")
	private FDivision fdivisionBean;
	
	@Column(name="TANGGALSETOR")
	@Temporal(TemporalType.DATE)
	private Date tanggalsetor;
	
	@Column(name="AMOUNTRETUR")
	private double amountRetur;
	@Column(name="AMOUNTDISKONKHUSUS")
	private double amountDiskonKhusus;
	@Column(name="AMOUNTGIRO")
	private double amountGiro;
	@Column(name="AMOUNTTRANSFER")
	private double amountTransfer;
	
	public long getRefno() {
		return refno;
	}
	public void setRefno(long refno) {
		this.refno = refno;
	}
	public String getRecapno() {
		return recapno;
	}
	public void setRecapno(String recapno) {
		this.recapno = recapno;
	}
	
	public FDivision getFdivisionBean() {
		return fdivisionBean;
	}
	public void setFdivisionBean(FDivision fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}
	public double getAmountRetur() {
		return amountRetur;
	}
	public void setAmountRetur(double amountRetur) {
		this.amountRetur = amountRetur;
	}
	public double getAmountDiskonKhusus() {
		return amountDiskonKhusus;
	}
	public void setAmountDiskonKhusus(double amountDiskonKhusus) {
		this.amountDiskonKhusus = amountDiskonKhusus;
	}
	public double getAmountGiro() {
		return amountGiro;
	}
	public void setAmountGiro(double amountGiro) {
		this.amountGiro = amountGiro;
	}
	public double getAmountTransfer() {
		return amountTransfer;
	}
	public void setAmountTransfer(double amountTransfer) {
		this.amountTransfer = amountTransfer;
	}
	
	
	public Date getTanggalsetor() {
		return tanggalsetor;
	}
	public void setTanggalsetor(Date tanggalsetor) {
		this.tanggalsetor = tanggalsetor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (refno ^ (refno >>> 32));
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
		FtSaleshRekapTampungan other = (FtSaleshRekapTampungan) obj;
		if (refno != other.refno)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "" + refno ;
	}
	
	
}
