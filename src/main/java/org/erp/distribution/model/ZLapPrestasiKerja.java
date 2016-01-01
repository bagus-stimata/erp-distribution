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
@Table(name="zlapprestasikerja")
public class ZLapPrestasiKerja {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="GRUP1")
	String grup1;
	@Column(name="GRUP2")
	String grup2;
	
	@Column(name="HARI")
	String hari;
	@Temporal(TemporalType.DATE)
	Date tanggal;
	
	@Column(name="JUMLAHTOKO")
	Integer jumlahToko;
	@Column(name="EFECTIVECALL")
	Integer efectiveCall;
	@Column(name="SKUSOLD")
	Integer skuSold;
	// SKU / EC
	@Column(name="RATARATA")
	Double rataRata;
	@Column(name="TOTALBEFOREDISCBEFOREPPN")
	Double totalBeforediscBeforeppn;
	@Column(name="DISCPERBARANG")
	Double discPerbarang;
	@Column(name="DISCNOTA")
	Double discNota;
	@Column(name="DPP")
	Double dpp;
	@Column(name="PPN")
	Double ppn;
	@Column(name="TOTALAFTERDISCAFTERPPN")
	Double totalAfterdiscAfterppn;

	@Column(name="STRING1")
	String string1;
	@Column(name="STRING2")
	String string2;
	@Column(name="INTEGER1")
	Integer integer1;
	@Column(name="INTEGER2")
	Integer integer2;
	@Column(name="DOUBLE1")
	Double double1;
	@Column(name="DOUBLE2")
	Double double2;
	@Temporal(TemporalType.DATE)
	@Column(name="DATE1")
	Date date1;
	@Temporal(TemporalType.DATE)
	@Column(name="DATE2")
	Date date2;
	
	
	public Long getId() {
		return id;
	}
	public Date getTanggal() {
		return tanggal;
	}
	public Integer getJumlahToko() {
		return jumlahToko;
	}
	public Integer getSkuSold() {
		return skuSold;
	}
	public Double getDiscPerbarang() {
		return discPerbarang;
	}
	public Double getDiscNota() {
		return discNota;
	}
	public Double getDpp() {
		return dpp;
	}
	public Double getPpn() {
		return ppn;
	}
	public Double getTotalAfterdiscAfterppn() {
		return totalAfterdiscAfterppn;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}
	public void setJumlahToko(Integer jumlahToko) {
		this.jumlahToko = jumlahToko;
	}
	public void setSkuSold(Integer skuSold) {
		this.skuSold = skuSold;
	}
	public void setDiscPerbarang(Double discPerbarang) {
		this.discPerbarang = discPerbarang;
	}
	public void setDiscNota(Double discNota) {
		this.discNota = discNota;
	}
	
	public Double getRataRata() {
		return rataRata;
	}
	public void setRataRata(Double rataRata) {
		this.rataRata = rataRata;
	}
	public void setDpp(Double dpp) {
		this.dpp = dpp;
	}
	public void setPpn(Double ppn) {
		this.ppn = ppn;
	}
	public void setTotalAfterdiscAfterppn(Double totalAfterdiscAfterppn) {
		this.totalAfterdiscAfterppn = totalAfterdiscAfterppn;
	}
	public String getHari() {
		return hari;
	}
	public String getString1() {
		return string1;
	}
	public String getString2() {
		return string2;
	}
	public Integer getInteger1() {
		return integer1;
	}
	public Integer getInteger2() {
		return integer2;
	}
	public Double getDouble1() {
		return double1;
	}
	public Double getDouble2() {
		return double2;
	}
	public Date getDate1() {
		return date1;
	}
	public Date getDate2() {
		return date2;
	}
	public void setHari(String hari) {
		this.hari = hari;
	}
	public void setString1(String string1) {
		this.string1 = string1;
	}
	public void setString2(String string2) {
		this.string2 = string2;
	}
	public void setInteger1(Integer integer1) {
		this.integer1 = integer1;
	}
	public void setInteger2(Integer integer2) {
		this.integer2 = integer2;
	}
	public void setDouble1(Double double1) {
		this.double1 = double1;
	}
	public void setDouble2(Double double2) {
		this.double2 = double2;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public Integer getEfectiveCall() {
		return efectiveCall;
	}
	public void setEfectiveCall(Integer efectiveCall) {
		this.efectiveCall = efectiveCall;
	}
	
	public Double getTotalBeforediscBeforeppn() {
		return totalBeforediscBeforeppn;
	}
	public void setTotalBeforediscBeforeppn(Double totalBeforediscBeforeppn) {
		this.totalBeforediscBeforeppn = totalBeforediscBeforeppn;
	}
	
	
	public String getGrup1() {
		return grup1;
	}
	public String getGrup2() {
		return grup2;
	}
	public void setGrup1(String grup1) {
		this.grup1 = grup1;
	}
	public void setGrup2(String grup2) {
		this.grup2 = grup2;
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
		ZLapPrestasiKerja other = (ZLapPrestasiKerja) obj;
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
