package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class AccJournalDetailPK implements Serializable{

	private static final long serialVersionUID = -8668565005794214113L;
	
	@Basic(optional=false)
	@Column(nullable=false)
	private String ref;
	@Column(nullable=false)
	private int noUrut;
	
	
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public int getNoUrut() {
		return noUrut;
	}
	public void setNoUrut(int noUrut) {
		this.noUrut = noUrut;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noUrut;
		result = prime * result + ((ref == null) ? 0 : ref.hashCode());
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
		AccJournalDetailPK other = (AccJournalDetailPK) obj;
		if (noUrut != other.noUrut)
			return false;
		if (ref == null) {
			if (other.ref != null)
				return false;
		} else if (!ref.equals(other.ref))
			return false;
		return true;
	}
	
	
	
}
