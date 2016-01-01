package org.erp.distribution.model.modelenum;

public enum EnumUserOtorize {
    ADMINISTRATOR(0, "ADMINISTRATOR", "ADMINISTRATOR SISTEM"),
    USER11(11, "SALESADMIN1", "Sales Admin"),
    USER12(12, "SALESADMIN2", "Sales Admin"),
    USER21(21, "WAREHOUSE1", "Gudang"),
    USER22(22, "WAREHOUSE2", "Gudang"),
    USER31(31, "ARADMIN1", "Pelunasan"),
    USER32(32, "ARADMIN2", "Pelunasan"),
    USER41(41, "CASHBANK1", "Cash Bank/Kasir"),
    USER42(42, "CASHBANK2", "Cash Bank/Kasir"),
    USER51(51, "ACCOUNTING1", "Accounting"),
    USER52(52, "ACCOUNTING2", "Accounting"),
    MANAGER1(4, "MANAGER1", "Manager Tingkat Pertama/UTAMA (Manager Paling Tinggi)"),
    MANAGER2(5, "MANAGER2", "Manager Tingkat Kedua (dibawah Manager Utama)"),
    
    //SERVICE HP DNA CELL
    ADMINSERVICEHPDANPENJ(101, "ADMINSERVICEHPDANPENJ", "Admin Service Hp dan Penjualan"),
    ADMINSERVICEHP(102, "ADMINSERVICEHP", "Admin Service HP"),
    TEKNISIHP(103, "TEKNISIHP", "Teknisi HP"),
    ADMINGUDANGUTAMAHP(104, "ADMINGUDANGUTAMAHP", "Admin Gudang Utama HP"),
    
    GUEST(7, "GUEST", "Guest");
    
    private int intCode;
    private String stringCode;
    private String description;
    
    private EnumUserOtorize(int intCode, String strCode, String description){
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
