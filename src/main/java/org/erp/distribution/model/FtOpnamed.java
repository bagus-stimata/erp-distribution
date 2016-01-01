package org.erp.distribution.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ftopnamed")
public class FtOpnamed {

	@EmbeddedId
	protected FtOpnamedPK id;
	
	private Integer nourut;
	
	@Transient
	private Double pprice;
	
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

	@Column(name="VISIBLE")
	private boolean visible;
	
	@Transient
	private Double subtotal;
	@Transient
	private Double subtotalafterppn;
	
	@ManyToOne
	@JoinColumn(name="refno", referencedColumnName="refno", insertable=true, updatable=true)
	private FtOpnameh ftopnamehBean;
	
	@ManyToOne
	@JoinColumn(name="id", referencedColumnName="id", insertable=true, updatable=true)
	private FProduct fproductBean;
	

	public FtOpnamedPK getId() {
		return id;
	}

	public FProduct getFproductBean() {
		return fproductBean;
	}

	public Integer getQty() {
		return qty;
	}

	public FtOpnameh getFtopnamehBean() {
		return ftopnamehBean;
	}

	public void setId(FtOpnamedPK id) {
		this.id = id;
	}

	public void setFproductBean(FProduct fproductBean) {
		this.fproductBean = fproductBean;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public void setFtopnamehBean(FtOpnameh ftopnamehBean) {
		this.ftopnamehBean = ftopnamehBean;
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

	public Double getPprice() {
		return pprice;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public Double getSubtotalafterppn() {
		return subtotalafterppn;
	}

	public void setPprice(Double pprice) {
		this.pprice = pprice;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public void setSubtotalafterppn(Double subtotalafterppn) {
		this.subtotalafterppn = subtotalafterppn;
	}

	public Integer getQtyadjust() {
		return qtyadjust;
	}

	public void setQtyadjust(Integer qtyadjust) {
		this.qtyadjust = qtyadjust;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public Integer getQtyteori() {
		return qtyteori;
	}

	public void setQtyteori(Integer qtyteori) {
		this.qtyteori = qtyteori;
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
		FtOpnamed other = (FtOpnamed) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + "";
	}
	
	
	
}