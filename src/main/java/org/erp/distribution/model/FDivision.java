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
@Table(name="fdivision")
public class FDivision {

	@Id
	@Column(name="ID", length=5)
	private String id;
	@Column(name="DESCRIPTION", length=100)
	private String description;

	@Column(name="STATUSACTIVE")
	private Boolean statusactive;
	
	@OneToMany(mappedBy="fdivisionBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<User> userSet;
	
	@OneToMany(mappedBy="fdivisionBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FWarehouse> fwarehouseSet;
	
	
	public Boolean getStatusactive() {
		return statusactive;
	}
	public void setStatusactive(Boolean statusactive) {
		this.statusactive = statusactive;
	}
	public Set<FWarehouse> getFwarehouseSet() {
		return fwarehouseSet;
	}
	public void setFwarehouseSet(Set<FWarehouse> fwarehouseSet) {
		this.fwarehouseSet = fwarehouseSet;
	}
	public Set<User> getUserSet() {
		return userSet;
	}
	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}
	public String getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setDescription(String description) {
		this.description = description;
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
		FDivision other = (FDivision) obj;
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