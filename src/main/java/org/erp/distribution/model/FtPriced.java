package org.erp.distribution.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ftpriced")
public class FtPriced {

	@EmbeddedId
	private FtPricedPK  id;
	
	@Column(name="NOURUT")
	private Integer nourut;
	
	@Column(name="PPRICE")
	private Double pprice;
	@Column(name="PPRICEAFTERPPN")
	private Double ppriceafterppn;
	@Column(name="PPRICEBEFORE")
	private Double ppricebefore;
	
	@Column(name="SPRICE")
	private Double sprice;
	@Column(name="SPRICEAFTERPPN")
	private Double spriceafterppn;
	@Column(name="SPRICEBEFORE")
	private Double spricebefore;

	@Column(name="PPRICE2")
	private Double pprice2;
	@Column(name="PPRICE2AFTERPPN")
	private Double pprice2afterppn;
	@Column(name="PPRICE2BEFORE")
	private Double pprice2before;
	
	@Column(name="SPRICE2")
	private Double sprice2;
	@Column(name="SPRICE2AFTERPPN")
	private Double sprice2afterppn;
	@Column(name="SPRICE2BEFORE")
	private Double sprice2before;
	
	@ManyToOne
	@JoinColumn(name="refno", referencedColumnName="refno", insertable=true, updatable=true)
	private FtPriceh  ftpricehBean;
	
	@ManyToOne
	@JoinColumn(name="id", referencedColumnName="id", insertable=true, updatable=true)
	private FProduct fproductBean;


	public FtPricedPK getId() {
		return id;
	}

	public Integer getNourut() {
		return nourut;
	}

	public Double getPprice() {
		return pprice;
	}

	public Double getPpriceafterppn() {
		return ppriceafterppn;
	}

	public Double getPpricebefore() {
		return ppricebefore;
	}

	public Double getSprice() {
		return sprice;
	}

	public Double getSpriceafterppn() {
		return spriceafterppn;
	}

	public Double getSpricebefore() {
		return spricebefore;
	}

	public Double getPprice2() {
		return pprice2;
	}

	public Double getPprice2afterppn() {
		return pprice2afterppn;
	}

	public Double getPprice2before() {
		return pprice2before;
	}

	public Double getSprice2() {
		return sprice2;
	}

	public Double getSprice2afterppn() {
		return sprice2afterppn;
	}

	public Double getSprice2before() {
		return sprice2before;
	}

	public FtPriceh getFtpricehBean() {
		return ftpricehBean;
	}

	public FProduct getFproductBean() {
		return fproductBean;
	}

	public void setId(FtPricedPK id) {
		this.id = id;
	}

	public void setNourut(Integer nourut) {
		this.nourut = nourut;
	}

	public void setPprice(Double pprice) {
		this.pprice = pprice;
	}

	public void setPpriceafterppn(Double ppriceafterppn) {
		this.ppriceafterppn = ppriceafterppn;
	}

	public void setPpricebefore(Double ppricebefore) {
		this.ppricebefore = ppricebefore;
	}

	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}

	public void setSpriceafterppn(Double spriceafterppn) {
		this.spriceafterppn = spriceafterppn;
	}

	public void setSpricebefore(Double spricebefore) {
		this.spricebefore = spricebefore;
	}

	public void setPprice2(Double pprice2) {
		this.pprice2 = pprice2;
	}

	public void setPprice2afterppn(Double pprice2afterppn) {
		this.pprice2afterppn = pprice2afterppn;
	}

	public void setPprice2before(Double pprice2before) {
		this.pprice2before = pprice2before;
	}

	public void setSprice2(Double sprice2) {
		this.sprice2 = sprice2;
	}

	public void setSprice2afterppn(Double sprice2afterppn) {
		this.sprice2afterppn = sprice2afterppn;
	}

	public void setSprice2before(Double sprice2before) {
		this.sprice2before = sprice2before;
	}

	public void setFtpricehBean(FtPriceh ftpricehBean) {
		this.ftpricehBean = ftpricehBean;
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
		FtPriced other = (FtPriced) obj;
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
