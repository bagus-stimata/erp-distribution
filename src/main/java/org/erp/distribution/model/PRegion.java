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
@Table(name="pregion")
public class PRegion {

	@Id
	@Column(name="ID", length=20)
	private String id;
	@Column(name="PREGION", length=100)
	private String pregion;
	
	@Column(name="NATIONALID", length=20)
	private String nationalId;
	
	@OneToMany(mappedBy="pregionBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<PArea> pareaSet;

	public String getId() {
		return id;
	}

	public String getPregion() {
		return pregion;
	}

	public String getNationalId() {
		return nationalId;
	}

	public Set<PArea> getPareaSet() {
		return pareaSet;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPregion(String pregion) {
		this.pregion = pregion;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public void setPareaSet(Set<PArea> pareaSet) {
		this.pareaSet = pareaSet;
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
		PRegion other = (PRegion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + "-" + pregion;
	}
	


	
}