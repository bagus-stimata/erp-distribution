package org.erp.distribution.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductJpaServiceImpl;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FSalesmanJpaServiceImpl;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FStockJpaServiceImpl;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaServiceImpl;
import org.erp.distribution.jpaservice.FtAdjustdJpaService;
import org.erp.distribution.jpaservice.FtAdjustdJpaServiceImpl;
import org.erp.distribution.jpaservice.FtAdjusthJpaService;
import org.erp.distribution.jpaservice.FtAdjusthJpaServiceImpl;
import org.erp.distribution.jpaservice.FtOpnamedJpaService;
import org.erp.distribution.jpaservice.FtOpnamedJpaServiceImpl;
import org.erp.distribution.jpaservice.FtOpnamehJpaService;
import org.erp.distribution.jpaservice.FtOpnamehJpaServiceImpl;
import org.erp.distribution.jpaservice.FtPurchasedJpaService;
import org.erp.distribution.jpaservice.FtPurchasedJpaServiceImpl;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaServiceImpl;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaServiceImpl;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaServiceImpl;
import org.erp.distribution.jpaservice.FtStocktransferdJpaService;
import org.erp.distribution.jpaservice.FtStocktransferdJpaServiceImpl;
import org.erp.distribution.jpaservice.FtStocktransferhJpaService;
import org.erp.distribution.jpaservice.FtStocktransferhJpaServiceImpl;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservice.SysvarJpaServiceImpl;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtAdjustd;
import org.erp.distribution.model.FtAdjusth;
import org.erp.distribution.model.FtOpnamed;
import org.erp.distribution.model.FtPurchased;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtStocktransferd;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;

public class ProductAndStockHelper extends CustomComponent{
	
	private SysvarJpaService sysvarJpaService;
	private FStockJpaService fStockJpaService;
	private FProductJpaService fProductJpaService;
	private FWarehouseJpaService fWarehouseJpaService;

	private FSalesmanJpaService fSalesmanJpaService;
	private FtSaleshJpaService ftSaleshJpaService;
	private FtSalesdJpaService ftSalesdJpaService;

	private FtPurchasehJpaService ftPurchasehJpaService;
	private FtPurchasedJpaService ftPurchasedJpaService;
	private FtStocktransferhJpaService ftStocktransferhJpaService;
	private FtStocktransferdJpaService ftStocktransferdJpaService;
	
	private FtAdjusthJpaService ftAdjusthJpaService;
	private FtAdjustdJpaService ftAdjustdJpaService;
	
	private FtOpnamehJpaService ftOpnamehJpaService;
	private FtOpnamedJpaService ftOpnamedJpaService;
	
	
	public ProductAndStockHelper(){		
		
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		setfSalesmanJpaService((((DashboardUI) getUI().getCurrent()).getfSalesmanJpaService()));
		

		setfSalesmanJpaService(fSalesmanJpaService);
		setFtSaleshJpaService((((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService()));
		setFtSalesdJpaService((((DashboardUI) getUI().getCurrent()).getFtSalesdJpaService()));
		
		setFtPurchasehJpaService((((DashboardUI) getUI().getCurrent()).getFtPurchasehJpaService()));
		setFtPurchasedJpaService((((DashboardUI) getUI().getCurrent()).getFtPurchasedJpaService()));		
		setFtStocktransferhJpaService((((DashboardUI) getUI().getCurrent()).getFtStocktransferhJpaService()));
		setFtStocktransferdJpaService((((DashboardUI) getUI().getCurrent()).getFtStocktransferdJpaService()));
	
		setFtAdjusthJpaService((((DashboardUI) getUI().getCurrent()).getFtAdjusthJpaService()));
		setFtAdjustdJpaService((((DashboardUI) getUI().getCurrent()).getFtAdjustdJpaService()));
		
		setFtOpnamehJpaService((((DashboardUI) getUI().getCurrent()).getFtOpnamehJpaService()));
		setFtOpnamedJpaService((((DashboardUI) getUI().getCurrent()).getFtOpnamedJpaService()));
		
	}
	//##UPDATE STOCK FROM TRANSACTION##
	//**STOCK TRANSFER**
	public void stockTransferAdd(FWarehouse fWarehouseFrom, FWarehouse fWarehouseTo, List<FtStocktransferd> listFt, Date trdate){
		stockTransferRemove(fWarehouseFrom, listFt, trdate);
		stockTransferAdd(fWarehouseTo, listFt, trdate);
	}
	public void stockTransferRemove(FWarehouse fWarehouseFrom, FWarehouse fWarehouseTo, List<FtStocktransferd> listFt, Date trdate){
		stockTransferRemove(fWarehouseTo, listFt, trdate);
		stockTransferAdd(fWarehouseFrom, listFt, trdate);
	}
	public void stockTransferAdd(FWarehouse fWarehouse, List<FtStocktransferd> listFt, Date trdate){
		
		Iterator<FtStocktransferd> iter = listFt.iterator();
		while (iter.hasNext()) {
			FtStocktransferd item = new FtStocktransferd();
			item = iter.next();
			
			//1. CARI :: Stockdate, Warehouse/salesman, Product
			//2. KALAU BELUM ADA TAMBAHKAN
			//2.1 JIKA SUDAH ADA MAKA DISITU
			
			List<FStock> listStock =  fStockJpaService.findAll(fWarehouse, item.getFproductBean(), trdate);
			FStock newStock = new FStock();
			Integer saldoAwal =0;
			
			if (listStock.size() > 0) {
				newStock = new FStock();
				newStock = listStock.get(0);
				
				saldoAwal = newStock.getSaldoawal();
				
				Integer newQtyIn = newStock.getQtyin() + item.getQty();
				newStock.setQtyin(newQtyIn);
				
				
			} else {
				//JIKA BELUM PERNAH ADA
				//1. LIHAT TANGGAL SEBELUM::untuk ambil saldo akhir --> untuk saldo awal
				//2. JIKA KOSONG:: maka saldo awal adalah nol
				List<FStock> listStockBefore = fStockJpaService.findAllBefore(fWarehouse, item.getFproductBean(), trdate);
				if (listStockBefore.size() >0) {
					FStock stockBefore = new FStock();
					stockBefore = listStockBefore.get(0);
					saldoAwal = stockBefore.getSaldoakhir(); 
				}
				
				newStock = new FStock();
				newStock.setFproductBean(item.getFproductBean());
				newStock.setFwarehouseBean(fWarehouse);
				newStock.setStockdate(trdate);
				
				newStock.setSaldoawal(saldoAwal);
				newStock.setQtyhold(item.getQty());
				newStock.setQtyin(item.getQty());
				newStock.setQtyout(0);
				newStock.setQtyadjust(0);
				

			}
			
			Integer saldoAkhir = saldoAwal + newStock.getQtyin() - newStock.getQtyout() + newStock.getQtyadjust();
			newStock.setSaldoakhir(saldoAkhir);
			fStockJpaService.updateObject(newStock);
			
			//UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN
			updateStockAfterdate(fWarehouse, item.getFproductBean(), trdate, newStock);
			
		}		
	}
	
	public void stockTransferRemove(FWarehouse fWarehouse, List<FtStocktransferd> listFt, Date trdate) {
		Iterator<FtStocktransferd> iter = listFt.iterator();
		while (iter.hasNext()) {
			FtStocktransferd item = new FtStocktransferd();
			item = iter.next();
			
			//1. CARI :: Stockdate, Warehouse/salesman, Product
			//2. KALAU BELUM ADA TAMBAHKAN
			//2.1 JIKA SUDAH ADA MAKA DISITU
			
			List<FStock> listStock =  fStockJpaService.findAll(fWarehouse, item.getFproductBean(), trdate);
			FStock newStock = new FStock();
			Integer saldoAwal =0;
			
			if (listStock.size() > 0) {
				newStock = new FStock();
				newStock = listStock.get(0);
				
				saldoAwal = newStock.getSaldoawal();
				
				Integer newQtyIn = newStock.getQtyin() - item.getQty();
				newStock.setQtyin(newQtyIn);
				
			} else {
				//JIKA BELUM PERNAH ADA
				//1. LIHAT TANGGAL SEBELUM::untuk ambil saldo akhir --> untuk saldo awal
				//2. JIKA KOSONG:: maka saldo awal adalah nol
				List<FStock> listStockBefore = fStockJpaService.findAllBefore(fWarehouse, item.getFproductBean(), trdate);
				if (listStockBefore.size() >0) {
					FStock stockBefore = new FStock();
					stockBefore = listStockBefore.get(0);
					saldoAwal = stockBefore.getSaldoakhir(); 
				}
				
				newStock = new FStock();
				newStock.setFproductBean(item.getFproductBean());
				newStock.setFwarehouseBean(fWarehouse);
				newStock.setStockdate(trdate);
				
				newStock.setSaldoawal(saldoAwal);
				newStock.setQtyhold(item.getQty());
				newStock.setQtyin(-item.getQty());
				newStock.setQtyout(0);
				newStock.setQtyadjust(0);

			}
			
			Integer saldoAkhir = saldoAwal + newStock.getQtyin() - newStock.getQtyout() + newStock.getQtyadjust();
			newStock.setSaldoakhir(saldoAkhir);
			fStockJpaService.updateObject(newStock);
			
			//UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN
			updateStockAfterdate(fWarehouse, item.getFproductBean(), trdate, newStock);
			
		}
		
	}
	//**INCOMING STOCK AND RETUR**
	public void incomingStockAdd(FWarehouse fWarehouse, List<FtPurchased> listFt, Date trdate){
		
		Iterator<FtPurchased> iter = listFt.iterator();
		while (iter.hasNext()) {
			FtPurchased item = new FtPurchased();
			item = iter.next();
			
			//1. CARI :: Stockdate, Warehouse/salesman, Product
			//2. KALAU BELUM ADA TAMBAHKAN
			//2.1 JIKA SUDAH ADA MAKA DISITU
			
			List<FStock> listStock =  fStockJpaService.findAll(fWarehouse, item.getFproductBean(), trdate);
			FStock newStock = new FStock();
			Integer saldoAwal =0;
			
			if (listStock.size() > 0) {
				newStock = new FStock();
				newStock = listStock.get(0);
				
				saldoAwal = newStock.getSaldoawal();
				
				Integer newQtyIn = newStock.getQtyin() + item.getQty();
				newStock.setQtyin(newQtyIn);
				
				
			} else {
				//JIKA BELUM PERNAH ADA
				//1. LIHAT TANGGAL SEBELUM::untuk ambil saldo akhir --> untuk saldo awal
				//2. JIKA KOSONG:: maka saldo awal adalah nol
				List<FStock> listStockBefore = fStockJpaService.findAllBefore(fWarehouse, item.getFproductBean(), trdate);
				if (listStockBefore.size() >0) {
					FStock stockBefore = new FStock();
					stockBefore = listStockBefore.get(0);
					saldoAwal = stockBefore.getSaldoakhir(); 
				}
				
				newStock = new FStock();
				newStock.setFproductBean(item.getFproductBean());
				newStock.setFwarehouseBean(fWarehouse);
				newStock.setStockdate(trdate);
				
				newStock.setSaldoawal(saldoAwal);
				newStock.setQtyhold(item.getQty());
				newStock.setQtyin(item.getQty());
				newStock.setQtyout(0);
				newStock.setQtyadjust(0);
				

			}
			
			Integer saldoAkhir = saldoAwal + newStock.getQtyin() - newStock.getQtyout() + newStock.getQtyadjust();
			newStock.setSaldoakhir(saldoAkhir);
			fStockJpaService.updateObject(newStock);
			
			//UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN
			updateStockAfterdate(fWarehouse, item.getFproductBean(), trdate, newStock);
			
		}
	}
	
	public void incomingStockRemove(FWarehouse fWarehouse, List<FtPurchased> listFt, Date trdate) {
		Iterator<FtPurchased> iter = listFt.iterator();
		while (iter.hasNext()) {
			FtPurchased item = new FtPurchased();
			item = iter.next();
			
			//1. CARI :: Stockdate, Warehouse/salesman, Product
			//2. KALAU BELUM ADA TAMBAHKAN
			//2.1 JIKA SUDAH ADA MAKA DISITU
			
			List<FStock> listStock =  fStockJpaService.findAll(fWarehouse, item.getFproductBean(), trdate);
			FStock newStock = new FStock();
			Integer saldoAwal =0;
			
			if (listStock.size() > 0) {
				newStock = new FStock();
				newStock = listStock.get(0);
				
				saldoAwal = newStock.getSaldoawal();
				
				Integer newQtyIn = newStock.getQtyin() - item.getQty();
				newStock.setQtyin(newQtyIn);
				
			} else {
				//JIKA BELUM PERNAH ADA
				//1. LIHAT TANGGAL SEBELUM::untuk ambil saldo akhir --> untuk saldo awal
				//2. JIKA KOSONG:: maka saldo awal adalah nol
				List<FStock> listStockBefore = fStockJpaService.findAllBefore(fWarehouse, item.getFproductBean(), trdate);
				if (listStockBefore.size() >0) {
					FStock stockBefore = new FStock();
					stockBefore = listStockBefore.get(0);
					saldoAwal = stockBefore.getSaldoakhir(); 
				}
				
				newStock = new FStock();
				newStock.setFproductBean(item.getFproductBean());
				newStock.setFwarehouseBean(fWarehouse);
				newStock.setStockdate(trdate);
				
				newStock.setSaldoawal(saldoAwal);
				newStock.setQtyhold(item.getQty());
				newStock.setQtyin(-item.getQty());
				newStock.setQtyout(0);
				newStock.setQtyadjust(0);

			}
			
			Integer saldoAkhir = saldoAwal + newStock.getQtyin() - newStock.getQtyout() + newStock.getQtyadjust();
			newStock.setSaldoakhir(saldoAkhir);
			fStockJpaService.updateObject(newStock);
			
			//UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN
			updateStockAfterdate(fWarehouse, item.getFproductBean(), trdate, newStock);
			
		}
		
	}

	public void incomingStockAddRetur(FWarehouse fWarehouse, List<FtPurchased> listFt, Date trdate){
		
		Iterator<FtPurchased> iter = listFt.iterator();
		while (iter.hasNext()) {
			FtPurchased item = new FtPurchased();
			item = iter.next();
			
			//1. CARI :: Stockdate, Warehouse/salesman, Product
			//2. KALAU BELUM ADA TAMBAHKAN
			//2.1 JIKA SUDAH ADA MAKA DISITU
			
			List<FStock> listStock =  fStockJpaService.findAll(fWarehouse, item.getFproductBean(), trdate);
			FStock newStock = new FStock();
			Integer saldoAwal =0;
			
			if (listStock.size() > 0) {
				newStock = new FStock();
				newStock = listStock.get(0);
				
				saldoAwal = newStock.getSaldoawal();
				
//				Integer newQtyIn = newStock.getQtyin() + item.getQty();
//				newStock.setQtyin(newQtyIn);
				Integer newQtyOut = newStock.getQtyout() + item.getQty();
				newStock.setQtyout(newQtyOut);
				
				
			} else {
				//JIKA BELUM PERNAH ADA
				//1. LIHAT TANGGAL SEBELUM::untuk ambil saldo akhir --> untuk saldo awal
				//2. JIKA KOSONG:: maka saldo awal adalah nol
				List<FStock> listStockBefore = fStockJpaService.findAllBefore(fWarehouse, item.getFproductBean(), trdate);
				if (listStockBefore.size() >0) {
					FStock stockBefore = new FStock();
					stockBefore = listStockBefore.get(0);
					saldoAwal = stockBefore.getSaldoakhir(); 
				}
				
				newStock = new FStock();
				newStock.setFproductBean(item.getFproductBean());
				newStock.setFwarehouseBean(fWarehouse);
				newStock.setStockdate(trdate);
				
				newStock.setSaldoawal(saldoAwal);
				newStock.setQtyhold(item.getQty());
//				newStock.setQtyin(item.getQty());
//				newStock.setQtyout(0);
				newStock.setQtyin(0);
				newStock.setQtyout(item.getQty());
				newStock.setQtyadjust(0);
				

			}
			
			Integer saldoAkhir = saldoAwal + newStock.getQtyin() - newStock.getQtyout() + newStock.getQtyadjust();
			newStock.setSaldoakhir(saldoAkhir);
			fStockJpaService.updateObject(newStock);
			
			//UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN
			updateStockAfterdate(fWarehouse, item.getFproductBean(), trdate, newStock);
			
		}
	}
	
	public void incomingStockRemoveRetur(FWarehouse fWarehouse, List<FtPurchased> listFt, Date trdate) {
		Iterator<FtPurchased> iter = listFt.iterator();
		while (iter.hasNext()) {
			FtPurchased item = new FtPurchased();
			item = iter.next();
			
			//1. CARI :: Stockdate, Warehouse/salesman, Product
			//2. KALAU BELUM ADA TAMBAHKAN
			//2.1 JIKA SUDAH ADA MAKA DISITU
			
			List<FStock> listStock =  fStockJpaService.findAll(fWarehouse, item.getFproductBean(), trdate);
			FStock newStock = new FStock();
			Integer saldoAwal =0;
			
			if (listStock.size() > 0) {
				newStock = new FStock();
				newStock = listStock.get(0);
				
				saldoAwal = newStock.getSaldoawal();
				
//				Integer newQtyIn = newStock.getQtyin() - item.getQty();
//				newStock.setQtyin(newQtyIn);
				Integer newQtyOut = newStock.getQtyout() - item.getQty();
				newStock.setQtyout(newQtyOut);
				
			} else {
				//JIKA BELUM PERNAH ADA
				//1. LIHAT TANGGAL SEBELUM::untuk ambil saldo akhir --> untuk saldo awal
				//2. JIKA KOSONG:: maka saldo awal adalah nol
				List<FStock> listStockBefore = fStockJpaService.findAllBefore(fWarehouse, item.getFproductBean(), trdate);
				if (listStockBefore.size() >0) {
					FStock stockBefore = new FStock();
					stockBefore = listStockBefore.get(0);
					saldoAwal = stockBefore.getSaldoakhir(); 
				}
				
				newStock = new FStock();
				newStock.setFproductBean(item.getFproductBean());
				newStock.setFwarehouseBean(fWarehouse);
				newStock.setStockdate(trdate);
				
				newStock.setSaldoawal(saldoAwal);
				newStock.setQtyhold(item.getQty());
//				newStock.setQtyin(-item.getQty());
//				newStock.setQtyout(0);
				newStock.setQtyin(0);
				newStock.setQtyout(-item.getQty());
				newStock.setQtyadjust(0);

			}
			
			Integer saldoAkhir = saldoAwal + newStock.getQtyin() - newStock.getQtyout() + newStock.getQtyadjust();
			newStock.setSaldoakhir(saldoAkhir);
			fStockJpaService.updateObject(newStock);
			
			//UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN
			updateStockAfterdate(fWarehouse, item.getFproductBean(), trdate, newStock);
			
		}		
	}
	//**SALES ORDER AND RETUR**
	public void soStockAdd(FWarehouse fWarehouse, List<FtSalesd> listFt, Date trdate){
		
		Iterator<FtSalesd> iter = listFt.iterator();
		while (iter.hasNext()) {
			FtSalesd item = new FtSalesd();
			item = iter.next();
			
			//1. CARI :: Stockdate, Warehouse/salesman, Product
			//2. KALAU BELUM ADA TAMBAHKAN
			//2.1 JIKA SUDAH ADA MAKA DISITU
			
			List<FStock> listStock =  fStockJpaService.findAll(fWarehouse, item.getFproductBean(), trdate);
			FStock newStock = new FStock();
			Integer saldoAwal =0;
			
			if (listStock.size() > 0) {
				newStock = new FStock();
				newStock = listStock.get(0);
				
				saldoAwal = newStock.getSaldoawal();
				
//				Integer newQtyIn = newStock.getQtyin() + item.getQty();
//				newStock.setQtyin(newQtyIn);
				Integer newQtyOut = newStock.getQtyout() + item.getQty();
				newStock.setQtyout(newQtyOut);
				
				
			} else {
				//JIKA BELUM PERNAH ADA
				//1. LIHAT TANGGAL SEBELUM::untuk ambil saldo akhir --> untuk saldo awal
				//2. JIKA KOSONG:: maka saldo awal adalah nol
				List<FStock> listStockBefore = fStockJpaService.findAllBefore(fWarehouse, item.getFproductBean(), trdate);
				if (listStockBefore.size() >0) {
					FStock stockBefore = new FStock();
					stockBefore = listStockBefore.get(0);
					saldoAwal = stockBefore.getSaldoakhir(); 
				}
				
				newStock = new FStock();
				newStock.setFproductBean(item.getFproductBean());
				newStock.setFwarehouseBean(fWarehouse);
				newStock.setStockdate(trdate);
				
				newStock.setSaldoawal(saldoAwal);
				newStock.setQtyhold(item.getQty());
//				newStock.setQtyin(item.getQty());
//				newStock.setQtyout(0);
				newStock.setQtyin(0);
				newStock.setQtyout(item.getQty());
				newStock.setQtyadjust(0);
				
			}
			
			Integer saldoAkhir = saldoAwal + newStock.getQtyin() - newStock.getQtyout() + newStock.getQtyadjust();
			newStock.setSaldoakhir(saldoAkhir);
			fStockJpaService.updateObject(newStock);
			
			//UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN
			updateStockAfterdate(fWarehouse, item.getFproductBean(), trdate, newStock);
			
		}
	}
	
	public void soStockRemove(FWarehouse fWarehouse, List<FtSalesd> listFt, Date trdate) {
		Iterator<FtSalesd> iter = listFt.iterator();
		while (iter.hasNext()) {
			FtSalesd item = new FtSalesd();
			item = iter.next();
			
			//1. CARI :: Stockdate, Warehouse/salesman, Product
			//2. KALAU BELUM ADA TAMBAHKAN
			//2.1 JIKA SUDAH ADA MAKA DISITU
			
			List<FStock> listStock =  fStockJpaService.findAll(fWarehouse, item.getFproductBean(), trdate);
			FStock newStock = new FStock();
			Integer saldoAwal =0;
			
			if (listStock.size() > 0) {
				newStock = new FStock();
				newStock = listStock.get(0);
				
				saldoAwal = newStock.getSaldoawal();
				
//				Integer newQtyIn = newStock.getQtyin() - item.getQty();
//				newStock.setQtyin(newQtyIn);
				Integer newQtyOut = newStock.getQtyout() - item.getQty();
				newStock.setQtyout(newQtyOut);
				
			} else {
				//JIKA BELUM PERNAH ADA
				//1. LIHAT TANGGAL SEBELUM::untuk ambil saldo akhir --> untuk saldo awal
				//2. JIKA KOSONG:: maka saldo awal adalah nol
				List<FStock> listStockBefore = fStockJpaService.findAllBefore(fWarehouse, item.getFproductBean(), trdate);
				if (listStockBefore.size() >0) {
					FStock stockBefore = new FStock();
					stockBefore = listStockBefore.get(0);
					saldoAwal = stockBefore.getSaldoakhir(); 
				}
				
				newStock = new FStock();
				newStock.setFproductBean(item.getFproductBean());
				newStock.setFwarehouseBean(fWarehouse);
				newStock.setStockdate(trdate);
				
				newStock.setSaldoawal(saldoAwal);
				newStock.setQtyhold(item.getQty());
//				newStock.setQtyin(-item.getQty());
//				newStock.setQtyout(0);
				newStock.setQtyin(0);
				newStock.setQtyout(-item.getQty());
				newStock.setQtyadjust(0);

			}
			
			Integer saldoAkhir = saldoAwal + newStock.getQtyin() - newStock.getQtyout() + newStock.getQtyadjust();
			newStock.setSaldoakhir(saldoAkhir);
			fStockJpaService.updateObject(newStock);
			
			//UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN
			updateStockAfterdate(fWarehouse, item.getFproductBean(), trdate, newStock);
		}		
	}
	
	public void soStockAddRetur(FWarehouse fWarehouse, List<FtSalesd> listFt, Date trdate){
		
		Iterator<FtSalesd> iter = listFt.iterator();
		while (iter.hasNext()) {
			FtSalesd item = new FtSalesd();
			item = iter.next();
			
			//1. CARI :: Stockdate, Warehouse/salesman, Product
			//2. KALAU BELUM ADA TAMBAHKAN
			//2.1 JIKA SUDAH ADA MAKA DISITU
			
			List<FStock> listStock =  fStockJpaService.findAll(fWarehouse, item.getFproductBean(), trdate);
			FStock newStock = new FStock();
			Integer saldoAwal =0;
			
			if (listStock.size() > 0) {
				newStock = new FStock();
				newStock = listStock.get(0);
				
				saldoAwal = newStock.getSaldoawal();
				
				Integer newQtyIn = newStock.getQtyin() + item.getQty();
				newStock.setQtyin(newQtyIn);
				
				
			} else {
				//JIKA BELUM PERNAH ADA
				//1. LIHAT TANGGAL SEBELUM::untuk ambil saldo akhir --> untuk saldo awal
				//2. JIKA KOSONG:: maka saldo awal adalah nol
				List<FStock> listStockBefore = fStockJpaService.findAllBefore(fWarehouse, item.getFproductBean(), trdate);
				if (listStockBefore.size() >0) {
					FStock stockBefore = new FStock();
					stockBefore = listStockBefore.get(0);
					saldoAwal = stockBefore.getSaldoakhir(); 
				}
				
				newStock = new FStock();
				newStock.setFproductBean(item.getFproductBean());
				newStock.setFwarehouseBean(fWarehouse);
				newStock.setStockdate(trdate);
				
				newStock.setSaldoawal(saldoAwal);
				newStock.setQtyhold(item.getQty());
				newStock.setQtyin(item.getQty());
				newStock.setQtyout(0);
				newStock.setQtyadjust(0);

			}
			
			Integer saldoAkhir = saldoAwal + newStock.getQtyin() - newStock.getQtyout() + newStock.getQtyadjust();
			newStock.setSaldoakhir(saldoAkhir);
			fStockJpaService.updateObject(newStock);
			
			//UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN
			updateStockAfterdate(fWarehouse, item.getFproductBean(), trdate, newStock);
			
		}
	}
	
	public void soStockRemoveRetur(FWarehouse fWarehouse, List<FtSalesd> listFt, Date trdate) {
		Iterator<FtSalesd> iter = listFt.iterator();
		while (iter.hasNext()) {
			FtSalesd item = new FtSalesd();
			item = iter.next();
			
			//1. CARI :: Stockdate, Warehouse/salesman, Product
			//2. KALAU BELUM ADA TAMBAHKAN
			//2.1 JIKA SUDAH ADA MAKA DISITU
			
			List<FStock> listStock =  fStockJpaService.findAll(fWarehouse, item.getFproductBean(), trdate);
			FStock newStock = new FStock();
			Integer saldoAwal =0;
			
			if (listStock.size() > 0) {
				newStock = new FStock();
				newStock = listStock.get(0);
				
				saldoAwal = newStock.getSaldoawal();
				
				Integer newQtyIn = newStock.getQtyin() - item.getQty();
				newStock.setQtyin(newQtyIn);
				
			} else {
				//JIKA BELUM PERNAH ADA
				//1. LIHAT TANGGAL SEBELUM::untuk ambil saldo akhir --> untuk saldo awal
				//2. JIKA KOSONG:: maka saldo awal adalah nol
				List<FStock> listStockBefore = fStockJpaService.findAllBefore(fWarehouse, item.getFproductBean(), trdate);
				if (listStockBefore.size() >0) {
					FStock stockBefore = new FStock();
					stockBefore = listStockBefore.get(0);
					saldoAwal = stockBefore.getSaldoakhir(); 
				}
				
				newStock = new FStock();
				newStock.setFproductBean(item.getFproductBean());
				newStock.setFwarehouseBean(fWarehouse);
				newStock.setStockdate(trdate);
				
				newStock.setSaldoawal(saldoAwal);
				newStock.setQtyhold(item.getQty());
				newStock.setQtyin(-item.getQty());
				newStock.setQtyout(0);
				newStock.setQtyadjust(0);

			}
			
			Integer saldoAkhir = saldoAwal + newStock.getQtyin() - newStock.getQtyout() + newStock.getQtyadjust();
			newStock.setSaldoakhir(saldoAkhir);
			fStockJpaService.updateObject(newStock);
			
			//UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN
			updateStockAfterdate(fWarehouse, item.getFproductBean(), trdate, newStock);
			
		}
		
	}
	
	//**STOCK OPNAME
	public void stockOpnameRemove(FWarehouse fWarehouse, List<FtOpnamed> listFt, Date trdate) {
		Iterator<FtOpnamed> iter = listFt.iterator();
		while (iter.hasNext()) {
			FtOpnamed item = new FtOpnamed();
			item = iter.next();
			
			//1. CARI :: Stockdate, Warehouse/salesman, Product
			//2. KALAU BELUM ADA TAMBAHKAN
			//2.1 JIKA SUDAH ADA MAKA DISITU
			
			List<FStock> listStock =  fStockJpaService.findAll(fWarehouse, item.getFproductBean(), trdate);
			FStock newStock = new FStock();
			Integer saldoAwal =0;
			
			if (listStock.size() > 0) {
				newStock = new FStock();
				newStock = listStock.get(0);
				
				saldoAwal = newStock.getSaldoawal();
				
				Integer newQtyAdjust = newStock.getQtyadjust() - item.getQtyadjust();
				newStock.setQtyadjust(newQtyAdjust);
				
			} else {
				//JIKA BELUM PERNAH ADA
				//1. LIHAT TANGGAL SEBELUM::untuk ambil saldo akhir --> untuk saldo awal
				//2. JIKA KOSONG:: maka saldo awal adalah nol
				List<FStock> listStockBefore = fStockJpaService.findAllBefore(fWarehouse, item.getFproductBean(), trdate);
				if (listStockBefore.size() >0) {
					FStock stockBefore = new FStock();
					stockBefore = listStockBefore.get(0);
					saldoAwal = stockBefore.getSaldoakhir(); 
				}
				
				newStock = new FStock();
				newStock.setFproductBean(item.getFproductBean());
				newStock.setFwarehouseBean(fWarehouse);
				newStock.setStockdate(trdate);
				
				newStock.setSaldoawal(saldoAwal);
				newStock.setQtyhold(item.getQty());
				newStock.setQtyin(-item.getQty());
				newStock.setQtyout(0);
				newStock.setQtyadjust(0);

			}
			
			Integer saldoAkhir = saldoAwal + newStock.getQtyin() - newStock.getQtyout() + newStock.getQtyadjust();
			newStock.setSaldoakhir(saldoAkhir);
			fStockJpaService.updateObject(newStock);
			
			//UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN
			updateStockAfterdate(fWarehouse, item.getFproductBean(), trdate, newStock);
			
		}
		
	}
	
	//##END:: UPDATE STOCK##
	
	public void updateStockAfterdate(FWarehouse fWarehouse, FProduct fProduct, Date trdate, FStock newStock){
		List<FStock> listStockAfter = fStockJpaService.findAllAfter(fWarehouse, fProduct, trdate);
		Iterator<FStock> iter = listStockAfter.iterator();
		while (iter.hasNext()) {
			FStock item = new FStock();
			item = iter.next();
			item.setSaldoawal(newStock.getSaldoakhir());
			Integer saldoAkhir = item.getSaldoawal() + item.getQtyin() - item.getQtyout() + item.getQtyadjust();
			item.setSaldoakhir(saldoAkhir);
			
			fStockJpaService.updateObject(item);
			
			newStock = new FStock();
			newStock = item;
		}
		
	}
	
	public void transferSaldoStokAwalFromBefore(Date stockdate){
		List<FWarehouse> listFWarehouse = fWarehouseJpaService.findAll();
		for (FWarehouse itemWarehouse: listFWarehouse) {
			List<FProduct> listFproduct = fProductJpaService.findAll();
			for (FProduct itemFproduct: listFproduct) {		
				updateSaldoStokStokAwalFromBefore(itemWarehouse, itemFproduct, stockdate);
			}
		}				
	}
	
	public void updateSaldoStokStokAwalFromBefore(FWarehouse fWarehouse, FProduct fProduct, Date stockDate) {
		//CARI
		List<FStock> listStock =  fStockJpaService.findAll(fWarehouse, fProduct, stockDate);
		FStock newStock = new FStock();
		Integer saldoAwal =0;
		
		//JIKA TANGGAL TERSEBUT ADA :::BIARIN
		if (listStock.size() > 0) {
			newStock = new FStock();
			newStock = listStock.get(0);
			
			saldoAwal = newStock.getSaldoawal();
			
//			Integer newQtyIn = newStock.getQtyin() - item.getQty();
			Integer newQtyIn = newStock.getQtyin();
			newStock.setQtyin(newQtyIn);
			
		//JIKA TANGGAL TERSEBUT TIDAK ADA :: MAKA KITA HARUS TANSFER DARI TANGGAL SEBELUMNY
		} else {
			//JIKA BELUM PERNAH ADA
			//1. LIHAT TANGGAL SEBELUM::untuk ambil saldo akhir --> untuk saldo awal
			//2. JIKA KOSONG:: maka saldo awal adalah nol
			List<FStock> listStockBefore = fStockJpaService.findAllBefore(fWarehouse, fProduct, stockDate);
			if (listStockBefore.size() >0) {
				FStock stockBefore = new FStock();
				stockBefore = listStockBefore.get(0);
				saldoAwal = stockBefore.getSaldoakhir(); 
			}
			
			newStock = new FStock();
			newStock.setFproductBean(fProduct);
			newStock.setFwarehouseBean(fWarehouse);
			newStock.setStockdate(stockDate);
			
			newStock.setSaldoawal(saldoAwal);
			newStock.setQtyhold(0);
			newStock.setQtyin(0);
			newStock.setQtyout(0);
			newStock.setQtyadjust(0);

		}
		
		Integer saldoAkhir = saldoAwal + newStock.getQtyin() - newStock.getQtyout() + newStock.getQtyadjust();
		newStock.setSaldoakhir(saldoAkhir);
		//CARI PENYEBAB KENAPA KOK SERING TIDAK AKTIF
		try{
			fStockJpaService.updateObject(newStock);
		} catch(Exception ex){}
		
		//UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN
		updateStockAfterdate(fWarehouse, fProduct, stockDate, newStock);
		
	}
	
	//##FIX OR RECALCULATE SALDO STOCK##
	public void recalculateSaldoStock(FWarehouse fWarehouse, Date dateFrom, Date dateTo){
		List<FWarehouse> listFWarehouse  = new ArrayList<FWarehouse>();
		
		if (fWarehouse==null){
			listFWarehouse = fWarehouseJpaService.findAll();		
		} else {
			listFWarehouse.add(fWarehouse);
		}
		
		for (FWarehouse itemWarehouse: listFWarehouse) {
			List<FProduct> listFproduct = fProductJpaService.findAll();
			//untuk yang proses manual saja
			if (fWarehouse==null){
//				Notification.show("Recalculate Stock of  " + itemWarehouse.getDescription(), Notification.TYPE_TRAY_NOTIFICATION);
			}
			
			for (FProduct itemFproduct: listFproduct) {				
				recalculateSaldoStockProcess(itemWarehouse, itemFproduct, dateFrom, dateTo);
			}
		}
		
	}
	
	public void recalculateSaldoStockProcess(FWarehouse itemWareHouse, FProduct itemFproduct, Date dateFrom, Date dateTo){
		/*
		 * MENGKALULASI SALDO STOK PADA GUDANG->PRODUCT --> Mulai tanggal sampai tanggal tertentu
		 */
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateFrom);
		while (cal.getTime().getTime()<= dateTo.getTime()) {
			FStock newFStock = new FStock();
			try{
				try{
					newFStock = fStockJpaService.findAll(itemWareHouse, itemFproduct, cal.getTime()).get(0);			
				} catch(Exception ex){
					//STOK AWAL
					newFStock.setFwarehouseBean(itemWareHouse);
					newFStock.setFproductBean(itemFproduct);
					newFStock.setStockdate(cal.getTime());
					newFStock.setSaldoawal(0);
					newFStock.setQtyin(0);
					newFStock.setQtyout(0);
					newFStock.setQtyhold(0);
					newFStock.setQtyadjust(0);
					newFStock.setSaldoakhir(0);
				}
				//**SALDO AWAL
				Integer saldoStockAwal = newFStock.getSaldoawal();
				
				//1. HITUNG STOCK IN :: FTSALESD(F,R), FTPURCHASED(F,R), FTRANSFERD
				Integer qtyIn = 0;
				qtyIn = getStockIn(itemWareHouse, itemFproduct, cal.getTime());				
				newFStock.setQtyin(qtyIn);				
				
				//2. HITUNG STOK OUT :: FTSALESD(F,R), FTPURCHASED(F,R), FTRANSFERD				
				Integer qtyOut = 0;
				qtyOut = getStockOut(itemWareHouse, itemFproduct, cal.getTime());
				newFStock.setQtyout(qtyOut);
				
				//3. HITUNG STOCK ADJUST PEYESUAIAN DAN STOCK OPNAME
				Integer qtyAdjustPenyesuaian =0;
				Integer qtyAdjustOpname =0;
				qtyAdjustPenyesuaian = getStockPenyesuaian(itemWareHouse, itemFproduct, cal.getTime());
				qtyAdjustOpname = getStockPenyesuaianStockopname(itemWareHouse, itemFproduct, cal.getTime());
				newFStock.setQtyadjust(qtyAdjustPenyesuaian + qtyAdjustOpname);
				
				//**SALDO AKHIR
				Integer saldoStockAkhir = saldoStockAwal + qtyIn - qtyOut + qtyAdjustPenyesuaian + qtyAdjustOpname;
				newFStock.setSaldoakhir(saldoStockAkhir);
				
				//##UPDATE KE DATABASE
				fStockJpaService.updateObject(newFStock);
				
				//##UPDATE STOCK SETELAHNYA SETELAH DIMASUKKAN>>KESANA TERUSSSS
				updateStockAfterdate(itemWareHouse, itemFproduct, cal.getTime(), newFStock);
				
			
			} catch(Exception ex){
				ex.printStackTrace();
			}
			//MAJUKAN NEXT DAY			
			cal.add(Calendar.DATE, 1);
		}
		
	}
	
	//##GET SALDO STOCK##
	public int getSaldoStock(FWarehouse fWarehouse, FProduct fProduct, Date tanggalStock){
		int jumlahSaldoStock = 0;
		
		List<FStock> listFStock = fStockJpaService.findAll(fWarehouse, fProduct, tanggalStock);		
		if (listFStock.size() >0 ) {
			jumlahSaldoStock = listFStock.get(0).getSaldoakhir();
		}
		return jumlahSaldoStock;
	}
	
	//1. ##GET SALDO STOCK IN FROM TRANSAKTION
	public int getStockIn(FWarehouse fWarehouse, FProduct fProduct, Date tanggalStock){
		Integer stockIn = 0;
		//1. HITUNG PURCHASE
		try{
			stockIn += getStockInPurchase(fWarehouse, fProduct, tanggalStock);
		} catch(Exception ex){
			ex.printStackTrace();			
		}
		//2. HITUNG SALES
		try{
			stockIn += getStockInSalesReturn(fWarehouse, fProduct, tanggalStock);				
		} catch(Exception ex){
			ex.printStackTrace();			
		}
		//3. TRANSFER STOCK
		//TRANSFER MASUK KE GUDANG TUJUAN (bertambah)
		try{
			stockIn += getStockInStocktransferIn(fWarehouse, fProduct, tanggalStock);
		} catch(Exception ex){
			ex.printStackTrace();			
		}
		//###STOK OPNAME DAN PENYESUAIAN ADA PADA KOLOM TERSENDIRI##
		//4. HITUNG STOCK OPNAME
		//5. HITUNG PENYESUAIAN
		try{
			stockIn += getStockPenyesuaian(fWarehouse, fProduct, tanggalStock);
		} catch(Exception ex){
			ex.printStackTrace();			
		}
		return stockIn;
	}
	public int getStockInPurchase(FWarehouse fWarehouse, FProduct fProduct, Date tanggalStock){
		Integer stockIn = 0;
		List<FtPurchased> listFtPurchased = ftPurchasedJpaService.findAll(fWarehouse, fProduct, tanggalStock, "F");
		Iterator<FtPurchased> iterPurchased = listFtPurchased.iterator();
		while (iterPurchased.hasNext()) {
			FtPurchased item = new FtPurchased();
			item = iterPurchased.next();
			stockIn += item.getQty();
		}		
		return stockIn;
	}
	public int getStockInSalesReturn(FWarehouse fWarehouse, FProduct fProduct, Date tanggalStock){
		Integer stockIn = 0;
		List<FtSalesd> listFtSalesd = ftSalesdJpaService.findAll(fWarehouse, fProduct, tanggalStock, "R");
		Iterator<FtSalesd> iterSalesd = listFtSalesd.iterator();
		while (iterSalesd.hasNext()) {
			FtSalesd item = new FtSalesd();
			item = iterSalesd.next();
			stockIn += item.getQty();
		}		
		return stockIn;
	}
	public int getStockInStocktransferIn(FWarehouse fWarehouse, FProduct fProduct, Date tanggalStock){
		Integer stockIn = 0;
		List<FtStocktransferd> listFtStockTransferd = ftStocktransferdJpaService.findAllTo(fWarehouse, fProduct, tanggalStock);
		Iterator<FtStocktransferd> iterStockTransferd = listFtStockTransferd.iterator();
		while (iterStockTransferd.hasNext()) {
			FtStocktransferd item = new FtStocktransferd();
			item = iterStockTransferd.next();
			try{
				stockIn += item.getQty();
			} catch(Exception ex){
				ex.printStackTrace();				
			}
		}
		return stockIn;
	}

	//2. ##GET SALDO STOCK OUT FROM TRANSAKTION	
	public int getStockOut(FWarehouse fWarehouse, FProduct fProduct, Date tanggalStock){
		Integer stockOut = 0;
		//1. HITUNG PURCHASE
		try{
			stockOut += getStockOutPurchaseReturn(fWarehouse, fProduct, tanggalStock);
		} catch(Exception ex){
			ex.printStackTrace();			
		}
		//2. HITUNG SALES
		try{
			stockOut += getStockOutSalesOrder(fWarehouse, fProduct, tanggalStock);	
		} catch(Exception ex){
			ex.printStackTrace();			
		}
//		//3. HITUNG TRANSFER STOCK
		//TRANSFER KELUAR DARI GUDANG ASAL (berkurang)
		try{
			stockOut += getStockOutStocktransferOut(fWarehouse, fProduct, tanggalStock);				
		} catch(Exception ex){
			ex.printStackTrace();
		}
		//###STOK OPNAME DAN PENYESUAIAN ADA PADA KOLOM TERSENDIRI##
		//4. HITUNG STOCK OPNAME
		//5. HITUNG PENYESUAIAN
		
		return stockOut;
	}
	public int getStockOutPurchaseReturn(FWarehouse fWarehouse, FProduct fProduct, Date tanggalStock){
		Integer stockOut = 0;
		List<FtPurchased> listFtPurchased = ftPurchasedJpaService.findAll(fWarehouse, fProduct, tanggalStock, "R");
		Iterator<FtPurchased> iterPurchased = listFtPurchased.iterator();
		while (iterPurchased.hasNext()) {
			FtPurchased item = new FtPurchased();
			item = iterPurchased.next();
			try{
				stockOut += item.getQty();
			} catch(Exception ex){
				ex.printStackTrace();				
			}
		}		
		return stockOut;
	}
	public int getStockOutSalesOrder(FWarehouse fWarehouse, FProduct fProduct, Date tanggalStock) {
		Integer stockOut = 0;
		List<FtSalesd> listFtSalesd = ftSalesdJpaService.findAll(fWarehouse, fProduct, tanggalStock, "F");
		Iterator<FtSalesd> iterSalesd = listFtSalesd.iterator();
		while (iterSalesd.hasNext()) {
			FtSalesd item = new FtSalesd();
			item = iterSalesd.next();
			try{
				stockOut += item.getQty();
			} catch(Exception ex){
				ex.printStackTrace();				
			}
		}
		return stockOut;		
	}
	public int getStockOutStocktransferOut(FWarehouse fWarehouse, FProduct fProduct, Date tanggalStock){
		Integer stockOut = 0;
		List<FtStocktransferd> listFtStocktransferd = ftStocktransferdJpaService.findAllFrom(fWarehouse, fProduct, tanggalStock);
		Iterator<FtStocktransferd> iterStocktransferd = listFtStocktransferd.iterator();
		while (iterStocktransferd.hasNext()) {
			FtStocktransferd item = new FtStocktransferd();
			item = iterStocktransferd.next();
			try{
				stockOut += item.getQty();
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return stockOut;
	}

	//3. ##GET SALDO STOCK ADJUST FROM TRANSAKTION	
	public int getStockPenyesuaian(FWarehouse fWarehouse, FProduct fProduct, Date tanggalStock){
		Integer stockPcs = 0;
		List<FtAdjustd> listFtAdjustd = ftAdjustdJpaService.findAll(fWarehouse, fProduct, tanggalStock);
		Iterator<FtAdjustd> iterFtAdjustd = listFtAdjustd.iterator();
		while (iterFtAdjustd.hasNext()) {
			FtAdjustd item = new FtAdjustd();
			item = iterFtAdjustd.next();
			try{
				stockPcs += item.getQty();
			} catch(Exception ex){
				ex.printStackTrace();				
			}
		}
		return stockPcs;
		
	}
	public int getStockPenyesuaianStockopname(FWarehouse fWarehouse, FProduct fProduct, Date tanggalStock){
		Integer stockPcs = 0;
		List<FtOpnamed> listFtOpnamed = ftOpnamedJpaService.findAll(fWarehouse, fProduct, tanggalStock);
		Iterator<FtOpnamed> iterFtOpnamed = listFtOpnamed.iterator();
		while (iterFtOpnamed.hasNext()) {
			FtOpnamed item = new FtOpnamed();
			item = iterFtOpnamed.next();
			try{
				stockPcs += item.getQtyadjust();
			} catch(Exception ex){
//				ex.printStackTrace();				
			}
		}
		return stockPcs;
		
	}
	
	public boolean isStockTransactionExist(Date stockDate) {
		boolean isExist = true;
		if (fStockJpaService.count(stockDate)==0){
			isExist=false;			
		}
		return isExist;
	}
	
	//KONVERSI PCS -> BES, SED, KEC
	public int getBesFromPcs(Integer pcs, FProduct fProduct){
		Integer valueBes =0;
		try{
			valueBes = pcs / fProduct.getConvfact1();
		} catch(Exception ex){}
		
		return valueBes;
	}
	public int getSedFromPcs(Integer pcs, FProduct fProduct){
		Integer valueSed = 0;
		try{
			Integer sisaQtyBes = pcs % fProduct.getConvfact1();		
			valueSed = sisaQtyBes / fProduct.getConvfact2();
		} catch(Exception ex){}
		
		return valueSed;
	}
	public int getKecFromPcs(Integer pcs, FProduct fProduct){
		Integer valueKec =0;
		try{
			Integer sisaQtyBes = pcs % fProduct.getConvfact1();
			Integer sisaQtySed = sisaQtyBes % fProduct.getConvfact2();
			valueKec = sisaQtySed;
		} catch(Exception ex){}
		return valueKec;
	}
	
	public double getPPriceBeforePpnFromFProduct(Integer pcs, FProduct fProduct){
		Double hargaBeliPcs = fProduct.getPprice() / fProduct.getConvfact1();
		return pcs * hargaBeliPcs;
	}
	public double getSPriceBeforePpnFromFProduct(Integer pcs, FProduct fProduct){
		Double hargaJualPcs = fProduct.getSprice() / fProduct.getConvfact1();
		return pcs * hargaJualPcs;
		
	}
	
	public static void main(String [] args){
		ProductAndStockHelper f= new ProductAndStockHelper();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date tglStok = null;
		try{
			tglStok = sdf.parse("15/04/2015");
		} catch(Exception ex){}
		
		
		f.transferSaldoStokAwalFromBefore(tglStok);
		
		
	}
	
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public FStockJpaService getfStockJpaService() {
		return fStockJpaService;
	}
	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}
	public FWarehouseJpaService getfWarehouseJpaService() {
		return fWarehouseJpaService;
	}
	public FSalesmanJpaService getfSalesmanJpaService() {
		return fSalesmanJpaService;
	}
	public FtSaleshJpaService getFtSaleshJpaService() {
		return ftSaleshJpaService;
	}
	public FtSalesdJpaService getFtSalesdJpaService() {
		return ftSalesdJpaService;
	}
	public FtPurchasehJpaService getFtPurchasehJpaService() {
		return ftPurchasehJpaService;
	}
	public FtPurchasedJpaService getFtPurchasedJpaService() {
		return ftPurchasedJpaService;
	}
	public FtStocktransferhJpaService getFtStocktransferhJpaService() {
		return ftStocktransferhJpaService;
	}
	public FtStocktransferdJpaService getFtStocktransferdJpaService() {
		return ftStocktransferdJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setfStockJpaService(FStockJpaService fStockJpaService) {
		this.fStockJpaService = fStockJpaService;
	}
	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}
	public void setfWarehouseJpaService(FWarehouseJpaService fWarehouseJpaService) {
		this.fWarehouseJpaService = fWarehouseJpaService;
	}
	public void setfSalesmanJpaService(FSalesmanJpaService fSalesmanJpaService) {
		this.fSalesmanJpaService = fSalesmanJpaService;
	}
	public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
		this.ftSaleshJpaService = ftSaleshJpaService;
	}
	public void setFtSalesdJpaService(FtSalesdJpaService ftSalesdJpaService) {
		this.ftSalesdJpaService = ftSalesdJpaService;
	}
	public void setFtPurchasehJpaService(FtPurchasehJpaService ftPurchasehJpaService) {
		this.ftPurchasehJpaService = ftPurchasehJpaService;
	}
	public void setFtPurchasedJpaService(FtPurchasedJpaService ftPurchasedJpaService) {
		this.ftPurchasedJpaService = ftPurchasedJpaService;
	}
	public void setFtStocktransferhJpaService(
			FtStocktransferhJpaService ftStocktransferhJpaService) {
		this.ftStocktransferhJpaService = ftStocktransferhJpaService;
	}
	public void setFtStocktransferdJpaService(
			FtStocktransferdJpaService ftStocktransferdJpaService) {
		this.ftStocktransferdJpaService = ftStocktransferdJpaService;
	}
	public FtAdjusthJpaService getFtAdjusthJpaService() {
		return ftAdjusthJpaService;
	}
	public FtAdjustdJpaService getFtAdjustdJpaService() {
		return ftAdjustdJpaService;
	}
	public FtOpnamehJpaService getFtOpnamehJpaService() {
		return ftOpnamehJpaService;
	}
	public FtOpnamedJpaService getFtOpnamedJpaService() {
		return ftOpnamedJpaService;
	}
	public void setFtAdjusthJpaService(FtAdjusthJpaService ftAdjusthJpaService) {
		this.ftAdjusthJpaService = ftAdjusthJpaService;
	}
	public void setFtAdjustdJpaService(FtAdjustdJpaService ftAdjustdJpaService) {
		this.ftAdjustdJpaService = ftAdjustdJpaService;
	}
	public void setFtOpnamehJpaService(FtOpnamehJpaService ftOpnamehJpaService) {
		this.ftOpnamehJpaService = ftOpnamehJpaService;
	}
	public void setFtOpnamedJpaService(FtOpnamedJpaService ftOpnamedJpaService) {
		this.ftOpnamedJpaService = ftOpnamedJpaService;
	}
	
	
	
}
