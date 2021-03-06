package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FtPricedPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="refno", insertable=false, updatable=false)
	private Long refno;
	@Column(name="id", insertable=false, updatable=false)
	
	private Long id;
	public Long getRefno() {
		return refno;
	}
	public Long getId() {
		return id;
	}
	public void setRefno(Long refno) {
		this.refno = refno;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		FtPricedPK other = (FtPricedPK) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (refno == null) {
			if (other.refno != null)
				return false;
		} else if (!refno.equals(other.refno))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FtPricedPK [refno=" + refno + ", id=" + id + "]";
	}
	
	
	

}