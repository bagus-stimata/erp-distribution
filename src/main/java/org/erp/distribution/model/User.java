package org.erp.distribution.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USERID", length=50)
	@Size(min=1, max=50)
	private String userId;
	@Column(name="ACTIVE")
	private boolean active;
	
	@Column(name="USERPASSWORD", length=100)
	private String userPassword;

	@Column(name="FULLNAME", length=100)
	private String fullName;

	@Column(name="GENDER")
	private boolean gender;

	@Column(name="EMAIL", length=100)
	private String email;
	
	@Column(name="ADDRESS", length=100)
	private String address;
	@Column(name="CITY", length=30)
	private String city;
	@Column(name="STATE", length=30)
	private String state;
	
	@Temporal(TemporalType.DATE)
	@Column(name="BIRTHDATE")
	private Date birthDate;
	@Temporal(TemporalType.DATE)
	@Column(name="ENTRYDATE")
	private Date joinDate;
	@Temporal(TemporalType.TIME)
	@Column(name="LASTLOGIN")
	private Date lastLogin;
	
	@Column(name="CREATEBY")
	private String createBy;

	@NotNull
	@Column(name="USEROTORIZETYPE")
	private String userOtorizeType;

	@ManyToOne
	@JoinColumn(name="fdivisionBean", referencedColumnName="id")
	private FDivision fdivisionBean;
	
	
	
	public FDivision getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(FDivision fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserId() {
		return userId;
	}

	public boolean isActive() {
		return active;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getFullName() {
		return fullName;
	}

	public boolean isGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public String getCreateBy() {
		return createBy;
	}

	public String getUserOtorizeType() {
		return userOtorizeType;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setUserOtorizeType(String userOtorizeType) {
		this.userOtorizeType = userOtorizeType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return userId + "";
	}


}