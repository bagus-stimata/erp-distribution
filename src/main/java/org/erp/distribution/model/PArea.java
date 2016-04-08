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
@Table(name="parea")
public class PArea {

	@Id
	@Column(name="ID", length=20)
	private String id;
	@Column(name="PAREA", length=100)
	private String parea;
	
	@OneToMany(mappedBy="pareaBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<PDistributor> pdistributorSet;

	@ManyToOne
	@JoinColumn(name="pregionBean", referencedColumnName="id")
	private PRegion pregionBean;

	public String getId() {
		return id;
	}

	public String getParea() {
		return parea;
	}

	public Set<PDistributor> getPdistributorSet() {
		return pdistributorSet;
	}

	public PRegion getPregionBean() {
		return pregionBean;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setParea(String parea) {
		this.parea = parea;
	}

	public void setPdistributorSet(Set<PDistributor> pdistributorSet) {
		this.pdistributorSet = pdistributorSet;
	}

	public void setPregionBean(PRegion pregionBean) {
		this.pregionBean = pregionBean;
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
		PArea other = (PArea) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + "-" + parea;
	}
	
	


	
}