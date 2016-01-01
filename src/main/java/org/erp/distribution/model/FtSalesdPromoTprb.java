package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ftsalesdpromotprb")
public class FtSalesdPromoTprb implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REFNOPROMO")
	private Long refnoPromo;
	
	
	@Column(name="NOURUT")
	private Integer nourut;
	
	@ManyToOne
	@JoinColumns({@JoinColumn(name="ftSalesdRefno", referencedColumnName="refno"),
		@JoinColumn(name="ftSalesdId", referencedColumnName="id"),
		@JoinColumn(name="ftSalesdFreegood", referencedColumnName="freegood")})
	private FtSalesd ftSalesdBean;	
	
	@ManyToOne
	@JoinColumn(name="fPromoBean", referencedColumnName="id")
	private FPromo fPromoBean;

	
	@ManyToOne
	@JoinColumn(name="fProductBean", referencedColumnName="id")
	private FProduct fProductBean;
	
	@Column(name="TPRBQTY")
	private Integer tprbqty;

	@Column(name="TPRBAFTERPPN")
	private Double tprbafterppn;

	public Long getRefnoPromo() {
		return refnoPromo;
	}

	public Integer getNourut() {
		return nourut;
	}

	public FtSalesd getFtSalesdBean() {
		return ftSalesdBean;
	}

	public FPromo getfPromoBean() {
		return fPromoBean;
	}

	public FProduct getfProductBean() {
		return fProductBean;
	}

	public Integer getTprbqty() {
		return tprbqty;
	}

	public Double getTprbafterppn() {
		return tprbafterppn;
	}

	public void setRefnoPromo(Long refnoPromo) {
		this.refnoPromo = refnoPromo;
	}

	public void setNourut(Integer nourut) {
		this.nourut = nourut;
	}

	public void setFtSalesdBean(FtSalesd ftSalesdBean) {
		this.ftSalesdBean = ftSalesdBean;
	}

	public void setfPromoBean(FPromo fPromoBean) {
		this.fPromoBean = fPromoBean;
	}

	public void setfProductBean(FProduct fProductBean) {
		this.fProductBean = fProductBean;
	}

	public void setTprbqty(Integer tprbqty) {
		this.tprbqty = tprbqty;
	}

	public void setTprbafterppn(Double tprbafterppn) {
		this.tprbafterppn = tprbafterppn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((refnoPromo == null) ? 0 : refnoPromo.hashCode());
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
		FtSalesdPromoTprb other = (FtSalesdPromoTprb) obj;
		if (refnoPromo == null) {
			if (other.refnoPromo != null)
				return false;
		} else if (!refnoPromo.equals(other.refnoPromo))
			return false;
		return true;
	}

	
	

		
	

}