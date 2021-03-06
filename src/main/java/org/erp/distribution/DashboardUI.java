package org.erp.distribution;

import org.erp.distribution.ap.kredittunai.VendorCreditModel;
import org.erp.distribution.ap.kredittunai.VendorCreditPresenter;
import org.erp.distribution.ap.kredittunai.VendorCreditView;
import org.erp.distribution.ap.kredittunai.saldohutangpervendor.LapSaldoHutangVendorModel;
import org.erp.distribution.ap.kredittunai.saldohutangpervendor.LapSaldoHutangVendorPresenter;
import org.erp.distribution.ap.kredittunai.saldohutangpervendor.LapSaldoHutangVendorView;
import org.erp.distribution.ap.lappembayaranhutang.LapPembayaranHutangModel;
import org.erp.distribution.ap.lappembayaranhutang.LapPembayaranHutangPresenter;
import org.erp.distribution.ap.lappembayaranhutang.LapPembayaranHutangView;
import org.erp.distribution.ar.kredittunai.CustomerCreditModel;
import org.erp.distribution.ar.kredittunai.CustomerCreditPresenter;
import org.erp.distribution.ar.kredittunai.CustomerCreditView;
import org.erp.distribution.ar.lappembayaranpiutang.LapPembayaranPiutangModel;
import org.erp.distribution.ar.lappembayaranpiutang.LapPembayaranPiutangPresenter;
import org.erp.distribution.ar.lappembayaranpiutang.LapPembayaranPiutangView;
import org.erp.distribution.ar.lapsaldopiutang.LapSaldoPiutangPerCustomerModel;
import org.erp.distribution.ar.lapsaldopiutang.LapSaldoPiutangPerCustomerPresenter;
import org.erp.distribution.ar.lapsaldopiutang.LapSaldoPiutangPerCustomerView;
import org.erp.distribution.ar.saldopiutangcustomersalesmanarea.LapPiutangCustomerSalesmanAreaModel;
import org.erp.distribution.ar.saldopiutangcustomersalesmanarea.LapPiutangCustomerSalesmanAreaPresenter;
import org.erp.distribution.ar.saldopiutangcustomersalesmanarea.LapPiutangCustomerSalesmanAreaView;
import org.erp.distribution.ar.sjpenagihan.SjPenagihanModel;
import org.erp.distribution.ar.sjpenagihan.SjPenagihanPresenter;
import org.erp.distribution.ar.sjpenagihan.SjPenagihanView;
import org.erp.distribution.gl.labarugi.LapLabaRugiKotorModel;
import org.erp.distribution.gl.labarugi.LapLabaRugiKotorPresenter;
import org.erp.distribution.gl.labarugi.LapLabaRugiKotorView;
import org.erp.distribution.jpaservice.*;
import org.erp.distribution.jpaservicehp.SCustomerJpaService;
import org.erp.distribution.jpaservicehp.SCustomerJpaServiceImpl;
import org.erp.distribution.jpaservicehp.STeknisiJpaService;
import org.erp.distribution.jpaservicehp.STeknisiJpaServiceImpl;
import org.erp.distribution.jpaservicehp.StServiceJpaService;
import org.erp.distribution.jpaservicehp.StServiceJpaServiceImpl;
import org.erp.distribution.jpaservicerep.LapAktifitasPromoListJpaService;
import org.erp.distribution.jpaservicerep.LapAktifitasPromoListJpaServiceImpl;
import org.erp.distribution.kontrolstok.lapmutasisaldostok.LapMutasiSaldoStockModel;
import org.erp.distribution.kontrolstok.lapmutasisaldostok.LapMutasiSaldoStockPresenter;
import org.erp.distribution.kontrolstok.lapmutasisaldostok.LapMutasiSaldoStockView;
import org.erp.distribution.kontrolstok.lapmutasistok.LapMutasiStockModel;
import org.erp.distribution.kontrolstok.lapmutasistok.LapMutasiStockPresenter;
import org.erp.distribution.kontrolstok.lapmutasistok.LapMutasiStockView;
import org.erp.distribution.kontrolstok.lapsaldostok.LapSaldoStockModel;
import org.erp.distribution.kontrolstok.lapsaldostok.LapSaldoStockPresenter;
import org.erp.distribution.kontrolstok.lapsaldostok.LapSaldoStockView;
import org.erp.distribution.kontrolstok.stockopname.StockOpnameModel;
import org.erp.distribution.kontrolstok.stockopname.StockOpnamePresenter;
import org.erp.distribution.kontrolstok.stockopname.StockOpnameView;
import org.erp.distribution.kontrolstok.stockopname.formopname.FormOpnameModel;
import org.erp.distribution.kontrolstok.stockopname.formopname.FormOpnamePresenter;
import org.erp.distribution.kontrolstok.stockopname.formopname.FormOpnameView;
import org.erp.distribution.kontrolstok.stocktransfer.StockTransferModel;
import org.erp.distribution.kontrolstok.stocktransfer.StockTransferPresenter;
import org.erp.distribution.kontrolstok.stocktransfer.StockTransferView;
import org.erp.distribution.master.customer.CustomerModel;
import org.erp.distribution.master.customer.CustomerPresenter;
import org.erp.distribution.master.customer.CustomerView;
import org.erp.distribution.master.customer.channel.CustomerChannelModel;
import org.erp.distribution.master.customer.channel.CustomerChannelPresenter;
import org.erp.distribution.master.customer.channel.CustomerChannelView;
import org.erp.distribution.master.division.DivisionModel;
import org.erp.distribution.master.division.DivisionPresenter;
import org.erp.distribution.master.division.DivisionView;
import org.erp.distribution.master.geomaps.GeoMapsModel;
import org.erp.distribution.master.geomaps.GeoMapsPresenter;
import org.erp.distribution.master.geomaps.GeoMapsView;
import org.erp.distribution.master.principal.area.PrincipalAreaModel;
import org.erp.distribution.master.principal.area.PrincipalAreaPresenter;
import org.erp.distribution.master.principal.area.PrincipalAreaView;
import org.erp.distribution.master.principal.region.PrincipalRegionModel;
import org.erp.distribution.master.principal.region.PrincipalRegionPresenter;
import org.erp.distribution.master.principal.region.PrincipalRegionView;
import org.erp.distribution.master.principal.territory.PrincipalTerritoryModel;
import org.erp.distribution.master.principal.territory.PrincipalTerritoryPresenter;
import org.erp.distribution.master.principal.territory.PrincipalTerritoryView;
import org.erp.distribution.master.product.ProductModel;
import org.erp.distribution.master.product.ProductPresenter;
import org.erp.distribution.master.product.ProductView;
import org.erp.distribution.master.product.pricelist.LapPriceListModel;
import org.erp.distribution.master.product.pricelist.LapPriceListPresenter;
import org.erp.distribution.master.product.pricelist.LapPriceListView;
import org.erp.distribution.master.producthargaalternatif.HargaAlternatifModel;
import org.erp.distribution.master.producthargaalternatif.HargaAlternatifPresenter;
import org.erp.distribution.master.producthargaalternatif.HargaAlternatifView;
import org.erp.distribution.master.productperubahanharga.PerubahanHargaModel;
import org.erp.distribution.master.productperubahanharga.PerubahanHargaPresenter;
import org.erp.distribution.master.productperubahanharga.PerubahanHargaView;
import org.erp.distribution.master.promoanddiskon.aktifitaspromo.daftarpromoberjalan.DaftarPromoBerjalanModel;
import org.erp.distribution.master.promoanddiskon.aktifitaspromo.daftarpromoberjalan.DaftarPromoBerjalanPresenter;
import org.erp.distribution.master.promoanddiskon.aktifitaspromo.daftarpromoberjalan.DaftarPromoBerjalanView;
import org.erp.distribution.master.promoanddiskon.aktifitaspromosi.AktifitasPromosiModel;
import org.erp.distribution.master.promoanddiskon.aktifitaspromosi.AktifitasPromosiPresenter;
import org.erp.distribution.master.promoanddiskon.aktifitaspromosi.AktifitasPromosiView;
import org.erp.distribution.master.promoanddiskon.aktifitaspromosi.reports.LapAktifitasPromosiModel;
import org.erp.distribution.master.promoanddiskon.aktifitaspromosi.reports.LapAktifitasPromosiPresenter;
import org.erp.distribution.master.promoanddiskon.aktifitaspromosi.reports.LapAktifitasPromosiView;
import org.erp.distribution.master.salesman.SalesmanModel;
import org.erp.distribution.master.salesman.SalesmanPresenter;
import org.erp.distribution.master.salesman.SalesmanView;
import org.erp.distribution.master.servicehp.scustomer.SCustomerModel;
import org.erp.distribution.master.servicehp.scustomer.SCustomerPresenter;
import org.erp.distribution.master.servicehp.scustomer.SCustomerView;
import org.erp.distribution.master.servicehp.smerkhp.SMerkHpModel;
import org.erp.distribution.master.servicehp.smerkhp.SMerkHpPresenter;
import org.erp.distribution.master.servicehp.smerkhp.SMerkHpView;
import org.erp.distribution.master.servicehp.steknisi.STeknisiModel;
import org.erp.distribution.master.servicehp.steknisi.STeknisiPresenter;
import org.erp.distribution.master.servicehp.steknisi.STeknisiView;
import org.erp.distribution.master.systemsetting.parameterdiskon.ParameterDiskonModel;
import org.erp.distribution.master.systemsetting.parameterdiskon.ParameterDiskonPresenter;
import org.erp.distribution.master.systemsetting.parameterdiskon.ParameterDiskonView;
import org.erp.distribution.master.systemsetting.parameterdiskonitem.ParamDiskonItemModel;
import org.erp.distribution.master.systemsetting.parameterdiskonitem.ParamDiskonItemPresenter;
import org.erp.distribution.master.systemsetting.parameterdiskonitem.ParamDiskonItemView;
import org.erp.distribution.master.systemsetting.parameterdiskonitemvendor.ParamDiskonItemVendorModel;
import org.erp.distribution.master.systemsetting.parameterdiskonitemvendor.ParamDiskonItemVendorPresenter;
import org.erp.distribution.master.systemsetting.parameterdiskonitemvendor.ParamDiskonItemVendorView;
import org.erp.distribution.master.systemsetting.parametersistem.ParameterSystemModel;
import org.erp.distribution.master.systemsetting.parametersistem.ParameterSystemPresenter;
import org.erp.distribution.master.systemsetting.parametersistem.ParameterSystemView;
import org.erp.distribution.master.tabel.area.CustomerAreaModel;
import org.erp.distribution.master.tabel.area.CustomerAreaPresenter;
import org.erp.distribution.master.tabel.area.CustomerAreaView;
import org.erp.distribution.master.tabel.customergroup.CustomerGroupModel;
import org.erp.distribution.master.tabel.customergroup.CustomerGroupPresenter;
import org.erp.distribution.master.tabel.customergroup.CustomerGroupView;
import org.erp.distribution.master.tabel.customersubgroup.CustomerSubGroupModel;
import org.erp.distribution.master.tabel.customersubgroup.CustomerSubGroupPresenter;
import org.erp.distribution.master.tabel.customersubgroup.CustomerSubGroupView;
import org.erp.distribution.master.tabel.productgroup.ProductGroupModel;
import org.erp.distribution.master.tabel.productgroup.ProductGroupPresenter;
import org.erp.distribution.master.tabel.productgroup.ProductGroupView;
import org.erp.distribution.master.tabel.productgroupdept.ProductGroupDeptModel;
import org.erp.distribution.master.tabel.productgroupdept.ProductGroupDeptPresenter;
import org.erp.distribution.master.tabel.productgroupdept.ProductGroupDeptView;
import org.erp.distribution.master.tabel.productgroupdivisi.ProductGroupDivisiModel;
import org.erp.distribution.master.tabel.productgroupdivisi.ProductGroupDivisiPresenter;
import org.erp.distribution.master.tabel.productgroupdivisi.ProductGroupDivisiView;
import org.erp.distribution.master.tabel.subarea.CustomerSubAreaModel;
import org.erp.distribution.master.tabel.subarea.CustomerSubAreaPresenter;
import org.erp.distribution.master.tabel.subarea.CustomerSubAreaView;
import org.erp.distribution.master.vendor.VendorModel;
import org.erp.distribution.master.vendor.VendorPresenter;
import org.erp.distribution.master.vendor.VendorView;
import org.erp.distribution.master.warehouse.WarehouseModel;
import org.erp.distribution.master.warehouse.WarehousePresenter;
import org.erp.distribution.master.warehouse.WarehouseView;
import org.erp.distribution.model.FChannel;
import org.erp.distribution.model.User;
import org.erp.distribution.pengguna.UserAccountModel;
import org.erp.distribution.pengguna.UserAccountPresenter;
import org.erp.distribution.pengguna.UserAccountView;
import org.erp.distribution.proses.akhirhari.ProsesAkhirHariModel;
import org.erp.distribution.proses.akhirhari.ProsesAkhirHariPresenter;
import org.erp.distribution.proses.akhirhari.ProsesAkhirHariView;
import org.erp.distribution.purchaseorder.incomingstock.IncomingStockModel;
import org.erp.distribution.purchaseorder.incomingstock.IncomingStockPresenter;
import org.erp.distribution.purchaseorder.incomingstock.IncomingStockView;
import org.erp.distribution.purchaseorder.incomingstock.lapincomingstock.LapIncomingStockModel;
import org.erp.distribution.purchaseorder.incomingstock.lapincomingstock.LapIncomingStockPresenter;
import org.erp.distribution.purchaseorder.incomingstock.lapincomingstock.LapIncomingStockView;
import org.erp.distribution.purchaseorder.retur.IncomingStockReturModel;
import org.erp.distribution.purchaseorder.retur.IncomingStockReturPresenter;
import org.erp.distribution.purchaseorder.retur.IncomingStockReturView;
import org.erp.distribution.purchaseorder.retur.lapmrv.LapMrvModel;
import org.erp.distribution.purchaseorder.retur.lapmrv.LapMrvPresenter;
import org.erp.distribution.purchaseorder.retur.lapmrv.LapMrvView;
import org.erp.distribution.salesorder.salesorder.lapprestasikerja.LapPrestasiKerjaModel;
import org.erp.distribution.salesorder.salesorder.lapprestasikerja.LapPrestasiKerjaPresenter;
import org.erp.distribution.salesorder.salesorder.lapprestasikerja.LapPrestasiKerjaView;
import org.erp.distribution.salesorder.salesorder.lapsalesorder.LapSalesOrderModel;
import org.erp.distribution.salesorder.salesorder.lapsalesorder.LapSalesOrderPresenter;
import org.erp.distribution.salesorder.salesorder.lapsalesorder.LapSalesOrderView;
import org.erp.distribution.salesorder.salesorder.lapsalespercustomer.LapSalesPerCustomerModel;
import org.erp.distribution.salesorder.salesorder.lapsalespercustomer.LapSalesPerCustomerPresenter;
import org.erp.distribution.salesorder.salesorder.lapsalespercustomer.LapSalesPerCustomerView;
import org.erp.distribution.salesorder.salesorder.lapsalestoexel.LapSalesExelModel;
import org.erp.distribution.salesorder.salesorder.lapsalestoexel.LapSalesExelPresenter;
import org.erp.distribution.salesorder.salesorder.lapsalestoexel.LapSalesExelView;
import org.erp.distribution.salesorder.salesorder.packinglist.PackingListModel;
import org.erp.distribution.salesorder.salesorder.packinglist.PackingListPresenter;
import org.erp.distribution.salesorder.salesorder.packinglist.PackingListView;
import org.erp.distribution.salesorder.salesorder.packinglistperhari.LapPackingListModel;
import org.erp.distribution.salesorder.salesorder.packinglistperhari.LapPackingListPresenter;
import org.erp.distribution.salesorder.salesorder.packinglistperhari.LapPackingListView;
import org.erp.distribution.salesorder.salesorder.printinvoice.PrintInvoiceModel;
import org.erp.distribution.salesorder.salesorder.printinvoice.PrintInvoicePresenter;
import org.erp.distribution.salesorder.salesorder.printinvoice.PrintInvoiceView;
import org.erp.distribution.salesorder.salesorder.retur.SalesOrderReturModel;
import org.erp.distribution.salesorder.salesorder.retur.SalesOrderReturPresenter;
import org.erp.distribution.salesorder.salesorder.retur.SalesOrderReturView;
import org.erp.distribution.salesorder.salesorder.retur.lapsalesretur.LapSalesReturModel;
import org.erp.distribution.salesorder.salesorder.retur.lapsalesretur.LapSalesReturPresenter;
import org.erp.distribution.salesorder.salesorder.retur.lapsalesretur.LapSalesReturView;
import org.erp.distribution.salesorder.salesorder.sales.SalesOrderModel;
import org.erp.distribution.salesorder.salesorder.sales.SalesOrderPresenter;
import org.erp.distribution.salesorder.salesorder.sales.SalesOrderView;
import org.erp.distribution.servicehp.lapservicerekap.LapServiceRekapModel;
import org.erp.distribution.servicehp.lapservicerekap.LapServiceRekapPresenter;
import org.erp.distribution.servicehp.lapservicerekap.LapServiceRekapView;
import org.erp.distribution.servicehp.penerimaanservice.PenerimaanServiceModel;
import org.erp.distribution.servicehp.penerimaanservice.PenerimaanServicePresenter;
import org.erp.distribution.servicehp.penerimaanservice.PenerimaanServiceView;
import org.erp.distribution.util.SysvarHelper;
import org.erp.distribution.util.UpdateSystemHelper;
import org.erp.distribution.util.UserOtorizeHelper;
import org.erp.distribution.util.export_import.siptigadara.ExportImportSipTigaDaraModel;
import org.erp.distribution.util.export_import.siptigadara.ExportImportSipTigaDaraPresenter;
import org.erp.distribution.util.export_import.siptigadara.ExportImportSipTigaDaraView;
import org.erp.distribution.utility.pembatalanpenyesuaian.PembatalanPenyesuaianModel;
import org.erp.distribution.utility.pembatalanpenyesuaian.PembatalanPenyesuaianPresenter;
import org.erp.distribution.utility.pembatalanpenyesuaian.PembatalanPenyesuaianView;
import org.erp.distribution.utility.recalculatesaldostock.RecalculateSaldoStockModel;
import org.erp.distribution.utility.recalculatesaldostock.RecalculateSaldoStockPresenter;
import org.erp.distribution.utility.recalculatesaldostock.RecalculateSaldoStockView;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
@Theme("vaadin_theme")
@Title("W-DES::Web Distribution System")
public class DashboardUI extends UI implements Command, Handler, ClickListener{

	//	private Panel panelLogin = new Panel(" --- W-DES Login ----------------------------------------");
	final VerticalLayout content = new VerticalLayout();
	private Panel panelLogin = new Panel();
	private Button btnSignIn = new Button("Sign In");
	private Button btnCancel = new Button("Cancel");
	private Window windowLogin = new Window("W-DES Login");
	private TextField fieldUserId = new TextField("USER ID");
	private PasswordField fieldPassword = new PasswordField("PASSWORD");
	
	private HorizontalLayout layoutHeader = new HorizontalLayout();
	private Button btnSystemCalender = new Button("Tanggal Sistem:: 01-01-2015");
	private Button btnUserId = new Button("USER");
	private Button btnUserSignOut = new Button("Sign Out");

    private User userActive = new User();
	//DILOAD SEBELUM MUNCUL LOGIN
	private SysvarJpaService sysvarJpaService = new SysvarJpaServiceImpl();
	private UserJpaService userJpaService = new UserJpaServiceImpl();
//	private SysvarHelper sysvarHelper = new SysvarHelper();
	private SysvarHelper sysvarHelper = new SysvarHelper(sysvarJpaService, userJpaService);
	private UpdateSystemHelper 	updateSystemHelper;
	
	//DI LOAD SETELAH LOGIN SCREEN
	private SCustomerJpaService sCustomerJpaService;
	private STeknisiJpaService sTeknisiJpaService;
	private StServiceJpaService stServiceJpaService;
	private SMerkJpaService sMerkJpaService;
	
	private FDivisionJpaService fDivisionJpaService;
	private FAreaJpaService fAreaJpaService;
	private FCustomergroupJpaService fCustomergroupJpaService;
	private FCustomerJpaService fCustomerJpaService;
	private FCustomersubgroupJpaService fCustomersubgroupJpaService;
	private FProductgroupdeptJpaService fProductgroupdeptJpaService;
	private FProductgroupdivisiJpaService fProductgroupdivisiJpaService;
	private FProductgroupJpaService fProductgroupJpaService;
	private FProductJpaService fProductJpaService;
	private FSalesmanJpaService fSalesmanJpaService;
	private FStockJpaService fStockJpaService;
	private FSubareaJpaService fSubareaJpaService;
	
	private FtArpaymentdJpaService ftArpaymentdJpaService;
	private FtArpaymenthJpaService ftArpaymenthJpaService;
	private FtappaymenthJpaService ftappaymenthJpaService;
	private FtappaymentdJpaService ftappaymentdJpaService;
	
	private FtOpnamedJpaService ftOpnamedJpaService;
	private FtOpnamehJpaService ftOpnamehJpaService;
	private FtPurchasedJpaService ftPurchasedJpaService;
	private FtPurchasehJpaService ftPurchasehJpaService;
	private FtSaleshJpaService ftSaleshJpaService;
	private FtSalesdJpaService ftSalesdJpaService;
		private FtSalesdPromoTprbJpaService ftSalesdPromoTprbJpaService;
		private FtSalesdPromoTpruDiscJpaService ftSalesdPromoTpruDiscJpaService;
		private FtSalesdPromoTpruCbJpaService ftSalesdPromoTpruCbJpaService;
	private FtSaleshRekapTampunganJpaService ftSaleshRekapTampunganJpaService;
	private FtPricehJpaService ftPricehJpaService;
	private FtPricedJpaService ftPricedJpaService;
	private FtPriceAlthJpaService ftPriceAlthJpaService;
	private FtPriceAltdJpaService ftPriceAltdJpaService;
	private FtAdjusthJpaService ftAdjusthJpaService;
	private FtAdjustdJpaService ftAdjustdJpaService;
	private FtStocktransferdJpaService ftStocktransferdJpaService;
	private FtStocktransferhJpaService ftStocktransferhJpaService;
	private FVendorJpaService fVendorJpaService;
	private FWarehouseJpaService fWarehouseJpaService;	

	private PRegionJpaService pRegionJpaService;	
	private PAreaJpaService pAreaJpaService;	
	private PDistributorJpaService pDistributorJpaService;	
	private FChannelJpaService fChannelJpaService;	
	
	
	private FParamDiskonJpaService fParamDiskonJpaService;
	private FParamDiskonItemJpaService fParamDiskonItemJpaService;
	private FParamDiskonItemVendorJpaService fParamDiskonItemVendorJpaService;
	private FPromoJpaService2 fpromoJpaService;
	
	private BankJpaService bankJpaService;
	private BukugiroJpaService bukugiroJpaService;
	private BukutransferJpaService bukutransferJpaService;

	private LapAktifitasPromoListJpaService lapAktifitasPromoListJpaService;
	
//	private LapPrestasiKerjaJpaService lapPrestasiKerjaJpaService;
//	private LapMutasiStockJpaService lapMutasiStockJpaService;
//	private LapPackingListJpaService lapPackingListJpaService;
//	private LapSJPenagihanListJpaService lapSJPenagihanListJpaService;
//	private LapStockOpanameJpaService lapStockOpanameJpaService;
//	private LapTemplate1JpaService lapTemplate1JpaService;
	
	
//	Panel panelWorkspace1 = new Panel();
//	VerticalLayout workspace1 = new VerticalLayout();
	private Panel panelHeader = new Panel();
	private Panel workspace1 = new Panel();
		private VerticalLayout layoutHeaderLeft = new VerticalLayout();
		private VerticalLayout layoutHeaderRight = new VerticalLayout();
			private HorizontalLayout layoutSystemInfo = new HorizontalLayout();
				private HorizontalLayout layoutSystemInfoPack = new HorizontalLayout();
			private HorizontalLayout layoutMenuBar = new HorizontalLayout();

	private UserOtorizeHelper userOtorizeHelper = new UserOtorizeHelper(this);
	
	//MENU
	public MenuBar menuBar1 = new MenuBar();
	public MenuItem menuHome = menuBar1.addItem("Home", FontAwesome.HOME, this);

	public MenuItem menuSetupMaster = menuBar1.addItem("SETUP MASTER", FontAwesome.DATABASE,  null);
		public MenuItem menuSetupMasterSysvar = menuSetupMaster.addItem("PARAMETER SISTEM", FontAwesome.GEAR,this);
		public MenuItem menuSetupMasterUser = menuSetupMaster.addItem("USER/Pengguna", FontAwesome.USER_MD, this);
		public MenuItem menuSetupMasterSeparator1  = menuSetupMaster.addSeparator();
		public MenuItem menuSetupMasterDivision = menuSetupMaster.addItem("DIVISI/CABANG", this);
		public MenuItem menuSetupMasterWarehouse = menuSetupMaster.addItem("Warehouse/Gudang", FontAwesome.BUILDING,this);
		public MenuItem menuSetupMasterSeparator12  = menuSetupMaster.addSeparator();
		public MenuItem menuSetupMasterVendor = menuSetupMaster.addItem("Vendor/Supplier", this);
		public MenuItem menuSetupMasterSalesman = menuSetupMaster.addItem("Salesman & Covered", this);
		public MenuItem menuSetupMasterCustomer = menuSetupMaster.addItem("Customer/outlet", FontAwesome.USERS, null);
			public MenuItem menuSetupMasterCustomer1 = menuSetupMasterCustomer.addItem("Customer/Outlet", this);
			public MenuItem menuSetupMasterCustomerSeparator1  = menuSetupMasterCustomer.addSeparator();
			public MenuItem menuSetupMasterCustomerGroup = menuSetupMasterCustomer.addItem("Grup Jenis", this);
			public MenuItem menuSetupMasterCustomerSubGroup = menuSetupMasterCustomer.addItem("Jenis", this);
			public MenuItem menuSetupMasterCustomerSeparator2  = menuSetupMasterCustomer.addSeparator();
			public MenuItem menuSetupMasterCustomerArea = menuSetupMasterCustomer.addItem("Area", this);
			public MenuItem menuSetupMasterCustomerSubArea = menuSetupMasterCustomer.addItem("Sub Area/Pasar", this);
			public MenuItem menuSetupMasterCustomerSeparator3  = menuSetupMasterCustomer.addSeparator();
			public MenuItem menuSetupMasterCustomerChannel = menuSetupMasterCustomer.addItem("Channel Cust", this);
		public MenuItem menuSetupMasterProduct = menuSetupMaster.addItem("Produk", FontAwesome.STACK_OVERFLOW, null);
			public MenuItem menuSetupMasterProduct1 = menuSetupMasterProduct.addItem("Produk", this);
			public MenuItem menuSetupMasterProductSeparator1  = menuSetupMasterProduct.addSeparator();
			public MenuItem menuSetupMasterProductGroup = menuSetupMasterProduct.addItem("Produk Grup", this);
			public MenuItem menuSetupMasterProductGroupDept = menuSetupMasterProduct.addItem("P. Department", this);
			public MenuItem menuSetupMasterProductGroupDivisi = menuSetupMasterProduct.addItem("P. BRAND", this);
			public MenuItem menuSetupMasterProductSeparator2  = menuSetupMasterProduct.addSeparator();
			public MenuItem menuSetupMasterProductPerubahanHarga = menuSetupMasterProduct.addItem("Perubahan Harga", this);
			public MenuItem menuSetupMasterProductSeparator3  = menuSetupMasterProduct.addSeparator();
			public MenuItem menuWarehouseLapPriceList = menuSetupMasterProduct.addItem("Lap. Price List", this);
		public MenuItem menuSetupMasterSeparator2  = menuSetupMaster.addSeparator();
		public MenuItem menuSetupMasterDiskon = menuSetupMaster.addItem("DISKON dan Promosi", this);
			public MenuItem menuSetupMasterDiskonAktifitasPromosi = menuSetupMasterDiskon.addItem("Aktifitas Promosi", this);
			public MenuItem menuSetupMasterDiskonAktifitasPromosiLaporan = menuSetupMasterDiskon.addItem("Lap. Aktifitas Promosi", this);
			public MenuItem menuSetupMasterDiskonAktifitasPromosiDaftarAktifitasPromosiBerjalan = menuSetupMasterDiskon.addItem("Daftar Aktifitas Promo Yang Berjalan", this);
			public MenuItem menuSetupMasterDiskonAktifitasSeparator1  = menuSetupMasterDiskon.addSeparator();
			public MenuItem menuSetupMasterDiskonParameterDiskon = menuSetupMasterDiskon.addItem("Parameter Diskon Nota", this);
			public MenuItem menuSetupMasterDiskonParameterDiskonItem = menuSetupMasterDiskon.addItem("Parameter Diskon Item", this);
			public MenuItem menuSetupMasterDiskonParameterDiskonItemVendor = menuSetupMasterDiskon.addItem("Parameter Diskon Item by Vendor (Akumulasi)", this);

		public MenuItem menuSetupMasterProductHargaAlternatif = menuSetupMaster.addItem("Harga Alternatif", this);
		public MenuItem menuSetupMasterSeparator3 = menuSetupMaster.addSeparator();

		public MenuItem menuSetupMasterServiceHp = menuSetupMaster.addItem("Master Service HP", this);
			public MenuItem menuSetupMasterServiceCustomer = menuSetupMasterServiceHp.addItem("Customer", this);
			public MenuItem menuSetupMasterServiceTeknisi = menuSetupMasterServiceHp.addItem("Teknisi", this);
			public MenuItem menuSetupMasterServiceMerkHp = menuSetupMasterServiceHp.addItem("Merk HP", this);

		public MenuItem menuSetupMasterGL = menuSetupMaster.addItem("Master GL", this);
			public MenuItem menuSetupMasterGLAccount = menuSetupMasterGL.addItem("Account", this);
			public MenuItem menuSetupMasterGLBukuBank = menuSetupMasterGL.addItem("Buku Bank", this);
			
		public MenuItem menuSetupPrincipal = menuSetupMaster.addItem("MASTER PRINCIPAL", null);
			public MenuItem menuSetupPrincipalRegion = menuSetupPrincipal.addItem("P. Region", this);
			public MenuItem menuSetupPrincipalArea = menuSetupPrincipal.addItem("P. Area", this);
			public MenuItem menuSetupPrincipalDistributor = menuSetupPrincipal.addItem("P. Distributor", this);

		public MenuItem menuSetupGeoMaps = menuSetupMaster.addItem("GEO MAPS", this);
		
	public MenuItem menuWarehouseStock = menuBar1.addItem("Kontrol Stok Gudang", FontAwesome.DROPBOX, null);
		public MenuItem menuWarehouseStockTransfer = menuWarehouseStock.addItem("Mutasi/Stock Transfer", FontAwesome.EXCHANGE, this);
		public MenuItem menuWarehouseStockOpname = menuWarehouseStock.addItem("Stock Opname", FontAwesome.ADJUST, this);
			public MenuItem menuWarehouseStockOpname1 = menuWarehouseStockOpname.addItem("Stock Opname", FontAwesome.ADJUST, this);
			public MenuItem menuWarehouseStockOpnameFormOpname = menuWarehouseStockOpname.addItem("Pencetakan Form Opname", null, this);
		public MenuItem menuWarehouseStockSeparator1  = menuWarehouseStock.addSeparator();
		public MenuItem menuWarehouseLapSaldoStock = menuWarehouseStock.addItem("Lap. Saldo Stok", this);
		public MenuItem menuWarehouseLapMutasiStok = menuWarehouseStock.addItem("Lap. Mutasi Stok", this);

	public MenuItem menuPurchaseOrder = menuBar1.addItem("PURCHASE", FontAwesome.CREDIT_CARD, null);
		public MenuItem menuPurchaseOrderIncomingStock = menuPurchaseOrder.addItem("Barang Datang", FontAwesome.TRUCK, this);
		public MenuItem menuPurchaseOrderRepIncomingStock = menuPurchaseOrder.addItem("Lap. Barang Datang", this);
		public MenuItem menuPurchaseOrderSeparator1  = menuPurchaseOrder.addSeparator();
		public MenuItem menuPurchaseOrderMrv = menuPurchaseOrder.addItem("Return Principal", FontAwesome.BACKWARD, this);
		public MenuItem menuPurchaseOrderRepMrv = menuPurchaseOrder.addItem("Lap. Market Return", this);
		public MenuItem menuPurchaseOrderSeparator2  = menuPurchaseOrder.addSeparator();
		public MenuItem menuPembayaranHutang = menuPurchaseOrder.addItem("Pembayaran Hutang", null, this);
		public MenuItem menuPembayaranHutangRep = menuPurchaseOrder.addItem("Lap. Hutang", null, null);
			public MenuItem menuPembayaranHutangRepSaldoHutangPerVendor = menuPembayaranHutangRep.addItem("Saldo Hutang Per Supplier", this);
			public MenuItem menuPembayaranHutangRepPembayaranHutang = menuPembayaranHutangRep.addItem("Buku Pembayaran Hutang", this);

	public MenuItem menuSalesOrder = menuBar1.addItem("SALES ORDER & AR", FontAwesome.SHOPPING_CART, null);
		public MenuItem menuSalesOrder1 = menuSalesOrder.addItem("SALES ORDER", FontAwesome.SHOPPING_CART, this);
		public MenuItem menuSalesOrderPencetakan = menuSalesOrder.addItem("Pencetakan Faktur", FontAwesome.PRINT, this);
		public MenuItem menuSalesOrderPackingList = menuSalesOrder.addItem("Paking List", FontAwesome.TRUCK, null);
			public MenuItem menuSalesOrderPackingList1 = menuSalesOrderPackingList.addItem("Paking List", this);
			public MenuItem menuSalesOrderPackingListRekapPerhari = menuSalesOrderPackingList.addItem("Rekap Per Hari", this);
		public MenuItem menuSalesOrderLaporan1 = menuSalesOrder.addItem("Lap. Sales Order", null, null);
			public MenuItem menuSalesOrderRep = menuSalesOrderLaporan1.addItem("Ihtisar Penjualan Harian(IPH)", this);
			public MenuItem menuSalesPerCustomer = menuSalesOrderLaporan1.addItem("Lap. Sales Per Customer", this);
	//			public MenuItem menuSalesPerbarang = menuSalesOrderLaporan1.addItem("Lap. Sales per Barang", this);
	//		public MenuItem menuSalesPerBarang = menuSalesOrderLaporan1.addItem("Lap. Sales Per Barang", null, null);
			public MenuItem menuSalesOrderLaporanSeparator3  = menuSalesOrderLaporan1.addSeparator();
			public MenuItem menuSalesOrderLaporan1SalesOrderRep = menuSalesOrderLaporan1.addItem("Lap. Sales Order", this);
//			public MenuItem menuSalesAreaPerBarang = menuSalesOrderLaporan1.addItem("Lap. Sales Area Per Barang", this);
//			public MenuItem menuSalesGrupPerBarang = menuSalesOrderLaporan1.addItem("Lap. Sales Per Grup Per Barang", this);
			public MenuItem menuSalesPrestasiKerja = menuSalesOrderLaporan1.addItem("Lap. Prestasi Kerja", this);
			public MenuItem menuSalesOrderLaporanSeparator4  = menuSalesOrderLaporan1.addSeparator();
			public MenuItem menuSalesToExel = menuSalesOrderLaporan1.addItem("Export Sales Detil To Exel", this);

		public MenuItem menuSalesOrderSeparator1  = menuSalesOrder.addSeparator();
		public MenuItem menuSalesOrderSalesReturn = menuSalesOrder.addItem("Sales Return", FontAwesome.BACKWARD, this);
		public MenuItem menuSalesOrderRepSalesReturn = menuSalesOrder.addItem("Lap. Sales Return", this);
		public MenuItem menuSalesOrderSeparator2  = menuSalesOrder.addSeparator();
		public MenuItem menuSalesOrderArPayment = menuSalesOrder.addItem("Pelunasan Piutang", FontAwesome.MONEY, this);
		public MenuItem menuSalesOrderArpaymentSjPenagihan = menuSalesOrder.addItem("Surat Jalan Penagihan", this);
		public MenuItem menuSalesOrderArPaymentRep = menuSalesOrder.addItem("Lap. Piutang", null, null);
			public MenuItem menuSalesOrderArpaymentRepSaldoPiutang = menuSalesOrderArPaymentRep.addItem("Saldo Piutang Global", this);
			public MenuItem menuSalesOrderArpaymentRepSaldoPiutangSalesArea = menuSalesOrderArPaymentRep.addItem("Saldo Piutang Outlet per Sales and Area", this);
			public MenuItem menuSalesOrderArpaymentRepPembayaranPiutang = menuSalesOrderArPaymentRep.addItem("Buku Pembayaran Piutang", this);
//		public MenuItem menuSalesOrderLaporanPiutang = menuSalesOrder.addItem("Lap. Piutang", this);

	public MenuItem menuServiceHp = menuBar1.addItem("Service Hp", FontAwesome.MOBILE_PHONE,null);
		public MenuItem menuServiceHpPenerimaanService = menuServiceHp.addItem("Penerimaan & Pengambilan Service", this);
		public MenuItem menuServiceHpLapService = menuServiceHp.addItem("Laporan Service", null);
			public MenuItem menuServiceHpLapServiceRekap = menuServiceHpLapService.addItem("Rekap Service", this);
//		public MenuItem menuPengambilanService = menuServiceHp.addItem("Pengambilan Service", this);
		public MenuItem menuServiceHpSeparator1  = menuServiceHp.addSeparator();
		public MenuItem menuServiceHpServiceTeknisi = menuServiceHp.addItem("Service Teknisi", this);
		public MenuItem menuServiceHpStockProductHp = menuServiceHp.addItem("Stock Produk", this);
		
	public MenuItem menuGL = menuBar1.addItem("GL::", FontAwesome.ADJUST,null);
		public MenuItem menuGLNeraca = menuGL.addItem("Neraca", this);
		public MenuItem menuGLLabaRugi = menuGL.addItem("Laba-Rugi", this);
		
	
	public MenuItem menuProses = menuBar1.addItem("Proses", FontAwesome.STAR_O,null);
		public MenuItem menuProsesAkhirhari = menuProses.addItem("Proses Akhir Hari", this);
		public MenuItem menuProsesSeparator1  = menuProses.addSeparator();
		public MenuItem menuProsesAkhirtahun = menuProses.addItem("Proses Akhir Tahun", this);
	public MenuItem menuUtilitas = menuBar1.addItem("Utilitas", FontAwesome.GEARS, null);
		public MenuItem menuUtilitasTheme = menuUtilitas.addItem("Themes", null);
		public MenuItem menuUtilitasThemeGantiTheme = menuUtilitasTheme.addItem("Ganti Thema", this);
		public MenuItem menuUtilitasPerbaikan = menuUtilitas.addItem("PERBAIKAN", null);
			public MenuItem menuUtilitasPerbaikanSaldoStock = menuUtilitasPerbaikan.addItem("REFRESH SALDO STOK", this);
			public MenuItem menuUtilitasPembatalanPenyesuaian = menuUtilitasPerbaikan.addItem("Pembatalan Penyesuaian", this);
		public MenuItem menuUtilitasSeparator1  = menuUtilitas.addSeparator();
		public MenuItem menuUtilitasExportImport = menuUtilitas.addItem("Export-Import", null);
			public MenuItem menuUtilitasExportImportSipAndroid = menuUtilitasExportImport.addItem("SIP Android Tiga Dara", this);

	public MenuItem menuHelp = menuBar1.addItem("Help",null);
		public MenuItem menuAbout = menuHelp.addItem("About", null);
	
	@Override
	public void menuSelected(MenuItem selectedItem) {
		
		if (selectedItem == menuSetupMasterUser){
			UserAccountModel objModel = new UserAccountModel();
			UserAccountView objView = new UserAccountView(objModel);
			UserAccountPresenter objPresenter = new UserAccountPresenter(objModel, objView);				
			
			workspace1.setCaption("OTORISASI USER/PENGGUNA");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
			
		}else if (selectedItem == menuHome) {
			HomeView homeView = new HomeView();
			homeView.setSizeFull();
			
			workspace1.setCaption("HOME");
			workspace1.setContent(homeView);
			
		}else if (selectedItem == menuSetupMasterSysvar) {
			ParameterSystemModel objModel = new ParameterSystemModel();
			ParameterSystemView objView = new ParameterSystemView(objModel);
			ParameterSystemPresenter objPresenter = new ParameterSystemPresenter(objModel, objView);				
			
			workspace1.setCaption("PARAMETER SISTEM");
			objView.setSizeFull();
//			
			workspace1.setContent(objView);
			
		}else if (selectedItem == menuSetupMasterCustomerArea) {
			CustomerAreaModel objModel = new CustomerAreaModel();
			CustomerAreaView objView = new CustomerAreaView(objModel);
			CustomerAreaPresenter objPresenter = new CustomerAreaPresenter(objModel, objView);				
			
			workspace1.setCaption("AREA CUSTOMER");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
			
		}else if (selectedItem == menuSetupMasterCustomerSubArea) {
			CustomerSubAreaModel objModel = new CustomerSubAreaModel();
			CustomerSubAreaView objView = new CustomerSubAreaView(objModel);
			CustomerSubAreaPresenter objPresenter = new CustomerSubAreaPresenter(objModel, objView);				
			
			workspace1.setCaption("SUB AREA/PASAR");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
			
		}else if (selectedItem == menuSetupMasterCustomerGroup) {
			CustomerGroupModel objModel = new CustomerGroupModel();
			CustomerGroupView objView = new CustomerGroupView(objModel);
			CustomerGroupPresenter objPresenter = new CustomerGroupPresenter(objModel, objView);				
			
			workspace1.setCaption("KELOMPOK JENIS CUSTOMER");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterCustomerSubGroup) {
			CustomerSubGroupModel objModel = new CustomerSubGroupModel();
			CustomerSubGroupView objView = new CustomerSubGroupView(objModel);
			CustomerSubGroupPresenter objPresenter = new CustomerSubGroupPresenter(objModel, objView);				
			
			workspace1.setCaption("JENIS CUSTOMER");
			objView.setSizeFull();
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterCustomerChannel) {
			CustomerChannelModel objModel = new CustomerChannelModel();
			CustomerChannelView objView = new CustomerChannelView(objModel);
			CustomerChannelPresenter objPresenter = new CustomerChannelPresenter(objModel, objView);				
			
			workspace1.setCaption("CUSTOMER CHANNEL");
			objView.setSizeFull();
//			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterProductGroup) {
			ProductGroupModel objModel = new ProductGroupModel();
			ProductGroupView objView = new ProductGroupView(objModel);
			ProductGroupPresenter objPresenter = new ProductGroupPresenter(objModel, objView);				
			
			workspace1.setCaption("GRUP PRODUCT");
			objView.setSizeFull();
//			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterProductGroupDept) {
			ProductGroupDeptModel objModel = new ProductGroupDeptModel();
			ProductGroupDeptView objView = new ProductGroupDeptView(objModel);
			ProductGroupDeptPresenter objPresenter = new ProductGroupDeptPresenter(objModel, objView);				
			
			workspace1.setCaption("DEPARTMENT");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterProductGroupDivisi) {
			ProductGroupDivisiModel objModel = new ProductGroupDivisiModel();
			ProductGroupDivisiView objView = new ProductGroupDivisiView(objModel);
			ProductGroupDivisiPresenter objPresenter = new ProductGroupDivisiPresenter(objModel, objView);				
			
			workspace1.setCaption("DIVISI");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterProductPerubahanHarga) {
			PerubahanHargaModel objModel = new PerubahanHargaModel();
			PerubahanHargaView objView = new PerubahanHargaView(objModel);
			PerubahanHargaPresenter objPresenter = new PerubahanHargaPresenter(objModel, objView);				
			
			workspace1.setCaption("PERUBAHAN HARGA");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterProductHargaAlternatif) {
			HargaAlternatifModel objModel = new HargaAlternatifModel();
			HargaAlternatifView objView = new HargaAlternatifView(objModel);
			HargaAlternatifPresenter objPresenter = new HargaAlternatifPresenter(objModel, objView);				
			
			workspace1.setCaption("HARGA ALTERNATIF");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterDivision) {
			DivisionModel objModel = new DivisionModel();
			DivisionView objView = new DivisionView(objModel);
			DivisionPresenter objPresenter = new DivisionPresenter(objModel, objView);				
			
			workspace1.setCaption("DIVISION/CABANG");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterVendor) {
			VendorModel objModel = new VendorModel();
			VendorView objView = new VendorView(objModel);
			VendorPresenter objPresenter = new VendorPresenter(objModel, objView);				
			
			workspace1.setCaption("PRINCIPAL/SUPPLIER");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterSalesman) {
			SalesmanModel objModel = new SalesmanModel();
			SalesmanView objView = new SalesmanView(objModel);
			SalesmanPresenter objPresenter = new SalesmanPresenter(objModel, objView);				
			
			workspace1.setCaption("MASTER SALESMAN");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterCustomer1) {
			CustomerModel objModel = new CustomerModel();
			CustomerView objView = new CustomerView(objModel);
			CustomerPresenter objPresenter = new CustomerPresenter(objModel, objView);				
			
			workspace1.setCaption("MASTER CUSTOMER");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterProduct1) {
			ProductModel objModel = new ProductModel();
			ProductView objView = new ProductView(objModel);
			ProductPresenter objPresenter = new ProductPresenter(objModel, objView);				
			
			workspace1.setCaption("MASTER PRODUCT");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterWarehouse) {
			WarehouseModel objModel = new WarehouseModel();
			WarehouseView objView = new WarehouseView(objModel);
			WarehousePresenter objPresenter = new WarehousePresenter(objModel, objView);				
			
			workspace1.setCaption("MASTER WAREHOUSE/GUDANG");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
			
		}else if (selectedItem == menuSetupMasterDiskonParameterDiskon) {
			ParameterDiskonModel objModel = new ParameterDiskonModel();
			ParameterDiskonView objView= new ParameterDiskonView(objModel);
			ParameterDiskonPresenter objPresenter = new ParameterDiskonPresenter(objModel, objView);				
			
			workspace1.setCaption("PARAMETER DISKON NOTA");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
			
		}else if (selectedItem == menuSetupMasterDiskonParameterDiskonItem) {
			ParamDiskonItemModel objModel = new ParamDiskonItemModel();
			ParamDiskonItemView objView= new ParamDiskonItemView(objModel);
			ParamDiskonItemPresenter objPresenter = new ParamDiskonItemPresenter(objModel, objView);				
			
			workspace1.setCaption("PARAMETER DISKON ITEM");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterDiskonParameterDiskonItemVendor) {
			ParamDiskonItemVendorModel objModel = new ParamDiskonItemVendorModel();
			ParamDiskonItemVendorView objView= new ParamDiskonItemVendorView(objModel);
			ParamDiskonItemVendorPresenter objPresenter = new ParamDiskonItemVendorPresenter(objModel, objView);				
			
			workspace1.setCaption("PARAM DISKON ITEM BY VENDOR (Akumulasi)");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterDiskonAktifitasPromosi) {
			AktifitasPromosiModel objModel = new AktifitasPromosiModel();
			AktifitasPromosiView objView= new AktifitasPromosiView(objModel);
			AktifitasPromosiPresenter objPresenter = new AktifitasPromosiPresenter(objModel, objView);				
			
			workspace1.setCaption("AKTIFITAS PROMOSI");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
			
		}else if (selectedItem == menuSetupMasterDiskonAktifitasPromosiLaporan) {
			LapAktifitasPromosiModel objModel = new LapAktifitasPromosiModel();
			LapAktifitasPromosiView objView = new LapAktifitasPromosiView(objModel);
			LapAktifitasPromosiPresenter objPresenter = new LapAktifitasPromosiPresenter(objModel, objView);				
			
			workspace1.setCaption("Lap. AKTIFITAS PROMOSI");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterDiskonAktifitasPromosiDaftarAktifitasPromosiBerjalan) {
			DaftarPromoBerjalanModel objModel = new DaftarPromoBerjalanModel();
			DaftarPromoBerjalanView objView = new DaftarPromoBerjalanView(objModel);
			DaftarPromoBerjalanPresenter objPresenter = new DaftarPromoBerjalanPresenter(objModel, objView);				
			
			workspace1.setCaption("Daftar Aktifitas Promosi yang sedang berjalan");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuWarehouseStockTransfer) {
			StockTransferModel objModel = new StockTransferModel();
			StockTransferView objView = new StockTransferView(objModel);
			StockTransferPresenter objPresenter = new StockTransferPresenter(objModel, objView);				
			
			workspace1.setCaption("MUTASI/TRANSFER STOCK");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuWarehouseStockOpname1) {
			StockOpnameModel objModel = new StockOpnameModel();
			StockOpnameView objView = new StockOpnameView(objModel);
			StockOpnamePresenter objPresenter = new StockOpnamePresenter(objModel, objView);				
			
			workspace1.setCaption("STOCK OPNAME");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuWarehouseStockOpnameFormOpname) {
			FormOpnameModel objModel = new FormOpnameModel();
			FormOpnameView objView = new FormOpnameView(objModel);
			FormOpnamePresenter objPresenter = new FormOpnamePresenter(objModel, objView);				
			
			workspace1.setCaption("PENCETAKAN FORM STOCK OPNAME");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuWarehouseLapSaldoStock) {
//			LapSaldoStockModel objModel = new LapSaldoStockModel();
//			LapSaldoStockView objView = new LapSaldoStockView(objModel);
//			LapSaldoStockPresenter objPresenter = new LapSaldoStockPresenter(objModel, objView);				

			LapMutasiSaldoStockModel objModel = new LapMutasiSaldoStockModel();
			LapMutasiSaldoStockView objView = new LapMutasiSaldoStockView(objModel);
			LapMutasiSaldoStockPresenter objPresenter = new LapMutasiSaldoStockPresenter(objModel, objView);				
			
			
			workspace1.setCaption("LAP SALDO STOK GUDANG");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuWarehouseLapPriceList) {
			LapPriceListModel objModel = new LapPriceListModel();
			LapPriceListView objView = new LapPriceListView(objModel);
			LapPriceListPresenter objPresenter = new LapPriceListPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP PRICE LIST");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuWarehouseLapMutasiStok) {
			LapMutasiStockModel objModel = new LapMutasiStockModel();
			LapMutasiStockView objView = new LapMutasiStockView(objModel);
			LapMutasiStockPresenter objPresenter = new LapMutasiStockPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP MUTASI STOK GUDANG");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuPurchaseOrderIncomingStock) {
			IncomingStockModel objModel = new IncomingStockModel();
			IncomingStockView objView = new IncomingStockView(objModel);
			IncomingStockPresenter objPresenter = new IncomingStockPresenter(objModel, objView);				
			
			workspace1.setCaption("INCOMING STOCK/Barang Datang");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuPurchaseOrderRepIncomingStock) {
			LapIncomingStockModel objModel = new LapIncomingStockModel();
			LapIncomingStockView objView = new LapIncomingStockView(objModel);
			LapIncomingStockPresenter objPresenter = new LapIncomingStockPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP INCOMING STOCK");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuPurchaseOrderMrv) {
			IncomingStockReturModel objModel = new IncomingStockReturModel();
			IncomingStockReturView objView = new IncomingStockReturView(objModel);
			IncomingStockReturPresenter objPresenter = new IncomingStockReturPresenter(objModel, objView);				
			
			workspace1.setCaption("MARKET RETURN VOUCHER/RETUR KE PRINCIPAL");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuPurchaseOrderRepMrv) {
			LapMrvModel objModel = new LapMrvModel();
			LapMrvView objView = new LapMrvView(objModel);
			LapMrvPresenter objPresenter = new LapMrvPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP Market Return Voucher (MRV)");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuPembayaranHutang) {
			VendorCreditModel objModel = new VendorCreditModel();
			VendorCreditView objView = new VendorCreditView(objModel);
			VendorCreditPresenter objPresenter = new VendorCreditPresenter(objModel, objView);				
			
			workspace1.setCaption("Pembayaran Hutang");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuPembayaranHutangRepSaldoHutangPerVendor) {
			LapSaldoHutangVendorModel objModel = new LapSaldoHutangVendorModel();
			LapSaldoHutangVendorView objView = new LapSaldoHutangVendorView(objModel);
			LapSaldoHutangVendorPresenter objPresenter = new LapSaldoHutangVendorPresenter(objModel, objView);				
			
			workspace1.setCaption("Lap. Saldo Hutang Vendor");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuPembayaranHutangRepPembayaranHutang) {
			LapPembayaranHutangModel objModel = new LapPembayaranHutangModel();
			LapPembayaranHutangView objView = new LapPembayaranHutangView(objModel);
			LapPembayaranHutangPresenter objPresenter = new LapPembayaranHutangPresenter(objModel, objView);				
			
			workspace1.setCaption("Pembayaran Hutang");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesOrder1) {
			SalesOrderModel objModel = new SalesOrderModel();
			SalesOrderView objView = new SalesOrderView(objModel);
			SalesOrderPresenter objPresenter = new SalesOrderPresenter(objModel, objView);				
			
			workspace1.setCaption("SALES ORDER AND INVOICE");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesOrderPencetakan) {
			PrintInvoiceModel objModel = new PrintInvoiceModel();
			PrintInvoiceView objView = new PrintInvoiceView(objModel);
			PrintInvoicePresenter objPresenter = new PrintInvoicePresenter(objModel, objView);				
			
			workspace1.setCaption("PENCETAKAN FAKTUR PENJUALAN");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesOrderPackingList1) {
			PackingListModel objModel = new PackingListModel();
			PackingListView objView = new PackingListView(objModel);
			PackingListPresenter objPresenter = new PackingListPresenter(objModel, objView);				
			
			workspace1.setCaption("PACKING LIST");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesOrderPackingListRekapPerhari) {
			LapPackingListModel objModel = new LapPackingListModel();
			LapPackingListView objView = new LapPackingListView(objModel);
			LapPackingListPresenter objPresenter = new LapPackingListPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP. REKAP HARIAN");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesOrderRep) {
//			LapSalesOrder2Model objModel = new LapSalesOrder2Model();
//			LapSalesOrder2View objView = new LapSalesOrder2View(objModel);
//			LapSalesOrder2Presenter objPresenter = new LapSalesOrder2Presenter(objModel, objView);				
//			
//			workspace1.setCaption("LAP. SALES ORDER");
//			objView.setSizeFull();
//			
//			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesPerCustomer) {
			LapSalesPerCustomerModel objModel = new LapSalesPerCustomerModel();
			LapSalesPerCustomerView objView = new LapSalesPerCustomerView(objModel);
			LapSalesPerCustomerPresenter objPresenter = new LapSalesPerCustomerPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP. SALES PER CUSTOMER");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
//		}else if (selectedItem == menuSalesPerbarang) {
//			LapSalesModel objModel = new LapSalesModel();
//			LapSalesView objView = new LapSalesView(objModel);
//			LapSalesPresenter objPresenter = new LapSalesPresenter(objModel, objView);				
//			
//			workspace1.setCaption("LAP. SALES PER BARANG");
//			objView.setSizeFull();
//			
//			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesPrestasiKerja) {
			LapPrestasiKerjaModel objModel = new LapPrestasiKerjaModel();
			LapPrestasiKerjaView objView = new LapPrestasiKerjaView(objModel);
			LapPrestasiKerjaPresenter objPresenter = new LapPrestasiKerjaPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP. PRESTASI KERJA");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesOrderLaporan1SalesOrderRep) {
			LapSalesOrderModel objModel = new LapSalesOrderModel();
			LapSalesOrderView objView = new LapSalesOrderView(objModel);
			LapSalesOrderPresenter objPresenter = new LapSalesOrderPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP. SALES ORDER");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
//		}else if (selectedItem == menuSalesAreaPerBarang) {
//			LapSalesAreaPerBarangModel objModel = new LapSalesAreaPerBarangModel();
//			LapSalesAreaPerBarangView objView = new LapSalesAreaPerBarangView(objModel);
//			LapSalesAreaPerBarangPresenter objPresenter = new LapSalesAreaPerBarangPresenter(objModel, objView);				
//			
//			workspace1.setCaption("LAP. SALES AREA PER BARANG");
//			objView.setSizeFull();
//			
//			workspace1.setContent(objView);
//		}else if (selectedItem == menuSalesGrupPerBarang) {
//			LapSalesProductGroupPerBarangModel objModel = new LapSalesProductGroupPerBarangModel();
//			LapSalesProductGroupPerBarangView objView = new LapSalesProductGroupPerBarangView(objModel);
//			LapSalesProductGroupPerBarangPresenter objPresenter = new LapSalesProductGroupPerBarangPresenter(objModel, objView);				
//			
//			workspace1.setCaption("LAP. SALES PER GRUP PER BARANG");
//			objView.setSizeFull();
//			
//			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesToExel) {
			LapSalesExelModel objModel = new LapSalesExelModel();
			LapSalesExelView objView = new LapSalesExelView(objModel);
			LapSalesExelPresenter objPresenter = new LapSalesExelPresenter(objModel, objView);				
			
			workspace1.setCaption("EXPORT SALES TO EXEL");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesOrderSalesReturn) {
			SalesOrderReturModel objModel = new SalesOrderReturModel();
			SalesOrderReturView objView = new SalesOrderReturView(objModel);
			SalesOrderReturPresenter objPresenter = new SalesOrderReturPresenter(objModel, objView);				
			
			workspace1.setCaption("SALES RETURN");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesOrderRepSalesReturn) {
			LapSalesReturModel objModel = new LapSalesReturModel();
			LapSalesReturView objView = new LapSalesReturView(objModel);
			LapSalesReturPresenter objPresenter = new LapSalesReturPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP. SALES RETURN");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesOrderArPayment) {
			CustomerCreditModel objModel = new CustomerCreditModel();
			CustomerCreditView objView = new CustomerCreditView(objModel);
			CustomerCreditPresenter objPresenter = new CustomerCreditPresenter(objModel, objView);				
			
			workspace1.setCaption("PEMBAYARAN PIUTANG");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesOrderArpaymentRepSaldoPiutang) {
			
			LapSaldoPiutangPerCustomerModel objModel = new LapSaldoPiutangPerCustomerModel();
			LapSaldoPiutangPerCustomerView objView = new LapSaldoPiutangPerCustomerView(objModel);
			LapSaldoPiutangPerCustomerPresenter objPresenter = new LapSaldoPiutangPerCustomerPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP. SALDO PIUTANG");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
			
		}else if (selectedItem == menuSalesOrderArpaymentSjPenagihan) {
			
			SjPenagihanModel objModel = new SjPenagihanModel();
			SjPenagihanView objView = new SjPenagihanView(objModel);
			SjPenagihanPresenter objPresenter = new SjPenagihanPresenter(objModel, objView);				
			
			workspace1.setCaption("SURAT JALAN PENAGIHAN");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
			
		}else if (selectedItem == menuSalesOrderArpaymentRepPembayaranPiutang) {
			
			LapPembayaranPiutangModel objModel = new LapPembayaranPiutangModel();
			LapPembayaranPiutangView objView = new LapPembayaranPiutangView(objModel);
			LapPembayaranPiutangPresenter objPresenter = new LapPembayaranPiutangPresenter(objModel, objView);				
			
			workspace1.setCaption("BUKU PEMBAYARAN PIUTANG");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSalesOrderArpaymentRepSaldoPiutangSalesArea) {
			
			LapPiutangCustomerSalesmanAreaModel objModel = new LapPiutangCustomerSalesmanAreaModel();
			LapPiutangCustomerSalesmanAreaView objView = new LapPiutangCustomerSalesmanAreaView(objModel);
			LapPiutangCustomerSalesmanAreaPresenter objPresenter = new LapPiutangCustomerSalesmanAreaPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP PIUTANG SALESMAN AND AREA");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuWarehouseStockTransfer) {
			IncomingStockModel objModel = new IncomingStockModel();
			IncomingStockView objView = new IncomingStockView(objModel);
			IncomingStockPresenter objPresenter = new IncomingStockPresenter(objModel, objView);				
			
			workspace1.setCaption("STOCK TRANSFER/MUTASI STOCK");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuWarehouseStockOpname1) {
			IncomingStockModel objModel = new IncomingStockModel();
			IncomingStockView objView = new IncomingStockView(objModel);
			IncomingStockPresenter objPresenter = new IncomingStockPresenter(objModel, objView);				
			
			workspace1.setCaption("STOCK OPNAME");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuProsesAkhirhari) {
			ProsesAkhirHariModel objModel = new ProsesAkhirHariModel();
			ProsesAkhirHariView objView = new ProsesAkhirHariView(objModel);
			ProsesAkhirHariPresenter objPresenter = new ProsesAkhirHariPresenter(objModel, objView);				
			
			workspace1.setCaption("PROSES AKHIR HARI");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuUtilitasPerbaikanSaldoStock) {
			RecalculateSaldoStockModel objModel = new RecalculateSaldoStockModel();
			RecalculateSaldoStockView objView = new RecalculateSaldoStockView(objModel);
			RecalculateSaldoStockPresenter objPresenter = new RecalculateSaldoStockPresenter(objModel, objView);				
			
			workspace1.setCaption("PERBAIKI SALDO STOCK");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuUtilitasPembatalanPenyesuaian) {
			PembatalanPenyesuaianModel objModel = new PembatalanPenyesuaianModel();
			PembatalanPenyesuaianView objView = new PembatalanPenyesuaianView(objModel);
			PembatalanPenyesuaianPresenter objPresenter = new PembatalanPenyesuaianPresenter(objModel, objView);				
			
			workspace1.setCaption("PEMBATALAN PENYESUAIAN PER HARI");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuUtilitasExportImportSipAndroid) {
			ExportImportSipTigaDaraModel objModel = new ExportImportSipTigaDaraModel();
			ExportImportSipTigaDaraView objView = new ExportImportSipTigaDaraView(objModel);
			ExportImportSipTigaDaraPresenter objPresenter = new ExportImportSipTigaDaraPresenter(objModel, objView);				
			
			workspace1.setCaption("EXPORT-IMPORT SIP TIGA DARA ANDROID");
			objView.setSizeFull();
			
			workspace1.setContent(objView);

		}else if (selectedItem == menuSetupMasterServiceCustomer) {
			SCustomerModel objModel = new SCustomerModel();
			SCustomerView objView = new SCustomerView(objModel);
			SCustomerPresenter objPresenter = new SCustomerPresenter(objModel, objView);				
			
			workspace1.setCaption("CUSTOMER");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterServiceTeknisi) {
			STeknisiModel objModel = new STeknisiModel();
			STeknisiView objView = new STeknisiView(objModel);
			STeknisiPresenter objPresenter = new STeknisiPresenter(objModel, objView);				
			
			workspace1.setCaption("TEKNISI");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupMasterServiceMerkHp) {
			SMerkHpModel objModel = new SMerkHpModel();
			SMerkHpView objView = new SMerkHpView(objModel);
			SMerkHpPresenter objPresenter = new SMerkHpPresenter(objModel, objView);				
			
			workspace1.setCaption("MERK HP");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuServiceHpPenerimaanService) {
			PenerimaanServiceModel objModel = new PenerimaanServiceModel();
			objModel.setParamAdminOrTeknisi("ADMIN");
			PenerimaanServiceView objView= new PenerimaanServiceView(objModel);
			PenerimaanServicePresenter objPresenter = new PenerimaanServicePresenter(objModel, objView);				
			
			workspace1.setCaption("PENERIMAAN SERVICE");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuServiceHpLapServiceRekap) {
			LapServiceRekapModel objModel = new LapServiceRekapModel();
			LapServiceRekapView objView= new LapServiceRekapView(objModel);
			LapServiceRekapPresenter objPresenter = new LapServiceRekapPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP REKAP SERVICE");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuServiceHpServiceTeknisi) {
			PenerimaanServiceModel objModel = new PenerimaanServiceModel();
			objModel.setParamAdminOrTeknisi("TEKNISI");
			PenerimaanServiceView objView= new PenerimaanServiceView(objModel);
			PenerimaanServicePresenter objPresenter = new PenerimaanServicePresenter(objModel, objView);				
			
			workspace1.setCaption("SERVICE TEKNISI");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupPrincipalRegion) {
			PrincipalRegionModel objModel = new PrincipalRegionModel();
			PrincipalRegionView objView = new PrincipalRegionView(objModel);
			PrincipalRegionPresenter objPresenter = new PrincipalRegionPresenter(objModel, objView);				
			
			workspace1.setCaption("P. REGION");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupPrincipalArea) {
			PrincipalAreaModel objModel = new PrincipalAreaModel();
			PrincipalAreaView objView = new PrincipalAreaView(objModel);
			PrincipalAreaPresenter objPresenter = new PrincipalAreaPresenter(objModel, objView);				
			
			workspace1.setCaption("P. AREA");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupPrincipalDistributor) {
			PrincipalTerritoryModel objModel = new PrincipalTerritoryModel();
			PrincipalTerritoryView objView = new PrincipalTerritoryView(objModel);
			PrincipalTerritoryPresenter objPresenter = new PrincipalTerritoryPresenter(objModel, objView);				
			
			workspace1.setCaption("P. TERRITORY/DISTRIBUTOR");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuSetupGeoMaps) {
			GeoMapsModel objModel = new GeoMapsModel();
			GeoMapsView objView = new GeoMapsView(objModel);
			GeoMapsPresenter objPresenter = new GeoMapsPresenter(objModel, objView);				
			
			workspace1.setCaption("GEO MAPS");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}else if (selectedItem == menuServiceHpStockProductHp) {
			
			LapSaldoStockModel objModel = new LapSaldoStockModel();
			LapSaldoStockView objView = new LapSaldoStockView(objModel);
			LapSaldoStockPresenter objPresenter = new LapSaldoStockPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP SALDO STOK GUDANG");
			objView.setSizeFull();

			workspace1.setContent(objView);
		}else if (selectedItem == menuGLNeraca) {
		}else if (selectedItem == menuGLLabaRugi) {
			LapLabaRugiKotorModel objModel = new LapLabaRugiKotorModel();
			LapLabaRugiKotorView objView= new LapLabaRugiKotorView(objModel);
			LapLabaRugiKotorPresenter objPresenter = new LapLabaRugiKotorPresenter(objModel, objView);				
			
			workspace1.setCaption("LAP. LABA RUGI KOTOR");
			objView.setSizeFull();
			
			workspace1.setContent(objView);
		}
		
	}
	
	public void initListener(){
		btnUserSignOut.addClickListener(this);
		
		btnSignIn.addClickListener(this);
		btnCancel.addClickListener(this);
	}
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == btnUserSignOut) {
			ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {
				@Override
				public void onClose(ConfirmDialog dialog) {
					if (dialog.isConfirmed()) {
						// Confirmed to continue
						userActive = new User();
						showWindowLogin();
					} else {
						// User did not confirm
					}
				}
			};

			final ConfirmDialog d = ConfirmDialog.show(getUI(), "Sign Out", "Yakin Keluar Aplikasi?",
					"Sign Out", "Cancel", konfirmDialogListener);

			final ShortcutListener enter = new ShortcutListener("Oke",
					KeyCode.ENTER, null) {
				@Override
				public void handleAction(Object sender, Object target) {
					d.close();
				}
			};

			d.setStyleName("dialog");
			d.getOkButton().setStyleName("small");
			d.getCancelButton().setStyleName("small");
			d.focus();

		} else if (event.getButton() == btnSignIn) {

					User user=new User();
					user = userJpaService.findById(fieldUserId.getValue());
					String strUser = "dummyxxx####";
					String strPass = "dummyxxx####";
					boolean userEnabled = false;
					try{
						strUser = user.getUserId();
						strPass= user.getUserPassword();
						strUser = strUser.trim();
						strPass = strPass.trim();
						userEnabled = user.isActive();
					} catch(Exception ex){}

					if (fieldUserId.getValue() != null
							&& fieldUserId.getValue().equalsIgnoreCase(strUser)
							&& fieldPassword.getValue() != null
							&& fieldPassword.getValue().equals(strPass)
							&& userEnabled==true) {


						//INISIALIASAI USER YANG AKTIF
						userActive = new User();
						userActive = user;

						Notification.show("Login Success!!.. ", Notification.TYPE_TRAY_NOTIFICATION);
						//TUTUP WINDO LOGIN
						windowLogin.close();
						initSystemVariable();

						//SETING OTORIZE MENU
						setOtorizeMenu();

					}else {
						Notification.show("User ID atau Password salah!", Notification.TYPE_TRAY_NOTIFICATION);

					}



			
		} else if (event.getButton()==btnCancel) {			
			//CHECK SECURITY PROBLEM
			content.removeComponent(panelHeader);
			content.removeComponent(workspace1);
			content.setVisible(false);
			windowLogin.close();
			
		}
	}


	@VaadinServletConfiguration(productionMode=false, ui=DashboardUI.class)
	public static class Servlet extends VaadinServlet{		
			    		
	}

	@Override
	protected void init(VaadinRequest request) {

		initComponent();
		initListener();
		buildView();
		
	}

	public void initComponent(){
		workspace1.setIcon(FontAwesome.REORDER);

		layoutHeader.setIcon(new ThemeResource("../images/logo-default/uwiga-blue.jpg") );

		getBtnSystemCalender().setIcon(FontAwesome.CALENDAR);
		getBtnSystemCalender().addStyleName(Reindeer.BUTTON_LINK);
		getBtnUserId().addStyleName(Reindeer.BUTTON_LINK);
		getBtnUserId().setIcon(FontAwesome.USER);
		getBtnUserSignOut().addStyleName(Reindeer.BUTTON_LINK);
		getBtnUserSignOut().setIcon(FontAwesome.POWER_OFF);

	}
	public void buildView(){
		workspace1.setSizeFull();

//		content.setMargin(true);
//		content.setSizeFull();
		content.setWidth("100%");
		content.setHeight("100%");

//		layoutHeader.setSizeFull();
//		layoutHeaderLeft.setSizeFull();
//		layoutHeaderRight.setSizeFull();
		layoutHeader.setWidth("100%");
//		layoutHeaderRight.setWidth("50%");
		layoutHeaderLeft.setWidth("100%");

		layoutSystemInfo.setWidth("100%");
		layoutMenuBar.setWidth("100%");

		layoutHeaderRight.addComponent(layoutSystemInfo);
		layoutHeaderRight.addComponent(layoutMenuBar);

		layoutMenuBar.addComponent(menuBar1);


//		layoutHeader.addComponent(layoutHeaderLeft);
		layoutHeader.addComponent(layoutHeaderRight);
		panelHeader.setContent(layoutHeader);



		layoutSystemInfoPack.setSpacing(true);
		layoutSystemInfoPack.addComponent(getBtnSystemCalender());
		layoutSystemInfoPack.addComponent(getBtnUserId());
		layoutSystemInfoPack.addComponent(getBtnUserSignOut());
		layoutSystemInfo.addComponent(layoutSystemInfoPack);

		layoutSystemInfo.setComponentAlignment(layoutSystemInfoPack, Alignment.MIDDLE_RIGHT);
		layoutMenuBar.setComponentAlignment(menuBar1, Alignment.MIDDLE_RIGHT);

		content.addComponent(panelHeader);
		content.addComponent(workspace1);
		content.setExpandRatio(workspace1, 1.0f);


		buildWindowLogin();

		setContent(content);

		layoutSystemInfo.setHeight("20px");
		setComponentStyles();

	}

	public void buildWindowLogin(){
		//WINDOW LOGIN MUNCUL
		windowLogin.setWidth("320px");
		windowLogin.setHeight("300px");
		windowLogin.center();
		windowLogin.setClosable(false);
		windowLogin.setResizable(false);
		windowLogin.setModal(true);


		VerticalLayout layoutLogin = new VerticalLayout();
		VerticalLayout layoutLogin1 = new VerticalLayout();
		HorizontalLayout layoutLogin2 = new HorizontalLayout();
		layoutLogin2.setSpacing(true);

		layoutLogin.setSizeFull();
		layoutLogin.setMargin(true);


		fieldUserId.setWidth("220px");
		fieldPassword.setWidth("220px");

//		fieldUserId.setValue("bagus");
//		fieldPassword.setValue("Welcome1");
		fieldUserId.setValue("");
		fieldPassword.setValue("");

		btnSignIn.setIcon(new ThemeResource("../images/navigation/14x14/Unlock.png") );
		btnCancel.setIcon(new ThemeResource("../images/navigation/14x14/Undo.png") );


		layoutLogin1.addComponent(fieldUserId);
		layoutLogin1.addComponent(fieldPassword);
		layoutLogin2.addComponent(btnSignIn);
		layoutLogin2.addComponent(btnCancel);

		layoutLogin.addComponent(layoutLogin1);
		layoutLogin.addComponent(layoutLogin2);

		panelLogin.setContent(layoutLogin);
		panelLogin.setSizeFull();

		VerticalLayout layoutUtama = new VerticalLayout();
		layoutUtama.setSizeFull();
		layoutUtama.setMargin(true);
		layoutUtama.addComponent(panelLogin);


		windowLogin.setContent(layoutUtama);

//		:::WINDOW LOGIN DULUAN
		showWindowLogin();
//      initSystemVariable();

		panelLogin.addActionHandler(this);
		
		//SET IMAGE ON BACKGROUND
		HomeView homeView = new HomeView();
		homeView.setSizeFull();
		
		workspace1.setCaption("HOME");
		workspace1.setContent(homeView);


	}
	public void showWindowLogin(){
		getUI().addWindow(windowLogin);		
	}
	
	public void setComponentStyles(){
//		windowLogin.setStyleName(Reindeer.WINDOW_BLACK);
//		panelLogin.setStyleName(Reindeer.PANEL_LIGHT);

//		menuBar1.addStyleName("small");

//		layoutHeader.addStyleName(Reindeer.LAYOUT_BLACK);


	}

	public void initSystemVariable(){
		
//		transaksiHelper = new TransaksiHelperImpl();
//		productAndStockHelper = new ProductAndStockHelper();
		
		fDivisionJpaService = new FDivisionJpaServiceImpl();
		fAreaJpaService = new FAreaJpaServiceImpl();
		fCustomergroupJpaService = new FCustomergroupJpaServiceImpl();
		fCustomerJpaService = new FCustomerJpaServiceImpl();
		fCustomersubgroupJpaService = new FCustomersubgroupJpaServiceImpl();
		fProductgroupdeptJpaService = new FProductgroupdeptJpaServiceImpl();
		fProductgroupdivisiJpaService = new FProductgroupdivisiJpaServiceImpl();
		fProductgroupJpaService = new FProductgroupJpaServiceImpl();
		fProductJpaService = new FProductJpaServiceImpl();
		fSalesmanJpaService = new FSalesmanJpaServiceImpl();
		fStockJpaService = new FStockJpaServiceImpl();
		fSubareaJpaService = new FSubareaJpaServiceImpl();
		
		ftArpaymentdJpaService = new FtArpaymentdJpaServiceImpl();
		ftArpaymenthJpaService = new FtArpaymenthJpaServiceImpl();
		ftappaymenthJpaService = new FtappaymenthJpaServiceImpl();
		ftappaymentdJpaService = new FtappaymentdJpaServiceImpl();
		
		ftOpnamedJpaService = new FtOpnamedJpaServiceImpl();
		ftOpnamehJpaService = new FtOpnamehJpaServiceImpl();
		ftPurchasedJpaService = new FtPurchasedJpaServiceImpl();
		ftPurchasehJpaService = new FtPurchasehJpaServiceImpl();
		ftSaleshJpaService = new FtSaleshJpaServiceImpl();
		ftSalesdJpaService = new FtSalesdJpaServiceImpl();
			ftSalesdPromoTprbJpaService = new FtSalesdPromoTprbJpaServiceImpl();
			ftSalesdPromoTpruDiscJpaService = new FtSalesdPromoTpruDiscJpaServiceImpl();
			ftSalesdPromoTpruCbJpaService = new FtSalesdPromoTpruCbJpaServiceImpl();
		ftSaleshRekapTampunganJpaService = new FtSaleshRekapTampunganJpaServiceImpl();
		ftPricehJpaService = new FtPricehJpaServiceImpl();
		ftPricedJpaService = new FtPricedJpaServiceImpl();
		ftPriceAlthJpaService = new FtPriceAlthJpaServiceImpl();
		ftPriceAltdJpaService = new FtPriceAltdJpaServiceImpl();
		ftAdjusthJpaService = new FtAdjusthJpaServiceImpl();
		ftAdjustdJpaService = new FtAdjustdJpaServiceImpl();
		ftStocktransferdJpaService = new FtStocktransferdJpaServiceImpl();
		ftStocktransferhJpaService = new FtStocktransferhJpaServiceImpl();
		fVendorJpaService = new FVendorJpaServiceImpl();
		fWarehouseJpaService = new FWarehouseJpaServiceImpl();
		

		pRegionJpaService = new PRegionJpaServiceImpl();
		pAreaJpaService = new PAreaJpaServiceImpl();
		pDistributorJpaService = new PDistributorJpaServiceImpl();
		fChannelJpaService = new FChannelJpaServiceImpl();
		
		fParamDiskonJpaService = new FParamDiskonJpaServiceImpl();
		fParamDiskonItemJpaService = new FParamDiskonItemJpaServiceImpl();
		fParamDiskonItemVendorJpaService = new FParamDiskonItemVendorJpaServiceImpl();
		fpromoJpaService = new FPromoJpaService2Impl();
		
		bankJpaService = new BankJpaServiceImpl();
		bukugiroJpaService = new BukugiroJpaServiceImpl();
		bukutransferJpaService = new BukutransferJpaServiceImpl();

		lapAktifitasPromoListJpaService = new LapAktifitasPromoListJpaServiceImpl();
		
//		lapPrestasiKerjaJpaService = new LapPrestasiKerjaJpaServiceImpl();
//		lapMutasiStockJpaService = new LapMutasiStockJpaServiceImpl();
//		lapPackingListJpaService = new LapPackingListJpaServiceImpl();
//		lapSJPenagihanListJpaService = new LapSJPenagihanListJpaServiceImpl();
//		lapStockOpanameJpaService = new LapStockOpanameJpaServiceImpl();
//		lapTemplate1JpaService = new LapTemplate1JpaServiceImpl();
		
		sCustomerJpaService = new SCustomerJpaServiceImpl();
		sTeknisiJpaService = new STeknisiJpaServiceImpl();
		stServiceJpaService = new StServiceJpaServiceImpl();
		sMerkJpaService = new SMerkJpaServiceImpl();
		
		
		//UPDATE DEFAULT JIKA PERLU
//		sysvarHelper.defaultConfigSysvar();
		updateSystemHelper  = new UpdateSystemHelper(ftSalesdJpaService, fStockJpaService);
		//####UPDATE SYSTEM
//		updateSystemHelper.updateVersion11();
//		updateSystemHelper.updateVersion12();
		
		
		
	}
	
	public void setOtorizeMenu(){
		userOtorizeHelper.setUserOtorize();
		userOtorizeHelper.setOtorizeProperties();
	}

	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}

	public UserJpaService getUserJpaService() {
		return userJpaService;
	}


	public FAreaJpaService getfAreaJpaService() {
		return fAreaJpaService;
	}

	public FCustomergroupJpaService getfCustomergroupJpaService() {
		return fCustomergroupJpaService;
	}

	public FCustomerJpaService getfCustomerJpaService() {
		return fCustomerJpaService;
	}

	public FProductgroupdeptJpaService getfProductgroupdeptJpaService() {
		return fProductgroupdeptJpaService;
	}

	public FProductgroupdivisiJpaService getfProductgroupdivisiJpaService() {
		return fProductgroupdivisiJpaService;
	}

	public FProductgroupJpaService getfProductgroupJpaService() {
		return fProductgroupJpaService;
	}

	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}


	public FSalesmanJpaService getfSalesmanJpaService() {
		return fSalesmanJpaService;
	}

	public FStockJpaService getfStockJpaService() {
		return fStockJpaService;
	}

	public FSubareaJpaService getfSubareaJpaService() {
		return fSubareaJpaService;
	}

	public FtArpaymentdJpaService getFtArpaymentdJpaService() {
		return ftArpaymentdJpaService;
	}

	public FtArpaymenthJpaService getFtArpaymenthJpaService() {
		return ftArpaymenthJpaService;
	}

	public FtOpnamedJpaService getFtOpnamedJpaService() {
		return ftOpnamedJpaService;
	}

	public FtOpnamehJpaService getFtOpnamehJpaService() {
		return ftOpnamehJpaService;
	}

	public FtPurchasedJpaService getFtPurchasedJpaService() {
		return ftPurchasedJpaService;
	}

	public FtPurchasehJpaService getFtPurchasehJpaService() {
		return ftPurchasehJpaService;
	}

	public FtSalesdJpaService getFtSalesdJpaService() {
		return ftSalesdJpaService;
	}

	public FtSaleshJpaService getFtSaleshJpaService() {
		return ftSaleshJpaService;
	}


	public FtStocktransferdJpaService getFtStocktransferdJpaService() {
		return ftStocktransferdJpaService;
	}

	public FtStocktransferhJpaService getFtStocktransferhJpaService() {
		return ftStocktransferhJpaService;
	}

	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
	}

	public FWarehouseJpaService getfWarehouseJpaService() {
		return fWarehouseJpaService;
	}

	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}

	public void setUserJpaService(UserJpaService userJpaService) {
		userJpaService = userJpaService;
	}

	public void setfAreaJpaService(FAreaJpaService fAreaJpaService) {
		this.fAreaJpaService = fAreaJpaService;
	}

	public void setfCustomergroupJpaService(
			FCustomergroupJpaService fCustomergroupJpaService) {
		this.fCustomergroupJpaService = fCustomergroupJpaService;
	}

	public void setfCustomerJpaService(FCustomerJpaService fCustomerJpaService) {
		this.fCustomerJpaService = fCustomerJpaService;
	}


	public FCustomersubgroupJpaService getfCustomersubgroupJpaService() {
		return fCustomersubgroupJpaService;
	}


	public MenuBar getMenuBar1() {
		return menuBar1;
	}

	public MenuItem getMenuHome() {
		return menuHome;
	}

	public MenuItem getMenuSetupMaster() {
		return menuSetupMaster;
	}

	public MenuItem getMenuSetupMasterVendor() {
		return menuSetupMasterVendor;
	}

	public MenuItem getMenuSetupMasterCustomer() {
		return menuSetupMasterCustomer;
	}

	public MenuItem getMenuSetupMasterCustomer1() {
		return menuSetupMasterCustomer1;
	}

	public MenuItem getMenuSetupMasterCustomerSeparator1() {
		return menuSetupMasterCustomerSeparator1;
	}

	public MenuItem getMenuSetupMasterCustomerGroup() {
		return menuSetupMasterCustomerGroup;
	}

	public MenuItem getMenuSetupMasterCustomerSubGroup() {
		return menuSetupMasterCustomerSubGroup;
	}

	public MenuItem getMenuSetupMasterCustomerSeparator2() {
		return menuSetupMasterCustomerSeparator2;
	}

	public MenuItem getMenuSetupMasterCustomerArea() {
		return menuSetupMasterCustomerArea;
	}

	public MenuItem getMenuSetupMasterCustomerSubArea() {
		return menuSetupMasterCustomerSubArea;
	}

	public MenuItem getMenuSetupMasterProduct() {
		return menuSetupMasterProduct;
	}

	public MenuItem getMenuWarehouseStock() {
		return menuWarehouseStock;
	}

	public MenuItem getMenuPurchaseOrder() {
		return menuPurchaseOrder;
	}

	public MenuItem getMenuSalesOrder() {
		return menuSalesOrder;
	}

	public MenuItem getMenuUtilitas() {
		return menuUtilitas;
	}

	public void setfCustomersubgroupJpaService(
			FCustomersubgroupJpaService fCustomersubgroupJpaService) {
		this.fCustomersubgroupJpaService = fCustomersubgroupJpaService;
	}

	public void setMenuBar1(MenuBar menuBar1) {
		this.menuBar1 = menuBar1;
	}

	public void setMenuHome(MenuItem menuHome) {
		this.menuHome = menuHome;
	}

	public void setMenuSetupMaster(MenuItem menuSetupMaster) {
		this.menuSetupMaster = menuSetupMaster;
	}

	public void setMenuSetupMasterVendor(MenuItem menuSetupMasterVendor) {
		this.menuSetupMasterVendor = menuSetupMasterVendor;
	}

	public void setMenuSetupMasterCustomer(MenuItem menuSetupMasterCustomer) {
		this.menuSetupMasterCustomer = menuSetupMasterCustomer;
	}

	public void setMenuSetupMasterCustomer1(MenuItem menuSetupMasterCustomer1) {
		this.menuSetupMasterCustomer1 = menuSetupMasterCustomer1;
	}

	public void setMenuSetupMasterCustomerSeparator1(
			MenuItem menuSetupMasterCustomerSeparator1) {
		this.menuSetupMasterCustomerSeparator1 = menuSetupMasterCustomerSeparator1;
	}

	public void setMenuSetupMasterCustomerGroup(
			MenuItem menuSetupMasterCustomerGroup) {
		this.menuSetupMasterCustomerGroup = menuSetupMasterCustomerGroup;
	}

	public void setMenuSetupMasterCustomerSubGroup(
			MenuItem menuSetupMasterCustomerSubGroup) {
		this.menuSetupMasterCustomerSubGroup = menuSetupMasterCustomerSubGroup;
	}

	public void setMenuSetupMasterCustomerSeparator2(
			MenuItem menuSetupMasterCustomerSeparator2) {
		this.menuSetupMasterCustomerSeparator2 = menuSetupMasterCustomerSeparator2;
	}

	public void setMenuSetupMasterCustomerArea(MenuItem menuSetupMasterCustomerArea) {
		this.menuSetupMasterCustomerArea = menuSetupMasterCustomerArea;
	}

	public void setMenuSetupMasterCustomerSubArea(
			MenuItem menuSetupMasterCustomerSubArea) {
		this.menuSetupMasterCustomerSubArea = menuSetupMasterCustomerSubArea;
	}

	public void setMenuSetupMasterProduct(MenuItem menuSetupMasterProduct) {
		this.menuSetupMasterProduct = menuSetupMasterProduct;
	}

	public void setMenuWarehouseStock(MenuItem menuWarehouseStock) {
		this.menuWarehouseStock = menuWarehouseStock;
	}

	public void setMenuPurchaseOrder(MenuItem menuPurchaseOrder) {
		this.menuPurchaseOrder = menuPurchaseOrder;
	}

	public void setMenuSalesOrder(MenuItem menuSalesOrder) {
		this.menuSalesOrder = menuSalesOrder;
	}

	public void setMenuUtilitas(MenuItem menuUtilitas) {
		this.menuUtilitas = menuUtilitas;
	}

	public void setfProductgroupdeptJpaService(
			FProductgroupdeptJpaService fProductgroupdeptJpaService) {
		this.fProductgroupdeptJpaService = fProductgroupdeptJpaService;
	}

	public void setfProductgroupdivisiJpaService(
			FProductgroupdivisiJpaService fProductgroupdivisiJpaService) {
		this.fProductgroupdivisiJpaService = fProductgroupdivisiJpaService;
	}

	public void setfProductgroupJpaService(
			FProductgroupJpaService fProductgroupJpaService) {
		this.fProductgroupJpaService = fProductgroupJpaService;
	}

	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}

	public void setfSalesmanJpaService(FSalesmanJpaService fSalesmanJpaService) {
		this.fSalesmanJpaService = fSalesmanJpaService;
	}

	public void setfStockJpaService(FStockJpaService fStockJpaService) {
		this.fStockJpaService = fStockJpaService;
	}

	public void setfSubareaJpaService(FSubareaJpaService fSubareaJpaService) {
		this.fSubareaJpaService = fSubareaJpaService;
	}

	public void setFtArpaymentdJpaService(
			FtArpaymentdJpaService ftArpaymentdJpaService) {
		this.ftArpaymentdJpaService = ftArpaymentdJpaService;
	}

	public void setFtArpaymenthJpaService(
			FtArpaymenthJpaService ftArpaymenthJpaService) {
		this.ftArpaymenthJpaService = ftArpaymenthJpaService;
	}

	public void setFtOpnamedJpaService(FtOpnamedJpaService ftOpnamedJpaService) {
		this.ftOpnamedJpaService = ftOpnamedJpaService;
	}

	public void setFtOpnamehJpaService(FtOpnamehJpaService ftOpnamehJpaService) {
		this.ftOpnamehJpaService = ftOpnamehJpaService;
	}

	public void setFtPurchasedJpaService(FtPurchasedJpaService ftPurchasedJpaService) {
		this.ftPurchasedJpaService = ftPurchasedJpaService;
	}

	public void setFtPurchasehJpaService(FtPurchasehJpaService ftPurchasehJpaService) {
		this.ftPurchasehJpaService = ftPurchasehJpaService;
	}

	public void setFtSalesdJpaService(FtSalesdJpaService ftSalesdJpaService) {
		this.ftSalesdJpaService = ftSalesdJpaService;
	}

	public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
		this.ftSaleshJpaService = ftSaleshJpaService;
	}


	public void setFtStocktransferdJpaService(
			FtStocktransferdJpaService ftStocktransferdJpaService) {
		this.ftStocktransferdJpaService = ftStocktransferdJpaService;
	}

	public void setFtStocktransferhJpaService(
			FtStocktransferhJpaService ftStocktransferhJpaService) {
		this.ftStocktransferhJpaService = ftStocktransferhJpaService;
	}

	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
	}

	public void setfWarehouseJpaService(FWarehouseJpaService fWarehouseJpaService) {
		this.fWarehouseJpaService = fWarehouseJpaService;
	}

	public SysvarHelper getSysvarHelper() {
		return sysvarHelper;
	}

	public void setSysvarHelper(SysvarHelper sysvarHelper) {
		this.sysvarHelper = sysvarHelper;
	}

	public User getUserActive() {
		return userActive;
	}

	public void setUserActive(User userActive) {
		this.userActive = userActive;
	}


	public FtPricehJpaService getFtPricehJpaService() {
		return ftPricehJpaService;
	}

	public FtPricedJpaService getFtPricedJpaService() {
		return ftPricedJpaService;
	}

	public void setFtPricehJpaService(FtPricehJpaService ftPricehJpaService) {
		this.ftPricehJpaService = ftPricehJpaService;
	}

	public void setFtPricedJpaService(FtPricedJpaService ftPricedJpaService) {
		this.ftPricedJpaService = ftPricedJpaService;
	}

	public FDivisionJpaService getfDivisionJpaService() {
		return fDivisionJpaService;
	}

	public FtSaleshRekapTampunganJpaService getFtSaleshRekapTampunganJpaService() {
		return ftSaleshRekapTampunganJpaService;
	}

	public void setfDivisionJpaService(FDivisionJpaService fDivisionJpaService) {
		this.fDivisionJpaService = fDivisionJpaService;
	}

	public void setFtSaleshRekapTampunganJpaService(
			FtSaleshRekapTampunganJpaService ftSaleshRekapTampunganJpaService) {
		this.ftSaleshRekapTampunganJpaService = ftSaleshRekapTampunganJpaService;
	}

	public BankJpaService getBankJpaService() {
		return bankJpaService;
	}

	public BukugiroJpaService getBukugiroJpaService() {
		return bukugiroJpaService;
	}

	public BukutransferJpaService getBukutransferJpaService() {
		return bukutransferJpaService;
	}

	public MenuItem getMenuSetupMasterSysvar() {
		return menuSetupMasterSysvar;
	}

	public MenuItem getMenuSetupMasterUser() {
		return menuSetupMasterUser;
	}

	public MenuItem getMenuSetupMasterSeparator1() {
		return menuSetupMasterSeparator1;
	}

	public MenuItem getMenuSetupMasterSalesman() {
		return menuSetupMasterSalesman;
	}

	public MenuItem getMenuSetupMasterProduct1() {
		return menuSetupMasterProduct1;
	}

	public MenuItem getMenuSetupMasterProductSeparator1() {
		return menuSetupMasterProductSeparator1;
	}

	public MenuItem getMenuSetupMasterProductGroup() {
		return menuSetupMasterProductGroup;
	}

	public MenuItem getMenuSetupMasterProductGroupDept() {
		return menuSetupMasterProductGroupDept;
	}

	public MenuItem getMenuSetupMasterProductGroupDivisi() {
		return menuSetupMasterProductGroupDivisi;
	}

	public MenuItem getMenuSetupMasterSeparator2() {
		return menuSetupMasterSeparator2;
	}

	public MenuItem getMenuSetupMasterWarehouse() {
		return menuSetupMasterWarehouse;
	}

	public MenuItem getMenuWarehouseStockTransfer() {
		return menuWarehouseStockTransfer;
	}

	public MenuItem getMenuWarehouseStockOpname() {
		return menuWarehouseStockOpname1;
	}

	public MenuItem getMenuWarehouseStockSeparator1() {
		return menuWarehouseStockSeparator1;
	}

	public MenuItem getMenuWarehouseLapSaldoStock() {
		return menuWarehouseLapSaldoStock;
	}

	public MenuItem getMenuWarehouseLapPriceList() {
		return menuWarehouseLapPriceList;
	}

	public MenuItem getMenuWarehouseLapMutasiStok() {
		return menuWarehouseLapMutasiStok;
	}

	public MenuItem getMenuPurchaseOrderIncomingStock() {
		return menuPurchaseOrderIncomingStock;
	}

	public MenuItem getMenuPurchaseOrderRepIncomingStock() {
		return menuPurchaseOrderRepIncomingStock;
	}

	public MenuItem getMenuPurchaseOrderSeparator1() {
		return menuPurchaseOrderSeparator1;
	}

	public MenuItem getMenuPurchaseOrderMrv() {
		return menuPurchaseOrderMrv;
	}

	public MenuItem getMenuPurchaseOrderRepMrv() {
		return menuPurchaseOrderRepMrv;
	}

	public MenuItem getMenuSalesOrder1() {
		return menuSalesOrder1;
	}

	public MenuItem getMenuSalesOrderPackingList() {
		return menuSalesOrderPackingList;
	}

	public MenuItem getMenuSalesOrderRep() {
		return menuSalesOrderRep;
	}


	public MenuItem getMenuSalesOrderSeparator1() {
		return menuSalesOrderSeparator1;
	}

	public MenuItem getMenuSalesOrderSalesReturn() {
		return menuSalesOrderSalesReturn;
	}

	public MenuItem getMenuSalesOrderRepSalesReturn() {
		return menuSalesOrderRepSalesReturn;
	}

	public MenuItem getMenuSalesOrderSeparator2() {
		return menuSalesOrderSeparator2;
	}

	public MenuItem getMenuSalesOrderArPayment() {
		return menuSalesOrderArPayment;
	}



	public MenuItem getMenuProses() {
		return menuProses;
	}

	public MenuItem getMenuProsesAkhirhari() {
		return menuProsesAkhirhari;
	}

	public MenuItem getMenuProsesSeparator1() {
		return menuProsesSeparator1;
	}

	public MenuItem getMenuProsesAkhirtahun() {
		return menuProsesAkhirtahun;
	}

	public MenuItem getMenuUtilitasPerbaikan() {
		return menuUtilitasPerbaikan;
	}

	public MenuItem getMenuUtilitasPerbaikanSaldoStock() {
		return menuUtilitasPerbaikanSaldoStock;
	}

	public MenuItem getMenuHelp() {
		return menuHelp;
	}

	public MenuItem getMenuAbout() {
		return menuAbout;
	}

	public void setBankJpaService(BankJpaService bankJpaService) {
		this.bankJpaService = bankJpaService;
	}

	public void setBukugiroJpaService(BukugiroJpaService bukugiroJpaService) {
		this.bukugiroJpaService = bukugiroJpaService;
	}

	public void setBukutransferJpaService(
			BukutransferJpaService bukutransferJpaService) {
		this.bukutransferJpaService = bukutransferJpaService;
	}

	public void setMenuSetupMasterSysvar(MenuItem menuSetupMasterSysvar) {
		this.menuSetupMasterSysvar = menuSetupMasterSysvar;
	}

	public void setMenuSetupMasterUser(MenuItem menuSetupMasterUser) {
		this.menuSetupMasterUser = menuSetupMasterUser;
	}

	public void setMenuSetupMasterSeparator1(MenuItem menuSetupMasterSeparator1) {
		this.menuSetupMasterSeparator1 = menuSetupMasterSeparator1;
	}

	public void setMenuSetupMasterSalesman(MenuItem menuSetupMasterSalesman) {
		this.menuSetupMasterSalesman = menuSetupMasterSalesman;
	}

	public void setMenuSetupMasterProduct1(MenuItem menuSetupMasterProduct1) {
		this.menuSetupMasterProduct1 = menuSetupMasterProduct1;
	}

	public void setMenuSetupMasterProductSeparator1(
			MenuItem menuSetupMasterProductSeparator1) {
		this.menuSetupMasterProductSeparator1 = menuSetupMasterProductSeparator1;
	}

	public void setMenuSetupMasterProductGroup(MenuItem menuSetupMasterProductGroup) {
		this.menuSetupMasterProductGroup = menuSetupMasterProductGroup;
	}

	public void setMenuSetupMasterProductGroupDept(
			MenuItem menuSetupMasterProductGroupDept) {
		this.menuSetupMasterProductGroupDept = menuSetupMasterProductGroupDept;
	}

	public void setMenuSetupMasterProductGroupDivisi(
			MenuItem menuSetupMasterProductGroupDivisi) {
		this.menuSetupMasterProductGroupDivisi = menuSetupMasterProductGroupDivisi;
	}

	public void setMenuSetupMasterSeparator2(MenuItem menuSetupMasterSeparator2) {
		this.menuSetupMasterSeparator2 = menuSetupMasterSeparator2;
	}

	public void setMenuSetupMasterWarehouse(MenuItem menuSetupMasterWarehouse) {
		this.menuSetupMasterWarehouse = menuSetupMasterWarehouse;
	}

	public void setMenuWarehouseStockTransfer(MenuItem menuWarehouseStockTransfer) {
		this.menuWarehouseStockTransfer = menuWarehouseStockTransfer;
	}

	public void setMenuWarehouseStockOpname(MenuItem menuWarehouseStockOpname) {
		this.menuWarehouseStockOpname1 = menuWarehouseStockOpname;
	}

	public void setMenuWarehouseStockSeparator1(
			MenuItem menuWarehouseStockSeparator1) {
		this.menuWarehouseStockSeparator1 = menuWarehouseStockSeparator1;
	}

	public void setMenuWarehouseLapSaldoStock(MenuItem menuWarehouseLapSaldoStock) {
		this.menuWarehouseLapSaldoStock = menuWarehouseLapSaldoStock;
	}

	public void setMenuWarehouseLapPriceList(MenuItem menuWarehouseLapPriceList) {
		this.menuWarehouseLapPriceList = menuWarehouseLapPriceList;
	}

	public void setMenuWarehouseLapMutasiStok(MenuItem menuWarehouseLapMutasiStok) {
		this.menuWarehouseLapMutasiStok = menuWarehouseLapMutasiStok;
	}

	public void setMenuPurchaseOrderIncomingStock(
			MenuItem menuPurchaseOrderIncomingStock) {
		this.menuPurchaseOrderIncomingStock = menuPurchaseOrderIncomingStock;
	}

	public void setMenuPurchaseOrderRepIncomingStock(
			MenuItem menuPurchaseOrderRepIncomingStock) {
		this.menuPurchaseOrderRepIncomingStock = menuPurchaseOrderRepIncomingStock;
	}

	public void setMenuPurchaseOrderSeparator1(MenuItem menuPurchaseOrderSeparator1) {
		this.menuPurchaseOrderSeparator1 = menuPurchaseOrderSeparator1;
	}

	public void setMenuPurchaseOrderMrv(MenuItem menuPurchaseOrderMrv) {
		this.menuPurchaseOrderMrv = menuPurchaseOrderMrv;
	}

	public void setMenuPurchaseOrderRepMrv(MenuItem menuPurchaseOrderRepMrv) {
		this.menuPurchaseOrderRepMrv = menuPurchaseOrderRepMrv;
	}

	public void setMenuSalesOrder1(MenuItem menuSalesOrder1) {
		this.menuSalesOrder1 = menuSalesOrder1;
	}

	public void setMenuSalesOrderPackingList(MenuItem menuSalesOrderPackingList) {
		this.menuSalesOrderPackingList = menuSalesOrderPackingList;
	}

	public void setMenuSalesOrderRep(MenuItem menuSalesOrderRep) {
		this.menuSalesOrderRep = menuSalesOrderRep;
	}


	public void setMenuSalesOrderSeparator1(MenuItem menuSalesOrderSeparator1) {
		this.menuSalesOrderSeparator1 = menuSalesOrderSeparator1;
	}

	public void setMenuSalesOrderSalesReturn(MenuItem menuSalesOrderSalesReturn) {
		this.menuSalesOrderSalesReturn = menuSalesOrderSalesReturn;
	}

	public void setMenuSalesOrderRepSalesReturn(
			MenuItem menuSalesOrderRepSalesReturn) {
		this.menuSalesOrderRepSalesReturn = menuSalesOrderRepSalesReturn;
	}

	public void setMenuSalesOrderSeparator2(MenuItem menuSalesOrderSeparator2) {
		this.menuSalesOrderSeparator2 = menuSalesOrderSeparator2;
	}

	public void setMenuSalesOrderArPayment(MenuItem menuSalesOrderArPayment) {
		this.menuSalesOrderArPayment = menuSalesOrderArPayment;
	}

	public void setMenuProses(MenuItem menuProses) {
		this.menuProses = menuProses;
	}

	public void setMenuProsesAkhirhari(MenuItem menuProsesAkhirhari) {
		this.menuProsesAkhirhari = menuProsesAkhirhari;
	}

	public void setMenuProsesSeparator1(MenuItem menuProsesSeparator1) {
		this.menuProsesSeparator1 = menuProsesSeparator1;
	}

	public void setMenuProsesAkhirtahun(MenuItem menuProsesAkhirtahun) {
		this.menuProsesAkhirtahun = menuProsesAkhirtahun;
	}

	public void setMenuUtilitasPerbaikan(MenuItem menuUtilitasPerbaikan) {
		this.menuUtilitasPerbaikan = menuUtilitasPerbaikan;
	}

	public void setMenuUtilitasPerbaikanSaldoStock(
			MenuItem menuUtilitasPerbaikanSaldoStock) {
		this.menuUtilitasPerbaikanSaldoStock = menuUtilitasPerbaikanSaldoStock;
	}

	public void setMenuHelp(MenuItem menuHelp) {
		this.menuHelp = menuHelp;
	}

	public void setMenuAbout(MenuItem menuAbout) {
		this.menuAbout = menuAbout;
	}



	public MenuItem getMenuUtilitasTheme() {
		return menuUtilitasTheme;
	}

	public MenuItem getMenuUtilitasThemeGantiTheme() {
		return menuUtilitasThemeGantiTheme;
	}

	


	public void setMenuSalesPrestasiKerja(MenuItem menuSalesPrestasiKerja) {
		this.menuSalesPrestasiKerja = menuSalesPrestasiKerja;
	}

	public void setMenuUtilitasTheme(MenuItem menuUtilitasTheme) {
		this.menuUtilitasTheme = menuUtilitasTheme;
	}

	public void setMenuUtilitasThemeGantiTheme(MenuItem menuUtilitasThemeGantiTheme) {
		this.menuUtilitasThemeGantiTheme = menuUtilitasThemeGantiTheme;
	}

	public FParamDiskonJpaService getfParamDiskonJpaService() {
		return fParamDiskonJpaService;
	}

	public MenuItem getMenuSetupMasterDiskon() {
		return menuSetupMasterDiskon;
	}

	public MenuItem getMenuSetupMasterDiskonParameterDiskon() {
		return menuSetupMasterDiskonParameterDiskon;
	}


	public void setfParamDiskonJpaService(
			FParamDiskonJpaService fParamDiskonJpaService) {
		this.fParamDiskonJpaService = fParamDiskonJpaService;
	}

	public void setMenuSetupMasterDiskon(MenuItem menuSetupMasterDiskon) {
		this.menuSetupMasterDiskon = menuSetupMasterDiskon;
	}

	public void setMenuSetupMasterDiskonParameterDiskon(
			MenuItem menuSetupMasterDiskonParameterDiskon) {
		this.menuSetupMasterDiskonParameterDiskon = menuSetupMasterDiskonParameterDiskon;
	}


	public MenuItem getMenuSalesOrderArPaymentRep() {
		return menuSalesOrderArPaymentRep;
	}
	
	public MenuItem getMenuSalesOrderArpaymentRepSaldoPiutang() {
		return menuSalesOrderArpaymentRepSaldoPiutang;
	}

	public MenuItem getMenuSalesOrderArpaymentRepPembayaranPiutang() {
		return menuSalesOrderArpaymentRepPembayaranPiutang;
	}

	public void setMenuSalesOrderArPaymentRep(MenuItem menuSalesOrderArPaymentRep) {
		this.menuSalesOrderArPaymentRep = menuSalesOrderArPaymentRep;
	}

	public void setMenuSalesOrderArpaymentRepSaldoPiutang(
			MenuItem menuSalesOrderArpaymentRepSaldoPiutang) {
		this.menuSalesOrderArpaymentRepSaldoPiutang = menuSalesOrderArpaymentRepSaldoPiutang;
	}

	public void setMenuSalesOrderArpaymentRepPembayaranPiutang(
			MenuItem menuSalesOrderArpaymentRepPembayaranPiutang) {
		this.menuSalesOrderArpaymentRepPembayaranPiutang = menuSalesOrderArpaymentRepPembayaranPiutang;
	}

	public UpdateSystemHelper getUpdateSystemHelper() {
		return updateSystemHelper;
	}

	public void setUpdateSystemHelper(UpdateSystemHelper updateSystemHelper) {
		this.updateSystemHelper = updateSystemHelper;
	}



	public void setMenuSalesOrderPackingListRekapPerhari(
			MenuItem menuSalesOrderPackingListRekapPerhari) {
		this.menuSalesOrderPackingListRekapPerhari = menuSalesOrderPackingListRekapPerhari;
	}


	public Panel getPanelLogin() {
		return panelLogin;
	}

	public MenuItem getMenuSalesToExel() {
		return menuSalesToExel;
	}

	public MenuItem getMenuUtilitasSeparator1() {
		return menuUtilitasSeparator1;
	}

	public MenuItem getMenuUtilitasExportImport() {
		return menuUtilitasExportImport;
	}

	public MenuItem getMenuUtilitasExportImportSipAndroid() {
		return menuUtilitasExportImportSipAndroid;
	}

	public void setPanelLogin(Panel panelLogin) {
		this.panelLogin = panelLogin;
	}

	public void setMenuSalesToExel(MenuItem menuSalesToExel) {
		this.menuSalesToExel = menuSalesToExel;
	}

	public void setMenuUtilitasSeparator1(MenuItem menuUtilitasSeparator1) {
		this.menuUtilitasSeparator1 = menuUtilitasSeparator1;
	}

	public void setMenuUtilitasExportImport(MenuItem menuUtilitasExportImport) {
		this.menuUtilitasExportImport = menuUtilitasExportImport;
	}

	public void setMenuUtilitasExportImportSipAndroid(
			MenuItem menuUtilitasExportImportSipAndroid) {
		this.menuUtilitasExportImportSipAndroid = menuUtilitasExportImportSipAndroid;
	}
	public Panel getWorkspace1() {
		return workspace1;
	}

	public void setWorkspace1(Panel workspace1) {
		this.workspace1 = workspace1;
	}

	public Button getBtnUserId() {
		return btnUserId;
	}

	public void setBtnUserId(Button btnUserId) {
		this.btnUserId = btnUserId;
	}

	public Button getBtnUserSignOut() {
		return btnUserSignOut;
	}

	public void setBtnUserSignOut(Button btnUserSignOut) {
		this.btnUserSignOut = btnUserSignOut;
	}

	public Button getBtnSystemCalender() {
		return btnSystemCalender;
	}

	public void setBtnSystemCalender(Button btnSystemCalender) {
		this.btnSystemCalender = btnSystemCalender;
	}

	
	public FtPriceAlthJpaService getFtPriceAlthJpaService() {
		return ftPriceAlthJpaService;
	}

	public FtPriceAltdJpaService getFtPriceAltdJpaService() {
		return ftPriceAltdJpaService;
	}

	public void setFtPriceAlthJpaService(FtPriceAlthJpaService ftPriceAlthJpaService) {
		this.ftPriceAlthJpaService = ftPriceAlthJpaService;
	}

	public void setFtPriceAltdJpaService(FtPriceAltdJpaService ftPriceAltdJpaService) {
		this.ftPriceAltdJpaService = ftPriceAltdJpaService;
	}


	public FPromoJpaService2 getFpromoJpaService() {
		return fpromoJpaService;
	}

	public void setFpromoJpaService(FPromoJpaService2 fpromoJpaService) {
		this.fpromoJpaService = fpromoJpaService;
	}


	

	public FtSalesdPromoTprbJpaService getFtSalesdPromoTprbJpaService() {
		return ftSalesdPromoTprbJpaService;
	}

	public FtSalesdPromoTpruDiscJpaService getFtSalesdPromoTpruDiscJpaService() {
		return ftSalesdPromoTpruDiscJpaService;
	}

	public FtSalesdPromoTpruCbJpaService getFtSalesdPromoTpruCbJpaService() {
		return ftSalesdPromoTpruCbJpaService;
	}

	public void setFtSalesdPromoTprbJpaService(
			FtSalesdPromoTprbJpaService ftSalesdPromoTprbJpaService) {
		this.ftSalesdPromoTprbJpaService = ftSalesdPromoTprbJpaService;
	}

	public void setFtSalesdPromoTpruDiscJpaService(
			FtSalesdPromoTpruDiscJpaService ftSalesdPromoTpruDiscJpaService) {
		this.ftSalesdPromoTpruDiscJpaService = ftSalesdPromoTpruDiscJpaService;
	}

	public void setFtSalesdPromoTpruCbJpaService(
			FtSalesdPromoTpruCbJpaService ftSalesdPromoTpruCbJpaService) {
		this.ftSalesdPromoTpruCbJpaService = ftSalesdPromoTpruCbJpaService;
	}


	public FParamDiskonItemJpaService getfParamDiskonItemJpaService() {
		return fParamDiskonItemJpaService;
	}

	public void setfParamDiskonItemJpaService(
			FParamDiskonItemJpaService fParamDiskonItemJpaService) {
		this.fParamDiskonItemJpaService = fParamDiskonItemJpaService;
	}


	public FtAdjusthJpaService getFtAdjusthJpaService() {
		return ftAdjusthJpaService;
	}

	public void setFtAdjusthJpaService(FtAdjusthJpaService ftAdjusthJpaService) {
		this.ftAdjusthJpaService = ftAdjusthJpaService;
	}

  
	public FtAdjustdJpaService getFtAdjustdJpaService() {
		return ftAdjustdJpaService;
	}

	public void setFtAdjustdJpaService(FtAdjustdJpaService ftAdjustdJpaService) {
		this.ftAdjustdJpaService = ftAdjustdJpaService;
	}


	public SCustomerJpaService getsCustomerJpaService() {
		return sCustomerJpaService;
	}

	public STeknisiJpaService getsTeknisiJpaService() {
		return sTeknisiJpaService;
	}

	public StServiceJpaService getStServiceJpaService() {
		return stServiceJpaService;
	}

	public void setsCustomerJpaService(SCustomerJpaService sCustomerJpaService) {
		this.sCustomerJpaService = sCustomerJpaService;
	}

	public void setsTeknisiJpaService(STeknisiJpaService sTeknisiJpaService) {
		this.sTeknisiJpaService = sTeknisiJpaService;
	}

	public void setStServiceJpaService(StServiceJpaService stServiceJpaService) {
		this.stServiceJpaService = stServiceJpaService;
	}


	public SMerkJpaService getsMerkJpaService() {
		return sMerkJpaService;
	}

	public void setsMerkJpaService(SMerkJpaService sMerkJpaService) {
		this.sMerkJpaService = sMerkJpaService;
	}


	public FParamDiskonItemVendorJpaService getfParamDiskonItemVendorJpaService() {
		return fParamDiskonItemVendorJpaService;
	}

	public void setfParamDiskonItemVendorJpaService(
			FParamDiskonItemVendorJpaService fParamDiskonItemVendorJpaService) {
		this.fParamDiskonItemVendorJpaService = fParamDiskonItemVendorJpaService;
	}


	public FtappaymenthJpaService getFtappaymenthJpaService() {
		return ftappaymenthJpaService;
	}

	public FtappaymentdJpaService getFtappaymentdJpaService() {
		return ftappaymentdJpaService;
	}

	public void setFtappaymenthJpaService(
			FtappaymenthJpaService ftappaymenthJpaService) {
		this.ftappaymenthJpaService = ftappaymenthJpaService;
	}

	public void setFtappaymentdJpaService(
			FtappaymentdJpaService ftappaymentdJpaService) {
		this.ftappaymentdJpaService = ftappaymentdJpaService;
	}


	public LapAktifitasPromoListJpaService getLapAktifitasPromoListJpaService() {
		return lapAktifitasPromoListJpaService;
	}

	public void setLapAktifitasPromoListJpaService(
			LapAktifitasPromoListJpaService lapAktifitasPromoListJpaService) {
		this.lapAktifitasPromoListJpaService = lapAktifitasPromoListJpaService;
	}


	public PRegionJpaService getpRegionJpaService() {
		return pRegionJpaService;
	}

	public PAreaJpaService getpAreaJpaService() {
		return pAreaJpaService;
	}

	public PDistributorJpaService getpDistributorJpaService() {
		return pDistributorJpaService;
	}

	public FChannelJpaService getfChannelJpaService() {
		return fChannelJpaService;
	}

	public void setpRegionJpaService(PRegionJpaService pRegionJpaService) {
		this.pRegionJpaService = pRegionJpaService;
	}

	public void setpAreaJpaService(PAreaJpaService pAreaJpaService) {
		this.pAreaJpaService = pAreaJpaService;
	}

	public void setpDistributorJpaService(
			PDistributorJpaService pDistributorJpaService) {
		this.pDistributorJpaService = pDistributorJpaService;
	}

	public void setfChannelJpaService(FChannelJpaService fChannelJpaService) {
		this.fChannelJpaService = fChannelJpaService;
	}


	//HOT KEY
	private static final ShortcutAction PANELLOGIN_ENTER= new ShortcutAction("Enter",
			KeyCode.ENTER, null);	
	
	private static final Action[] ACTIONS = new Action[] {};
	private static final Action[] ACTIONS_PANELLOGIN = new Action[] { PANELLOGIN_ENTER };
	
	@Override
	public Action[] getActions(Object target, Object sender) {
		if (sender == getPanelLogin()) {
			return ACTIONS_PANELLOGIN;
		}
		return ACTIONS;
	}

	@Override
	public void handleAction(Action action, Object sender, Object target) {
		if (action==PANELLOGIN_ENTER){
			btnSignIn.click();
		}
		
	}

	

	
}
