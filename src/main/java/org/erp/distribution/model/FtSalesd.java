package org.erp.distribution.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="ftsalesd")
public class FtSalesd implements Serializable{

	@EmbeddedId
	protected FtSalesdPK id;
	
	private Boolean promo;
	
	@Column(name="NOURUT")
	private Integer nourut;
	
	@Column(name="SPRICE")
	private Double sprice;
	@Transient
	private Double spriceafterppn;

	@Transient
	private Integer qty1;
	@Transient
	private Integer qty2;
	@Transient
	private Integer qty3;

	@Column(name="QTY")
	private Integer qty;
	
	@Transient
	private Double subtotal;
	@Transient
	private Double subtotalafterppn;
	
	@Column(name="DISC1")
	private Double disc1;
	@Transient
	private Double disc1rp;
	@Transient
	private Double disc1rpafterppn;

	@Transient
	private Double subtotalafterdisc1;
	@Transient
	private Double subtotalafterdisc1afterppn;
	
	@Column(name="DISC2")
	private Double disc2;
	@Transient
	private Double disc2rp;
	@Transient
	private Double disc2rpafterppn;	
	
	@Transient
	private Double subtotalafterdisc2;
	@Transient
	private Double subtotalafterdisc2afterppn;

	//TPR BARANG DAN UANG
	@Column(name="TPRB")
	private Double tprb;
	@Column(name="TPRUDISC")
	private Double tprudisc;
	@Column(name="TPRUCASHBACK")
	private Double tprucashback;
	
	@Transient
	private Double subtotalafterdisc;
	@Transient
	private Double subtotalafterdiscafterppn;
	
	
	//AFTER DISKON NOTA
	@Transient
	private Double discNota1;
	@Transient
	private Double discNota1Rp;
	@Transient
	private Double discNota1RpAfterPpn;

	@Transient
	private Double subTotalAfterDiscNota1;
	@Transient
	private Double subTotalAfterDiscNota1AfterPpn;
	
	@Transient
	private Double discNota;
	@Transient
	private Double discNotaRp;
	@Transient
	private Double discNotaRpAfterPpn;

	@Transient
	private Double subTotalAfterDiscNota;
	@Transient
	private Double subTotalAfterDiscNotaAfterPpn;
	
	
	@ManyToOne
	@JoinColumn(name="refno", referencedColumnName="refno")
	private FtSalesh ftsaleshBean;
	
	@ManyToOne
	@JoinColumn(name="id", referencedColumnName="id")
	private FProduct fproductBean;
	
	@OneToMany(mappedBy="ftSalesdBean",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private List<FtSalesdPromoTprb> ftsalesdPromoTprbList;
	
	@OneToMany(mappedBy="ftSalesdBean",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private List<FtSalesdPromoTpruCb> ftsalesdPromoTpruCbList;

	@OneToMany(mappedBy="ftSalesdBean",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private List<FtSalesdPromoTpruDisc> ftsalesdPromoTpruDiscList;

	
	public Boolean getPromo() {
		return promo;
	}
	public void setPromo(Boolean promo) {
		this.promo = promo;
	}
	public List<FtSalesdPromoTprb> getFtsalesdPromoTprbList() {
		return ftsalesdPromoTprbList;
	}
	public List<FtSalesdPromoTpruCb> getFtsalesdPromoTpruCbList() {
		return ftsalesdPromoTpruCbList;
	}
	public List<FtSalesdPromoTpruDisc> getFtsalesdPromoTpruDiscList() {
		return ftsalesdPromoTpruDiscList;
	}
	public void setFtsalesdPromoTprbList(
			List<FtSalesdPromoTprb> ftsalesdPromoTprbList) {
		this.ftsalesdPromoTprbList = ftsalesdPromoTprbList;
	}
	public void setFtsalesdPromoTpruCbList(
			List<FtSalesdPromoTpruCb> ftsalesdPromoTpruCbList) {
		this.ftsalesdPromoTpruCbList = ftsalesdPromoTpruCbList;
	}
	public void setFtsalesdPromoTpruDiscList(
			List<FtSalesdPromoTpruDisc> ftsalesdPromoTpruDiscList) {
		this.ftsalesdPromoTpruDiscList = ftsalesdPromoTpruDiscList;
	}
	public Double getTprb() {
		return tprb;
	}
	public Double getTprudisc() {
		return tprudisc;
	}
	public Double getTprucashback() {
		return tprucashback;
	}
	public void setTprb(Double tprb) {
		this.tprb = tprb;
	}
	public void setTprudisc(Double tprudisc) {
		this.tprudisc = tprudisc;
	}
	public void setTprucashback(Double tprucashback) {
		this.tprucashback = tprucashback;
	}
	public FtSalesdPK getId() {
		return id;
	}
	public Integer getNourut() {
		return nourut;
	}
	public Double getSprice() {
		return sprice;
	}
	public Double getSpriceafterppn() {
		return spriceafterppn;
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
	public FtSalesh getFtsaleshBean() {
		return ftsaleshBean;
	}
	public FProduct getFproductBean() {
		return fproductBean;
	}
	public void setId(FtSalesdPK id) {
		this.id = id;
	}
	public void setNourut(Integer nourut) {
		this.nourut = nourut;
	}
	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}
	public void setSpriceafterppn(Double spriceafterppn) {
		this.spriceafterppn = spriceafterppn;
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
	public void setFtsaleshBean(FtSalesh ftsaleshBean) {
		this.ftsaleshBean = ftsaleshBean;
	}
	public void setFproductBean(FProduct fproductBean) {
		this.fproductBean = fproductBean;
	}
	
	public Double getSubtotalafterdisc1() {
		return subtotalafterdisc1;
	}
	public Double getSubtotalafterdisc1afterppn() {
		return subtotalafterdisc1afterppn;
	}
	public Double getSubtotalafterdisc2() {
		return subtotalafterdisc2;
	}
	public Double getSubtotalafterdisc2afterppn() {
		return subtotalafterdisc2afterppn;
	}
	public void setSubtotalafterdisc1(Double subtotalafterdisc1) {
		this.subtotalafterdisc1 = subtotalafterdisc1;
	}
	public void setSubtotalafterdisc1afterppn(Double subtotalafterdisc1afterppn) {
		this.subtotalafterdisc1afterppn = subtotalafterdisc1afterppn;
	}
	public void setSubtotalafterdisc2(Double subtotalafterdisc2) {
		this.subtotalafterdisc2 = subtotalafterdisc2;
	}
	public void setSubtotalafterdisc2afterppn(Double subtotalafterdisc2afterppn) {
		this.subtotalafterdisc2afterppn = subtotalafterdisc2afterppn;
	}
	

	
	public Double getDiscNota1() {
		return discNota1;
	}
	public Double getDiscNota1Rp() {
		return discNota1Rp;
	}
	public Double getDiscNota1RpAfterPpn() {
		return discNota1RpAfterPpn;
	}
	public Double getSubTotalAfterDiscNota1() {
		return subTotalAfterDiscNota1;
	}
	public Double getSubTotalAfterDiscNota1AfterPpn() {
		return subTotalAfterDiscNota1AfterPpn;
	}
	public Double getDiscNota() {
		return discNota;
	}
	public Double getDiscNotaRp() {
		return discNotaRp;
	}
	public Double getDiscNotaRpAfterPpn() {
		return discNotaRpAfterPpn;
	}
	public Double getSubTotalAfterDiscNota() {
		return subTotalAfterDiscNota;
	}
	public Double getSubTotalAfterDiscNotaAfterPpn() {
		return subTotalAfterDiscNotaAfterPpn;
	}
	public void setDiscNota1(Double discNota1) {
		this.discNota1 = discNota1;
	}
	public void setDiscNota1Rp(Double discNota1Rp) {
		this.discNota1Rp = discNota1Rp;
	}
	public void setDiscNota1RpAfterPpn(Double discNota1RpAfterPpn) {
		this.discNota1RpAfterPpn = discNota1RpAfterPpn;
	}
	public void setSubTotalAfterDiscNota1(Double subTotalAfterDiscNota1) {
		this.subTotalAfterDiscNota1 = subTotalAfterDiscNota1;
	}
	public void setSubTotalAfterDiscNota1AfterPpn(
			Double subTotalAfterDiscNota1AfterPpn) {
		this.subTotalAfterDiscNota1AfterPpn = subTotalAfterDiscNota1AfterPpn;
	}
	public void setDiscNota(Double discNota) {
		this.discNota = discNota;
	}
	public void setDiscNotaRp(Double discNotaRp) {
		this.discNotaRp = discNotaRp;
	}
	public void setDiscNotaRpAfterPpn(Double discNotaRpAfterPpn) {
		this.discNotaRpAfterPpn = discNotaRpAfterPpn;
	}
	public void setSubTotalAfterDiscNota(Double subTotalAfterDiscNota) {
		this.subTotalAfterDiscNota = subTotalAfterDiscNota;
	}
	public void setSubTotalAfterDiscNotaAfterPpn(
			Double subTotalAfterDiscNotaAfterPpn) {
		this.subTotalAfterDiscNotaAfterPpn = subTotalAfterDiscNotaAfterPpn;
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
		FtSalesd other = (FtSalesd) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}