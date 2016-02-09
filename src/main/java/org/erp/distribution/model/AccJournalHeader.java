package org.erp.distribution.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="accjournalheader")
public class AccJournalHeader implements Serializable{

	private static final long serialVersionUID = -8668565005794214113L;
	@Id
	private String ref;
	@Temporal(TemporalType.DATE)
	private Date tglTrans;
	private int tahun;
	private int periode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "journalHeader")
    @Fetch(FetchMode.JOIN)
    private Set<AccJournalDetail> journalDetails;

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public Date getTglTrans() {
		return tglTrans;
	}

	public void setTglTrans(Date tglTrans) {
		this.tglTrans = tglTrans;
	}

	public int getTahun() {
		return tahun;
	}

	public void setTahun(int tahun) {
		this.tahun = tahun;
	}

	public int getPeriode() {
		return periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}

	public Set<AccJournalDetail> getJournalDetails() {
		return journalDetails;
	}

	public void setJournalDetails(Set<AccJournalDetail> journalDetails) {
		this.journalDetails = journalDetails;
	}
    
	
}
