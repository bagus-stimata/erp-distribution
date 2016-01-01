package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FtAppaymentdPK implements Serializable{

	@Column(name="refnopayment", insertable=false, updatable=false)
	private Long refnopayment;
	
	@Column(name="refnopurchase", insertable=false, updatable=false)
	private Long refnopurchase;

	public Long getRefnopayment() {
		return refnopayment;
	}

	public Long getRefnopurchase() {
		return refnopurchase;
	}

	public void setRefnopayment(Long refnopayment) {
		this.refnopayment = refnopayment;
	}

	public void setRefnopurchase(Long refnopurchase) {
		this.refnopurchase = refnopurchase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((refnopayment == null) ? 0 : refnopayment.hashCode());
		result = prime * result
				+ ((refnopurchase == null) ? 0 : refnopurchase.hashCode());
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
		FtAppaymentdPK other = (FtAppaymentdPK) obj;
		if (refnopayment == null) {
			if (other.refnopayment != null)
				return false;
		} else if (!refnopayment.equals(other.refnopayment))
			return false;
		if (refnopurchase == null) {
			if (other.refnopurchase != null)
				return false;
		} else if (!refnopurchase.equals(other.refnopurchase))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return refnopayment + " : " + refnopurchase;
	}
	
	
	

}