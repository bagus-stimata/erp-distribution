package org.erp.distribution.util;

import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;

public class HeaderDetilSalesHelperImpl implements HeaderDetilSalesHelper{

	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();
	
	private FtSalesh ftSalesh = new FtSalesh();
	private FtSalesh newFtSalesh = new FtSalesh();
	private FtSalesd ftSalesd = new FtSalesd();
	private FtSalesd newFtSalesd = new FtSalesd();
	private Double ppnFloat =0.0;

	private boolean roundedDiscRp=false;
	private boolean roundedTotal=false;
	
	private KonversiProductAndStock konversiProductAndStock = new KonversiProductAndStockImpl();
	public HeaderDetilSalesHelperImpl() {
		initVariable();
	}
	public HeaderDetilSalesHelperImpl(FtSalesh ftSalesh) {
		this.ftSalesh = ftSalesh;		
		this.newFtSalesh = ftSalesh;
		initVariable();
	}
	public HeaderDetilSalesHelperImpl(FtSalesd ftSalesd) {
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
		
		if (isRoundedDiscRp()==true) disc1rp = Math.round((double) disc1rp);
		return disc1rp;		
	}

	@Override
	public double getDetilDisc1RpAfterPpn() {
		
		double disc1persen = (double) ftSalesd.getDisc1()/100.0;
		double disc1rpafterppn = getDetilSubtotalAfterPpn() * disc1persen;
		
		if (isRoundedDiscRp()==true) disc1rpafterppn = Math.round((double) disc1rpafterppn);
		return disc1rpafterppn;		
	}
	@Override
	public double getDetilSubtotalAfterDisc1() {
		double subtotalafterdisc1 = getDetilSubtotal() - getDetilDisc1Rp();

		if (isRoundedTotal()==true) subtotalafterdisc1 = Math.round((double) subtotalafterdisc1);
		return subtotalafterdisc1;
	}
	@Override
	public double getDetilSubtotalAfterDisc1AfterPpn() {
		double subtotalafterdisc1afterppn = getDetilSubtotalAfterPpn() - getDetilDisc1RpAfterPpn();

		if (isRoundedTotal()==true) subtotalafterdisc1afterppn = Math.round((double) subtotalafterdisc1afterppn);
		return subtotalafterdisc1afterppn;
	}

	@Override
	public double getDetilDisc2Rp() {
		double disc2persen = (double) ftSalesd.getDisc2()/100.0;
		double disc2rp = getDetilSubtotalAfterDisc1() * disc2persen;
		
		if (isRoundedDiscRp()==true) disc2rp = Math.round((double) disc2rp);
		return disc2rp;
	}
	@Override
	public double getDetilDisc2RpAfterPpn() {
		double disc2persen = (double) ftSalesd.getDisc2()/100.0;
		double disc2rpafterppn = getDetilSubtotalAfterDisc1AfterPpn() * disc2persen;

		if (isRoundedDiscRp()==true) disc2rpafterppn = Math.round((double) disc2rpafterppn);
		return disc2rpafterppn;
	}

	@Override
	public double getDetilSubtotalAfterDisc2() {
		double subtotalafterdisc2 = getDetilSubtotalAfterDisc1() - getDetilDisc2Rp();
		
		if (isRoundedTotal()==true) subtotalafterdisc2 = Math.round((double) subtotalafterdisc2);
		return subtotalafterdisc2;
	}
	@Override
	public double getDetilSubtotalAfterDisc2AfterPpn() {
		double subtotalafterdisc2afterppn = getDetilSubtotalAfterDisc1AfterPpn() - getDetilDisc2RpAfterPpn();

		if (isRoundedTotal()==true) subtotalafterdisc2afterppn = Math.round((double) subtotalafterdisc2afterppn);
		return subtotalafterdisc2afterppn;
	}
	
	@Override
	public double getDetilDiscNota1Rp() {
		double discNota1persen = (double) ftSalesd.getFtsaleshBean().getDisc1()/100.0;
		double discNota1rp = getDetilSubtotalAfterDisc2() * discNota1persen;
		
		if (isRoundedDiscRp()==true) discNota1rp = Math.round((double) discNota1rp);
		return discNota1rp;		
	}
	@Override
	public double getDetilDiscNota1RpAfterPpn() {
		double discNota1persen = (double) ftSalesd.getFtsaleshBean().getDisc1()/100.0;
		double discNota1rpafterppn = getDetilSubtotalAfterDisc2AfterPpn() * discNota1persen;
		
		if (isRoundedDiscRp()==true) discNota1rpafterppn = Math.round((double) discNota1rpafterppn);
		return discNota1rpafterppn;		
	}
	
	@Override
	public double getDetilSubtotalAfterDiscNota1() {
		double subtotalafterdiscNota1 = getDetilSubtotalAfterDisc2() - getDetilDiscNota1Rp();

		if (isRoundedTotal()==true) subtotalafterdiscNota1 = Math.round((double) subtotalafterdiscNota1);
		return subtotalafterdiscNota1;
	}
	
	@Override
	public double getDetilSubtotalAfterDiscNota1AfterPpn() {
		double subtotalafterdiscNota1afterppn = getDetilSubtotalAfterDisc2AfterPpn() - getDetilDiscNota1RpAfterPpn();

		if (isRoundedTotal()==true) subtotalafterdiscNota1afterppn = Math.round((double) subtotalafterdiscNota1afterppn);
		return subtotalafterdiscNota1afterppn;
	}
	@Override
	public double getDetilDiscNotaRp() {
		double discNotaPersen = (double) ftSalesd.getFtsaleshBean().getDisc()/100.0;
		double discNotaRp = getDetilSubtotalAfterDiscNota1() * discNotaPersen;

		if (isRoundedDiscRp()==true) discNotaRp = Math.round((double) discNotaRp);
		return discNotaRp;
	}
	@Override
	public double getDetilDiscNotaRpAfterPpn() {
		double discNotaPersen = (double) ftSalesd.getFtsaleshBean().getDisc()/100.0;
		double discNotaRpafterppn = getDetilSubtotalAfterDiscNota1AfterPpn() * discNotaPersen;

		if (isRoundedDiscRp()==true) discNotaRpafterppn = Math.round((double) discNotaRpafterppn);
		return discNotaRpafterppn;
	}
	
	@Override
	public double getDetilSubtotalAfterDiscNota() {
		double subtotalafterdiscNota = getDetilSubtotalAfterDiscNota1() - getDetilDiscNotaRp();

		if (isRoundedTotal()==true) subtotalafterdiscNota = Math.round((double) subtotalafterdiscNota);
		return subtotalafterdiscNota;
	}
	@Override
	public double getDetilSubtotalAfterDiscNotaAfterPpn() {
		double subtotalafterdiscNotaafterppn = getDetilSubtotalAfterDiscNota1AfterPpn() - getDetilDiscNotaRpAfterPpn();

		if (isRoundedTotal()==true) subtotalafterdiscNotaafterppn = Math.round((double) subtotalafterdiscNotaafterppn);
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
		
		//SUBTOTAL AFTER DISC SAMA DENGAN SUBTOTAL AFTER DISC2
		newFtSalesd.setSubtotalafterdisc2(getDetilSubtotalAfterDisc2());
		newFtSalesd.setSubtotalafterdisc2afterppn(getDetilSubtotalAfterDisc2AfterPpn());
		newFtSalesd.setSubtotalafterdisc(getDetilSubtotalAfterDisc2());
		newFtSalesd.setSubtotalafterdiscafterppn(getDetilSubtotalAfterDisc2AfterPpn());
		
		return newFtSalesd;
	}
	

	
//#####FSALESH
	
	@Override
	public double getHeaderAmount() {
		return ftSalesh.getAmount();
	}
	@Override
	public double getHeaderAmountAfterPpn() {
		double newAmountAfterPpn = ftSalesh.getAmount() * ppnFloat;
		return newAmountAfterPpn;
	}
	
	@Override	
	public double getHeaderDisc1Rp() {
		double disc1persen = (double) ftSalesh.getDisc1()/100.0;
		double disc1rp = getHeaderAmount() * disc1persen;
		
		if (isRoundedDiscRp()==true) disc1rp = Math.round((double) disc1rp);
		return disc1rp;		
	}
	@Override
	public double getHeaderDisc1RpAfterPpn() {
		
		double disc1persen = (double) ftSalesh.getDisc1()/100.0;
		double disc1rpafterppn = getHeaderAmountAfterPpn() * disc1persen;
		
		if (isRoundedDiscRp()==true) disc1rpafterppn = Math.round((double) disc1rpafterppn);
		return disc1rpafterppn;		
	}
	
	@Override	
	public double getHeaderDisc2Rp() {
		double disc2persen = (double) ftSalesh.getDisc2()/100.0;
		double disc2rp = getHeaderAmount() * disc2persen;
		
		if (isRoundedDiscRp()==true) disc2rp = Math.round((double) disc2rp);
		return disc2rp;		
	}
	@Override
	public double getHeaderDisc2RpAfterPpn() {
		
		double disc2persen = (double) ftSalesh.getDisc2()/100.0;
		double disc2rpafterppn = getHeaderAmountAfterPpn() * disc2persen;
		
		if (isRoundedDiscRp()==true) disc2rpafterppn = Math.round((double) disc2rpafterppn);
		return disc2rpafterppn;		
	}
	
	
	@Override
	public double getHeaderSubtotalAfterDisc12() {
		double subtotalafterdisc12 = getHeaderAmount() - getHeaderDisc1Rp()-getHeaderDisc2Rp();

		if (isRoundedTotal()==true) subtotalafterdisc12 = Math.round((double) subtotalafterdisc12);
		return subtotalafterdisc12;
	}
	@Override
	public double getHeaderSubtotalAfterDisc12AfterPpn() {
		double subtotalafterdisc12afterppn = getHeaderAmountAfterPpn() - getHeaderDisc1RpAfterPpn()-getHeaderDisc2RpAfterPpn();

		if (isRoundedTotal()==true) subtotalafterdisc12afterppn = Math.round((double) subtotalafterdisc12afterppn);
		return subtotalafterdisc12afterppn;
	}
	
	@Override	
	public double getHeaderDiscRp() {
		double discpersen = (double) ftSalesh.getDisc()/100.0;
		double discrp = getHeaderSubtotalAfterDisc12() * discpersen;

		if (isRoundedDiscRp()==true) discrp = Math.round((double) discrp);
		return discrp;
	}
	@Override
	public double getHeaderDiscRpAfterPpn() {
		double discpersen = (double) ftSalesh.getDisc()/100.0;
		double discrpafterppn = getHeaderSubtotalAfterDisc12AfterPpn() * discpersen;

		if (isRoundedDiscRp()==true) discrpafterppn = Math.round((double) discrpafterppn);
		return discrpafterppn;
	}
	
	@Override
	public double getHeaderSubtotalAfterDisc() {
		double subtotalafterdisc = getHeaderSubtotalAfterDisc12() - getHeaderDiscRp();

		if (isRoundedTotal()==true) subtotalafterdisc = Math.round((double) subtotalafterdisc);
		return subtotalafterdisc;
	}
	@Override
	public double getHeaderSubtotalAfterDiscAfterPpn() {
		double subtotalafterdiscafterppn = getHeaderSubtotalAfterDisc12AfterPpn() - getHeaderDiscRpAfterPpn();

		if (isRoundedTotal()==true) subtotalafterdiscafterppn = Math.round((double) subtotalafterdiscafterppn);
		return subtotalafterdiscafterppn;
	}
	
	@Override
	public FtSalesh getFillFtSalesh() {
		newFtSalesh.setAmount(getHeaderAmount());
		newFtSalesh.setAmountafterppn(getHeaderAmountAfterPpn());
		newFtSalesh.setDisc1rp(getHeaderDisc1Rp());
		newFtSalesh.setDisc1rpafterppn(getHeaderDisc1RpAfterPpn());
		newFtSalesh.setDisc2rp(getHeaderDisc2Rp());
		newFtSalesh.setDisc2rpafterppn(getHeaderDisc2RpAfterPpn());
		
		newFtSalesh.setAmountafterdisc12(getHeaderSubtotalAfterDisc12());
		newFtSalesh.setAmountafterdisc12afterppn(getHeaderSubtotalAfterDisc12AfterPpn());

		newFtSalesh.setDiscrp(getHeaderDiscRp());
		newFtSalesh.setDiscrpafterppn(getHeaderDiscRpAfterPpn());
		
		newFtSalesh.setAmountafterdisc(getHeaderSubtotalAfterDisc());
		newFtSalesh.setAmountafterdiscafterppn(getHeaderSubtotalAfterDiscAfterPpn());
		
		return newFtSalesh;
	}
	public boolean isRoundedDiscRp() {
		return roundedDiscRp;
	}
	public boolean isRoundedTotal() {
		return roundedTotal;
	}
	public void setRoundedDiscRp(boolean roundedDiscRp) {
		this.roundedDiscRp = roundedDiscRp;
	}
	public void setRoundedTotal(boolean roundedTotal) {
		this.roundedTotal = roundedTotal;
	}
	public void setSprice(double newSPrice){
		ftSalesd.setSprice(newSPrice);
	}
	public FtSalesh getFtSalesh() {
		return ftSalesh;
	}
	public FtSalesh getNewFtSalesh() {
		return newFtSalesh;
	}
	public FtSalesd getFtSalesd() {
		return ftSalesd;
	}
	public FtSalesd getNewFtSalesd() {
		return newFtSalesd;
	}
	public Double getPpnFloat() {
		return ppnFloat;
	}
	public void setFtSalesh(FtSalesh ftSalesh) {
		this.ftSalesh = ftSalesh;
	}
	public void setNewFtSalesh(FtSalesh newFtSalesh) {
		this.newFtSalesh = newFtSalesh;
	}
	public void setFtSalesd(FtSalesd ftSalesd) {
		this.ftSalesd = ftSalesd;
	}
	public void setNewFtSalesd(FtSalesd newFtSalesd) {
		this.newFtSalesd = newFtSalesd;
	}
	public void setPpnFloat(Double ppnFloat) {
		this.ppnFloat = ppnFloat;
	}
	
	
	
	
}
