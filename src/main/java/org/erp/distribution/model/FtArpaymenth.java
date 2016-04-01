package org.erp.distribution.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="ftarpaymenth")
public class FtArpaymenth {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long refno;
	
	@Column(name="NOREK", length=15)
	private String norek;
	
	@Temporal(TemporalType.DATE)
	private Date trdate;
	@Temporal(TemporalType.DATE)
	private Date entrydate;
	
	@Column(name="NOTES", length=150)
	private String notes;
	
	@Column(name="ENDOFDAY")
	private Boolean endofday;
	@Column(name="PRINTCOUNTER")
	private Integer printcounter;
	
	@Column(name="USERID", length=50)
	private String userid;
	@Column(name="CLOSING")
	private Boolean closing;
	
	@OneToMany(mappedBy="ftarpaymenthBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FtArpaymentd> ftarpaymentdSet;


	public Long getRefno() {
		return refno;
	}


	public String getNorek() {
		return norek;
	}


	public Date getTrdate() {
		return trdate;
	}


	public Date getEntrydate() {
		return entrydate;
	}


	public Set<FtArpaymentd> getFtarpaymentdSet() {
		return ftarpaymentdSet;
	}


	public void setRefno(Long refno) {
		this.refno = refno;
	}


	public void setNorek(String norek) {
		this.norek = norek;
	}


	public void setTrdate(Date trdate) {
		this.trdate = trdate;
	}


	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public void setFtarpaymentdSet(Set<FtArpaymentd> ftarpaymentdSet) {
		this.ftarpaymentdSet = ftarpaymentdSet;
	}


	public Boolean getEndofday() {
		return endofday;
	}


	public void setEndofday(Boolean endofday) {
		this.endofday = endofday;
	}


	public Integer getPrintcounter() {
		return printcounter;
	}


	public void setPrintcounter(Integer printcounter) {
		this.printcounter = printcounter;
	}


	public String getUserid() {
		return userid;
	}


	public Boolean getClosing() {
		return closing;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public void setClosing(Boolean closing) {
		this.closing = closing;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		FtArpaymenth other = (FtArpaymenth) obj;
		if (refno == null) {
			if (other.refno != null)
				return false;
		} else if (!refno.equals(other.refno))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return norek + "";
	}
	
	

}