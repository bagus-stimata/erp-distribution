package org.erp.distribution.model;

import java.util.*;

import javax.persistence.CascadeType;
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
@Table(name="fproductgroup")
public class FProductgroup {

	@Id
	private String id;
	
	private String description;
	private Double disc;
	
	@ManyToOne
	@JoinColumn(name="fproductgroupdivisiBean", referencedColumnName="id")
	private FProductgroupdivisi fproductgroupdivisiBean;
	
	@ManyToOne
	@JoinColumn(name="fproductgroupdeptBean", referencedColumnName="id")
	private FProductgroupdept fproductgroupdeptBean;
	
//	@OneToMany(mappedBy="fproductgroupBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fproductgroupBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FProduct> fproductSet;
//
	@OneToMany(mappedBy="fproductgroupBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FPromo> fpromoSet;
	
	@OneToMany(mappedBy="fproductgroupBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FParamDiskonItem> fparamdiskonitemSet;

	
	
	public Set<FParamDiskonItem> getFparamdiskonitemSet() {
		return fparamdiskonitemSet;
	}

	public void setFparamdiskonitemSet(Set<FParamDiskonItem> fparamdiskonitemSet) {
		this.fparamdiskonitemSet = fparamdiskonitemSet;
	}

	public Set<FPromo> getFpromoSet() {
		return fpromoSet;
	}

	public void setFpromoSet(Set<FPromo> fpromoSet) {
		this.fpromoSet = fpromoSet;
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

	public FProductgroupdivisi getFproductgroupdivisiBean() {
		return fproductgroupdivisiBean;
	}

	public FProductgroupdept getFproductgroupdeptBean() {
		return fproductgroupdeptBean;
	}

	public Set<FProduct> getFproductSet() {
		return fproductSet;
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

	public void setFproductgroupdivisiBean(
			FProductgroupdivisi fproductgroupdivisiBean) {
		this.fproductgroupdivisiBean = fproductgroupdivisiBean;
	}

	public void setFproductgroupdeptBean(FProductgroupdept fproductgroupdeptBean) {
		this.fproductgroupdeptBean = fproductgroupdeptBean;
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
		FProductgroup other = (FProductgroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  id + " - " + description;
	}
	
	

}