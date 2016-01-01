package org.erp.distribution.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="zlapsjpenagihanlist")
public class ZLapSJPenagihanList {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="GRUP1")
	String grup1;
	@Column(name="GRUP2")
	String grup2;
	@Column(name="GRUP3")
	String grup3;

	@Column(name="SJPENGIRIMANNO")
	String sjpengirimanno;
	@Column(name="SJPENGIRIMANDATE")
	@Temporal(TemporalType.DATE)
	Date sjpengirimandate;

	@Column(name="SJPENAGIHANNO")
	String sjpenagihanno;
	@Column(name="SJPENAGIHANDATE")
	@Temporal(TemporalType.DATE)
	Date sjpenagihandate;
	
	@Column(name="SPCODE")
	String spcode;
	@Column(name="SPNAME")
	String spname;
	
	@Column(name="CUSTNO")
	String custno;
	@Column(name="CUSTNAME")
	String custname;
	
	@Column(name="INVOICENO")
	String invoiceno;	
	@Column(name="INVOICEDATE")
	@Temporal(TemporalType.DATE)
	Date invoicedate;
	@Column(name="DUEDATE")
	@Temporal(TemporalType.DATE)
	Date duedate;
	
	@Column(name="TUNAIKREDIT")
	String tunaikredit;	
	
	@Column(name="PRICE1")
	Double price1;
	@Column(name="PRICE2")
	Double price2;
	@Column(name="PRICE3")
	Double price3;
	@Column(name="PRICE4")
	Double price4;
	
	
	
	public Date getDuedate() {
		return duedate;
	}
	public String getTunaikredit() {
		return tunaikredit;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public void setTunaikredit(String tunaikredit) {
		this.tunaikredit = tunaikredit;
	}
	public Date getSjpengirimandate() {
		return sjpengirimandate;
	}
	public Date getSjpenagihandate() {
		return sjpenagihandate;
	}
	public void setSjpengirimandate(Date sjpengirimandate) {
		this.sjpengirimandate = sjpengirimandate;
	}
	public void setSjpenagihandate(Date sjpenagihandate) {
		this.sjpenagihandate = sjpenagihandate;
	}
	public Long getId() {
		return id;
	}
	public String getGrup1() {
		return grup1;
	}
	public String getGrup2() {
		return grup2;
	}
	public String getGrup3() {
		return grup3;
	}
	public String getSjpenagihanno() {
		return sjpenagihanno;
	}
	public String getSpcode() {
		return spcode;
	}
	public String getSpname() {
		return spname;
	}
	public String getCustno() {
		return custno;
	}
	public String getCustname() {
		return custname;
	}
	public String getInvoiceno() {
		return invoiceno;
	}
	public Date getInvoicedate() {
		return invoicedate;
	}
	public Double getPrice1() {
		return price1;
	}
	public Double getPrice2() {
		return price2;
	}
	public Double getPrice3() {
		return price3;
	}
	public Double getPrice4() {
		return price4;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setGrup1(String grup1) {
		this.grup1 = grup1;
	}
	public void setGrup2(String grup2) {
		this.grup2 = grup2;
	}
	public void setGrup3(String grup3) {
		this.grup3 = grup3;
	}
	public void setSjpenagihanno(String sjpenagihanno) {
		this.sjpenagihanno = sjpenagihanno;
	}
	public void setSpcode(String spcode) {
		this.spcode = spcode;
	}
	public void setSpname(String spname) {
		this.spname = spname;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}
	public void setPrice1(Double price1) {
		this.price1 = price1;
	}
	public void setPrice2(Double price2) {
		this.price2 = price2;
	}
	public void setPrice3(Double price3) {
		this.price3 = price3;
	}
	public void setPrice4(Double price4) {
		this.price4 = price4;
	}
	
	
	public String getSjpengirimanno() {
		return sjpengirimanno;
	}
	public void setSjpengirimanno(String sjpengirimanno) {
		this.sjpengirimanno = sjpengirimanno;
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
		ZLapSJPenagihanList other = (ZLapSJPenagihanList) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ZLapPrestasiKerja [id=" + id + "]";
	}
	

	
	
	
	
}
