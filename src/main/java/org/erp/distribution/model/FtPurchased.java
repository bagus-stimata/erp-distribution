package org.erp.distribution.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ftpurchased")
public class FtPurchased implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId	
	protected FtPurchasedPK id;
	
	@Column(name="NOURUT")
	private Integer nourut;
	
	@Column(name="PPRICE")
	private Double pprice;
	@Transient
	private Double ppriceafterppn;

	@Transient
	private Integer qty1;
	@Transient
	private Integer qty2;
	@Transient
	private Integer qty3;

	@Column(name="QTY")
	private Integer qty;
	
	@Column(name="DISC1")
	private Double disc1;
	@Transient
	private Double disc1rp;
	@Transient
	private Double disc1rpafterppn;

	@Column(name="DISC2")
	private Double disc2;
	@Transient
	private Double disc2rp;
	@Transient
	private Double disc2rpafterppn;
	
	@Transient
	private Double subtotal;
	@Transient
	private Double subtotalafterppn;

	@Transient
	private Double subtotalafterdisc;
	@Transient
	private Double subtotalafterdiscafterppn;
	
	
	@ManyToOne
	@JoinColumn(name="refno", referencedColumnName="refno")
	private FtPurchaseh ftpurchasehBean;

	@ManyToOne
	@JoinColumn(name="id", referencedColumnName="id")
	private FProduct fproductBean;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FtPurchasedPK getId() {
		return id;
	}

	public Integer getNourut() {
		return nourut;
	}

	public Double getPprice() {
		return pprice;
	}

	public Double getPpriceafterppn() {
		return ppriceafterppn;
	}

	public Integer getQty1() {
		return qty1;
	}

	public Integer getQty2() {
		return qty2;
	}

	public Integer getQty3() {
		return qty3;
	}

	public Integer getQty() {
		return qty;
	}

	public Double getDisc1() {
		return disc1;
	}

	public Double getDisc1rp() {
		return disc1rp;
	}

	public Double getDisc1rpafterppn() {
		return disc1rpafterppn;
	}

	public Double getDisc2() {
		return disc2;
	}

	public Double getDisc2rp() {
		return disc2rp;
	}

	public Double getDisc2rpafterppn() {
		return disc2rpafterppn;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public Double getSubtotalafterppn() {
		return subtotalafterppn;
	}

	public Double getSubtotalafterdisc() {
		return subtotalafterdisc;
	}

	public Double getSubtotalafterdiscafterppn() {
		return subtotalafterdiscafterppn;
	}

	public FtPurchaseh getFtpurchasehBean() {
		return ftpurchasehBean;
	}

	public FProduct getFproductBean() {
		return fproductBean;
	}

	public void setId(FtPurchasedPK id) {
		this.id = id;
	}

	public void setNourut(Integer nourut) {
		this.nourut = nourut;
	}

	public void setPprice(Double pprice) {
		this.pprice = pprice;
	}

	public void setPpriceafterppn(Double ppriceafterppn) {
		this.ppriceafterppn = ppriceafterppn;
	}

	public void setQty1(Integer qty1) {
		this.qty1 = qty1;
	}

	public void setQty2(Integer qty2) {
		this.qty2 = qty2;
	}

	public void setQty3(Integer qty3) {
		this.qty3 = qty3;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public void setDisc1(Double disc1) {
		this.disc1 = disc1;
	}

	public void setDisc1rp(Double disc1rp) {
		this.disc1rp = disc1rp;
	}

	public void setDisc1rpafterppn(Double disc1rpafterppn) {
		this.disc1rpafterppn = disc1rpafterppn;
	}

	public void setDisc2(Double disc2) {
		this.disc2 = disc2;
	}

	public void setDisc2rp(Double disc2rp) {
		this.disc2rp = disc2rp;
	}

	public void setDisc2rpafterppn(Double disc2rpafterppn) {
		this.disc2rpafterppn = disc2rpafterppn;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public void setSubtotalafterppn(Double subtotalafterppn) {
		this.subtotalafterppn = subtotalafterppn;
	}

	public void setSubtotalafterdisc(Double subtotalafterdisc) {
		this.subtotalafterdisc = subtotalafterdisc;
	}

	public void setSubtotalafterdiscafterppn(Double subtotalafterdiscafterppn) {
		this.subtotalafterdiscafterppn = subtotalafterdiscafterppn;
	}

	public void setFtpurchasehBean(FtPurchaseh ftpurchasehBean) {
		this.ftpurchasehBean = ftpurchasehBean;
	}

	public void setFproductBean(FProduct fproductBean) {
		this.fproductBean = fproductBean;
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
		FtPurchased other = (FtPurchased) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
}