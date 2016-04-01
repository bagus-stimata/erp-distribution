package org.erp.distribution.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="fwarehouse")
public class FWarehouse {

	@Id
	@Column(name="ID", length=10)
	private String id;
	
	@Column(name="NUMBERPRIORITY")
	private Integer numberPriority;
	@Column(name="DESCRIPTION", length=100)
	private String description;
	@Column(name="GUDANGUTAMA")
	private Boolean gudangutama;
	@Column(name="ADDRESS1", length=100)
	private String address1;
	@Column(name="CITY1", length=30)
	private String city1;
	@Column(name="STATE1", length=30)
	private String state1;
	@Column(name="PHONE", length=50)
	private String phone;
	@Column(name="STATUSACTIVE")
	private Boolean statusactive;
	
	@OneToMany(mappedBy="fwarehouseBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FStock> fstockSet;
	
	@OneToMany(mappedBy="fwarehouseBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtAdjusth> ftadjusthSet;
	
	@OneToMany(mappedBy="fwarehouseBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtOpnameh> ftopnamehSet;

	@OneToMany(mappedBy="fwarehouseBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtPurchaseh> ftpurchasehSet;

	@OneToMany(mappedBy="fwarehouseBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtSalesh> ftsaleshSet;
	
	@OneToMany(mappedBy="fwarehouseBeanFrom", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtStocktransferh> ftstocktransferhFromSet;
	

	@OneToMany(mappedBy="fwarehouseBeanTo", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtStocktransferh> ftstocktransferhToSet;
	
	@ManyToOne
	@JoinColumn(name="fdivisionBean", referencedColumnName="id")
	private FDivision fdivisionBean;

	
	
	public Set<FtAdjusth> getFtadjusthSet() {
		return ftadjusthSet;
	}

	public FDivision getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFtadjusthSet(Set<FtAdjusth> ftadjusthSet) {
		this.ftadjusthSet = ftadjusthSet;
	}

	public void setFdivisionBean(FDivision fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public Integer getNumberPriority() {
		return numberPriority;
	}

	public void setNumberPriority(Integer numberPriority) {
		this.numberPriority = numberPriority;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getGudangutama() {
		return gudangutama;
	}

	public String getAddress1() {
		return address1;
	}

	public String getCity1() {
		return city1;
	}

	public String getState1() {
		return state1;
	}

	public String getPhone() {
		return phone;
	}

	public Boolean getStatusactive() {
		return statusactive;
	}

	public Set<FStock> getFstockSet() {
		return fstockSet;
	}

	public Set<FtOpnameh> getFtopnamehSet() {
		return ftopnamehSet;
	}

	public Set<FtPurchaseh> getFtpurchasehSet() {
		return ftpurchasehSet;
	}

	public Set<FtSalesh> getFtsaleshSet() {
		return ftsaleshSet;
	}

	public Set<FtStocktransferh> getFtstocktransferhFromSet() {
		return ftstocktransferhFromSet;
	}

	public Set<FtStocktransferh> getFtstocktransferhToSet() {
		return ftstocktransferhToSet;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setGudangutama(Boolean gudangutama) {
		this.gudangutama = gudangutama;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setStatusactive(Boolean statusactive) {
		this.statusactive = statusactive;
	}

	public void setFstockSet(Set<FStock> fstockSet) {
		this.fstockSet = fstockSet;
	}

	public void setFtopnamehSet(Set<FtOpnameh> ftopnamehSet) {
		this.ftopnamehSet = ftopnamehSet;
	}

	public void setFtpurchasehSet(Set<FtPurchaseh> ftpurchasehSet) {
		this.ftpurchasehSet = ftpurchasehSet;
	}

	public void setFtsaleshSet(Set<FtSalesh> ftsaleshSet) {
		this.ftsaleshSet = ftsaleshSet;
	}

	public void setFtstocktransferhFromSet(
			Set<FtStocktransferh> ftstocktransferhFromSet) {
		this.ftstocktransferhFromSet = ftstocktransferhFromSet;
	}

	public void setFtstocktransferhToSet(Set<FtStocktransferh> ftstocktransferhToSet) {
		this.ftstocktransferhToSet = ftstocktransferhToSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		FWarehouse other = (FWarehouse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " - " + description;
	}
	
	

}