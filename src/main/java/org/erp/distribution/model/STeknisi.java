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
@Table(name="steknisi")
public class STeknisi {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID" )
	private Long id;
	
	@Column(name="NIP" )
	private String nip;

	@Column(name="NAME" )
	private String name;
	
	
	@Column(name="ADDRESS1" )
	private String address1;
	@Column(name="CITY1" )
	private String city1;
	@Column(name="STATE1" )
	private String state1;
	@Column(name="PHONE1" )
	private String phone1;
	@Column(name="EMAIL" )
	private String email;

	@Column(name="BORNPLACE")
	private String bornplace;
	@Column(name="BORNDATE" )
	@Temporal(TemporalType.DATE)
	private Date borndate;

	@Column(name="JOINDATE" )
	@Temporal(TemporalType.DATE)
	private Date joindate;
	@Column(name="LASTTRANS" )
	@Temporal(TemporalType.DATE)
	private Date lasttrans;

	@Column(name="STATUSACTIVE" )
	private Boolean statusactive;
	

	@Column(name="FEEPERSERVICE")
	private Double feePerService;

	@Column(name="PERCENTFEEPERSERVICE")
	private Double percentFeePerService;
	
	
	@OneToMany(mappedBy="steknisiBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<StService> stServiceSet;
	


	public Set<StService> getStServiceSet() {
		return stServiceSet;
	}

	public void setStServiceSet(Set<StService> stServiceSet) {
		this.stServiceSet = stServiceSet;
	}

	public Long getId() {
		return id;
	}

	public String getNip() {
		return nip;
	}

	public String getName() {
		return name;
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

	public String getPhone1() {
		return phone1;
	}

	public String getEmail() {
		return email;
	}

	public String getBornplace() {
		return bornplace;
	}

	public Date getBorndate() {
		return borndate;
	}

	public Date getJoindate() {
		return joindate;
	}

	public Date getLasttrans() {
		return lasttrans;
	}

	public Boolean getStatusactive() {
		return statusactive;
	}

	public Double getFeePerService() {
		return feePerService;
	}

	public Double getPercentFeePerService() {
		return percentFeePerService;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBornplace(String bornplace) {
		this.bornplace = bornplace;
	}

	public void setBorndate(Date borndate) {
		this.borndate = borndate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public void setLasttrans(Date lasttrans) {
		this.lasttrans = lasttrans;
	}

	public void setStatusactive(Boolean statusactive) {
		this.statusactive = statusactive;
	}

	public void setFeePerService(Double feePerService) {
		this.feePerService = feePerService;
	}

	public void setPercentFeePerService(Double percentFeePerService) {
		this.percentFeePerService = percentFeePerService;
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
		STeknisi other = (STeknisi) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nip + " - " + name;
	}
	
	

}