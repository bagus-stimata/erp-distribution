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
@Table(name="farea")
public class FArea {

	@Id
	@Column(name="ID", length=10)
	private String id;
	@Column(name="DESCRIPTION", length=100)
	private String description;
	
	@OneToMany(mappedBy="fareaBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FSubarea> fsubareaSet;

	@ManyToOne
	@JoinColumn(name="fsalesmanBean", referencedColumnName="spcode")
	private FSalesman fsalesmanBean;
	
	
	public FSalesman getFsalesmanBean() {
		return fsalesmanBean;
	}

	public void setFsalesmanBean(FSalesman fsalesmanBean) {
		this.fsalesmanBean = fsalesmanBean;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Set<FSubarea> getFsubareaSet() {
		return fsubareaSet;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFsubareaSet(Set<FSubarea> fsubareaSet) {
		this.fsubareaSet = fsubareaSet;
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
		FArea other = (FArea) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  id + " - "  + description;
	}

	
}