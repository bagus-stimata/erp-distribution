package org.erp.distribution.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the ACCOUNTBALANCE database table.
 * 
 */
@Embeddable
public class AccAccountbalancePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNTID", length=10)
	private String accountid;

	@Temporal(TemporalType.DATE)
	@Column(name="FDATE")
	private Date fdate;
	
//	@Column(name="DIVISION", insertable=false, updatable=false, length=10)
//	private String division;


	@Override
	public String toString() {
		return  fdate + " - " +  accountid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountid == null) ? 0 : accountid.hashCode());
		result = prime * result + ((fdate == null) ? 0 : fdate.hashCode());
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
		AccAccountbalancePK other = (AccAccountbalancePK) obj;
		if (accountid == null) {
			if (other.accountid != null)
				return false;
		} else if (!accountid.equals(other.accountid))
			return false;
		if (fdate == null) {
			if (other.fdate != null)
				return false;
		} else if (!fdate.equals(other.fdate))
			return false;
		return true;
	}

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	
}