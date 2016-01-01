package org.erp.distribution.model.modelenum;

public enum EnumOperationStatus {
    OPEN(0, "OPEN", "Tidak ada operasi apapun"),
    CLOSE(1, "CLOSE", "Sedang ada operasi"),
    ADDING(2, "ADDING", "Sedang ada operasi penambahan"),
    EDITING(3, "EDITING", "Sedang ada operasi editing"),
    OTHER(7, "OTHER", "Other Reserved");
    
    private int intCode;
    private String stringCode;
    private String description;
    
    private EnumOperationStatus(int intCode, String strCode, String description){
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
