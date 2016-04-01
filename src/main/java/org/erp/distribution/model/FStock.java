package org.erp.distribution.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="fstock")
public class FStock {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REFNO")
	private Long refno;
	
	@Column(name="STOCKDATE")
	@Temporal(TemporalType.DATE)
	private Date stockdate;
	@Column(name="WEEK")
	private Integer week;
	
	@Column(name="SALDOAWAL")
	private Integer saldoawal;
	@Column(name="QTYIN")
	private Integer qtyin;
	@Column(name="QTYOUT")
	private Integer qtyout;
	@Column(name="QTYHOLD")	
	private Integer qtyhold;
	
	@Column(name="QTYADJUST")	
	private Integer qtyadjust;
	
	@Column(name="SALDOAKHIR")
	private Integer saldoakhir;
	
	
	@Column(name="TIPESTOK", length=5)
	private String tipestok;
	@Column(name="DISTRIBUTORCODE", length=15)
	private String distributorcode;
	
	@ManyToOne
	@JoinColumn(name="fwarehouseBean", referencedColumnName="id")
	private FWarehouse fwarehouseBean;

	@ManyToOne
	@JoinColumn(name="fsalesmanBean", referencedColumnName="id")
	private FSalesman fsalesmanBean;
	
	@ManyToOne
	@JoinColumn(name="fproductBean", referencedColumnName="id")
	private FProduct fproductBean;

	public String getDistributorcode() {
		return distributorcode;
	}

	public void setDistributorcode(String distributorcode) {
		this.distributorcode = distributorcode;
	}

	public Long getRefno() {
		return refno;
	}

	public Date getStockdate() {
		return stockdate;
	}

	public Integer getWeek() {
		return week;
	}

	public Integer getSaldoawal() {
		return saldoawal;
	}

	public Integer getQtyin() {
		return qtyin;
	}

	public Integer getQtyout() {
		return qtyout;
	}

	public Integer getQtyhold() {
		return qtyhold;
	}

	public Integer getSaldoakhir() {
		return saldoakhir;
	}

	public String getTipestok() {
		return tipestok;
	}

	public FWarehouse getFwarehouseBean() {
		return fwarehouseBean;
	}

	public FSalesman getFsalesmanBean() {
		return fsalesmanBean;
	}

	public FProduct getFproductBean() {
		return fproductBean;
	}

	public void setRefno(Long refno) {
		this.refno = refno;
	}

	public void setStockdate(Date stockdate) {
		this.stockdate = stockdate;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public void setSaldoawal(Integer saldoawal) {
		this.saldoawal = saldoawal;
	}

	public void setQtyin(Integer qtyin) {
		this.qtyin = qtyin;
	}

	public void setQtyout(Integer qtyout) {
		this.qtyout = qtyout;
	}

	public void setQtyhold(Integer qtyhold) {
		this.qtyhold = qtyhold;
	}

	public void setSaldoakhir(Integer saldoakhir) {
		this.saldoakhir = saldoakhir;
	}

	public void setTipestok(String tipestok) {
		this.tipestok = tipestok;
	}

	public void setFwarehouseBean(FWarehouse fwarehouseBean) {
		this.fwarehouseBean = fwarehouseBean;
	}

	public void setFsalesmanBean(FSalesman fsalesmanBean) {
		this.fsalesmanBean = fsalesmanBean;
	}

	public void setFproductBean(FProduct fproductBean) {
		this.fproductBean = fproductBean;
	}

	public Integer getQtyadjust() {
		return qtyadjust;
	}

	public void setQtyadjust(Integer qtyadjust) {
		this.qtyadjust = qtyadjust;
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
		FStock other = (FStock) obj;
		if (refno == null) {
			if (other.refno != null)
				return false;
		} else if (!refno.equals(other.refno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return stockdate + ":" + saldoawal + ":" + qtyin + ":" + qtyout + ":" + saldoakhir;
	}
	
	

}