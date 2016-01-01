/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.erp.distribution.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.*;

/**
 *
 * @author yhawin
 */
@Entity
@Table(name="sysvar")
public class Sysvar implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="ID", length=20)
    private String id;
    
    @Column(name="GROUPSYSVAR", length=15)
    private String groupSysvar;
    
    @Column(name="DESCRIPSI", length=100)
    private String deskripsi;
    @Column(name="NOTES", length=200)
    private String notes;    
    @Column(name="TIPEDATA", length=10)
    private String tipeData;
    @Column(name="LENGHTDATA")
    private Integer lenghtData;

    @Column(name="PREFIX", length=3) 
    private String prefix;
    
    @Column(name="NILAISTRING1", length=200)
    private String nilaiString1;
    @Column(name="NILAISTRING22", length=200)    
    private String nilaiString2;
    @Column(name="NILAIBOL1") 
    private Boolean nilaiBol1;
    @Column(name="NILAIBOL2") 
    private Boolean nilaiBol2;

    @Column(name="NILAIINT1") 
    private Integer nilaiInt1;
    @Column(name="NILAIINT2") 
    private Integer nilaiInt2;
    @Column(name="NILAIDOUBLE1") 
    private Double nilaiDouble1;
    @Column(name="NILAIDOUBLE2") 
    private Double nilaiDouble2;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="NILAIDATE1") 
    private Date nilaiDate1;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="NILAIDATE2") 
    private Date nilaiDate2;
    @Column(name="NILAITIME1") 
    private Time nilaiTime1;
    @Column(name="NILAITIME2") 
    private Time nilaiTime2;

    public String getDeskripsi() {
        return deskripsi;
    }

    
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGroupSysvar() {
        return groupSysvar;
    }

    public void setGroupSysvar(String groupSysvar) {
        this.groupSysvar = groupSysvar;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTipeData() {
        return tipeData;
    }

    public void setTipeData(String tipeData) {
        this.tipeData = tipeData;
    }

    public Boolean getNilaiBol1() {
        return nilaiBol1;
    }

    public void setNilaiBol1(Boolean nilaiBol1) {
        this.nilaiBol1 = nilaiBol1;
    }

    public Boolean getNilaiBol2() {
        return nilaiBol2;
    }

    public void setNilaiBol2(Boolean nilaiBol2) {
        this.nilaiBol2 = nilaiBol2;
    }

    public String getNilaiString1() {
        return nilaiString1;
    }

    public void setNilaiString1(String nilaiString1) {
        this.nilaiString1 = nilaiString1;
    }

    public String getNilaiString2() {
        return nilaiString2;
    }

    public void setNilaiString2(String nilaiString2) {
        this.nilaiString2 = nilaiString2;
    }

    public Double getNilaiDouble1() {
        return nilaiDouble1;
    }

    public void setNilaiDouble1(Double nilaiDouble1) {
        this.nilaiDouble1 = nilaiDouble1;
    }

    public Double getNilaiDouble2() {
        return nilaiDouble2;
    }

    public void setNilaiDouble2(Double nilaiDouble2) {
        this.nilaiDouble2 = nilaiDouble2;
    }

    public Integer getNilaiInt1() {
        return nilaiInt1;
    }

    public void setNilaiInt1(Integer nilaiInt1) {
        this.nilaiInt1 = nilaiInt1;
    }

    public Integer getNilaiInt2() {
        return nilaiInt2;
    }

    public void setNilaiInt2(Integer nilaiInt2) {
        this.nilaiInt2 = nilaiInt2;
    }

    public Date getNilaiDate1() {
        return nilaiDate1;
    }

    public void setNilaiDate1(Date nilaiDate1) {
        this.nilaiDate1 = nilaiDate1;
    }

    public Date getNilaiDate2() {
        return nilaiDate2;
    }

    public void setNilaiDate2(Date nilaiDate2) {
        this.nilaiDate2 = nilaiDate2;
    }

    public Time getNilaiTime1() {
        return nilaiTime1;
    }

    public void setNilaiTime1(Time nilaiTime1) {
        this.nilaiTime1 = nilaiTime1;
    }

    public Time getNilaiTime2() {
        return nilaiTime2;
    }

    public void setNilaiTime2(Time nilaiTime2) {
        this.nilaiTime2 = nilaiTime2;
    }


    public Integer getLenghtData() {
		return lenghtData;
	}

	public void setLenghtData(Integer lenghtData) {
		this.lenghtData = lenghtData;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sysvar other = (Sysvar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return id + "";
	}


    
}
