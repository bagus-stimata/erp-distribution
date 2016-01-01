package org.erp.distribution.model.modelenum;

public enum EnumStatusService {
    BELUM(0, "BELUM", "Belum Service/Baru Masuk"),
    PROSES(1, "PROSES", "Dalam Proses Service"),
    SELESAI(2, "SELESAI", "Telah Selesai Service"),
    CANCEL(10, "CANCEL", "Batal Service");
    
    private int intCode;
    private String stringCode;
    private String description;
    
    private EnumStatusService(int intCode, String strCode, String description){
        this.intCode = intCode;
        this.stringCode = strCode;
        this.description = description;    
    }
    public String getStrCode(){
        return stringCode;
    }
    public int getIntCode(){
        return intCode;
    }
    public String getDescription(){
        return description;
    }

}
