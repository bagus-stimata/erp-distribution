package org.erp.distribution.util;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.model.modelenum.EnumUserOtorize;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by yhawin on 21/07/15.
 */
public class UserOtorizeHelper {
    DashboardUI mainUI;
    public UserOtorizeHelper(DashboardUI mainUI){
        this.mainUI = mainUI;
    }
    public void setUserOtorize(){
    	
    	setOpenAndVisibleAllOtorize();
    	
        mainUI.menuHome.setEnabled(true);

        if (mainUI.getUserActive().getUserOtorizeType().equals(EnumUserOtorize.ADMINISTRATOR.getStrCode())) {
        	setOpenAndVisibleAllOtorize();

        } else if (mainUI.getUserActive().getUserOtorizeType().equals(EnumUserOtorize.USER11.getStrCode())) {
//			mainUI.menuSetupMaster.setEnabled(true);
            mainUI.menuSetupMasterSysvar.setEnabled(false);
            mainUI.menuSetupMasterUser.setEnabled(false);
            mainUI.menuSetupMasterWarehouse.setEnabled(false);
            mainUI.menuSetupMasterDiskon.setEnabled(false);

        } else if (mainUI.getUserActive().getUserOtorizeType().equals(EnumUserOtorize.USER12.getStrCode())) {
            mainUI.getMenuSetupMaster().setVisible(false);
            mainUI.menuSetupMasterSysvar.setEnabled(true);
            mainUI.menuSetupMasterUser.setEnabled(true);
            mainUI.menuSetupMasterVendor.setEnabled(true);
            mainUI.menuSetupMasterSalesman.setEnabled(true);
            mainUI.menuSetupMasterCustomer.setEnabled(true);
            mainUI.menuSetupMasterCustomer1.setEnabled(true);
            mainUI.menuSetupMasterCustomerSeparator1.setEnabled(true);
            mainUI.menuSetupMasterCustomerGroup.setEnabled(true);
            mainUI.menuSetupMasterCustomerSubGroup.setEnabled(true);
            mainUI.menuSetupMasterCustomerArea.setEnabled(true);
            mainUI.menuSetupMasterCustomerSubArea.setEnabled(true);
            mainUI.menuSetupMasterProduct.setEnabled(true);
            mainUI.menuSetupMasterProduct1.setEnabled(true);
            mainUI.menuSetupMasterProductSeparator1.setEnabled(true);
            mainUI.menuSetupMasterProductGroup.setEnabled(true);
            mainUI.menuSetupMasterProductGroupDept.setEnabled(true);
            mainUI.menuSetupMasterProductGroupDivisi.setEnabled(true);
            mainUI.menuSetupMasterWarehouse.setEnabled(true);
            mainUI.menuSetupMasterDiskon.setEnabled(true);
            mainUI.menuSetupMasterDiskonParameterDiskon.setEnabled(true);

            mainUI.menuWarehouseStock.setEnabled(true);
            mainUI.menuWarehouseStockTransfer.setVisible(false);
            mainUI.menuWarehouseStockOpname1.setVisible(false);
            mainUI.menuWarehouseLapSaldoStock.setEnabled(true);
            mainUI.menuWarehouseLapPriceList.setEnabled(true);
            mainUI.menuWarehouseLapMutasiStok.setEnabled(true);

            mainUI.menuPurchaseOrder.setEnabled(true);
            mainUI.menuPurchaseOrderIncomingStock.setVisible(true);
            mainUI.menuPurchaseOrderRepIncomingStock.setEnabled(true);
            mainUI.menuPurchaseOrderSeparator1.setEnabled(true);
            mainUI.menuPurchaseOrderMrv.setVisible(true);
            mainUI.menuPurchaseOrderRepMrv.setEnabled(true);

            mainUI.menuSalesOrder.setEnabled(true);
            mainUI.menuSalesOrder1.setVisible(false);
            mainUI.menuSalesOrderPencetakan.setVisible(false);
            mainUI.menuSalesOrderPackingList.setEnabled(true);
            mainUI.menuSalesOrderPackingList1.setEnabled(false);
            mainUI.menuSalesOrderPackingListRekapPerhari.setEnabled(true);
            mainUI.menuSalesOrderLaporan1.setEnabled(true);
            mainUI.menuSalesOrderRep.setEnabled(true);
            mainUI.menuSalesPerBarang.setEnabled(true);
            mainUI.menuSalesPrestasiKerja.setEnabled(true);
            mainUI.menuSalesOrderSeparator1.setEnabled(true);

            mainUI.menuSalesOrderSalesReturn.setVisible(false);
            mainUI.menuSalesOrderRepSalesReturn.setEnabled(true);
            mainUI.menuSalesOrderArPayment.setVisible(false);
            mainUI.menuSalesOrderArPaymentRep.setEnabled(true);
            mainUI.menuSalesOrderArpaymentRepSaldoPiutang.setEnabled(true);
            mainUI.menuSalesOrderArpaymentRepPembayaranPiutang.setEnabled(true);


            mainUI.menuProses.setVisible(false);
            mainUI.menuProsesAkhirhari.setEnabled(true);
            mainUI.menuProsesAkhirtahun.setEnabled(true);
            mainUI.menuUtilitas.setVisible(false);
            mainUI.menuUtilitasTheme.setEnabled(true);
            mainUI.menuUtilitasThemeGantiTheme.setEnabled(true);
            mainUI.menuUtilitasPerbaikan.setEnabled(true);
            mainUI.menuUtilitasPerbaikanSaldoStock.setEnabled(true);

            mainUI.menuHelp.setVisible(false);
            mainUI.menuAbout.setVisible(false);

        } else if (mainUI.getUserActive().getUserOtorizeType().equals(EnumUserOtorize.TEKNISIHP.getStrCode())) {

            mainUI.menuSetupMaster.setVisible(false);
            
            mainUI.menuWarehouseStock.setVisible(false);
            
            mainUI.menuPurchaseOrder.setVisible(false);
            
            mainUI.menuSalesOrder.setVisible(false);
            

	        mainUI.menuServiceHp.setEnabled(true);
	        mainUI.menuServiceHpPenerimaanService.setEnabled(false);
	        mainUI.menuServiceHpLapServiceRekap.setEnabled(false);
	        mainUI.menuServiceHpServiceTeknisi.setEnabled(true);
	        mainUI.menuServiceHpStockProductHp.setEnabled(true);
	        
            mainUI.menuGL.setVisible(false);
            
            mainUI.menuProses.setVisible(false);
            
            mainUI.menuUtilitas.setEnabled(false);

            mainUI.menuHelp.setEnabled(true);
            mainUI.menuAbout.setEnabled(true);
        	
        } else if (mainUI.getUserActive().getUserOtorizeType().equals(EnumUserOtorize.ADMINSERVICEHP.getStrCode())) {
            mainUI.menuSetupMaster.setEnabled(true);
            mainUI.menuSetupMasterSysvar.setVisible(false);
            mainUI.menuSetupMasterUser.setVisible(false);
            mainUI.menuSetupMasterVendor.setVisible(false);
            mainUI.menuSetupMasterSalesman.setVisible(false);
            mainUI.menuSetupMasterCustomer.setVisible(false);
            mainUI.menuSetupMasterCustomer1.setVisible(false);
            mainUI.menuSetupMasterCustomerSeparator1.setVisible(false);
            mainUI.menuSetupMasterCustomerGroup.setVisible(false);
            mainUI.menuSetupMasterCustomerSubGroup.setVisible(false);
            mainUI.menuSetupMasterCustomerArea.setVisible(false);
            mainUI.menuSetupMasterCustomerSubArea.setVisible(false);
            mainUI.menuSetupMasterProduct.setVisible(false);
            mainUI.menuSetupMasterProduct1.setVisible(false);
            mainUI.menuSetupMasterProductSeparator1.setVisible(false);
            mainUI.menuSetupMasterProductGroup.setVisible(false);
            mainUI.menuSetupMasterProductGroupDept.setVisible(false);
            mainUI.menuSetupMasterProductGroupDivisi.setVisible(false);
            mainUI.menuSetupMasterProductHargaAlternatif.setVisible(false);
            mainUI.menuSetupMasterProductPerubahanHarga.setVisible(false);          
            mainUI.menuWarehouseLapPriceList.setVisible(false);
            
            mainUI.menuSetupMasterDivision.setVisible(false);
            mainUI.menuSetupMasterWarehouse.setVisible(false);
            mainUI.menuSetupMasterDiskon.setVisible(false);
            mainUI.menuSetupMasterDiskonParameterDiskon.setVisible(false);

            mainUI.menuSetupMasterServiceHp.setEnabled(true);
            mainUI.menuSetupMasterGL.setEnabled(false);
            
            mainUI.menuSetupMasterSeparator1.setVisible(false);
            mainUI.menuSetupMasterSeparator12.setVisible(false);
            mainUI.menuSetupMasterSeparator2.setVisible(false);
            mainUI.menuSetupMasterSeparator3.setVisible(false);
                        

            mainUI.menuWarehouseStock.setVisible(false);

            mainUI.menuPurchaseOrder.setVisible(false);

            mainUI.menuSalesOrder.setVisible(false);

	        mainUI.menuServiceHp.setEnabled(true);
	        mainUI.menuServiceHpPenerimaanService.setEnabled(true);
	        mainUI.menuServiceHpLapServiceRekap.setEnabled(true);
	        
	        mainUI.menuServiceHpServiceTeknisi.setEnabled(false);
	        mainUI.menuServiceHpStockProductHp.setEnabled(true);
	        
            mainUI.menuGL.setVisible(false);

            mainUI.menuProses.setVisible(false);
            
            mainUI.menuUtilitas.setEnabled(false);

            mainUI.menuHelp.setEnabled(true);
            mainUI.menuAbout.setEnabled(true);

        } else if (mainUI.getUserActive().getUserOtorizeType().equals(EnumUserOtorize.ADMINSERVICEHPDANPENJ.getStrCode())) {
            mainUI.menuSetupMaster.setEnabled(true);
            mainUI.menuSetupMasterSysvar.setVisible(false);
            mainUI.menuSetupMasterUser.setVisible(false);

            mainUI.menuSetupMasterVendor.setEnabled(false);
            mainUI.menuSetupMasterSalesman.setEnabled(false);
//            mainUI.menuSetupMasterCustomer.setVisible(false);
//            mainUI.menuSetupMasterCustomer1.setVisible(false);
//            mainUI.menuSetupMasterCustomerSeparator1.setVisible(false);
            mainUI.menuSetupMasterCustomerGroup.setEnabled(false);
            mainUI.menuSetupMasterCustomerSubGroup.setEnabled(false);
            mainUI.menuSetupMasterCustomerArea.setEnabled(false);
            mainUI.menuSetupMasterCustomerSubArea.setEnabled(false);
//            mainUI.menuSetupMasterProduct.setVisible(false);
//            mainUI.menuSetupMasterProduct1.setVisible(false);
//            mainUI.menuSetupMasterProductSeparator1.setVisible(false);
            mainUI.menuSetupMasterProductGroup.setEnabled(false);
            mainUI.menuSetupMasterProductGroupDept.setEnabled(false);
            mainUI.menuSetupMasterProductGroupDivisi.setEnabled(false);
            mainUI.menuSetupMasterProductHargaAlternatif.setEnabled(false);
            mainUI.menuSetupMasterProductPerubahanHarga.setEnabled(false);          
//            mainUI.menuWarehouseLapPriceList.setVisible(false);
            
            mainUI.menuSetupMasterDivision.setEnabled(false);
            mainUI.menuSetupMasterWarehouse.setEnabled(false);
            mainUI.menuSetupMasterDiskon.setEnabled(false);
            mainUI.menuSetupMasterDiskonParameterDiskon.setEnabled(false);

            mainUI.menuSetupMasterServiceHp.setEnabled(true);
            mainUI.menuSetupMasterGL.setEnabled(false);
            

            mainUI.menuWarehouseStock.setEnabled(true);
            mainUI.menuWarehouseStockTransfer.setEnabled(true);
            //FALSE
            mainUI.menuWarehouseStockOpname1.setEnabled(false);            
            mainUI.menuWarehouseStockOpnameFormOpname.setEnabled(true);
            
            mainUI.menuWarehouseLapSaldoStock.setEnabled(true);
            mainUI.menuWarehouseLapMutasiStok.setEnabled(true);

            mainUI.menuPurchaseOrder.setEnabled(false);
            mainUI.menuPurchaseOrderIncomingStock.setEnabled(true);
            mainUI.menuPurchaseOrderRepIncomingStock.setEnabled(true);
            mainUI.menuPurchaseOrderSeparator1.setEnabled(true);
            mainUI.menuPurchaseOrderMrv.setEnabled(true);
            mainUI.menuPurchaseOrderRepMrv.setEnabled(true);
            
            mainUI.menuPembayaranHutang.setEnabled(true);
            mainUI.menuPembayaranHutangRep.setEnabled(true);

            mainUI.menuServiceHp.setEnabled(true);
            mainUI.menuServiceHpPenerimaanService.setEnabled(true);
            mainUI.menuServiceHpLapServiceRekap.setEnabled(true);
            mainUI.menuServiceHpServiceTeknisi.setEnabled(false);
            mainUI.menuServiceHpStockProductHp.setEnabled(true);
            
            mainUI.menuServiceHpSeparator1.setVisible(true);
            
            mainUI.menuGL.setVisible(false);
            
            mainUI.menuProses.setVisible(false);
            
            mainUI.menuUtilitas.setEnabled(true);
            mainUI.menuUtilitasTheme.setEnabled(true);
            mainUI.menuUtilitasThemeGantiTheme.setEnabled(true);
            mainUI.menuUtilitasPerbaikan.setEnabled(true);
            mainUI.menuUtilitasPerbaikanSaldoStock.setEnabled(true);
            mainUI.menuUtilitasExportImport.setEnabled(true);

            mainUI.menuHelp.setEnabled(true);
            mainUI.menuAbout.setEnabled(true);
            
        }
        
        //PERKECUALIAN MENU   
//        mainUI.menuServiceHp.setVisible(false);
//        mainUI.menuSetupMasterServiceHp.setVisible(false);
        
        mainUI.menuGL.setVisible(false);
       
        
    }

    public void setOpenAndVisibleAllOtorize(){
    	
    	mainUI.menuHome.setVisible(true);
        
        mainUI.menuSetupMaster.setVisible(true);
        mainUI.menuSetupMasterSysvar.setVisible(true);
        mainUI.menuSetupMasterUser.setVisible(true);
        mainUI.menuSetupMasterVendor.setVisible(true);
        mainUI.menuSetupMasterSalesman.setVisible(true);
        mainUI.menuSetupMasterCustomer.setVisible(true);
        mainUI.menuSetupMasterCustomer1.setVisible(true);
        mainUI.menuSetupMasterCustomerSeparator1.setVisible(true);
        mainUI.menuSetupMasterCustomerGroup.setVisible(true);
        mainUI.menuSetupMasterCustomerSubGroup.setVisible(true);
        mainUI.menuSetupMasterCustomerArea.setVisible(true);
        mainUI.menuSetupMasterCustomerSubArea.setVisible(true);
        mainUI.menuSetupMasterProduct.setVisible(true);
        mainUI.menuSetupMasterProduct1.setVisible(true);
        mainUI.menuSetupMasterProductSeparator1.setVisible(true);
        mainUI.menuSetupMasterProductGroup.setVisible(true);
        mainUI.menuSetupMasterProductGroupDept.setVisible(true);
        mainUI.menuSetupMasterProductGroupDivisi.setVisible(true);
        mainUI.menuSetupMasterProductHargaAlternatif.setVisible(true);
        mainUI.menuSetupMasterProductPerubahanHarga.setVisible(true);            
        mainUI.menuWarehouseLapPriceList.setVisible(true);
        
        mainUI.menuSetupMasterDivision.setVisible(true);
        mainUI.menuSetupMasterWarehouse.setVisible(true);
        mainUI.menuSetupMasterDiskon.setVisible(true);
        mainUI.menuSetupMasterDiskonParameterDiskon.setVisible(true);

        mainUI.menuSetupMasterServiceHp.setVisible(true);
        mainUI.menuSetupMasterGL.setVisible(true);
        
        mainUI.menuSetupMasterSeparator1.setVisible(true);
        mainUI.menuSetupMasterSeparator12.setVisible(true);
        mainUI.menuSetupMasterSeparator2.setVisible(true);
        mainUI.menuSetupMasterSeparator3.setVisible(true);
        
        mainUI.menuWarehouseStock.setVisible(true);
        mainUI.menuWarehouseStockTransfer.setVisible(true);
        mainUI.menuWarehouseStockOpname1.setVisible(true);
        mainUI.menuWarehouseLapSaldoStock.setVisible(true);
        mainUI.menuWarehouseLapMutasiStok.setVisible(true);

        mainUI.menuWarehouseStockSeparator1.setVisible(true);
        
        mainUI.menuPurchaseOrder.setVisible(true);
        mainUI.menuPurchaseOrderIncomingStock.setVisible(true);
        mainUI.menuPurchaseOrderRepIncomingStock.setVisible(true);
        mainUI.menuPurchaseOrderSeparator1.setVisible(true);
        mainUI.menuPurchaseOrderMrv.setVisible(true);
        mainUI.menuPurchaseOrderRepMrv.setVisible(true);
        
        mainUI.menuPembayaranHutang.setVisible(true);
        mainUI.menuPembayaranHutangRep.setVisible(true);

        mainUI.menuPurchaseOrderSeparator1.setVisible(true);
        mainUI.menuPurchaseOrderSeparator2.setVisible(true);

        
        mainUI.menuSalesOrder.setVisible(true);
        mainUI.menuSalesOrder1.setVisible(true);
        mainUI.menuSalesOrderPencetakan.setVisible(true);
        mainUI.menuSalesOrderPackingList.setVisible(true);
        mainUI.menuSalesOrderPackingList1.setVisible(true);
        mainUI.menuSalesOrderPackingListRekapPerhari.setVisible(true);
        mainUI.menuSalesOrderLaporan1.setVisible(true);
        mainUI.menuSalesOrderRep.setVisible(true);
        mainUI.menuSalesPerBarang.setVisible(true);
        mainUI.menuSalesPrestasiKerja.setVisible(true);
        mainUI.menuSalesOrderSeparator1.setVisible(true);
        mainUI.menuSalesOrderSalesReturn.setVisible(true);
        mainUI.menuSalesOrderRepSalesReturn.setVisible(true);
        mainUI.menuSalesOrderArPayment.setVisible(true);
        mainUI.menuSalesOrderArPaymentRep.setVisible(true);
            mainUI.menuSalesOrderArpaymentRepSaldoPiutang.setVisible(true);
            mainUI.menuSalesOrderArpaymentRepPembayaranPiutang.setVisible(true);
        //			MenuItem menuSalesOrderLaporanPiutang = menuSalesOrder.addItem("Lap. Piutang", this);

        mainUI.menuSalesOrderSeparator1.setVisible(true);
        mainUI.menuSalesOrderSeparator2.setVisible(true);

        mainUI.menuServiceHp.setVisible(true);
        mainUI.menuServiceHpPenerimaanService.setVisible(true);
        mainUI.menuServiceHpLapServiceRekap.setVisible(true);
        mainUI.menuServiceHpServiceTeknisi.setVisible(true);
        mainUI.menuServiceHpStockProductHp.setVisible(true);
        
        mainUI.menuGL.setVisible(true);
    	mainUI.menuGLNeraca.setVisible(true);
    	mainUI.menuGLLabaRugi.setVisible(true);
        
        mainUI.menuProses.setVisible(true);
        mainUI.menuProsesAkhirhari.setVisible(true);
        mainUI.menuProsesAkhirtahun.setVisible(true);
        
        mainUI.menuUtilitas.setVisible(true);
        mainUI.menuUtilitasTheme.setVisible(true);
        mainUI.menuUtilitasThemeGantiTheme.setVisible(true);
        mainUI.menuUtilitasPerbaikan.setVisible(true);
        mainUI.menuUtilitasPerbaikanSaldoStock.setVisible(true);
        mainUI.menuUtilitasExportImport.setVisible(true);

        mainUI.menuHelp.setVisible(true);
        mainUI.menuAbout.setVisible(true);
    	
        //###SET ENABLE
        mainUI.menuHome.setEnabled(true);
        
        mainUI.menuSetupMaster.setEnabled(true);
        mainUI.menuSetupMasterSysvar.setEnabled(true);
        mainUI.menuSetupMasterUser.setEnabled(true);
        mainUI.menuSetupMasterVendor.setEnabled(true);
        mainUI.menuSetupMasterSalesman.setEnabled(true);
        mainUI.menuSetupMasterCustomer.setEnabled(true);
        mainUI.menuSetupMasterCustomer1.setEnabled(true);
        mainUI.menuSetupMasterCustomerSeparator1.setEnabled(true);
        mainUI.menuSetupMasterCustomerGroup.setEnabled(true);
        mainUI.menuSetupMasterCustomerSubGroup.setEnabled(true);
        mainUI.menuSetupMasterCustomerArea.setEnabled(true);
        mainUI.menuSetupMasterCustomerSubArea.setEnabled(true);
        mainUI.menuSetupMasterProduct.setEnabled(true);
        mainUI.menuSetupMasterProduct1.setEnabled(true);
        mainUI.menuSetupMasterProductSeparator1.setEnabled(true);
        mainUI.menuSetupMasterProductGroup.setEnabled(true);
        mainUI.menuSetupMasterProductGroupDept.setEnabled(true);
        mainUI.menuSetupMasterProductGroupDivisi.setEnabled(true);
        mainUI.menuSetupMasterProductHargaAlternatif.setEnabled(true);
        mainUI.menuSetupMasterProductPerubahanHarga.setEnabled(true);            
        mainUI.menuWarehouseLapPriceList.setEnabled(true);
        
        mainUI.menuSetupMasterServiceHp.setEnabled(true);
        mainUI.menuSetupMasterGL.setEnabled(true);
        

        mainUI.menuSetupMasterDivision.setEnabled(true);
        mainUI.menuSetupMasterWarehouse.setEnabled(true);
        mainUI.menuSetupMasterDiskon.setEnabled(true);
        mainUI.menuSetupMasterDiskonParameterDiskon.setEnabled(true);

        mainUI.menuWarehouseStock.setEnabled(true);
        mainUI.menuWarehouseStockTransfer.setEnabled(true);
        mainUI.menuWarehouseStockOpname.setEnabled(true);
	        mainUI.menuWarehouseStockOpname1.setEnabled(true);
	        mainUI.menuWarehouseStockOpnameFormOpname.setEnabled(true);
        mainUI.menuWarehouseLapSaldoStock.setEnabled(true);
        mainUI.menuWarehouseLapMutasiStok.setEnabled(true);

        mainUI.menuPurchaseOrder.setEnabled(true);
        mainUI.menuPurchaseOrderIncomingStock.setEnabled(true);
        mainUI.menuPurchaseOrderRepIncomingStock.setEnabled(true);
        mainUI.menuPurchaseOrderSeparator1.setEnabled(true);
        mainUI.menuPurchaseOrderMrv.setEnabled(true);
        mainUI.menuPurchaseOrderRepMrv.setEnabled(true);
        
        mainUI.menuPembayaranHutang.setEnabled(true);
        mainUI.menuPembayaranHutangRep.setEnabled(true);

        mainUI.menuSalesOrder.setEnabled(true);
        mainUI.menuSalesOrder1.setEnabled(true);
        mainUI.menuSalesOrderPencetakan.setEnabled(true);
        mainUI.menuSalesOrderPackingList.setEnabled(true);
        mainUI.menuSalesOrderPackingList1.setEnabled(true);
        mainUI.menuSalesOrderPackingListRekapPerhari.setEnabled(true);
        mainUI.menuSalesOrderLaporan1.setEnabled(true);
        mainUI.menuSalesOrderRep.setEnabled(true);
        mainUI.menuSalesPerBarang.setEnabled(true);
        mainUI.menuSalesPrestasiKerja.setEnabled(true);
        mainUI.menuSalesOrderSeparator1.setEnabled(true);
        mainUI.menuSalesOrderSalesReturn.setEnabled(true);
        mainUI.menuSalesOrderRepSalesReturn.setEnabled(true);
        mainUI.menuSalesOrderArPayment.setEnabled(true);
        mainUI.menuSalesOrderArPaymentRep.setEnabled(true);
            mainUI.menuSalesOrderArpaymentRepSaldoPiutang.setEnabled(true);
            mainUI.menuSalesOrderArpaymentRepPembayaranPiutang.setEnabled(true);
        //			MenuItem menuSalesOrderLaporanPiutang = menuSalesOrder.addItem("Lap. Piutang", this);


        mainUI.menuServiceHp.setEnabled(true);
        mainUI.menuServiceHpPenerimaanService.setEnabled(true);
        mainUI.menuServiceHpLapServiceRekap.setEnabled(true);
        mainUI.menuServiceHpServiceTeknisi.setEnabled(true);
        mainUI.menuServiceHpStockProductHp.setEnabled(true);
        
        mainUI.menuServiceHpSeparator1.setVisible(true);

        mainUI.menuGL.setEnabled(true);
        	mainUI.menuGLNeraca.setEnabled(true);
        	mainUI.menuGLLabaRugi.setEnabled(true);
        
        mainUI.menuProses.setEnabled(true);
        mainUI.menuProsesAkhirhari.setEnabled(true);
        mainUI.menuProsesAkhirtahun.setEnabled(true);
        
        mainUI.menuUtilitas.setEnabled(true);
        mainUI.menuUtilitasTheme.setEnabled(true);
        mainUI.menuUtilitasThemeGantiTheme.setEnabled(true);
        mainUI.menuUtilitasPerbaikan.setEnabled(true);
        mainUI.menuUtilitasPerbaikanSaldoStock.setEnabled(true);
        mainUI.menuUtilitasExportImport.setEnabled(true);

        mainUI.menuHelp.setEnabled(true);
        mainUI.menuAbout.setEnabled(true);

        
        
        //PERKECUALIAN MENU   
//        mainUI.menuServiceHp.setVisible(false);
//        mainUI.menuSetupMasterServiceHp.setVisible(false);
        
        mainUI.menuGL.setEnabled(false);
       
        
    }
    public void setOtorizeProperties(){

        mainUI.getBtnUserId().setCaption(mainUI.getUserActive().getUserId() + "::" +
                (mainUI.getUserActive().getFullName()==null?"":mainUI.getUserActive().getFullName()));

        String tanggalTransaksi="";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            tanggalTransaksi = sdf.format(mainUI.getSysvarHelper().getTanggalTransaksiBerjalan());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        mainUI.getBtnSystemCalender().setCaption("Tanggal Sistem: " + tanggalTransaksi);

    }


}
