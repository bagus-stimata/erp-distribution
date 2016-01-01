package org.erp.distribution.model;

import java.util.Date;
import java.util.Set;

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
@Table(name="ftpriceh")
public class FtPriceh {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REFNO")
	private Long refno;
	
	@Column(name="NOREK")
	private String norek;

	@Temporal(TemporalType.DATE)
	@Column(name="ENTRYDATE")
	private Date entrydate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="TRDATE")
	private Date trdate;
	
	@Column(name="POSTING")
	private Boolean posting;
	@Temporal(TemporalType.DATE)
	@Column(name="POSTINGDATE")
	private Date postingdate;
	
	@Column(name="ENDOFDAY")
	private Boolean endofday;
	
	@Column(name="PRINTCOUNTER")
	private Integer printcounter;
	
	@OneToMany(mappedBy="ftpricehBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FtPriced> ftpricedSet;
	
//	@OneToMany(mappedBy="ftpricehBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
//	@Fetch(FetchMode.JOIN)
//	private Set<FCustomersubgroup> fcustomersubgroupSet;



	public Long getRefno() {
		return refno;
	}

	public String getNorek() {
		return norek;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public Date getTrdate() {
		return trdate;
	}

	public Boolean getPosting() {
		return posting;
	}

	public Date getPostingdate() {
		return postingdate;
	}

	public Boolean getEndofday() {
		return endofday;
	}

	public Integer getPrintcounter() {
		return printcounter;
	}

	public Set<FtPriced> getFtpricedSet() {
		return ftpricedSet;
	}

	public void setRefno(Long refno) {
		this.refno = refno;
	}

	public void setNorek(String norek) {
		this.norek = norek;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public void setTrdate(Date trdate) {
		this.trdate = trdate;
	}

	public void setPosting(Boolean posting) {
		this.posting = posting;
	}

	public void setPostingdate(Date postingdate) {
		this.postingdate = postingdate;
	}

	public void setEndofday(Boolean endofday) {
		this.endofday = endofday;
	}

	public void setPrintcounter(Integer printcounter) {
		this.printcounter = printcounter;
	}

	public void setFtpricedSet(Set<FtPriced> ftpricedSet) {
		this.ftpricedSet = ftpricedSet;
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
		FtPriceh other = (FtPriceh) obj;
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
