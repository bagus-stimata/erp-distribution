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
@Table(name="ftsalesdpromotprucb")
public class FtSalesdPromoTpruCb implements Serializable{

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
	
	@Column(name="TPRUCASHBACK")
	private Double tprucashback;
	
	
	
	public FPromo getfPromoBean() {
		return fPromoBean;
	}
	public void setfPromoBean(FPromo fPromoBean) {
		this.fPromoBean = fPromoBean;
	}
	public Long getRefnoPromo() {
		return refnoPromo;
	}
	public Integer getNourut() {
		return nourut;
	}
	public FtSalesd getFtSalesdBean() {
		return ftSalesdBean;
	}
	public Double getTprucashback() {
		return tprucashback;
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
	public void setTprucashback(Double tprucashback) {
		this.tprucashback = tprucashback;
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
		FtSalesdPromoTpruCb other = (FtSalesdPromoTpruCb) obj;
		if (refnoPromo == null) {
			if (other.refnoPromo != null)
				return false;
		} else if (!refnoPromo.equals(other.refnoPromo))
			return false;
		return true;
	}
	
	
	

}