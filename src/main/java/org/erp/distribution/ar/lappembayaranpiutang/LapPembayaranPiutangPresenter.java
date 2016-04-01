package org.erp.distribution.ar.lappembayaranpiutang;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LapPembayaranPiutangPresenter implements ClickListener{
	private LapPembayaranPiutangModel model;
	private LapPembayaranPiutangView view;

	public LapPembayaranPiutangPresenter(LapPembayaranPiutangModel model, LapPembayaranPiutangView view){
		this.model = model;
		this.view = view;
		initListener();
	}
	
	public void initListener(){
		view.getBtnPreview().addClickListener(this);
		view.getBtnClose().addClickListener(this);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnPreview()){
			preview();
//			previewNew();
		} else if (event.getButton()==view.getBtnClose()){
		}
	}
	
	private String strParamSJPenagihan = "";
	private String strParamTunaiKredit = "";
	private Date dateArpaymentdateFrom = null;
	private Date dateArpaymentdateTo = null;
	
	private String strParamSalesman = "";
	private String strParamCustomer = "";

	private String strParamProductgroup = "";
	private String strParamWarehouseId = "";
	private Date dateParamStockdate = null;
	private Integer paramSumSaldostock = 0;
	
	private String paramCompanyName = "W-DES";
	private String paramCompanyAddress = "Jl. Kauman Gang IV/A Malang";
	private String paramCompanyPhone = "Telp.082143574692";
	private String paramJudulLaporan = "LAPORAN SALES";
	
	
	public void resetParameters(){
		strParamSJPenagihan = "%";
		strParamTunaiKredit = "%";

		strParamSalesman = "%";
		strParamCustomer = "%";
		
		strParamProductgroup= "%";
		strParamWarehouseId = "%";
		dateParamStockdate = model.getTransaksiHelper().getCurrentTransDate();
		paramSumSaldostock = 0;
	}
	public void reloadParameter(){
		if (view.getComboGroup1().getValue().equals("T")) {
			strParamTunaiKredit  = "T";
		} else if (view.getComboGroup1().getValue().equals("K")) {
			strParamTunaiKredit  = "K";			
		}else {
			strParamTunaiKredit = "%";
		}
		
		strParamSalesman = "%";
		try{
			strParamSalesman = ((FSalesman) view.getComboGroup3().getConvertedValue()).getSpcode();
		} catch(Exception ex){}
		strParamCustomer = "%";
		try{
			strParamCustomer = ((FCustomer) view.getComboGroup4().getConvertedValue()).getCustno();
		} catch(Exception ex){}
		
		try{
			FWarehouse fWarehouse = new FWarehouse();
			fWarehouse = (FWarehouse) view.getComboGroup1().getConvertedValue();
			strParamWarehouseId = fWarehouse.getId().trim();
		} catch(Exception ex){}
		
		try{
			strParamSJPenagihan = "%" + view.getFieldSuratJalanPenagihan().getValue().trim() + "%";
		} catch(Exception ex){}
		try{
			dateArpaymentdateFrom = view.getDateField1From().getValue();
			dateArpaymentdateTo = view.getDateField1To().getValue();
		} catch(Exception ex){}
		try{
			FWarehouse fWarehouse = new FWarehouse();
			fWarehouse = (FWarehouse) view.getComboGroup1().getConvertedValue();
			strParamWarehouseId = fWarehouse.getId().trim();
		} catch(Exception ex){}
		try{
			FProductgroup fProductgroup = new FProductgroup();
			fProductgroup = (FProductgroup) view.getComboGroup2().getConvertedValue();
			strParamProductgroup = fProductgroup.getId().trim();
		} catch(Exception ex){}
		try{
			dateParamStockdate = view.getDateField1From().getValue();
		} catch(Exception ex){}
		//JIKA HANYA YANG ADA MUTASI
		if (view.getCheckBox1().getValue()==true) {			
			paramSumSaldostock=0;
		}else {
			paramSumSaldostock=-100000000;			
		}
		
	}
	public void preview(){
		paramCompanyName = model.getSysvarHelper().getCompanyNameFaktur();
		paramCompanyAddress = model.getSysvarHelper().getCompanyAddressFaktur();
		paramCompanyPhone = model.getSysvarHelper().getCompanyPhoneFaktur();
		
		
		resetParameters();
		reloadParameter();
		
		showPreview("/erp/distribution/reports/arpayment/pembayaranpiutang/pembayaranpiutang1.jasper", "pembayaranpiutang1");
		
	}
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		parameters.put("paramJudulLaporan", paramJudulLaporan);
		parameters.put("paramCompanyName", paramCompanyName);
		parameters.put("paramCompanyAddress", paramCompanyAddress);
		parameters.put("paramCompanyPhone", paramCompanyPhone);

		
		parameters.put("paramSJPenagihan", strParamSJPenagihan);
		parameters.put("paramTunaiKredit", strParamTunaiKredit);
		parameters.put("paramArpaymentdateFrom", dateArpaymentdateFrom);
		parameters.put("paramArpaymentdateTo", dateArpaymentdateTo);
		parameters.put("paramSalesman", strParamSalesman);
		parameters.put("paramCustomer", strParamCustomer);
		
//		parameters.put("paramWarehouseId",  strParamWarehouseId);
//		parameters.put("paramProductgroup", "%" +  strParamProductgroup  + "%");
//		
//		parameters.put("paramSumSaldostock", paramSumSaldostock);


		//CONNECTION
		final Connection con = new ReportJdbcConfigHelper().getConnection();
		
		
		StreamResource.StreamSource source = new StreamSource() {			
			@Override
			public InputStream getStream() {
				byte[] b = null;
				try {
					b = JasperRunManager.runReportToPdf(report, parameters, con);
				} catch (JRException ex) {
					System.out.println(ex);
				}
				return new ByteArrayInputStream(b);
			}
		};
		
		String fileName = "ar_bukupembayaran_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new__" + outputFilePath, false);
	
	} catch (JRException e) {
		e.printStackTrace();
	}
	
}
	
	public void previewNew(){
		
	}
}
