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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="stservice")
public class StService {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REFNO" )
	private Long refno;
	
	
	@Column(name="NOREG", length=20 )
	private String noreg;
	
	@Column(name="NOJOB", length=20  )
	private String nojob;
	
	
	//IDENTITAS HP SERVICE
	//merek ada dibawah
	@Column(name="TIPE", length=100  )
	private String tipeHp;
	
	@Column(name="IMEI", length=50  )
	private String imei;

	@Column(name="PIN", length=50  )
	private String pin;

	
	@Column(name="TANGGALMASUK" )
	@Temporal(TemporalType.DATE)
	private Date tanggalmasuk;

	@Column(name="TANGGALPENGAMBILAN" )
	@Temporal(TemporalType.DATE)
	private Date tanggalpengambilan;
	
	@Column(name="UANGMUKA")
	private Double uangMuka;

	

	
	@Column(name="YANGMENYERAHKAN" )
	private String yangMenyerahkan;


	@Column(name="BIAYA")
	private Double biaya;

	@Column(name="BIAYASPAREPART")
	private Double biayaSparePart;
	
	/*
	 * 0. Belum Service
	 * 1. Dalam Proses
	 * 2. Selesai 
	 * 10. Cancel
	 * */		
	@Column(name="STATUSSSERVICE")
	private Integer statusService;

	@Column(name="LAMAGARANSI")
	private Integer lamaGaransi;
	
	@Column(name="TELAHDIAMBIL" )
	private Boolean telahDiambil;
	@Column(name="TELAHDIAMBILTANGGAL" )
	@Temporal(TemporalType.DATE)
	private Date telahDiambilTanggal;
	
	//KELENGKAPAN
	@Column(name="KELENGKAPAN", length=500 )
	private String kelengkapan;
	
	@Column(name="KELBATTERY")
	private Boolean kelBattery;
	@Column(name="KELMEMORYCARD" )
	private Boolean kelMemorycard;
	@Column(name="KELSIMCARD" )
	private Boolean kelSimcard;
	@Column(name="KELCHARGER" )
	private Boolean kelCharger;
	@Column(name="KELDOSBOX" )
	private Boolean kelDosbox;
	@Column(name="KELLAIN" )
	private Boolean kelLain;
	
	@Column(name="PRINTPENGAMBILANCOUNTER" )
	private Integer printPengambilanCounter;
	@Column(name="PRINTPENERIMAANCOUNTER" )
	private Integer printPenerimaanCounter;
	
	@Column(name="KELUHAN", length=500)
	private String keluhan;

	
	@Column(name="ANALISAKERUSAKAN", length=500)
	private String analisaKerusakan;
	
	@Column(name="STATUSSELESAISERVICE")
	private Boolean statusSelesaiService;
	
	
	
	@ManyToOne
	@JoinColumn(name="scustomerBean", referencedColumnName="id")
	private SCustomer scustomerBean;

	@ManyToOne
	@JoinColumn(name="smerkBean", referencedColumnName="id")
	private SMerk smerkBean;

	@ManyToOne
	@JoinColumn(name="steknisiBean", referencedColumnName="id")
	private STeknisi steknisiBean;
	
	@OneToMany(mappedBy="stserviceBean", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<StServiceItem> stserviceItemSet;



	
	public Double getBiayaSparePart() {
		return biayaSparePart;
	}





	public void setBiayaSparePart(Double biayaSparePart) {
		this.biayaSparePart = biayaSparePart;
	}





	public Integer getPrintPengambilanCounter() {
		return printPengambilanCounter;
	}





	public Integer getPrintPenerimaanCounter() {
		return printPenerimaanCounter;
	}





	public void setPrintPengambilanCounter(Integer printPengambilanCounter) {
		this.printPengambilanCounter = printPengambilanCounter;
	}





	public void setPrintPenerimaanCounter(Integer printPenerimaanCounter) {
		this.printPenerimaanCounter = printPenerimaanCounter;
	}





	public Set<StServiceItem> getStserviceItemSet() {
		return stserviceItemSet;
	}





	public void setStserviceItemSet(Set<StServiceItem> stserviceItemSet) {
		this.stserviceItemSet = stserviceItemSet;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((refno == null) ? 0 : refno.hashCode());
		return result;
	}

	



	public SMerk getSmerkBean() {
		return smerkBean;
	}





	public STeknisi getSteknisiBean() {
		return steknisiBean;
	}





	public void setSmerkBean(SMerk smerkBean) {
		this.smerkBean = smerkBean;
	}





	public void setSteknisiBean(STeknisi steknisiBean) {
		this.steknisiBean = steknisiBean;
	}





	public SCustomer getScustomerBean() {
		return scustomerBean;
	}


	public void setScustomerBean(SCustomer scustomerBean) {
		this.scustomerBean = scustomerBean;
	}


	public String getTipeHp() {
		return tipeHp;
	}


	public String getImei() {
		return imei;
	}


	public String getPin() {
		return pin;
	}


	public Date getTanggalmasuk() {
		return tanggalmasuk;
	}


	public Date getTanggalpengambilan() {
		return tanggalpengambilan;
	}


	public Double getUangMuka() {
		return uangMuka;
	}


	public Boolean getTelahDiambil() {
		return telahDiambil;
	}


	public String getYangMenyerahkan() {
		return yangMenyerahkan;
	}


	public Double getBiaya() {
		return biaya;
	}


	public Integer getStatusService() {
		return statusService;
	}


	public Integer getLamaGaransi() {
		return lamaGaransi;
	}


	public Date getTelahDiambilTanggal() {
		return telahDiambilTanggal;
	}


	public String getKelengkapan() {
		return kelengkapan;
	}


	public Boolean getKelBattery() {
		return kelBattery;
	}


	public Boolean getKelMemorycard() {
		return kelMemorycard;
	}


	public Boolean getKelSimcard() {
		return kelSimcard;
	}


	public Boolean getKelCharger() {
		return kelCharger;
	}


	public Boolean getKelDosbox() {
		return kelDosbox;
	}


	public Boolean getKelLain() {
		return kelLain;
	}


	public String getKeluhan() {
		return keluhan;
	}


	public String getAnalisaKerusakan() {
		return analisaKerusakan;
	}


	public Boolean getStatusSelesaiService() {
		return statusSelesaiService;
	}


	public void setTipeHp(String tipeHp) {
		this.tipeHp = tipeHp;
	}


	public void setImei(String imei) {
		this.imei = imei;
	}


	public void setPin(String pin) {
		this.pin = pin;
	}


	public void setTanggalmasuk(Date tanggalmasuk) {
		this.tanggalmasuk = tanggalmasuk;
	}


	public void setTanggalpengambilan(Date tanggalpengambilan) {
		this.tanggalpengambilan = tanggalpengambilan;
	}


	public void setUangMuka(Double uangMuka) {
		this.uangMuka = uangMuka;
	}


	public void setTelahDiambil(Boolean telahDiambil) {
		this.telahDiambil = telahDiambil;
	}


	public void setYangMenyerahkan(String yangMenyerahkan) {
		this.yangMenyerahkan = yangMenyerahkan;
	}


	public void setBiaya(Double biaya) {
		this.biaya = biaya;
	}


	public void setStatusService(Integer statusService) {
		this.statusService = statusService;
	}


	public void setLamaGaransi(Integer lamaGaransi) {
		this.lamaGaransi = lamaGaransi;
	}


	public void setTelahDiambilTanggal(Date telahDiambilTanggal) {
		this.telahDiambilTanggal = telahDiambilTanggal;
	}


	public void setKelengkapan(String kelengkapan) {
		this.kelengkapan = kelengkapan;
	}


	public void setKelBattery(Boolean kelBattery) {
		this.kelBattery = kelBattery;
	}


	public void setKelMemorycard(Boolean kelMemorycard) {
		this.kelMemorycard = kelMemorycard;
	}


	public void setKelSimcard(Boolean kelSimcard) {
		this.kelSimcard = kelSimcard;
	}


	public void setKelCharger(Boolean kelCharger) {
		this.kelCharger = kelCharger;
	}


	public void setKelDosbox(Boolean kelDosbox) {
		this.kelDosbox = kelDosbox;
	}


	public void setKelLain(Boolean kelLain) {
		this.kelLain = kelLain;
	}


	public void setKeluhan(String keluhan) {
		this.keluhan = keluhan;
	}


	public void setAnalisaKerusakan(String analisaKerusakan) {
		this.analisaKerusakan = analisaKerusakan;
	}


	public void setStatusSelesaiService(Boolean statusSelesaiService) {
		this.statusSelesaiService = statusSelesaiService;
	}


	public Long getRefno() {
		return refno;
	}


	public String getNoreg() {
		return noreg;
	}


	public String getNojob() {
		return nojob;
	}


	public void setRefno(Long refno) {
		this.refno = refno;
	}


	public void setNoreg(String noreg) {
		this.noreg = noreg;
	}


	public void setNojob(String nojob) {
		this.nojob = nojob;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StService other = (StService) obj;
		if (refno == null) {
			if (other.refno != null)
				return false;
		} else if (!refno.equals(other.refno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StService [refno=" + refno + "]";
	}
	
	

	
	

}