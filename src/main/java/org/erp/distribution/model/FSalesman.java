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
@Table(name="fsalesman")
public class FSalesman {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String spcode;
	
	private String spname;
	private String salestype;
	private String address1;
	private String city1;
	private String state1;
	private String phone;
	private String mobile;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date joindate;
	@Temporal(TemporalType.DATE)
	private Date lasttrans;
	private String bornplace;
	@Temporal(TemporalType.DATE)
	private Date borndate;
	private Integer religion;
	private Boolean statusactive;
	
//	@OneToMany(mappedBy="fsalesmanBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fsalesmanBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FStock> fstockSet;
	
//	@OneToMany(mappedBy="fsalesmanBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fsalesmanBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtOpnameh> ftopnamehSet;

//	@OneToMany(mappedBy="fsalesmanBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fsalesmanBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtSalesh> ftsaleshSet;
	
	@Column(name="VENDORCOVERED")
	private boolean vendorcovered;	
	@OneToMany(mappedBy="fsalesmanBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FVendor> fVendorSet;
	
	@Column(name="AREACOVERED")
	private boolean areacovered;
	@OneToMany(mappedBy="fsalesmanBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FArea> fAreaSet;
	
	public Long getId() {
		return id;
	}

	public String getSpcode() {
		return spcode;
	}

	public String getSpname() {
		return spname;
	}

	public String getSalestype() {
		return salestype;
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

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public Date getJoindate() {
		return joindate;
	}

	public Date getLasttrans() {
		return lasttrans;
	}

	public String getBornplace() {
		return bornplace;
	}


	public Integer getReligion() {
		return religion;
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

	public Set<FtSalesh> getFtsaleshSet() {
		return ftsaleshSet;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSpcode(String spcode) {
		this.spcode = spcode;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public void setSalestype(String salestype) {
		this.salestype = salestype;
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

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public void setLasttrans(Date lasttrans) {
		this.lasttrans = lasttrans;
	}

	public void setBornplace(String bornplace) {
		this.bornplace = bornplace;
	}


	public Date getBorndate() {
		return borndate;
	}

	public void setBorndate(Date borndate) {
		this.borndate = borndate;
	}

	public void setReligion(Integer religion) {
		this.religion = religion;
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

	public void setFtsaleshSet(Set<FtSalesh> ftsaleshSet) {
		this.ftsaleshSet = ftsaleshSet;
	}

	
	public boolean isVendorcovered() {
		return vendorcovered;
	}

	public Set<FVendor> getfVendorSet() {
		return fVendorSet;
	}

	public boolean isAreacovered() {
		return areacovered;
	}

	public Set<FArea> getfAreaSet() {
		return fAreaSet;
	}

	public void setVendorcovered(boolean vendorcovered) {
		this.vendorcovered = vendorcovered;
	}

	public void setfVendorSet(Set<FVendor> fVendorSet) {
		this.fVendorSet = fVendorSet;
	}

	public void setAreacovered(boolean areacovered) {
		this.areacovered = areacovered;
	}

	public void setfAreaSet(Set<FArea> fAreaSet) {
		this.fAreaSet = fAreaSet;
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
		FSalesman other = (FSalesman) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return spcode + " - " + spname;
	}
	
	

}