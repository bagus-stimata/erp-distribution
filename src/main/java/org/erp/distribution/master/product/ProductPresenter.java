package org.erp.distribution.master.product;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class ProductPresenter implements ClickListener, ValueChangeListener, ItemClickListener, BlurListener{
	private ProductModel model;
	private ProductView view;
	
	public ProductPresenter(ProductModel model, ProductView view){
		this.model = model;
		this.view = view;
		
		initListener();
	}
	
	public void initListener(){
		view.getBtnNewForm().addClickListener(this);
		view.getBtnEditForm().addClickListener(this);
		view.getBtnCancelForm().addClickListener(this);
		view.getBtnDeleteForm().addClickListener(this);
		view.getBtnHelp().addClickListener(this);
		view.getBtnPrint().addClickListener(this);
		view.getBtnSaveForm().addClickListener(this);
		view.getBtnSearch().addClickListener(this);

		view.getFieldPprice().addBlurListener(this);
		view.getFieldPpriceafterppn().addBlurListener(this);
		view.getFieldSprice().addBlurListener(this);
		view.getFieldSpriceafterppn().addBlurListener(this);
		
		view.getTable().addItemClickListener(this);
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
	}
	
	@Override
	public void blur(BlurEvent event) {
		if (event.getComponent()==view.getFieldPprice()) {
			double price = 0.0;
			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())) {
				price = model.getNewItemHeader().getPprice() + (model.getNewItemHeader().getPprice()*0.1);
				model.newItemHeader.setPpriceafterppn(price);
			} else {
				price = model.getItemHeader().getPprice() + (model.getItemHeader().getPprice()*0.1);				
				model.itemHeader.setPpriceafterppn(price);
			}
		} else if (event.getComponent()==view.getFieldPpriceafterppn()) {
			double price =0.0;
			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())) {
				price = model.getNewItemHeader().getPpriceafterppn() / 1.1;				
				model.newItemHeader.setPprice(price);
			}else {
				price = model.getItemHeader().getPpriceafterppn() / 1.1;				
				model.itemHeader.setPprice(price);				
			}
			
		} else if (event.getComponent()==view.getFieldSprice()) {
			double price =0.0;
			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())) {
				price = model.getNewItemHeader().getSprice() + (model.getNewItemHeader().getSprice()*0.1);
				model.newItemHeader.setSpriceafterppn(price);
			}else {
				price = model.getItemHeader().getSprice() + (model.getItemHeader().getSprice()*0.1);				
				model.itemHeader.setSpriceafterppn(price);
			}			
		} else if (event.getComponent()==view.getFieldSpriceafterppn()) {
			
			double price =0.0;
			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())) {
				price = model.getNewItemHeader().getSpriceafterppn() / 1.1;
				model.newItemHeader.setSprice(price);			
			}else {
				price = model.getItemHeader().getSpriceafterppn() / 1.1;
				model.itemHeader.setSprice(price);			
			}
		}
		//KOMPUTER
		if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())) {
			model.getBinderHeader().setItemDataSource(model.getNewItemHeader());
		}else {
			model.getBinderHeader().setItemDataSource(model.getItemHeader());
		}
		
	}
	
	@Override
	public void itemClick(ItemClickEvent event) {
		Object itemId = event.getItemId();
		Item item = view.getTable().getItem(itemId);
		boolean entitySelected = item != null;
		// modify visibility of form and delete button if an item is selected
		view.getFormLayout().setVisible(entitySelected);
		view.getBtnDeleteForm().setEnabled(entitySelected);
		
		if (entitySelected) {
			
			model.itemHeader = new FProduct();
			model.itemHeader = model.getBeanItemContainerHeader().getItem(itemId).getBean();
			model.getBinderHeader().setItemDataSource(model.itemHeader);
			
			if (event.isDoubleClick()){
				view.showWindowForm();
			}
		}
		
		view.bindAndBuildFieldGroupComponent();		
		model.setOperationStatus(EnumOperationStatus.EDITING.getStrCode());		
		view.setFormButtonAndTextState();
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		//Antisipasi
		if (model.getOperationStatus()==null) model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		if (model.getOperationStatus().equals("")) model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		
		if (event.getButton() == view.getBtnNewForm()) {
			addItem();				
			view.showWindowForm();
		} else if (event.getButton() == view.getBtnEditForm()) {
			view.showWindowForm();
		} else if (event.getButton() == view.getBtnDeleteForm()) {
			deleteForm();
		} else if (event.getButton() == view.getBtnSaveForm()){
			 ConfirmDialog commitDialog = ConfirmDialog.show(view.getUI(), "Konfirmasi Simpan", "Simpan Data?", "Save", "No",
	                new ConfirmDialog.Listener() {
	                    public void onClose(ConfirmDialog dialog) {
	                        if (dialog.isConfirmed()) {
	                            // Confirmed to continue
	                    		try {			
	                    			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
	                    				if (insertAndValidateForm()==0){		                    					
	                    					view.getTable().focus();
	        	                        	view.closeWindowForm();
	                    				}else {
	                    					view.focustIdOrDesc();
	                    				}
	                    			} else if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
	                    				if (updateAndValidateForm()==0){				                    			
	                    					view.getTable().focus();		                    					
	        	                        	view.closeWindowForm();
	                    				}else {
	                    					view.focustIdOrDesc();
	                    				}
	                    			}
	                    			
	                    		} catch(Exception ex) {
	                    			Notification.show("Terjadi Kesalahan Simpan");
	                    		}
	                    		
	                        } else {
	                        	view.focustIdOrDesc();
	                        }
	                    }
	                });	        
			 commitDialog.setStyleName("dialog");
			 commitDialog.getOkButton().setStyleName("small");
			 commitDialog.getCancelButton().setStyleName("small");
			 //Jangan lupa
			 commitDialog.focus();
			
		} else if(event.getButton() == view.getBtnCancelForm()) {
			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
				discardForm();
			} else if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode()) || 
					model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
				if (view.getFormLayout().isVisible()){
					discardForm();
				}				
			}else{	
				view.getTable().focus();
			}		
			
//			discardForm(operationStatus);
		} else if (event.getButton() == view.getBtnSearch()) {
			searchForm();
		} else if (event.getButton() == view.getBtnPrint()){			
			printForm();
		}else if (event.getButton() == view.getBtnHelp()){
			helpForm();
		}
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
		
	}
	
	public void addItem(){
		try {
			
			//1. Deklarasikan object awal
			model.newItemHeader = new FProduct();
			//Karena tidak pisa pake null representation
			
			model.newItemHeader.setPcode("New");
			model.newItemHeader.setBarcode("");
			model.newItemHeader.setConvfact1(1);
			model.newItemHeader.setConvfact2(1);
			model.newItemHeader.setDistcode("");
			model.newItemHeader.setPackaging("");
			model.newItemHeader.setPname("");
			model.newItemHeader.setPprice(0.0);
			model.newItemHeader.setPpriceafterppn(0.0);
			model.newItemHeader.setPprice2(0.0);
			model.newItemHeader.setProdclass(1);
			model.newItemHeader.setShortcode("");
			model.newItemHeader.setShortname("");
			model.newItemHeader.setShortpackaging("");
			model.newItemHeader.setSprice(0.0);
			model.newItemHeader.setSpriceafterppn(0.0);
			model.newItemHeader.setSprice2(0.0);
			model.newItemHeader.setStatusactive(true);
			model.newItemHeader.setUom1("KRT");
			model.newItemHeader.setUom2("PAK");
			model.newItemHeader.setUom3("PCS");
			model.newItemHeader.setWeight(0.0);
			
			
			//2. SET NEW DATA ITEM TO BINDER
			model.getBinderHeader().setItemDataSource(model.newItemHeader);
			
			//3. REFRESH VIEW AND SHOW FORM LAYOUT
			view.bindAndBuildFieldGroupComponent();			
			view.getFormLayout().setVisible(true);
			
			//3.SET FORM STATE AND BUTTON STATE
			model.setOperationStatus(EnumOperationStatus.ADDING.getStrCode());
			view.setFormButtonAndTextState();
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	public int discardForm(){
		 ConfirmDialog discardDialog = ConfirmDialog.show(view.getUI(),"Konfirmasi Discard", 
				 "Yakin keluar mode editing(discard)?", "Yes", "No",
	                new ConfirmDialog.Listener() {
	                    public void onClose(ConfirmDialog dialog) {
	                        if (dialog.isConfirmed()) {
	                            // Confirmed to continue
	                    		try {			
	                    			//1. form discard
	                    			model.getBinderHeader().discard();
	                    			model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
	                    			view.setFormButtonAndTextState();
	                    			//2. focust ke table
	                    			view.getTable().focus();
	                    			
	                    		} catch(Exception ex) {
	                    			Notification.show("Terjadi Kesalahan Discard", Notification.TYPE_TRAY_NOTIFICATION);
	                    		}
	                        	view.closeWindowForm();
	                        } else {
	                        	view.focustIdOrDesc();
	                         
	                        }
	                    }
	                });	        
		 discardDialog.setStyleName("dialog");
		 discardDialog.getOkButton().setStyleName("small");
		 discardDialog.getCancelButton().setStyleName("small");
		 //Jangan lupa
		 discardDialog.focus();
		
		return 0;
		
	}
	public void deleteForm(){
		final Object itemId = view.getTable().getValue();
		
		if (itemId != null){			
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                    	try {
                    		model.getfProductJpaService().removeObject(model.getItemHeader());
                    		
                    		model.getBeanItemContainerHeader().removeItem(itemId);
                    		
                    		view.getTable().refreshRowCache();
                    		view.setDisplay();
                			Notification.show("Delete Sukses", Notification.TYPE_TRAY_NOTIFICATION);		                        		
                    	} catch(Exception ex) {
                			Notification.show("Error Delete!!", Notification.TYPE_TRAY_NOTIFICATION);		                        		
                    	}
                    } else {
                    // User did not confirm
                    }
					view.getTable().focus();
				}
			};
			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Hapus", "Yakin akan hapus data?", 
					 "Oke Delete..", "Cancel", konfirmDialogListener);
			 
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
		
		}else {
			Notification.show("Tidak ada yang dipilih!!!", Notification.TYPE_TRAY_NOTIFICATION);
		}
	}

	public int insertAndValidateForm(){
		boolean kodeValid = true;
		boolean inputValid = true;
		String theId = (String) view.getFieldId().getConvertedValue();
		
		if (theId.trim().equalsIgnoreCase("New")){
			kodeValid = false;
			Notification.show("ID tidak boleh New!!", Notification.TYPE_TRAY_NOTIFICATION);
			view.focustIdOrDesc();			
			return 1;			
		}
		
//		//VALIDASI KODE TIDAK DIPERLUKAN LAGI 
//		if (model.getfAreaJpaService().findAll(theId).size()>0){
//			kodeValid = false;
//			Notification.show("Kode Sudah Terpakai!!.");	
//			view.focustIdOrDesc();
//			return 1;			
//		}
		
		//2. Additional Validasi input di handle oleh form Validator
		//3. Aksi Save
		if (kodeValid && inputValid) {
			try {				
				//3.1 commit form >> MASUKKAN KE DATABASE
				model.getBinderHeader().commit(); //COMMIT TETAP PAKE NEW TAPI CLIENT SIDE
				//PAKSA KONVERSION FAKTORI YANG NULL atau 0 KE 1
				try{
					if (model.getNewItemHeader().getConvfact1()<=0) {
						model.getNewItemHeader().setConvfact1(1);
					}
				} catch(Exception ex){}
				try{
					if (model.getNewItemHeader().getConvfact2()<=0) {
						model.getNewItemHeader().setConvfact2(1);
					}
				} catch(Exception ex){}
				
				model.getfProductJpaService().createObject(model.getNewItemHeader());
				
				
				//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel
				model.getBeanItemContainerHeader().addItem(model.getNewItemHeader());
				view.getTable().refreshRowCache();
				
				//3.3. atur kondisi status form
				view.setDisplay();
				model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
				view.setFormButtonAndTextState();
				//3.4. Refresh lagi container >> SUDAH TIDAK DIPERLUKAN LAGI OLEH KARENA OPERSI //3.2
				
				Notification.show("Penambahan Sukses!!!", Notification.TYPE_TRAY_NOTIFICATION);
				try{
					view.getTable().focus();				
				} catch(Exception ex){}
				
			} catch(Exception ex) {
				
				
				Notification.show("Terjadi Kesalahan Insert data!!!", Notification.TYPE_TRAY_NOTIFICATION);
				view.focustIdOrDesc();
				ex.printStackTrace();
				return 1;
			}
		} else {
			return 2;
		}
		
		
		return 0;
	}
	
	public int updateAndValidateForm(){
		try{
			//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel >> dan database
			model.getBinderHeader().commit();
			model.getfProductJpaService().updateObject(model.getItemHeader());
			
			view.getTable().refreshRowCache();
			
			//3.3. atur kondisi status form
			view.setDisplay();
			model.setOperationStatus(EnumOperationStatus.EDITING.getStrCode());
			view.setFormButtonAndTextState();
			//3.4. Refresh lagi container >> SUDAH TIDAK DIPERLUKAN LAGI OLEH KARENA OPERSI //3.2
			
			Notification.show("UPDATE Sukses!!!", Notification.TYPE_TRAY_NOTIFICATION);
			try{
				view.getTable().focus();				
			} catch(Exception ex){}
			
		} catch(Exception ex){
			view.focustIdOrDesc();
			return 1;
		}
		return 0;
	}
	
	public int searchForm(){
		//1. Remove filter dan Refresh container dalulu dahulu
		model.reload();
		model.getBeanItemContainerHeader().removeAllContainerFilters();
		
		//2. Baru Kasih Filter Lagi
//		String theRefno = view.getFieldSearchByRefno().getValue().toString().trim();
		String theId = view.getFieldSearchById().getValue().toString().trim();
		String theDesc = view.getFieldSearchByDesc().getValue().toString().trim();
		
		
//		Filter filter1 = new SimpleStringFilter("refno", theRefno, true, false);
		Filter filter2 = new Or(new SimpleStringFilter("pcode", theId, true, false));
		Filter filter3 = new Or(new SimpleStringFilter("pname", theDesc, true, false));
//		model.getBeanItemContainerBukugiro().addContainerFilter(filter1);
		model.getBeanItemContainerHeader().addContainerFilter(filter2);
		model.getBeanItemContainerHeader().addContainerFilter(filter3);
		
		//3. Refresh TABLE
		view.setDisplay();
		view.getTable().refreshRowCache();
		//4. Focus KE TABLE
		view.getTable().focus();
		
		return 0;
		
	}
	
	public void printForm(){	
		
		String basepath = VaadinService.getCurrent()
	            .getBaseDirectory().getAbsolutePath();
		String filePathDestination = basepath + "/Product.xls";
	
		
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Produk");

        Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
        data.put(1, new Object[] {"GRUP ID","GROUP", "KODE", "NAMA BARANG", "Packaging", "CONVFACT1", "CONVFACT2", 
        		"Harga Beli-PPN", "Harga Beli+PPN", "PCS Beli+PPN ", "Harga Jual-PPN", "Harga Jual+PPN", "PCS Jual+PPN"});
        
        Collection itemIds = model.getBeanItemContainerHeader().getItemIds();
        int lastRow = 1;
        for (Object itemId:itemIds) {
        	FProduct domain = new FProduct();
        	domain = model.getBeanItemContainerHeader().getItem(itemId).getBean();
        	lastRow++;
	        data.put(lastRow, new Object[] {domain.getFproductgroupBean().getId(),  domain.getFproductgroupBean().getDescription(), 
	        		domain.getPcode(), domain.getPname(), domain.getPackaging(), domain.getConvfact1(), domain.getConvfact2(),
	        		domain.getPprice(), domain.getPprice()*1.1, domain.getPprice()*1.1/domain.getConvfact1()
	        		, domain.getSprice(), domain.getSprice()*1.1, domain.getSprice()*1.1/domain.getConvfact1()});
        }
        Set<Integer> keyset = data.keySet();
        int rownum = 0;
        for (Integer key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof Date)
                    cell.setCellValue((Date)obj);
                else if(obj instanceof Boolean)
                    cell.setCellValue((Boolean)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }

        try {
            FileOutputStream out =
                    new FileOutputStream(new File(filePathDestination));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        
		Resource res = new FileResource(new File(filePathDestination));		
		FileDownloader fd = new FileDownloader(res);
		
		fd.extend(view.getBtnPrint());
	
	}
	
	public void helpForm(){
	}
	public ProductModel getModel() {
		return model;
	}
	public void setModel(ProductModel model) {
		this.model = model;
	}
	public ProductView getView() {
		return view;
	}
	public void setView(ProductView view) {
		this.view = view;
	}


	
	
}
