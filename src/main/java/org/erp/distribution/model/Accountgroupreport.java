package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;

/**
 * The persistent class for the ACCOUNTGROUPREPORT database table.
 * 
 */
@Entity
@Table(name="accountgroupreport")
@NamedQuery(name="Accountgroupreport.findAll", query="SELECT a FROM Accountgroupreport a")
public class Accountgroupreport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", length=10)
	private String id;

	@Column(name="NAME", length=50)
	private String name;

	//bi-directional many-to-one association to Accountgroup
	@OneToMany(mappedBy="accountgroupreportBean")
	private Set<AccAccountgroup> accountgroups;

	public Accountgroupreport() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AccAccountgroup> getAccountgroups() {
		return this.accountgroups;
	}

	public void setAccountgroups(Set<AccAccountgroup> accountgroups) {
		this.accountgroups = accountgroups;
	}

	public AccAccountgroup addAccountgroup(AccAccountgroup accountgroup) {
		getAccountgroups().add(accountgroup);
		accountgroup.setAccountgroupreportBean(this);

		return accountgroup;
	}

	public AccAccountgroup removeAccountgroup(AccAccountgroup accountgroup) {
		getAccountgroups().remove(accountgroup);
		accountgroup.setAccountgroupreportBean(null);

		return accountgroup;
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
		Accountgroupreport other = (Accountgroupreport) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " - " + name;
	}

}