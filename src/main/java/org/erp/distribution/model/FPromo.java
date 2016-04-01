package org.erp.distribution.model;

import java.util.Date;
import java.util.Set;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="fpromo")
public class FPromo {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="NOREK", length=15)
	private String norek;
	
	@ManyToOne
	@JoinColumn(name="fProductBean", referencedColumnName="id")
    private FProduct fProductBean;   

	@Column(name="FORFPRODUCTGROUP", length=30)
    private String forFproductGroup;
	@Column(name="FORFPRODUCTGROUPAKUMULASI")
	private boolean forFproductGroupAkumulasi;
	
	@ManyToOne
	@JoinColumn(name="fproductgroupBean", referencedColumnName="id", nullable=true)
    private FProductgroup fproductgroupBean;
	
	@Column(name="DESCRIPTION")
    private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="PERIODEFROM")
    private Date periodeFrom;
	@Temporal(TemporalType.DATE)
	@Column(name="PERIODETO")
    private Date periodeTo;
    
    
	@Column(name="FORFCUSTOMERSUBGROUP")
    private String forFcustomersubgroup;
	@ManyToOne
	@JoinColumn(name="fcustomersubgroupBean", referencedColumnName="id", nullable=true)
    private FCustomersubgroup fcustomersubgroupBean;
    
	@Column(name="CLAIMPABRIK")
	private Boolean claimPabrik;
	@Column(name="TARGET")
    private Double target;
	@Column(name="TARGETAPPLIED")
    private Double targetApplied;
    
	//BONUS BARANG
	@ManyToOne
	@JoinColumn(name="freeFproductBean", referencedColumnName="id", nullable=true)
    private FProduct freeFproductBean;   
	private Boolean freeKelipatan;
	
	@Column(name="FREEQTY1")
    private Integer freeQty1;
	@Column(name="FREEQTYGET1")
    private Integer freeQtyGet1;
	@Column(name="FREEQTY2")
    private Integer freeQty2;
	@Column(name="FREEQTYGET2")
    private Integer freeQtyGet2;
	@Column(name="FREEQTY3")
    private Integer freeQty3;
	@Column(name="FREEQTYGET3")
    private Integer freeQtyGet3;
	@Column(name="FREEQTY4")
    private Integer freeQty4;
	@Column(name="FREEQTYGET4")
    private Integer freeQtyGet4;
	
	//DISCOUNT SUBTOTAL DAPAT DISKON
	private Boolean discKelipatan;
	@Column(name="DISCVALUE1")
    private Double discValue1;
	@Column(name="DISCPERCENTGET1")
    private Double discPercentGet1;
	@Column(name="DISCVALUE2")
    private Double discValue2;
	@Column(name="DISCPERCENTGET2")
    private Double discPercentGet2;
	@Column(name="DISCVALUE3")
    private Double discValue3;
	@Column(name="DISCPERCENTGET3")
    private Double discPercentGet3;
	@Column(name="DISCVALUE4")
    private Double discValue4;
	@Column(name="DISCPERCENTGET4")
    private Double discPercentGet4;

	//DISCOUNT SUBTOTAL DAPAT DISKON
	private Boolean discFromItemKelipatan;
	@Column(name="DISCFROMITEM_FREEQTY1")
    private Integer discFromItemFreeQty1;
	@Column(name="DISCFROMITEM_DISCPERCENTGET1")
    private Double discFromItemdiscPercentGet1;
	@Column(name="DISCFROMITEM_FREEQTY2")
    private Integer discFromItemFreeQty2;
	@Column(name="DISCFROMITEM_DISCPERCENTGET2")
    private Double discFromItemdiscPercentGet2;
	@Column(name="DISCFROMITEM_FREEQTY3")
    private Integer discFromItemFreeQty3;
	@Column(name="DISCFROMITEM_DISCPERCENTGET3")
    private Double discFromItemdiscPercentGet3;
	@Column(name="DISCFROMITEM_FREEQTY4")
    private Integer discFromItemFreeQty4;
	@Column(name="DISCFROMITEM_DISCPERCENTGET4")
    private Double discFromItemdiscPercentGet4;
	
	//CASHBACK	
	@Column(name="CASHBACKVALUE1")
    private Double cashBackValue1;
	@Column(name="CASHBACKGET1")
    private Double cashBackGet1;
	@Column(name="CASHBACKVALUE2")
    private Double cashBackValue2;
	@Column(name="CASHBACKGET2")
    private Double cashBackGet2;
	@Column(name="CASHBACKVALUE3")
    private Double cashBackValue3;
	@Column(name="CASHBACKGET3")
    private Double cashBackGet3;
	@Column(name="CASHBACKVALUE4")
    private Double cashBackValue4;
	@Column(name="CASHBACKGET4")
    private Double cashBackGet4;

	@Column(name="STATUSAKTIFPROMO")
	private boolean statusAktifPromo;


	@OneToMany(mappedBy="fPromoBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FtSalesdPromoTprb> ftSalesdPromoTprbSet;
	
	@OneToMany(mappedBy="fPromoBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private Set<FtSalesdPromoTpruDisc> ftSalesdPromoTpruDiscSet;
	
	
	public Boolean getDiscFromItemKelipatan() {
		return discFromItemKelipatan;
	}
	public Integer getDiscFromItemFreeQty1() {
		return discFromItemFreeQty1;
	}
	public Double getDiscFromItemdiscPercentGet1() {
		return discFromItemdiscPercentGet1;
	}
	public Integer getDiscFromItemFreeQty2() {
		return discFromItemFreeQty2;
	}
	public Double getDiscFromItemdiscPercentGet2() {
		return discFromItemdiscPercentGet2;
	}
	public Integer getDiscFromItemFreeQty3() {
		return discFromItemFreeQty3;
	}
	public Double getDiscFromItemdiscPercentGet3() {
		return discFromItemdiscPercentGet3;
	}
	public Integer getDiscFromItemFreeQty4() {
		return discFromItemFreeQty4;
	}
	public Double getDiscFromItemdiscPercentGet4() {
		return discFromItemdiscPercentGet4;
	}
	public void setDiscFromItemKelipatan(Boolean discFromItemKelipatan) {
		this.discFromItemKelipatan = discFromItemKelipatan;
	}
	public void setDiscFromItemFreeQty1(Integer discFromItemFreeQty1) {
		this.discFromItemFreeQty1 = discFromItemFreeQty1;
	}
	public void setDiscFromItemdiscPercentGet1(Double discFromItemdiscPercentGet1) {
		this.discFromItemdiscPercentGet1 = discFromItemdiscPercentGet1;
	}
	public void setDiscFromItemFreeQty2(Integer discFromItemFreeQty2) {
		this.discFromItemFreeQty2 = discFromItemFreeQty2;
	}
	public void setDiscFromItemdiscPercentGet2(Double discFromItemdiscPercentGet2) {
		this.discFromItemdiscPercentGet2 = discFromItemdiscPercentGet2;
	}
	public void setDiscFromItemFreeQty3(Integer discFromItemFreeQty3) {
		this.discFromItemFreeQty3 = discFromItemFreeQty3;
	}
	public void setDiscFromItemdiscPercentGet3(Double discFromItemdiscPercentGet3) {
		this.discFromItemdiscPercentGet3 = discFromItemdiscPercentGet3;
	}
	public void setDiscFromItemFreeQty4(Integer discFromItemFreeQty4) {
		this.discFromItemFreeQty4 = discFromItemFreeQty4;
	}
	public void setDiscFromItemdiscPercentGet4(Double discFromItemdiscPercentGet4) {
		this.discFromItemdiscPercentGet4 = discFromItemdiscPercentGet4;
	}
	public Set<FtSalesdPromoTprb> getFtSalesdPromoTprbSet() {
		return ftSalesdPromoTprbSet;
	}
	public void setFtSalesdPromoTprbSet(Set<FtSalesdPromoTprb> ftSalesdPromoTprbSet) {
		this.ftSalesdPromoTprbSet = ftSalesdPromoTprbSet;
	}
	public Set<FtSalesdPromoTpruDisc> getFtSalesdPromoTpruDiscSet() {
		return ftSalesdPromoTpruDiscSet;
	}
	public void setFtSalesdPromoTpruDiscSet(
			Set<FtSalesdPromoTpruDisc> ftSalesdPromoTpruDiscSet) {
		this.ftSalesdPromoTpruDiscSet = ftSalesdPromoTpruDiscSet;
	}
	public boolean isStatusAktifPromo() {
		return statusAktifPromo;
	}
	public void setStatusAktifPromo(boolean statusAktifPromo) {
		this.statusAktifPromo = statusAktifPromo;
	}
	public Boolean getClaimPabrik() {
		return claimPabrik;
	}
	public void setClaimPabrik(Boolean claimPabrik) {
		this.claimPabrik = claimPabrik;
	}
	public Boolean getFreeKelipatan() {
		return freeKelipatan;
	}
	public Boolean getDiscKelipatan() {
		return discKelipatan;
	}
	public void setFreeKelipatan(Boolean freeKelipatan) {
		this.freeKelipatan = freeKelipatan;
	}
	public void setDiscKelipatan(Boolean discKelipatan) {
		this.discKelipatan = discKelipatan;
	}
	public String getNorek() {
		return norek;
	}
	public void setNorek(String norek) {
		this.norek = norek;
	}
	public String getForFproductGroup() {
		return forFproductGroup;
	}
	public FProductgroup getFproductgroupBean() {
		return fproductgroupBean;
	}
	public String getForFcustomersubgroup() {
		return forFcustomersubgroup;
	}
	public FCustomersubgroup getFcustomersubgroupBean() {
		return fcustomersubgroupBean;
	}
	public Double getTarget() {
		return target;
	}
	public Double getTargetApplied() {
		return targetApplied;
	}
	public FProduct getFreeFproductBean() {
		return freeFproductBean;
	}
	public void setForFproductGroup(String forFproductGroup) {
		this.forFproductGroup = forFproductGroup;
	}
	public void setFproductgroupBean(FProductgroup fproductgroupBean) {
		this.fproductgroupBean = fproductgroupBean;
	}
	public void setForFcustomersubgroup(String forFcustomersubgroup) {
		this.forFcustomersubgroup = forFcustomersubgroup;
	}
	public void setFcustomersubgroupBean(FCustomersubgroup fcustomersubgroupBean) {
		this.fcustomersubgroupBean = fcustomersubgroupBean;
	}
	public void setTarget(Double target) {
		this.target = target;
	}
	public void setTargetApplied(Double targetApplied) {
		this.targetApplied = targetApplied;
	}
	public void setFreeFproductBean(FProduct freeFproductBean) {
		this.freeFproductBean = freeFproductBean;
	}
	public Long getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public Date getPeriodeFrom() {
		return periodeFrom;
	}
	public Date getPeriodeTo() {
		return periodeTo;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPeriodeFrom(Date periodeFrom) {
		this.periodeFrom = periodeFrom;
	}
	public void setPeriodeTo(Date periodeTo) {
		this.periodeTo = periodeTo;
	}
	
	public Integer getFreeQty1() {
		return freeQty1;
	}
	public Integer getFreeQtyGet1() {
		return freeQtyGet1;
	}
	public Integer getFreeQty2() {
		return freeQty2;
	}
	public Integer getFreeQtyGet2() {
		return freeQtyGet2;
	}
	public Integer getFreeQty3() {
		return freeQty3;
	}
	public Integer getFreeQtyGet3() {
		return freeQtyGet3;
	}
	public Integer getFreeQty4() {
		return freeQty4;
	}
	public Integer getFreeQtyGet4() {
		return freeQtyGet4;
	}
	public Double getDiscValue1() {
		return discValue1;
	}
	public Double getDiscPercentGet1() {
		return discPercentGet1;
	}
	public Double getDiscValue2() {
		return discValue2;
	}
	public Double getDiscPercentGet2() {
		return discPercentGet2;
	}
	public Double getDiscValue3() {
		return discValue3;
	}
	public Double getDiscPercentGet3() {
		return discPercentGet3;
	}
	public Double getDiscValue4() {
		return discValue4;
	}
	public Double getDiscPercentGet4() {
		return discPercentGet4;
	}
	public Double getCashBackValue1() {
		return cashBackValue1;
	}
	public Double getCashBackGet1() {
		return cashBackGet1;
	}
	public Double getCashBackValue2() {
		return cashBackValue2;
	}
	public Double getCashBackGet2() {
		return cashBackGet2;
	}
	public Double getCashBackValue3() {
		return cashBackValue3;
	}
	public Double getCashBackGet3() {
		return cashBackGet3;
	}
	public Double getCashBackValue4() {
		return cashBackValue4;
	}
	public Double getCashBackGet4() {
		return cashBackGet4;
	}
	public void setFreeQty1(Integer freeQty1) {
		this.freeQty1 = freeQty1;
	}
	public void setFreeQtyGet1(Integer freeQtyGet1) {
		this.freeQtyGet1 = freeQtyGet1;
	}
	public void setFreeQty2(Integer freeQty2) {
		this.freeQty2 = freeQty2;
	}
	public void setFreeQtyGet2(Integer freeQtyGet2) {
		this.freeQtyGet2 = freeQtyGet2;
	}
	public void setFreeQty3(Integer freeQty3) {
		this.freeQty3 = freeQty3;
	}
	public void setFreeQtyGet3(Integer freeQtyGet3) {
		this.freeQtyGet3 = freeQtyGet3;
	}
	public void setFreeQty4(Integer freeQty4) {
		this.freeQty4 = freeQty4;
	}
	public void setFreeQtyGet4(Integer freeQtyGet4) {
		this.freeQtyGet4 = freeQtyGet4;
	}
	public void setDiscValue1(Double discValue1) {
		this.discValue1 = discValue1;
	}
	public void setDiscPercentGet1(Double discPercentGet1) {
		this.discPercentGet1 = discPercentGet1;
	}
	public void setDiscValue2(Double discValue2) {
		this.discValue2 = discValue2;
	}
	public void setDiscPercentGet2(Double discPercentGet2) {
		this.discPercentGet2 = discPercentGet2;
	}
	public void setDiscValue3(Double discValue3) {
		this.discValue3 = discValue3;
	}
	public void setDiscPercentGet3(Double discPercentGet3) {
		this.discPercentGet3 = discPercentGet3;
	}
	public void setDiscValue4(Double discValue4) {
		this.discValue4 = discValue4;
	}
	public void setDiscPercentGet4(Double discPercentGet4) {
		this.discPercentGet4 = discPercentGet4;
	}
	public void setCashBackValue1(Double cashBackValue1) {
		this.cashBackValue1 = cashBackValue1;
	}
	public void setCashBackGet1(Double cashBackGet1) {
		this.cashBackGet1 = cashBackGet1;
	}
	public void setCashBackValue2(Double cashBackValue2) {
		this.cashBackValue2 = cashBackValue2;
	}
	public void setCashBackGet2(Double cashBackGet2) {
		this.cashBackGet2 = cashBackGet2;
	}
	public void setCashBackValue3(Double cashBackValue3) {
		this.cashBackValue3 = cashBackValue3;
	}
	public void setCashBackGet3(Double cashBackGet3) {
		this.cashBackGet3 = cashBackGet3;
	}
	public void setCashBackValue4(Double cashBackValue4) {
		this.cashBackValue4 = cashBackValue4;
	}
	public void setCashBackGet4(Double cashBackGet4) {
		this.cashBackGet4 = cashBackGet4;
	}
	
	
	
	public FProduct getfProductBean() {
		return fProductBean;
	}
	public void setfProductBean(FProduct fProductBean) {
		this.fProductBean = fProductBean;
	}
	

	public boolean isForFproductGroupAkumulasi() {
		return forFproductGroupAkumulasi;
	}
	public void setForFproductGroupAkumulasi(boolean forFproductGroupAkumulasi) {
		this.forFproductGroupAkumulasi = forFproductGroupAkumulasi;
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
		FPromo other = (FPromo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "[" + norek + "] " + description;
	}
    
    
    
    

}
