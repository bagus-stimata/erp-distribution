package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FtSalesdPK implements Serializable{

	@Column(name="refno", insertable=false, updatable=false)
	private Long refno;
	@Column(name="id", insertable=false, updatable=false)
	private Long id;
	
	@Column(name="freegood")
	private Boolean freegood;

	public Long getRefno() {
		return refno;
	}

	public Long getId() {
		return id;
	}

	public Boolean getFreegood() {
		return freegood;
	}

	public void setRefno(Long refno) {
		this.refno = refno;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFreegood(Boolean freegood) {
		this.freegood = freegood;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((freegood == null) ? 0 : freegood.hashCode());
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
		FtSalesdPK other = (FtSalesdPK) obj;
		if (freegood == null) {
			if (other.freegood != null)
				return false;
		} else if (!freegood.equals(other.freegood))
			return false;
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
		return refno + ":" + id + ":" + freegood;
	}
	
	
	

}