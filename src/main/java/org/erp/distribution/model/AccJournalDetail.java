package org.erp.distribution.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="accjournaldetail")
public class AccJournalDetail{

	private static final long serialVersionUID = -8668565005794214113L;
	
	@EmbeddedId
	private AccJournalDetailPK journalDetailPK;
	private String description;
	private boolean DebetKredit;
	private Double jumlah;
	
    @ManyToOne(optional = true)
    @JoinColumn(name = "ref", referencedColumnName = "ref", nullable = true,  insertable = false, updatable = false)
    private AccJournalHeader journalHeader;


//    @ManyToOne(optional = true)
//    @JoinColumn(name = "account", referencedColumnName = "id", nullable = true,  insertable = false, updatable = false)
//    private Account account;

    

	public AccJournalDetailPK getJournalDetailPK() {
		return journalDetailPK;
	}

	public void setJournalDetailPK(AccJournalDetailPK journalDetailPK) {
		this.journalDetailPK = journalDetailPK;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDebetKredit() {
		return DebetKredit;
	}

	public void setDebetKredit(boolean debetKredit) {
		DebetKredit = debetKredit;
	}

	public Double getJumlah() {
		return jumlah;
	}

	public void setJumlah(Double jumlah) {
		this.jumlah = jumlah;
	}

	public AccJournalHeader getJournalHeader() {
		return journalHeader;
	}

	public void setJournalHeader(AccJournalHeader journalHeader) {
		this.journalHeader = journalHeader;
	}
	
	
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "journalDetail")
//    @Fetch(FetchMode.JOIN)
//    private Set<Account> accounts;
    
	

	
}
