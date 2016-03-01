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
@Table(name="ftsalesh")
public class FtSalesh {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REFNO")
	private Long refno;
	
	@Column(name="ORDERNO")
	private String orderno;
	@Column(name="INVOICENO")
	private String invoiceno;
	@Column(name="RECAPNO")
	private String recapno;
	
	@Column(name="SURATJALANNO")
	private String suratjalanno;	
	@Column(name="SURATJALAN")
	private Boolean suratjalan;
	@Column(name="SJDATE")
	@Temporal(TemporalType.DATE)
	private Date sjdate;
	
	@Column(name="SJPENAGIHANNO")
	private String sjpenagihanno;	
	@Temporal(TemporalType.DATE)
	private Date sjpenagihandate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="INVOICEDATE")
	private Date invoicedate;
	@Temporal(TemporalType.DATE)
	@Column(name="ORDERDATE")
	private Date orderdate;
	
	@Column(name="TOP")
	private Integer top;
	@Temporal(TemporalType.DATE)
	@Column(name="DUEDATE")
	private Date duedate;
	
	@Temporal(TemporalType.DATE)	
	@Column(name="ENTRYDATE")
	private Date entrydate;
	
	@Column(name="SALDO")
	private Boolean saldo;
	
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

	@Column(name="PPNPERCENT")
	private Double ppnpercent;
	@Transient
	private Double ppnrp;
	
	//AMOUNT
	@Column(name="AMOUNT")
	private Double amount;
	@Transient
	private Double amountafterppn;

	//AMOUNT AFTER DISC1 dan DISC2
	@Transient
	private Double amountafterdisc12;
	@Transient
	private Double amountafterdisc12afterppn;

	//AMOUNT AFTER DISC
	@Column(name="AMOUNTAFTERDISC")
	private Double amountafterdisc;
	@Column(name="AMOUNTAFTERDISCAFTERPPN")
	private Double amountafterdiscafterppn;

	@Column(name="AMOUNTPAY")
	private Double amountpay;
	
//FOR PAYMENT DIMAS FINANCE	
	@Column(name="amountrevisi")
	private double amountrevisi;
	@Column(name="amountreturtampung")
	private double amountreturtampung;
	@Column(name="returtampunglunas")
	private boolean returtampunglunas;
	@Column(name="lunas")
	private boolean lunas;
	@Column(name="terkirim")
	private boolean terkirim;
	@Column(name="tertunda")
	private boolean tertunda;
	@Column(name="tertundacounter")
	private int tertundacounter;
	
	@Transient
	CheckBox selected = new CheckBox();
	

	@Column(length=5, name="TUNAIKREDIT")
	private String tunaikredit;
	@Column(length=5, name="TIPEFAKTUR")
	private String tipefaktur;
	@Column(length=5, name="TIPEJUAL")
	private String tipejual;
	
	@Column(name="ENDOFDAY")
	private Boolean endofday;
	
	@Column(name="PRINTCOUNTER")
	private Integer printcounter;
	
	
	@ManyToOne
	@JoinColumn(name="fsalesmanBean", referencedColumnName="id")
	private FSalesman fsalesmanBean;
	
	@ManyToOne
	@JoinColumn(name="fcustomerBean", referencedColumnName="id")
	private FCustomer fcustomerBean;
	
	@OneToMany(mappedBy="ftsaleshBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FtSalesd> ftsalesdSet;
	
	@OneToMany(mappedBy="ftsaleshBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FtArpaymentd> ftarpaymentdSet;
	
	@ManyToOne
	@JoinColumn(name="fwarehouseBean", referencedColumnName="id")
	private FWarehouse fwarehouseBean;
	

	
	public Double getAmountafterdisc12() {
		return amountafterdisc12;
	}

	public Double getAmountafterdisc12afterppn() {
		return amountafterdisc12afterppn;
	}

	public void setAmountafterdisc12(Double amountafterdisc12) {
		this.amountafterdisc12 = amountafterdisc12;
	}

	public void setAmountafterdisc12afterppn(Double amountafterdisc12afterppn) {
		this.amountafterdisc12afterppn = amountafterdisc12afterppn;
	}

	public String getSjpenagihanno() {
		return sjpenagihanno;
	}

	public Date getSjpenagihandate() {
		return sjpenagihandate;
	}

	public void setSjpenagihanno(String sjpenagihanno) {
		this.sjpenagihanno = sjpenagihanno;
	}

	public void setSjpenagihandate(Date sjpenagihandate) {
		this.sjpenagihandate = sjpenagihandate;
	}

	public Long getRefno() {
		return refno;
	}

	public String getOrderno() {
		return orderno;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public Date getInvoicedate() {
		return invoicedate;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public Boolean getSaldo() {
		return saldo;
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

	public Double getPpnpercent() {
		return ppnpercent;
	}

	public Double getPpnrp() {
		return ppnrp;
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

	public String getTunaikredit() {
		return tunaikredit;
	}

	public String getTipefaktur() {
		return tipefaktur;
	}

	public String getTipejual() {
		return tipejual;
	}

	public Boolean getEndofday() {
		return endofday;
	}

	public Integer getPrintcounter() {
		return printcounter;
	}

	public FSalesman getFsalesmanBean() {
		return fsalesmanBean;
	}

	public FCustomer getFcustomerBean() {
		return fcustomerBean;
	}

	public Set<FtSalesd> getFtsalesdSet() {
		return ftsalesdSet;
	}

	public Set<FtArpaymentd> getFtarpaymentdSet() {
		return ftarpaymentdSet;
	}

	public FWarehouse getFwarehouseBean() {
		return fwarehouseBean;
	}

	public void setRefno(Long refno) {
		this.refno = refno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public void setSaldo(Boolean saldo) {
		this.saldo = saldo;
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

	public void setPpnpercent(Double ppnpercent) {
		this.ppnpercent = ppnpercent;
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

	public void setTunaikredit(String tunaikredit) {
		this.tunaikredit = tunaikredit;
	}

	public void setTipefaktur(String tipefaktur) {
		this.tipefaktur = tipefaktur;
	}

	public void setTipejual(String tipejual) {
		this.tipejual = tipejual;
	}

	public void setEndofday(Boolean endofday) {
		this.endofday = endofday;
	}

	public void setPrintcounter(Integer printcounter) {
		this.printcounter = printcounter;
	}

	public void setFsalesmanBean(FSalesman fsalesmanBean) {
		this.fsalesmanBean = fsalesmanBean;
	}

	public void setFcustomerBean(FCustomer fcustomerBean) {
		this.fcustomerBean = fcustomerBean;
	}

	public void setFtsalesdSet(Set<FtSalesd> ftsalesdSet) {
		this.ftsalesdSet = ftsalesdSet;
	}

	public void setFtarpaymentdSet(Set<FtArpaymentd> ftarpaymentdSet) {
		this.ftarpaymentdSet = ftarpaymentdSet;
	}

	public void setFwarehouseBean(FWarehouse fwarehouseBean) {
		this.fwarehouseBean = fwarehouseBean;
	}

	public Integer getTop() {
		return top;
	}


	public Date getDuedate() {
		return duedate;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public String getRecapno() {
		return recapno;
	}

	public void setRecapno(String recapno) {
		this.recapno = recapno;
	}

	public String getSuratjalanno() {
		return suratjalanno;
	}

	public Boolean getSuratjalan() {
		return suratjalan;
	}

	public void setSuratjalanno(String suratjalanno) {
		this.suratjalanno = suratjalanno;
	}

	public void setSuratjalan(Boolean suratjalan) {
		this.suratjalan = suratjalan;
	}

	public double getAmountrevisi() {
		return amountrevisi;
	}

	public void setAmountrevisi(double amountrevisi) {
		this.amountrevisi = amountrevisi;
	}

	public int getTertundacounter() {
		return tertundacounter;
	}

	public void setTertundacounter(int tertundacounter) {
		this.tertundacounter = tertundacounter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((refno == null) ? 0 : refno.hashCode());
		return result;
	}

	public boolean isReturtampunglunas() {
		return returtampunglunas;
	}

	public void setReturtampunglunas(boolean returtampunglunas) {
		this.returtampunglunas = returtampunglunas;
	}

	public double getAmountreturtampung() {
		return amountreturtampung;
	}

	public void setAmountreturtampung(double amountreturtampung) {
		this.amountreturtampung = amountreturtampung;
	}

	public boolean isLunas() {
		return lunas;
	}

	public void setLunas(boolean lunas) {
		this.lunas = lunas;
	}

	public boolean isTerkirim() {
		return terkirim;
	}

	public boolean isTertunda() {
		return tertunda;
	}

	public void setTerkirim(boolean terkirim) {
		this.terkirim = terkirim;
	}

	public void setTertunda(boolean tertunda) {
		this.tertunda = tertunda;
	}

	public CheckBox getSelected() {
		return selected;
	}

	public void setSelected(CheckBox selected) {
		this.selected = selected;
	}

	public Date getSjdate() {
		return sjdate;
	}

	public void setSjdate(Date sjdate) {
		this.sjdate = sjdate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FtSalesh other = (FtSalesh) obj;
		if (refno == null) {
			if (other.refno != null)
				return false;
		} else if (!refno.equals(other.refno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return orderno + "";
	}

	
}