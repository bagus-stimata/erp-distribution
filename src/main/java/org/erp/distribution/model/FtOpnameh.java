package org.erp.distribution.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="ftopnameh")
public class FtOpnameh {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long refno;
	
	@Column(name="norek", length=15)
	private String norek;
	
	@Column(name="trdate")
	@Temporal(TemporalType.DATE)
	private Date trdate;
	@Column(name="entrydate")
	@Temporal(TemporalType.DATE)
	private Date entrydate;
	@Column(name="posting")
	private Boolean posting;
	@Column(name="tipeopname")
	private Integer tipeopname;
	@Column(name="endofday")
	private Boolean endofday;
	@Column(name="printcounter")
	private Integer printcounter;
	
	@ManyToOne
	@JoinColumn(name="fwarehouseBean", referencedColumnName="id")
	private FWarehouse fwarehouseBean;
	
	@ManyToOne
	@JoinColumn(name="fsalesmanBean", referencedColumnName="id")
	private FSalesman fsalesmanBean;

	@OneToMany(mappedBy="ftopnamehBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FtOpnamed> ftopnamedSet;

	public Long getRefno() {
		return refno;
	}


	public Date getTrdate() {
		return trdate;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public Boolean getPosting() {
		return posting;
	}

	public Integer getTipeopname() {
		return tipeopname;
	}

	public FWarehouse getFwarehouseBean() {
		return fwarehouseBean;
	}

	public FSalesman getFsalesmanBean() {
		return fsalesmanBean;
	}

	public Set<FtOpnamed> getFtopnamedSet() {
		return ftopnamedSet;
	}

	public void setRefno(Long refno) {
		this.refno = refno;
	}



	public String getNorek() {
		return norek;
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

	public void setPosting(Boolean posting) {
		this.posting = posting;
	}

	public void setTipeopname(Integer tipeopname) {
		this.tipeopname = tipeopname;
	}

	public void setFwarehouseBean(FWarehouse fwarehouseBean) {
		this.fwarehouseBean = fwarehouseBean;
	}

	public void setFsalesmanBean(FSalesman fsalesmanBean) {
		this.fsalesmanBean = fsalesmanBean;
	}

	public void setFtopnamedSet(Set<FtOpnamed> ftopnamedSet) {
		this.ftopnamedSet = ftopnamedSet;
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
		FtOpnameh other = (FtOpnameh) obj;
		if (refno == null) {
			if (other.refno != null)
				return false;
		} else if (!refno.equals(other.refno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  norek + "";
	}
	
	

}