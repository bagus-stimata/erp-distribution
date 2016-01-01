package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FCustomerSpecDiscPK implements Serializable{

	@Column(name="idcust", insertable=false, updatable=false)
	private Long idCust;
	@Column(name="idproduct", insertable=false, updatable=false)
	private Long idProduct;
	
	public Long getIdCust() {
		return idCust;
	}
	public Long getIdProduct() {
		return idProduct;
	}
	public void setIdCust(Long idCust) {
		this.idCust = idCust;
	}
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCust == null) ? 0 : idCust.hashCode());
		result = prime * result
				+ ((idProduct == null) ? 0 : idProduct.hashCode());
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
		FCustomerSpecDiscPK other = (FCustomerSpecDiscPK) obj;
		if (idCust == null) {
			if (other.idCust != null)
				return false;
		} else if (!idCust.equals(other.idCust))
			return false;
		if (idProduct == null) {
			if (other.idProduct != null)
				return false;
		} else if (!idProduct.equals(other.idProduct))
			return false;
		return true;
	}
	
	

}