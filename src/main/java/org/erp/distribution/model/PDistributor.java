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
@Table(name="pdistributor")
public class PDistributor {

	@Id
	@Column(name="ID", length=20)
	private String id;
	@Column(name="PDISTRIBUTOR", length=100)
	private String pdistributor;
	
	@OneToMany(mappedBy="pdistributorBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FCustomer> fcustomerSet;

	@ManyToOne
	@JoinColumn(name="pareaBean", referencedColumnName="id")
	private PArea pareaBean;

	public String getId() {
		return id;
	}

	public String getPdistributor() {
		return pdistributor;
	}

	public Set<FCustomer> getFcustomerSet() {
		return fcustomerSet;
	}

	public PArea getPareaBean() {
		return pareaBean;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPdistributor(String pdistributor) {
		this.pdistributor = pdistributor;
	}

	public void setFcustomerSet(Set<FCustomer> fcustomerSet) {
		this.fcustomerSet = fcustomerSet;
	}

	public void setPareaBean(PArea pareaBean) {
		this.pareaBean = pareaBean;
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
		PDistributor other = (PDistributor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + "-" + pdistributor;
	}
	
	

	
}