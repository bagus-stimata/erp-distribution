package org.erp.distribution.model;

import javax.persistence.*;

import java.util.Date;

public class ZLapTemplate2 {
	private Long id;
	
	@Column(name="GRUP1")
	private	String grup1;
	@Column(name="GRUP2")
	private	String grup2;
	@Column(name="GRUP3")
	private	String grup3;
	@Column(name="GRUP4")
	private	String grup4;
	@Column(name="GRUP5")
	private	String grup5;

	@Column(name="STRING1")
	private	String string1;
	@Column(name="STRING2")
	private	String string2;
	@Column(name="STRING3")
	private	String string3;
	@Column(name="STRING4")
	private	String string4;
	@Column(name="STRING5")
	private	String string5;
	@Column(name="STRING6" )
	private	String string6;
	@Column(name="STRING7")
	private	String string7;
	@Column(name="STRING8")
	private	String string8;
	@Column(name="STRING9")
	private	String string9;
	@Column(name="STRING10")
	private	String string10;
	@Column(name="STRING11")
	private	String string11;
	@Column(name="STRING12")
	private	String string12;
	@Column(name="STRING13")
	private	String string13;
	@Column(name="STRING14")
	private	String string14;
	@Column(name="STRING15")
	private	String string15;

	@Column(name="INTEGER1")
	private int int1;
	@Column(name="INTEGER2")
	private int int2;
	@Column(name="INTEGER3")
	private int int3;
	@Column(name="INTEGER4")
	private int int4;
	@Column(name="INTEGER5")
	private int int5;
	@Column(name="INTEGER6")
	private int int6;
	@Column(name="INTEGER7")
	private int int7;
	@Column(name="INTEGER8")
	private int int8;
	@Column(name="INTEGER9")
	private int int9;
	@Column(name="INTEGER10")
	private int int10;

	
	@Column(name="DOUBLE1")
	private double double1;
	@Column(name="DOUBLE2")
	private double double2;
	@Column(name="DOUBLE3")
	private double double3;
	@Column(name="DOUBLE4")
	private double double4;
	@Column(name="DOUBLE5")
	private double double5;
	@Column(name="DOUBLE6")
	private double double6;
	@Column(name="DOUBLE7")
	private double double7;
	@Column(name="DOUBLE8")
	private double double8;
	@Column(name="DOUBLE9")
	private double double9;
	@Column(name="DOUBLE10")
	private double double10;
	
	@Column(name="DATE1")
	@Temporal(TemporalType.DATE)
	Date date1;
	@Column(name="DATE2")
	@Temporal(TemporalType.DATE)
	Date date2;
	@Column(name="DATE3")
	@Temporal(TemporalType.DATE)
	Date date3;
	@Column(name="DATE4")
	@Temporal(TemporalType.DATE)
	Date date4;
	@Column(name="DATE5")
	@Temporal(TemporalType.DATE)
	Date date5;

	
	private boolean bol1;
	private boolean bol2;
	private boolean bol3;
	private boolean bol4;
	private boolean bol5;
	
	public Long getId() {
		return id;
	}
	public String getGrup1() {
		return grup1;
	}
	public String getGrup2() {
		return grup2;
	}
	public String getGrup3() {
		return grup3;
	}
	public String getString1() {
		return string1;
	}
	public String getString2() {
		return string2;
	}
	public String getString3() {
		return string3;
	}
	public String getString4() {
		return string4;
	}
	public String getString5() {
		return string5;
	}
	public String getString6() {
		return string6;
	}
	public String getString7() {
		return string7;
	}
	public int getInt1() {
		return int1;
	}
	public int getInt2() {
		return int2;
	}
	public int getInt3() {
		return int3;
	}
	public int getInt4() {
		return int4;
	}
	public int getInt5() {
		return int5;
	}
	public double getDouble1() {
		return double1;
	}
	public double getDouble2() {
		return double2;
	}
	public double getDouble3() {
		return double3;
	}
	public double getDouble4() {
		return double4;
	}
	public double getDouble5() {
		return double5;
	}
	public Date getDate1() {
		return date1;
	}
	public Date getDate2() {
		return date2;
	}
	public Date getDate3() {
		return date3;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setGrup1(String grup1) {
		this.grup1 = grup1;
	}
	public void setGrup2(String grup2) {
		this.grup2 = grup2;
	}
	public void setGrup3(String grup3) {
		this.grup3 = grup3;
	}
	public void setString1(String string1) {
		this.string1 = string1;
	}
	public void setString2(String string2) {
		this.string2 = string2;
	}
	public void setString3(String string3) {
		this.string3 = string3;
	}
	public void setString4(String string4) {
		this.string4 = string4;
	}
	public void setString5(String string5) {
		this.string5 = string5;
	}
	public void setString6(String string6) {
		this.string6 = string6;
	}
	public void setString7(String string7) {
		this.string7 = string7;
	}
	public void setInt1(int int1) {
		this.int1 = int1;
	}
	public void setInt2(int int2) {
		this.int2 = int2;
	}
	public void setInt3(int int3) {
		this.int3 = int3;
	}
	public void setInt4(int int4) {
		this.int4 = int4;
	}
	public void setInt5(int int5) {
		this.int5 = int5;
	}
	public void setDouble1(double double1) {
		this.double1 = double1;
	}
	public void setDouble2(double double2) {
		this.double2 = double2;
	}
	public void setDouble3(double double3) {
		this.double3 = double3;
	}
	public void setDouble4(double double4) {
		this.double4 = double4;
	}
	public void setDouble5(double double5) {
		this.double5 = double5;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public void setDate3(Date date3) {
		this.date3 = date3;
	}
	public String getGrup4() {
		return grup4;
	}
	public String getGrup5() {
		return grup5;
	}
	public String getString8() {
		return string8;
	}
	public String getString9() {
		return string9;
	}
	public String getString10() {
		return string10;
	}
	public String getString11() {
		return string11;
	}
	public String getString12() {
		return string12;
	}
	public String getString13() {
		return string13;
	}
	public String getString14() {
		return string14;
	}
	public String getString15() {
		return string15;
	}
	public int getInt6() {
		return int6;
	}
	public int getInt7() {
		return int7;
	}
	public int getInt8() {
		return int8;
	}
	public int getInt9() {
		return int9;
	}
	public int getInt10() {
		return int10;
	}
	public double getDouble6() {
		return double6;
	}
	public double getDouble7() {
		return double7;
	}
	public double getDouble8() {
		return double8;
	}
	public double getDouble9() {
		return double9;
	}
	public double getDouble10() {
		return double10;
	}
	public Date getDate4() {
		return date4;
	}
	public Date getDate5() {
		return date5;
	}
	public boolean isBol1() {
		return bol1;
	}
	public boolean isBol2() {
		return bol2;
	}
	public boolean isBol3() {
		return bol3;
	}
	public boolean isBol4() {
		return bol4;
	}
	public boolean isBol5() {
		return bol5;
	}
	public void setGrup4(String grup4) {
		this.grup4 = grup4;
	}
	public void setGrup5(String grup5) {
		this.grup5 = grup5;
	}
	public void setString8(String string8) {
		this.string8 = string8;
	}
	public void setString9(String string9) {
		this.string9 = string9;
	}
	public void setString10(String string10) {
		this.string10 = string10;
	}
	public void setString11(String string11) {
		this.string11 = string11;
	}
	public void setString12(String string12) {
		this.string12 = string12;
	}
	public void setString13(String string13) {
		this.string13 = string13;
	}
	public void setString14(String string14) {
		this.string14 = string14;
	}
	public void setString15(String string15) {
		this.string15 = string15;
	}
	public void setInt6(int int6) {
		this.int6 = int6;
	}
	public void setInt7(int int7) {
		this.int7 = int7;
	}
	public void setInt8(int int8) {
		this.int8 = int8;
	}
	public void setInt9(int int9) {
		this.int9 = int9;
	}
	public void setInt10(int int10) {
		this.int10 = int10;
	}
	public void setDouble6(double double6) {
		this.double6 = double6;
	}
	public void setDouble7(double double7) {
		this.double7 = double7;
	}
	public void setDouble8(double double8) {
		this.double8 = double8;
	}
	public void setDouble9(double double9) {
		this.double9 = double9;
	}
	public void setDouble10(double double10) {
		this.double10 = double10;
	}
	public void setDate4(Date date4) {
		this.date4 = date4;
	}
	public void setDate5(Date date5) {
		this.date5 = date5;
	}
	public void setBol1(boolean bol1) {
		this.bol1 = bol1;
	}
	public void setBol2(boolean bol2) {
		this.bol2 = bol2;
	}
	public void setBol3(boolean bol3) {
		this.bol3 = bol3;
	}
	public void setBol4(boolean bol4) {
		this.bol4 = bol4;
	}
	public void setBol5(boolean bol5) {
		this.bol5 = bol5;
	}
	
	
	
}
