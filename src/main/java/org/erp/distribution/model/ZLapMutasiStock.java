package org.erp.distribution.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="zlapmutasistock")
public class ZLapMutasiStock {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="GRUP1")
	String grup1;
	@Column(name="GRUP2")
	String grup2;
	@Column(name="GRUP3")
	String grup3;

	@Column(name="PCODE")
	String pcode;
	@Column(name="PNAME")
	String pname;
	
	
	@Column(name="STOCKDATE")
	@Temporal(TemporalType.DATE)
	Date stockdate;
	
	//**SALDO AWAL
	@Column(name="SALDOAWALPCS")
	Integer saldoAwalPcs;
	@Column(name="SALDOAWALBES")
	Integer saldoAwalBes;
	@Column(name="SALDOAWALSED")
	Integer saldoAwalSed;
	@Column(name="SALDOAWALKEC")
	Integer saldoAwalKec;
	@Column(name="SALDOAWALNILAIBELI")
	Double saldoAwalNilaiBeli;
	@Column(name="SALDOAWALNILAIJUAL")
	Double saldoAwalNilaiJual;

	//**PENJUALAN(-)
	@Column(name="PENJUALANPCS")
	Integer penjualanPcs;
	@Column(name="PENJUALANBES")
	Integer penjualanBes;
	@Column(name="PENJUALANSED")
	Integer penjualanSed;
	@Column(name="PENJUALANKEC")
	Integer penjualanKec;
	@Column(name="PENJUALANNILAIBELI")
	Double penjualanNilaiBeli;
	@Column(name="PENJUALANNILAIJUAL")
	Double penjualanNilaiJual;
	
	//**RETUR PENJUALAN(+)
	@Column(name="RETURPENJUALANPCS")
	Integer returPenjualanPcs;
	@Column(name="RETURPENJUALANBES")
	Integer returPenjualanBes;
	@Column(name="RETURPENJUALANSED")
	Integer returPenjualanSed;
	@Column(name="RETURPENJUALANKEC")
	Integer returPenjualanKec;
	@Column(name="RETURPENJUALANNILAIBELI")
	Double returPenjualanNilaiBeli;
	@Column(name="RETURPENJUALANNILAIJUAL")
	Double returPenjualanNilaiJual;
	

	//**PEMBELIAN (+)
	@Column(name="PEMBELIANPCS")
	Integer pembelianPcs;
	@Column(name="PEMBELIANBES")
	Integer pembelianBes;
	@Column(name="PEMBELIANSED")
	Integer pembelianSed;
	@Column(name="PEMBELIANKEC")
	Integer pembelianKec;
	@Column(name="PEMBELIANNILAIBELI")
	Double pembelianNilaiBeli;
	@Column(name="PEMBELIANNILAIJUAL")
	Double pembelianNilaiJual;


	//**RETUR PEMBELIAN (-)
	@Column(name="RETURPEMBELIANPCS")
	Integer returPembelianPcs;
	@Column(name="RETURPEMBELIANBES")
	Integer returPembelianBes;
	@Column(name="RETURPEMBELIANSED")
	Integer returPembelianSed;
	@Column(name="RETURPEMBELIANKEC")
	Integer returPembelianKec;
	@Column(name="RETURPEMBELIANNILAIBELI")
	Double returPembelianNilaiBeli;
	@Column(name="RETURPEMBELIANNILAIJUAL")
	Double returPembelianNilaiJual;
	
	//**TRANSFER IN (+)
	@Column(name="TRANSFERINPCS")
	Integer transferInPcs;
	@Column(name="TRANSFERINBES")
	Integer transferInBes;
	@Column(name="TRANSFERINSED")
	Integer transferInSed;
	@Column(name="TRANSFERINKEC")
	Integer transferInKec;
	@Column(name="TRANSFERINNILAIBELI")
	Double transferInNilaiBeli;
	@Column(name="TRANSFERINNILAIJUAL")
	Double transferInNilaiJual;

	//**TRANSFER OUT (+)
	@Column(name="TRANSFEROUTPCS")
	Integer transferOutPcs;
	@Column(name="TRANSFEROUTBES")
	Integer transferOutBes;
	@Column(name="TRANSFEROUTSED")
	Integer transferOutSed;
	@Column(name="TRANSFEROUTKEC")
	Integer transferOutKec;
	@Column(name="TRANSFEROUTNILAIBELI")
	Double transferOutNilaiBeli;
	@Column(name="TRANSFEROUTNILAIJUAL")
	Double transferOutNilaiJual;
	
	//**PENYESUAIAN/STOCK OPNAME (+/-)
	@Column(name="PEYESUAIANPCS")
	Integer penyesuaianPcs;
	@Column(name="PEYESUAIANBES")
	Integer penyesuaianBes;
	@Column(name="PEYESUAIANSED")
	Integer penyesuaianSed;
	@Column(name="PEYESUAIANKEC")
	Integer penyesuaianKec;
	@Column(name="PEYESUAIANNILAIBELI")
	Double penyesuaianNilaiBeli;
	@Column(name="PEYESUAIANNILAIJUAL")
	Double penyesuaianNilaiJual;
	
	
	//**SALDO AKHIR
	@Column(name="SALDOAKHIRPCS")
	Integer saldoAkhirPcs;
	@Column(name="SALDOAKHIRBES")
	Integer saldoAkhirBes;
	@Column(name="SALDOAKHIRSED")
	Integer saldoAkhirSed;
	@Column(name="SALDOAKHIRKEC")
	Integer saldoAkhirKec;
	@Column(name="SALDOAKHIRNILAIBELI")
	Double saldoAkhirNilaiBeli;
	@Column(name="SALDOAKHIRNILAIJUAL")
	Double saldoAkhirNilaiJual;
	

	public Long getId() {
		return id;
	}
	public String getGrup1() {
		return grup1;
	}
	public String getGrup2() {
		return grup2;
	}
	public String getGrup3() {
		return grup3;
	}
	public String getPcode() {
		return pcode;
	}
	public String getPname() {
		return pname;
	}
	public Date getStockdate() {
		return stockdate;
	}
	public Integer getSaldoAwalPcs() {
		return saldoAwalPcs;
	}
	public Integer getSaldoAwalBes() {
		return saldoAwalBes;
	}
	public Integer getSaldoAwalSed() {
		return saldoAwalSed;
	}
	public Integer getSaldoAwalKec() {
		return saldoAwalKec;
	}
	public Double getSaldoAwalNilaiBeli() {
		return saldoAwalNilaiBeli;
	}
	public Double getSaldoAwalNilaiJual() {
		return saldoAwalNilaiJual;
	}
	public Integer getPenjualanPcs() {
		return penjualanPcs;
	}
	public Integer getPenjualanBes() {
		return penjualanBes;
	}
	public Integer getPenjualanSed() {
		return penjualanSed;
	}
	public Integer getPenjualanKec() {
		return penjualanKec;
	}
	public Double getPenjualanNilaiBeli() {
		return penjualanNilaiBeli;
	}
	public Double getPenjualanNilaiJual() {
		return penjualanNilaiJual;
	}
	public Integer getReturPenjualanPcs() {
		return returPenjualanPcs;
	}
	public Integer getReturPenjualanBes() {
		return returPenjualanBes;
	}
	public Integer getReturPenjualanSed() {
		return returPenjualanSed;
	}
	public Integer getReturPenjualanKec() {
		return returPenjualanKec;
	}
	public Double getReturPenjualanNilaiBeli() {
		return returPenjualanNilaiBeli;
	}
	public Double getReturPenjualanNilaiJual() {
		return returPenjualanNilaiJual;
	}
	public Integer getPembelianPcs() {
		return pembelianPcs;
	}
	public Integer getPembelianBes() {
		return pembelianBes;
	}
	public Integer getPembelianSed() {
		return pembelianSed;
	}
	public Integer getPembelianKec() {
		return pembelianKec;
	}
	public Double getPembelianNilaiBeli() {
		return pembelianNilaiBeli;
	}
	public Double getPembelianNilaiJual() {
		return pembelianNilaiJual;
	}
	public Integer getReturPembelianPcs() {
		return returPembelianPcs;
	}
	public Integer getReturPembelianBes() {
		return returPembelianBes;
	}
	public Integer getReturPembelianSed() {
		return returPembelianSed;
	}
	public Integer getReturPembelianKec() {
		return returPembelianKec;
	}
	public Double getReturPembelianNilaiBeli() {
		return returPembelianNilaiBeli;
	}
	public Double getReturPembelianNilaiJual() {
		return returPembelianNilaiJual;
	}
	public Integer getTransferInPcs() {
		return transferInPcs;
	}
	public Integer getTransferInBes() {
		return transferInBes;
	}
	public Integer getTransferInSed() {
		return transferInSed;
	}
	public Integer getTransferInKec() {
		return transferInKec;
	}
	public Double getTransferInNilaiBeli() {
		return transferInNilaiBeli;
	}
	public Double getTransferInNilaiJual() {
		return transferInNilaiJual;
	}
	public Integer getTransferOutPcs() {
		return transferOutPcs;
	}
	public Integer getTransferOutBes() {
		return transferOutBes;
	}
	public Integer getTransferOutSed() {
		return transferOutSed;
	}
	public Integer getTransferOutKec() {
		return transferOutKec;
	}
	public Double getTransferOutNilaiBeli() {
		return transferOutNilaiBeli;
	}
	public Double getTransferOutNilaiJual() {
		return transferOutNilaiJual;
	}
	public Integer getPenyesuaianPcs() {
		return penyesuaianPcs;
	}
	public Integer getPenyesuaianBes() {
		return penyesuaianBes;
	}
	public Integer getPenyesuaianSed() {
		return penyesuaianSed;
	}
	public Integer getPenyesuaianKec() {
		return penyesuaianKec;
	}
	public Double getPenyesuaianNilaiBeli() {
		return penyesuaianNilaiBeli;
	}
	public Double getPenyesuaianNilaiJual() {
		return penyesuaianNilaiJual;
	}
	public Integer getSaldoAkhirPcs() {
		return saldoAkhirPcs;
	}
	public Integer getSaldoAkhirBes() {
		return saldoAkhirBes;
	}
	public Integer getSaldoAkhirSed() {
		return saldoAkhirSed;
	}
	public Integer getSaldoAkhirKec() {
		return saldoAkhirKec;
	}
	public Double getSaldoAkhirNilaiBeli() {
		return saldoAkhirNilaiBeli;
	}
	public Double getSaldoAkhirNilaiJual() {
		return saldoAkhirNilaiJual;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setGrup1(String grup1) {
		this.grup1 = grup1;
	}
	public void setGrup2(String grup2) {
		this.grup2 = grup2;
	}
	public void setGrup3(String grup3) {
		this.grup3 = grup3;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public void setStockdate(Date stockdate) {
		this.stockdate = stockdate;
	}
	public void setSaldoAwalPcs(Integer saldoAwalPcs) {
		this.saldoAwalPcs = saldoAwalPcs;
	}
	public void setSaldoAwalBes(Integer saldoAwalBes) {
		this.saldoAwalBes = saldoAwalBes;
	}
	public void setSaldoAwalSed(Integer saldoAwalSed) {
		this.saldoAwalSed = saldoAwalSed;
	}
	public void setSaldoAwalKec(Integer saldoAwalKec) {
		this.saldoAwalKec = saldoAwalKec;
	}
	public void setSaldoAwalNilaiBeli(Double saldoAwalNilaiBeli) {
		this.saldoAwalNilaiBeli = saldoAwalNilaiBeli;
	}
	public void setSaldoAwalNilaiJual(Double saldoAwalNilaiJual) {
		this.saldoAwalNilaiJual = saldoAwalNilaiJual;
	}
	public void setPenjualanPcs(Integer penjualanPcs) {
		this.penjualanPcs = penjualanPcs;
	}
	public void setPenjualanBes(Integer penjualanBes) {
		this.penjualanBes = penjualanBes;
	}
	public void setPenjualanSed(Integer penjualanSed) {
		this.penjualanSed = penjualanSed;
	}
	public void setPenjualanKec(Integer penjualanKec) {
		this.penjualanKec = penjualanKec;
	}
	public void setPenjualanNilaiBeli(Double penjualanNilaiBeli) {
		this.penjualanNilaiBeli = penjualanNilaiBeli;
	}
	public void setPenjualanNilaiJual(Double penjualanNilaiJual) {
		this.penjualanNilaiJual = penjualanNilaiJual;
	}
	public void setReturPenjualanPcs(Integer returPenjualanPcs) {
		this.returPenjualanPcs = returPenjualanPcs;
	}
	public void setReturPenjualanBes(Integer returPenjualanBes) {
		this.returPenjualanBes = returPenjualanBes;
	}
	public void setReturPenjualanSed(Integer returPenjualanSed) {
		this.returPenjualanSed = returPenjualanSed;
	}
	public void setReturPenjualanKec(Integer returPenjualanKec) {
		this.returPenjualanKec = returPenjualanKec;
	}
	public void setReturPenjualanNilaiBeli(Double returPenjualanNilaiBeli) {
		this.returPenjualanNilaiBeli = returPenjualanNilaiBeli;
	}
	public void setReturPenjualanNilaiJual(Double returPenjualanNilaiJual) {
		this.returPenjualanNilaiJual = returPenjualanNilaiJual;
	}
	public void setPembelianPcs(Integer pembelianPcs) {
		this.pembelianPcs = pembelianPcs;
	}
	public void setPembelianBes(Integer pembelianBes) {
		this.pembelianBes = pembelianBes;
	}
	public void setPembelianSed(Integer pembelianSed) {
		this.pembelianSed = pembelianSed;
	}
	public void setPembelianKec(Integer pembelianKec) {
		this.pembelianKec = pembelianKec;
	}
	public void setPembelianNilaiBeli(Double pembelianNilaiBeli) {
		this.pembelianNilaiBeli = pembelianNilaiBeli;
	}
	public void setPembelianNilaiJual(Double pembelianNilaiJual) {
		this.pembelianNilaiJual = pembelianNilaiJual;
	}
	public void setReturPembelianPcs(Integer returPembelianPcs) {
		this.returPembelianPcs = returPembelianPcs;
	}
	public void setReturPembelianBes(Integer returPembelianBes) {
		this.returPembelianBes = returPembelianBes;
	}
	public void setReturPembelianSed(Integer returPembelianSed) {
		this.returPembelianSed = returPembelianSed;
	}
	public void setReturPembelianKec(Integer returPembelianKec) {
		this.returPembelianKec = returPembelianKec;
	}
	public void setReturPembelianNilaiBeli(Double returPembelianNilaiBeli) {
		this.returPembelianNilaiBeli = returPembelianNilaiBeli;
	}
	public void setReturPembelianNilaiJual(Double returPembelianNilaiJual) {
		this.returPembelianNilaiJual = returPembelianNilaiJual;
	}
	public void setTransferInPcs(Integer transferInPcs) {
		this.transferInPcs = transferInPcs;
	}
	public void setTransferInBes(Integer transferInBes) {
		this.transferInBes = transferInBes;
	}
	public void setTransferInSed(Integer transferInSed) {
		this.transferInSed = transferInSed;
	}
	public void setTransferInKec(Integer transferInKec) {
		this.transferInKec = transferInKec;
	}
	public void setTransferInNilaiBeli(Double transferInNilaiBeli) {
		this.transferInNilaiBeli = transferInNilaiBeli;
	}
	public void setTransferInNilaiJual(Double transferInNilaiJual) {
		this.transferInNilaiJual = transferInNilaiJual;
	}
	public void setTransferOutPcs(Integer transferOutPcs) {
		this.transferOutPcs = transferOutPcs;
	}
	public void setTransferOutBes(Integer transferOutBes) {
		this.transferOutBes = transferOutBes;
	}
	public void setTransferOutSed(Integer transferOutSed) {
		this.transferOutSed = transferOutSed;
	}
	public void setTransferOutKec(Integer transferOutKec) {
		this.transferOutKec = transferOutKec;
	}
	public void setTransferOutNilaiBeli(Double transferOutNilaiBeli) {
		this.transferOutNilaiBeli = transferOutNilaiBeli;
	}
	public void setTransferOutNilaiJual(Double transferOutNilaiJual) {
		this.transferOutNilaiJual = transferOutNilaiJual;
	}
	public void setPenyesuaianPcs(Integer penyesuaianPcs) {
		this.penyesuaianPcs = penyesuaianPcs;
	}
	public void setPenyesuaianBes(Integer penyesuaianBes) {
		this.penyesuaianBes = penyesuaianBes;
	}
	public void setPenyesuaianSed(Integer penyesuaianSed) {
		this.penyesuaianSed = penyesuaianSed;
	}
	public void setPenyesuaianKec(Integer penyesuaianKec) {
		this.penyesuaianKec = penyesuaianKec;
	}
	public void setPenyesuaianNilaiBeli(Double penyesuaianNilaiBeli) {
		this.penyesuaianNilaiBeli = penyesuaianNilaiBeli;
	}
	public void setPenyesuaianNilaiJual(Double penyesuaianNilaiJual) {
		this.penyesuaianNilaiJual = penyesuaianNilaiJual;
	}
	public void setSaldoAkhirPcs(Integer saldoAkhirPcs) {
		this.saldoAkhirPcs = saldoAkhirPcs;
	}
	public void setSaldoAkhirBes(Integer saldoAkhirBes) {
		this.saldoAkhirBes = saldoAkhirBes;
	}
	public void setSaldoAkhirSed(Integer saldoAkhirSed) {
		this.saldoAkhirSed = saldoAkhirSed;
	}
	public void setSaldoAkhirKec(Integer saldoAkhirKec) {
		this.saldoAkhirKec = saldoAkhirKec;
	}
	public void setSaldoAkhirNilaiBeli(Double saldoAkhirNilaiBeli) {
		this.saldoAkhirNilaiBeli = saldoAkhirNilaiBeli;
	}
	public void setSaldoAkhirNilaiJual(Double saldoAkhirNilaiJual) {
		this.saldoAkhirNilaiJual = saldoAkhirNilaiJual;
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
		ZLapMutasiStock other = (ZLapMutasiStock) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ZLapPrestasiKerja [id=" + id + "]";
	}
	

	
	
	
	
}
