package org.erp.distribution.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="fcustomer")
public class FCustomer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="CUSTNO", length=15)
	private String custno;
	@Column(name="OUTLETACTIVE")
	private Boolean outletActive;
	
	@Column(name="CUSTNAME", length=100)
	private String custname;
	@Column(name="NAMAPADAFAKTURPAJAK", length=150)
	private String namaPadaFakturPajak;
	@Column(name="TUNAIKREDIT", length=5)
	private String tunaikredit;
	@Column(name="TOP")
	private Integer top;
	@Column(name="CREDITLIMIT")
	private Double creditlimit;
	@Column(name="OPENINVOICE")
	private Integer openinvoice;
	
	@Column(name="ADDRESS1", length=120)
	private String address1;
	@Column(name="CITY1", length=50)
	private String city1;
	@Column(name="STATE1", length=50)
	private String state1;
	@Column(name="PHONE1", length=50)
	private String phone1;
	@Column(name="ADDRESS2", length=120)
	private String address2;
	@Column(name="CITY2", length=50)
	private String city2;
	@Column(name="STATE2", length=50)
	private String state2;
	@Column(name="PHONE2", length=50)
	private String phone2;
	@Column(name="NPWP", length=120)
	private String npwp;
	@Column(name="EMAIL", length=120)
	private String email;
	@Column(name="STATUSACTIVE")
	private Boolean statusactive;

	@Column(name="HARIKUNJUNGAN")
	private Integer harikunjungan;
	@Column(name="PEKANKUNJUNGAN")
	private Integer pekankunjungan;
	
	@Column(name="NOEFFCALL")
	private boolean noeffcall;

	@Column(name="LATITUDE")
	private double latitude;
	@Column(name="LONGITUDE")
	private double longitude;
	
	@ManyToOne
	@JoinColumn(name="fcustomersubgroupBean", referencedColumnName="id")
	private FCustomersubgroup fcustomersubgroupBean;
	
	@ManyToOne
	@JoinColumn(name="fsubareaBean", referencedColumnName="id")
	private FSubarea fsubareaBean;

	@ManyToOne
	@JoinColumn(name="fchannelBean", referencedColumnName="id")
	private FChannel fchannelBean;

	@ManyToOne
	@JoinColumn(name="pdistributorBean", referencedColumnName="id")
	private PDistributor pdistributorBean;
	
//	@OneToMany(mappedBy="fcustomerBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fcustomerBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtSalesh> ftsaleshSet;
	
	@ManyToOne
	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="refno", nullable=true)
	private FtPriceAlth ftPriceAlthBean;
	

	public Long getId() {
		return id;
	}

	public String getCustno() {
		return custno;
	}

	public String getCustname() {
		return custname;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTunaikredit() {
		return tunaikredit;
	}

	public Integer getTop() {
		return top;
	}

	public Double getCreditlimit() {
		return creditlimit;
	}

	public Integer getOpeninvoice() {
		return openinvoice;
	}

	public String getAddress1() {
		return address1;
	}

	public String getCity1() {
		return city1;
	}

	public String getState1() {
		return state1;
	}

	public String getPhone1() {
		return phone1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCity2() {
		return city2;
	}

	public String getState2() {
		return state2;
	}

	public String getPhone2() {
		return phone2;
	}

	public String getNpwp() {
		return npwp;
	}

	public Boolean getStatusactive() {
		return statusactive;
	}

	public FCustomersubgroup getFcustomersubgroupBean() {
		return fcustomersubgroupBean;
	}

	public FSubarea getFsubareaBean() {
		return fsubareaBean;
	}

	public Set<FtSalesh> getFtsaleshSet() {
		return ftsaleshSet;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public void setTunaikredit(String tunaikredit) {
		this.tunaikredit = tunaikredit;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public void setCreditlimit(Double creditlimit) {
		this.creditlimit = creditlimit;
	}

	public void setOpeninvoice(Integer openinvoice) {
		this.openinvoice = openinvoice;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public void setStatusactive(Boolean statusactive) {
		this.statusactive = statusactive;
	}

	public void setFcustomersubgroupBean(FCustomersubgroup fcustomersubgroupBean) {
		this.fcustomersubgroupBean = fcustomersubgroupBean;
	}

	public void setFsubareaBean(FSubarea fsubareaBean) {
		this.fsubareaBean = fsubareaBean;
	}

	public void setFtsaleshSet(Set<FtSalesh> ftsaleshSet) {
		this.ftsaleshSet = ftsaleshSet;
	}

	
	public FtPriceAlth getFtPriceAlthBean() {
		return ftPriceAlthBean;
	}

	public void setFtPriceAlthBean(FtPriceAlth ftPriceAlthBean) {
		this.ftPriceAlthBean = ftPriceAlthBean;
	}

	public String getNamaPadaFakturPajak() {
		return namaPadaFakturPajak;
	}

	public void setNamaPadaFakturPajak(String namaPadaFakturPajak) {
		this.namaPadaFakturPajak = namaPadaFakturPajak;
	}

	public FChannel getFchannelBean() {
		return fchannelBean;
	}

	public PDistributor getPdistributorBean() {
		return pdistributorBean;
	}

	public void setFchannelBean(FChannel fchannelBean) {
		this.fchannelBean = fchannelBean;
	}

	public void setPdistributorBean(PDistributor pdistributorBean) {
		this.pdistributorBean = pdistributorBean;
	}
	

	public Boolean getOutletActive() {
		return outletActive;
	}

	public Integer getHarikunjungan() {
		return harikunjungan;
	}

	public Integer getPekankunjungan() {
		return pekankunjungan;
	}

	public void setOutletActive(Boolean outletActive) {
		this.outletActive = outletActive;
	}

	public void setHarikunjungan(Integer harikunjungan) {
		this.harikunjungan = harikunjungan;
	}

	public void setPekankunjungan(Integer pekankunjungan) {
		this.pekankunjungan = pekankunjungan;
	}

	
	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isNoeffcall() {
		return noeffcall;
	}

	public void setNoeffcall(boolean noeffcall) {
		this.noeffcall = noeffcall;
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
		FCustomer other = (FCustomer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return custno + " - " + custname +" - " + address1;
	}
	
	

}