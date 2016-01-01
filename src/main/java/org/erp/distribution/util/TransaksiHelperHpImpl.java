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
import org.erp.distribution.jpaservicehp.StServiceJpaService;
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
import org.erp.distribution.model.StService;
import org.erp.distribution.model.Sysvar;

import com.vaadin.ui.CustomComponent;


public class TransaksiHelperHpImpl extends CustomComponent implements TransaksiHelperHp{
	private SysvarJpaService sysvarJpaService;

	private StServiceJpaService stServiceJpaService;
	
	 public TransaksiHelperHpImpl() {
			setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
			setStServiceJpaService((((DashboardUI) getUI().getCurrent()).getStServiceJpaService()));
			
			
	 }


	@Override
	public String getCurrentRegServiceRefno() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_REGHP").iterator();
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
	public String getNextRegServiceRefno() {
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
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_REGHP", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_REGHP", null).iterator();
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
		if (stServiceJpaService.count() <= 0){
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
		if (stServiceJpaService.count()>0){
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
				StService item = new StService();
				List<StService> list = new ArrayList<StService>();
				list = stServiceJpaService.findAllByField("noreg", newKode, null);
				
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
	public String getCurrentJobServiceRefno() {
		//KALAU NOMOR URUT MASIH KOSONG MAKA DIKASIH INI
		String nomorUrutSekarang = "0000001";
		String prefix ="";
		//1. GET NOMOR URUT SEKARANG
		//2. NOMOR URUT SEKARANG TAMBAH 1
		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
		Iterator<Sysvar> iter = sysvarJpaService.findAllById("_URUT_JOBHP").iterator();
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
	public String getNextJobServiceRefno() {
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
		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_JOBHP", null);
		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_JOBHP", null).iterator();
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
		if (stServiceJpaService.count() <= 0){
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
		if (stServiceJpaService.count()>0){
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
				StService item = new StService();
				List<StService> list = new ArrayList<StService>();
				list = stServiceJpaService.findAllByField("nojob", newKode, null);
				
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
	
	

	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}

	public StServiceJpaService getStServiceJpaService() {
		return stServiceJpaService;
	}

	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}

	public void setStServiceJpaService(StServiceJpaService stServiceJpaService) {
		this.stServiceJpaService = stServiceJpaService;
	}
	
//	@Override
//	public String getNextFtArpaymenthRefno() {
//		boolean baru=true;
//		String nomorUrutDefault = "0000001";
//		String nomorUrutSekarang = nomorUrutDefault;
//		int panjangdefault = 7;
//		int panjang = panjangdefault;
//		String prefix ="";
//		Sysvar sysvar = new Sysvar();
//		//1. GET NOMOR URUT SEKARANG
//		//2. NOMOR URUT SEKARANG TAMBAH 1
//		//3. CEK (DENGAN PERULANGAN)>> JIKA NOMOR URUT SEKARANG SUDAH TER PAKAI MAKA TAMBAH 1 LAGI
//
//		List<Sysvar> listSysvar = new ArrayList<Sysvar>();
//		listSysvar = sysvarJpaService.findAllByField("id", "_URUT_ARPAYH", null);
//		Iterator<Sysvar> iter = sysvarJpaService.findAllByField("id", "_URUT_ARPAYH", null).iterator();
//		while (iter.hasNext()) {
//			sysvar = new Sysvar();
//			sysvar = iter.next();
//			nomorUrutSekarang = sysvar.getNilaiString1().trim();
//			//ANTISIPASI NILAI NULL
//			panjang = sysvar.getLenghtData();
//			if (sysvar.getPrefix() != null){
//				prefix = sysvar.getPrefix().trim();
//			}
//		}
//		//PEMBUATAN KODE BARU DIMULAI
//		String newKode = nomorUrutSekarang;
//		
//		//TAMBAH PANJANG SESUAI DENGAN LEN	
//		if (ftArpaymenthJpaService.count() <= 0){
//			int intKode = Integer.valueOf(newKode);
//			intKode +=1;
//			String strKode = String.valueOf(intKode);			
//			int len = 0;
//			//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
//			while (len < panjang-1) {				
//				len = strKode.length();
//				strKode = "0" + strKode;
//			}
//			strKode = prefix + strKode;
//			//INTINYA
//			newKode = strKode;
//			
//		} else {
//			baru=false;
//		}
//		
//		//ULANG SAMPAI TIDAK ADA YANG KEMBAR >> JIKA NAIK SATU TIDAK KEMBAR MAKA ITU NOMOR SEKARANG
//		if (ftArpaymenthJpaService.count()>0){
//			boolean kodeKembar = true;
//			while (kodeKembar==true){
//				//1. Jadikan integer dan tambah satu
//				//2. Tambah panjang sampai 7 dan tambah prefix
//				//3. CEK >> DATABASE >> JIKA TIDAK KEMBAR MAKA KEMBAR FALSE
//				int intKode = Integer.valueOf(newKode);
//				intKode +=1;
//				String strKode = String.valueOf(intKode);
//				
//				int len = 0;
//				//JIKA MELEBIHI PANJANG MAKA AKAN MEMANJANG OTOMATIS
//				while (len < panjang-1) {				
//					len = strKode.length();
//					strKode = "0" + strKode;
//				}
//				strKode = prefix + strKode;
//				//INTINYA
//				newKode = strKode;
//				
//				//JIKA TIDAK ADA YANG SAMA MAKA BERHENTI
//				FtArpaymenth item = new FtArpaymenth();
//				List<FtArpaymenth> list = new ArrayList<FtArpaymenth>();
//				list = ftArpaymenthJpaService.findAllByField("norek", newKode, null);
//				
//				if (list.size()==0){
//					kodeKembar = false;
//				}
//				
//				
//				//GAK TAHU KENAPA KOK GAK MAU ANGKA 2
//				if (strKode.equals("2")) {
//					kodeKembar = false;
//				}
//			}
//		}	
//		//Rubah nilai SYSVAR BARU di database donk
//		sysvar.setNilaiString1(newKode);
//		sysvarJpaService.updateObject(sysvar);
//		
//		return newKode;
//	}




	
	
	

}
