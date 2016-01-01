package org.erp.distribution.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ftpricealtd")
public class FtPriceAltd {

	@EmbeddedId
	private FtPriceAltdPK  id;
	
	@Column(name="NOURUT")
	private Integer nourut;
	
	@Column(name="SPRICE")
	private Double sprice;
	@Column(name="SPRICEAFTERPPN")
	private Double spriceafterppn;
	@Column(name="SPRICE2")
	private Double sprice2;
	@Column(name="SPRICE2AFTERPPN")
	private Double sprice2afterppn;

	@Column(name="SPRICEALT")
	private Double spricealt;
	@Column(name="SPRICEALTAFTERPPN")
	private Double spricealtafterppn;
	@Column(name="SPRICEALT2")
	private Double spricealt2;
	@Column(name="SPRICEALT2AFTERPPN")
	private Double spricealt2afterppn;
	
	@ManyToOne
	@JoinColumn(name="refno", referencedColumnName="refno", insertable=true, updatable=true)
	private FtPriceAlth  ftpricealthBean;
	
	@ManyToOne
	@JoinColumn(name="id", referencedColumnName="id", insertable=true, updatable=true)
	private FProduct fproductBean;

	

	public FtPriceAltdPK getId() {
		return id;
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

	public Double getSpricealt() {
		return spricealt;
	}

	public Double getSpricealtafterppn() {
		return spricealtafterppn;
	}

	public Double getSprice2() {
		return sprice2;
	}

	public Double getSprice2afterppn() {
		return sprice2afterppn;
	}

	public Double getSpricealt2() {
		return spricealt2;
	}

	public Double getSpricealt2afterppn() {
		return spricealt2afterppn;
	}

	public FtPriceAlth getFtpricealthBean() {
		return ftpricealthBean;
	}

	public FProduct getFproductBean() {
		return fproductBean;
	}

	public void setId(FtPriceAltdPK id) {
		this.id = id;
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

	public void setSpricealt(Double spricealt) {
		this.spricealt = spricealt;
	}

	public void setSpricealtafterppn(Double spricealtafterppn) {
		this.spricealtafterppn = spricealtafterppn;
	}

	public void setSprice2(Double sprice2) {
		this.sprice2 = sprice2;
	}

	public void setSprice2afterppn(Double sprice2afterppn) {
		this.sprice2afterppn = sprice2afterppn;
	}

	public void setSpricealt2(Double spricealt2) {
		this.spricealt2 = spricealt2;
	}

	public void setSpricealt2afterppn(Double spricealt2afterppn) {
		this.spricealt2afterppn = spricealt2afterppn;
	}

	public void setFtpricealthBean(FtPriceAlth ftpricealthBean) {
		this.ftpricealthBean = ftpricealthBean;
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
		FtPriceAltd other = (FtPriceAltd) obj;
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
