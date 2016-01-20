package org.erp.distribution.util.export_import.siptigadara;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.filefilter.NotFileFilter;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesdPK;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.util.ReportJdbcConfigHelper;
import org.vaadin.dialogs.ConfirmDialog;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class ExportImportSipTigaDaraPresenter implements ClickListener{
	private ExportImportSipTigaDaraModel model;
	private ExportImportSipTigaDaraView view;

	public ExportImportSipTigaDaraPresenter(ExportImportSipTigaDaraModel model, ExportImportSipTigaDaraView view){
		this.model = model;
		this.view = view;
		initListener();
	}
	
	public void initListener(){
		view.getBtnProcess().addClickListener(this);
		view.getBtnClose().addClickListener(this);
		
		view.getBtnExport().addClickListener(this);
		view.getBtnImport().addClickListener(this);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnProcess()){
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                    	
                    } else {
                    }
				}
			};
			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(), "Export", "Perbaikan stok akan melakukan perhitungan ulang! Lanjutkan proses kalkulasi?", 
					 "Oke", "Cancel", konfirmDialogListener);
			 
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
			
			
		} else if (event.getButton()==view.getBtnExport()){
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                    	try{
                    		exportSip();
                    	} catch(Exception ex){
                    		ex.printStackTrace();
                    	}
                    } else {
                    }
				}
			};
			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(), "Export", "Lanjutkan Export database.xml SipAndroid?", 
					 "Oke", "Cancel", konfirmDialogListener);
			 
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
			
		} else if (event.getButton()==view.getBtnImport()){
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                    	
                    	try{
                    		for (int i=0; i<100; i++){                    		
                    			String strFilePath = String.valueOf(i) + ".xml";
                    			try{
                    				importPenjualanSip0(model.getSysvarHelper().getPathSipAndroidTigaDara() + strFilePath);
//                    				importPenjualanSip0(strFilePath);
                    			} catch(Exception ex){}
                    		}
                    		
                    		Notification.show("Selesai Import Transaksi Penjulan dari SIP Android" , Notification.TYPE_TRAY_NOTIFICATION);
                    		
                    	} catch(Exception ex){}
                    	
                    	
                    } else {
                    }
				}
			};
			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(), "Import", "Lanjutkan Export PENJUALAN SipAndroid?", 
					 "Oke", "Cancel", konfirmDialogListener);
			 
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
			
		} else if (event.getButton()==view.getBtnClose()){
		}
	}
	
	public void exportSip() throws ParserConfigurationException, TransformerException{
		 //##### MKYOUNG SAMPLE
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("RuntimeSIP_Database");
			doc.appendChild(rootElement);
	 
			FWarehouse fWarehouse = new FWarehouse();
			fWarehouse = (FWarehouse) view.getComboGroup2().getValue();
			Date stockDate = view.getDateField1From().getValue();
			Iterator<FStock> iterFStock = model.getfStockJpaService().findAll(fWarehouse.getId(), (stockDate)).iterator();
			while (iterFStock.hasNext()){
				FStock fStock = new FStock();
				fStock = iterFStock.next();
				if (fStock.getFproductBean().getStatusactive() != null){
					if (fStock.getFproductBean().getStatusactive()==true){
				//##ELEMENT KEDUA
					// Barang elements
					Element barang = doc.createElement("Barang");
					rootElement.appendChild(barang);
			 
		//			// set attribute to Barang element'
		//			Attr attr = doc.createAttribute("id");
		//			attr.setValue("1");
		//			barang.setAttributeNode(attr);	 
					// shorten way
					// staff.setAttribute("id", "1");
		 
						Element kodeBarang = doc.createElement("id");
						kodeBarang.appendChild(doc.createTextNode(String.valueOf(fStock.getFproductBean().getPcode())));
						barang.appendChild(kodeBarang);
				 
						// Nama elements
						Element namaBarang = doc.createElement("Nama");
						namaBarang.appendChild(doc.createTextNode(String.valueOf(fStock.getFproductBean().getPname() + " " + fStock.getFproductBean().getPackaging())));
						barang.appendChild(namaBarang);
				 
						// Harga elements
						Double hargaBarangAfterPpn = fStock.getFproductBean().getSprice()/fStock.getFproductBean().getConvfact1() * 1.1;
						
						Element hargaBarang = doc.createElement("Harga");
						hargaBarang.appendChild(doc.createTextNode(String.valueOf(hargaBarangAfterPpn.intValue()) + ".00"));
						barang.appendChild(hargaBarang);
				 
						// Stock elements
						Element stockBarang = doc.createElement("stock");
						stockBarang.appendChild(doc.createTextNode(String.valueOf(fStock.getSaldoakhir()) + ".00"));
						barang.appendChild(stockBarang);
					}
				}
			}
	 
			Iterator<FCustomer> iterFCustomer = model.getfCustomerJpaService().findAll().iterator();
			while (iterFCustomer.hasNext()) {
				FCustomer fCustomer = new FCustomer();
				fCustomer = iterFCustomer.next();
		
				if (fCustomer.getStatusactive()==true){
					//##ELEMENT KEDUA
					Element customer = doc.createElement("Customer");
					rootElement.appendChild(customer);
			 
		//			// set attribute to Barang element
		//			Attr attr = doc.createAttribute("id");
		//			attr.setValue("1");
		//			barang.setAttributeNode(attr);	 
					// shorten way
					// staff.setAttribute("id", "1");
			
					// Barang elements
					Element kodeCustomer = doc.createElement("id");
					kodeCustomer.appendChild(doc.createTextNode(String.valueOf(fCustomer.getCustno())));
					customer.appendChild(kodeCustomer);
			 
					// Nama elements
					Element barcodeCustomer = doc.createElement("Barcode");
					barcodeCustomer.appendChild(doc.createTextNode(""));
					customer.appendChild(barcodeCustomer);
			 
					// Harga elements
					Element namaCustomer = doc.createElement("Nama");
					namaCustomer.appendChild(doc.createTextNode(String.valueOf(fCustomer.getCustname())));
					customer.appendChild(namaCustomer);
			 
					// Stock elements
					Element alamatCustomer = doc.createElement("Alamat");
					alamatCustomer.appendChild(doc.createTextNode(String.valueOf(fCustomer.getAddress1())));
					customer.appendChild(alamatCustomer);
				}
			
			}
			
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			
			
			
			StreamResult result = new StreamResult(new File(model.getSysvarHelper().getPathSipAndroidTigaDara() + "database.xml"));
//			StreamResult result = new StreamResult(new File("database.xml"));
	 
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);

			System.out.println("File saved!");
	 
			Notification.show("Export Selesai!", Notification.TYPE_TRAY_NOTIFICATION);
		
	}

	public void importPenjualanSip() throws ParserConfigurationException, IOException, SAXException{
	       	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = builderFactory.newDocumentBuilder();
	 
	        // lokasi file XML
	        File file = new File("perusahaan.xml");
	 
	        // konversi file menjadi Document
	        Document document = builder.parse(file);
	 
	        // mengambil tag perusahaan
	        Element perusahaan = (Element) document.getElementsByTagName("perusahaan").item(0);
	 
	        // mengambilkan tag karyawan
	        NodeList list = perusahaan.getElementsByTagName("karyawan");
	 
	        for (int i = 0; i < list.getLength(); i++) {
	            Element karyawan = (Element) list.item(i);
	 
	            System.out.println("NIP : " + karyawan.getAttribute("nip"));
	 
	            Node nama =  karyawan.getElementsByTagName("nama").item(0);
	            System.out.println("Nama : " + nama.getTextContent());
	 
	            Node email =  karyawan.getElementsByTagName("email").item(0);
	            System.out.println("Email : " + email.getTextContent());
	 
	            System.out.println("=====================================");
	        }
		
	        
	        File file0 = new File("0.xml");
	        Document document0 = builder.parse(file);
		
	}
	public void importPenjualanSip0(String strFilePath) throws ParserConfigurationException, IOException, SAXException{
       	model.itemHeader = new FtSalesh();
       	
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
 
        // lokasi file XML
        File file = new File(strFilePath);
        
        Document document = builder.parse(file);
        Element transaksi = (Element) document.getElementsByTagName("Transaksi").item(0);
        NodeList listCustomer = transaksi.getElementsByTagName("Customer");
 
        for (int i = 0; i < listCustomer.getLength(); i++) {
        	
            Element customer = (Element) listCustomer.item(i);  
            Node kodeCustomer =  customer.getElementsByTagName("id").item(0);
 
            Node namaCustomer =  customer.getElementsByTagName("Nama").item(0);
 
            Node alamatCustomer =  customer.getElementsByTagName("Alamat").item(0);

            Node kreditCustomer =  customer.getElementsByTagName("kredit").item(0);

            Node duedateCustomer =  customer.getElementsByTagName("tgl_jatuh_tempo").item(0);
            
            //SET KREDIT
            model.itemHeader.setRefno((long) 0);
            
            model.itemHeader.setOrderno(model.getTransaksiHelper().getNextFtSaleshRefno());
            model.itemHeader.setInvoiceno("");    		
            model.itemHeader.setOrderdate(model.getTransaksiHelper().getCurrentTransDate());
            model.itemHeader.setInvoicedate(model.getTransaksiHelper().getCurrentTransDate());
    		
			Boolean kredit = false;
			model.itemHeader.setTunaikredit("T");
			model.itemHeader.setTop(0);    		
			model.itemHeader.setDuedate(model.getTransaksiHelper().getCurrentTransDate());
			if (Boolean.parseBoolean(kreditCustomer.getTextContent())==true) {
				model.itemHeader.setTunaikredit("K");
				model.itemHeader.setTop(7);
	    		Calendar cal = Calendar.getInstance();
	    		cal.setTime(model.getTransaksiHelper().getCurrentTransDate());
	    		cal.add(Calendar.DATE, 7);
	    		model.itemHeader.setDuedate(cal.getTime());				
			}
			
			model.itemHeader.setAmount(0.0);
			model.itemHeader.setAmountpay(0.0);
			model.itemHeader.setDisc(0.0);
			model.itemHeader.setDisc1(0.0);
			model.itemHeader.setDisc2(0.0);
			model.itemHeader.setPpnpercent(10.0);
			model.itemHeader.setSaldo(false);
			model.itemHeader.setPrintcounter(0);
			model.itemHeader.setTipefaktur("F");

			model.itemHeader.setAmountafterdisc(0.0);
			model.itemHeader.setAmountafterdiscafterppn(0.0);
			model.itemHeader.setEndofday(false);
			model.itemHeader.setEntrydate(model.getTransaksiHelper().getCurrentTransDate());
    		
    		FSalesman fSalesman = new FSalesman();
    		fSalesman = (FSalesman) view.getComboGroup1().getValue();
    		model.itemHeader.setFsalesmanBean(fSalesman);
    		
    		FCustomer fCustomer = new FCustomer();
    		fCustomer = model.getfCustomerJpaService().findAllByCustno(kodeCustomer.getTextContent()).get(0);
    		model.itemHeader.setFcustomerBean(fCustomer);
    		
    		FWarehouse fWarehouse = new FWarehouse();
    		fWarehouse = (FWarehouse) view.getComboGroup2().getValue();
    		model.itemHeader.setFwarehouseBean(fWarehouse);
//    		
    		model.itemHeader.setTipejual(fSalesman.getSalestype());
            
    		model.getFtSaleshJpaService().createObject(model.itemHeader);
    		
        }

        //PERBAIKI REFNO
        String nomorOrder = model.itemHeader.getOrderno();
        model.itemHeader = new FtSalesh();
        model.itemHeader = model.getFtSaleshJpaService().findAllByOrderno(nomorOrder, "F").get(0);
        
        //MULAI LAGI DETAIL
        model.listDetail = new ArrayList<FtSalesd>();
        
        // mengambilkan tag karyawan
        NodeList listDetail = transaksi.getElementsByTagName("Detail");
         
        for (int i = 0; i < listDetail.getLength(); i++) {
        	FtSalesd ftSalesd = new FtSalesd();
        	
            Element detail = (Element) listDetail.item(i); 
//            System.out.println("NIP : " + customer.getAttribute("nip"));
 
            Node kodeBarang =  detail.getElementsByTagName("kode_barang").item(0);
            System.out.println("Kode Barang : " + kodeBarang.getTextContent());
 
            Node jumlahBarang =  detail.getElementsByTagName("jumlah").item(0);
            System.out.println("Jumlah : " + jumlahBarang.getTextContent());
 
            //ITEM HEADER
            ftSalesd.setFtsaleshBean(model.itemHeader);
            
            FProduct fProduct = new FProduct();            
            try{
	            fProduct = model.getfProductJpaService().findAllByPcode(String.valueOf(kodeBarang.getTextContent()).trim()).get(0);
	            ftSalesd.setFproductBean(fProduct);            
	            ftSalesd.setSprice(fProduct.getSprice());
	            System.out.println("Produk : " + fProduct.getPcode() + fProduct.getPname() + fProduct.getPackaging());
            } catch(Exception ex){}

    		FtSalesdPK id = new FtSalesdPK();
    		id.setRefno(model.itemHeader.getRefno());
    		id.setId(fProduct.getId());
    		id.setFreegood(false);            
    		ftSalesd.setId(id);
    		
    		ftSalesd.setNourut(i);
    		
    		double doubleQty = Double.valueOf(jumlahBarang.getTextContent());
    		int intQty = (int) doubleQty;    		
            ftSalesd.setQty(intQty);
            ftSalesd.setDisc1(0.0);
            ftSalesd.setDisc2(0.0);
            ftSalesd.setDisc1rp(0.0);
            ftSalesd.setDisc1rpafterppn(0.0);
            ftSalesd.setDisc2rp(0.0);
            ftSalesd.setDisc2rpafterppn(0.0);
            ftSalesd.setPromo(false);
            
            Double hargaPerPcs = fProduct.getSprice()/fProduct.getConvfact1();
            
            ftSalesd.setSubtotal(hargaPerPcs * intQty);
            ftSalesd.setSubtotalafterdisc(ftSalesd.getSubtotal());
            ftSalesd.setSubtotalafterppn(ftSalesd.getSubtotal()*1.1);
            ftSalesd.setSubtotalafterdiscafterppn(ftSalesd.getSubtotal()*1.1);

            

            model.getFtSalesdJpaService().updateObject(ftSalesd);
            model.listDetail.add(ftSalesd);
            
            System.out.println("===================================== ");
        }
        
	        updateAndCalculateHeaderByItemDetil();
	        model.getFtSaleshJpaService().updateObject(model.itemHeader);
        
		
	}
	
	public Double getParamPpn(){
		return model.getTransaksiHelper().getParamPpn();
	}

	private double detailTotalAfterdiscWithPpn;
	private double detailTotalAfterdiscNoPpn;
	
	public void updateAndCalculateHeaderByItemDetil(){
		double sumDiscrp1 = 0;
		double sumDiscrp1afterppn = 0;		
		double sumDiscrp2 = 0 ;
		double sumDiscrp2afterppn = 0 ;
		
		double sumTotalNoPpn =0;
		double sumTotalWithPpn = 0;
		double sumTotalAfterdiscNoPpn =0;
		double sumTotalAfterdiscWithPpn = 0;		
		double record = 0;
		
		for (FtSalesd item : model.listDetail) {
			record++;
			FtSalesd itemDetil = new FtSalesd();
			itemDetil = item;

//			//ANGGAP DISKON 0 SEMUA
//			sumDiscrp1=0;
//			sumDiscrp1afterppn=0;
//			sumDiscrp2=0;
//			sumDiscrp2afterppn=0;
			
			sumDiscrp1 += item.getDisc1rp();
			sumDiscrp1afterppn += item.getDisc1rpafterppn();
			sumDiscrp2 += item.getDisc2rp();
			sumDiscrp2afterppn += item.getDisc2rpafterppn();
			
			sumTotalNoPpn += item.getSubtotal();
			sumTotalWithPpn += item.getSubtotalafterppn();
			sumTotalAfterdiscNoPpn += item.getSubtotalafterdisc();
			sumTotalAfterdiscWithPpn += item.getSubtotalafterdiscafterppn();
		}

		model.itemHeader.setAmount(sumTotalNoPpn);
		model.itemHeader.setAmountafterppn(sumTotalWithPpn);
		
		//DETIL
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMinimumIntegerDigits(0);

		detailTotalAfterdiscNoPpn = sumTotalAfterdiscNoPpn;
		detailTotalAfterdiscWithPpn = sumTotalAfterdiscWithPpn;
		
		//HEADER
		Double disc1persen = model.itemHeader.getDisc1()/100;
		Double disc1rp = sumTotalAfterdiscNoPpn * disc1persen;
		Double disc1rpafterppn = sumTotalAfterdiscWithPpn * disc1persen;
		Double disc2persen = model.itemHeader.getDisc2()/100;
		Double disc2rp = sumTotalAfterdiscNoPpn * disc2persen;
		Double disc2rpafterppn = sumTotalAfterdiscWithPpn * disc2persen;
		
		model.itemHeader.setDisc1rp((double) Math.round(disc1rp));
		model.itemHeader.setDisc2rp((double) Math.round(disc2rp));
		model.itemHeader.setDisc1rpafterppn((double) Math.round(disc1rpafterppn) );
		model.itemHeader.setDisc2rpafterppn((double) Math.round(disc2rpafterppn));

		//AKUMULASI DIS 1+2
		Double sumTotalAfterdisc12Noppn = sumTotalAfterdiscNoPpn - disc1rp - disc2rp;
		Double sumTotalAfterdisc12Withppn = sumTotalAfterdiscWithPpn - disc1rpafterppn - disc2rpafterppn;
		
		Double discpersen = model.itemHeader.getDisc()/100;
		Double discrp = sumTotalAfterdisc12Noppn * discpersen;
		Double discrpafterppn = sumTotalAfterdisc12Withppn * discpersen;
		model.itemHeader.setDiscrp((double) Math.round(discrp));
		model.itemHeader.setDiscrpafterppn((double) Math.round(discrpafterppn));
		
		//AKUMULAS DDPP DAN PPN
		Double sumTotalDpp = sumTotalAfterdisc12Noppn - discrp;
		Double sumTotalPpn = (sumTotalAfterdisc12Noppn - discrp)* getParamPpn()/100;
		
		model.itemHeader.setAmountafterdisc((double) Math.round(sumTotalDpp));
		model.itemHeader.setPpnpercent(getParamPpn());
		model.itemHeader.setPpnrp((double) Math.round(sumTotalPpn));

		Double sumTotalAfterdiscAfterppn = sumTotalAfterdisc12Withppn - discrpafterppn;		
		model.itemHeader.setAmountafterdiscafterppn((double) Math.round(sumTotalAfterdiscAfterppn));
		
		//::REFRESH

	}
	
	
	
}
