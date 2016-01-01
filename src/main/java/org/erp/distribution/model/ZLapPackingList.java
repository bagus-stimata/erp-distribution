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
@Table(name="zlappackinglist")
public class ZLapPackingList {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="GRUP1")
	String grup1;
	@Column(name="GRUP2")
	String grup2;
	@Column(name="GRUP3")
	String grup3;

	@Column(name="PCODE")
	String pcode;
	@Column(name="PNAME")
	String pname;
	@Column(name="CONVFACT1")
	Integer convfact1;
	@Column(name="CONVFACT2")
	Integer convfact2;
	
	@Column(name="UOM1")
	String uom1;
	@Column(name="UOM2")
	String uom2;
	@Column(name="UOM3")
	String uom3;
	
	@Column(name="SJDATE")
	@Temporal(TemporalType.DATE)
	Date sjdate;
	//**SALDO AWAL

	
	//**SALDO AKHIR
	@Column(name="QTY_PCS")
	Integer qtyPcs;
	
	@Column(name="QTY_BES")
	Integer qtyBes;
	@Column(name="QTY_SED")
	Integer qtySed;
	@Column(name="QTY_KEC")
	Integer qtyKec;

	
	public String getUom3() {
		return uom3;
	}
	public void setUom3(String uom3) {
		this.uom3 = uom3;
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
	public String getPcode() {
		return pcode;
	}
	public String getPname() {
		return pname;
	}
	public Date getSjdate() {
		return sjdate;
	}
	public Integer getQtyPcs() {
		return qtyPcs;
	}
	public Integer getQtyBes() {
		return qtyBes;
	}
	public Integer getQtySed() {
		return qtySed;
	}
	public Integer getQtyKec() {
		return qtyKec;
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
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public void setSjdate(Date sjdate) {
		this.sjdate = sjdate;
	}
	public void setQtyPcs(Integer qtyPcs) {
		this.qtyPcs = qtyPcs;
	}
	public void setQtyBes(Integer qtyBes) {
		this.qtyBes = qtyBes;
	}
	public void setQtySed(Integer qtySed) {
		this.qtySed = qtySed;
	}
	public void setQtyKec(Integer qtyKec) {
		this.qtyKec = qtyKec;
	}
	
	public Integer getConvfact1() {
		return convfact1;
	}
	public Integer getConvfact2() {
		return convfact2;
	}
	public void setConvfact1(Integer convfact1) {
		this.convfact1 = convfact1;
	}
	public void setConvfact2(Integer convfact2) {
		this.convfact2 = convfact2;
	}
	

	
	public String getUom1() {
		return uom1;
	}
	public String getUom2() {
		return uom2;
	}
	public void setUom1(String uom1) {
		this.uom1 = uom1;
	}
	public void setUom2(String uom2) {
		this.uom2 = uom2;
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
		ZLapPackingList other = (ZLapPackingList) obj;
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
