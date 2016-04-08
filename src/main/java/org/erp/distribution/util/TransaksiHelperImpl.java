package org.erp.distribution.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FPromoJpaService2Impl;
import org.erp.distribution.jpaservice.FPromoJpaService2;
import org.erp.distribution.jpaservice.FtAdjusthJpaService;
import org.erp.distribution.jpaservice.FtAdjusthJpaServiceImpl;
import org.erp.distribution.jpaservice.FtArpaymentdJpaService;
import org.erp.distribution.jpaservice.FtArpaymentdJpaServiceImpl;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtArpaymenthJpaServiceImpl;
import org.erp.distribution.jpaservice.FtOpnamedJpaService;
import org.erp.distribution.jpaservice.FtOpnamedJpaServiceImpl;
import org.erp.distribution.jpaservice.FtOpnamehJpaService;
import org.erp.distribution.jpaservice.FtOpnamehJpaServiceImpl;
import org.erp.distribution.jpaservice.FtPriceAlthJpaService;
import org.erp.distribution.jpaservice.FtPriceAlthJpaServiceImpl;
import org.erp.distribution.jpaservice.FtPurchasedJpaService;
import org.erp.distribution.jpaservice.FtPurchasedJpaServiceImpl;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaServiceImpl;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaServiceImpl;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaServiceImpl;
import org.erp.distribution.jpaservice.FtPricedJpaService;
import org.erp.distribution.jpaservice.FtPricedJpaServiceImpl;
import org.erp.distribution.jpaservice.FtPricehJpaService;
import org.erp.distribution.jpaservice.FtPricehJpaServiceImpl;
import org.erp.distribution.jpaservice.FtStocktransferdJpaService;
import org.erp.distribution.jpaservice.FtStocktransferdJpaServiceImpl;
import org.erp.distribution.jpaservice.FtStocktransferhJpaService;
import org.erp.distribution.jpaservice.FtStocktransferhJpaServiceImpl;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservice.SysvarJpaServiceImpl;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FPromo;
import org.erp.distribution.model.FtAdjustd;
import org.erp.distribution.model.FtAdjusth;
import org.erp.distribution.model.FtArpaymenth;
import org.erp.distribution.model.FtOpnameh;
import org.erp.distribution.model.FtPriceAlth;
import org.erp.distribution.model.FtPriced;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.FtPriceh;
import org.erp.distribution.model.FtStocktransferh;
import org.erp.distribution.model.Sysvar;

import com.vaadin.ui.CustomComponent;


public class TransaksiHelperImpl extends CustomComponent implements TransaksiHelper{
//	private SysvarJpaService sysvarJpaService = new SysvarJpaServiceImpl();	
	private SysvarJpaService sysvarJpaService;

	private FProductJpaService fProductJpaService;
	
//	private FtArpaymentdJpaService ftArpaymentdJpaService = new FtArpaymentdJpaServiceImpl();
	private FtArpaymenthJpaService ftArpaymenthJpaService;
//	private FtOpnamedJpaService ftOpnamedJpaService = new FtOpnamedJpaServiceImpl();
	private FtOpnamehJpaService ftOpnamehJpaService;
//	private FtPurchasedJpaService ftPurchasedJpaService = new FtPurchasedJpaServiceImpl();
	private FtPurchasehJpaService ftPurchasehJpaService;
//	private FtSalesdJpaService ftSalesdJpaService = new FtSalesdJpaServiceImpl();
	private FtSaleshJpaService ftSaleshJpaService;
//	private FtSpricedJpaService ftSpricedJpaService = new FtSpricedJpaServiceImpl();
	private FtPricehJpaService ftSpricehJpaService;
//	private FtStocktransferdJpaService ftStocktransferd = new FtStocktransferdJpaServiceImpl();
	private FtStocktransferhJpaService ftStocktransferhJpaService;
	
	private FtAdjusthJpaService ftAdjusthJpaService;
	private FtPriceAlthJpaService ftPriceAlthJpaService;
	private FPromoJpaService2 fPromoJpaService;

	private FtPricehJpaService ftPricehJpaService;
	private FtPricedJpaService FtPricedJpaService;
	
	 public TransaksiHelperImpl() {
			setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
			setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
			
			
			setFtArpaymenthJpaService((((DashboardUI) getUI().getCurrent()).getFtArpaymenthJpaService()));
			setFtOpnamehJpaService((((DashboardUI) getUI().getCurrent()).getFtOpnamehJpaService()));
			setFtPurchasehJpaService((((DashboardUI) getUI().getCurrent()).getFtPurchasehJpaService()));
			setFtSaleshJpaService((((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService()));
			setFtSpricehJpaService((((DashboardUI) getUI().getCurrent()).getFtPricehJpaService()));
			setFtStocktransferhJpaService((((DashboardUI) getUI().getCurrent()).getFtStocktransferhJpaService()));

			setFtAdjusthJpaService((((DashboardUI) getUI().getCurrent()).getFtAdjusthJpaService()));
			setFtPriceAlthJpaService((((DashboardUI) getUI().getCurrent()).getFtPriceAlthJpaService()));
			setfPromoJpaService((((DashboardUI) getUI().getCurrent()).getFpromoJpaService()));

			setFtPricehJpaService((((DashboardUI) getUI().getCurrent()).getFtPricehJpaService()));
			setFtPricedJpaService((((DashboardUI) getUI().getCurrent()).getFtPricedJpaService()));
		 
	 }
	
	 
	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}


	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}


	public FtArpaymenthJpaService getFtArpaymenthJpaService() {
		return ftArpaymenthJpaService;
	}


	public FtOpnamehJpaService getFtOpnamehJpaService() {
		return ftOpnamehJpaService;
	}


	public FtPurchasehJpaService getFtPurchasehJpaService() {
		return ftPurchasehJpaService;
	}


	public FtSaleshJpaService getFtSaleshJpaService() {
		return ftSaleshJpaService;
	}


	public FtPricehJpaService getFtSpricehJpaService() {
		return ftSpricehJpaService;
	}


	public FtStocktransferhJpaService getFtStocktransferhJpaService() {
		return ftStocktransferhJpaService;
	}


	public FtAdjusthJpaService getFtAdjusthJpaService() {
		return ftAdjusthJpaService;
	}


	public FtPriceAlthJpaService getFtPriceAlthJpaService() {
		return ftPriceAlthJpaService;
	}


	public FPromoJpaService2 getfPromoJpaService() {
		return fPromoJpaService;
	}


	public FtPricehJpaService getFtPricehJpaService() {
		return ftPricehJpaService;
	}


	public FtPricedJpaService getFtPricedJpaService() {
		return FtPricedJpaService;
	}


	public void setFtArpaymenthJpaService(
			FtArpaymenthJpaService ftArpaymenthJpaService) {
		this.ftArpaymenthJpaService = ftArpaymenthJpaService;
	}


	public void setFtOpnamehJpaService(FtOpnamehJpaService ftOpnamehJpaService) {
		this.ftOpnamehJpaService = ftOpnamehJpaService;
	}


	public void setFtPurchasehJpaService(FtPurchasehJpaService ftPurchasehJpaService) {
		this.ftPurchasehJpaService = ftPurchasehJpaService;
	}


	public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
		this.ftSaleshJpaService = ftSaleshJpaService;
	}


	public void setFtSpricehJpaService(FtPricehJpaService ftSpricehJpaService) {
		this.ftSpricehJpaService = ftSpricehJpaService;
	}


	public void setFtStocktransferhJpaService(
			FtStocktransferhJpaService ftStocktransferhJpaService) {
		this.ftStocktransferhJpaService = ftStocktransferhJpaService;
	}


	public void setFtAdjusthJpaService(FtAdjusthJpaService ftAdjusthJpaService) {
		this.ftAdjusthJpaService = ftAdjusthJpaService;
	}


	public void setFtPriceAlthJpaService(FtPriceAlthJpaService ftPriceAlthJpaService) {
		this.ftPriceAlthJpaService = ftPriceAlthJpaService;
	}


	public void setfPromoJpaService(FPromoJpaService2 fPromoJpaService) {
		this.fPromoJpaService = fPromoJpaService;
	}


	public void setFtPricehJpaService(FtPricehJpaService ftPricehJpaService) {
		this.ftPricehJpaService = ftPricehJpaService;
	}


	public void setFtPricedJpaService(FtPricedJpaService ftPricedJpaService) {
		FtPricedJpaService = ftPricedJpaService;
	}


	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}


	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}


	@Override
	public Date getCurrentTransDate() {
		return sysvarJpaService.findById("_TRANSDATE").getNilaiDate1();
	}
	
	@Override
	public String getCurrentFtPurchasehRefno() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_PURCHASEH").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}
	@Override
	public String getCurrentFtPurchasehRefnoRetur() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_PURCHASEHR").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getCurrentFtArpaymenthRefno() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_ARPAYH").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getCurrentFtOpnamehRefno() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_OPNAMEH").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getCurrentFtSaleshRefno() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_SALESHPO").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}
	@Override
	public String getCurrentFtSaleshRefnoRetur() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_SALESHPOR").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getCurrentFtSaleshInvoiceno() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_SALESHINV").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}
	@Override
	public String getCurrentFtSaleshSuratJalan() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_SALESH_SJ").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getCurrentFtSaleshInvoicenoRetur() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_SALESHINVR").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getCurrentFtSpricehRefno() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_SPRICEH").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getCurrentFtStocktransferhRefno() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_STKTRANSH").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getNextFtArpaymenthRefno() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_ARPAYH", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_ARPAYH", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftArpaymenthJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftArpaymenthJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtArpaymenth item = new FtArpaymenth();
				List<FtArpaymenth> list = new ArrayList<FtArpaymenth>();
				list = ftArpaymenthJpaService.findAllByField("norek", newKode, null);
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}

	@Override
	public String getNextFtOpnamehRefno() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_OPNAMEH", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_OPNAMEH", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftOpnamehJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftOpnamehJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtOpnameh item = new FtOpnameh();
				List<FtOpnameh> list = new ArrayList<FtOpnameh>();
				list = ftOpnamehJpaService.findAllByField("norek", newKode, null);
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}

	@Override
	public String getNextFtPurchasehRefno() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_PURCHASEH", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_PURCHASEH", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftPurchasehJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftPurchasehJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtPurchaseh item = new FtPurchaseh();
				List<FtPurchaseh> list = new ArrayList<FtPurchaseh>();
//				list = ftPurchasehJpaService.findAllByField("nopo", newKode, null);
//				for (FtPurchaseh domain:list) {
//					if (domain.getTipefaktur().equals("R")) {
//						list.remove(domain);
//					}
//				}
				list = ftPurchasehJpaService.findAllByNopo(newKode, "R");
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}
	@Override
	public String getNextFtPurchasehRefnoRetur() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_PURCHASEHR", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_PURCHASEHR", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftPurchasehJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftPurchasehJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtPurchaseh item = new FtPurchaseh();
				List<FtPurchaseh> list = new ArrayList<FtPurchaseh>();
//				list = ftPurchasehJpaService.findAllByField("nopo", newKode, null);
//				for (FtPurchaseh domain:list) {
//					if (domain.getTipefaktur().equals("F")) {
//						list.remove(domain);
//					}
//				}
				list = ftPurchasehJpaService.findAllByNopo(newKode, "R");
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}

	@Override
	public String getNextFtSaleshRefno() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_SALESHPO", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_SALESHPO", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftSaleshJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftSaleshJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtSalesh item = new FtSalesh();
				List<FtSalesh> list = new ArrayList<FtSalesh>();
//				list = ftSaleshJpaService.findAllByField("orderno", newKode, null);
//				for (FtSalesh domain:list) {
//					if (domain.getTipefaktur().equals("R")) {
//						list.remove(domain);
//					}
//				}
				list = ftSaleshJpaService.findAllByOrderno(newKode, "F");
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}
	@Override
	public String getNextFtSaleshRefnoRetur() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_SALESHPOR", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_SALESHPOR", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftSaleshJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftSaleshJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtSalesh item = new FtSalesh();
				List<FtSalesh> list = new ArrayList<FtSalesh>();
//				list = ftSaleshJpaService.findAllByField("orderno", newKode, null);
//				for (FtSalesh domain:list) {
//					if (domain.getTipefaktur().equals("F")) {
//						list.remove(domain);
//					}
//				}
				list = ftSaleshJpaService.findAllByOrderno(newKode, "R");
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}
	@Override
	public String getNextFtSaleshInvoiceno() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_SALESHINV", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_SALESHINV", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftSaleshJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftSaleshJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtSalesh item = new FtSalesh();
				List<FtSalesh> list = new ArrayList<FtSalesh>();
//				list = ftSaleshJpaService.findAllByField("invoiceno", newKode, null);
//				for (FtSalesh domain:list) {
//					if (domain.getTipefaktur().equals("R")) {
//						list.remove(domain);
//					}
//				}
				list = ftSaleshJpaService.findAllByInvoiceno(newKode, "F");
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}
	@Override
	public String getNextFtSaleshSuratJalan() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_SALESH_SJ", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_SALESH_SJ", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftSaleshJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftSaleshJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtSalesh item = new FtSalesh();
				List<FtSalesh> list = new ArrayList<FtSalesh>();
//				list = ftSaleshJpaService.findAllByField("invoiceno", newKode, null);
//				for (FtSalesh domain:list) {
//					if (domain.getTipefaktur().equals("R")) {
//						list.remove(domain);
//					}
//				}
				list = ftSaleshJpaService.findAllBySuratjalanNo(newKode, "F");
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}
	@Override
	public String getNextFtSaleshInvoicenoRetur() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_SALESHINVR", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_SALESHINVR", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftSaleshJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftSaleshJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtSalesh item = new FtSalesh();
				List<FtSalesh> list = new ArrayList<FtSalesh>();
//				list = ftSaleshJpaService.findAllByField("invoiceno", newKode, null);
//				for (FtSalesh domain:list) {
//					if (domain.getTipefaktur().equals("F")) {
//						list.remove(domain);
//					}
//				}
				list = ftSaleshJpaService.findAllByInvoiceno(newKode, "R");
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}

	@Override
	public String getNextFtSpricehRefno() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_SPRICEH", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_SPRICEH", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftSpricehJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftSpricehJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtPriceh item = new FtPriceh();
				List<FtPriceh> list = new ArrayList<FtPriceh>();
				list = ftSpricehJpaService.findAllByField("norek", newKode, null);
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}

	@Override
	public String getNextFtStocktransferhRefno() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_STKTRANSH", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_STKTRANSH", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftStocktransferhJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftStocktransferhJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtStocktransferh item = new FtStocktransferh();
				List<FtStocktransferh> list = new ArrayList<FtStocktransferh>();
				list = ftStocktransferhJpaService.findAllByField("norek", newKode, null);
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}

	@Override
	public Double getParamPpn() {
		return 10.0;
	}

	@Override
	public void prosesAkhirHari() {
		//1. AMBIL TANGGAL TRANSAKSI BERJALAN SEKARANG
		Date transDate = new Date();
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_TRANSDATE", null).iterator();
		
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			 if (sysvar.getNilaiDate1()!=null){
				 transDate = sysvar.getNilaiDate1();
				 
				 //1. UPDATE TRANSAKSI PENJUALAN DAN RETUR
				 List<FtSalesh> listFtSalesh  = ftSaleshJpaService.findAllByInvoicedate(transDate);
				 Iterator<FtSalesh> iterFtSalesh = listFtSalesh.iterator();
				 while (iterFtSalesh.hasNext()){
					 FtSalesh domain = new FtSalesh();
					 domain = iterFtSalesh.next();
					 //HANYA YANG ADA INVOICE NO
					 if (! domain.getInvoiceno().trim().equals("")) {
						 domain.setEndofday(true);
					 }
					 ftSaleshJpaService.updateObject(domain);
					 //HAPUS
					 if (domain.getOrderno().equalsIgnoreCase("New")) {
						 ftSaleshJpaService.removeObject(domain);						 
					 }
				 }

				 //2. 
				 List<FtPurchaseh> listFtPurchaseh  = ftPurchasehJpaService.findAllByOrderdate(transDate);
				 Iterator<FtPurchaseh> iterFtPurchaseh = listFtPurchaseh.iterator();
				 while (iterFtPurchaseh.hasNext()){
					 FtPurchaseh domain = new FtPurchaseh();
					 domain = iterFtPurchaseh.next();
					 domain.setEndofday(true);
					 ftPurchasehJpaService.updateObject(domain);
					 
					 if (domain.getNopo().equalsIgnoreCase("New")) {
						 ftPurchasehJpaService.removeObject(domain);						 
					 }
				 }
				 
				 //4. CLOSING STOCK TRANSFER
				 List<FtStocktransferh> listFtStocttransferh  = ftStocktransferhJpaService.findAllByTrdate(transDate);
				 Iterator<FtStocktransferh> iterFtStocktransferh = listFtStocttransferh.iterator();
				 while (iterFtStocktransferh.hasNext()){
					 FtStocktransferh domain = new FtStocktransferh();
					 domain = iterFtStocktransferh.next();
					 domain.setEndofday(true);
					 ftStocktransferhJpaService.updateObject(domain);
					 
					 if (domain.getNorek().equalsIgnoreCase("New")) {
						 ftStocktransferhJpaService.removeObject(domain);						 
					 }
				 }
				 
				 //4. CLOSING STOCK OPNAME
				 List<FtOpnameh> listFtOpnameh  = ftOpnamehJpaService.findAllByTrdate(transDate);
				 Iterator<FtOpnameh> iterFtOpnameh = listFtOpnameh.iterator();
				 while (iterFtOpnameh.hasNext()){
					 FtOpnameh domain = new FtOpnameh();
					 domain = iterFtOpnameh.next();
					 domain.setEndofday(true);
					 ftOpnamehJpaService.updateObject(domain);
					 
					 if (domain.getNorek().equalsIgnoreCase("New")) {
						 ftOpnamehJpaService.removeObject(domain);						 
					 }
				 }
				 
				 //5. CLOSING STOCK MUTASI
				 List<FtAdjusth> listFtAdjusth  = ftAdjusthJpaService.findAllByTrdate(transDate);
				 Iterator<FtAdjusth> iterFtAdjusth = listFtAdjusth.iterator();
				 while (iterFtAdjusth.hasNext()){
					 FtAdjusth domain = new FtAdjusth();
					 domain = iterFtAdjusth.next();
					 domain.setEndofday(true);
					 ftAdjusthJpaService.updateObject(domain);
					 
					 if (domain.getNorek().equalsIgnoreCase("New")) {
						 ftAdjusthJpaService.removeObject(domain);						 
					 }
				 }
				 
				 
			 //2. MAJUKAN SATU HARI:: SEMENTARA PAKE DATE SYSTEM
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(transDate);
				 cal.add(Calendar.DATE,1);
				 transDate = cal.getTime();
				 
				 //UPDATE :: KE DATABASE
				 sysvar.setNilaiDate1(transDate);			 
				 sysvarJpaService.updateObject(sysvar);
				 
					//6. JIKA ADA KENAIKAN HARGA MAKA IMPLEMENTASIKAN
					List<FtPriceh> listFtPriceh = new ArrayList<FtPriceh>();
					listFtPriceh = ftPricehJpaService.findAllByTrDate(transDate);
					for (FtPriceh ftPriceh: listFtPriceh) {
						if (ftPriceh.getPosting()==true) {
							List<FtPriced> listFtPriced = new ArrayList<FtPriced>(ftPriceh.getFtpricedSet());
							for (FtPriced ftPriced: listFtPriced) {
								FProduct fProduct = new FProduct();
								fProduct = ftPriced.getFproductBean();

								if (ftPriced.getPprice()>0)
									fProduct.setPprice(ftPriced.getPprice());
								if (ftPriced.getPprice2()>0)
									fProduct.setPprice2(ftPriced.getPprice2());
								if (ftPriced.getPprice2afterppn()>0)
									fProduct.setPprice2afterppn(ftPriced.getPprice2afterppn());
								if (ftPriced.getPpriceafterppn()>0)
									fProduct.setPpriceafterppn(ftPriced.getPpriceafterppn());

								fProduct.setSprice(ftPriced.getSprice());
								fProduct.setSprice2(ftPriced.getSprice2());
								fProduct.setSprice2afterppn(ftPriced.getSprice2afterppn());
								fProduct.setSpriceafterppn(ftPriced.getSpriceafterppn());
								//UPDATE KE DATABASE
								fProductJpaService.updateObject(fProduct);
							}
						}
					}
				 
				 
			 }
			
		}
		
		
	}

	@Override
	public String getNextFtPricehRefno() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_PRICEH", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_PRICEH", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftPricehJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftPricehJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtPriceh item = new FtPriceh();
				List<FtPriceh> list = new ArrayList<FtPriceh>();
				list = ftPricehJpaService.findAllByField("norek", newKode, null);
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}

	@Override
	public String getNextFtPriceAlthRefno() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_PRICEALTH", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_PRICEALTH", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftPriceAlthJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftPriceAlthJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtPriceAlth item = new FtPriceAlth();
				List<FtPriceAlth> list = new ArrayList<FtPriceAlth>();
				list = ftPriceAlthJpaService.findAllByField("norek", newKode, null);
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}

	@Override
	public String getCurrentFtPricehRefno() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_PRICEH").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getCurrentFtPriceAlthRefno() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_PRICEALTH").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getCurrentFtStockAdjusthRefno() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("__URUT_STKADJUSTH").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getNextFtStockAdjusthRefno() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_ADJUSTH", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_ADJUSTH", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftAdjusthJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftAdjusthJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtAdjusth item = new FtAdjusth();
				List<FtAdjusth> list = new ArrayList<FtAdjusth>();
				list = ftAdjusthJpaService.findAllByField("norek", newKode, null);
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}

	@Override
	public String getCurrentFPromo() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_PROMO").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getNextFPromo() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_PROMO", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_PROMO", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (fPromoJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (fPromoJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FPromo item = new FPromo();
				List<FPromo> list = new ArrayList<FPromo>();
				list = fPromoJpaService.findAllByField("norek", newKode, null);
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}

	@Override
	public String getCurrentFtSaleshSJPenagihan() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_SJPENAGIHAN").iterator();
		while (iter.hasNext()) {
			Sysvar sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//NOMOR URUT SEKARANG
		String currentKode = prefix + nomorUrutSekarang;
		return currentKode;
	}

	@Override
	public String getNextFtSaleshSJPenagihan() {
		boolean baru=true;
		String nomorUrutDefault = "0000001";
		String nomorUrutSekarang = nomorUrutDefault;
		int panjangdefault = 7;
		int panjang = panjangdefault;
		String prefix ="";
		Sysvar sysvar = new Sysvar();
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI

		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_SJPENAGIHAN", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_SJPENAGIHAN", null).iterator();
		while (iter.hasNext()) {
			sysvar = new Sysvar();
			sysvar = iter.next();
			nomorUrutSekarang = sysvar.getNilaiString1().trim();
			//ANTISIPASI NILAI NULL
			panjang = sysvar.getLenghtData();
			if (sysvar.getPrefix() != null){
				prefix = sysvar.getPrefix().trim();
			}
		}
		//PEMBUATAN KODE BARU DIMULAI
		String newKode = nomorUrutSekarang;
		
		//TAMBAH PANJANG SESUAI DENGAN LEN	
		if (ftSaleshJpaService.count() <= 0){
			int intKode = Integer.valueOf(newKode);
			intKode +=1;
			String strKode = String.valueOf(intKode);			
			int len = 0;
			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
			while (len < panjang-1) {				
				len = strKode.length();
				strKode = "0" + strKode;
			}
			strKode = prefix + strKode;
			//INTINYA
			newKode = strKode;
			
		} else {
			baru=false;
		}
		
		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
		if (ftSaleshJpaService.count()>0){
			boolean kodeKembar = true;
			while (kodeKembar==true){
				//1. Jadikan integer dan tambah satu
				//2. Tambah panjang sampai 7 dan tambah prefix
				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
				int intKode = Integer.valueOf(newKode);
				intKode +=1;
				String strKode = String.valueOf(intKode);
				
				int len = 0;
				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
				while (len < panjang-1) {				
					len = strKode.length();
					strKode = "0" + strKode;
				}
				strKode = prefix + strKode;
				//INTINYA
				newKode = strKode;
				
				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
				FtSalesh item = new FtSalesh();
				List<FtSalesh> list = new ArrayList<FtSalesh>();
//				list = ftSaleshJpaService.findAllByField("invoiceno", newKode, null);
//				for (FtSalesh domain:list) {
//					if (domain.getTipefaktur().equals("R")) {
//						list.remove(domain);
//					}
//				}
				list = ftSaleshJpaService.findAllBySJPenagihanNo(newKode, "F");
				
				if (list.size()==0){
					kodeKembar = false;
				}
				
				
				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
				if (strKode.equals("2")) {
					kodeKembar = false;
				}
			}
		}	
		//Rubah nilai SYSVAR BARU di database donk
		sysvar.setNilaiString1(newKode);
		sysvarJpaService.updateObject(sysvar);
		
		return newKode;
	}

	
	
	

}
