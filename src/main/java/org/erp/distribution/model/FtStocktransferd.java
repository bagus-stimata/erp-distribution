package org.erp.distribution.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ftstocktransferd")
public class FtStocktransferd {

	@EmbeddedId
	protected FtStocktransferdPK id;
	
	@Column(name="NOURUT")
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
	
	@Transient
	private Double subtotal;
	@Transient
	private Double subtotalafterppn;
	
	@ManyToOne
	@JoinColumn(name="refno", referencedColumnName="refno")
	private FtStocktransferh ftstocktransferhBean;
	
	@ManyToOne
	@JoinColumn(name="id", referencedColumnName="id")
	private FProduct fproductBean;

	public FtStocktransferdPK getId() {
		return id;
	}

	public Integer getQty() {
		return qty;
	}

	public FtStocktransferh getFtstocktransferhBean() {
		return ftstocktransferhBean;
	}

	public FProduct getFproductBean() {
		return fproductBean;
	}

	public void setId(FtStocktransferdPK id) {
		this.id = id;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public void setFtstocktransferhBean(FtStocktransferh ftstocktransferhBean) {
		this.ftstocktransferhBean = ftstocktransferhBean;
	}

	public void setFproductBean(FProduct fproductBean) {
		this.fproductBean = fproductBean;
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

	public void setQty1(Integer qty1) {
		this.qty1 = qty1;
	}

	public void setQty2(Integer qty2) {
		this.qty2 = qty2;
	}

	public void setQty3(Integer qty3) {
		this.qty3 = qty3;
	}

	public Integer getNourut() {
		return nourut;
	}

	public void setNourut(Integer nourut) {
		this.nourut = nourut;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public Double getSubtotalafterppn() {
		return subtotalafterppn;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public void setSubtotalafterppn(Double subtotalafterppn) {
		this.subtotalafterppn = subtotalafterppn;
	}

	public Double getPprice() {
		return pprice;
	}

	public void setPprice(Double pprice) {
		this.pprice = pprice;
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
		FtStocktransferd other = (FtStocktransferd) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

	
}