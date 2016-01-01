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
@Table(name="zlapaktifitaspromolist")
public class ZLapAktifitasPromoList {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="GRUP1")
	String grup1;
	@Column(name="GRUP2")
	String grup2;
	@Column(name="GRUP3")
	String grup3;


	@Column(name="PROMOID")
	private String promoid;
	@Column(name="PROMODESC")
	private String promodesc;
	
	@Column(name="INVOICENO")
	private String invoiceno;
	@Column(name="INVOICEDATE")
	@Temporal(TemporalType.DATE)
	private Date invoicedate;
	
	@Column(name="CUSTAREA")
	private String custarea;
	@Column(name="CUSTSUBAREA")
	private String custsubarea;
	@Column(name="CUSTGROUP")
	private String custgroup;
	@Column(name="CUSTSUBGROUP")
	private String custsubgroup;
	
	@Column(name="CUSTNO")
	private String custno;
	@Column(name="CUSTNAME")
	private String custname;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="CITY")
	private String city;
	
	//NILAI NOTA
	@Column(name="SUBTOTALAFTERDISCAFTERPPN")
	private Double subtotalafterdiscafterppn;

	//PROMO BARANG
	@Column(name="fREEBONUSPCODE")
	private String freebonuspcode;
	@Column(name="fREEBONUSPNAME")
	private String freebonuspname;

	@Column(name="fREEBONUSQTYPCS")
	private Integer freebonusqtypcs;
	@Column(name="fREEBONUSQTYBES")
	private Integer freebonusqtybes;
	@Column(name="fREEBONUSQTYSED")
	private Integer freebonusqtysed;
	@Column(name="fREEBONUSQTYKEC")
	private Integer freebonusqtykec;
	
	@Column(name="fREEBONUSAFTERPPN")
	private Double freebonusafterppn;
	
	//PROMO DISC
	
	@Column(name="DISC1")
	private Double disc1;	
	@Column(name="DISC2")
	private Double disc2;

	@Column(name="DISC1AFTERPPN")
	private Double disc1afterppn;
	@Column(name="DISC2AFTERPPN")
	private Double disc2afterppn;
	
	//PROMO CASHBACK
	
	@Column(name="CASHBACKAFTERPPN")
	private Double cashbackafterppn;

	
	
	
	public Integer getFreebonusqtypcs() {
		return freebonusqtypcs;
	}

	public Integer getFreebonusqtybes() {
		return freebonusqtybes;
	}

	public Integer getFreebonusqtysed() {
		return freebonusqtysed;
	}

	public Integer getFreebonusqtykec() {
		return freebonusqtykec;
	}

	public void setFreebonusqtypcs(Integer freebonusqtypcs) {
		this.freebonusqtypcs = freebonusqtypcs;
	}

	public void setFreebonusqtybes(Integer freebonusqtybes) {
		this.freebonusqtybes = freebonusqtybes;
	}

	public void setFreebonusqtysed(Integer freebonusqtysed) {
		this.freebonusqtysed = freebonusqtysed;
	}

	public void setFreebonusqtykec(Integer freebonusqtykec) {
		this.freebonusqtykec = freebonusqtykec;
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

	public String getInvoiceno() {
		return invoiceno;
	}

	public Date getInvoicedate() {
		return invoicedate;
	}

	public String getCustgroup() {
		return custgroup;
	}

	public String getCustsubarea() {
		return custsubarea;
	}

	public String getCustsubgroup() {
		return custsubgroup;
	}

	public String getCustno() {
		return custno;
	}

	public String getCustname() {
		return custname;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public Double getSubtotalafterdiscafterppn() {
		return subtotalafterdiscafterppn;
	}

	public Double getFreebonusafterppn() {
		return freebonusafterppn;
	}

	public Double getDisc1() {
		return disc1;
	}

	public Double getDisc2() {
		return disc2;
	}

	public Double getDisc1afterppn() {
		return disc1afterppn;
	}

	public Double getDisc2afterppn() {
		return disc2afterppn;
	}

	public Double getCashbackafterppn() {
		return cashbackafterppn;
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

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}

	public void setCustgroup(String custgroup) {
		this.custgroup = custgroup;
	}

	public void setCustsubarea(String custsubarea) {
		this.custsubarea = custsubarea;
	}

	public void setCustsubgroup(String custsubgroup) {
		this.custsubgroup = custsubgroup;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setSubtotalafterdiscafterppn(Double subtotalafterdiscafterppn) {
		this.subtotalafterdiscafterppn = subtotalafterdiscafterppn;
	}

	public void setFreebonusafterppn(Double freebonusafterppn) {
		this.freebonusafterppn = freebonusafterppn;
	}

	public void setDisc1(Double disc1) {
		this.disc1 = disc1;
	}

	public void setDisc2(Double disc2) {
		this.disc2 = disc2;
	}

	public void setDisc1afterppn(Double disc1afterppn) {
		this.disc1afterppn = disc1afterppn;
	}

	public void setDisc2afterppn(Double disc2afterppn) {
		this.disc2afterppn = disc2afterppn;
	}

	public void setCashbackafterppn(Double cashbackafterppn) {
		this.cashbackafterppn = cashbackafterppn;
	}

	public String getFreebonuspcode() {
		return freebonuspcode;
	}

	public String getFreebonuspname() {
		return freebonuspname;
	}

	public void setFreebonuspcode(String freebonuspcode) {
		this.freebonuspcode = freebonuspcode;
	}

	public void setFreebonuspname(String freebonuspname) {
		this.freebonuspname = freebonuspname;
	}

	public String getCustarea() {
		return custarea;
	}

	public void setCustarea(String custarea) {
		this.custarea = custarea;
	}

	public String getPromoid() {
		return promoid;
	}

	public String getPromodesc() {
		return promodesc;
	}

	public void setPromoid(String promoid) {
		this.promoid = promoid;
	}

	public void setPromodesc(String promodesc) {
		this.promodesc = promodesc;
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
		ZLapAktifitasPromoList other = (ZLapAktifitasPromoList) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
