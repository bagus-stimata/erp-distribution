package org.erp.distribution.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservice.SysvarJpaServiceImpl;
import org.erp.distribution.model.Sysvar;

import com.vaadin.ui.CustomComponent;

public class SysvarHelper extends CustomComponent{
	private SysvarJpaService sysvarJpaService =  new  SysvarJpaServiceImpl();
	
//	private DivisionJpaService divisionService = new DivisionJpaServiceImpl();
	
	//1. CEK PARAMETER SYSTEM KALAU TIDAK ADA BUAT DEFAULT
	
	public SysvarHelper() {
		
		defaultConfigSysvar();
		
	}
	
	public void defaultConfigSysvar(){
		
		cekOrCreateNewConfigSysvar();
		//1. INFORMASI PERUSAHAAN
		
		//2. TRANSAKSI BERJALAN
		//3. NOMOR URUT TRANSAKSI
	}

	/**		
	 * 	JUMLAH PARAMETER SYSTEM = 16
	 * 
	 * 		_VERSION_PRG	= Program Version
	 * 		_PRSH_KODE		= Kode Perusahaan
	 * 		_PRSH_NAMA		= Nama Perusahaan
	 * 		_COMP_NAME_F	= Nama Perusahaan pada Faktur
	 * 		_COMP_DESC_F	= Deskripsi Perusahaan pada Faktur
	 * 		_COMP_ADDR_F	= Alamat Perusahaan pada Faktur
	 * 		_COMP_PHONE_F	= Telpon Perusahaan pada Faktur
	 * 		_COMP_NPWP_F	= NPWP Perusahaan pada Faktur
	 * 		_JUDUL_FAKTUR_T	= JUDUL FAKTUR TUNAI
	 * 		_JUDUL_FAKTUR_K	= JUDUL FAKTUR KREDIT
	 * 		_JUDUL_FAKTUR_R	= JUDUL FAKTUR RETUR
	 * 
	 * 
	 * 		_TRANSDATE 		= Tanggal Transaksi
	 * 		_TR_PEKAN		= Pekan Transaksi Berjalan
	 * 		_TR_PERIODE		= Periode/BULAN Transaksi Berjalan
	 * 		_TR_TAHUN		= Tahun Transaksi Transaksi Berjalan
	 
	 * 		_URUT_ARPAYH 		= Urut Account Receivable (AR)
	 * 		_URUT_OPNAMEH		= Urut Stock Opname 
	 * 		_URUT_PURCHASEH		= Urut Stock Purchase/Pembelian Pabrik 
	 * 		_URUT_PURCHASEHR		= Urut Stock Purchase/Pembelian Pabrik RETUR
	 * 		_URUT_SALESHPO		= Urut Sales Order PO
	 * 		_URUT_SALESHPOR		= Urut Sales Order PO RETUR
	 * 		_URUT_SALESHINV		= Urut Sales Order INVOICENO RETUR
	 * 		_URUT_SALESHINVR		= Urut Sales Order INVOICENO
	 * 		_URUT_SALESH_SJ		= Urut Surat Jalan
	 * 		_URUT_SPRICEH		= Urut Perubahan Harga (Spriceh)
	 * 		_URUT_STKTRANSH		= Urut Stock Transfer Gudang
	 * 		_URUT_PROMO		= Urut Promo

	 * 		_URUT_BKGR		= Urut Buku Giro 
	 * 		_URUT_TRANSF	= Urut Buku Transfer
	 * 		_TOLE_AR_EDIT   = Tolerance Ar Edit
	 * 		_ALLOW_AR_MIN	= AR Boleh Minus
	 * 		_AR_MIN_IS_LNS	= Jumlah Nominal AR Minus Yang Dianggap Lunas
	 * 		_ALLOW_AR_PLUS  = AR Boleh Plus
	 * 
	 * 		_AR_OVERDUE = Overdue Default Nota Kredit
	 * 		_TOLE_OVERDUE = Toleransi Overdue Nota Kredit sebelum jadi OD
	 * 
	 * 
	 * 		_URUT_BKB  = Nomor urut Buku Kas Besar
	 * 		_URUT_BKK  = Nomor urut Buku Kas Kecil
	 * 		_URUT_BBANK  = Nomor Urut Buku Bank
	 * 

	 * 		_FORMAT_FAKTUR  = Format Faktur Penjualan
	 * 		_FORMAT_FAK_RETAIL  = Format Faktur Retail
	 * 		_PRSH_NAMA_RETAIL		= Nama Perusahaan Pada Faktur Retail
	 * 		_COMP_NAME_RET	= Nama Perusahaan pada Faktur Retail (Nota)
	 * 		_COMP_NAME_RET	= Nama Perusahaan pada Faktur Retail (Nota)
	 * 		_COMP_ADDR_RET	= Alamat Perusahaan pada Faktur Retail (Nota)
	 * 		_COMP_PHONE_RET	= Telpon Perusahaan pada Faktur Retail (Nota)
	 * 		_COMP_NPWP_RET	= NPWP Perusahaan pada Faktur Retail (Nota)
	 * 
	 * 		_SHORTNAME_FAKTUR 		= Faktur Menggunakan Nama Pendek Barang
	 * 		_SHORTNAME_PACKLIST 	= Packing List Menggunakan Nama Pendek Barang
	 * 		_DUE_DATE  = Jatuh Tempo Due Date
	 * 		_ALLOW_STOCK_MINUS  = Stock Boleh Minus
	 * 		_LOCK_HJUAL_FJUAL  = Lock Harga Jual Pada Faktur Penjualan
	 * 		_FAKTUR_TUNAI_LUNAS  = Faktur Tunai Langsung Lunas
	 * 		_PATH_SIP_TIGADARA  = Path Export-Import Sistem Sales Order Android Tiga Dara
	 * 		_ENTRI_JUAL_HRGPPN  = Input Item Barang Penjualan menggunakan Harga Sesudah PPN
	 * 		_ENTRI_BELI_HRGPPN  = Input Item Barang PEMBELIAN menggunakan Harga Sesudah PPN
	 * 
	 * 		_URUT_REGHP 		= Urut Register Service HP
	 * 		_URUT_JOBHP 		= Urut Job Service HP
	 * 
	 */
	
	public void cekOrCreateNewConfigSysvar(){
		Sysvar sysvar = new Sysvar();
		
		sysvar = sysvarJpaService.findById("_VERSION_PRG");
		if (sysvar==null){
			sysvar = new Sysvar();
			sysvar.setId("_VERSION_PRG");

			sysvar.setDeskripsi("Program Version");
			sysvar.setGroupSysvar("_VERSION_PRG");
			sysvar.setTipeData("STRING1");
			sysvar.setNilaiString1("1.0");
			
			sysvarJpaService.createObject(sysvar);
		}
		
		sysvar = sysvarJpaService.findById("_PRSH_KODE");
		if (sysvar==null){
			sysvar = new Sysvar();
			sysvar.setId("_PRSH_KODE");
			
			sysvar.setDeskripsi("Kode Perusahaan");
			sysvar.setGroupSysvar("_PERUSAHAAN");
			sysvar.setTipeData("STRING1");
			sysvar.setNilaiString1("101010");

			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_PRSH_NAMA");
		if (sysvar==null){
			sysvar = new Sysvar();
			sysvar.setId("_PRSH_NAMA");
			
			sysvar.setDeskripsi("Nama Perusahaan");
			sysvar.setGroupSysvar("_PERUSAHAAN");
			sysvar.setTipeData("STRING1");
			sysvar.setNilaiString1("PT. Forward Creative Tech");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		
		sysvar = sysvarJpaService.findById("_PRSH_AREA");
		if (sysvar==null){
			sysvar = new Sysvar();
			sysvar.setId("_PRSH_AREA");
			
			sysvar.setDeskripsi("Area Perusahaan");
			sysvar.setGroupSysvar("_PERUSAHAAN");
			sysvar.setTipeData("STRING2");
			sysvar.setNilaiString1("AREA");
			sysvar.setNilaiString1("AREA");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_PRSH_REG");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_PRSH_REG");
			
			sysvar.setDeskripsi("Region Perusahaan");
			sysvar.setGroupSysvar("_PERUSAHAAN");
			sysvar.setTipeData("STRING2");
			sysvar.setNilaiString1("REG");
			sysvar.setNilaiString2("REGION");

			sysvarJpaService.createObject(sysvar);
			
		}
		
		sysvar = sysvarJpaService.findById("_TRANSDATE");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_TRANSDATE");
			
			sysvar.setDeskripsi("Tanggal Transaksi Berjalan/TANGGAL SETOR");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("DATE1");
			
			Date tgl = new Date(new Date().getYear(), 0, 1);			
			sysvar.setNilaiDate1(tgl);
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_TR_PERIODE");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_TR_PERIODE");
			
			sysvar.setDeskripsi("Periode Transaksi Berjalan");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(1);
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_TR_TAHUN");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_TR_TAHUN");
			
			sysvar.setDeskripsi("Tahun Transaksi Berjalan");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int currentYear = cal.get(Calendar.YEAR);
			sysvar.setNilaiInt1(currentYear);
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_TR_PEKAN");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_TR_PEKAN");
			
			sysvar.setDeskripsi("Pekan Transaksi Berjalan");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(1);
			
			sysvarJpaService.createObject(sysvar);
			
		}
		
		//#### TRANSAKSI PENJUALAN OUTLET ####

			sysvar = sysvarJpaService.findById("_URUT_ARPAYH");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_ARPAYH");
				
				sysvar.setDeskripsi(" Urut Account Receivable (AR)");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_OPNAMEH");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_OPNAMEH");
				
				sysvar.setDeskripsi("Urut Stock Opname");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_PURCHASEH");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_PURCHASEH");
				
				sysvar.setDeskripsi("Urut Stock Purchase/Pembelian Pabrik");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_PURCHASEHR");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_PURCHASEHR");
				
				sysvar.setDeskripsi("Urut RETUR Pabrik ");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_SALESHPO");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_SALESHPO");
				
				sysvar.setDeskripsi("Urut Sales Order");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_SALESHPOR");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_SALESHPOR");
				
				sysvar.setDeskripsi("Urut Sales Order RETUR");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_SALESHINV");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_SALESHINV");
				
				sysvar.setDeskripsi("Urut Sales Order NOMOR INVOICE");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_SALESH_SJ");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_SALESH_SJ");
				
				sysvar.setDeskripsi("Urut SURAT JALAN");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_SJPENAGIHAN");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_SJPENAGIHAN");
				
				sysvar.setDeskripsi("Urut SURAT JALAN PENAGIHAN");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_SALESHINVR");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_SALESHINVR");
				
				sysvar.setDeskripsi("Urut Sales Order NOMOR RETUR");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_SPRICEH");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_SPRICEH");
				
				sysvar.setDeskripsi("Urut Perubahan Harga (Spriceh)");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_STKTRANSH");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_STKTRANSH");
				
				sysvar.setDeskripsi("Urut Stock Transfer Gudang");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_PROMO");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_PROMO");
				
				sysvar.setDeskripsi("Urut Aktifitas Promo");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}

			
			sysvar = sysvarJpaService.findById("_URUT_PRICEH");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_PRICEH");
				
				sysvar.setDeskripsi("Urut Perubahan Harga Beli dan Jual(ftPriceh)");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			sysvar = sysvarJpaService.findById("_URUT_PRICEALTH");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_PRICEALTH");
				
				sysvar.setDeskripsi("Urut Harga Alternatif Beli dan Jual");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}

			sysvar = sysvarJpaService.findById("_URUT_ADJUSTH");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_ADJUSTH");
				
				sysvar.setDeskripsi("Urut Harga Stok Opname/Stock Adjust");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(10);
				sysvar.setNilaiString1("0000000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
			
//#########################################	 	 
		 
		sysvar = sysvarJpaService.findById("_URUT_BKGR");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_URUT_BKGR");
			
			sysvar.setDeskripsi("Nomor Urut Buku Giro");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(10);
			sysvar.setNilaiString1("0000000001");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_URUT_TRANSF");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_URUT_TRANSF");
			
			sysvar.setDeskripsi("Nomor Urut Buku Transfer");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(10);
			sysvar.setNilaiString1("0000000001");
			
			sysvarJpaService.createObject(sysvar);
			
		}		
		sysvar = sysvarJpaService.findById("_URUT_AR");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_URUT_AR");
			
			sysvar.setDeskripsi("Nomor Urut Dokumen Transaksi Account Receivable");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(10);
			sysvar.setNilaiString1("0000000001");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_TOLE_AR_EDIT");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_TOLE_AR_EDIT");
			
			sysvar.setDeskripsi("Toleransi Boleh Edit Transaksi Account Receivable Seteleh Proses Akhir hari");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(7);
			
			sysvarJpaService.createObject(sysvar);
			
		}
		
		sysvar = sysvarJpaService.findById("_ALLOW_AR_MIN");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_ALLOW_AR_MIN");
			
			sysvar.setDeskripsi("Pembayaran AR Boleh Minus");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("BOL1");
			sysvar.setNilaiBol1(true);
			
			sysvarJpaService.createObject(sysvar);
			
		}
		
		sysvar = sysvarJpaService.findById("_AR_MIN_IS_LNS");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_AR_MIN_IS_LNS");
			
			sysvar.setDeskripsi("Jumlah Nominal AR Minus Yang Dianggap Lunas");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("DOUBLE1");
			sysvar.setNilaiDouble1(100.0);
			
			sysvarJpaService.createObject(sysvar);
			
		}		
		sysvar = sysvarJpaService.findById("_ALLOW_AR_PLUS");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_ALLOW_AR_PLUS");
			
			sysvar.setDeskripsi("Pembayaran AR Boleh Surplus");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("BOL1");
			sysvar.setNilaiBol1(true);
			
			sysvarJpaService.createObject(sysvar);
			
		}
		
		sysvar = sysvarJpaService.findById("_AR_OVERDUE");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_AR_OVERDUE");
			
			sysvar.setDeskripsi("Overdue/Jatuh Tempo Default Nota Kredit");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(7);
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_TOLE_OVERDUE");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_TOLE_OVERDUE");
			
			sysvar.setDeskripsi("Toleransi Overdue Nota Kredit sebelum jadi OD");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(7); //7 hari dari Jatuh tempo >> berarti 14 hari
			
			sysvarJpaService.createObject(sysvar);
			
		}
		
		sysvar = sysvarJpaService.findById("_URUT_BBANK");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_URUT_BBANK");
			
			sysvar.setDeskripsi("Nomor Urut Buku Bank");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(10);
			sysvar.setNilaiString1("0000000001");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_URUT_BKB");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_URUT_BKB");
			
			sysvar.setDeskripsi("Nomor Urut Buku Kas Besar");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(10);
			sysvar.setNilaiString1("0000000001");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_URUT_BKK");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_URUT_BKK");
			
			sysvar.setDeskripsi("Nomor Urut Buku Kas Kecil");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(10);
			sysvar.setNilaiString1("0000000001");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		
		sysvar = sysvarJpaService.findById("_FORMAT_FAKTUR");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_FORMAT_FAKTUR");
			
			sysvar.setDeskripsi("Format Faktur");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(1);
			
			sysvarJpaService.createObject(sysvar);
		}
		//FORMAT FAKTUR RETAIL
		sysvar = sysvarJpaService.findById("_FORMAT_FAK_RETAIL");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_FORMAT_FAK_RETAIL");
			
			sysvar.setDeskripsi("Format Faktur Retail (101 S.D 200");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(101);
			
			sysvarJpaService.createObject(sysvar);
		}
		sysvar = sysvarJpaService.findById("_PRSH_NAMA");
		if (sysvar==null){
			sysvar = new Sysvar();
			sysvar.setId("_PRSH_NAMA_RETAIL");
			
			sysvar.setDeskripsi("Nama Perusahaan Retail");
			sysvar.setGroupSysvar("_PRSH_NAMA_RETAIL");
			sysvar.setTipeData("STRING1");
			sysvar.setNilaiString1("PT. Forward Creative Tech");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		
		sysvar = sysvarJpaService.findById("_PRSH_NAMA");
		if (sysvar==null){
			sysvar = new Sysvar();
			sysvar.setId("_PRSH_NAMA_RETAIL");
			
			sysvar.setDeskripsi("Nama Perusahaan Retail");
			sysvar.setGroupSysvar("_PRSH_NAMA_RETAIL");
			sysvar.setTipeData("STRING1");
			sysvar.setNilaiString1("PT. Forward Creative Tech");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_COMP_NAME_RET");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_COMP_NAME_RET");
			
			sysvar.setDeskripsi("Nama Perusahaan pada Faktur Retail (Nota)");
			sysvar.setGroupSysvar("_FORMATF");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("DES(DISTRIBUTION ENTERPRISE SYSTEM)");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_COMP_ADDR_RET");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_COMP_ADDR_RET");
			
			sysvar.setDeskripsi("Alamat Perusahaan pada Faktur Retail (Nota)");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("Jln. Kauman Gang IVA/45, Malang");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_COMP_PHONE_RET");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_COMP_PHONE_RET");
			
			sysvar.setDeskripsi("Telpon Perusahaan pada Faktur Retail (Nota)");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("Telp. 0821-43574692");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_COMP_NPWP_RET");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_COMP_NPWP_RET");
			
			sysvar.setDeskripsi("NPWK dan SK Pengukuhan Pajak pada Faktur Retail (Nota)");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("NPWP. 123456789, SK. 01-01-2014");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		
		
		sysvar = sysvarJpaService.findById("_SHORTNAME_FAKTUR");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_SHORTNAME_FAKTUR");
			
			sysvar.setDeskripsi("Faktur Menggunakan Nama Pendek Barang");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("BOL1");
			sysvar.setNilaiInt1(0);
			
			sysvarJpaService.createObject(sysvar);
		}
	
		sysvar = sysvarJpaService.findById("_SHORTNAME_PACKLIST");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_SHORTNAME_PACKLIST");
			
			sysvar.setDeskripsi("Packing List Menggunakan Nama Pendek Barang");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("BOL1");
			sysvar.setNilaiInt1(0);
			
			sysvarJpaService.createObject(sysvar);
		}
			
		sysvar = sysvarJpaService.findById("_DUE_DATE");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_DUE_DATE");
			
			sysvar.setDeskripsi("Jatuh Tempo Due Date");
			sysvar.setGroupSysvar("_TRANSAKSI");
			sysvar.setTipeData("INT1");
			sysvar.setNilaiInt1(14);
			
			sysvarJpaService.createObject(sysvar);
		}
		sysvar = sysvarJpaService.findById("_ALLOW_STOCK_MINUS");
		if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_ALLOW_STOCK_MINUS");
				
				sysvar.setDeskripsi("Stock Gudang Boleh Minus");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("BOL1");
				sysvar.setNilaiBol1(false);
				
				sysvarJpaService.createObject(sysvar);
		}
		sysvar = sysvarJpaService.findById("_LOCK_HJUAL_FJUAL");
		if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_LOCK_HJUAL_FJUAL");
				
				sysvar.setDeskripsi("Lock Harga Jual Pada Faktur Penjualan");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("BOL1");
				sysvar.setNilaiBol1(false);
				
				sysvarJpaService.createObject(sysvar);
		}

		sysvar = sysvarJpaService.findById("_ENTRI_JUAL_HRGPPN");
		if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_ENTRI_JUAL_HRGPPN");
				
				sysvar.setDeskripsi("Input Item Barang Penjualan menggunakan Harga Sesudah PPN");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("BOL1");
				sysvar.setNilaiBol1(false);
				
				sysvarJpaService.createObject(sysvar);
		}
		sysvar = sysvarJpaService.findById("_ENTRI_BELI_HRGPPN");
		if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_ENTRI_BELI_HRGPPN");
				
				sysvar.setDeskripsi("Input Item Barang PEMBELIAN menggunakan Harga Sesudah PPN");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("BOL1");
				sysvar.setNilaiBol1(false);
				
				sysvarJpaService.createObject(sysvar);
		}

		sysvar = sysvarJpaService.findById("_FAKTUR_TUNAI_LUNAS");
		if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_FAKTUR_TUNAI_LUNAS");
				
				sysvar.setDeskripsi("Faktur Tunai Langsung Lunas");
				sysvar.setGroupSysvar("_TRANSAKSI");
				sysvar.setTipeData("BOL1");
				sysvar.setNilaiBol1(false);
				
				sysvarJpaService.createObject(sysvar);
		}
		
		
		sysvar = sysvarJpaService.findById("_COMP_NAME_F");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_COMP_NAME_F");
			
			sysvar.setDeskripsi("Nama Perusahaan pada Faktur");
			sysvar.setGroupSysvar("_FORMATF");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("DES(DISTRIBUTION ENTERPRISE SYSTEM)");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_COMP_DESC_F");
		if (sysvar==null){
			sysvar = new Sysvar();
			sysvar.setId("_COMP_DESC_F");
			
			sysvar.setDeskripsi("Deskripsi Perusahaan pada Faktur");
			sysvar.setGroupSysvar("_PERUSAHAAN");
			sysvar.setTipeData("STRING1");
			sysvar.setNilaiString1("Research and Software Solution");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		
		sysvar = sysvarJpaService.findById("_COMP_ADDR_F");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_COMP_ADDR_F");
			
			sysvar.setDeskripsi("Alamat Perusahaan pada Faktur");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("Jln. Kauman Gang IVA/45, Malang");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_COMP_NAME_F");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_COMP_NAME_F");
			
			sysvar.setDeskripsi("Nama Perusahaan pada Faktur");
			sysvar.setGroupSysvar("_FORMATF");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("DES(DISTRIBUTION ENTERPRISE SYSTEM)");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_COMP_PHONE_F");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_COMP_PHONE_F");
			
			sysvar.setDeskripsi("Telpon Perusahaan pada Faktur");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("Telp. 0821-43574692");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_COMP_NPWP_F");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_COMP_NPWP_F");
			
			sysvar.setDeskripsi("NPWK dan SK Pengukuhan Pajak pada Faktur");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("NPWP. 123456789, SK. 01-01-2014");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_JUDUL_FAKTUR_T");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_JUDUL_FAKTUR_T");
			
			sysvar.setDeskripsi("Judul pada faktur Tunai");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("FAKTUR PENJUALAN");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_JUDUL_FAKTUR_T");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_JUDUL_FAKTUR_T");
			
			sysvar.setDeskripsi("Judul pada faktur Tunai");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("FAKTUR PENJUALAN");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_JUDUL_FAKTUR_K");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_JUDUL_FAKTUR_K");
			
			sysvar.setDeskripsi("Judul pada faktur Kredit");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("FAKTUR PENJUALAN");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_JUDUL_FAKTUR_R");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_JUDUL_FAKTUR_R");
			
			sysvar.setDeskripsi("Judul pada faktur Retur");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("RETUR PENJUALAN");
			
			sysvarJpaService.createObject(sysvar);
			
		}
		sysvar = sysvarJpaService.findById("_JUDUL_FAKTUR_RET");
		if (sysvar==null){
			sysvar = new Sysvar();		
			sysvar.setId("_JUDUL_FAKTUR_RET");
			
			sysvar.setDeskripsi("Judul pada faktur RETAIL");
			sysvar.setGroupSysvar("_FORMAT");
			sysvar.setTipeData("STRING1");
			sysvar.setLenghtData(100);
			sysvar.setNilaiString1("FAKTUR PENJUALAN");
			
			sysvarJpaService.createObject(sysvar);
			
		}
			sysvar = sysvarJpaService.findById("_PATH_SIP_TIGADARA");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_PATH_SIP_TIGADARA");
				
				sysvar.setDeskripsi("Path Export-Import Sistem Sales Order Android Tiga Dara");
				sysvar.setGroupSysvar("_UTILITIES");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(7);
				sysvar.setNilaiString1("c://sip_android/");
				
				sysvarJpaService.createObject(sysvar);
				
			}
					 
			sysvar = sysvarJpaService.findById("_URUT_REGHP");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_REGHP");
				
				sysvar.setDeskripsi("Urut Register Service HP");
				sysvar.setGroupSysvar("_SERVICEHP");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(7);
				sysvar.setNilaiString1("0000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}
					 
			sysvar = sysvarJpaService.findById("_URUT_JOBHP");
			if (sysvar==null){
				sysvar = new Sysvar();		
				sysvar.setId("_URUT_JOBHP");
				
				sysvar.setDeskripsi("Urut Job Service HP");
				sysvar.setGroupSysvar("_SERVICEHP");
				sysvar.setTipeData("STRING1");
				sysvar.setLenghtData(7);
				sysvar.setNilaiString1("0000001");
				
				sysvarJpaService.createObject(sysvar);
				
			}

		
	}
	
	//2. TanggalTransaksi Berjalan
	public Date getTanggalTransaksiBerjalan(){
		Date transDate = null;
		transDate = sysvarJpaService.findById("_TRANSDATE").getNilaiDate1();
		return transDate;
	}
	
	public int getFormatFaktur(){
		return sysvarJpaService.findById("_FORMAT_FAKTUR").getNilaiInt1();
		
	}
	public int getFormatFakturRetail(){
		return sysvarJpaService.findById("_FORMAT_FAK_RETAIL").getNilaiInt1();
		
	}
	public int getDueDate(){
		return sysvarJpaService.findById("_DUE_DATE").getNilaiInt1();
		
	}
	public boolean isAllowStockMinus(){
		return sysvarJpaService.findById("_ALLOW_STOCK_MINUS").getNilaiBol1();
		
	}
	public boolean isFakturTunaiLangsungLunas(){
		return sysvarJpaService.findById("_FAKTUR_TUNAI_LUNAS").getNilaiBol1();
		
	}
//	 * 		_COMP_NAME_F	= Nama Perusahaan pada Faktur
//	 * 		_COMP_ADDR_F	= Alamat Perusahaan pada Faktur
//	 * 		_COMP_PHONE_F	= Telpon Perusahaan pada Faktur
//	 * 		_COMP_NPWP_F	= NPWP Perusahaan pada Faktur
//	 * 		_JUDUL_FAKTUR_T	= JUDUL FAKTUR TUNAI
//	 * 		_JUDUL_FAKTUR_K	= JUDUL FAKTUR KREDIT
//	 * 		_JUDUL_FAKTUR_R	= JUDUL FAKTUR RETUR
	public String getCompanyNameFaktur(){
		return sysvarJpaService.findById("_COMP_NAME_F").getNilaiString1();
	}
	public String getCompanyNameDescription(){
		return sysvarJpaService.findById("_COMP_DESC_F").getNilaiString1();
	}
	public String getCompanyNameFakturRetur(){
		return sysvarJpaService.findById("_COMP_NAME_F").getNilaiString1();	
	}
	public String getCompanyAddressFaktur(){
		return sysvarJpaService.findById("_COMP_ADDR_F").getNilaiString1();
	}
	public String getCompanyPhoneFaktur(){
		return sysvarJpaService.findById("_COMP_PHONE_F").getNilaiString1();
	}
	public String getCompanyNpwpFaktur(){
		return sysvarJpaService.findById("_COMP_NPWP_F").getNilaiString1();
	}
	public String getCompanyNameFakturRetail(){
		return sysvarJpaService.findById("_COMP_NAME_RET").getNilaiString1();
	}
	public String getCompanyAddressFakturRetail(){
		return sysvarJpaService.findById("_COMP_ADDR_RET").getNilaiString1();
	}
	public String getCompanyPhoneFakturRetail(){
		return sysvarJpaService.findById("_COMP_PHONE_RET").getNilaiString1();
	}
	public String getCompanyNpwpFakturRetail(){
		return sysvarJpaService.findById("_COMP_NPWP_RET").getNilaiString1();
	}
	
	public String getJudulFakturTunai(){
		return sysvarJpaService.findById("_JUDUL_FAKTUR_T").getNilaiString1();
	}
	public String getJudulFakturKredit(){
		return sysvarJpaService.findById("_JUDUL_FAKTUR_K").getNilaiString1();
	}
	public String getJudulFakturRetur(){
		return sysvarJpaService.findById("_JUDUL_FAKTUR_R").getNilaiString1();
	}
	public String getJudulFakturRetail(){
		return sysvarJpaService.findById("_JUDUL_FAKTUR_RET").getNilaiString1();
	}
	public Boolean isShortNamePadaFaktur(){
		return sysvarJpaService.findById("_SHORTNAME_FAKTUR").getNilaiBol1();
	}
	public Boolean isShortNamePadaPackingList(){
		return sysvarJpaService.findById("_SHORTNAME_PACKLIST").getNilaiBol1();
	}

	public String getPathSipAndroidTigaDara(){
		return sysvarJpaService.findById("_PATH_SIP_TIGADARA").getNilaiString1();
	}
	public Boolean isLockHJualFJual(){
		return sysvarJpaService.findById("_LOCK_HJUAL_FJUAL").getNilaiBol1();
	}
	public Boolean isEntryItemPenjualanHargaSelelahPPN(){
		return sysvarJpaService.findById("_ENTRI_JUAL_HRGPPN").getNilaiBol1();
	}
	public Boolean isEntryItemPembelianHargaSelelahPPN(){
		return sysvarJpaService.findById("_ENTRI_BELI_HRGPPN").getNilaiBol1();
	}
	
		
	public static void main(String [] args){
		SysvarHelper helper = new SysvarHelper();
		helper.defaultConfigSysvar();
		
	}
}
