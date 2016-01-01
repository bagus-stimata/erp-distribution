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
@Table(name="scustomer")
public class SCustomer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID" )
	private Long id;
	
	@Column(name="CUSTNO" )
	private String custno;
	
	@Column(name="CUSTNAME" )
	private String custname;
	
	@Column(name="ADDRESS1" )
	private String address1;
	@Column(name="CITY1" )
	private String city1;
	@Column(name="STATE1" )
	private String state1;
	@Column(name="PHONE1" )
	private String phone1;
	@Column(name="ADDRESS2" )
	private String address2;
	@Column(name="CITY2" )
	private String city2;
	@Column(name="STATE2" )
	private String state2;
	@Column(name="PHONE2" )
	private String phone2;
	@Column(name="NPWP" )
	private String npwp;
	@Column(name="EMAIL" )
	private String email;

	@Column(name="JOINDATE" )
	@Temporal(TemporalType.DATE)
	private Date joindate;
	@Column(name="LASTTRANS" )
	@Temporal(TemporalType.DATE)
	private Date lasttrans;

	@Column(name="STATUSACTIVE" )
	private Boolean statusactive;
	
	
	@OneToMany(mappedBy="scustomerBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<StService> stServiceSet;
	

	public Set<StService> getStServiceSet() {
		return stServiceSet;
	}

	public void setStServiceSet(Set<StService> stServiceSet) {
		this.stServiceSet = stServiceSet;
	}

	public String getCustno() {
		return custno;
	}

	public String getCustname() {
		return custname;
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

	public String getAddress2() {
		return address2;
	}

	public String getCity2() {
		return city2;
	}

	public String getState2() {
		return state2;
	}

	public String getPhone2() {
		return phone2;
	}

	public String getNpwp() {
		return npwp;
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

	public Boolean getStatusactive() {
		return statusactive;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public void setCustname(String custname) {
		this.custname = custname;
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

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
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

	public void setStatusactive(Boolean statusactive) {
		this.statusactive = statusactive;
	}

	public Long getId() {
		return id;
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
		SCustomer other = (SCustomer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return custno + " - " + custname +" - " + address1;
	}
	
	

}