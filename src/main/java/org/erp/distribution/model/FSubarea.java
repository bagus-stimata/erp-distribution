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
@Table(name="fsubarea")
public class FSubarea {

	@Id
	private String id;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="fareaBean", referencedColumnName="id")
	private FArea fareaBean;
	
//	@OneToMany(mappedBy="fsubareaBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fsubareaBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FCustomer> fcustomerSet;

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public FArea getFareaBean() {
		return fareaBean;
	}

	public Set<FCustomer> getFcustomerSet() {
		return fcustomerSet;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFareaBean(FArea fareaBean) {
		this.fareaBean = fareaBean;
	}

	public void setFcustomerSet(Set<FCustomer> fcustomerSet) {
		this.fcustomerSet = fcustomerSet;
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
		FSubarea other = (FSubarea) obj;
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