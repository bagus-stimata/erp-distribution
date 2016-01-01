package org.erp.distribution.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="stserviceitem")
public class StServiceItem implements Serializable{

	@EmbeddedId
	protected StServiceItemPK id;
	
	private Boolean promo;
	
	@Column(name="NOURUT")
	private Integer nourut;
	
	@Column(name="SPRICE")
	private Double sprice;
	@Transient
	private Double spriceafterppn;

	@Transient
	private Integer qty1;
	@Transient
	private Integer qty2;
	@Transient
	private Integer qty3;

	
	@Transient
	private Double subtotal;
	@Transient
	private Double subtotalafterppn;

	@ManyToOne
	@JoinColumn(name="refno", referencedColumnName="refno")
	private StService stserviceBean;
	
	@ManyToOne
	@JoinColumn(name="id", referencedColumnName="id")
	private FProduct fproductBean;
	

	
	
	public StService getStserviceBean() {
		return stserviceBean;
	}
	public void setStserviceBean(StService stserviceBean) {
		this.stserviceBean = stserviceBean;
	}
	public StServiceItemPK getId() {
		return id;
	}
	public Boolean getPromo() {
		return promo;
	}
	public Integer getNourut() {
		return nourut;
	}
	public Double getSprice() {
		return sprice;
	}
	public Double getSpriceafterppn() {
		return spriceafterppn;
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
	public Double getSubtotal() {
		return subtotal;
	}
	public Double getSubtotalafterppn() {
		return subtotalafterppn;
	}
	public FProduct getFproductBean() {
		return fproductBean;
	}
	public void setId(StServiceItemPK id) {
		this.id = id;
	}
	public void setPromo(Boolean promo) {
		this.promo = promo;
	}
	public void setNourut(Integer nourut) {
		this.nourut = nourut;
	}
	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}
	public void setSpriceafterppn(Double spriceafterppn) {
		this.spriceafterppn = spriceafterppn;
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
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public void setSubtotalafterppn(Double subtotalafterppn) {
		this.subtotalafterppn = subtotalafterppn;
	}
	public void setFproductBean(FProduct fproductBean) {
		this.fproductBean = fproductBean;
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
		StServiceItem other = (StServiceItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}