package org.erp.distribution.model;

import java.util.Set;

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
@Table(name="fcustomersubgroup")
public class FCustomersubgroup {

	@Id
	@Column(name="ID", length=15)
	private String id;
	@Column(name="DESCRIPTION", length=100)
	private String description;
	@Column(name="DISC")
	private Double disc;
	
//	@ManyToOne
//	@JoinColumn(name="ftpricehBean", referencedColumnName="refno")
//	private FtPriceh ftpricehBean;
	
//	@ManyToOne
//	@JoinColumn(name="ftspricealthBean", referencedColumnName="refno")
//	private FtPriceAlth ftspricealthBean;
	
	@ManyToOne
	@JoinColumn(name="fcustomergroupBean", referencedColumnName="id")
	private FCustomergroup fcustomergroupBean;
	
	@OneToMany(mappedBy="fcustomersubgroupBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FCustomer> fcustomerSet;

	@OneToMany(mappedBy="fcustomersubgroupBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FParamDiskonNota> fParamDiskonSet;

	@OneToMany(mappedBy="fcustomersubgroupBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FParamDiskonNota> fParamDiskonItemSet;
	
	@ManyToOne
	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="refno", nullable=true)
	private FtPriceAlth ftPriceAlthBean;
	
	@OneToMany(mappedBy="fcustomersubgroupBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FPromo> fpromoSet;

	
	
	public Set<FParamDiskonNota> getfParamDiskonItemSet() {
		return fParamDiskonItemSet;
	}

	public void setfParamDiskonItemSet(Set<FParamDiskonNota> fParamDiskonItemSet) {
		this.fParamDiskonItemSet = fParamDiskonItemSet;
	}

	public Set<FPromo> getFpromoSet() {
		return fpromoSet;
	}

	public void setFpromoSet(Set<FPromo> fpromoSet) {
		this.fpromoSet = fpromoSet;
	}

	public FtPriceAlth getFtPriceAlthBean() {
		return ftPriceAlthBean;
	}

	public void setFtPriceAlthBean(FtPriceAlth ftPriceAlthBean) {
		this.ftPriceAlthBean = ftPriceAlthBean;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Double getDisc() {
		return disc;
	}


	public FCustomergroup getFcustomergroupBean() {
		return fcustomergroupBean;
	}

	public Set<FCustomer> getFcustomerSet() {
		return fcustomerSet;
	}

	public Set<FParamDiskonNota> getfParamDiskonSet() {
		return fParamDiskonSet;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDisc(Double disc) {
		this.disc = disc;
	}
	public void setFcustomergroupBean(FCustomergroup fcustomergroupBean) {
		this.fcustomergroupBean = fcustomergroupBean;
	}

	public void setFcustomerSet(Set<FCustomer> fcustomerSet) {
		this.fcustomerSet = fcustomerSet;
	}

	public void setfParamDiskonSet(Set<FParamDiskonNota> fParamDiskonSet) {
		this.fParamDiskonSet = fParamDiskonSet;
	}

	@Override
	public String toString() {
		return   id + " - " + description;
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
		FCustomersubgroup other = (FCustomersubgroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}