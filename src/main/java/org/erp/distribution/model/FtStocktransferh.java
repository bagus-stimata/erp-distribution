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
@Table(name="ftstocktransferh")
public class FtStocktransferh {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REFNO")
	private Long refno;

	@Column(name="NOREK")
	private String norek;
	
	@Column(name="TRDATE")
	@Temporal(TemporalType.DATE)
	private Date trdate;
	@Column(name="ENTRYDATE")
	@Temporal(TemporalType.DATE)
	private Date entrydate;
	@Column(name="TIPETRANSFER")
	private Integer tipetransfer;
	
	@Column(name="ENDOFDAY")
	private Boolean endofday;
	@Column(name="PRINTCOUNTER")
	private Integer printcounter;
	
	@ManyToOne
	@JoinColumn(name="fwarehouseBeanFrom", referencedColumnName="id")
	private FWarehouse fwarehouseBeanFrom;

	@ManyToOne
	@JoinColumn(name="fwarehouseBeanTo", referencedColumnName="id")
	private FWarehouse fwarehouseBeanTo;
	
	@OneToMany(mappedBy="ftstocktransferhBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FtStocktransferd> ftstocktransferdSet;

	


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

	public Integer getTipetransfer() {
		return tipetransfer;
	}

	public Boolean getEndofday() {
		return endofday;
	}

	public Integer getPrintcounter() {
		return printcounter;
	}

	public FWarehouse getFwarehouseBeanFrom() {
		return fwarehouseBeanFrom;
	}

	public FWarehouse getFwarehouseBeanTo() {
		return fwarehouseBeanTo;
	}

	public Set<FtStocktransferd> getFtstocktransferdSet() {
		return ftstocktransferdSet;
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

	public void setTipetransfer(Integer tipetransfer) {
		this.tipetransfer = tipetransfer;
	}

	public void setEndofday(Boolean endofday) {
		this.endofday = endofday;
	}

	public void setPrintcounter(Integer printcounter) {
		this.printcounter = printcounter;
	}

	public void setFwarehouseBeanFrom(FWarehouse fwarehouseBeanFrom) {
		this.fwarehouseBeanFrom = fwarehouseBeanFrom;
	}

	public void setFwarehouseBeanTo(FWarehouse fwarehouseBeanTo) {
		this.fwarehouseBeanTo = fwarehouseBeanTo;
	}

	public void setFtstocktransferdSet(Set<FtStocktransferd> ftstocktransferdSet) {
		this.ftstocktransferdSet = ftstocktransferdSet;
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
		FtStocktransferh other = (FtStocktransferh) obj;
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