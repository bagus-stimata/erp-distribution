package org.erp.distribution.model;

import java.util.*;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.vaadin.ui.CheckBox;

@Entity
@Table(name="ftpurchaseh")
public class FtPurchaseh {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REFNO")
	private Long refno;
	
	@Column(name="NOPO")
	private String nopo;
	@Column(name="INVOICENO")
	private String invoiceno;
	@Temporal(TemporalType.DATE)
	@Column(name="INVOICEDATE")
	private Date invoicedate;
	@Temporal(TemporalType.DATE)
	@Column(name="PODATE")
	private Date podate;

	@Temporal(TemporalType.DATE)
	@Column(name="DUEDATE")
	private Date duedate;
	
	@Column(length=5, name="TUNAIKREDIT")
	private String tunaikredit;
	@Column(length=5, name="TIPEFAKTUR")
	private String tipefaktur;
	
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
	
	@Column(name="DISC")
	private Double disc;
	@Transient
	private Double discrp;
	@Transient
	private Double discrpafterppn;
	
	@Column(name="SALDO")
	private Boolean saldo;
	@Column(name="PPNPERCENT")
	private Double ppnpercent;
	@Transient
	private Double ppnrp;

	//AMOUNT
	@Column(name="AMOUNT")
	private Double amount;
	@Transient
	private Double amountafterppn;
	//AMOUNT AFTER DISC
	//DPP
	@Column(name="AMOUNTAFTERDISC")
	private Double amountafterdisc;
	//DPP+PPN
	@Column(name="AMOUNTAFTERDISCAFTERPPN")
	private Double amountafterdiscafterppn;

	//AMOUNT PAY
	@Column(name="AMOUNTPAY")
	private Double amountpay;
	
	@Column(name="ENDOFDAY")
	private Boolean endofday;
	
	@Column(name="PRINTCOUNTER")
	private Integer printcounter;
	
	@Column(name="lunas")
	private boolean lunas;
	
	@Column(name="amountrevisi")
	private double amountrevisi;

	
	@ManyToOne
	@JoinColumn(name="fvendorBean", referencedColumnName="id")
	private FVendor fvendorBean;

	@ManyToOne
	@JoinColumn(name="fwarehouseBean", referencedColumnName="id")
	private FWarehouse fwarehouseBean;
	
	@OneToMany(mappedBy="ftpurchasehBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FtPurchased> ftpurchasedSet;

	@OneToMany(mappedBy="ftpurchasehBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FtAppaymentd> ftappaymentdSet;
	
	@Transient
	CheckBox selected = new CheckBox();

	public String getTunaikredit() {
		return tunaikredit;
	}

	public void setTunaikredit(String tunaikredit) {
		this.tunaikredit = tunaikredit;
	}

	public double getAmountrevisi() {
		return amountrevisi;
	}

	public void setAmountrevisi(double amountrevisi) {
		this.amountrevisi = amountrevisi;
	}

	public Set<FtAppaymentd> getFtappaymentdSet() {
		return ftappaymentdSet;
	}

	public void setFtappaymentdSet(Set<FtAppaymentd> ftappaymentdSet) {
		this.ftappaymentdSet = ftappaymentdSet;
	}

	public boolean isLunas() {
		return lunas;
	}

	public void setLunas(boolean lunas) {
		this.lunas = lunas;
	}

	public Date getDuedate() {
		return duedate;
	}

	public CheckBox getSelected() {
		return selected;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public void setSelected(CheckBox selected) {
		this.selected = selected;
	}

	public Long getRefno() {
		return refno;
	}

	public String getNopo() {
		return nopo;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public Date getInvoicedate() {
		return invoicedate;
	}

	public Date getPodate() {
		return podate;
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

	public Double getDisc() {
		return disc;
	}

	public Double getDiscrp() {
		return discrp;
	}

	public Double getDiscrpafterppn() {
		return discrpafterppn;
	}

	public Boolean getSaldo() {
		return saldo;
	}

	public Double getPpnpercent() {
		return ppnpercent;
	}

	public Double getAmount() {
		return amount;
	}

	public Double getAmountafterppn() {
		return amountafterppn;
	}

	public Double getAmountafterdisc() {
		return amountafterdisc;
	}

	public Double getAmountafterdiscafterppn() {
		return amountafterdiscafterppn;
	}

	public Double getAmountpay() {
		return amountpay;
	}

	public Boolean getEndofday() {
		return endofday;
	}

	public FVendor getFvendorBean() {
		return fvendorBean;
	}

	public FWarehouse getFwarehouseBean() {
		return fwarehouseBean;
	}

	public Set<FtPurchased> getFtpurchasedSet() {
		return ftpurchasedSet;
	}

	public void setRefno(Long refno) {
		this.refno = refno;
	}

	public void setNopo(String nopo) {
		this.nopo = nopo;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}

	public void setPodate(Date podate) {
		this.podate = podate;
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

	public void setDisc(Double disc) {
		this.disc = disc;
	}

	public void setDiscrp(Double discrp) {
		this.discrp = discrp;
	}

	public void setDiscrpafterppn(Double discrpafterppn) {
		this.discrpafterppn = discrpafterppn;
	}

	public void setSaldo(Boolean saldo) {
		this.saldo = saldo;
	}

	public void setPpnpercent(Double ppnpercent) {
		this.ppnpercent = ppnpercent;
	}

	public Double getPpnrp() {
		return ppnrp;
	}

	public void setPpnrp(Double ppnrp) {
		this.ppnrp = ppnrp;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setAmountafterppn(Double amountafterppn) {
		this.amountafterppn = amountafterppn;
	}

	public void setAmountafterdisc(Double amountafterdisc) {
		this.amountafterdisc = amountafterdisc;
	}

	public void setAmountafterdiscafterppn(Double amountafterdiscafterppn) {
		this.amountafterdiscafterppn = amountafterdiscafterppn;
	}

	public void setAmountpay(Double amountpay) {
		this.amountpay = amountpay;
	}

	public void setEndofday(Boolean endofday) {
		this.endofday = endofday;
	}

	public void setFvendorBean(FVendor fvendorBean) {
		this.fvendorBean = fvendorBean;
	}

	public void setFwarehouseBean(FWarehouse fwarehouseBean) {
		this.fwarehouseBean = fwarehouseBean;
	}

	public void setFtpurchasedSet(Set<FtPurchased> ftpurchasedSet) {
		this.ftpurchasedSet = ftpurchasedSet;
	}

	public Integer getPrintcounter() {
		return printcounter;
	}

	public void setPrintcounter(Integer printcounter) {
		this.printcounter = printcounter;
	}

	public String getTipefaktur() {
		return tipefaktur;
	}

	public void setTipefaktur(String tipefaktur) {
		this.tipefaktur = tipefaktur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((refno == null) ? 0 : refno.hashCode());
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
		FtPurchaseh other = (FtPurchaseh) obj;
		if (refno == null) {
			if (other.refno != null)
				return false;
		} else if (!refno.equals(other.refno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nopo + "";
	}

	
	
}