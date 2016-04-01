package org.erp.distribution.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ftadjustd")
public class FtAdjustd {

	@EmbeddedId
	protected FtAdjustdPK id;
	@Column(name="NOURUT")
	private Integer nourut;
	
	@Transient
	private Integer qty1;
	@Transient
	private Integer qty2;
	@Transient
	private Integer qty3;
	
	@Column(name="QTY")
	private Integer qty;	
	@Column(name="QTYTEORI")
	private Integer qtyteori;
	@Column(name="QTYADJUST")
	private Integer qtyadjust;
	
	@ManyToOne
	@JoinColumn(name="refno", referencedColumnName="refno", insertable=true, updatable=true)
	private FtAdjusth ftadjusthBean;
	
	@ManyToOne
	@JoinColumn(name="id", referencedColumnName="id", insertable=true, updatable=true)
	private FProduct fproductBean;

	public FtAdjustdPK getId() {
		return id;
	}

	public Integer getNourut() {
		return nourut;
	}

	public Integer getQty1() {
		return qty1;
	}

	public Integer getQty2() {
		return qty2;
	}

	public Integer getQty3() {
		return qty3;
	}

	public Integer getQty() {
		return qty;
	}

	public FtAdjusth getFtadjusthBean() {
		return ftadjusthBean;
	}

	public FProduct getFproductBean() {
		return fproductBean;
	}

	public void setId(FtAdjustdPK id) {
		this.id = id;
	}

	public void setNourut(Integer nourut) {
		this.nourut = nourut;
	}

	public void setQty1(Integer qty1) {
		this.qty1 = qty1;
	}

	public void setQty2(Integer qty2) {
		this.qty2 = qty2;
	}

	public void setQty3(Integer qty3) {
		this.qty3 = qty3;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public void setFtadjusthBean(FtAdjusth ftadjusthBean) {
		this.ftadjusthBean = ftadjusthBean;
	}

	public void setFproductBean(FProduct fproductBean) {
		this.fproductBean = fproductBean;
	}

	public Integer getQtyteori() {
		return qtyteori;
	}

	public Integer getQtyadjust() {
		return qtyadjust;
	}

	public void setQtyteori(Integer qtyteori) {
		this.qtyteori = qtyteori;
	}

	public void setQtyadjust(Integer qtyadjust) {
		this.qtyadjust = qtyadjust;
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
		FtAdjustd other = (FtAdjustd) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}