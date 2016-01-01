package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FtArpaymentdPK implements Serializable{

	@Column(name="refnopayment", insertable=false, updatable=false)
	private Long refnopayment;
	
	@Column(name="refnosales", insertable=false, updatable=false)
	private Long refnosales;
	
	public Long getRefnopayment() {
		return refnopayment;
	}
	public Long getRefnosales() {
		return refnosales;
	}
	public void setRefnopayment(Long refnopayment) {
		this.refnopayment = refnopayment;
	}
	public void setRefnosales(Long refnosales) {
		this.refnosales = refnosales;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((refnopayment == null) ? 0 : refnopayment.hashCode());
		result = prime * result
				+ ((refnosales == null) ? 0 : refnosales.hashCode());
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
		FtArpaymentdPK other = (FtArpaymentdPK) obj;
		if (refnopayment == null) {
			if (other.refnopayment != null)
				return false;
		} else if (!refnopayment.equals(other.refnopayment))
			return false;
		if (refnosales == null) {
			if (other.refnosales != null)
				return false;
		} else if (!refnosales.equals(other.refnosales))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return refnopayment + ":"
				+ refnosales;
	}
	
	
	

}