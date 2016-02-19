package org.erp.distribution.util;

import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;

public class HeaderDetilHelperImpl implements HeaderDetilHelper{

	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();
	
	private FtSalesh ftSalesh = new FtSalesh();
	private FtSalesh newFtSalesh = new FtSalesh();
	private FtSalesd ftSalesd = new FtSalesd();
	private FtSalesd newFtSalesd = new FtSalesd();
	private Double ppnFloat =0.0;
	
	private KonversiProductAndStock konversiProductAndStock = new KonversiProductAndStockImpl();
	
	public HeaderDetilHelperImpl() {
		initVariable();
	}
	public HeaderDetilHelperImpl(FtSalesh ftSalesh) {
		this.ftSalesh = ftSalesh;		
		this.newFtSalesh = ftSalesd.getFtsaleshBean();
		initVariable();
	}
	public HeaderDetilHelperImpl(FtSalesd ftSalesd) {
		this.ftSalesd = ftSalesd;
		this.newFtSalesd = ftSalesd;
		this.konversiProductAndStock = new KonversiProductAndStockImpl(ftSalesd.getQty(), ftSalesd.getFproductBean());
		this.ftSalesh = ftSalesd.getFtsaleshBean();
		this.newFtSalesh = ftSalesd.getFtsaleshBean();
		initVariable();
	}
	public void initVariable(){
		ppnFloat = (transaksiHelper.getParamPpn()/100) + 1;
	}
	
	@Override
	public int getDetilQtyBes() {
		return konversiProductAndStock.getBesFromPcs();
	}
	@Override
	public int getDetilQtySed() {
		return konversiProductAndStock.getSedFromPcs();
	}
	@Override
	public int getDetilQtyKec() {
		return konversiProductAndStock.getKecFromPcs();
	}
	
	@Override
	public double getDetilSpriceAfterPpn() {
		double newSpriceAfterPpn = ftSalesd.getSprice() * ppnFloat;
		return newSpriceAfterPpn;
	}

	@Override
	public double getDetilSubtotal() {
		FProduct fProduct = new FProduct();
		fProduct = ftSalesd.getFproductBean();
		double pricePcsNoPpn = ftSalesd.getSprice() / fProduct.getConvfact1();
		double subtotalBeforedisc = pricePcsNoPpn * ftSalesd.getQty();			
		
		return subtotalBeforedisc;
	}

	@Override
	public double getDetilSubtotalAfterPpn() {
		FProduct fProduct = new FProduct();
		fProduct = ftSalesd.getFproductBean();
		
		double pricePcsAfterPpn = getDetilSpriceAfterPpn() / fProduct.getConvfact1();
		double subtotalBeforediscAfterPpn = pricePcsAfterPpn * ftSalesd.getQty();			
		return subtotalBeforediscAfterPpn;
	}

	
	@Override
	public double getDetilDisc1Rp() {
				
		double disc1persen = (double) ftSalesd.getDisc1()/100.0;
		double disc1rp = getDetilSubtotal() * disc1persen;
		
		return disc1rp;		
	}

	@Override
	public double getDetilDisc1RpAfterPpn() {
		
		double disc1persen = (double) ftSalesd.getDisc1()/100.0;
		double disc1rpafterppn = getDetilSubtotalAfterPpn() * disc1persen;
		
		return disc1rpafterppn;		
	}
	@Override
	public double getDetilSubtotalAfterDisc1() {
		double subtotalafterdisc1 = getDetilSubtotal() - getDetilDisc1Rp();
		return subtotalafterdisc1;
	}
	@Override
	public double getDetilSubtotalAfterDisc1AfterPpn() {
		double subtotalafterdisc1afterppn = getDetilSubtotalAfterPpn() - getDetilDisc1RpAfterPpn();
		return subtotalafterdisc1afterppn;
	}

	@Override
	public double getDetilDisc2Rp() {
		double disc2persen = (double) ftSalesd.getDisc2()/100.0;
		double disc2rp = getDetilSubtotalAfterDisc1() * disc2persen;
		return disc2rp;
	}

	@Override
	public double getDetilDisc2RpAfterPpn() {
		double disc2persen = (double) ftSalesd.getDisc2()/100.0;
		double disc2rpafterppn = getDetilSubtotalAfterDisc1AfterPpn() * disc2persen;
		return disc2rpafterppn;
	}

	@Override
	public double getDetilSubtotalAfterDisc2() {
		double subtotalafterdisc2 = getDetilSubtotalAfterDisc1() - getDetilDisc2Rp();
		return subtotalafterdisc2;
	}
	@Override
	public double getDetilSubtotalAfterDisc2AfterPpn() {
		double subtotalafterdisc2afterppn = getDetilSubtotalAfterDisc1AfterPpn() - getDetilDisc2RpAfterPpn();
		return subtotalafterdisc2afterppn;
	}

	@Override
	public double getDetilDiscNota1Rp() {
		double discNota1persen = (double) ftSalesd.getFtsaleshBean().getDisc1()/100.0;
		double discNota1rp = getDetilSubtotalAfterDisc2() * discNota1persen;
		
		return discNota1rp;		
	}
	@Override
	public double getDetilDiscNota1RpAfterPpn() {
		double discNota1persen = (double) ftSalesd.getFtsaleshBean().getDisc1()/100.0;
		double discNota1rpafterppn = getDetilSubtotalAfterDisc2AfterPpn() * discNota1persen;
		
		return discNota1rpafterppn;		
	}
	@Override
	public double getDetilSubtotalAfterDiscNota1() {
		double subtotalafterdiscNota1 = getDetilSubtotalAfterDisc2() - getDetilDiscNota1Rp();
		return subtotalafterdiscNota1;
	}
	@Override
	public double getDetilSubtotalAfterDiscNota1AfterPpn() {
		double subtotalafterdiscNota1afterppn = getDetilSubtotalAfterDisc2AfterPpn() - getDetilDiscNota1RpAfterPpn();
		return subtotalafterdiscNota1afterppn;
	}
	@Override
	public double getDetilDiscNotaRp() {
		double discNotaPersen = (double) ftSalesd.getFtsaleshBean().getDisc()/100.0;
		double discNotaRp = getDetilSubtotalAfterDiscNota1() * discNotaPersen;
		return discNotaRp;
	}
	@Override
	public double getDetilDiscNotaRpAfterPpn() {
		double discNotaPersen = (double) ftSalesd.getFtsaleshBean().getDisc()/100.0;
		double discNotaRpafterppn = getDetilSubtotalAfterDiscNota1AfterPpn() * discNotaPersen;
		return discNotaRpafterppn;
	}
	@Override
	public double getDetilSubtotalAfterDiscNota() {
		double subtotalafterdiscNota = getDetilSubtotalAfterDiscNota1() - getDetilDiscNotaRp();
		return subtotalafterdiscNota;
	}
	@Override
	public double getDetilSubtotalAfterDiscNotaAfterPpn() {
		double subtotalafterdiscNotaafterppn = getDetilSubtotalAfterDiscNota1AfterPpn() - getDetilDiscNotaRpAfterPpn();
		return subtotalafterdiscNotaafterppn;
	}
	
	@Override
	public FtSalesd getFillFtSalesd() {
		
		getFillFtSalesdOnly();
		
		//FILL FtSalesd From Nota
		newFtSalesd.setDiscNota1Rp(getDetilDiscNota1Rp());
		newFtSalesd.setDiscNota1RpAfterPpn(getDetilDiscNota1RpAfterPpn());
		
		newFtSalesd.setSubTotalAfterDiscNota1(getDetilSubtotalAfterDiscNota1());
		newFtSalesd.setSubTotalAfterDiscNota1AfterPpn(getDetilSubtotalAfterDiscNota1AfterPpn());

		newFtSalesd.setDiscNotaRp(getDetilDiscNotaRp());
		newFtSalesd.setDiscNotaRpAfterPpn(getDetilDiscNotaRpAfterPpn());
		
		newFtSalesd.setSubTotalAfterDiscNota(getDetilSubtotalAfterDiscNota());
		newFtSalesd.setSubTotalAfterDiscNotaAfterPpn(getDetilSubtotalAfterDiscNotaAfterPpn());
		
		return newFtSalesd;
	}
	@Override
	public FtSalesd getFillFtSalesdOnly() {
		
		newFtSalesd.setSpriceafterppn(getDetilSpriceAfterPpn());
		
		newFtSalesd.setQty1(getDetilQtyBes());
		newFtSalesd.setQty2(getDetilQtySed());
		newFtSalesd.setQty3(getDetilQtyKec());
		
		newFtSalesd.setSubtotal(getDetilSubtotal());
		newFtSalesd.setSubtotalafterppn(getDetilSubtotalAfterPpn());
		
		newFtSalesd.setDisc1rp(getDetilDisc1Rp());
		newFtSalesd.setDisc1rpafterppn(getDetilDisc1RpAfterPpn());
		
		newFtSalesd.setSubtotalafterdisc1(getDetilSubtotalAfterDisc1());
		newFtSalesd.setSubtotalafterdisc1afterppn(getDetilSubtotalAfterDisc1AfterPpn());
		
		newFtSalesd.setDisc2rp(getDetilDisc2Rp());
		newFtSalesd.setDisc2rpafterppn(getDetilDisc2RpAfterPpn());
		
		newFtSalesd.setSubtotalafterdisc2(getDetilSubtotalAfterDisc2());
		newFtSalesd.setSubtotalafterdisc2afterppn(getDetilSubtotalAfterDisc2AfterPpn());
		
		return newFtSalesd;
	}
	@Override
	public FtSalesd getFillFtSalesh() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
