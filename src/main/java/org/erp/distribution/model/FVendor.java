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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="fvendor")
public class FVendor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	@Column(name="VCODE")
	private String vcode;
	@Column(name="VNAME")
	private String vname;
	@Column(name="ADDRESS1")
	private String address1;
	@Column(name="CITY1")
	private String city1;
	@Column(name="STATE1")
	private String state1;
	@Column(name="PHONE")
	private String phone;
	@Column(name="EMAIL")
	private String email;
	@Column(name="NPWP")
	private String npwp;
	
//	@OneToMany(mappedBy="fvendorBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fvendorBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtPurchaseh> ftpurchasehSet;

	@OneToMany(mappedBy="fvendorBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FProduct> fproductSet;

	@OneToMany(mappedBy="fvendorBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FParamDiskonItem> fParamDiskonItemSet;

	@OneToMany(mappedBy="fvendorBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FParamDiskonItemVendor> fParamDiskonItemVendorSet;
	
	@ManyToOne
	@JoinColumn(name="fsalesmanBean", referencedColumnName="spcode")
	private FSalesman fsalesmanBean;
	

	
	public Set<FParamDiskonItemVendor> getfParamDiskonItemVendorSet() {
		return fParamDiskonItemVendorSet;
	}

	public void setfParamDiskonItemVendorSet(
			Set<FParamDiskonItemVendor> fParamDiskonItemVendorSet) {
		this.fParamDiskonItemVendorSet = fParamDiskonItemVendorSet;
	}

	public FSalesman getFsalesmanBean() {
		return fsalesmanBean;
	}

	public void setFsalesmanBean(FSalesman fsalesmanBean) {
		this.fsalesmanBean = fsalesmanBean;
	}

	
	public Set<FParamDiskonItem> getfParamDiskonItemSet() {
		return fParamDiskonItemSet;
	}

	public void setfParamDiskonItemSet(Set<FParamDiskonItem> fParamDiskonItemSet) {
		this.fParamDiskonItemSet = fParamDiskonItemSet;
	}

	public Long getId() {
		return id;
	}

	public String getVcode() {
		return vcode;
	}

	public String getVname() {
		return vname;
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

	public String getEmail() {
		return email;
	}

	public String getNpwp() {
		return npwp;
	}

	public Set<FtPurchaseh> getFtpurchasehSet() {
		return ftpurchasehSet;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public void setVname(String vname) {
		this.vname = vname;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public void setFtpurchasehSet(Set<FtPurchaseh> ftpurchasehSet) {
		this.ftpurchasehSet = ftpurchasehSet;
	}

	public Set<FProduct> getFproductSet() {
		return fproductSet;
	}

	public void setFproductSet(Set<FProduct> fproductSet) {
		this.fproductSet = fproductSet;
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
		FVendor other = (FVendor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  vcode + "-" + vname;
	}
	
	

}