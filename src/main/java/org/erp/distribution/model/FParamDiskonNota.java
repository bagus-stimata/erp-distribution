package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the BANK database table.
 * 
 */

@Entity
@Table(name="fparamdiskonnota")
public class FParamDiskonNota implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Double nominal1;
	private Double diskon1;
	private Double diskon1plus;

	private Double nominal2;
	private Double diskon2;
	private Double diskon2plus;

	private Double nominal3;
	private Double diskon3;
	private Double diskon3plus;

	private Double nominal4;
	private Double diskon4;
	private Double diskon4plus;

	private Double nominal5;
	private Double diskon5;
	private Double diskon5plus;
	

	private Boolean allsubgrup;
	@ManyToOne
	@JoinColumn(name="fcustomersubgroupBean", referencedColumnName="id")
	private FCustomersubgroup fcustomersubgroupBean;
	
	private Boolean alltunaikredit;
	@Column(length=5)
	private String tunaikredit;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getId() {
		return id;
	}
	public Double getNominal1() {
		return nominal1;
	}
	public Double getDiskon1() {
		return diskon1;
	}
	public Double getDiskon1plus() {
		return diskon1plus;
	}
	public Double getNominal2() {
		return nominal2;
	}
	public Double getDiskon2() {
		return diskon2;
	}
	public Double getDiskon2plus() {
		return diskon2plus;
	}
	public Double getNominal3() {
		return nominal3;
	}
	public Double getDiskon3() {
		return diskon3;
	}
	public Double getDiskon3plus() {
		return diskon3plus;
	}
	public Double getNominal4() {
		return nominal4;
	}
	public Double getDiskon4() {
		return diskon4;
	}
	public Double getDiskon4plus() {
		return diskon4plus;
	}
	public Double getNominal5() {
		return nominal5;
	}
	public Double getDiskon5() {
		return diskon5;
	}
	public Double getDiskon5plus() {
		return diskon5plus;
	}
	public Boolean getAllsubgrup() {
		return allsubgrup;
	}
	public FCustomersubgroup getFcustomersubgroupBean() {
		return fcustomersubgroupBean;
	}
	public Boolean getAlltunaikredit() {
		return alltunaikredit;
	}
	public String getTunaikredit() {
		return tunaikredit;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNominal1(Double nominal1) {
		this.nominal1 = nominal1;
	}
	public void setDiskon1(Double diskon1) {
		this.diskon1 = diskon1;
	}
	public void setDiskon1plus(Double diskon1plus) {
		this.diskon1plus = diskon1plus;
	}
	public void setNominal2(Double nominal2) {
		this.nominal2 = nominal2;
	}
	public void setDiskon2(Double diskon2) {
		this.diskon2 = diskon2;
	}
	public void setDiskon2plus(Double diskon2plus) {
		this.diskon2plus = diskon2plus;
	}
	public void setNominal3(Double nominal3) {
		this.nominal3 = nominal3;
	}
	public void setDiskon3(Double diskon3) {
		this.diskon3 = diskon3;
	}
	public void setDiskon3plus(Double diskon3plus) {
		this.diskon3plus = diskon3plus;
	}
	public void setNominal4(Double nominal4) {
		this.nominal4 = nominal4;
	}
	public void setDiskon4(Double diskon4) {
		this.diskon4 = diskon4;
	}
	public void setDiskon4plus(Double diskon4plus) {
		this.diskon4plus = diskon4plus;
	}
	public void setNominal5(Double nominal5) {
		this.nominal5 = nominal5;
	}
	public void setDiskon5(Double diskon5) {
		this.diskon5 = diskon5;
	}
	public void setDiskon5plus(Double diskon5plus) {
		this.diskon5plus = diskon5plus;
	}
	public void setAllsubgrup(Boolean allsubgrup) {
		this.allsubgrup = allsubgrup;
	}
	public void setFcustomersubgroupBean(FCustomersubgroup fcustomersubgroupBean) {
		this.fcustomersubgroupBean = fcustomersubgroupBean;
	}
	public void setAlltunaikredit(Boolean alltunaikredit) {
		this.alltunaikredit = alltunaikredit;
	}
	public void setTunaikredit(String tunaikredit) {
		this.tunaikredit = tunaikredit;
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
		FParamDiskonNota other = (FParamDiskonNota) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}