package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the ACCOUNTGROUP database table.
 * 
 */
@Entity
@Table(name="accountgroup")
public class AccAccountgroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", length=10)
	private String id;

	@Column(name="NAME", length=50)
	private String name;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="accountgroup")
	private Set<AccAccount> accounts;

	//bi-directional many-to-one association to Accountgroupreport
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="accountgroupreportBean")
	private Accountgroupreport accountgroupreportBean;

	public AccAccountgroup() {
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

	public Set<AccAccount> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<AccAccount> accounts) {
		this.accounts = accounts;
	}

	public AccAccount addAccount(AccAccount account) {
		getAccounts().add(account);
		account.setAccountgroup(this);

		return account;
	}

	public AccAccount removeAccount(AccAccount account) {
		getAccounts().remove(account);
		account.setAccountgroup(null);

		return account;
	}


	public Accountgroupreport getAccountgroupreportBean() {
		return accountgroupreportBean;
	}

	public void setAccountgroupreportBean(Accountgroupreport accountgroupreportBean) {
		this.accountgroupreportBean = accountgroupreportBean;
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
		AccAccountgroup other = (AccAccountgroup) obj;
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