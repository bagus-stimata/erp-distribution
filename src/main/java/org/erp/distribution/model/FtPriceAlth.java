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
@Table(name="ftpricealth")
public class FtPriceAlth {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REFNO")
	private Long refno;
	
	@Column(name="NOREK", length=15)
	private String norek;
	
	@Column(name="DESCRIPTION", length=50)
	private String description;
	

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
	
	@OneToMany(mappedBy="ftpricealthBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FtPriceAltd> ftPricealtdSet;

	@OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FCustomer> fCustomerSet;
	
	@OneToMany(mappedBy="ftPriceAlthBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FCustomersubgroup> fCustomersubgroupSet;
	
//	@OneToMany(mappedBy="ftspricealthBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
//	@Fetch(FetchMode.JOIN)
//	private Set<FCustomersubgroup> fcustomersubgroupSet;
//



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((refno == null) ? 0 : refno.hashCode());
		return result;
	}



	public Set<FCustomersubgroup> getfCustomersubgroupSet() {
		return fCustomersubgroupSet;
	}



	public void setfCustomersubgroupSet(Set<FCustomersubgroup> fCustomersubgroupSet) {
		this.fCustomersubgroupSet = fCustomersubgroupSet;
	}



	public Set<FCustomer> getfCustomerSet() {
		return fCustomerSet;
	}



	public void setfCustomerSet(Set<FCustomer> fCustomerSet) {
		this.fCustomerSet = fCustomerSet;
	}



	public Long getRefno() {
		return refno;
	}



	public String getNorek() {
		return norek;
	}



	public Date getEntrydate() {
		return entrydate;
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



	public Set<FtPriceAltd> getFtPricealtdSet() {
		return ftPricealtdSet;
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



	public Date getTrdate() {
		return trdate;
	}



	public void setTrdate(Date trdate) {
		this.trdate = trdate;
	}



	public void setFtPricealtdSet(Set<FtPriceAltd> ftPricealtdSet) {
		this.ftPricealtdSet = ftPricealtdSet;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FtPriceAlth other = (FtPriceAlth) obj;
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
