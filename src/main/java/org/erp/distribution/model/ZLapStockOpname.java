package org.erp.distribution.model;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name="zlapstockopname")
public class ZLapStockOpname {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="GRUP1")
	private	String grup1;
	@Column(name="GRUP2")
	private	String grup2;
	@Column(name="GRUP3")
	private	String grup3;

	@Column(name="PCODE")
	private	String pcode;
	@Column(name="PNAME")
	private	String pname;
	
	@Column(name="STOCKOPNAMEDATE")
	@Temporal(TemporalType.DATE)
	Date stockopnamedate;
	
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
	
	
	
	//**PENAMBAHAN**
	@Column(name="PENAMBAHANPCS")
	private Integer penambahanPcs;
	@Column(name="PENAMBAHANBES")
	private Integer penambahanBes;
	@Column(name="PENAMBAHANSED")
	private Integer penambahanSed;
	@Column(name="PENAMBAHANKEC")
	private	Integer penambahanKec;
	@Column(name="PENAMBAHANNILAIBELI")
	private	Double penambahanNilaiBeli;
	@Column(name="PENAMBAHANNILAIJUAL")
	private	Double penambahanNilaiJual;

	//**PENGURANGAN(-)
	@Column(name="PENGURANGANPCS")
	private	Integer penguranganPcs;
	@Column(name="PENGURANGANBES")
	private	Integer penguranganBes;
	@Column(name="PENGURANGANSED")
	private	Integer penguranganSed;
	@Column(name="PENGURANGANKEC")
	private	Integer penguranganKec;
	@Column(name="PENGURANGANNILAIBELI")
	private	Double penguranganNilaiBeli;
	@Column(name="PENGURANGANNILAIJUAL")
	private	Double penguranganNilaiJual;


	//**QTY FISIK
	@Column(name="FISIKPCS")
	private	Integer fisikPcs;
	@Column(name="FISIKBES")
	private	Integer fisikBes;
	@Column(name="FISIKSED")
	private	Integer fisikSed;
	@Column(name="FISIKKEC")
	private	Integer fisikKec;
	
	//**QTY TEORI
	@Column(name="TEORIPCS")
	private	Integer teoriPcs;
	@Column(name="TEORIBES")
	private	Integer teoriBes;
	@Column(name="TEORISED")
	private	Integer teoriSed;
	@Column(name="TEORIKEC")
	private	Integer teoriKec;
	
	@Column(name="AKUMULASINILAIBELI")
	private	Double akumulasiNilaiBeli;
	@Column(name="AKUMULASINILAIJUAL")
	private	Double akumulasiNilaiJual;
	
	
	public Date getStockopnamedate() {
		return stockopnamedate;
	}
	public Integer getPenambahanSed() {
		return penambahanSed;
	}
	public Integer getPenambahanKec() {
		return penambahanKec;
	}
	public void setStockopnamedate(Date stockopnamedate) {
		this.stockopnamedate = stockopnamedate;
	}
	public void setPenambahanSed(Integer penambahanSed) {
		this.penambahanSed = penambahanSed;
	}
	public void setPenambahanKec(Integer penambahanKec) {
		this.penambahanKec = penambahanKec;
	}
	
	public Integer getFisikPcs() {
		return fisikPcs;
	}
	public Integer getFisikBes() {
		return fisikBes;
	}
	public Integer getFisikSed() {
		return fisikSed;
	}
	public Integer getFisikKec() {
		return fisikKec;
	}
	public Integer getTeoriPcs() {
		return teoriPcs;
	}
	public Integer getTeoriBes() {
		return teoriBes;
	}
	public Integer getTeoriSed() {
		return teoriSed;
	}
	public Integer getTeoriKec() {
		return teoriKec;
	}
	public void setFisikPcs(Integer fisikPcs) {
		this.fisikPcs = fisikPcs;
	}
	public void setFisikBes(Integer fisikBes) {
		this.fisikBes = fisikBes;
	}
	public void setFisikSed(Integer fisikSed) {
		this.fisikSed = fisikSed;
	}
	public void setFisikKec(Integer fisikKec) {
		this.fisikKec = fisikKec;
	}
	public void setTeoriPcs(Integer teoriPcs) {
		this.teoriPcs = teoriPcs;
	}
	public void setTeoriBes(Integer teoriBes) {
		this.teoriBes = teoriBes;
	}
	public void setTeoriSed(Integer teoriSed) {
		this.teoriSed = teoriSed;
	}
	public void setTeoriKec(Integer teoriKec) {
		this.teoriKec = teoriKec;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
		ZLapStockOpname other = (ZLapStockOpname) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ZLapPrestasiKerja [id=" + getId() + "]";
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrup1() {
		return grup1;
	}

	public void setGrup1(String grup1) {
		this.grup1 = grup1;
	}

	public String getGrup2() {
		return grup2;
	}

	public void setGrup2(String grup2) {
		this.grup2 = grup2;
	}

	public String getGrup3() {
		return grup3;
	}

	public void setGrup3(String grup3) {
		this.grup3 = grup3;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getPenambahanPcs() {
		return penambahanPcs;
	}

	public void setPenambahanPcs(Integer penambahanPcs) {
		this.penambahanPcs = penambahanPcs;
	}


	public Integer getPenambahanBes() {
		return penambahanBes;
	}
	public void setPenambahanBes(Integer penambahanBes) {
		this.penambahanBes = penambahanBes;
	}
	public Double getPenambahanNilaiBeli() {
		return penambahanNilaiBeli;
	}

	public void setPenambahanNilaiBeli(Double penambahanNilaiBeli) {
		this.penambahanNilaiBeli = penambahanNilaiBeli;
	}

	public Double getPenambahanNilaiJual() {
		return penambahanNilaiJual;
	}

	public void setPenambahanNilaiJual(Double penambahanNilaiJual) {
		this.penambahanNilaiJual = penambahanNilaiJual;
	}

	public Integer getPenguranganPcs() {
		return penguranganPcs;
	}

	public void setPenguranganPcs(Integer penguranganPcs) {
		this.penguranganPcs = penguranganPcs;
	}

	public Integer getPenguranganBes() {
		return penguranganBes;
	}

	public void setPenguranganBes(Integer penguranganBes) {
		this.penguranganBes = penguranganBes;
	}

	public Integer getPenguranganSed() {
		return penguranganSed;
	}

	public void setPenguranganSed(Integer penguranganSed) {
		this.penguranganSed = penguranganSed;
	}

	public Integer getPenguranganKec() {
		return penguranganKec;
	}

	public void setPenguranganKec(Integer penguranganKec) {
		this.penguranganKec = penguranganKec;
	}

	public Double getPenguranganNilaiBeli() {
		return penguranganNilaiBeli;
	}

	public void setPenguranganNilaiBeli(Double penguranganNilaiBeli) {
		this.penguranganNilaiBeli = penguranganNilaiBeli;
	}

	public Double getPenguranganNilaiJual() {
		return penguranganNilaiJual;
	}

	public void setPenguranganNilaiJual(Double penguranganNilaiJual) {
		this.penguranganNilaiJual = penguranganNilaiJual;
	}

	public Double getAkumulasiNilaiBeli() {
		return akumulasiNilaiBeli;
	}

	public void setAkumulasiNilaiBeli(Double akumulasiNilaiBeli) {
		this.akumulasiNilaiBeli = akumulasiNilaiBeli;
	}

	public Double getAkumulasiNilaiJual() {
		return akumulasiNilaiJual;
	}

	public void setAkumulasiNilaiJual(Double akumulasiNilaiJual) {
		this.akumulasiNilaiJual = akumulasiNilaiJual;
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
	
	
}
