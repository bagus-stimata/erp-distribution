package org.erp.distribution.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="fproductgroupdept")
public class FProductgroupdept {

	@Id
	private String id;
	
	private String description;
	
//	@OneToMany(mappedBy="fproductgroupdeptBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fproductgroupdeptBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FProductgroup> fproductgroupSet;

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Set<FProductgroup> getFproductgroupSet() {
		return fproductgroupSet;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFproductgroupSet(Set<FProductgroup> fproductgroupSet) {
		this.fproductgroupSet = fproductgroupSet;
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
		FProductgroupdept other = (FProductgroupdept) obj;
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