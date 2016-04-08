package org.erp.distribution.model.modelenum;

public enum EnumProteksiStatus {
    DO_NOTHING(0, "N", "Tidak ada operasi apapun"),
    WARNING(1, "W", "Sedang ada operasi"),
    REJECT(2, "R", "Sedang ada operasi penambahan");
    
    private int intCode;
    private String stringCode;
    private String description;
    
    private EnumProteksiStatus(int intCode, String strCode, String description){
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
