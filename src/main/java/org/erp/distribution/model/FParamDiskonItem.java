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
@Table(name="fparamdiskonitem")
public class FParamDiskonItem implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="NOREK", length=15)
	private String noRek;
	
	@Column(name="DESCRIPTION", length=300)
	private String description;

	//Value
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

	//Qty In PCS
	private Integer qtyLebihDari1;
	private Double diskonFromQty1;
	private Double diskonFromQty1plus;
	
	private Integer qtyLebihDari2;
	private Double diskonFromQty2;
	private Double diskonFromQty2plus;
	
	private Integer qtyLebihDari3;
	private Double diskonFromQty3;
	private Double diskonFromQty3plus;

	private Integer qtyLebihDari4;
	private Double diskonFromQty4;
	private Double diskonFromQty4plus;

	private Integer qtyLebihDari5;
	private Double diskonFromQty5;
	private Double diskonFromQty5plus;
	
	private Boolean allvendor;
	@ManyToOne
	@JoinColumn(name="fvendorBean", referencedColumnName="id")
	private FVendor fvendorBean;
	
	private Boolean allsubgrup;
	@ManyToOne
	@JoinColumn(name="fcustomersubgroupBean", referencedColumnName="id")
	private FCustomersubgroup fcustomersubgroupBean;

	private Boolean allproductgrup;
	@ManyToOne
	@JoinColumn(name="fproductgroupBean", referencedColumnName="id")
	private FProductgroup fproductgroupBean;
	
	private Boolean alltunaikredit;
	@Column(length=5)
	private String tunaikredit;

	private Boolean statusActive;
	

	
	
	public Integer getQtyLebihDari5() {
		return qtyLebihDari5;
	}
	public Double getDiskonFromQty5() {
		return diskonFromQty5;
	}
	public Double getDiskonFromQty5plus() {
		return diskonFromQty5plus;
	}
	public void setQtyLebihDari5(Integer qtyLebihDari5) {
		this.qtyLebihDari5 = qtyLebihDari5;
	}
	public void setDiskonFromQty5(Double diskonFromQty5) {
		this.diskonFromQty5 = diskonFromQty5;
	}
	public void setDiskonFromQty5plus(Double diskonFromQty5plus) {
		this.diskonFromQty5plus = diskonFromQty5plus;
	}
	public String getNoRek() {
		return noRek;
	}
	public void setNoRek(String noRek) {
		this.noRek = noRek;
	}
	public Integer getQtyLebihDari1() {
		return qtyLebihDari1;
	}
	public Double getDiskonFromQty1() {
		return diskonFromQty1;
	}
	public Double getDiskonFromQty1plus() {
		return diskonFromQty1plus;
	}
	public Integer getQtyLebihDari2() {
		return qtyLebihDari2;
	}
	public Double getDiskonFromQty2() {
		return diskonFromQty2;
	}
	public Double getDiskonFromQty2plus() {
		return diskonFromQty2plus;
	}
	public Integer getQtyLebihDari3() {
		return qtyLebihDari3;
	}
	public Double getDiskonFromQty3() {
		return diskonFromQty3;
	}
	public Double getDiskonFromQty3plus() {
		return diskonFromQty3plus;
	}
	public Integer getQtyLebihDari4() {
		return qtyLebihDari4;
	}
	public Double getDiskonFromQty4() {
		return diskonFromQty4;
	}
	public Double getDiskonFromQty4plus() {
		return diskonFromQty4plus;
	}
	public void setQtyLebihDari1(Integer qtyLebihDari1) {
		this.qtyLebihDari1 = qtyLebihDari1;
	}
	public void setDiskonFromQty1(Double diskonFromQty1) {
		this.diskonFromQty1 = diskonFromQty1;
	}
	public void setDiskonFromQty1plus(Double diskonFromQty1plus) {
		this.diskonFromQty1plus = diskonFromQty1plus;
	}
	public void setQtyLebihDari2(Integer qtyLebihDari2) {
		this.qtyLebihDari2 = qtyLebihDari2;
	}
	public void setDiskonFromQty2(Double diskonFromQty2) {
		this.diskonFromQty2 = diskonFromQty2;
	}
	public void setDiskonFromQty2plus(Double diskonFromQty2plus) {
		this.diskonFromQty2plus = diskonFromQty2plus;
	}
	public void setQtyLebihDari3(Integer qtyLebihDari3) {
		this.qtyLebihDari3 = qtyLebihDari3;
	}
	public void setDiskonFromQty3(Double diskonFromQty3) {
		this.diskonFromQty3 = diskonFromQty3;
	}
	public void setDiskonFromQty3plus(Double diskonFromQty3plus) {
		this.diskonFromQty3plus = diskonFromQty3plus;
	}
	public void setQtyLebihDari4(Integer qtyLebihDari4) {
		this.qtyLebihDari4 = qtyLebihDari4;
	}
	public void setDiskonFromQty4(Double diskonFromQty4) {
		this.diskonFromQty4 = diskonFromQty4;
	}
	public void setDiskonFromQty4plus(Double diskonFromQty4plus) {
		this.diskonFromQty4plus = diskonFromQty4plus;
	}
	public Boolean getAllproductgrup() {
		return allproductgrup;
	}
	public FProductgroup getFproductgroupBean() {
		return fproductgroupBean;
	}
	public void setAllproductgrup(Boolean allproductgrup) {
		this.allproductgrup = allproductgrup;
	}
	public void setFproductgroupBean(FProductgroup fproductgroupBean) {
		this.fproductgroupBean = fproductgroupBean;
	}
	public Boolean getStatusActive() {
		return statusActive;
	}
	public void setStatusActive(Boolean statusActive) {
		this.statusActive = statusActive;
	}
	public Boolean getAllvendor() {
		return allvendor;
	}
	public FVendor getFvendorBean() {
		return fvendorBean;
	}
	public void setAllvendor(Boolean allvendor) {
		this.allvendor = allvendor;
	}
	public void setFvendorBean(FVendor fvendorBean) {
		this.fvendorBean = fvendorBean;
	}
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		FParamDiskonItem other = (FParamDiskonItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}