package org.erp.distribution.model;

import java.util.Date;
import java.util.Set;

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
@Table(name="fproduct")
public class FProduct {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="PCODE", length=15)
	private String pcode;
	@Column(name="PNAME", length=100)
	private String pname;
	
	//BATCH CODE --> Berhubungan dengan Stockist atau Gudang
	//PRODUCTION CODE --> Berhubungan dengan TANGGAL DIPRODUKSI DAN EXP.DATE
	@Column(name="BATCHCODE", length=30)
	private String batchCode;
	@Column(name="PRODUCTIONCODE", length=30)
	private String productionCode;
	@Temporal(TemporalType.DATE)
	@Column(name="PRODUCTIONDATE")
	private Date productionDate;
	@Temporal(TemporalType.DATE)
	@Column(name="EXPIREDDATE")
	private Date expiredDate;
	
	@Transient
	CheckBox selected = new CheckBox();
	
	@Column(name="SHORTCODE", length=12)
	private String shortcode;
	@Column(name="DISTCODE", length=25)
	private String distcode;
	@Column(name="BARCODE", length=100)
	private String barcode;
	@Column(name="PRODCLASS")
	private Integer prodclass;
	@Column(name="PACKAGING", length=35)
	private String packaging;
	@Column(name="SHORTNAME", length=50)
	private String shortname;
	@Column(name="SHORTPACKAGING", length=25)
	private String shortpackaging;
	@Column(name="UOM1", length=15)
	private String uom1;
	@Column(name="UOM2", length=15)
	private String uom2;
	@Column(name="UOM3", length=15)
	private String uom3;
	@Column(name="CONVFACT1")
	private Integer convfact1;
	@Column(name="CONVFACT2")
	private Integer convfact2;
	@Column(name="PPRICE")
	private Double pprice;
	@Column(name="PPRICEAFTERPPN")
	private Double ppriceafterppn;
	@Column(name="PPRICE2")
	private Double pprice2;
	@Column(name="PPRICE2AFTERPPN")
	private Double pprice2afterppn;
	@Column(name="SPRICE")
	private Double sprice;
	@Column(name="SPRICEAFTERPPN")
	private Double spriceafterppn;
	@Column(name="SPRICE2")
	private Double sprice2;
	@Column(name="SPRICE2AFTERPPN")
	private Double sprice2afterppn;
	
	@Column(name="MIQTYSTOK")
	private Integer minqtystok;
	@Column(name="MAXQTYSTOK")
	private Integer maxqtystok;
	
	//TIDAK BOLEH DIGANTI-GANTI
	@Column(name="VOLUME")
	private int volume;
	@Column(name="WEIGHT")
	private Double weight;
	@Column(name="STATUSACTIVE")
	private Boolean statusactive;
	
//	@Column(name="SUPPLIER", length=75)
//	private String supplier;
	
	@ManyToOne
	@JoinColumn(name="fvendorBean", referencedColumnName="id", nullable=true)
	private FVendor fvendorBean;

	@ManyToOne
	@JoinColumn(name="fproductgroupBean", referencedColumnName="id")
	private FProductgroup fproductgroupBean;
	
//	@OneToMany(mappedBy="fproductBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fproductBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FStock> fstockSet;

//	@OneToMany(mappedBy="fproductBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fproductBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtSalesd> ftsalesdSet;
	
//	@OneToMany(mappedBy="fproductBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fproductBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtOpnamed> ftopnamedSet;

//	@OneToMany(mappedBy="fproductBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fproductBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtPriced> ftspricedSet;

//	@OneToMany(mappedBy="fproductBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fproductBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtPurchased> fpurchasedSet;

//	@OneToMany(mappedBy="fproductBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@OneToMany(mappedBy="fproductBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtStocktransferd> ftstocktransferdSet;

	@OneToMany(mappedBy="fProductBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FPromo> fpromoProductSet;

	@OneToMany(mappedBy="freeFproductBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FPromo> fpromoBonusSet;
	
	@OneToMany(mappedBy="fProductBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<FtSalesdPromoTprb> FtSalesdPromoTprbSet;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	
	
	public Set<FtSalesdPromoTprb> getFtSalesdPromoTprbSet() {
		return FtSalesdPromoTprbSet;
	}



	public void setFtSalesdPromoTprbSet(Set<FtSalesdPromoTprb> ftSalesdPromoTprbSet) {
		FtSalesdPromoTprbSet = ftSalesdPromoTprbSet;
	}



	public int getVolume() {
		return volume;
	}



	public void setVolume(int volume) {
		this.volume = volume;
	}



	public Set<FPromo> getFpromoBonusSet() {
		return fpromoBonusSet;
	}



	public void setFpromoBonusSet(Set<FPromo> fpromoBonusSet) {
		this.fpromoBonusSet = fpromoBonusSet;
	}



	public Set<FPromo> getFpromoProductSet() {
		return fpromoProductSet;
	}



	public void setFpromoProductSet(Set<FPromo> fpromoProductSet) {
		this.fpromoProductSet = fpromoProductSet;
	}



	public Long getId() {
		return id;
	}

	public String getPcode() {
		return pcode;
	}

	public String getPname() {
		return pname;
	}

	public CheckBox getSelected() {
		return selected;
	}

	public String getShortcode() {
		return shortcode;
	}

	public String getDistcode() {
		return distcode;
	}

	public String getBarcode() {
		return barcode;
	}

	public Integer getProdclass() {
		return prodclass;
	}

	public String getPackaging() {
		return packaging;
	}

	public String getShortname() {
		return shortname;
	}

	public String getShortpackaging() {
		return shortpackaging;
	}

	public String getUom1() {
		return uom1;
	}

	public String getUom2() {
		return uom2;
	}

	public String getUom3() {
		return uom3;
	}

	public Integer getConvfact1() {
		return convfact1;
	}

	public Integer getConvfact2() {
		return convfact2;
	}

	public Double getPprice() {
		return pprice;
	}

	public Double getPpriceafterppn() {
		return ppriceafterppn;
	}

	public Double getPprice2() {
		return pprice2;
	}

	public Double getPprice2afterppn() {
		return pprice2afterppn;
	}

	public Double getSprice() {
		return sprice;
	}

	public Double getSpriceafterppn() {
		return spriceafterppn;
	}

	public Double getSprice2() {
		return sprice2;
	}

	public Double getSprice2afterppn() {
		return sprice2afterppn;
	}

	public Double getWeight() {
		return weight;
	}

	public Boolean getStatusactive() {
		return statusactive;
	}


	public FVendor getFvendorBean() {
		return fvendorBean;
	}

	public FProductgroup getFproductgroupBean() {
		return fproductgroupBean;
	}

	public Set<FStock> getFstockSet() {
		return fstockSet;
	}

	public Set<FtSalesd> getFtsalesdSet() {
		return ftsalesdSet;
	}

	public Set<FtOpnamed> getFtopnamedSet() {
		return ftopnamedSet;
	}

	public Set<FtPriced> getFtspricedSet() {
		return ftspricedSet;
	}

	public Set<FtPurchased> getFpurchasedSet() {
		return fpurchasedSet;
	}

	public Set<FtStocktransferd> getFtstocktransferdSet() {
		return ftstocktransferdSet;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public void setSelected(CheckBox selected) {
		this.selected = selected;
	}

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}

	public void setDistcode(String distcode) {
		this.distcode = distcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setProdclass(Integer prodclass) {
		this.prodclass = prodclass;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public void setShortpackaging(String shortpackaging) {
		this.shortpackaging = shortpackaging;
	}

	public void setUom1(String uom1) {
		this.uom1 = uom1;
	}

	public void setUom2(String uom2) {
		this.uom2 = uom2;
	}

	public void setUom3(String uom3) {
		this.uom3 = uom3;
	}

	public void setConvfact1(Integer convfact1) {
		this.convfact1 = convfact1;
	}

	public void setConvfact2(Integer convfact2) {
		this.convfact2 = convfact2;
	}

	public void setPprice(Double pprice) {
		this.pprice = pprice;
	}

	public void setPpriceafterppn(Double ppriceafterppn) {
		this.ppriceafterppn = ppriceafterppn;
	}

	public void setPprice2(Double pprice2) {
		this.pprice2 = pprice2;
	}

	public void setPprice2afterppn(Double pprice2afterppn) {
		this.pprice2afterppn = pprice2afterppn;
	}

	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}

	public void setSpriceafterppn(Double spriceafterppn) {
		this.spriceafterppn = spriceafterppn;
	}

	public void setSprice2(Double sprice2) {
		this.sprice2 = sprice2;
	}

	public void setSprice2afterppn(Double sprice2afterppn) {
		this.sprice2afterppn = sprice2afterppn;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public void setStatusactive(Boolean statusactive) {
		this.statusactive = statusactive;
	}

	public void setFvendorBean(FVendor fvendorBean) {
		this.fvendorBean = fvendorBean;
	}

	public void setFproductgroupBean(FProductgroup fproductgroupBean) {
		this.fproductgroupBean = fproductgroupBean;
	}

	public void setFstockSet(Set<FStock> fstockSet) {
		this.fstockSet = fstockSet;
	}

	public void setFtsalesdSet(Set<FtSalesd> ftsalesdSet) {
		this.ftsalesdSet = ftsalesdSet;
	}

	public void setFtopnamedSet(Set<FtOpnamed> ftopnamedSet) {
		this.ftopnamedSet = ftopnamedSet;
	}

	public void setFtspricedSet(Set<FtPriced> ftspricedSet) {
		this.ftspricedSet = ftspricedSet;
	}

	public void setFpurchasedSet(Set<FtPurchased> fpurchasedSet) {
		this.fpurchasedSet = fpurchasedSet;
	}

	public void setFtstocktransferdSet(Set<FtStocktransferd> ftstocktransferdSet) {
		this.ftstocktransferdSet = ftstocktransferdSet;
	}
	

	public Integer getMinqtystok() {
		return minqtystok;
	}



	public Integer getMaxqtystok() {
		return maxqtystok;
	}



	public void setMinqtystok(Integer minqtystok) {
		this.minqtystok = minqtystok;
	}



	public void setMaxqtystok(Integer maxqtystok) {
		this.maxqtystok = maxqtystok;
	}



	public String getBatchCode() {
		return batchCode;
	}



	public String getProductionCode() {
		return productionCode;
	}



	public Date getProductionDate() {
		return productionDate;
	}



	public Date getExpiredDate() {
		return expiredDate;
	}



	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}



	public void setProductionCode(String productionCode) {
		this.productionCode = productionCode;
	}



	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}



	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FProduct other = (FProduct) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return pcode + " - " + pname + " - "
				+ packaging;
	}

	
	

}